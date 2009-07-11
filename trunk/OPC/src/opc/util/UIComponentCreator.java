package opc.util;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

import opc.calculator.OptionsCalculatorInterface;

public class UIComponentCreator {

    private static final int INPUT_FIELD_SIZE = 10;
    private static final String INPUT_FIELD_DEFAULT_VALUE = "0";
    private static final String[] COMPOUNDING_FREQUENCY_ITEMS = 
        { "Continuously", "Annually", "Semi-annually", "Quarterly", "Monthly" };
    private static final String[] BASIC_OPTION_TYPES =
        { OptionsCalculatorInterface.OPTION_TYPE.STOCK_OPTION,
          OptionsCalculatorInterface.OPTION_TYPE.STOCK_INDEX_OPTION,
          OptionsCalculatorInterface.OPTION_TYPE.CURRENCY_OPTION,
          OptionsCalculatorInterface.OPTION_TYPE.FUTURES_OPTION };
    
    public static JFormattedTextField createTextField( boolean editable )
    {
        JFormattedTextField field = new JFormattedTextField();
        field.setValue( INPUT_FIELD_DEFAULT_VALUE );
        field.setColumns( INPUT_FIELD_SIZE );
        field.setEditable( editable );
        return field;
    }
    
    public static JRadioButton createRadioButton( String value, boolean selected )
    {
        JRadioButton button = new JRadioButton( value );
        button.setActionCommand( value );
        button.setSelected( selected );
        
        return button;
    }
    
    public static JComboBox createCompoundingComboBox()
    {
        return new JComboBox( COMPOUNDING_FREQUENCY_ITEMS );
    }

    public static JComboBox createOptionTypeComboBox()
    {
        return new JComboBox( BASIC_OPTION_TYPES );
    }
    
}
