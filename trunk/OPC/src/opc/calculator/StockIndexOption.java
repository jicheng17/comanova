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
public class StockIndexOption extends PlainVanillaOption {

    private double q;
    private String qtype;

    public void sendInputs( HashMap<String,String> inputs )
    {
        super.sendInputs(inputs);
        q = Double.parseDouble(inputs.get(CALCULATOR_INPUT.Q) );
        qtype = inputs.get(CALCULATOR_INPUT.Q_TYPE);
        b = r - q;
    }
}