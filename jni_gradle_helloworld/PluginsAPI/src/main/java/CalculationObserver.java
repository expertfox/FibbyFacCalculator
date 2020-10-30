/**
 * This interface should be implemented to get post calculation data of one iteration
 * within the range of x
 */
public interface CalculationObserver {

    /**
     * Implement this interface for the plugin to observe the corresponding answer(y value) to the increasing x value
     *
     * This can be used for functions as well. But not recommended as it is called in the latter part of the main program.
     *
     * notfies the registered plugins registered via
     * void registerPlugin(CalculationObserver callback) if the API interface
     *
     * @param x carries the current x value of the iteration range
     * @param y carries the current y value, the answer of the expression for the current x
     */
    void notifyComplete(double x, double y);
}
