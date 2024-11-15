package test.polynomial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.polynomial.entities.Expression;
import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;
import test.polynomial.interfaces.IPolynomialService;
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
    public String parse(String expression) {

        Expression expressionInstance = fetchExpression(expression);
        if (expressionInstance == null) { // no expression found, need to parse
            return "";
        } else { // return found
            return expressionInstance.getPolynomial().getSimplified();
        }
    }

    /**
     * Parses given expression and solve it as function
     *
     * @param expression    - string expression to parse
     * @param argumentValue - given argument
     * @return result of function
     */
    @Override
    public int solve(String expression, String argumentValue) {
        int argument = Integer.parseInt(argumentValue);
        Expression expressionInstance = fetchExpression(expression);
        if (expressionInstance == null) { // no expression found, need to parse and calculate
            return 0;
        } else { // calculate result
            return solveFunction(expressionInstance.getPolynomial(), argument);
        }
    }

    /**
     * Removes spaces and replaces hyphens with minus(they look similar)
     * @param originalExpression - expression to fix
     * @return result of fixing
     */
    private String fixExpression(String originalExpression) {
        String result = originalExpression.replaceAll("\\s+", "").replaceAll("−", "-"); // Remove all whitespace
        return result;
    }

    /**
     * Fetches expression instance from DB
     * @param expression - original expression
     * @return found expression or null
     */
    private Expression fetchExpression(String expression) {
        expression = fixExpression(expression);
        Optional<Expression> foundExpression = expressionRepository.findByOriginalExpression(expression);
        return foundExpression.orElse(null);
    }

    /**
     * Solves function using terms from polymonial and argument
     * @param polynomial
     * @param argument
     * @return result of solving
     */
    private int solveFunction(Polynomial polynomial, int argument){
        int result = 0;
        for (Term term : polynomial.getTerms()) {
            result += term.getCoefficient() * (Math.pow(argument, term.getExponent()));
        }
        return result;
    }
}
