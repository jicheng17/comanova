/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;

import java.text.DecimalFormat;
import java.util.HashMap;

import opc.mathalgo.Adjustment;
import opc.mathalgo.NormOneDim;



/**
 *
 * @author user
 */
public class PlainVanillaOption extends AbstractOptionsCalculator {

    //Intermediate
    protected int eta = 0; // eta = 1 for long position; eta = -1 for short position
    protected int phi = 0; // phi = 1 for call option; phi = -1 for put option
    protected double d1 = 0.0;
    protected double d2 = 0.0;

    // b = r: Black and Scholes stock option model (1973)
    // b = r-q: Merton stock option model with dividend yield q (1973)
    // b = 0: Black futures option model (1976)
    // b = r = 0: Asay margined futures option model (1982)
    // b = r - rf: Garman and Kohlhagen currency option model (1983)
    public PlainVanillaOption()
    {
    }
   
    public void setPrice()
    {
        longOrShort();
        callOrPut();

        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        
        if (optionflag.equals(CALL_PUT.CALL)){
            price = S*exp((b-r)*T)*Nd1-X*exp(-r*T)*Nd2;
        }
        else if (optionflag.equals(CALL_PUT.PUT)){
            price = X*exp((-r)*T)*(1-Nd2)-S*exp((b-r)*T)*(1-Nd1);
        }
    }
    
    public void setDelta()
    {
        double Nd1 = NormOneDim.cdf(d1);
        if (optionflag.equals(CALL_PUT.CALL)){
            delta = eta*exp((b-r)*T)*Nd1;
        }
        else if (optionflag.equals(CALL_PUT.PUT)){
            delta = eta*exp((b-r)*T)*(Nd1-1);
        }
    }

    public void setDeltaX()
    {
        double Nd2 = NormOneDim.cdf(d2);
        if (optionflag.equals(CALL_PUT.CALL)){
            deltaX = -eta*exp(-r*T)*Nd2;
        }
        else if (optionflag.equals(CALL_PUT.PUT)){
            deltaX = eta*exp(-r*T)*(1-Nd2);
        }
    }
    public void setDDeltaDvol()
    {
        double phid1 = NormOneDim.pdf(d1);
        dDeltaDvol = -eta*(exp((b-r)*T)*d2/sigma)*phid1/100;
    }

    public void setGamma()
    {
        double phid1 = NormOneDim.pdf(d1);
        gamma = eta*(phid1*exp((b-r)*T))/(S*sigma*sqrt(T));
    }

    public void setGammaP()
    {
        gammaP = S*gamma/100;
    }

    public void setDGammaDvol()
    {
        dGammaDvol = gamma*(d1*d2-1)/sigma/100;
    }

    public void setSpeed()
    {
        speed = -(gamma/S)*(d1/(sigma*sqrt(T))+1);
    }

    public void setVega()
    {
        double phid1 = NormOneDim.pdf(d1);
        vega = eta*S*exp((b-r)*T)*phid1*sqrt(T)/100;
    }

    public void setVegaP()
    {
        vegaP = (sigma*100)*vega/10;
    }

    public void setDVegaDvol()
    {
        dVegaDvol = vega*(d1*d2)/sigma/100;
    }

    public void setTheta()
    {
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);
        double phid1 = NormOneDim.pdf(d1);
        if (optionflag.equals(CALL_PUT.CALL)){
            theta = eta*((r-b)*S*exp((b-r)*T)*Nd1
                             - (S*exp((b-r)*T)*phid1*sigma)/(2*sqrt(T))
                             - r*X*exp(-r*T)*Nd2);
        }
        else if (optionflag.equals(CALL_PUT.PUT)){
            theta = eta*((b-r)*S*exp((b-r)*T)*(1-Nd1)
                            +  r*X*exp(-r*T)*(1-Nd2)
                            - (S*exp((b-r)*T)*phid1*sigma)/(2*sqrt(T)));
        }
        theta = theta / 365;
    }

    public void setRho()
    {
        double Nd2 = NormOneDim.cdf(d2);
        if (optionflag.equals(CALL_PUT.CALL))
        {
            if (b!=0.0)
            {
                rho = eta*X*T*exp(-r*T)*Nd2;
            }
            else
            {
                rho = -eta*T*price;
            }
        }
        else if (optionflag.equals(CALL_PUT.PUT))
        {
            if (b!=0.0)
            {
                rho = eta*X*T*exp(-r*T)*(Nd2-1);
            }
            else
            {
                rho = -eta*T*price;
            }
        }
        rho = rho / 100;
    }

    public void setElasticity()
    {
        elasticity = delta*S/price;
    }

    public void setCarry()
    {
        double phid1 = NormOneDim.pdf(d1);
        if (optionflag.equals(CALL_PUT.CALL)){
            carry = eta*S*T*exp((b-r)*T)*phid1;
        }
        else if (optionflag.equals(CALL_PUT.PUT)){
            carry = eta*S*T*exp((b-r)*T)*(1-phid1);
        }
        carry = carry / 100;
    }

    public void setRiskNeutralDensity()
    {
        double phid2 = NormOneDim.pdf(d2);
        riskNeutralDensity = eta * phid2 * exp(-r*T) / (X * sigma * sqrt(T));
    }

    private void dvalues()
    {
        d1 = (log(S/X)+(b+0.5*sigma*sigma)*T)/(sigma*sqrt(T));
        d2 = (log(S/X)+(b-0.5*sigma*sigma)*T)/(sigma*sqrt(T));
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
        if (positionflag.equals(LONG_SHORT.LONG))
            eta = 1;
        else if (positionflag.equals(LONG_SHORT.SHORT))
            eta = -1;
    }

    public void callOrPut()
    {
        if (optionflag.equals(CALL_PUT.CALL))
            phi = 1;
        else if (optionflag.equals(CALL_PUT.PUT))
            phi = -1;
    }

    // calculate options values and sensitivities
    public void calculate()
    {
        dvalues();

        setPrice();
        setDelta();
        setDeltaX();
        setDDeltaDvol();
        setGamma();
        setGammaP();
        setDGammaDvol();
        setVega();
        setVegaP();
        setDVegaDvol();
        setTheta();
        setRho();
        setElasticity();
        setCarry();
        setSpeed();
        setRiskNeutralDensity();
    }

    // send inputs from GUI to corresponding back-end calculators
    public void sendInputs( HashMap<String,String> inputs )
    {
        this.S = Double.parseDouble(inputs.get(CALCULATOR_INPUT.S) );
        this.X = Double.parseDouble(inputs.get(CALCULATOR_INPUT.X) );
        this.T = Double.parseDouble(inputs.get(CALCULATOR_INPUT.T) );
        this.r = Double.parseDouble(inputs.get(CALCULATOR_INPUT.R) );
        this.sigma = Double.parseDouble(inputs.get(CALCULATOR_INPUT.SIGMA) );
        this.timeconvention = inputs.get(CALCULATOR_INPUT.TIME_CONVENTION);
        this.rtype = inputs.get(CALCULATOR_INPUT.R_TYPE);
        this.positionflag = inputs.get(CALCULATOR_INPUT.POSITION_FLAG);
        this.optionflag = inputs.get(CALCULATOR_INPUT.OPTION_FLAG);
        this.T = Adjustment.getAnnualTime(T, timeconvention);
        this.r = Adjustment.getContinuousRate(r, rtype);
    }
    
    // get outputs from corresponding back-end calculators to GUI
    public HashMap<String,String> getOutputs()
    {
        DecimalFormat df = new DecimalFormat("#0.000000");

        outputMap.put( GUI_OUTPUT.OPTION_VALUE, df.format(price) + "" );
        outputMap.put( GUI_OUTPUT.DELTA, df.format(delta) + "" );
        outputMap.put( GUI_OUTPUT.DELTAX, df.format(deltaX) + "" );
        outputMap.put( GUI_OUTPUT.D_DELTA_DVOL, df.format(dDeltaDvol) + "" );
        outputMap.put( GUI_OUTPUT.D_GAMMA_DVOL, df.format(dGammaDvol) + "" );
        outputMap.put( GUI_OUTPUT.D_VEGA_DVOL, df.format(dVegaDvol) + "" );
        outputMap.put( GUI_OUTPUT.ELASTICITY, df.format(elasticity) + "" );
        outputMap.put( GUI_OUTPUT.GAMMA, df.format(gamma) + "" );
        outputMap.put( GUI_OUTPUT.GAMMA_P, df.format(gammaP) + "" );
        outputMap.put( GUI_OUTPUT.RHO, df.format(rho) + "" );
        outputMap.put( GUI_OUTPUT.SPEED, df.format(speed) + "" );
        outputMap.put( GUI_OUTPUT.THETA, df.format(theta) + "" );
        outputMap.put( GUI_OUTPUT.VEGA, df.format(vega) + "" );
        outputMap.put( GUI_OUTPUT.VEGA_P, df.format(vegaP) + "" );
        outputMap.put( GUI_OUTPUT.RISKNEUTRALDENSITY, df.format(riskNeutralDensity) + "" );

        return outputMap;
    }
}
