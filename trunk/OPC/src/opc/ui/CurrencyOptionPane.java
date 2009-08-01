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
public class CurrencyOptionPane extends OPCBasePane {

    private final String CURRENCY_SPOT_PRICE_STRING = "Currency Spot Price: ";
    private final String FOREIGN_RISK_FREE_RATE_STRING = "Foreign Risk Free Rate: ";
    private final String CALL_PUT_STRING = "Call/Put: ";
    private final String FOREIGN_RISK_COMPOUNDING_STRING = "Foreign Risk Compounding: ";

    private JLabel currencySpotPriceLabel;
    private JLabel foreignRiskFreeRateLabel;
    private JLabel callPutLabel;
    private JLabel foreignRiskCompoundingLabel;
    private JFormattedTextField currencySpotPriceField;
    private JFormattedTextField foreignRiskFreeRateField;
    private JRadioButton callButton;
    private JRadioButton putButton;
    private ButtonGroup callPutGroup;
    private JComboBox foreignRiskCompoundingComboBox;

    //private JComponent riskNeutralDensityGraphTab;

    public CurrencyOptionPane()
    {
        super();
    }

    public void initComponent()
    {
        super.initComponent();

        currencySpotPriceLabel = new JLabel( CURRENCY_SPOT_PRICE_STRING );
        callPutLabel = new JLabel( CALL_PUT_STRING );
        foreignRiskFreeRateLabel = new JLabel( FOREIGN_RISK_FREE_RATE_STRING );
        foreignRiskCompoundingLabel = new JLabel( FOREIGN_RISK_COMPOUNDING_STRING );

        currencySpotPriceField = UIComponentCreator.createTextField( true );
        currencySpotPriceLabel.setLabelFor( currencySpotPriceField );
        
        foreignRiskFreeRateField = UIComponentCreator.createTextField( true );
        foreignRiskFreeRateLabel.setLabelFor( foreignRiskFreeRateField );
        foreignRiskCompoundingComboBox = UIComponentCreator.createCompoundingComboBox();

        callPutGroup = new ButtonGroup();
        callPutGroup.add( callButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.CALL, true) );
        callPutGroup.add( putButton=UIComponentCreator.createRadioButton(OptionsCalculatorInterface.CALL_PUT.PUT, false) );
        JPanel callPutPane = new JPanel( new GridLayout(1,0) );
        callPutPane.add( callButton );
        callPutPane.add( putButton );

        inputPanel.addInputComponent( currencySpotPriceLabel, currencySpotPriceField, OPCInputPane.INPUT_TYPE.TEXT_FIELD, 0 );
        inputPanel.addInputComponent( foreignRiskFreeRateLabel, foreignRiskFreeRateField, OPCInputPane.INPUT_TYPE.TEXT_FIELD, 4 );
        inputPanel.addInputComponent( callPutLabel, callPutPane, OPCInputPane.INPUT_TYPE.RADIO_BUTTON, 0 );
        inputPanel.addInputComponent( foreignRiskCompoundingLabel, foreignRiskCompoundingComboBox, 
                OPCInputPane.INPUT_TYPE.COMBO_BOX, 1 );

        //riskNeutralDensityGraphTab = makeTextPanel( "To be implemented" );
        //this.addTab( RISK_NEUTRAL_DENSITY_TITLE, null, riskNeutralDensityGraphTab, "Risk Neutral Density Graph Panel" );
    }
    
    public HashMap<String,String> constructInputMap()
    {
        HashMap<String,String> inputMap = super.constructInputMap();
        
        // add additional inputs for this particular option type
        String currencySpotPrice = (String)currencySpotPriceField.getValue();
        String callPut = callPutGroup.getSelection().getActionCommand();
        String foreignRiskFreeRate = (String)foreignRiskFreeRateField.getValue();
        String foreignRiskCompounding = (String)foreignRiskCompoundingComboBox.getSelectedItem();
        
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.CURRENCY_SPOT_PRICE, currencySpotPrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.CALL_PUT, callPut.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.FOREIGN_RISK_FREE_RATE, foreignRiskFreeRate.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.FOREIGN_RISK_COMPOUNDING, foreignRiskCompounding.trim() );
        
        return inputMap;
    }
    
    public void refreshOutput( HashMap<String,String> outputMap )
    {
        super.refreshOutput( outputMap );
        
        // update additional outputs for this particular option type
    }
}
