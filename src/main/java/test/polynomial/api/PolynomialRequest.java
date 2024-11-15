package test.polynomial.api;

/**
 * POJO for using in POST Body of request
 */
public class PolynomialRequest {

    /**
     * expression to parse
     */
    private String expression;
    /**
     * argument to solve function
     */
    private String argument;

    public String getExpression() {
        return expression;
    }

    public PolynomialRequest setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public String getArgument() {
        return argument;
    }

    public PolynomialRequest setArgument(String argument) {
        this.argument = argument;
        return this;
    }
}
