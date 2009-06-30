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
public class StockOption extends PlainVanillaOption {

    public void sendInputs( HashMap<String,String> inputs )
    {
        super.sendInputs(inputs);
        b = r;
    }
}
