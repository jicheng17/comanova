/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.mathalgo;
import static java.lang.Math.*;

/**
 *
 * @author user
 */
public class NumericalDifference {

    public static double FirstOrderDifference(double y1, double y_1, double a)
    {
        return (y1 - y_1) / (2 * a);
    }

    public static double SecondOrderDifference(double y1, double y0, double y_1, double a)
    {
        return (y1 - 2 * y0 + y_1) / pow(a, 2);
    }

    public static double ThirdOrderDifference(double y2, double y1, double y0, double y_1, double a)
    {
        return (y2 - 3 * y1 + 3 * y0 - y_1) / pow(a, 3);
    }

    public static double SecondOrderMixedDifference(double z11, double z1_1, double z_11, double z_1_1,
                                                        double a, double b)
    {
        return (z_11 - z1_1 - z_11 + z_1_1) / (4 * a * b);
    }

    public static double ThirdOrderMixedDifference(double z11, double z01, double z_11, double z1_1,
                                                      double z0_1, double z_1_1, double a, double b)
    {
        return (z11 - 2 * z01 + z_11 - z1_1 + 2 * z0_1 - z_1_1) / (2 * pow(a, 2) * b );
    }

}
