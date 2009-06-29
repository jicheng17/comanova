/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

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
    
    public double[][] getPlotArray();

    // TODO: complete all common methods
    
}
