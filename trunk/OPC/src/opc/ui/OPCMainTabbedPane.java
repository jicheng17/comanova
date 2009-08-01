/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import javax.swing.JTabbedPane;

/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCMainTabbedPane extends JTabbedPane{

    public static OPCMainTabbedPane mainPane;

    public OPCMainTabbedPane()
    {
        super();
        this.setTabLayoutPolicy( JTabbedPane.SCROLL_TAB_LAYOUT );
    }

    public final static synchronized OPCMainTabbedPane getInstance()
    {
        if( mainPane == null )
        {
            return mainPane = new OPCMainTabbedPane();
        }
        
        return mainPane;
    }
}
