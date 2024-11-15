package test.polynomial.services;

import org.springframework.stereotype.Service;
import test.polynomial.interfaces.IPolynomialService;
/**
 * Implementation of {@link IPolynomialService}
 */
@Service
public class PolynomialService implements IPolynomialService {
    /**
     * Parses given expression
     *
     * @param expression - string expression to parse
     * @return parsed result
     */
    @Override
    public String parse(String expression) {
        return "";
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
        return 0;
    }
}
