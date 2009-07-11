/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

/**
 *
 * @author user
 */
import java.util.HashMap;

public class FuturesOption extends PlainVanillaOption {
    public void sendInputs( HashMap<String,String> inputs )
    {
        super.sendInputs(inputs);
        b = 0;
    }
}
