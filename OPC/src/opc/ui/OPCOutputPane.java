/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import opc.util.UIComponentCreator;
/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCOutputPane extends JPanel {

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
    private final String RISK_NEUTRAL_DENSITY_STRING = "Risk Neutral Density: ";

    private JPanel labelPane;
    private JPanel fieldPane;

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
    private JLabel riskNeutralDensityLabel;

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
    private JFormattedTextField riskNeutralDensityField;


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
        riskNeutralDensityLabel = new JLabel( RISK_NEUTRAL_DENSITY_STRING );

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
        riskNeutralDensityField = UIComponentCreator.createTextField( false );

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
        
        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createTitledBorder("OUTPUT"));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }

    public void addOutputComponent( JLabel label, JFormattedTextField component )
    {
        label.setLabelFor( component );
        labelPane.add( label );
        labelPane.validate();
        fieldPane.add( component );
        fieldPane.validate();
    }

    public void addRiskNeutralDensity()
    {
        riskNeutralDensityLabel.setLabelFor( riskNeutralDensityField );
        labelPane.add( riskNeutralDensityLabel );
        labelPane.validate();
        fieldPane.add( riskNeutralDensityField );
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
    }

    public void removeTheta()
    {
        labelPane.remove( thetaLabel );
        labelPane.validate();
        fieldPane.remove( thetaField );
        fieldPane.validate();
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

    public void setRiskNeutralDensity( String value )
    {
        riskNeutralDensityField.setValue( value );
    }
    
}
