package test.polynomial.helpers;

import org.junit.jupiter.api.Test;
import test.polynomial.entities.Expression;
import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;
import test.polynomial.exceptions.ParsingException;
import test.polynomial.pojo.ExpressionWrapper;

import static org.junit.jupiter.api.Assertions.*;

class ParseHelperTest {

    @Test
    void testParseSingleTerm() throws ParsingException {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("3*x^2"));
        Polynomial result = ParseHelper.parseExpression(wrapper);

        assertNotNull(result);
        assertEquals(1, result.getTerms().size());
        Term term = result.getTerms().get(0);
        assertEquals(3, term.getCoefficient());
        assertEquals(2, term.getExponent());
    }

    @Test
    void testParseMultipleTerms() throws ParsingException {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("2*x^2+5*x−3"));
        Polynomial result = ParseHelper.parseExpression(wrapper);

        assertNotNull(result);
        assertEquals(3, result.getTerms().size());
        assertEquals("2*x^2 + 5*x - 3", result.getSimplified());
    }

    @Test
    void testParseNestedParentheses() throws ParsingException {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("(x+2)*(x−1)"));
        Polynomial result = ParseHelper.parseExpression(wrapper);

        assertNotNull(result);
        assertEquals(3, result.getTerms().size());
        assertEquals("x^2 + x - 2", result.getSimplified());
    }

    @Test
    void testParseSingleVariable() throws ParsingException {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("x"));
        Polynomial result = ParseHelper.parseExpression(wrapper);

        assertNotNull(result);
        assertEquals(1, result.getTerms().size());
        Term term = result.getTerms().get(0);
        assertEquals(1, term.getCoefficient());
        assertEquals(1, term.getExponent());
    }

    @Test
    void testParseConstant() throws ParsingException {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("7"));
        Polynomial result = ParseHelper.parseExpression(wrapper);

        assertNotNull(result);
        assertEquals(1, result.getTerms().size());
        Term term = result.getTerms().get(0);
        assertEquals(7, term.getCoefficient());
        assertEquals(0, term.getExponent());
    }

    @Test
    void testInvalidCharacter() {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("3*x^2 + y"));
        wrapper.setIndex(5);
        ParsingException exception = assertThrows(ParsingException.class, () -> ParseHelper.parseFactor(wrapper));

        assertEquals("Unexpected character:  ", exception.getMessage());
    }


    @Test
    void testInvalidCoefficientParsing() {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("vx^2"));
        ParsingException exception = assertThrows(ParsingException.class, () -> ParseHelper.parseExpression(wrapper));
        System.out.println(exception.getMessage());
        assertTrue(exception.getMessage().contains("Unexpected character: v"));
    }

    @Test
    void testInvalidExponentParsing() {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("x^a"));
        ParsingException exception = assertThrows(ParsingException.class, () -> ParseHelper.parseExpression(wrapper));

        assertTrue(exception.getMessage().contains("Can't parse exponent"));
    }

    @Test
    void testParseTermWithMultiplication() throws ParsingException {
        ExpressionWrapper wrapper = new ExpressionWrapper(new Expression("2*x*x^2"));
        Polynomial result = ParseHelper.parseExpression(wrapper);

        assertNotNull(result);
        assertEquals(1, result.getTerms().size());
        Term term = result.getTerms().get(0);
        assertEquals(2, term.getCoefficient());
        assertEquals(3, term.getExponent()); // x * x^2 -> x^3
    }
}
