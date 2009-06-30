package opc.calculator;

import java.util.HashMap;

public abstract class AbstractOptionsCalculator implements OptionsCalculatorInterface {

    // calculate options values and sensitivities
    public abstract void calculate(); 
    
    // send inputs from GUI to corresponding back-end calculators
    public abstract void sendInputs( HashMap<String,String> inputs ); 
    
    // get outputs from corresponding back-end calculators to GUI
    public abstract HashMap<String,String> getOutputs();
    
    // get the output array for graph plotting
    public abstract double[][] getPlotArray();

    // TODO any common methods that can be used for all calculators can be implemented below
}
