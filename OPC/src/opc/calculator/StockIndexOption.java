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
public class StockIndexOption extends PlainVanillaOption {

    private double q;
    private String qtype;

    public StockIndexOption() {
        super();
        q = 0.02;
        qtype = "Continuously";
        b = r - q;
    }

    public StockIndexOption(double S, double X, double T, double r, double q, double sigma,
            String timeconvention, String rtype, String qtype, String positionflag, String optionflag)
    {
        super(S, X, T, r, sigma, timeconvention, rtype, positionflag, optionflag);
        this.q = Adjustment.getContinuousRate(q, qtype);
        this.qtype = qtype;
        this.b = r - q;
    }
}