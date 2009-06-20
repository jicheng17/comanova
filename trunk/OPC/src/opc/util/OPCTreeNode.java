/*
 * OPC model type tree node
 */

package opc.util;

import java.util.ArrayList;
/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCTreeNode {

    private String nodeName;
    private ArrayList<OPCTreeNode> childList;

    public OPCTreeNode()
    {
        this.childList = new ArrayList<OPCTreeNode>();
    }

    public OPCTreeNode( String nodeName )
    {
        this.nodeName = nodeName;
        this.childList = new ArrayList<OPCTreeNode>();
    }

    public void addChildNode( OPCTreeNode child )
    {
        this.childList.add( child );
    }

    public String getNodeName()
    {
        return this.nodeName;
    }

    public boolean isLeaf()
    {
        return this.childList.size() == 0;
    }

    public ArrayList<OPCTreeNode> getChildNodes()
    {
        return this.childList;
    }
    
    public OPCTreeNode findChildNode( OPCTreeNode toFind )
    {
        if( isLeaf() ) // no child node, return null
            return null;

        OPCTreeNode child;
        for( int i = 0; i < childList.size(); i++ )
        {
            child = childList.get(i);
            if( child.getNodeName().equals(toFind.getNodeName()) )
            {
                return child;
            }
            OPCTreeNode result = child.findChildNode(toFind);
            if( result != null )
            {
                return result;
            }
        }

        return null;
    }
}
