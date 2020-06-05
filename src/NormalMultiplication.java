public class NormalMultiplication {

    public NormalMultiplication(){

    }

    public int[][] matrixMultiplication(int[][] a, int[][] b){
        int aCol = a[0].length;
        int aRow = a.length;
        int bCol = b[0].length;

        int[][] c = new int[aRow][bCol];

        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < bCol; j++) {
                int cValue = 0;
                for (int k = 0; k < aCol; k++) {
                    cValue += a[i][k]*b[k][j];
                }
                c[i][j] = cValue;
            }
        }

        return c;
    }
}
