/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import opc.calculator.OptionsCalculatorInterface;

/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCInputPane extends JPanel {

    public interface INPUT_TYPE
    {
        final int RADIO_BUTTON = 1;
        final int TEXT_FIELD = 2;
        final int COMBO_BOX = 3;
    }
    
    private final String LONG_SHORT_STRING = "Long/Short: ";
    private final String TIME_UNIT_STRING = "Time Unit: ";
    private final String STRIKE_PRICE_STRING = "Strike Price: ";
    private final String TIME_TO_MATURITY_STRING = "Time to Maturity: ";
    private final String RISK_FREE_RATE_STRING = "Risk-free Rate: ";
    private final String VOLATILITY_STRING = "Volatility: ";
    private final String COMPOUNDING_FREQUENCY_STRING = "Compounding Frequency: ";
    private final String[] COMPOUNDING_FREQUENCY_ITEMS = { "Continuously", "Annually", "Semi-annually", "Quarterly", "Monthly" };
    private final int INPUT_FIELD_SIZE = 10;

    private int numRadioGrp = 2;    // number of radio button groups in the input panel
    private int numTextField = 4;   // number of text fields in the input panel
    private int numComboBox = 1;    // number of combo boxes in the input panel

    private JPanel labelPane;
    private JPanel fieldPane;

    private ButtonGroup longShortGroup;
    private ButtonGroup timeUnitGroup;

    private JRadioButton longButton;
    private JRadioButton shortButton;
    private JRadioButton yearsButton;
    private JRadioButton daysButton;

    private JComboBox compoundingFrequencyComboBox;

    private JLabel longShortLabel;
    private JLabel timeUnitLabel;
    private JLabel strikePriceLabel;
    private JLabel timeToMaturityLabel;
    private JLabel riskFreeRateLabel;
    private JLabel volatilityLabel;
    private JLabel compoundingFrequencyLabel;

    private JFormattedTextField strikePriceField;
    private JFormattedTextField timeToMaturityField;
    private JFormattedTextField riskFreeRateField;
    private JFormattedTextField volatilityField;

    public OPCInputPane()
    {
        super( new BorderLayout() );
        initComponent();
    }

    private void initComponent()
    {
        // init radio buttons
        longShortGroup = new ButtonGroup();
        timeUnitGroup = new ButtonGroup();
        longButton = new JRadioButton( OptionsCalculatorInterface.LONG_SHORT.LONG );
        longButton.setActionCommand( OptionsCalculatorInterface.LONG_SHORT.LONG );
        longButton.setSelected( true );
        shortButton = new JRadioButton( OptionsCalculatorInterface.LONG_SHORT.SHORT );
        shortButton.setActionCommand( OptionsCalculatorInterface.LONG_SHORT.SHORT );
        yearsButton = new JRadioButton( OptionsCalculatorInterface.TIME_UINT.YEAR );
        yearsButton.setActionCommand( OptionsCalculatorInterface.TIME_UINT.YEAR );
        yearsButton.setSelected( true );
        daysButton = new JRadioButton( OptionsCalculatorInterface.TIME_UINT.DAY );
        daysButton.setActionCommand( OptionsCalculatorInterface.TIME_UINT.DAY );
        longShortGroup.add( longButton );
        longShortGroup.add( shortButton );
        timeUnitGroup.add( yearsButton );
        timeUnitGroup.add( daysButton );
        JPanel longShortPane = new JPanel( new GridLayout(1,0) );
        longShortPane.add( longButton );
        longShortPane.add( shortButton );
        JPanel timeUnitPane = new JPanel( new GridLayout(1,0) );
        timeUnitPane.add( yearsButton );
        timeUnitPane.add( daysButton );
        
        // init labels
        longShortLabel = new JLabel( LONG_SHORT_STRING );
        timeUnitLabel = new JLabel( TIME_UNIT_STRING );
        strikePriceLabel = new JLabel( STRIKE_PRICE_STRING );
        timeToMaturityLabel = new JLabel( TIME_TO_MATURITY_STRING );
        riskFreeRateLabel = new JLabel( RISK_FREE_RATE_STRING );
        volatilityLabel = new JLabel( VOLATILITY_STRING );
        compoundingFrequencyLabel = new JLabel( COMPOUNDING_FREQUENCY_STRING );

        // init text fields
        strikePriceField = createTextField();
        timeToMaturityField = createTextField();
        riskFreeRateField = createTextField();
        volatilityField = createTextField();

        // bond labels to text fields
        strikePriceLabel.setLabelFor( strikePriceField );
        timeToMaturityLabel.setLabelFor( timeToMaturityField );
        riskFreeRateLabel.setLabelFor( riskFreeRateField );
        volatilityLabel.setLabelFor( volatilityField );

        // init combo boxes
        compoundingFrequencyComboBox = new JComboBox( COMPOUNDING_FREQUENCY_ITEMS );

        //Lay out the labels in a panel.
        labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add( longShortLabel );
        labelPane.add( timeUnitLabel );
        labelPane.add( strikePriceLabel );
        labelPane.add( timeToMaturityLabel );
        labelPane.add( riskFreeRateLabel );
        labelPane.add( volatilityLabel );
        labelPane.add( compoundingFrequencyLabel );

        //Layout the text fields in a panel.
        fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add( longShortPane );
        fieldPane.add( timeUnitPane );
        fieldPane.add( strikePriceField );
        fieldPane.add( timeToMaturityField );
        fieldPane.add( riskFreeRateField );
        fieldPane.add( volatilityField );
        fieldPane.add( compoundingFrequencyComboBox );

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createTitledBorder("INPUT"));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }

    protected JFormattedTextField createTextField()
    {
        JFormattedTextField field = new JFormattedTextField();
        field.setValue( "0.0" );
        field.setColumns( INPUT_FIELD_SIZE );

        return field;
    }

    public void addInputComponent( JLabel label, JComponent component, int inputType, int offset )
    {
        int index = 0;
        switch( inputType )
        {
            case INPUT_TYPE.RADIO_BUTTON:
            {
                index = offset;
                numRadioGrp++;
                break;
            }
            case INPUT_TYPE.TEXT_FIELD:
            {
                //component = createTextField();
                index = numRadioGrp + offset;
                numTextField++;
                break;
            }
            case INPUT_TYPE.COMBO_BOX:
            {
                index = numRadioGrp + numTextField + offset;
                numComboBox++;
                break;
            }
        }

        label.setLabelFor( component );
        labelPane.add( label, index );
        fieldPane.add( component, index );
    }

    public String getStrikePrice()
    {
        return (String)strikePriceField.getValue();
    }

    public String getTimeToMaturity()
    {
        return (String)timeToMaturityField.getValue();
    }

    public String getRiskFreeRate()
    {
        return (String)riskFreeRateField.getValue();
    }

    public String getVolatility()
    {
        return (String)volatilityField.getValue();
    }

    public String getLongShort()
    {
        return longShortGroup.getSelection().getActionCommand();
    }

    public String getTimeUnit()
    {
        return timeUnitGroup.getSelection().getActionCommand();
    }

    public String getCompoundingFrequency()
    {
        return compoundingFrequencyComboBox.getSelectedItem().toString();
    }
}
