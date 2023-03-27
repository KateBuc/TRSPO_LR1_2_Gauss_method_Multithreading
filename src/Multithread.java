import java.util.Random;

public class Multithread implements Runnable{
    private static final int N = 3;

    public void run(){

        GaussianElimination ge = new GaussianElimination();

        double[] B = new double[N/2];
        double[][] A = new double[N/2][N/2];
        Random random = new Random();


        for (int i = 0; i < N/2; i++)
            for (int j = 0; j < N/2; j++)
                A[i][j] = (random.nextInt()%10) + 1;


        for (int i = 0; i < N/2; i++)
            B[i] = (random.nextInt()%10) + 1;

        ge.solve(A,B);

    }

}
