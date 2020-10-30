public interface FunctionPlugins {
    /**
     * this will be called by the Main program to the *registered* Math plugins.
     * @param x the current x value of the range of the iteration will be sent by the main.
     *          use this value to evaluate the functions that are processed by your plugin
     */
    void evalFunc(double x);
}
