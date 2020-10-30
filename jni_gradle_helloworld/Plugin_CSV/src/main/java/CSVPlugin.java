import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVPlugin implements IPlugin, CalculationObserver {
    API object;
    CSVPrinter printer;
    Map<String, String> expEvals = new HashMap<>();
    String[] HEADER = {"(X) values","(Y) result"};

    @Override
    public void notifyComplete(double x, double y) {
        expEvals.put(Double.toString(x),Double.toString(y));

        if(x == object.getMaxVal()){
            try {
                createCSVFile();
                Thread.sleep(300);
                System.out.println("\nOPEN 'Output.csv' for the full range of answers");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void start(API objectOfAPI) {
        object = objectOfAPI;
        objectOfAPI.registerPlugin(this);

        System.out.println("CSVPLUGIN PLUGIN LOADED");

        try {
            this.createCSVFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ***** source https://www.baeldung.com/apache-commons-csv *******
    public void createCSVFile() throws IOException {
        FileWriter out = new FileWriter("Output.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADER))) {
            expEvals.forEach((x, y) -> {
                try {
                    printer.printRecord(x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
