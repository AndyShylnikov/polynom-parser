package test.polynomial.interfaces;

import test.polynomial.exceptions.ParsingException;

/**
 * Interface used by {@link test.polynomial.controllers.PolynomialController}
 */
public interface IPolynomialService {
    /**
     * Parses given expression
     * @param expression - string expression to parse
     * @return parsed result
     */
    public String parse(String expression) throws ParsingException;

    /**
     * Parses given expression and solve it as function
     * @param expression - string expression to parse
     * @param argumentValue - given argument
     * @return result of function
     */
    public int solve(String expression, String argumentValue) throws ParsingException;
}
