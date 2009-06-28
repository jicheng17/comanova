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
public class CurrencyOption extends PlainVanillaOption {

    private double rf;
    private String rftype;

    public CurrencyOption() {
        super();
        rf = 0.08;
        rftype = "Continuously";
        b = r - rf;
    }

    public CurrencyOption(double S, double X, double T, double r, double rf, double sigma,
            String timeconvention, String rtype, String rftype, String positionflag, String optionflag)
    {
        super(S, X, T, r, sigma, timeconvention, rtype, positionflag, optionflag);
        this.rf = Adjustment.getContinuousRate(rf, rftype);
        this.rftype = rftype;
        this.b = r - rf;
    }
}