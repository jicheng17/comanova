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
public class FuturesOptionsTabbedPane extends OPCTabbedPane {

    private final String FUTURES_PRICE_STRING = "Futures Price: ";
    private final String CALL_PUT_STRING = "Call/Put: ";

    private JLabel futuresPriceLabel;
    private JLabel callPutLabel;
    private JFormattedTextField futuresPriceField;
    private JRadioButton callButton;
    private JRadioButton putButton;
    private ButtonGroup callPutGroup;

    public FuturesOptionsTabbedPane()
    {
        super();
    }

    public void initComponent()
    {
        super.initComponent();

        futuresPriceLabel = new JLabel( FUTURES_PRICE_STRING );
        callPutLabel = new JLabel( CALL_PUT_STRING );
        futuresPriceField = UIComponentCreator.createTextField();
        futuresPriceLabel.setLabelFor( futuresPriceField );
        callPutGroup = new ButtonGroup();
        callPutGroup.add( callButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.CALL, true) );
        callPutGroup.add( putButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.PUT, false) );
        JPanel callPutPane = new JPanel( new GridLayout(1,0) );
        callPutPane.add( callButton );
        callPutPane.add( putButton );

        inputPanel.addInputComponent( futuresPriceLabel, futuresPriceField, OPCInputPane.INPUT_TYPE.TEXT_FIELD, 0 );
        inputPanel.addInputComponent( callPutLabel, callPutPane, OPCInputPane.INPUT_TYPE.RADIO_BUTTON, 0 );
    }
    
    public HashMap<String,String> constructInputMap()
    {
        HashMap<String,String> inputMap = super.constructInputMap();
        
        // add additional inputs for this particular option type
        String futuresPrice = (String)futuresPriceField.getValue();
        String callPut = callPutGroup.getSelection().getActionCommand();
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.FUTURES_PRICE, futuresPrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.CALL_PUT, callPut.trim() );
        
        return inputMap;
    }
    
    public void refreshOutput( HashMap<String,String> outputMap )
    {
        super.refreshOutput( outputMap );
        
        // update additional outputs for this particular option type
    }
}
