/**
 * Every plugin should implement this Interface
 */
public interface IPlugin {

    /**
     * This is like the main method of the Plugin. This is common for all the plugins
     * @param objectOfAPI takes in an object that has implemented API interface
     */
    void start(API objectOfAPI);
}
