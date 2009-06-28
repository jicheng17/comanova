/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import opc.mathalgo.*;
/**
 *
 * @author user
 */
public class FuturesOption extends PlainVanillaOption {

    public FuturesOption() {
        super();
        b = 0;
    }

    public FuturesOption(double S, double X, double T, double r, double sigma,
        String timeconvention, String rtype, String positionflag, String optionflag)
    {
        super(S, X, T, r, sigma, timeconvention, rtype, positionflag, optionflag);
        this.b = 0;
    }
}
