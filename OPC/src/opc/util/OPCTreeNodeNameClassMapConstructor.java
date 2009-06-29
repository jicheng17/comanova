/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.util;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
/**
 *
 * @author ZHAO QINGHUA
 */
public class OPCTreeNodeNameClassMapConstructor {

    public static HashMap<String,String> getTreeNodeNameClassMap( String inputLocation )
    {
        HashMap<String,String> nameClassMap = new HashMap<String,String>();

        try
        {
            BufferedReader br = new BufferedReader( new FileReader(inputLocation) );

            String input = "";
            while( (input=br.readLine()) != null )
            {
                int colonPos = input.indexOf( ":" );
                String nodeName = input.substring( 0, colonPos );
                String nodeClass = input.substring( colonPos+1 );
                nameClassMap.put( nodeName, nodeClass );
            }
        }
        catch( IOException e )
        {
            System.err.println( "Error when reading input file: " + inputLocation + e );
        }
        catch( Exception e )
        {
            System.err.println( "Encountered an unexpected exception: " + e );
        }
        
        return nameClassMap;
    }
}
