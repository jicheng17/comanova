package opc.calculator;

import java.util.HashMap;

public abstract class AbstractOptionsCalculator implements OptionsCalculatorInterface {

    // Input
    protected double S;
    protected double X;
    protected double sigma;
    protected double T;
    protected double r;
    protected double b;
    protected String rtype;
    protected String timeconvention;
    protected String positionflag;
    protected String optionflag;

    // Output
    protected double price = 0.0;
    protected double delta = 0.0;
    protected double gamma = 0.0;
    protected double gammaP = 0.0;
    protected double vega = 0.0;
    protected double vegaP = 0.0;
    protected double theta = 0.0;
    protected double speed = 0.0;
    protected double rho = 0.0;
    protected double elasticity = 0.0;
    protected double carry = 0.0;
    protected double dDeltaDvol = 0.0;
    protected double dGammaDvol = 0.0;
    protected double dVegaDvol = 0.0;
    protected double deltaX = 0.0;

    protected HashMap<String,String> outputMap = new HashMap<String,String>();

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
