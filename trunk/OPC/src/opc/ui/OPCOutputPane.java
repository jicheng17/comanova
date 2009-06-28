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

/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCOutputPane extends JPanel {

    private final String OPTION_VALUE_STRING = "Option Value: ";
    private final String DELTA_STRING = "Delta: ";
    private final String ELASTICITY_STRING = "Elasticity: ";
    private final String GAMMA_STRING = "Gamma: ";
    private final String DGAMMA_DVOL_STRING = "dGammaDvol: ";
    private final String GAMMAP_STRING = "GammaP: ";
    private final String VEGA_STRING = "Vega: ";
    private final String DVEGA_DVOL_STRING = "dVegaDvol: ";
    private final String THETA_STRING = "Theta: ";
    private final String RHO_STRING = "Rho: ";
    private final String DDELTA_DVOL_STRING = "dDeltaDvol: ";
    private final String SPEED_STRING = "Speed: ";
    private final String DELTAX_STRING = "DeltaX: ";

    private final int OUTPUT_FIELD_SIZE = 10;

    private JPanel labelPane;
    private JPanel fieldPane;

    private JLabel optionValueLabel;
    private JLabel deltaLabel;
    private JLabel elasticityLabel;
    private JLabel gammaLabel;
    private JLabel dGammaDvolLabel;
    private JLabel gammaPLabel;
    private JLabel vegaLabel;
    private JLabel dVegaDvolLabel;
    private JLabel thetaLabel;
    private JLabel rhoLabel;
    private JLabel dDeltaDvolLabel;
    private JLabel speedLabel;
    private JLabel deltaXLabel;

    private JFormattedTextField optionValueField;
    private JFormattedTextField deltaField;
    private JFormattedTextField elasticityField;
    private JFormattedTextField gammaField;
    private JFormattedTextField dGammaDvolField;
    private JFormattedTextField gammaPField;
    private JFormattedTextField vegaField;
    private JFormattedTextField dVegaDvolField;
    private JFormattedTextField thetaField;
    private JFormattedTextField rhoField;
    private JFormattedTextField dDeltaDvolField;
    private JFormattedTextField speedField;
    private JFormattedTextField deltaXField;


    public OPCOutputPane()
    {
        super( new BorderLayout() );
        initComponent();
    }

    private void initComponent()
    {
        // init labels
        optionValueLabel = new JLabel( OPTION_VALUE_STRING );
        deltaLabel = new JLabel( DELTA_STRING );
        elasticityLabel = new JLabel( ELASTICITY_STRING );
        gammaLabel = new JLabel( GAMMA_STRING );
        dGammaDvolLabel = new JLabel( DGAMMA_DVOL_STRING );
        gammaPLabel = new JLabel( GAMMAP_STRING );
        vegaLabel = new JLabel( VEGA_STRING );
        dVegaDvolLabel = new JLabel( DVEGA_DVOL_STRING );
        thetaLabel = new JLabel( THETA_STRING );
        rhoLabel = new JLabel( RHO_STRING );
        dDeltaDvolLabel = new JLabel( DDELTA_DVOL_STRING );
        speedLabel = new JLabel( SPEED_STRING );
        deltaXLabel = new JLabel( DELTAX_STRING );

        // init text fields
        optionValueField = createTextField();
        deltaField = createTextField();
        elasticityField = createTextField();
        gammaField = createTextField();
        dGammaDvolField = createTextField();
        gammaPField = createTextField();
        vegaField = createTextField();
        dVegaDvolField = createTextField();
        thetaField = createTextField();
        rhoField = createTextField();
        dDeltaDvolField = createTextField();
        speedField = createTextField();
        deltaXField = createTextField();

        // bond labels to text fields
        optionValueLabel.setLabelFor( optionValueField );
        deltaLabel.setLabelFor( deltaField );
        elasticityLabel.setLabelFor( elasticityField );
        gammaLabel.setLabelFor( gammaField );
        dGammaDvolLabel.setLabelFor( dGammaDvolField );
        gammaPLabel.setLabelFor( gammaPField );
        vegaLabel.setLabelFor( vegaField );
        dVegaDvolLabel.setLabelFor( dVegaDvolField );
        thetaLabel.setLabelFor( thetaField );
        rhoLabel.setLabelFor( rhoField );
        dDeltaDvolLabel.setLabelFor( dDeltaDvolField );
        speedLabel.setLabelFor( speedField );
        deltaXLabel.setLabelFor( deltaXField );

        //Lay out the labels in a panel.
        labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add( optionValueLabel );
        labelPane.add( deltaLabel );
        labelPane.add( elasticityLabel );
        labelPane.add( gammaLabel );
        labelPane.add( dGammaDvolLabel );
        labelPane.add( gammaPLabel );
        labelPane.add( vegaLabel );
        labelPane.add( dVegaDvolLabel );
        labelPane.add( thetaLabel );
        labelPane.add( rhoLabel );
        labelPane.add( dDeltaDvolLabel );
        labelPane.add( speedLabel );
        labelPane.add( deltaXLabel );

        //Layout the text fields in a panel.
        fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add( optionValueField );
        fieldPane.add( deltaField );
        fieldPane.add( elasticityField );
        fieldPane.add( gammaField );
        fieldPane.add( dGammaDvolField );
        fieldPane.add( gammaPField );
        fieldPane.add( vegaField );
        fieldPane.add( dVegaDvolField );
        fieldPane.add( thetaField );
        fieldPane.add( rhoField );
        fieldPane.add( dDeltaDvolField );
        fieldPane.add( speedField );
        fieldPane.add( deltaXField );

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createTitledBorder("OUTPUT"));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }

    private JFormattedTextField createTextField()
    {
        JFormattedTextField field = new JFormattedTextField();
        field.setColumns( OUTPUT_FIELD_SIZE );
        field.setEditable( false );

        return field;
    }

    public void addOutputComponent( JLabel label, JFormattedTextField component )
    {
        label.setLabelFor( component );
        labelPane.add( label );
        fieldPane.add( component );
    }
}
