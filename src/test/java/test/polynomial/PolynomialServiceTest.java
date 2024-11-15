package test.polynomial.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.polynomial.entities.Expression;
import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;
import test.polynomial.exceptions.ParsingException;
import test.polynomial.helpers.ParseHelper;
import test.polynomial.helpers.PolynomialHelper;
import test.polynomial.repositories.ExpressionRepository;
import test.polynomial.repositories.PolynomialRepository;
import test.polynomial.repositories.TermRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PolynomialServiceTest {

    @Mock
    private ExpressionRepository expressionRepository;

    @Mock
    private PolynomialRepository polynomialRepository;

    @Mock
    private TermRepository termRepository;

    @InjectMocks
    private PolynomialService polynomialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParse_NewExpression() throws ParsingException {
        String expression = "3x^2+5x+7";

        // Simulate no existing expression in DB
        when(expressionRepository.findByOriginalExpression(anyString())).thenReturn(Optional.empty());

        // Simulate saving behavior
        Expression mockExpression = new Expression();
        when(expressionRepository.save(any(Expression.class))).thenReturn(mockExpression);

        Polynomial mockPolynomial = new Polynomial();
        mockPolynomial.setSimplified("3x^2 + 5x + 7");
        when(polynomialRepository.save(any(Polynomial.class))).thenReturn(mockPolynomial);

        // Run the method
        String result = polynomialService.parse(expression);

        // Verify behavior
        verify(expressionRepository, times(1)).findByOriginalExpression(expression);
        verify(expressionRepository, times(1)).save(any(Expression.class));
        verify(polynomialRepository, times(1)).save(any(Polynomial.class));

        assertEquals("3*x^2 + 5*x + 7", result);
    }

    @Test
    void testParse_ExistingExpression() throws ParsingException {
        String expression = "3x^2+5x+7";

        // Simulate existing expression in DB
        Polynomial mockPolynomial = new Polynomial();
        mockPolynomial.setSimplified("3x^2 + 5x + 7");

        Expression mockExpression = new Expression();
        mockExpression.setOriginalExpression(expression);
        mockExpression.setPolynomial(mockPolynomial);

        when(expressionRepository.findByOriginalExpression(expression)).thenReturn(Optional.of(mockExpression));

        // Run the method
        String result = polynomialService.parse(expression);

        // Verify behavior
        verify(expressionRepository, times(1)).findByOriginalExpression(expression);
        verify(expressionRepository, never()).save(any(Expression.class));
        verify(polynomialRepository, never()).save(any(Polynomial.class));

        assertEquals("3x^2 + 5x + 7", result);
    }

    @Test
    void testSolve_ValidArgument() throws ParsingException {
        String expression = "3*x^2+5*x+7";
        String argumentValue = "2";

        Polynomial mockPolynomial = new Polynomial();
        mockPolynomial.setTerms(Arrays.asList(
                new Term(3, 2), // 3x^2
                new Term(5, 1), // 5x
                new Term(7, 0)  // 7
        ));

        Expression mockExpression = new Expression();
        mockExpression.setPolynomial(mockPolynomial);

        when(expressionRepository.findByOriginalExpression(expression)).thenReturn(Optional.of(mockExpression));
        when(polynomialRepository.save(any(Polynomial.class))).thenReturn(mockPolynomial);

        // Mock PolynomialHelper.solve
        int mockResult = 29; // 3(2^2) + 5(2) + 7
        mockStatic(PolynomialHelper.class);
        mockStatic(ParseHelper.class);
        when(PolynomialHelper.solve(any(Polynomial.class), eq(2))).thenReturn(mockResult);

        // Run the method
        int result = polynomialService.solve(expression, argumentValue);

        // Verify behavior
        verify(expressionRepository, times(1)).findByOriginalExpression(expression);
        assertEquals(mockResult, result);
    }

    @Test
    void testSolve_InvalidArgument() {
        String expression = "3x^2 + 5x + 7";
        String invalidArgument = "abc";

        ParsingException exception = assertThrows(ParsingException.class, () -> {
            polynomialService.solve(expression, invalidArgument);
        });

        assertEquals("Can parse argument. You passed \"abc\"", exception.getMessage());
    }
}
