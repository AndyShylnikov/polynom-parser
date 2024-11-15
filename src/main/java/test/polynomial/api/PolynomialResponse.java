package test.polynomial.api;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * POJO for response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PolynomialResponse {
    /**
     * Result on solving function
     */
    private Integer result;
    /**
     * Simplified expression
     */
    private String expression;
    /**
     * Error message
     */
    private String message;


    public Integer getResult() {
        return result;
    }

    public PolynomialResponse setResult(Integer result) {
        this.result = result;
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public PolynomialResponse setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PolynomialResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
