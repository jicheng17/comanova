/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.mathalgo;

/**
 *
 * @author user
 */

import static java.lang.Math.*;

public class Adjustment {

    public static double getAnnualTime(double T, String timeconvention)
    {
        if (timeconvention.equals("Days"))
                T = T/365;
        return T;
    }

    public static double getContinuousRate(double r, String ratetype)
    {
        int m = 0;
        if (ratetype.equals(OPCConstants.COMPOUNDING_FREQUENCY.CONTINUOUSLY))
                m = 0;
        else if (ratetype.equals(OPCConstants.COMPOUNDING_FREQUENCY.ANNUALLY))
                m = 1;
        else if (ratetype.equals(OPCConstants.COMPOUNDING_FREQUENCY.SEMI_ANNUALLY))
                m = 2;
        else if (ratetype.equals(OPCConstants.COMPOUNDING_FREQUENCY.QUARTERLY))
                m = 4;
        else if (ratetype.equals(OPCConstants.COMPOUNDING_FREQUENCY.MONTHLY))
                m = 12;

        if (m != 0)
            r = m*log(1+r/m);

        return r;
    }
}
