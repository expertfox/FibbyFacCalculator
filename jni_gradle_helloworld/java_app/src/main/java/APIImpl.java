import java.util.ArrayList;

public class APIImpl implements API {
    //this is for future desined plugins
    ArrayList<FunctionPlugins> myFunctions = new ArrayList<>();

    ExpressionData expression;
    ArrayList<CalculationObserver> calculationObserversList = new ArrayList<>();

    public APIImpl(ExpressionData expression) {
        this.expression = expression;
    }

    /**
     * registers a list of obeservers
     * @param callback an object which implements CalculationObserver interface should be passed.
     */
    @Override
    public void registerPlugin(CalculationObserver callback) {
        calculationObserversList.add(callback);
    }


    @Override
    public void registerFunctionPlugin(FunctionPlugins callback) {
        myFunctions.add(callback);
    }

    @Override
    public String getExpression() {
        return expression.getExpression();
    }

    @Override
    public String getExpressionAfterFunctionEval() {
        return expression.getExpressionAfterFunctionEval();
    }

    @Override
    public void setExpressionAfterFunctionEval(String ex) {
        expression.setExpressionAfterFunctionEval(ex);
    }

    @Override
    public double getMinVal() {
        return expression.getMinX();
    }

    @Override
    public double getMaxVal() {
        return expression.getMaxX();
    }

    @Override
    public double getIncrement() {
        return expression.getIncX();
    }
}
