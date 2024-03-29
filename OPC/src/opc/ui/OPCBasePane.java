/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;


import opc.calculator.OptionsCalculatorInterface;

/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCBasePane extends JPanel implements ActionListener {


    protected OPCInputPane inputPanel;        // input panel for user input
    protected OPCOutputPane outputPanel;      // output panel for numerical outputs
    protected JComponent leftPanel;           // leftPanel = inputPanel + outputPanel
    protected JComponent rightPanel;          // graph panel for Option Value graph view
    protected JComponent btnPanel;
    //protected JComponent mainPanel;           // the first tab, which combines leftPanel and rightPanel

    protected JButton calculateBtn;           // the button to trigger calculation

    /*private JComponent valueGraphTab;       // the tab for optionValue graph view
    protected JComponent deltaGraphTab;       // the tab for delta graph view
    protected JComponent deltaXGraphTab;      // the tab for deltaX graph view
    protected JComponent dDeltaDvolGraphTab;  // the tab for dDeltaDvol graph view
    protected JComponent gammaGraphTab;       // the tab for gamma graph view
    protected JComponent gammaXGraphTab;      // the tab for gammaX graph view
    protected JComponent gammaPGraphTab;      // the tab for gammaP graph view
    protected JComponent dGamaDvolGraphTab;   // the tab for dGammaDvol graph view
    protected JComponent vegaGraphTab;        // the tab for vega graph view
    protected JComponent vegaPGraphTab;       // the tab for vegaP graph view
    protected JComponent dVegalDvolGraphTab;  // the tab for dVegalDvol graph view
    protected JComponent rhoGraphTab;         // the tab for rho graph view
    protected JComponent futuresRhoGraphTab;  // the tab for futuresRho graph view
    protected JComponent elasticityGraphTab;  // the tab for elasticity graph view
    protected JComponent thetaGraphTab;       // the tab for theta graph view
    protected JComponent speedGraphTab;       // the tab for speed graph view
    protected JComponent carryGraphTab;       // the tab for carry graph view*/
    

    protected String mainPanelTitle;

    //private final String VALUE_TAB_TITLE = "Option Value";
    protected final String CALCULATE_BUTTON_LABEL = "Calculate";
    /*protected final String DELTA_TAB_TITLE = "Delta";
    protected final String DELTAX_TAB_TITLE = "DeltaX";
    protected final String DDELTA_DVOL_TAB_TITLE = "dDeltaDvol";
    protected final String GAMMA_TAB_TITLE = "Gamma";
    protected final String GAMMAX_TAB_TITLE = "GammaX";
    protected final String GAMMAP_TAB_TITLE = "GammaP";
    protected final String DGAMMA_DVOL_TAB_TITLE = "dGammaDvol";
    protected final String VEGA_TAB_TITLE = "Vega";
    protected final String VEGAP_TAB_TITLE = "VegaP";
    protected final String DVEGA_DVOL_TAB_TITLE = "dVegaDvol";
    protected final String RHO_TAB_TITLE = "Rho";
    protected final String FUTURES_RHO_TAB_TITLE = "FuturesRho";
    protected final String ELASTICITY_TAB_TITLE = "Elasticity";
    protected final String THETA_TAB_TITLE = "Theta";
    protected final String SPEED_TAB_TITLE = "Speed";
    protected final String CARRY_TAB_TITLE = "Carry";
    protected final String RISK_NEUTRAL_DENSITY_TITLE = "Risk Neutral Density";*/
    
    public OPCBasePane()
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
        
        //mainPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        inputPanel = new OPCInputPane();
        outputPanel = new OPCOutputPane();

        btnPanel = new JPanel();
        btnPanel.setLayout( new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS) );
        btnPanel.add(Box.createRigidArea(new Dimension(0,180)));
        btnPanel.add( calculateBtn );
        btnPanel.add(Box.createRigidArea(new Dimension(0,250)));

        /*leftPanel.setLayout( new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS) );
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add( inputPanel );
        leftPanel.add(Box.createRigidArea(new Dimension(0,130)));*/
        leftPanel.setLayout( new BorderLayout() );
        leftPanel.add( Box.createRigidArea(new Dimension(0,20)), BorderLayout.NORTH );
        leftPanel.add( inputPanel, BorderLayout.CENTER );
        leftPanel.add( Box.createRigidArea(new Dimension(0,130)), BorderLayout.SOUTH );

        JPanel tempPanel = new JPanel();
        tempPanel.setLayout( new BoxLayout(tempPanel, BoxLayout.LINE_AXIS) );
        tempPanel.add( leftPanel );
        tempPanel.add(Box.createRigidArea(new Dimension(5,0)));
        tempPanel.add( btnPanel );
        tempPanel.add(Box.createRigidArea(new Dimension(5,0)));
        tempPanel.add( outputPanel );

        //rightPanel = makeTextPanel( "To be implemented" );*/

        this.setLayout( new BorderLayout() );
        this.add( new JPanel(), BorderLayout.WEST );
        //this.add( new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER );
        this.add( tempPanel, BorderLayout.CENTER );
        this.add( new JPanel(), BorderLayout.EAST );

        /*deltaGraphTab = makeTextPanel( "To be implemented" );
        deltaXGraphTab = makeTextPanel( "To be implemented" );
        dDeltaDvolGraphTab = makeTextPanel( "To be implemented" );
        gammaGraphTab = makeTextPanel( "To be implemented" );
        gammaXGraphTab = makeTextPanel( "To be implemented" );
        gammaPGraphTab = makeTextPanel( "To be implemented" );
        dGamaDvolGraphTab = makeTextPanel( "To be implemented" );
        vegaGraphTab = makeTextPanel( "To be implemented" );
        vegaPGraphTab = makeTextPanel( "To be implemented" );
        dVegalDvolGraphTab = makeTextPanel( "To be implemented" );
        rhoGraphTab = makeTextPanel( "To be implemented" );
        futuresRhoGraphTab = makeTextPanel( "To be implemented" );
        elasticityGraphTab = makeTextPanel( "To be implemented" );
        thetaGraphTab = makeTextPanel( "To be implemented" );
        speedGraphTab = makeTextPanel( "To be implemented" );
        carryGraphTab = makeTextPanel( "To be implemented" );*/
        
        //this.addTab( mainPanelTitle, null, mainPanel, "Main Panel" );
        /*this.addTab( DELTA_TAB_TITLE, null, deltaGraphTab, "Delta Graph Panel");
        this.addTab( DELTAX_TAB_TITLE, null, deltaXGraphTab, "DeltaX Graph Panel");
        this.addTab( DDELTA_DVOL_TAB_TITLE, null, dDeltaDvolGraphTab, "dDeltaDvol Graph Panel" );
        this.addTab( GAMMA_TAB_TITLE, null, gammaGraphTab, "Gamma Graph Panel" );
        this.addTab( GAMMAX_TAB_TITLE, null, gammaXGraphTab, "GammaX Graph Panel" );
        this.addTab( GAMMAP_TAB_TITLE, null, gammaPGraphTab, "GammaP Graph Panel" );
        this.addTab( DGAMMA_DVOL_TAB_TITLE, null, dGamaDvolGraphTab, "dGammaDvol Graph Panel" );
        this.addTab( VEGA_TAB_TITLE, null, vegaGraphTab, "Vega Graph Panel" );
        this.addTab( VEGAP_TAB_TITLE, null, vegaPGraphTab, "VegaP Graph Panel" );
        this.addTab( DVEGA_DVOL_TAB_TITLE, null, dVegalDvolGraphTab, "dVegaDvol Graph Panel" );
        this.addTab( RHO_TAB_TITLE, null, rhoGraphTab, "Rho Graph Panel" );
        this.addTab( FUTURES_RHO_TAB_TITLE, null, futuresRhoGraphTab, "FuturesRho Graph Panel" );
        this.addTab( ELASTICITY_TAB_TITLE, null, elasticityGraphTab, "Elasticity Graph Panel");
        this.addTab( THETA_TAB_TITLE, null, thetaGraphTab, "Theta Graph Panel" );
        this.addTab( SPEED_TAB_TITLE, null, speedGraphTab, "Speed Graph Panel" );
        this.addTab( CARRY_TAB_TITLE, null, carryGraphTab, "Carry Graph Panel" );*/
        
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
    
    public void actionPerformed( ActionEvent e )
    {
        // Handling calculate button event
        // Step 1: validate inputs TODO
        // Step 2: send inputs to backend calculator
        // Step 3: collect output from backend and refresh output panel
        ArrayList<String> errorList = validateInputs();
        
        if( errorList.size() > 0 )
        {
            // TODO pop up a dialogue box to show error message
            return;
        }

        OptionsCalculatorInterface calculator = getCalculatorInstance();
        if( calculator == null ) // class cast exception possibly 
        {
            System.out.println( "Error in initializing calculator instance for " + mainPanelTitle );
            return;
        }
        HashMap<String,String> inputMap = constructInputMap();
        calculator.sendInputs( inputMap );
        calculator.calculate();
        HashMap<String,String> outputMap = calculator.getOutputs();
        
        refreshOutput( outputMap );
    }
    
    public ArrayList<String> validateInputs()
    {
        // TODO
        ArrayList<String> errorList = new ArrayList<String>();
        return errorList;
    }
    
    public OptionsCalculatorInterface getCalculatorInstance()
    {
        String className = OPCMainUI.treeNodeNameCalculatorClassMap.get( mainPanelTitle );
        OptionsCalculatorInterface result = null;
        try
        {
            Class<? extends OptionsCalculatorInterface> factory = Class.forName(className).asSubclass(OptionsCalculatorInterface.class);
            result = factory.newInstance();
        }
        catch (Exception cnfe )
        {
            cnfe.printStackTrace();
        }    
        
        return result;
    }
    
    public HashMap<String,String> constructInputMap()
    {
        HashMap<String,String> inputMap = new HashMap<String,String>();
        
        String strikePrice = inputPanel.getStrikePrice();
        String timeToMaturity = inputPanel.getTimeToMaturity();
        String riskFreeRate = inputPanel.getRiskFreeRate();
        String volatility = inputPanel.getVolatility();
        String longShort = inputPanel.getLongShort();
        String timeUnit = inputPanel.getTimeUnit();
        String riskFreeCompounding = inputPanel.getRiskFreeCompounding();
        
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.STRIKE_PRICE, strikePrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.LOWER_STRIKE_PRICE, strikePrice.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.TIME_TO_MATURITY, timeToMaturity.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.RISK_FREE_RATE, riskFreeRate.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.VOLATILITY, volatility.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.LONG_SHORT, longShort.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.TIME_UNIT, timeUnit.trim() );
        inputMap.put( OptionsCalculatorInterface.GUI_INPUT.RISK_FREE_COMPOUNDING, riskFreeCompounding.trim() );
        
        return inputMap;
    }
    
    public void refreshOutput( HashMap<String,String> outputMap )
    {
        outputPanel.setOptionValue( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.OPTION_VALUE) );
        outputPanel.setDelta( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.DELTA) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.DELTA) : OPCOutputPane.NA );
        outputPanel.setDeltaX( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.DELTAX) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.DELTAX) : OPCOutputPane.NA );
        outputPanel.setDDeltaDvol( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.D_DELTA_DVOL) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.D_DELTA_DVOL) : OPCOutputPane.NA );
        outputPanel.setGamma( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA) : OPCOutputPane.NA );
        outputPanel.setVega( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.VEGA) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.VEGA) : OPCOutputPane.NA );
        outputPanel.setGammaX( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA_X) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA_X) : OPCOutputPane.NA );
        outputPanel.setGammaP( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA_P) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.GAMMA_P) : OPCOutputPane.NA );
        outputPanel.setDGammaDvol( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.D_GAMMA_DVOL) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.D_GAMMA_DVOL) : OPCOutputPane.NA );
        outputPanel.setVegaP( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.VEGA_P) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.VEGA_P) : OPCOutputPane.NA );
        outputPanel.setDVegaDvol( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.D_VEGA_DVOL) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.D_VEGA_DVOL) : OPCOutputPane.NA );
        outputPanel.setRho( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.RHO) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.RHO) : OPCOutputPane.NA );
        outputPanel.setFuturesRho( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.FUTURESRHO) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.FUTURESRHO) : OPCOutputPane.NA );
        outputPanel.setElasticity( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.ELASTICITY) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.ELASTICITY) : OPCOutputPane.NA );
        outputPanel.setTheta( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.THETA) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.THETA) : OPCOutputPane.NA );
        outputPanel.setSpeed( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.SPEED) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.SPEED) : OPCOutputPane.NA );
        outputPanel.setCarry( outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.CARRY) != null ? 
                outputMap.get(OptionsCalculatorInterface.GUI_OUTPUT.CARRY) : OPCOutputPane.NA );
    }
}
