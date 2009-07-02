package opc.util;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

public class UIComponentCreator {

    private static final int INPUT_FIELD_SIZE = 10;
    private static final String INPUT_FIELD_DEFAULT_VALUE = "0";
    private static final String[] COMPOUNDING_FREQUENCY_ITEMS = 
        { "Continuously", "Annually", "Semi-annually", "Quarterly", "Monthly" };
    
    public static JFormattedTextField createTextField()
    {
        JFormattedTextField field = new JFormattedTextField();
        field.setValue( INPUT_FIELD_DEFAULT_VALUE );
        field.setColumns( INPUT_FIELD_SIZE );

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
}
