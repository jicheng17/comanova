package opc.calculator;

import java.util.HashMap;
import static java.lang.Math.*;
import opc.mathalgo.NumericalDifference;

public abstract class AbstractOptionsCalculator implements OptionsCalculatorInterface {

    // Input
    protected double S;
    protected double X;
    protected double sigma;
    protected double T;
    protected double r;
    protected double q;
    protected double rf;
    protected double b;
    protected String rtype;
    protected String qtype;
    protected String rftype;
    protected String timeconvention;
    protected String positionflag;
    protected String optionflag;
    protected String optiontype;

    // Output
    protected double price = 0.0;
    protected double delta = 0.0;
    protected double gamma = 0.0;
    protected double gammaP = 0.0;
    protected double vega = 0.0;
    protected double vegaP = 0.0;
    protected double theta = 0.0;
    protected double speed = 0.0;
    protected double rho = 0.0;
    protected double futuresrho = 0.0;
    protected double elasticity = 0.0;
    protected double carry = 0.0;
    protected double dDeltaDvol = 0.0;
    protected double dGammaDvol = 0.0;
    protected double dVegaDvol = 0.0;
    protected double deltaX = 0.0;
    protected double gammaX = 0.0;

    protected final static double DSCALER = 0.01;
    protected final static double DPERCENTAGE = 0.01;
    protected double eta = 0;

    protected HashMap<String,String> outputMap = new HashMap<String,String>();

    public void longOrShort()
    {
        if (positionflag.equals(LONG_SHORT.LONG))
            eta = 1;
        else if (positionflag.equals(LONG_SHORT.SHORT))
            eta = -1;
    }

    public void setPrice()
    {

    }

    public void setDelta()
    {
        double orignalS = S;
        double orignalPrice = price;

        S = orignalS + DSCALER;
        setPrice();
        double upperPrice = price;

        S = orignalS - DSCALER;
        setPrice();
        double lowerPrice = price;

        delta = eta * NumericalDifference.FirstOrderDifference(upperPrice, lowerPrice, DSCALER);

        S = orignalS;
        price = orignalPrice;
    }

    public void setDeltaX()
    {
        double orignalX = X;
        double orignalPrice = price;

        X = orignalX + DSCALER;
        setPrice();
        double upperPrice = price;

        X = orignalX - DSCALER;
        setPrice();
        double lowerPrice = price;

        deltaX = eta * NumericalDifference.FirstOrderDifference(upperPrice, lowerPrice, DSCALER);

        X = orignalX;
        price = orignalPrice;
    }

    public void setDDeltaDvol()
    {
        double orignalS = S;
        double orignalSigma = sigma;
        double orignalPrice = price;

        S = orignalS + DSCALER;
        sigma = orignalSigma + DPERCENTAGE;
        setPrice();
        double uuPrice = price;
        sigma = orignalSigma - DPERCENTAGE;
        setPrice();
        double ulPrice = price;

        S = orignalS + DSCALER;
        sigma = orignalSigma + DPERCENTAGE;
        setPrice();
        double luPrice = price;
        sigma = orignalSigma - DPERCENTAGE;
        setPrice();
        double llPrice = price;

        dDeltaDvol = eta * NumericalDifference.SecondOrderMixedDifference(uuPrice, ulPrice, luPrice, llPrice,
                                                                         DSCALER, DPERCENTAGE);
        S = orignalS;
        sigma = orignalSigma;
        price = orignalPrice;
    }

    public void setGamma()
    {
        double orignalS = S;
        double orignalPrice = price;

        S = orignalS + DSCALER;
        setPrice();
        double upperPrice = price;

        S = orignalS - DSCALER;
        setPrice();
        double lowerPrice = price;

        gamma = eta * NumericalDifference.SecondOrderDifference(upperPrice, orignalPrice, lowerPrice, DSCALER);

        S = orignalS;
        price = orignalPrice;
    }

    public void setGammaP()
    {
        gammaP = gamma * S / 100;
    }

    public void GammaX()
    {
        double orignalX = X;
        double orignalPrice = price;

        X = orignalX + DSCALER;
        setPrice();
        double upperPrice = price;

        X = orignalX - DSCALER;
        setPrice();
        double lowerPrice = price;

        gammaX = eta * NumericalDifference.SecondOrderDifference(upperPrice, orignalPrice, lowerPrice, DSCALER);

        X = orignalX;
        price = orignalPrice;
    }

    public void setDGammaDvol()
    {

    }

    public void setSpeed()
    {

    }

    public void setVega()
    {
        double orignalSigma = sigma;
        double orignalPrice = price;

        sigma = orignalSigma + DPERCENTAGE;
        setPrice();
        double upperPrice = price;

        System.out.println(upperPrice);
        System.out.println(sigma);
        sigma = orignalSigma - DPERCENTAGE;
        setPrice();
        double lowerPrice = price;

        System.out.println(lowerPrice);
        System.out.println(sigma);
        System.out.println(eta);
        
        vega = 0.01 * eta * NumericalDifference.FirstOrderDifference(upperPrice, lowerPrice, DPERCENTAGE);

        sigma = orignalSigma;
        price = orignalPrice;
    }

    public void setVegaP()
    {
        vegaP = (sigma*100)*vega/10;
    }

    public void setDVegaDvol()
    {

    }


    public void setRho()
    {
        double orignalr = r;
        double orignalb = b;
        double orignalPrice = price;

        r = orignalr + DPERCENTAGE;
        b = orignalb + DPERCENTAGE;
        setPrice();
        double upperPrice = price;

        r = orignalr - DPERCENTAGE;
        b = orignalb - DPERCENTAGE;
        setPrice();
        double lowerPrice = price;

        rho = 0.01 * eta * NumericalDifference.FirstOrderDifference(upperPrice, lowerPrice, DPERCENTAGE);

        r = orignalr;
        b = orignalb;
        price = orignalPrice;
    }

    public void setFuturesRho()
    {
        double orignalr = r;
        double orignalPrice = price;

        r = orignalr + DPERCENTAGE;
        setPrice();
        double upperPrice = price;

        r = orignalr - DPERCENTAGE;
        setPrice();
        double lowerPrice = price;

        futuresrho = 0.01 * eta * NumericalDifference.SecondOrderDifference(upperPrice, orignalPrice, lowerPrice, DPERCENTAGE);

        r = orignalr;
        price = orignalPrice;
    }

    public void setTheta()
    {

    }
    
    public void setElasticity()
    {
        elasticity = delta * S / price;
    }

    public void setCarry()
    {
        double orignalb = b;
        double orignalPrice = price;

        b = orignalb + DPERCENTAGE;
        setPrice();
        double upperPrice = price;

        b = orignalb - DPERCENTAGE;
        setPrice();
        double lowerPrice = price;

        carry = 0.01 * eta * NumericalDifference.SecondOrderDifference(upperPrice, orignalPrice, lowerPrice, DPERCENTAGE);

        b = orignalb;
        price = orignalPrice;
    }

    // calculate options values and sensitivities
    public abstract void calculate(); 
    
    // send inputs from GUI to corresponding back-end calculators
    public abstract void sendInputs( HashMap<String,String> inputs ); 
    
    // get outputs from corresponding back-end calculators to GUI
    public abstract HashMap<String,String> getOutputs();
    
    // get the output array for graph plotting
    public abstract double[][] getPlotArray();

    // TODO any common methods that can be used for all calculators can be implemented below
}
