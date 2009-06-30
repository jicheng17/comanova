/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import java.util.HashMap;

/**
 *
 * @author user
 */
public interface OptionsCalculatorInterface {

    

    public interface LONG_SHORT
    {
        final String LONG = "Long";
        final String SHORT = "Short";
    }

    public interface CALL_PUT
    {
        final String CALL = "Call";
        final String PUT = "Put";
    }

    public interface TIME_UINT
    {
        final String YEAR = "Years";
        final String DAY = "Days";
    }
    
    public interface CALCULATOR_INPUT
    {
        // TODO: any additional calculator input will be added here
        final String S = "S";
        final String X = "X";
        final String T = "T";
        final String SIGMA = "sigma";
        final String R = "r";
        final String Q = "q";
        final String RF = "rf";
        final String R_TYPE = "rtype";
        final String Q_TYPE = "qtype";
        final String RF_TYPE = "rftype";
        final String TIME_CONVENTION = "timeconvention";
        final String POSITION_FLAG = "positionflag";
        final String OPTION_FLAG = "optionflag";
    }
    
    public interface GUI_INPUT
    {
        // TODO: any additional GUI input towards calculator input mapping will be added here
        final String STOCK_PRICE = CALCULATOR_INPUT.S;
        final String STRIKE_PRICE = CALCULATOR_INPUT.X;
        final String TIME_TO_MATURITY = CALCULATOR_INPUT.T;
        final String RISK_FREE_RATE = CALCULATOR_INPUT.R;
        final String VOLATILITY = CALCULATOR_INPUT.SIGMA;
        final String TIME_UNIT = CALCULATOR_INPUT.TIME_CONVENTION;
        final String COMPOUNDING_FREQUENCY = CALCULATOR_INPUT.R_TYPE;
        final String LONG_SHORT = CALCULATOR_INPUT.POSITION_FLAG;
        final String CALL_PUT = CALCULATOR_INPUT.OPTION_FLAG;
    }
    
    // calculate options values and sensitivities
    public void calculate(); 
    
    // send inputs from GUI to corresponding back-end calculators
    public void sendInputs( HashMap<String,String> inputs ); 
    
    // get outputs from corresponding back-end calculators to GUI
    public HashMap<String,String> getOutputs();
    
    // get the output array for graph plotting
    public double[][] getPlotArray();
    
}
