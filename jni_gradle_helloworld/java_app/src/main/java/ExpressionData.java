
//THIS CLASS STORES ALL THE DATA OF THE EXPRESSION
public class ExpressionData {

    private String expression;
    private String expressionAfterFunctionEval;

    private double minX;
    private double maxX;
    private double incX;

    public String getExpressionAfterFunctionEval() {
        return expressionAfterFunctionEval;
    }

    public void setExpressionAfterFunctionEval(String expressionAfterFibEval) {
        this.expressionAfterFunctionEval = expressionAfterFibEval;
    }

    public String getExpression() {
        return expression;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getIncX() {
        return incX;
    }

    public ExpressionData(String expression, double minX, double maxX, double incX) {
        this.expression = expression;
        this.minX = minX;
        this.maxX = maxX;
        this.incX = incX;
    }


}
