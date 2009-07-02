/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import opc.calculator.OptionsCalculatorInterface;
import opc.util.UIComponentCreator;

/**
 *
 * @author ZHAO QINGHUA
 */
public class StockOptionsTabbedPane extends OPCTabbedPane {

    private final String STOCK_PRICE_STRING = "Stock Price: ";
    private final String CALL_PUT_STRING = "Call/Put: ";

    private JLabel stockPriceLabel;
    private JLabel callPutLabel;
    private JFormattedTextField stockPriceField;
    private JRadioButton callButton;
    private JRadioButton putButton;
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
        stockPriceField = UIComponentCreator.createTextField();
        stockPriceLabel.setLabelFor( stockPriceField );
        callPutGroup = new ButtonGroup();
        callPutGroup.add( callButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.CALL, true) );
        callPutGroup.add( putButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.PUT, false) );
        JPanel callPutPane = new JPanel( new GridLayout(1,0) );
        callPutPane.add( callButton );
        callPutPane.add( putButton );

        inputPanel.addInputComponent( stockPriceLabel, stockPriceField, OPCInputPane.INPUT_TYPE.TEXT_FIELD, 0 );
        inputPanel.addInputComponent( callPutLabel, callPutPane, OPCInputPane.INPUT_TYPE.RADIO_BUTTON, 0 );
    }
    
    public HashMap<String,String> constructInputMap()
    {
        HashMap<String,String> inputMap = super.constructInputMap();
        
        // add additional inputs for this particular option type
        String stockPrice = (String)stockPriceField.getValue();
        String callPut = callPutGroup.getSelection().getActionCommand();
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.STOCK_PRICE, stockPrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.CALL_PUT, callPut.trim() );
        
        return inputMap;
    }
    
    public void refreshOutput( HashMap<String,String> outputMap )
    {
        super.refreshOutput( outputMap );
        
        // update additional outputs for this particular option type
    }
}
