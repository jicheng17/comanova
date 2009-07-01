/*
 * This class construct the options model types tree by reading in the type input
 */

package opc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCTreeConstructor {

    final static String TREE_ROOT_NAME = "Options Model Types";
    //final static String INPUT_LOCATION = "..\\conf\\Options Model Types.config";
    final static String INPUT_LOCATION = "C:\\conf\\Options Model Types.config";

    public static OPCTreeNode constructTree() 
    {
        OPCTreeNode root = new OPCTreeNode( TREE_ROOT_NAME );

        try
        {
            BufferedReader br = new BufferedReader( new FileReader(INPUT_LOCATION) );

            String input = "";
            StringTokenizer st;

            while( (input=br.readLine()) != null )
            {
                int colonPos = input.indexOf( ":" );
                String nodeName = input.substring( 0, colonPos );
                String child = input.substring( colonPos+1 );
                st = new StringTokenizer( child, "," );

                OPCTreeNode temp = new OPCTreeNode( nodeName );

                OPCTreeNode result = root.findChildNode(temp);
                if( result == null ) // cannot find, append as a child of root
                {
                    System.out.println( "Cannot find node: " + nodeName );
                    while( st.hasMoreTokens() )
                    {
                        String token = st.nextToken();
                        System.out.println( "Add child node: " + token + " to " + nodeName );
                        temp.addChildNode( new OPCTreeNode(token) );
                    }

                    System.out.println( "Append " + nodeName + " to root" );
                    root.addChildNode( temp );
                }
                else
                {
                    System.out.println( "Found node: " + nodeName );
                    while( st.hasMoreTokens() )
                    {
                        String token = st.nextToken();
                        System.out.println( "Add child node: " + token + " to " + nodeName );
                        result.addChildNode( new OPCTreeNode(token) );
                    }
                }
            }
        }
        catch( IOException e )
        {
            System.err.println( "Error when reading input file: " + INPUT_LOCATION + e );
        }
        catch( Exception e )
        {
            System.err.println( "Encountered an unexpected exception: " + e );
        }

        return root;
    }
}
