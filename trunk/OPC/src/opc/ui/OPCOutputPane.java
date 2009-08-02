/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import opc.util.UIComponentCreator;
/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCOutputPane extends JPanel implements ActionListener {

    public final static String NA = "N.A.";
    private final static String DEFAULT = "Default";
    private final String OPTION_VALUE_STRING = "Option Value: ";
    private final String AMERICAN_OPTION_VALUE_STRING = "American Option Value: ";
    private final String EUROPEAN_OPTION_VALUE_STRING = "European Option Value: ";
    private final String DELTA_STRING = "Delta: ";
    private final String DELTAX_STRING = "DeltaX: ";
    private final String DDELTA_DVOL_STRING = "dDeltaDvol: ";
    private final String GAMMA_STRING = "Gamma: ";
    private final String GAMMAX_STRING = "GammaX: ";
    private final String GAMMAP_STRING = "GammaP: ";
    private final String DGAMMA_DVOL_STRING = "dGammaDvol: ";
    private final String VEGA_STRING = "Vega: ";
    private final String VEGAP_STRING = "VegaP: ";
    private final String DVEGA_DVOL_STRING = "dVegaDvol: ";
    private final String RHO_STRING = "Rho: ";
    private final String FUTURES_RHO_STRING = "FuturesRho: ";
    private final String ELASTICITY_STRING = "Elasticity: ";
    private final String THETA_STRING = "Theta: ";
    private final String SPEED_STRING = "Speed: ";
    private final String CARRY_STRING = "Carry: ";

    public interface BUTTON_ACTION
    {
        public final String SHOW_OPTION_VALUE = "1";
        public final String SHOW_DELTA = "2";
        public final String SHOW_DELTAX = "3";
        public final String SHOW_DDELTA_DVOL = "4";
        public final String SHOW_GAMMA = "5";
        public final String SHOW_GAMMAX = "6";
        public final String SHOW_GAMMAP = "7";
        public final String SHOW_DGAMMA_DVOL = "8";
        public final String SHOW_VEGA = "9";
        public final String SHOW_VEGAP = "10";
        public final String SHOW_DVEGA_DVOL = "11";
        public final String SHOW_RHO = "12";
        public final String SHOW_FUTURES_RHO = "13";
        public final String SHOW_ELASTICITY = "14";
        public final String SHOW_THETA = "15";
        public final String SHOW_SPEED = "16";
        public final String SHOW_CARRY = "17";
    }

    private JPanel labelPane;
    private JPanel fieldPane;
    private JPanel buttonPane;

    private JLabel optionValueLabel;
    private JLabel europeanOptionValueLabel;
    private JLabel deltaLabel;
    private JLabel deltaXLabel;
    private JLabel dDeltaDvolLabel;
    private JLabel gammaLabel;
    private JLabel gammaXLabel;
    private JLabel gammaPLabel;
    private JLabel dGammaDvolLabel;
    private JLabel vegaLabel;
    private JLabel vegaPLabel;
    private JLabel dVegaDvolLabel;
    private JLabel rhoLabel;
    private JLabel futuresRhoLabel;
    private JLabel elasticityLabel;
    private JLabel thetaLabel;
    private JLabel speedLabel;
    private JLabel carryLabel;

    private JFormattedTextField optionValueField;
    private JFormattedTextField europeanOptionValueField;
    private JFormattedTextField deltaField;
    private JFormattedTextField deltaXField;
    private JFormattedTextField dDeltaDvolField;
    private JFormattedTextField gammaField;
    private JFormattedTextField gammaXField;
    private JFormattedTextField gammaPField;
    private JFormattedTextField dGammaDvolField;
    private JFormattedTextField vegaField;
    private JFormattedTextField vegaPField;
    private JFormattedTextField dVegaDvolField;
    private JFormattedTextField futuresRhoField;
    private JFormattedTextField rhoField;
    private JFormattedTextField elasticityField;
    private JFormattedTextField thetaField;
    private JFormattedTextField speedField;
    private JFormattedTextField carryField;

    private JButton optionValueButton;
    private JButton europeanOptionValueButton;
    private JButton deltaButton;
    private JButton deltaXButton;
    private JButton dDeltaDvolButton;
    private JButton gammaButton;
    private JButton gammaXButton;
    private JButton gammaPButton;
    private JButton dGammaDvolButton;
    private JButton vegaButton;
    private JButton vegaPButton;
    private JButton dVegaDvolButton;
    private JButton rhoButton;
    private JButton futuresRhoButton;
    private JButton elasticityButton;
    private JButton thetaButton;
    private JButton speedButton;
    private JButton carryButton;

    public OPCOutputPane()
    {
        super( new BorderLayout() );
        initComponent();
    }

    private void initComponent()
    {
        // init labels
        optionValueLabel = new JLabel( OPTION_VALUE_STRING );
        europeanOptionValueLabel = new JLabel( EUROPEAN_OPTION_VALUE_STRING );
        deltaLabel = new JLabel( DELTA_STRING );
        deltaXLabel = new JLabel( DELTAX_STRING );
        dDeltaDvolLabel = new JLabel( DDELTA_DVOL_STRING );
        gammaLabel = new JLabel( GAMMA_STRING );
        gammaXLabel = new JLabel( GAMMAX_STRING );
        gammaPLabel = new JLabel( GAMMAP_STRING );
        dGammaDvolLabel = new JLabel( DGAMMA_DVOL_STRING );
        vegaLabel = new JLabel( VEGA_STRING );
        vegaPLabel = new JLabel( VEGAP_STRING );
        dVegaDvolLabel = new JLabel( DVEGA_DVOL_STRING );
        rhoLabel = new JLabel( RHO_STRING );
        futuresRhoLabel = new JLabel( FUTURES_RHO_STRING );
        elasticityLabel = new JLabel( ELASTICITY_STRING );
        thetaLabel = new JLabel( THETA_STRING );
        speedLabel = new JLabel( SPEED_STRING );
        carryLabel = new JLabel( CARRY_STRING );

        // init text fields
        optionValueField = UIComponentCreator.createTextField( false );
        europeanOptionValueField = UIComponentCreator.createTextField( false );
        deltaField = UIComponentCreator.createTextField( false );
        deltaXField = UIComponentCreator.createTextField( false );
        dDeltaDvolField = UIComponentCreator.createTextField( false );
        gammaField = UIComponentCreator.createTextField( false );
        gammaXField = UIComponentCreator.createTextField( false );
        gammaPField = UIComponentCreator.createTextField( false );
        dGammaDvolField = UIComponentCreator.createTextField( false );
        vegaField = UIComponentCreator.createTextField( false );
        vegaPField = UIComponentCreator.createTextField( false );
        dVegaDvolField = UIComponentCreator.createTextField( false );
        rhoField = UIComponentCreator.createTextField( false );
        futuresRhoField = UIComponentCreator.createTextField( false );
        elasticityField = UIComponentCreator.createTextField( false );
        thetaField = UIComponentCreator.createTextField( false );
        speedField = UIComponentCreator.createTextField( false );
        carryField = UIComponentCreator.createTextField( false );

        // init buttons
        optionValueButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_OPTION_VALUE, "Show Plot", true, this );
        europeanOptionValueButton = UIComponentCreator.createButton( "", "Show Plot", false, this );
        deltaButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_DELTA, "Show Plot", true, this );
        deltaXButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_DELTAX, "Show Plot", true, this );
        dDeltaDvolButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_DDELTA_DVOL, "Show Plot", true, this );
        gammaButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_GAMMA, "Show Plot", true, this );
        gammaXButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_GAMMAX, "Show Plot", true, this );
        gammaPButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_GAMMAP, "Show Plot", true, this );
        dGammaDvolButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_DGAMMA_DVOL, "Show Plot", true, this );
        vegaButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_VEGA, "Show Plot", true, this );
        vegaPButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_VEGAP, "Show Plot", true, this );
        dVegaDvolButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_DVEGA_DVOL, "Show Plot", true, this );
        rhoButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_RHO, "Show Plot", true, this );
        futuresRhoButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_FUTURES_RHO, "Show Plot", true, this );
        elasticityButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_ELASTICITY, "Show Plot", true, this );
        thetaButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_THETA, "Show Plot", true, this );
        speedButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_SPEED, "Show Plot", true, this );
        carryButton = UIComponentCreator.createButton( BUTTON_ACTION.SHOW_CARRY, "Show Plot", true, this );

        // bond labels to text fields
        optionValueLabel.setLabelFor( optionValueField );
        deltaLabel.setLabelFor( deltaField );
        deltaXLabel.setLabelFor( deltaXField );
        dDeltaDvolLabel.setLabelFor( dDeltaDvolField );
        gammaLabel.setLabelFor( gammaField );
        gammaXLabel.setLabelFor( gammaXField );
        gammaPLabel.setLabelFor( gammaPField );
        dGammaDvolLabel.setLabelFor( dGammaDvolField );
        vegaLabel.setLabelFor( vegaField );
        vegaPLabel.setLabelFor( vegaPField );
        dVegaDvolLabel.setLabelFor( dVegaDvolField );
        rhoLabel.setLabelFor( rhoField );
        futuresRhoLabel.setLabelFor( futuresRhoField );
        elasticityLabel.setLabelFor( elasticityField );
        thetaLabel.setLabelFor( thetaField );
        speedLabel.setLabelFor( speedField );
        carryLabel.setLabelFor( carryField );

        //Lay out the labels in a panel.
        labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add( optionValueLabel );
        labelPane.add( deltaLabel );
        labelPane.add( deltaXLabel );
        labelPane.add( dDeltaDvolLabel );
        labelPane.add( gammaLabel );
        labelPane.add( gammaXLabel );
        labelPane.add( gammaPLabel );
        labelPane.add( dGammaDvolLabel );
        labelPane.add( vegaLabel );
        labelPane.add( vegaPLabel );
        labelPane.add( dVegaDvolLabel );
        labelPane.add( rhoLabel );
        labelPane.add( futuresRhoLabel );
        labelPane.add( elasticityLabel );
        labelPane.add( thetaLabel );
        labelPane.add( speedLabel );
        labelPane.add( carryLabel);

        //Layout the text fields in a panel.
        fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add( optionValueField );
        fieldPane.add( deltaField );
        fieldPane.add( deltaXField );
        fieldPane.add( dDeltaDvolField );
        fieldPane.add( gammaField );
        fieldPane.add( gammaXField );
        fieldPane.add( gammaPField );
        fieldPane.add( dGammaDvolField );
        fieldPane.add( vegaField );
        fieldPane.add( vegaPField );
        fieldPane.add( dVegaDvolField );
        fieldPane.add( rhoField );
        fieldPane.add( futuresRhoField );
        fieldPane.add( elasticityField );
        fieldPane.add( thetaField );
        fieldPane.add( speedField );
        fieldPane.add( carryField );

        // Layout the buttons in a panel
        buttonPane = new JPanel(new GridLayout(0,1) );
        buttonPane.add( optionValueButton );
        buttonPane.add( deltaButton );
        buttonPane.add( deltaXButton );
        buttonPane.add( dDeltaDvolButton );
        buttonPane.add( gammaButton );
        buttonPane.add( gammaXButton );
        buttonPane.add( gammaPButton );
        buttonPane.add( dGammaDvolButton );
        buttonPane.add( vegaButton );
        buttonPane.add( vegaPButton );
        buttonPane.add( dVegaDvolButton );
        buttonPane.add( rhoButton );
        buttonPane.add( futuresRhoButton );
        buttonPane.add( elasticityButton );
        buttonPane.add( thetaButton );
        buttonPane.add( speedButton );
        buttonPane.add( carryButton );

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createTitledBorder("OUTPUT"));
        add(labelPane, BorderLayout.LINE_START);
        add(fieldPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.LINE_END);
    }

    public void addOutputComponent( JLabel label, JFormattedTextField component )
    {
        label.setLabelFor( component );
        labelPane.add( label );
        labelPane.validate();
        fieldPane.add( component );
        fieldPane.validate();
    }

    public void addEuropeanOptionValue()
    {
        optionValueLabel.setText( AMERICAN_OPTION_VALUE_STRING );
        europeanOptionValueLabel.setLabelFor( europeanOptionValueField );
        labelPane.add( europeanOptionValueLabel, 1 );
        labelPane.validate();
        fieldPane.add( europeanOptionValueField, 1 );
        fieldPane.validate();
        buttonPane.add( europeanOptionValueButton, 1 );
        buttonPane.validate();
    }

    public void removeTheta()
    {
        labelPane.remove( thetaLabel );
        labelPane.validate();
        fieldPane.remove( thetaField );
        fieldPane.validate();
        buttonPane.remove( thetaButton );
        buttonPane.validate();
    }

    public void setOptionValue( String value )
    {
        optionValueField.setValue( value );
    }

    public void setEuropeanOptionValue( String value )
    {
        europeanOptionValueField.setValue( value );
    }
    
    public void setDelta( String value )
    {
        deltaField.setValue( value );
    }

    public void setDeltaX( String value )
    {
        deltaXField.setValue( value );
    }

    public void setDDeltaDvol( String value )
    {
        dDeltaDvolField.setValue( value );
    }

    public void setGamma( String value )
    {
        gammaField.setValue( value );
    }

    public void setGammaX( String value )
    {
        gammaXField.setValue( value );
    }

    public void setGammaP( String value )
    {
        gammaPField.setValue( value );
    }

    public void setDGammaDvol( String value )
    {
        dGammaDvolField.setValue( value );
    }

    public void setVega( String value )
    {
        vegaField.setValue( value );
    }

    public void setVegaP( String value )
    {
        vegaPField.setValue( value );
    }

    public void setDVegaDvol( String value )
    {
        dVegaDvolField.setValue( value );
    }

    public void setRho( String value )
    {
        rhoField.setValue( value );
    }

    public void setFuturesRho( String value )
    {
        futuresRhoField.setValue( value );
    }

    public void setElasticity( String value )
    {
        elasticityField.setValue( value );
    }

    public void setTheta( String value )
    {
        thetaField.setValue( value );
    }
    
    public void setSpeed( String value )
    {
        speedField.setValue( value );
    }

    public void setCarry( String value )
    {
        carryField.setValue( value );
    }

    public void actionPerformed( ActionEvent e )
    {
        String action = e.getActionCommand();
        if( action.equals(BUTTON_ACTION.SHOW_OPTION_VALUE) )
        {
            performGraphAnalysis( optionValueField, "Option Value" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_DELTA) )
        {
            performGraphAnalysis( deltaField, "Delta" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_DELTAX) )
        {
            performGraphAnalysis( deltaXField, "DeltaX" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_DDELTA_DVOL) )
        {
            performGraphAnalysis( dDeltaDvolField, "dDeltaDvol" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_GAMMA) )
        {
            performGraphAnalysis( gammaField, "Gamma" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_GAMMAX) )
        {
            performGraphAnalysis( gammaXField, "GammaX" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_GAMMAP) )
        {
            performGraphAnalysis( gammaPField, "GammaP" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_DGAMMA_DVOL) )
        {
            performGraphAnalysis( dGammaDvolField, "dGammaDvol" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_VEGA) )
        {
            performGraphAnalysis( vegaField, "Vega" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_VEGAP) )
        {
            performGraphAnalysis( vegaPField, "VegaP" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_DVEGA_DVOL) )
        {
            performGraphAnalysis( dVegaDvolField, "dVegaDvol" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_RHO) )
        {
            performGraphAnalysis( rhoField, "Rho" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_FUTURES_RHO) )
        {
            performGraphAnalysis( futuresRhoField, "FuturesRho" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_ELASTICITY) )
        {
            performGraphAnalysis( elasticityField, "Elasticity" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_THETA) )
        {
            performGraphAnalysis( thetaField, "Theta" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_SPEED) )
        {
            performGraphAnalysis( speedField, "Speed" );
        }
        else if (action.equals(BUTTON_ACTION.SHOW_CARRY) )
        {
            performGraphAnalysis( carryField, "Carry" );
        }
    }

    private void performGraphAnalysis( JFormattedTextField field, String label )
    {
        if( field.getValue().equals(UIComponentCreator.INPUT_FIELD_DEFAULT_VALUE) )
        {
            showGraph( DEFAULT );
        }
        else if( field.getValue().equals(NA) )
        {
            showGraph( NA );
        }
        else
        {
            showGraph( label );
        }
    }

    private void showGraph( String label )
    {
        if( label.equals(DEFAULT) )
        {
            JOptionPane.showMessageDialog(this, "Output data not available yet!");
        }
        else if( label.equals(NA) )
        {
            JOptionPane.showMessageDialog(this, "Output graph not applicable!");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Graph Plot of " + label + ": " + " Sorry, not implemented yet!" );
        }
    }
}
