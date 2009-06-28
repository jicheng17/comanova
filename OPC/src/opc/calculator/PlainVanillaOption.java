/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import opc.mathalgo.*;
import static java.lang.Math.*;
import java.text.DecimalFormat;



/**
 *
 * @author user
 */
public class PlainVanillaOption implements OptionsCalculatorInterface{

    // Input
    protected double S;
    protected  double X;
    protected  double sigma;
    protected  double T;
    protected  double r;
    protected  double b;
    protected  String rtype;
    protected  String timeconvention;
    protected  String positionflag;
    protected  String optionflag;

    //Intermediate
    private int eta = 0; // eta = 1 for long position; eta = -1 for short position
    private int phi = 0; // phi = 1 for call option; phi = -1 for put option
    
    // Output
    private double d1 = 0.0;
    private double d2 = 0.0;
    private double callprice = 0.0;
    private double putprice = 0.0;
    private double calldelta = 0.0;
    private double putdelta = 0.0;
    private double callgamma = 0.0;
    private double putgamma = 0.0;
    private double callgammaP = 0.0;
    private double putgammaP = 0.0;
    private double callvega = 0.0;
    private double putvega = 0.0;
    private double calltheta = 0.0;
    private double puttheta = 0.0;
    private double callrho = 0.0;
    private double putrho = 0.0;
    private double callelasticity = 0.0;
    private double putelasticity = 0.0;
    private double callcarry = 0.0;
    private double putcarry = 0.0;


    // b = r: Black and Scholes stock option model (1973)
    // b = r-q: Merton stock option model with dividend yield q (1973)
    // b = 0: Black futures option model (1976)
    //// b = r = 0: Asay margined futures option model (1982)
    // b = r - rf: Garman and Kohlhagen currency option model (1983)


    public PlainVanillaOption() {
    //default
        S = 120;
        X = 100;
        T = 0.25;
        r = 0.1;
        sigma = 0.4;
        timeconvention = "Years";
        rtype = "Continuously";
        positionflag = "Long";
        optionflag = "Call";
    }

    public PlainVanillaOption(double S, double X, double T, double r, double sigma,
            String timeconvention, String rtype, String positionflag, String optionflag)
    {
        this.S = S;
        this.X = X;
        this.T = Adjustment.getAnnualTime(T, timeconvention);
        this.r = Adjustment.getContinuousRate(r, rtype);
        this.sigma = sigma;
        this.timeconvention = timeconvention;
        this.rtype = rtype;
        this.positionflag = positionflag;
        this.optionflag = optionflag;
    }
   
    public void setCall(double call)
    {
        callprice = call;
    }

    public double getCall()
    {
        return callprice;
    }

    public void setPut(double put)
    {
        putprice = put;
    }

    public double getPut()
    {
        return putprice;
    }

    
    public void setDelta()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);
        if (optionflag.equals("call")){
            calldelta = eta*exp((b-r)*T)*Nd1;
        }
        else if (optionflag.equals("put")){
            putdelta = eta*exp((b-r)*T)*(Nd1-1);
        }
    }

    public double getDelta()
    {
        if (optionflag.equals("call")){
            return calldelta;
        }
        else if (optionflag.equals("put")){
            return putdelta;
        }
        return 0;
    }

     public void setGamma()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);
        if (optionflag.equals("call")){
            callgamma = eta*(phid1*exp((b-r)*T))/(S*sigma*sqrt(T));
        }
        else if (optionflag.equals("put")){
            putgamma = eta*(phid1*exp((b-r)*T))/(S*sigma*sqrt(T));
        }
    }

    public double getGamma()
    {
        if (optionflag.equals("call")){
            return callgamma;
        }
        else if (optionflag.equals("put")){
            return putgamma;
        }
        return 0;
    }

    public void setGammaP()
    {
        if (optionflag.equals("call")){
            callgammaP = S*getGamma()/100;
        }
        else if (optionflag.equals("put")){
            putgammaP = S*getGamma()/100;
        }
    }
    
    public double getGammaP()
    {
        if (optionflag.equals("call")){
            return callgammaP;
        }
        else if (optionflag.equals("put")){
            return putgammaP;
        }
        return 0;
    }
     public void setVega()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);
        if (optionflag.equals("call")){
            callvega = eta*S*exp((b-r)*T)*phid1*sqrt(T);
        }
        else if (optionflag.equals("put")){
            putvega = eta*S*exp((b-r)*T)*phid1*sqrt(T);
        }
    }

    public double getVega()
    {
        if (optionflag.equals("call")){
            return callvega;
        }
        else if (optionflag.equals("put")){
            return putvega;
        }
        return 0;
    }

     public void setTheta()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);
        if (optionflag.equals("call")){
            calltheta = eta*((r-b)*S*exp((b-r)*T)*Nd1
                             - (S*exp((b-r)*T)*phid1*sigma)/(2*sqrt(T))
                             - r*X*exp(-r*T)*Nd2);
        }
        else if (optionflag.equals("put")){
            puttheta = eta*((b-r)*S*exp((b-r)*T)*(1-Nd1)
                            +  r*X*exp(-r*T)*(1-Nd2)
                            - (S*exp((b-r)*T)*phid1*sigma)/(2*sqrt(T)));
        }
    }

    public double getTheta()
    {
        if (optionflag.equals("call")){
            return calltheta;
        }
        else if (optionflag.equals("put")){
            return puttheta;
        }
        return 0;
    }
   
    public void setRho()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);
        if (optionflag.equals("call")){
            if (b!=0.0)
            {
                callrho = eta*X*T*exp(-r*T)*Nd2;
            }
            else
            {
                callrho = -eta*T*getCall();
            }
        }
        else if (optionflag.equals("put")){
            if (b!=0.0)
            {
                putrho = eta*X*T*exp(-r*T)*(Nd2-1);
            }
            else
            {
                putrho = -eta*T*getPut();
            }
        }
    }

    public double getRho()
    {
        if (optionflag.equals("call")){
            return callrho;
        }
        else if (optionflag.equals("put")){
            return putrho;
        }
        return 0;
    }

    public void setElasticity()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);
        if (optionflag.equals("call")){
            callelasticity = getDelta()*S/getCall();
        }
        else if (optionflag.equals("put")){
            putelasticity = getDelta()*S/getPut();
        }
    }

    public double getElasticity()
    {
        if (optionflag.equals("call")){
            return callelasticity;
        }
        else if (optionflag.equals("put")){
            return putelasticity;
        }
        return 0;
    }

    public void setCarry()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);
        if (optionflag.equals("call")){
            callcarry = eta*S*T*exp((b-r)*T)*phid1;
        }
        else if (optionflag.equals("put")){
            putcarry = eta*S*T*exp((b-r)*T)*(1-phid1);
        }
    }

    public double getCarry()
    {
        if (optionflag.equals("call")){
            return callcarry;
        }
        else if (optionflag.equals("put")){
            return putcarry;
        }
        return 0;
    }

    private void dvalues()
    {
        d1 = (log(S/X)+(b+0.5*sigma*sigma)*T)/(sigma*sqrt(T));
        d2 = (log(S/X)+(b-0.5*sigma*sigma)*T)/(sigma*sqrt(T));
    }

    public void calculateValue()
    {
        dvalues();
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        double phid2 = NormOneDim.pdf(d2);

        if (optionflag.equals("call")){
            setCall(S*exp((b-r)*T)*Nd1-X*exp(-r*T)*Nd2);
        }
        else if (optionflag.equals("put")){
            setPut(X*exp((-r)*T)*(1-Nd2)-S*exp((b-r)*T)*(1-Nd1));
        }

        setDelta();
        setGamma();
        setVega();
        setTheta();
        setRho();
        setElasticity();
        setCarry();
    }

    public double[][] getPlotArray()
    {
        double minS = 0.5*X;
        double maxS = 1.5*X;
        double minT = 0.01*T;
        double maxT = 2*T;;

        double Ns = 20;
        double Nt = 20;

        return null;
    }

    

    public void longOrShort()
    {
        if (positionflag.equals("long"))
            eta = 1;
        else if (positionflag.equals("short"))
            eta = -1;
    }

    public void callOrPut()
    {
        if (optionflag.equals("call"))
            phi = 1;
        else if (optionflag.equals("put"))
            phi = -1;
    }


    public static void main(String[] args)
    {

        DecimalFormat opcformat = new DecimalFormat("#0.000000");

        /*double stockprice = 100;
        double strikeprice = 100;
        double timetomaturity = 38;
        String timeconvention = "Days";
        double riskfreerate = 0.1;
        String rtype = "Monthly";
        double dividendyield = 0.02;
        String divtype = "Semi-annually";
        double volatility = 0.4;
        String position = "long";
        String option = "put";*/

        double stockprice = 345;
        double strikeprice = 365;
        double timetomaturity = 46;
        String timeconvention = "Days";
        double riskfreerate = 0.07;
        String rtype = "Semi-annually";
        double dividendyield = 0.05;
        String divtype = "Monthly";
        double volatility = 0.2;
        String position = "short";
        String option = "call";

        
        timetomaturity =  Adjustment.getAnnualTime(timetomaturity, timeconvention);
        riskfreerate =  Adjustment.getContinuousRate(riskfreerate, rtype);
        dividendyield =  Adjustment.getContinuousRate(dividendyield, divtype);

        double costofcarry = riskfreerate - dividendyield;



        PlainVanillaOption pvo = new PlainVanillaOption(stockprice, strikeprice,timetomaturity,
                                 riskfreerate, volatility, timeconvention, rtype, position, option);

        pvo.longOrShort();
        pvo.callOrPut();
        pvo.calculateValue();

        double price = 0.0;
        if (option.equals("call"))
           price = pvo.getCall();
        else if (option.equals("put"))
           price = pvo.getPut();

        double delta = pvo.getDelta();
        double gamma = pvo.getGamma();
        double gammaP = pvo.getGammaP();
        double vega = pvo.getVega()/100;
        double theta = pvo.getTheta()/365;
        double rho = pvo.getRho()/100;
        double elasticity = pvo.getElasticity();
        double carry = pvo.getCarry()/100;
        
        String pricestring = opcformat.format(price);
        String deltastring = opcformat.format(delta);
        String gammastring = opcformat.format(gamma);
        String gammaPstring = opcformat.format(gammaP);
        String vegastring = opcformat.format(vega);
        String thetastring = opcformat.format(theta);
        String rhostring = opcformat.format(rho);
        String elasticitystring = opcformat.format(elasticity);
        String carrystring = opcformat.format(carry);
        
        System.out.println("This is a " + position + " position on a " + option + " option:");
        System.out.println("Value:      " + pricestring);
        System.out.println("Delta:      " + deltastring);
        System.out.println("Gamma:      " + gammastring);
        System.out.println("GammaP:      " + gammaPstring);
        System.out.println("Vega:       " + vegastring);
        System.out.println("Theta:      " + thetastring);
        System.out.println("Rho:        " + rhostring);
        System.out.println("Elasticity: " + elasticitystring);
        System.out.println("Carry:      " + carrystring);
        
    }
}
