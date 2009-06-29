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

import opc.calculator.OptionsCalculatorInterface;
import opc.calculator.StockOption;

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
        double stockPrice = Double.parseDouble( (String)stockPriceField.getValue() );
        double strikePrice = Double.parseDouble( inputPanel.getStrikePrice() );
        double timeToMaturity = Double.parseDouble( inputPanel.getTimeToMaturity() );
        double riskFreeRate = Double.parseDouble( inputPanel.getRiskFreeRate() );
        double volatility = Double.parseDouble( inputPanel.getVolatility() );
        String longShort = inputPanel.getLongShort();
        String timeUnit = inputPanel.getTimeUnit();
        String compoundingFrequency = inputPanel.getCompoundingFrequency();
        String callPut = callPutGroup.getSelection().getActionCommand();

        System.out.println( stockPrice + " " + strikePrice + " " + timeToMaturity + " " + riskFreeRate + " "
                + volatility + " " + longShort + " " + timeUnit + " " + compoundingFrequency + " " + callPut );

        StockOption calculator = new StockOption( stockPrice, strikePrice, timeToMaturity, riskFreeRate, volatility,
                timeUnit, compoundingFrequency, longShort, callPut );

        calculator.longOrShort();
        calculator.callOrPut();
        calculator.calculateValue();

        double price = 0.0;
        if (callPut.equals("call"))
           price = calculator.getCall();
        else if (callPut.equals("put"))
           price = calculator.getPut();

        double delta = calculator.getDelta();
        double gamma = calculator.getGamma();
        double gammaP = calculator.getGammaP();
        double vega = calculator.getVega()/100;
        double theta = calculator.getTheta()/365;
        double rho = calculator.getRho()/100;
        double elasticity = calculator.getElasticity();
        double carry = calculator.getCarry()/100;

        outputPanel.setOptionValue( price );

        /*OptionsCalculatorInterface a = null;
        try
        {
            Class<? extends OptionsCalculatorInterface> tabbedPane = Class.forName("opc.calculator.StockOption").asSubclass(OptionsCalculatorInterface.class);
            a = tabbedPane.newInstance();
        }
        catch (Exception cnfe )
        {
            cnfe.printStackTrace();
        }*/
        // Step 3: collect output from backend and refresh output panel
    }
}
