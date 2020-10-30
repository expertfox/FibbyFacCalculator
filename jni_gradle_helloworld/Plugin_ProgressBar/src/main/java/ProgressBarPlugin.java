public class ProgressBarPlugin implements IPlugin, CalculationObserver {

    double exMax;
    double exMin;
    double increment;
    String expression;

    int calcounter = 0;
    int current = 0;

    @Override
    public void notifyComplete(double x, double y) {
        calcounter++;
        double totnumoperations = (exMax-exMin)/increment + 1;
        current = (int)(calcounter/totnumoperations*100);
        System.out.print(current+"%"+"[");

        //printing the progress bar
        for(int i=0;i<current;i++){
            System.out.print("=");
        }
        System.out.print("]");
        System.out.print("\r");


    }

    @Override
    public void start(API objectOfAPI) {

        this.expression = objectOfAPI.getExpression();
        this.exMax = objectOfAPI.getMaxVal();
        this.exMin = objectOfAPI.getMinVal();
        this.increment = objectOfAPI.getIncrement();

        //registeriong
        objectOfAPI.registerPlugin(this);

        System.out.println("PROGRESSBAR PLUGIN LOADED");


    }
}
