import java.io.FileWriter;
import java.io.IOException;

public class Laba2{

    public static void main(String[] args) {
        double Re = 1;
        double Pr = 400;
        double Nu = 1 + (1/2) * (0.55 * Math.pow(Re,0.5) * Math.pow(Pr, 1/3));
        double D = 4 * Math.pow(10,-6) , d = 0.001;
        double bi = Nu * D/d;

        double L = 0.1;
        double Cs = 10;
// заданиe условий
        double hx = L/5; //шаг по координате
        double Tmax = 10000;
        double tau = 10;//шаг по времени
        int M;
        double mm =L/hx; // колличество шагов по координате
        M = (int) mm;
        int N;
        double nn = Tmax / tau ; // колличество шагов по времени
                N= (int) nn;
        double[][] Cnj = new double[N][M];
        for (int i = 0; i < M ; i++) {
                Cnj[0][i] = 205;
        }

        double [] alpha = new double[M];
        double [] beta = new double[M];
        double [] a = new double[M];
        double [] b = new double[M];
        double [] c = new double[M];
        double [] e = new double[M];
        int i=0;
        int j=0;
        while (i < N-1){
            alpha[0]=1;
            beta[0]=0;

            for ( j = 1; j < M-1; j++) {
                a[j] = -D * tau /( hx * hx);
                b[j] = 1 + 2 * D * tau / (hx * hx);
                c[j] = -D * tau / (hx * hx);
                e[j] = Cnj[i][j];
                alpha[j] = -a[j] / (b[j] + c[j] * alpha[j - 1]);
                beta[j] = (e[j] - c[j] * beta[j - 1]) / (b[j] + c[j] * alpha[j - 1]);
            }

            Cnj[i+1][M-1] = ( hx*bi/D*Cs + beta[M-2] ) / ( 1 - alpha[M-2] + hx*bi/D );

            for ( j = M-2; j >=0; j--) {
               Cnj[i+1][j]=alpha[j]* Cnj[i+1][j+1] + beta[j];
            }

            try(FileWriter writer = new FileWriter("laba2.txt", false))
            {

                writer.write(i);
                writer.flush();
            }
            catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
        i++;
        }

     /*   try(FileWriter writer = new FileWriter("laba2.txt", false))
        {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
}
