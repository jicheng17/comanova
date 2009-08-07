/*
 * This is the main UI class for Options Pricing Calculator
 */

package opc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import opc.util.OPCTreeConstructor;
import opc.util.OPCTreeNode;
import opc.util.OPCTreeNodeNameClassMapConstructor;

/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCMainUI {

    public static OPCMainUI mainUI;

    final int MAIN_FRAME_WIDTH = 900;
    final int MAIN_FRAME_HEIGHT = 500;
    final String MAIN_UI_TITLE = "Options Pricing Calculator";
    final String TREE_ROOT_NAME = "Options Model Types";
    final String UI_CLASS_MAP_FILE_LOCATION = "C:\\conf\\OPC UI Class Map.config";
    final String CALCULATOR_CLASS_MAP_FILE_LOCATION = "C:\\conf\\OPC Calculator Class Map.config";

    public static HashMap<String, String> treeNodeNameCalculatorClassMap;
    public static HashMap<String,String> treeNodeNameUIClassMap;
    public JFrame mainFrame;
    private JSplitPane splitPane;
    private JTree optionModelTree;
    private JScrollPane treeView;
    private OPCMainTabbedPane rightPane;

    public OPCMainUI()
    {
    }

    public final static synchronized OPCMainUI getInstance()
    {
        if( mainUI == null )
        {
            return mainUI = new OPCMainUI();
        }

        return mainUI;
    }

    private void initComponents()
    {
        treeNodeNameCalculatorClassMap = 
            OPCTreeNodeNameClassMapConstructor.getTreeNodeNameClassMap( CALCULATOR_CLASS_MAP_FILE_LOCATION );
        treeNodeNameUIClassMap = 
            OPCTreeNodeNameClassMapConstructor.getTreeNodeNameClassMap( UI_CLASS_MAP_FILE_LOCATION );
        
        // init main frame
        mainFrame = new JFrame( MAIN_UI_TITLE );
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // init option model tree
        constructOptionModelTree();

        // add tree to ScrollPane on the left
        treeView = new JScrollPane( optionModelTree );
        treeView.setBorder(BorderFactory.createTitledBorder("Types"));

        // init main tabbed pane on the right
        rightPane = OPCMainTabbedPane.getInstance();
    }
    
    private void constructOptionModelTree()
    {
        OPCTreeNode opcRoot = OPCTreeConstructor.constructTree();
        
        DefaultMutableTreeNode root = processHierarchy( opcRoot );
        optionModelTree = new JTree( root );
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setOpenIcon(null);
        renderer.setClosedIcon(null);
        renderer.setLeafIcon(null);
        optionModelTree.setCellRenderer(renderer);
        MouseListener ml = getTreeMouseListener();
        optionModelTree.addMouseListener(ml);
    }

    private MouseListener getTreeMouseListener()
    {
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = optionModelTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = optionModelTree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 2) {
                        DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode)selPath.getLastPathComponent();
                        if( nodeSelected.isLeaf() )
                        {
                            String nodeName = (String)nodeSelected.getUserObject();
                            String nodeClass = treeNodeNameUIClassMap.get(nodeName);
                            OPCBasePane newPane = new OPCBasePane();
                            try
                            {
                                Class<? extends OPCBasePane> tabbedPane = Class.forName(nodeClass).asSubclass(OPCBasePane.class);
                                newPane = tabbedPane.newInstance();
                            }
                            catch (Exception cnfe )
                            {
                                cnfe.printStackTrace();
                            }
                            newPane.setMainPanelTitle( nodeName );
                            newPane.display();
                            rightPane.addTab( nodeName, newPane );
                            rightPane.setSelectedComponent( newPane );
                        }
                    }
                }
            }
        };
        return ml;
    }
    
    private DefaultMutableTreeNode processHierarchy( OPCTreeNode root )
    {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode( root.getNodeName() );
        if( root.isLeaf() )
            return result;

        ArrayList<OPCTreeNode> childList = root.getChildNodes();
        for( int i = 0; i < childList.size(); i++ )
        {
            result.add( processHierarchy(childList.get(i)) );
        }

        return result;
    }

    private void positionWindow()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        
        int x = (screenSize.width - mainFrame.getWidth()) / 2;
        int y = (screenSize.height - mainFrame.getHeight()) / 2;

        //Set the new frame location
        mainFrame.setLocation(x, y); 
    }
    
    public void show()
    {
        initComponents();
        
        splitPane.setOneTouchExpandable(true);
        splitPane.setLeftComponent(treeView);
        splitPane.setRightComponent(rightPane);
        mainFrame.add( splitPane, BorderLayout.CENTER );
        try
        {
            // Set System L&F
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.err.println( "error setting look and feel" + e );
        }
        mainFrame.setDefaultLookAndFeelDecorated(true);
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        positionWindow();
        mainFrame.setResizable( false );
        mainFrame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        OPCMainUI mainUI = OPCMainUI.getInstance();
        mainUI.show();
    }

}
