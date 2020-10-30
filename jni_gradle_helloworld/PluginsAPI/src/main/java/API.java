/**
 * This is the interface of the API of the FibbyFacCalculator
 *
 * @author ExpertFox
 */

public interface API {
    /**
     * This method registers the plugins (multiple plugins can be registered) that
     * need the x values and y values (answer for each x value evaluation of the expression) of each evaluation.
     * x, and resulting y values are sent to the plugin by the notifyComplete(double x, double y) callback method
     * of the CalculationObserver interface
     * @param callback an object which implements CalculationObserver interface should be passed.
     */
    void registerPlugin(CalculationObserver callback);


    /**
     * This is for registering function plugins designed, eg:- for fib(x), fac(x), sin(x), tan(x)
     *
     * All the calculations should be done within the plugin and sent back to the API's Object as a simple expression
     * of x (string) or as a number value (string) by setting the string using setExpressionAfterFunctionEval()
     *
     * @param callback an object which implements the FunctionPlugins interface should be passed
     */
    void registerFunctionPlugin(FunctionPlugins callback);

    /**
     * Get the original expression that was inputted by the user
     * @return returns a string of the expression
     */
    String getExpression();

    /**
     * Update the expression after calculation
     * Eg:- if x+5+fib(x) , x=3 has been sent to fibonacci calculator and it evaluates the expression,
     * then set the expression again.
     *
     * This new expression should be updated as the usable expression for the next calculation.
     *
     * It could be a value in (String) or an expression with math functions remaining eg:- sin(x)
     * but you should have the necessary plugin for sin(x) as for the example
     *
     * * @param ex the calculated expression.
     */
    void setExpressionAfterFunctionEval(String ex);

    /**
     * Get the updated expression which has gone through partial calculation
     * or
     * or the number value (string) after calculation
     * @return returns a string of the changed expression
     */
    String getExpressionAfterFunctionEval();

    /**
     *
     * @return returns the starting value of x
     */
    double getMinVal();

    /**
     *
     * @return returns the ending value of x
     */
    double getMaxVal();

    /**
     *
     * @return the increment of x within the range
     */
    double getIncrement();

}
