import org.python.core.PyFloat;
import org.python.util.PythonInterpreter;
import java.io.IOException;

public class RunningScript {

    public void evaluate(APIImpl apiObject, ExpressionData expressionData) throws IOException {
        double calculatedY = 0;
        double x;

        expressionData.setExpressionAfterFunctionEval(expressionData.getExpression());

        for(x= expressionData.getMinX(); x <= expressionData.getMaxX(); x+=expressionData.getIncX()) {

                //Call the math plugins and get the result
                if (apiObject.myFunctions != null) {
                    for (FunctionPlugins myFunc : apiObject.myFunctions) {
                        if(myFunc!=null) {
                            myFunc.evalFunc(x);

                            try {
                                calculatedY = Double.parseDouble(expressionData.getExpressionAfterFunctionEval());
                            }
                            catch (NumberFormatException e){
                                /*IF ANY EXTERNAL FUNCTION RETURNS AN EXPRESSION THAT IS PARTIALLY CAlCULATED
                                EG:- parts of sin(x), cos(x) still available in expression
                                CALL THE NEXT MATH FUNCTION (expecting that it solves the remaining part XD) */
                                continue;
                            }
                        }
                    }
                }


                /*Calculating the normal function of x using jython, if no plugins are loaded this part will run.
                 even  if plugins are loaded this will run but doesnt make a difference as the plugins does the calculations
                 within the plugins */
                PythonInterpreter interpreter = new PythonInterpreter();
                interpreter.set("x", x);
                calculatedY = ((PyFloat) interpreter.eval("float(" + expressionData.getExpressionAfterFunctionEval() + ")")).getValue();


                //notifying the observers of the results.
                if (apiObject.calculationObserversList != null) {
                    for (CalculationObserver calculationObserver : apiObject.calculationObserversList) {
                        calculationObserver.notifyComplete(x, calculatedY);
                    }
                }

                //setting the expression back to the original expression for the next increment
                expressionData.setExpressionAfterFunctionEval(expressionData.getExpression());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

    }

}
