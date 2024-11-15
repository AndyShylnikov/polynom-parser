package test.polynomial.helpers;

import test.polynomial.pojo.ExpressionWrapper;
import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;

/**
 * Helper class for expession parsing
 */
public class ParseHelper {

    /**
     * Parses expression and returns parsed and simplified polynomial
     *
     * @param wrapper - given wrapper with string expression inside
     * @return parsed and simplified polynomial
     */
    public static Polynomial parseExpression(ExpressionWrapper wrapper) {
        Polynomial result = parseTerm(wrapper);

        while (wrapper.getIndex() < getExpressionLength(wrapper)) {
            char operator = getCurrentSymbol(wrapper);
            if (operator == '+' || operator == '−') {
                incrementIndex(wrapper);
                Polynomial nextTerm = parseTerm(wrapper);
                if (operator == '+') {
                    result = PolynomialHelper.add(result, nextTerm);
                } else {
                    result = PolynomialHelper.subtract(result, nextTerm);
                }
            } else {
                break;
            }
        }
        result = PolynomialHelper.simplify(result);
        result.setSimplified(StringHelper.polymonialToString(result));
        return result;
    }

    private static char getCurrentSymbol(ExpressionWrapper wrapper) {
        return wrapper.getExpression().getOriginalExpression().charAt(wrapper.getIndex());
    }


    /**
     * Parses terms
     *
     * @param wrapper given with string expression inside
     * @return polynomial
     */
    public static Polynomial parseTerm(ExpressionWrapper wrapper) {
        Polynomial result = parseFactor(wrapper);

        while (wrapper.getIndex() < getExpressionLength(wrapper)) {
            char operator = getCurrentSymbol(wrapper);
            if (operator == '*') {
                incrementIndex(wrapper);
                Polynomial nextFactor = parseFactor(wrapper);
                result = PolynomialHelper.multiply(result, nextFactor);
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Parses factor with handling nested parentheses and variables/constants
     *
     * @param wrapper given with string expression inside
     * @return polynomial
     */
    public static Polynomial parseFactor(ExpressionWrapper wrapper) {
        char currentChar = getCurrentSymbol(wrapper);

        if (currentChar == '(') { // Parse nested expression
            incrementIndex(wrapper);
            Polynomial result = parseExpression(wrapper);
            if (wrapper.getIndex() < getExpressionLength(wrapper) && getCurrentSymbol(wrapper) == ')') {
                incrementIndex(wrapper); // Skip closing parenthesis
            }
            return result;
        } else if (Character.isDigit(currentChar) || currentChar == 'x') { // Parse constant or variable
            return parsePolynomial(wrapper);
        }

        throw new IllegalArgumentException("Unexpected character: " + currentChar);
    }

    private static int getExpressionLength(ExpressionWrapper wrapper) {
        return wrapper.getExpression().getOriginalExpression().length();
    }

    /**
     * Parse a polynomial terms like "x", "2*x", "3*x^2", or a constant "5"
     *
     * @param wrapper given with string expression inside
     * @return polynomial
     */
    public static Polynomial parsePolynomial(ExpressionWrapper wrapper) {
        int coefficient = 1;
        int exponent = 0;

        if (Character.isDigit(getCurrentSymbol(wrapper))) {
            int start = wrapper.getIndex();
            while (wrapper.getIndex() < getExpressionLength(wrapper) && Character.isDigit(getCurrentSymbol(wrapper))) {
                incrementIndex(wrapper);
            }
            coefficient = Integer.parseInt(wrapper.getExpression().getOriginalExpression().substring(start, wrapper.getIndex()));
        }

        if (wrapper.getIndex() < getExpressionLength(wrapper) && getCurrentSymbol(wrapper) == 'x') {
            exponent = 1;
            incrementIndex(wrapper);
            if (wrapper.getIndex() < getExpressionLength(wrapper) && getCurrentSymbol(wrapper) == '^') {
                incrementIndex(wrapper); //Skip '^' symbol
                int start = wrapper.getIndex();
                while (wrapper.getIndex() < getExpressionLength(wrapper) && Character.isDigit(getCurrentSymbol(wrapper))) {
                    incrementIndex(wrapper);
                }
                exponent = Integer.parseInt(wrapper.getExpression().getOriginalExpression().substring(start, wrapper.getIndex()));
            }
        }

        Polynomial polynomial = new Polynomial();
        PolynomialHelper.addTerm(polynomial, new Term(coefficient, exponent));
        return polynomial;
    }

    /**
     * Increments index inside given expression wrapper
     *
     * @param wrapper - given expression wrapper
     */
    private static void incrementIndex(ExpressionWrapper wrapper) {
        int index = wrapper.getIndex();
        index++;
        wrapper.setIndex(index);

    }
}