/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.util.HashMap;

import opc.calculator.OptionsCalculatorInterface;

/**
 *
 * @author ZHAO QINGHUA
 */
public class StockOptionsTabbedPane extends OPCTabbedPane {

    private final String STOCK_PRICE_STRING = "Stock Price: ";
    private final String CALL_PUT_STRING = "Call/Put: ";

    protected JLabel stockPriceLabel;
    protected JLabel callPutLabel;
    protected JFormattedTextField stockPriceField;
    protected JRadioButton callButton;
    protected JRadioButton putButton;
    private ButtonGroup callPutGroup;

    public StockOptionsTabbedPane()
    {
        super();
    }

    public void initComponent()
    {
        super.initComponent();

        stockPriceLabel = new JLabel( STOCK_PRICE_STRING );
        callPutLabel = new JLabel( CALL_PUT_STRING );

        stockPriceField = createTextField();

        callButton = new JRadioButton( OptionsCalculatorInterface.CALL_PUT.CALL );
        callButton.setSelected( true );
        callButton.setActionCommand( OptionsCalculatorInterface.CALL_PUT.CALL );
        putButton = new JRadioButton( OptionsCalculatorInterface.CALL_PUT.PUT );
        putButton.setActionCommand( OptionsCalculatorInterface.CALL_PUT.PUT );
        callPutGroup = new ButtonGroup();
        callPutGroup.add( callButton );
        callPutGroup.add( putButton );
        JPanel callPutPane = new JPanel( new GridLayout(1,0) );
        callPutPane.add( callButton );
        callPutPane.add( putButton );

        inputPanel.addInputComponent( stockPriceLabel, stockPriceField, OPCInputPane.INPUT_TYPE.TEXT_FIELD, 0 );
        inputPanel.addInputComponent( callPutLabel, callPutPane, OPCInputPane.INPUT_TYPE.RADIO_BUTTON, 0 );
    }
    
    public void actionPerformed( ActionEvent e )
    {
        // Step 1: validate inputs TODO
        // Step 2: send inputs to backend calculator
        String stockPrice = (String)stockPriceField.getValue();
        String strikePrice = inputPanel.getStrikePrice();
        String timeToMaturity = inputPanel.getTimeToMaturity();
        String riskFreeRate = inputPanel.getRiskFreeRate();
        String volatility = inputPanel.getVolatility();
        String longShort = inputPanel.getLongShort();
        String timeUnit = inputPanel.getTimeUnit();
        String riskFreeCompounding = inputPanel.getRiskFreeCompounding();
        String callPut = callPutGroup.getSelection().getActionCommand();

        System.out.println( stockPrice + " " + strikePrice + " " + timeToMaturity + " " + riskFreeRate + " "
                + volatility + " " + longShort + " " + timeUnit + " " + riskFreeCompounding + " " + callPut );

        OptionsCalculatorInterface a = null;
        try
        {
            Class<? extends OptionsCalculatorInterface> b = Class.forName("opc.calculator.StockOption").asSubclass(OptionsCalculatorInterface.class);
            a = b.newInstance();
        }
        catch (Exception cnfe )
        {
            cnfe.printStackTrace();
        }

        HashMap<String,String> inputMap = new HashMap<String,String>();
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.STOCK_PRICE, stockPrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.STRIKE_PRICE, strikePrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.TIME_TO_MATURITY, timeToMaturity.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.RISK_FREE_RATE, riskFreeRate.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.VOLATILITY, volatility.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.LONG_SHORT, longShort.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.TIME_UNIT, timeUnit.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.RISK_FREE_COMPOUNDING, riskFreeCompounding.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.CALL_PUT, callPut.trim() );

        HashMap<String,String> outputMap = new HashMap<String,String>();
        a.sendInputs(inputMap);
        a.calculate();
        outputMap = a.getOutputs();

        outputPanel.setOptionValue( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.OPTION_VALUE) );
        outputPanel.setDelta( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.DELTA) );
        outputPanel.setGamma( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA) );
        outputPanel.setVega( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.VEGA) );
        outputPanel.setGammaP( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA_P) );
        outputPanel.setVegaP( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.VEGA_P) );
        outputPanel.setTheta( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.THETA) );
        outputPanel.setRho( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.RHO) );
        outputPanel.setElasticity( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.ELASTICITY) );
        outputPanel.setSpeed( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.SPEED) );

        // Step 3: collect output from backend and refresh output panel
    }
}
