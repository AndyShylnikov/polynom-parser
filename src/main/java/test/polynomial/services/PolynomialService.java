package test.polynomial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.polynomial.entities.Expression;
import test.polynomial.entities.Polynomial;
import test.polynomial.exceptions.ParsingException;
import test.polynomial.helpers.ParseHelper;
import test.polynomial.helpers.PolynomialHelper;
import test.polynomial.interfaces.IPolynomialService;
import test.polynomial.pojo.ExpressionWrapper;
import test.polynomial.repositories.ExpressionRepository;
import test.polynomial.repositories.PolynomialRepository;
import test.polynomial.repositories.TermRepository;

import java.util.Optional;

/**
 * Implementation of {@link IPolynomialService}
 */
@Service
public class PolynomialService implements IPolynomialService {
    @Autowired
    private ExpressionRepository expressionRepository;
    @Autowired
    private PolynomialRepository polynomialRepository;
    @Autowired
    private TermRepository termRepository;

    /**
     * Parses given expression
     *
     * @param expression - string expression to parse
     * @return parsed result
     */
    @Override
    public String parse(String expression) throws ParsingException {
        Polynomial polynomial = parseToPolynomial(expression);
        return polynomial.getSimplified();
    }

    /**
     * Parses given expression and solve it as function
     *
     * @param expression    - string expression to parse
     * @param argumentValue - given argument
     * @return result of function
     */
    @Override
    public int solve(String expression, String argumentValue) throws ParsingException {
        try {
            int argument = Integer.parseInt(argumentValue);
            Polynomial polynomial = parseToPolynomial(expression);
            return PolynomialHelper.solve(polynomial, argument);
        } catch (NumberFormatException e) {
            throw new ParsingException("Can parse argument. You passed \"" + argumentValue + "\"");
        }

    }

    /**
     * Removes spaces and replaces hyphens with minus(they look similar)
     *
     * @param originalExpression - expression to fix
     * @return result of fixing
     */
    private String fixExpression(String originalExpression) {
        String result = originalExpression.replaceAll("\\s+", "").replaceAll("-", "−"); // Remove all whitespace
        return result;
    }

    /**
     * Fetches expression instance from DB
     *
     * @param expression - original expression
     * @return found expression or null
     */
    private Expression fetchExpression(String expression) {
        Optional<Expression> foundExpression = expressionRepository.findByOriginalExpression(expression);
        return foundExpression.orElse(null);
    }


    /**
     * Parses given expression
     *
     * @param expression - string expression to parse
     * @return parsed result
     */
    private Polynomial parseToPolynomial(String expression) throws ParsingException {
        expression = fixExpression(expression);
        Polynomial polynomial;
        Expression expressionInstance = fetchExpression(expression);
        if (expressionInstance == null) { // no expression found, need to parse
            expressionInstance = new Expression();
            expressionInstance.setOriginalExpression(expression);
            ExpressionWrapper wrapper = new ExpressionWrapper(expressionInstance);
            polynomial = ParseHelper.parseExpression(wrapper);
            expressionInstance.setPolynomial(polynomial);
            polynomialRepository.save(polynomial);
            Expression saved = expressionRepository.save(expressionInstance);
        } else { // return found
            polynomial = expressionInstance.getPolynomial();
        }
        return polynomial;

    }
}
