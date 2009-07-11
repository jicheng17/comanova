/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import static java.lang.Math.*;

import java.text.DecimalFormat;
import java.util.HashMap;

import opc.mathalgo.Adjustment;
import opc.mathalgo.NormOneDim;
import opc.mathalgo.NormTwoDim;

/**
 *
 * @author user
 */
public class AmericanOption extends AbstractOptionsCalculator{

    //Intermediate
    protected double d1 = 0.0;
    protected double d2 = 0.0;
    private double e_price = 0.0;
    //private final double MULTIPLIER = pow(10,-5);

    // b = r: Black and Scholes stock option model (1973)
    // b = r-q: Merton stock option model with dividend yield q (1973)
    // b = 0: Black futures option model (1976)
    // b = r = 0: Asay margined futures option model (1982)
    // b = r - rf: Garman and Kohlhagen currency option model (1983)
    public AmericanOption()
    {
    }


    public void setPrice()
    {
        longOrShort();
        
        if (optionflag.equals(CALL_PUT.PUT))
        {
            double orignalS = S;
            double orignalX = X;
            double orignalr = r;
            double orignalb = b;
            String orignalOptionflag = optionflag;

            S = orignalX;
            X = orignalS;
            r = orignalr - orignalb;
            b = - orignalb;
            optionflag = CALL_PUT.CALL;
            
            setPrice();

            S = orignalS;
            X = orignalX;
            r = orignalr;
            b = orignalb;
            optionflag = orignalOptionflag;
        }
        else if (optionflag.equals(CALL_PUT.CALL))
        {
            if (b >= r)
            {
                dvalues();
                double Nd1 = NormOneDim.cdf(d1);
                double Nd2 = NormOneDim.cdf(d2);
                price = S * exp((b - r) * T) * Nd1 - X * exp(-r * T) * Nd2;

                /*
                System.out.println(S);
                System.out.println(X);
                System.out.println(T);
                System.out.println(r);
                System.out.println(b);
                System.out.println(d1);
                System.out.println(d2);
                System.out.println(price);
                */

            }
            else
            {
                double beta =  0.5 - b / (sigma * sigma)+ sqrt(pow((b / (sigma * sigma) - 0.5), 2)
                               + 2 * r / (sigma * sigma));

                double t1 = 0.5 * (sqrt(5) - 1) * T;

                double Binf = (beta * X)/(beta - 1);
                double B0 = max(X,(r * X) / (r - b));

                double h1 = - (b * t1 + 2 * sigma * sqrt(t1)) * ((X * X) / ((Binf - B0) * B0));
                double h2 = - (b * T + 2 * sigma * sqrt(T)) * ((X * X)/((Binf - B0) * B0));

                double I1 = B0 + (Binf - B0) * (1-exp(h1));
                double I2 = B0 + (Binf - B0) * (1-exp(h2));                                                                                                                                                                                                                              I2 = B0 + (Binf - B0)*(1-exp(h2));

                double alpha1 = (I1 - X) * pow(I1, -beta);
                double alpha2 = (I2 - X) * pow(I2, -beta);

                if (S >= I2)
                {
                    price = S - X;
                }
                else
                {
                   price = alpha2 * pow(S, beta) - alpha2 * phi(S,t1,beta,I2,I2,r,b,sigma)
                         + phi(S,t1,1,I2,I2,r,b,sigma) - phi(S,t1,1,I1,I2,r,b,sigma)
                         - X * phi(S,t1,0,I2,I2,r,b,sigma) + X * phi(S,t1,0,I1,I2,r,b,sigma)
                         + alpha1 * phi(S,t1,beta,I1,I2,r,b,sigma) - alpha1 * psi(S,T,beta,I1,I2,I1,t1,r,b,sigma)
                         + psi(S,T,1,I1,I2,I1,t1,r,b,sigma) - psi(S,T,1,X,I2,I1,t1,r,b,sigma)
                         - X * psi(S,T,0,I1,I2,I1,t1,r,b,sigma) + X * psi(S,T,0,X,I2,I1,t1,r,b,sigma);
                }
            }
        }
    }

    private double phi(double S, double T, double gamma, double H, double I, double r, double b, double sigma)
    {
        double lambda = -r + gamma * b + 0.5 * gamma * (gamma-1) * sigma * sigma;
        double kappa = 2 * b / (sigma * sigma) + 2 * gamma - 1;

        double d = -(log(S / H) + (b + (gamma - 0.5) * sigma * sigma) * T) / (sigma * sqrt(T));


        return exp(lambda * T) * pow(S, gamma) * (NormOneDim.cdf(d)
               - pow(I / S, kappa) * NormOneDim.cdf(d - (2 * log(I / S))/(sigma * sqrt(T))));

    }

    private double psi(double S, double T, double gamma, double H, double I2, double I1,
                       double t1, double r, double b, double sigma)
    {
        double lambda = -r + gamma * b + 0.5 * gamma * (gamma-1) * sigma * sigma;
        double kappa = 2 * b / (sigma * sigma) + 2 * gamma - 1;

        double rho1 = sqrt(t1/T);

        double e1 = (log(S / I1) + (b + (gamma - 0.5) * sigma * sigma) * t1) / (sigma * sqrt(t1));
        double e2 = (log((I2 * I2) / (S * I1)) + (b + (gamma - 0.5) * sigma * sigma) * t1)/(sigma * sqrt(t1));
        double e3 = (log(S / I1) - (b + (gamma - 0.5) * sigma * sigma) * t1) / (sigma * sqrt(t1));
        double e4 = (log((I2 * I2) / (S * I1)) - (b + (gamma - 0.5) * sigma * sigma) * t1)/(sigma * sqrt(t1));

        double f1 = (log(S / H) + (b + (gamma - 0.5) * sigma * sigma) * T) / (sigma * sqrt(T));
        double f2 = (log((I2 * I2) / (S * H)) + (b + (gamma - 0.5) * sigma * sigma) * T) / (sigma * sqrt(T));
        double f3 = (log((I1 * I1) / (S * H)) + (b + (gamma - 0.5) * sigma * sigma) * T) / (sigma * sqrt(T));
        double f4 = (log((S * I1 * I1) / (H * I2 * I2)) + (b + (gamma - 0.5) * sigma * sigma) * T) / (sigma * sqrt(T));

        return exp(lambda * T) * pow(S, gamma) * (NormTwoDim.cdf(-e1,-f1,rho1)
               - pow(I2 / S, kappa) * NormTwoDim.cdf(-e2,-f2,rho1)
               - pow(I1 / S, kappa) * NormTwoDim.cdf(-e3,-f3,-rho1)
               + pow(I1 / I2, kappa) * NormTwoDim.cdf(-e4,-f4,-rho1));
    
    }

    private void dvalues()
    {
        d1 = (log(S / X) + (b + 0.5 * sigma * sigma) * T) / (sigma * sqrt(T));
        d2 = (log(S / X) + (b - 0.5 * sigma * sigma) * T) / (sigma * sqrt(T));
    }

    public void setEuropeanOptionPrice()
    {
        longOrShort();
        callOrPut();
        dvalues();
        
        double Nd1 = NormOneDim.cdf(d1);
        double Nd2 = NormOneDim.cdf(d2);

        if (optionflag.equals(CALL_PUT.CALL)){
            e_price = S*exp((b-r)*T)*Nd1-X*exp(-r*T)*Nd2;
        }
        else if (optionflag.equals(CALL_PUT.PUT)){
            e_price = X*exp((-r)*T)*(1-Nd2)-S*exp((b-r)*T)*(1-Nd1);
        }
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


    public void callOrPut()
    {

    }

    // calculate options values and sensitivities
    public void calculate()
    {
        setPrice();
        setEuropeanOptionPrice();
        setDelta();
        setDeltaX();
        setDDeltaDvol();
        setGamma();
        setGammaX();
        setGammaP();
        setDGammaDvol();
        setVega();
        setVegaP();
        setDVegaDvol();
        setTheta();
        setRho();
        setFuturesRho();
        setElasticity();
        setCarry();
        setSpeed();
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
        this.r = Adjustment.getContinuousRate(r, rtype);
        this.T = Adjustment.getAnnualTime(T, timeconvention);
        this.optiontype = inputs.get(CALCULATOR_INPUT.OPTION_TYPE);

        if (optiontype.equals(OPTION_TYPE.STOCK_OPTION))
        {
            b = r;
        }
        else if (optiontype.equals(OPTION_TYPE.STOCK_INDEX_OPTION))
        {
            this.q = Double.parseDouble(inputs.get(CALCULATOR_INPUT.Q) );
            this.qtype = inputs.get(CALCULATOR_INPUT.Q_TYPE);
            this.q = Adjustment.getContinuousRate(q, qtype);
            b = r - q;
        }
        else if (optiontype.equals(OPTION_TYPE.CURRENCY_OPTION))
        {
            this.rf = Double.parseDouble(inputs.get(CALCULATOR_INPUT.RF) );
            this.rftype = inputs.get(CALCULATOR_INPUT.RF_TYPE);
            this.rf = Adjustment.getContinuousRate(rf, rftype);
            b = r - rf;
        }
        else if (optiontype.equals(OPTION_TYPE.FUTURES_OPTION))
        {
            b = 0;
        }


    }

    // get outputs from corresponding back-end calculators to GUI
    public HashMap<String,String> getOutputs()
    {
        DecimalFormat df = new DecimalFormat("#0.000000");

        outputMap.put( GUI_OUTPUT.OPTION_VALUE, df.format(price) + "" );
        outputMap.put( GUI_OUTPUT.EUROPEAN_OPTION_VALUE, df.format(e_price) + "" );
        outputMap.put( GUI_OUTPUT.DELTA, df.format(delta) + "" );
        outputMap.put( GUI_OUTPUT.DELTAX, df.format(deltaX) + "" );
        outputMap.put( GUI_OUTPUT.D_DELTA_DVOL, df.format(dDeltaDvol) + "" );
        outputMap.put( GUI_OUTPUT.D_GAMMA_DVOL, df.format(dGammaDvol) + "" );
        outputMap.put( GUI_OUTPUT.D_VEGA_DVOL, df.format(dVegaDvol) + "" );
        outputMap.put( GUI_OUTPUT.ELASTICITY, df.format(elasticity) + "" );
        outputMap.put( GUI_OUTPUT.GAMMA, df.format(gamma) + "" );
        outputMap.put( GUI_OUTPUT.GAMMA_P, df.format(gammaP) + "" );
        outputMap.put( GUI_OUTPUT.GAMMA_X, df.format(gammaX) + "" );
        outputMap.put( GUI_OUTPUT.RHO, df.format(rho) + "" );
        outputMap.put( GUI_OUTPUT.FUTURESRHO, df.format(futuresRho) + "" );
        outputMap.put( GUI_OUTPUT.CARRY, df.format(carry) + "" );
        outputMap.put( GUI_OUTPUT.SPEED, df.format(speed) + "" );
        outputMap.put( GUI_OUTPUT.THETA, df.format(theta) + "" );
        outputMap.put( GUI_OUTPUT.VEGA, df.format(vega) + "" );
        outputMap.put( GUI_OUTPUT.VEGA_P, df.format(vegaP) + "" );

        return outputMap;
    }

}
