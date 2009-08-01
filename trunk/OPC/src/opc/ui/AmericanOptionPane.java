/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import java.util.HashMap;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import opc.calculator.OptionsCalculatorInterface;
import opc.util.UIComponentCreator;

/**
 *
 * @author ZHAO QINGHUA
 */
public class AmericanOptionPane extends OPCBasePane {

    private final String OPTION_TYPE_STRING = "Option Type: ";

    private JLabel optionTypeLabel;
    private JComboBox optionTypeComboBox;

    private OPCBasePane tabbedPane = new OPCBasePane();

    public AmericanOptionPane()
    {
        super();
    }

    public void initComponent()
    {
        super.initComponent();

        optionTypeLabel = new JLabel( OPTION_TYPE_STRING );
        optionTypeComboBox = UIComponentCreator.createOptionTypeComboBox();
        optionTypeComboBox.addActionListener( this );

        String className = OPCMainUI.treeNodeNameUIClassMap.get( OptionsCalculatorInterface.OPTION_TYPE.STOCK_OPTION );
        try
        {
            Class<? extends OPCBasePane> tabbedPaneFactory = Class.forName(className).asSubclass(OPCBasePane.class);
            tabbedPane = tabbedPaneFactory.newInstance();
        }
        catch (Exception cnfe )
        {
            cnfe.printStackTrace();
        }
        tabbedPane.initComponent();
        tabbedPane.inputPanel.addInputComponent( optionTypeLabel, optionTypeComboBox, OPCInputPane.INPUT_TYPE.RADIO_BUTTON, 0 );
        inputPanel.reconstructInputPane( tabbedPane.inputPanel );

        outputPanel.addEuropeanOptionValue();
    }

    public void actionPerformed( ActionEvent e )
    {
        // Handling option types combo box selection event
        if( e.getSource() instanceof JComboBox )
        {
            JComboBox temp = (JComboBox)e.getSource();
            String selected = (String)temp.getSelectedItem();
            String className = OPCMainUI.treeNodeNameUIClassMap.get( selected );
            try
            {
                Class<? extends OPCBasePane> tabbedPaneFactory = Class.forName(className).asSubclass(OPCBasePane.class);
                tabbedPane = tabbedPaneFactory.newInstance();
            }
            catch (Exception cnfe )
            {
                cnfe.printStackTrace();
            }

            tabbedPane.initComponent();
            tabbedPane.inputPanel.addInputComponent( optionTypeLabel, optionTypeComboBox, OPCInputPane.INPUT_TYPE.RADIO_BUTTON, 0 );
            inputPanel.reconstructInputPane( tabbedPane.inputPanel );
            
            return;
        }

        // Handling calculate button event
        super.actionPerformed(e);
    }

    public HashMap<String,String> constructInputMap()
    {
        HashMap<String,String> inputMap = tabbedPane.constructInputMap();
        
        String optionType = (String)optionTypeComboBox.getSelectedItem();
        
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.OPTION_TYPE, optionType.trim() );

        return inputMap;
    }

    public void refreshOutput( HashMap<String,String> outputMap )
    {
        super.refreshOutput( outputMap );

        outputPanel.setEuropeanOptionValue( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.EUROPEAN_OPTION_VALUE) );
        // update additional outputs for this particular option type
    }
}
