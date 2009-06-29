/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCTabbedPane extends JTabbedPane implements ActionListener {


    protected OPCInputPane inputPanel;        // input panel for user input
    protected OPCOutputPane outputPanel;      // output panel for numerical outputs
    protected JComponent leftPanel;           // leftPanel = inputPanel + outputPanel
    protected JComponent rightPanel;          // graph panel for Option Value graph view
    protected JComponent mainPanel;           // the first tab, which combines leftPanel and rightPanel

    protected JButton calculateBtn;           // the button to trigger calculation

    //private JComponent valueGraphTab;       // the tab for optionValue graph view
    protected JComponent deltaGraphTab;       // the tab for delta graph view
    protected JComponent elasticityGraphTab;  // the tab for elasticity graph view
    protected JComponent gammaGraphTab;       // the tab for gamma graph view
    protected JComponent dGamaDvolGraphTab;   // the tab for dGammaDvol graph view
    protected JComponent gammaPGraphTab;      // the tab for gammaP graph view
    protected JComponent vegaGraphTab;        // the tab for vega graph view
    protected JComponent dVegalDvolGraphTab;  // the tab for dVegalDvol graph view
    protected JComponent thetaGraphTab;       // the tab for theta graph view
    protected JComponent rhoGraphTab;         // the tab for rho graph view
    protected JComponent dDeltaDvolGraphTab;  // the tab for dDeltaDvol graph view
    protected JComponent speedGraphTab;       // the tab for speed graph view
    protected JComponent deltaXGraphTab;      // the tab for deltaX graph view

    protected String mainPanelTitle;

    //private final String VALUE_TAB_TITLE = "Option Value";
    protected final String CALCULATE_BUTTON_LABEL = "Calculate";
    protected final String DELTA_TAB_TITLE = "Delta";
    protected final String ELASTICITY_TAB_TITLE = "Elasticity";
    protected final String GAMMA_TAB_TITLE = "Gamma";
    protected final String DGAMMA_DVOL_TAB_TITLE = "dGammaDvol";
    protected final String GAMMAP_TAB_TITLE = "GammaP";
    protected final String VEGA_TAB_TITLE = "Vega";
    protected final String DVEGA_DVOL_TAB_TITLE = "dVegaDvol";
    protected final String THETA_TAB_TITLE = "Theta";
    protected final String RHO_TAB_TITLE = "Rho";
    protected final String DDELTA_DVOL_TAB_TITLE = "dDeltaDvol";
    protected final String SPEED_TAB_TITLE = "Speed";
    protected final String DELTAX_TAB_TITLE = "DeltaX";
    private final int INPUT_FIELD_SIZE = 10;


    public OPCTabbedPane()
    {
        super();
        //initComponent();
    }

    public void display()
    {
        initComponent();
    }

    protected void initComponent()
    {
        calculateBtn = new JButton( CALCULATE_BUTTON_LABEL );
        calculateBtn.addActionListener( this );
        
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        inputPanel = new OPCInputPane();
        outputPanel = new OPCOutputPane();
        JPanel btnPanel = new JPanel();
        btnPanel.add( calculateBtn );
        leftPanel.setLayout( new BorderLayout() );
        leftPanel.add( inputPanel, BorderLayout.NORTH );
        leftPanel.add( btnPanel, BorderLayout.CENTER );
        leftPanel.add( outputPanel, BorderLayout.SOUTH );
        
        rightPanel = makeTextPanel( "To be implemented" );

        mainPanel.setLayout( new BorderLayout() );
        mainPanel.add( leftPanel, BorderLayout.WEST );
        mainPanel.add( new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER );
        mainPanel.add( rightPanel, BorderLayout.EAST );

        deltaGraphTab = makeTextPanel( "To be implemented" );
        elasticityGraphTab = makeTextPanel( "To be implemented" );
        gammaGraphTab = makeTextPanel( "To be implemented" );
        dGamaDvolGraphTab = makeTextPanel( "To be implemented" );
        gammaPGraphTab = makeTextPanel( "To be implemented" );
        vegaGraphTab = makeTextPanel( "To be implemented" );
        dVegalDvolGraphTab = makeTextPanel( "To be implemented" );
        thetaGraphTab = makeTextPanel( "To be implemented" );
        rhoGraphTab = makeTextPanel( "To be implemented" );
        dDeltaDvolGraphTab = makeTextPanel( "To be implemented" );
        speedGraphTab = makeTextPanel( "To be implemented" );
        deltaXGraphTab = makeTextPanel( "To be implemented" );

        this.addTab( mainPanelTitle, null, mainPanel, "Main Panel" );
        this.addTab( DELTA_TAB_TITLE, null, deltaGraphTab, "Delta Graph Panel");
        this.addTab( ELASTICITY_TAB_TITLE, null, elasticityGraphTab, "Elasticity Graph Panel");
        this.addTab( GAMMA_TAB_TITLE, null, gammaGraphTab, "Gamma Graph Panel" );
        this.addTab( DGAMMA_DVOL_TAB_TITLE, null, dGamaDvolGraphTab, "dGammaDvol Graph Panel" );
        this.addTab( GAMMAP_TAB_TITLE, null, gammaPGraphTab, "GammaP Graph Panel" );
        this.addTab( VEGA_TAB_TITLE, null, vegaGraphTab, "Vega Graph Panel" );
        this.addTab( DVEGA_DVOL_TAB_TITLE, null, dVegalDvolGraphTab, "dVegaDvol Graph Panel" );
        this.addTab( THETA_TAB_TITLE, null, thetaGraphTab, "Theta Graph Panel" );
        this.addTab( RHO_TAB_TITLE, null, rhoGraphTab, "Rho Graph Panel" );
        this.addTab( DDELTA_DVOL_TAB_TITLE, null, dDeltaDvolGraphTab, "dDeltaDvol Graph Panel" );
        this.addTab( SPEED_TAB_TITLE, null, speedGraphTab, "Speed Graph Panel" );
        this.addTab( DELTAX_TAB_TITLE, null, deltaXGraphTab, "DeltaX Graph Panel" );

        //this.setMnemonicAt(3, KeyEvent.VK_4);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    public void setMainPanelTitle( String newTitle )
    {
        this.mainPanelTitle = newTitle;
    }

    protected JFormattedTextField createTextField()
    {
        JFormattedTextField field = new JFormattedTextField();
        field.setValue( "0.0" );
        field.setColumns( INPUT_FIELD_SIZE );

        return field;
    }
    
    public void actionPerformed( ActionEvent e )
    {
        // Step 1: validate inputs TODO
        // Step 2: send inputs to backend calculator
        // Step 3: collect output from backend and refresh output panel
    }
}
