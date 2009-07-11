/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import opc.calculator.OptionsCalculatorInterface;
import opc.util.UIComponentCreator;

/**
 *
 * @author ZHAO QINGHUA
 */
public class StockIndexOptionsTabbedPane extends OPCTabbedPane {

    private final String STOCK_INDEX_PRICE_STRING = "Stock Index Price: ";
    private final String DIVIDEND_YIELD_STRING = "Dividend Yield: ";
    private final String CALL_PUT_STRING = "Call/Put: ";
    private final String DIVIDEND_COMPOUNDING_STRING = "Dividend Compounding: ";

    private JLabel stockIndexPriceLabel;
    private JLabel dividendYieldLabel;
    private JLabel callPutLabel;
    private JLabel dividendCompoundingLabel;
    private JFormattedTextField stockIndexPriceField;
    private JFormattedTextField dividendYieldField;
    private JRadioButton callButton;
    private JRadioButton putButton;
    private ButtonGroup callPutGroup;
    private JComboBox dividendCompoundingComboBox;

    private JComponent riskNeutralDensityGraphTab;

    public StockIndexOptionsTabbedPane()
    {
        super();
    }

    public void initComponent()
    {
        super.initComponent();

        stockIndexPriceLabel = new JLabel( STOCK_INDEX_PRICE_STRING );
        callPutLabel = new JLabel( CALL_PUT_STRING );
        dividendYieldLabel = new JLabel( DIVIDEND_YIELD_STRING );
        dividendCompoundingLabel = new JLabel( DIVIDEND_COMPOUNDING_STRING );
        
        stockIndexPriceField = UIComponentCreator.createTextField( true );
        stockIndexPriceLabel.setLabelFor( stockIndexPriceField );
        
        dividendYieldField = UIComponentCreator.createTextField( true );
        dividendYieldLabel.setLabelFor( dividendYieldField );
        dividendCompoundingComboBox = UIComponentCreator.createCompoundingComboBox();
        
        callPutGroup = new ButtonGroup();
        callPutGroup.add( callButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.CALL, true) );
        callPutGroup.add( putButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.PUT, false) );
        JPanel callPutPane = new JPanel( new GridLayout(1,0) );
        callPutPane.add( callButton );
        callPutPane.add( putButton );

        inputPanel.addInputComponent( stockIndexPriceLabel, stockIndexPriceField, OPCInputPane.INPUT_TYPE.TEXT_FIELD, 0 );
        inputPanel.addInputComponent( dividendYieldLabel, dividendYieldField, OPCInputPane.INPUT_TYPE.TEXT_FIELD, 4 );
        inputPanel.addInputComponent( callPutLabel, callPutPane, OPCInputPane.INPUT_TYPE.RADIO_BUTTON, 0 );
        inputPanel.addInputComponent( dividendCompoundingLabel, dividendCompoundingComboBox, 
                OPCInputPane.INPUT_TYPE.COMBO_BOX, 1 );

        outputPanel.addRiskNeutralDensity();

        riskNeutralDensityGraphTab = makeTextPanel( "To be implemented" );
        this.addTab( RISK_NEUTRAL_DENSITY_TITLE, null, riskNeutralDensityGraphTab, "Risk Neutral Density Graph Panel" );

    }
    
    public HashMap<String,String> constructInputMap()
    {
        HashMap<String,String> inputMap = super.constructInputMap();
        
        // add additional inputs for this particular option type
        String stockIndexPrice = (String)stockIndexPriceField.getValue();
        String callPut = callPutGroup.getSelection().getActionCommand();
        String dividendYield = (String)dividendYieldField.getValue();
        String dividendCompounding = (String)dividendCompoundingComboBox.getSelectedItem();
        
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.STOCK_INDEX_PRICE, stockIndexPrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.CALL_PUT, callPut.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.DIVIDEND_YIELD, dividendYield.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.DIVIDEND_COMPOUNDING, dividendCompounding.trim() );
        
        return inputMap;
    }
    
    public void refreshOutput( HashMap<String,String> outputMap )
    {
        super.refreshOutput( outputMap );
        
        // update additional outputs for this particular option type
    }
}
