/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.calculator;

import java.util.HashMap;

/**
 *
 * @author user
 */
public interface OptionsCalculatorInterface {

    
    public interface LONG_SHORT
    {
        final String LONG = "Long";
        final String SHORT = "Short";
    }

    public interface CALL_PUT
    {
        final String CALL = "Call";
        final String PUT = "Put";
    }

    public interface TIME_UNIT
    {
        final String YEAR = "Years";
        final String DAY = "Days";
    }

    public interface IN_OUT
    {
        final String IN = "in";
        final String OUT = "out";
    }

    public interface SINGLE_BINARY_BARRIER_TYPE
    {
        final int DICashOrNothingNoneAtHit = 1;
        final int UICashOrNothingNoneAtHit = 2;
        final int DIAssetOrNothingNoneAtHit = 3;
        final int UIAssetOrNothingNoneAtHit = 4;
        final int DICashOrNothingNoneAtExpiry = 5;
        final int UICashOrNothingNoneAtExpiry = 6;
        final int DIAssetOrNothingNoneAtExpiry = 7;
        final int UIAssetOrNothingNoneAtExpiry = 8;
        final int DOCashOrNothingNoneAtExpiry = 9;
        final int UOCashOrNothingNoneAtExpiry = 10;
        final int DOAssetOrNothingNoneAtExpiry = 11;
        final int UOAssetOrNothingNoneAtExpiry = 12;
        final int DICashOrNothingCallAtExpiry = 13;
        final int UICashOrNothingCallAtExpiry = 14;
        final int DIAssetOrNothingCallAtExpiry = 15;
        final int UIAssetOrNothingCallAtExpiry = 16;
        final int DICashOrNothingPutAtExpiry = 17;
        final int UICashOrNothingPutAtExpiry = 18;
        final int DIAssetOrNothingPutAtExpiry = 19;
        final int UIAssetOrNothingPutAtExpiry = 20;
        final int DOCashOrNothingCallAtExpiry = 21;
        final int UOCashOrNothingCallAtExpiry = 22;
        final int DOAssetOrNothingCallAtExpiry = 23;
        final int UOAssetOrNothingCallAtExpiry = 24;
        final int DOCashOrNothingPutAtExpiry = 25;
        final int UOCashOrNothingPutAtExpiry =26;
        final int DOAssetOrNothingPutAtExpiry = 27;
        final int UOAssetOrNothingPutAtExpiry = 28;
    }

    public interface OPTION_TYPE
    {
        final String STOCK_OPTION = "Stock Options";
        final String STOCK_INDEX_OPTION = "Stock Indexes Options";
        final String CURRENCY_OPTION = "Currency Options";
        final String FUTURES_OPTION = "Futures Options";
    }
    
    public interface CALCULATOR_INPUT
    {
        // TODO: any additional calculator input will be added here
        final String OPTION_TYPE = "optiontype";
        final String S = "S";
        final String X = "X";
        final String K = "K";
        final String X1 = "X1";
        final String X2 = "X2";
        final String U = "U";
        final String L = "L";
        final String H = "H";
        final String T = "T";
        final String SIGMA = "sigma";
        final String R = "r";
        final String Q = "q";
        final String RF = "rf";
        final String R_TYPE = "rtype";
        final String Q_TYPE = "qtype";
        final String RF_TYPE = "rftype";
        final String TIME_CONVENTION = "timeconvention";
        final String POSITION_FLAG = "positionflag";
        final String OPTION_FLAG = "optionflag";
        final String LOWER_BARRIER_FLAG = "lowerbarrierflag";
        final String UPPER_BARRIER_FLAG = "upperbarrierflag";
        final String SINGLE_BINARY_BARRIER_TYPE = "sbb";
    }
    
    public interface GUI_INPUT
    {
        // TODO: any additional GUI input towards calculator input mapping will be added here
        final String OPTION_TYPE = CALCULATOR_INPUT.OPTION_TYPE;
        final String STOCK_PRICE = CALCULATOR_INPUT.S;
        final String STOCK_INDEX_PRICE = CALCULATOR_INPUT.S;
        final String CURRENCY_SPOT_PRICE = CALCULATOR_INPUT.S;
        final String FUTURES_PRICE = CALCULATOR_INPUT.S;
        final String STRIKE_PRICE = CALCULATOR_INPUT.X;
        final String LOWER_STRIKE_PRICE = CALCULATOR_INPUT.X1;
        final String UPPER_STRIKE_PRICE = CALCULATOR_INPUT.X2;
        final String BARRIER = CALCULATOR_INPUT.H;
        final String SINGLE_BINARY_BARRIER_TYPE = CALCULATOR_INPUT.SINGLE_BINARY_BARRIER_TYPE;
        final String LOWER_BARRIER = CALCULATOR_INPUT.L;
        final String UPPER_BARRIER = CALCULATOR_INPUT.U;
        final String LOWER_BARRIER_FLAG = CALCULATOR_INPUT.LOWER_BARRIER_FLAG;
        final String UPPER_BARRIER_FLAG = CALCULATOR_INPUT.UPPER_BARRIER_FLAG;
        final String CASH_REBATE = CALCULATOR_INPUT.K;
        final String TIME_TO_MATURITY = CALCULATOR_INPUT.T;
        final String RISK_FREE_RATE = CALCULATOR_INPUT.R;
        final String VOLATILITY = CALCULATOR_INPUT.SIGMA;
        final String TIME_UNIT = CALCULATOR_INPUT.TIME_CONVENTION;
        final String RISK_FREE_COMPOUNDING = CALCULATOR_INPUT.R_TYPE;
        final String LONG_SHORT = CALCULATOR_INPUT.POSITION_FLAG;
        final String CALL_PUT = CALCULATOR_INPUT.OPTION_FLAG;
        final String DIVIDEND_YIELD = CALCULATOR_INPUT.Q;
        final String DIVIDEND_COMPOUNDING = CALCULATOR_INPUT.Q_TYPE;
        final String FOREIGN_RISK_FREE_RATE = CALCULATOR_INPUT.RF;
        final String FOREIGN_RISK_COMPOUNDING = CALCULATOR_INPUT.RF_TYPE;
    }

    public interface GUI_OUTPUT
    {
        // TODO: any additional GUI output from calculator will be added here
        final String OPTION_VALUE = "price";
        final String EUROPEAN_OPTION_VALUE = "e_price";
        final String DELTA = "delta";
        final String DELTAX = "deltaX";
        final String GAMMAX = "gammaX";
        final String D_DELTA_DVOL = "dDeltaDvol";
        final String ELASTICITY = "elasticity";
        final String GAMMA = "gamma";
        final String D_GAMMA_DVOL = "dGammaDvol";
        final String GAMMA_P = "gammaP";
        final String GAMMA_X = "gammaX";
        final String VEGA = "vega";
        final String D_VEGA_DVOL = "dVegaDvol";
        final String VEGA_P = "vegaP";
        final String SPEED = "speed";
        final String THETA = "theta";
        final String RHO = "rho";
        final String FUTURESRHO = "futuresRho";
        final String CARRY = "carry";
        final String RISKNEUTRALDENSITY = "riskNeutralDensity";
    }

    // calculate options values and sensitivities
    public void calculate(); 
    
    // send inputs from GUI to corresponding back-end calculators
    public void sendInputs( HashMap<String,String> inputs ); 
    
    // get outputs from corresponding back-end calculators to GUI
    public HashMap<String,String> getOutputs();
    
    // get the output array for graph plotting
    public double[][] getPlotArray();
    
}
