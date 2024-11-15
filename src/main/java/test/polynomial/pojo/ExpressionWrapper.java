package test.polynomial.pojo;

import test.polynomial.entities.Expression;

/**
 * Represents wrapper on polynomial expression passed as String
 */
public class ExpressionWrapper {
    /**
     * Passed expression
     */

    private Expression expression;
    /**
     * Value used in parsing process
     */
    private int index;

    public ExpressionWrapper(Expression expression) {
        this.expression = expression;
        index = 0;
    }

    public Expression getExpression() {
        return expression;
    }

    public ExpressionWrapper setExpression(Expression expression) {
        this.expression = expression;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public ExpressionWrapper setIndex(int index) {
        this.index = index;
        return this;
    }
}
