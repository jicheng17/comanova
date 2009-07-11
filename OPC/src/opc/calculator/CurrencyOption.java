/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import java.util.HashMap;
import opc.mathalgo.Adjustment;
/**
 *
 * @author user
 */
public class CurrencyOption extends PlainVanillaOption {

    public void sendInputs( HashMap<String,String> inputs )
    {
        super.sendInputs(inputs);
        rf = Double.parseDouble(inputs.get(CALCULATOR_INPUT.RF) );
        rftype = inputs.get(CALCULATOR_INPUT.RF_TYPE);
        this.rf = Adjustment.getContinuousRate(rf, rftype);
        b = r - rf;
    }
}