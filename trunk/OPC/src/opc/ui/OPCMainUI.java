/*
 * This is the main UI class for Options Pricing Calculator
 */

package opc.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
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

    final static int MAIN_FRAME_WIDTH = 1500;
    final static int MAIN_FRAME_HEIGHT = 800;
    final static String MAIN_UI_TITLE = "Options Pricing Calculator";
    final static String TREE_ROOT_NAME = "Options Model Types";
    final static String UI_CLASS_MAP_FILE_LOCATION = "C:\\conf\\OPC UI Class Map.config";
    final static String CALCULATOR_CLASS_MAP_FILE_LOCATION = "C:\\conf\\OPC Calculator Class Map.config";

    public static HashMap<String, String> treeNodeNameCalculatorClassMap;
    public static HashMap<String,String> treeNodeNameUIClassMap;
    private static JFrame mainFrame;
    private static JSplitPane splitPane;
    private static JTree optionModelTree;
    private static JScrollPane treeView;
    private static JPanel inputPanel;
    private static JLabel inputLabel;

    private static void initComponents()
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

        // init input panel on the right
        inputPanel = new JPanel();
        inputLabel = new JLabel();
    }
    
    private static void constructOptionModelTree()
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

    private static MouseListener getTreeMouseListener()
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
                            OPCTabbedPane rightPane = new OPCTabbedPane();
                            try
                            {
                                Class<? extends OPCTabbedPane> tabbedPane = Class.forName(nodeClass).asSubclass(OPCTabbedPane.class);
                                rightPane = tabbedPane.newInstance();
                            }
                            catch (Exception cnfe )
                            {
                                cnfe.printStackTrace();
                            }
                            rightPane.setMainPanelTitle( nodeName );
                            rightPane.display();
                            splitPane.setRightComponent( rightPane );
                        }
                    }
                }
            }
        };
        return ml;
    }
    
    private static DefaultMutableTreeNode processHierarchy( OPCTreeNode root )
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

    public static void show()
    {
        initComponents();
        
        splitPane.setOneTouchExpandable(true);
        splitPane.setLeftComponent(treeView);
        inputPanel.add( inputLabel );
        splitPane.setRightComponent(inputPanel);
        mainFrame.add( splitPane, BorderLayout.CENTER );
        mainFrame.setDefaultLookAndFeelDecorated(true);
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        //mainFrame.setResizable( false );
        mainFrame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        OPCMainUI.show();
    }

}