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
        double y = abs(x);
        double z;

        double [] a = {0.0352624965998911, 0.700383064443688, 6.37396220353165, 33.912866078383,
                      112.079291497871, 221.213596169931, 220.206867912376};
        double [] b = {0.0883883476483184, 1.75566716318264, 16.064177579207, 86.7807322029461,
                      296.564248779674, 637.333633378831, 793.826512519948, 440.413735824752};
        double [] c = {0.65, 4, 3, 2, 1};

        double A = a[0];
        double B = b[0];
        double C = c[0];
    
        if (y > 37)
        {
            z = 0;
        }
        else
        {
            if (y < 7.07106781186547)
            {
                for (int i = 1; i < a.length; i++)
                {
                    A = A*y + a[i];
                }
                for (int i = 1; i < b.length; i++)
                {
                    B = B*y + b[i];
                }
                z = exp(-y*y/2)*(A/B);
            }
            else
            {
                for (int i = 1; i < c.length; i++)
                {
                    C = c[i]/(y + C);
                }
                C = C + y;
                z = exp(-y*y/2)/(2.506628274631*C);
            }
        }

        if (x > 0)
        {
            z = 1 - z;
        }

        return z;
    }
    /*
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
    }*/
    public static void main(String[] args)
    {
        double p[] = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1,-0.1,-0.2,-0.3,-0.4,-0.5,-0.6,-0.7,-0.8,-0.9,-1};
        for (int i = 0; i < p.length; i++ )
        {
            System.out.println(cdf(p[i]));
        }
    }
}
