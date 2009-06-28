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

public class NormOneDim {
    private static double PI = 3.141592653589793238462643;

    public static double pdf(double x)
    {
        return 1.0/sqrt(2.0*PI)*exp(-0.5*x*x);
    }

    public static double cdf(double x)
    {
        if (x > 6.0)
        {
            return 1.0;
        }
        else if (x < -6.0)
        {
            return 0.0;
        }

        double b1 =  0.319381530;
        double b2 = -0.356563782;
        double b3 =  1.781477937;
        double b4 = -1.821255978;
        double b5 =  1.330274429;
        double p = 0.2316419;

        double a = abs(x);
        double t = 1/(1+a*p);
        double b = 1.0/sqrt(2.0*PI)*exp(-x*x/2);
        double n = ((((b5*t+b4)*t+b3)*t+b2)*t+b1)*t;
        n = 1.0-b*n;
        if (x < 0.0)
        {
           n = 1.0 - n;
        }
        return n;
    }

}
