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

public class NormTwoDim {
    private static double PI = 3.141592653589793238462643;

    public static double cdf(double X, double y, double rho)
    {
        double [][] W = {{0.17132449237917,  0.0471753363865118, 0.0176140071391521},
                         {0.360761573048138, 0.106939325995318,  0.0406014298003869},
                         {0.46791393457269,  0.160078328543346,  0.0626720483341091},
                         {0,                 0.203167426723066,  0.0832767415767048},
                         {0,                 0.233492536538355,  0.10193011981724},
                         {0,                 0.249147045813403,  0.118194531961518},
                         {0,                 0,                  0.131688638449177},
                         {0,                 0,                  0.142096109318382},
                         {0,                 0,                  0.149172986472604},
                         {0,                 0,                  0.152753387130726}};

        double [][] XX = {{-0.932469514203152, -0.981560634246719, -0.993128599185095},
                          {-0.661209386466265, -0.904117256370475, -0.963971927277914},
                          {-0.238619186083197, -0.769902674194305, -0.912234428251326},
                          {0,                  -0.587317954286617, -0.839116971822219},
                          {0,                  -0.36783149899818,  -0.746331906460151},
                          {0,                  -0.125233408511469, -0.636053680726515},
                          {0,                  0,                  -0.510867001950827},
                          {0,                  0,                  -0.37370608871542},
                          {0,                  0,                  -0.227785851141645},
                          {0,                  0,                  -0.0765265211334973}};

        int NG = 0;
        int LG = 0;

        if (abs(rho) < 0.3)
        {
            NG = 1;
            LG = 3;
        }
        else if (abs(rho) < 0.75)
        {
            NG = 2;
            LG = 6;
        }
        else
        {
            NG = 3;
            LG = 10;
        }

        double h = - X;
        double k = -y;
        double hk = h * k;
        double BVN = 0;

        if (abs(rho) < 0.925)
        {
            if (abs(rho) > 0)
            {
               double hs = (h*h + k*k) / 2;
               double asr = asin(rho);
               for (int i = 1; i <= LG; i++)
               {
                   for (int ISs = -1; ISs <= 1; ISs += 2)
                   {
                        double sn = sin(asr * (ISs * XX[i-1][NG-1] + 1) / 2);
                        BVN = BVN + W[i-1][NG-1] * exp((sn*hk-hs)/(1-sn*sn));
                   }
               }
               BVN = BVN * asr /(4*PI);
            }
            BVN = BVN + NormOneDim.cdf(-h) * NormOneDim.cdf(-k);
        }
        else
        {
            if (rho < 0)
            {
                k = -k;
                hk = -hk;
            }
            if (abs(rho) < 1)
            {
                double Ass = (1-rho)*(1+rho);
                double A = sqrt(Ass);
                double bs = pow(h-k,2);
                double c = (4-hk)/8;
                double d = (12-hk)/16;
                double asr = -(bs/Ass + hk)/2;
                if (asr > -100)
                {
                   BVN = A*exp(asr)*(1-c*(bs-Ass)*(1-d*bs/5)/3 + c*d*Ass*Ass/5);
                   if (-hk < 100)
                   {
                      double b = sqrt(bs);
                      BVN = BVN - exp(-hk/2)*sqrt(2*PI)*NormOneDim.cdf(-b/A)*b*(1-c*bs*(1-d*bs/5)/3);
                   }
                }
                A = A/2;
                for (int i = 1; i <= LG; i++)
                {
                   for (int ISs = -1; ISs <= 1; ISs += 2)
                   {
                      double xs = pow(A* (ISs * XX[i-1][NG-1] + 1),2);
                      double rs = sqrt(1-xs);
                      asr = -(bs/xs + hk)/2;
                      if (asr > -100)
                      {
                         BVN = BVN + A*W[i-1][NG-1]*exp(asr)*(exp(-hk*(1-rs)/(2*(1+rs)))/rs-(1+c*xs*(1+d*xs)));
                      }
                   }
                }
                BVN = - BVN/(2*PI);
            }
            if (rho > 0)
            {
               BVN = BVN + NormOneDim.cdf(-max(h,k));
            }
            else
            {
               BVN = - BVN;
               if (k > h)
               {
                   BVN = BVN + NormOneDim.cdf(k) - NormOneDim.cdf(h);
               }
            }
        }
        return BVN;
    }

    public static void main(String[] args)
    {
        double [] a = {0,0,0,0,0,0,0,0,0,-0.5,-0.5,-0.5,-0.5,-0.5,-0.5,-0.5,-0.5,-0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0,0.00001};
        double [] b = {0,0,0,-0.5,-0.5,-0.5,0.5,0.5,0.5,0,0,0,-0.5,-0.5,-0.5,0.5,0.5,0.5,0,0,0,-0.5,-0.5,-0.5,0.5,0.5,0.5,-0.9999999,-0.9999999};
        double [] rho = {0,-0.5,0.5,0,-0.5,0.5,0,-0.5,0.5,0,-0.5,0.5,0,-0.5,0.5,0,-0.5,0.5,0,-0.5,0.5,0,-0.5,0.5,0,-0.5,0.5,-0.9999999,-0.9999999};
        for (int i = 0; i < a.length; i++ )
        {
            System.out.println(cdf(a[i],b[i],rho[i]));
        }
    }
}
