import org.python.core.PyFloat;
import org.python.util.PythonInterpreter;

public class FibonacciFactorial implements IPlugin, FunctionPlugins {
    API object;


    @Override
    public void evalFunc(double x) {

        PythonInterpreter interpreter = new PythonInterpreter();

        //setting the value of x in python to the value of x from java method
        interpreter.set("x",x);

        //binding the static functions to the python environment so that whenever python finds a fib() or fac() part, it iwll evaluate
        interpreter.exec("from FiboAndFac import(fib)");
        interpreter.exec("from FiboAndFac import(fac)");

        //evauating Fibonacci and factorial
        double answer = ((PyFloat) interpreter.eval("float(" + object.getExpressionAfterFunctionEval() + ")")).getValue();

        object.setExpressionAfterFunctionEval(Double.toString(answer));

    }


    @Override
    public void start(API objectOfAPI) {
       object = objectOfAPI;
       objectOfAPI.setExpressionAfterFunctionEval(objectOfAPI.getExpression());

       //registering plugin object
       objectOfAPI.registerFunctionPlugin(this);

        System.out.println("FIBANDFAC PLUGIN LOADED");

    }

}
