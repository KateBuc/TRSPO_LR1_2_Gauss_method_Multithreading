import java.util.Random;

public class GaussianElimination
{
    private static final int N = 400;
    public static void solve(double[][] A, double[] B)
    {
        int N = B.length;
        for (int k = 0; k < N; k++)
        {
            // ����������� �������� �����
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            // ����� ������ � ������� A
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            // ���� ������ ������� �������� � ������� ��������
            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            //������� � ����� � �� �
            for (int i = k + 1; i < N; i++)
            {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }


    }
    //��������� ����������� �������
    public static void printRowEchelonForm(double[][] A, double[] B)
    {
        int N = B.length;
        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.printf("%.3f ", A[i][j]);
            System.out.printf("| %.3f\n", B[i]);
        }
        System.out.println();
    }


    public static void printSolution(double[] sol)
    {
        int N = sol.length;
        System.out.println("\nSolution : ");
        for (int i = 0; i < N; i++)
            System.out.printf("%.3f ", sol[i]);
        System.out.println();
    }



    public static void main (String[] args)
    {

        System.out.println("Gaussian Elimination Algorithm\n");

        GaussianElimination ge = new GaussianElimination();




        double[] B = new double[N];
        double[][] A = new double[N][N];
        Random random = new Random();



        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = (random.nextInt()%10) + 1;


        for (int i = 0; i < N; i++)
            B[i] = (random.nextInt()%10) + 1;
        long time = System.currentTimeMillis();
        Multithread m1 = new Multithread();
        Thread thread1 =new Thread(m1);
        thread1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadSolve();
            }
        }).start();

        ge.solve(A,B);

        //��������� ������� ������
        printRowEchelonForm(A, B);

        // �������� ���
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }

        System.out.println("�omputation time: "+(System.currentTimeMillis() - time)+" milliseconds");


        printSolution(solution);
    }

    static void threadSolve() {

        GaussianElimination ge = new GaussianElimination();


        double[] B = new double[N];
        double[][] A = new double[N][N];
        Random random = new Random();



        for (int i = 0; i < N/2; i++)
            for (int j = 0; j < N/2; j++)
                A[i][j] = (random.nextInt()%10) + 1;


        for (int i = 0; i < N/2; i++)
            B[i] = (random.nextInt()%10) + 1;

        ge.solve(A,B);
    }
}