public class CProgressBarPlugin implements IPlugin, CalculationObserver {
    API obj;
    int calcounter;

    static
    {
        //loading the c library
        System.loadLibrary("readerLibC");
    }

    //STATIC NATIVE METHOD
    public native static void progressBar (CProgressBarPlugin current, int counter);

    @Override
    public void start(API objectOfAPI) {
        System.out.println("C_PROGRESSBAR PLUGIN LOADED");
        objectOfAPI.registerPlugin(this);
        obj = objectOfAPI;
    }

    @Override
    public void notifyComplete(double x, double y) {
        calcounter++;

        //this object of the class CProgressBarPlugin is passed instead of the API object
        progressBar(this,calcounter);

    }

    //These methods are written here because JNI does not seem to support GetObjectClass for interface
    double getMinVal(){
       return obj.getMinVal();
    }

    double getMaxVal(){
        return obj.getMaxVal();
    }

    double getIncrement(){
        return obj.getIncrement();
    }
}
