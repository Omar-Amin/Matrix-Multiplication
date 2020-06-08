import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //testRuntimePerformance(1);
    }

    private static void testRuntimePerformance(int avg){
        int[][] a = generateMatrix(5000,700);
        int[][] b = generateMatrix(700,5000);

        long avgRuntime = 0;
        for (int i = 0; i < avg; i++) {
            long start = System.currentTimeMillis();
            MatrixMultiplication.normal(a, b);
            avgRuntime += System.currentTimeMillis()-start;
        }
        System.out.println("Average execution time for normal multiplication: " + (float) avgRuntime/avg + " ms");
        /* Average on 73065 ms */

        avgRuntime = 0;
        for (int i = 0; i < avg; i++) {
            long start = System.currentTimeMillis();
            MatrixMultiplication.transposedNormal(a, b);
            avgRuntime += System.currentTimeMillis()-start;
        }
        System.out.println("Average execution time for transposed multiplication: " + (float) avgRuntime/avg + " ms");
        /* Average on 11380 ms */

        avgRuntime = 0;
        for (int i = 0; i < avg; i++) {
            long start = System.currentTimeMillis();
            MatrixMultiplication.tiledMultiplication(a, b,17);
            avgRuntime += System.currentTimeMillis()-start;
        }
        System.out.println("Average execution time for tiled multiplication: " + (float) avgRuntime/avg + " ms");
        /* Average on 21516 ms */

        avgRuntime = 0;
        for (int i = 0; i < avg; i++) {
            long start = System.currentTimeMillis();
            MatrixMultiplication.tiledTransposedMultiplication(a, b,17);
            avgRuntime += System.currentTimeMillis()-start;
        }
        System.out.println("Average execution time for tiled transposed multiplication: " + (float) avgRuntime/avg + " ms");
        /* Average on 12289 ms */

        avgRuntime = 0;
        for (int i = 0; i < avg; i++) {
            long start = System.currentTimeMillis();
            MultiplicationThreads.threadTiledTransposedMultiplication(a, b,17);
            avgRuntime += System.currentTimeMillis()-start;
        }
        System.out.println("Average execution time for threaded multiplication: " + (float) avgRuntime/avg + " ms");
        /* Average on 3325 ms */
    }

    private static int[][] generateMatrix(int h, int w){
        int[][] matrix = new int[h][w];
        Random random = new Random();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = random.nextInt(10)+1;
            }
        }

        return matrix;
    }
}
