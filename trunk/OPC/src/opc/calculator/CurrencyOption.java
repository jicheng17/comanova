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
public class CurrencyOption extends PlainVanillaOption {

    private double rf;
    private String rftype;

    public void sendInputs( HashMap<String,String> inputs )
    {
        super.sendInputs(inputs);
        rf = Double.parseDouble(inputs.get(CALCULATOR_INPUT.RF) );
        rftype = inputs.get(CALCULATOR_INPUT.RF_TYPE);
        b = r - rf;
    }
}