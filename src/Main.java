import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] a = {{1,2,3},{4,5,6}};
        int[][] b = {{7,8},{9,10},{11,12}};
        NormalMultiplication nm = new NormalMultiplication();

        System.out.println(Arrays.deepToString(nm.matrixMultiplication(a, b)));
    }
}
