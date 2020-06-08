import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[][] a = generateMatrix(5000,2500);
        int[][] b = generateMatrix(2500,5000);

        MatrixMultiplication nm = new MatrixMultiplication();
        //System.out.println(Arrays.deepToString(nm.normal(a, b)));
        long start = System.currentTimeMillis();
        nm.threadTiledTransposedMultiplication(a, b, 25);
        System.out.println(System.currentTimeMillis()-start + " ms");
    }

    private static int[][] generateMatrix(int h, int w){
        int[][] matrix = new int[h][w];
        Random random = new Random(100);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = random.nextInt(10)+1;
            }
        }

        return matrix;
    }
}
