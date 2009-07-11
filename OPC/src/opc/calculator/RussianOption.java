/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import static java.lang.Math.*;


import java.text.DecimalFormat;
import java.util.HashMap;

import opc.mathalgo.Adjustment;
/**
 *
 * @author user
 */
public class RussianOption extends AbstractOptionsCalculator{

     //Intermediate
    protected double a1 = 0.0;
    protected double a2 = 0.0;
    //private final double MULTIPLIER = pow(10,-5);

    // b = r: Black and Scholes stock option model (1973)
    // b = r-q: Merton stock option model with dividend yield q (1973)
    // b = 0: Black futures option model (1976)
    // b = r = 0: Asay margined futures option model (1982)
    // b = r - rf: Garman and Kohlhagen currency option model (1983)
    public RussianOption()
    {
    }



    public void setPrice()
    {
        longOrShort();
        callOrPut();
        aValues();
        
        if (optionflag.equals(CALL_PUT.CALL)){
            if (b >= r)
            {
                
            }
            else
            {
            price = X/(a1-1)*pow((a1-1)/a1*(S/X),a1);
            }
        }
        else if (optionflag.equals(CALL_PUT.PUT)){
            price = X/(1-a2)*pow((a2-1)/a2*(S/X),a2);
        }
    }

   
    private void aValues()
    {
        a1 = 0.5 - b/(sigma*sigma) + sqrt(pow(b/(sigma*sigma)-0.5,2)+2*r/(sigma*sigma));
        a2 = 0.5 - b/(sigma*sigma) - sqrt(pow(b/(sigma*sigma)-0.5,2)+2*r/(sigma*sigma));
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
        setDelta();
        setDeltaX();
        setDDeltaDvol();
        setGamma();
        setGammaP();
        setDGammaDvol();
        setVega();
        setVegaP();
        setDVegaDvol();
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
        this.r = Double.parseDouble(inputs.get(CALCULATOR_INPUT.R) );
        this.sigma = Double.parseDouble(inputs.get(CALCULATOR_INPUT.SIGMA) );
        this.rtype = inputs.get(CALCULATOR_INPUT.R_TYPE);
        this.positionflag = inputs.get(CALCULATOR_INPUT.POSITION_FLAG);
        this.optionflag = inputs.get(CALCULATOR_INPUT.OPTION_FLAG);
        this.r = Adjustment.getContinuousRate(r, rtype);
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
