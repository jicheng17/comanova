/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;


import opc.calculator.OptionsCalculatorInterface;
import opc.mathalgo.*;
import static java.lang.Math.*;
/**
 *
 * @author user
 */
public class NoDivStockOption {
    private double S;
    private double X;
    private double sigma;
    private double T;
    private double r;
    private String rtype;
    //private String optiontype;
    private String positionflag;
    private String optionflag;
    private String timeconvention;

    private double b;
    private String btype;

    public NoDivStockOption() {
        S = 120;
        X = 100;
        T = 0.25;
        r = 0.1;
        sigma = 0.4;
        timeconvention = "Years";
        rtype = "Continuous";
        //optiontype = "NoDividendStockOption";
        positionflag = "long";
        optionflag = "call";

        b = r;
        btype = rtype;
    }

    public NoDivStockOption(double S, double X, double T, double r, double sigma, 
    String timeconvention, String rtype, String positionflag, String optionflag)
    {
        this.S = S;
        this.X = X;
        this.T = T;
        this.r = r;
        this.sigma = sigma;
        this.timeconvention = timeconvention;
        this.rtype = rtype;
        //this.optiontype = optiontype;
        this.positionflag = positionflag;
        this.optionflag = optionflag;

        this.b = r;
        this.btype = rtype;
    }


    





    



}
