import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

public class ConsoleIO
{

    public static void main(String[] args) throws IOException {

        ExpressionData expressionData;
        APIImpl pluginControl;
        Scanner getUserInput = new Scanner(System.in);

        //GETTING THE PLUGINS FROM THE FILE.....
        ArrayList<String> availablePlugins = new ArrayList<>();

        try {
            File myObj = new File("src/pluginlist.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                availablePlugins.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //----------INTRO
        System.out.println("-------------------------WELCOME-------------------------");
        System.out.println("----------------To the FibbyFacCalculator----------------");
        System.out.println("----------example: fac(fac(x+1))+fib(2*x+1)--------------");
        //----------INTRO OVER


        //-------------------GETTING USER INPUTS----------------------
        System.out.println("---------------------------------------------------------");

        System.out.print("# ENTER EXPRESSION TO EVALUATE: ");
        String expression = getUserInput.nextLine();

        System.out.print("# ENTER MINVAL: ");
        double minval = getUserInput.nextDouble();

        System.out.print("# ENTER MAXVAL: ");
        double maxval = getUserInput.nextDouble();

        System.out.print("# ENTER INCREMENT: ");
        double incre = getUserInput.nextDouble();

        System.out.println("---------------------------------------------------------");

        //Creating an object of expression and APIImpl and passing the expression to API object.
        expressionData = new ExpressionData(expression, minval, maxval, incre);
        pluginControl = new APIImpl(expressionData);


        //-------------GETTING THE REQUIRED PLUGINS FOR THE USER---------
        System.out.println("To start, select the plugins that you require...");

        //Getting the necessary plugins from the user
        int noofplugins = 0;
        for (String plg : availablePlugins) {
            noofplugins++;
            System.out.println(noofplugins + ": " + plg + "\n");
        }
        System.out.println("Enter the required plugins' numbers seperated by a comma(,) \nEg:- 1,3,4");
        System.out.println("If you require no plugins, input zero '0'\n");

        Scanner getUserInput1 = new Scanner(System.in);

        String allReqPlugins = getUserInput1.nextLine();

        //If 0 is entered, no plugin will be loaded.
        if (!allReqPlugins.equals("0")) {
            String[] reqPlugins = allReqPlugins.split(",");

            for (String rPl : reqPlugins) {
                int requiredPluginNumber = Integer.parseInt(rPl);

                try {

                    //Loading the plugin using the Reflection API
                    Class<?> classOfThePlugin;
                    classOfThePlugin = Class.forName(availablePlugins.get(requiredPluginNumber - 1));
                    Object pluginObj = classOfThePlugin.getConstructor().newInstance();

                    //invoking the start method of the Plugin
                    Method method = classOfThePlugin.getMethod("start", API.class);
                    method.invoke(pluginObj, pluginControl);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

            //--------Running the calculations
            RunningScript jythonScript = new RunningScript();
            jythonScript.evaluate(pluginControl, expressionData);

            //Do not close the app until enter hits
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            in.readLine();

     }

}
