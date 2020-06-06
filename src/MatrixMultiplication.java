
public class MatrixMultiplication {

    public MatrixMultiplication(){

    }

    public int[][] normal(int[][] a, int[][] b){
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

    public int[][] transposedNormal(int[][] a, int[][] b){
        int[][] newB = new int[b[0].length][b.length];

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length ; j++) {
                newB[j][i] = b[i][j];
            }
        }

        int aCol = a[0].length;
        int aRow = a.length;
        int bCol = newB.length;

        int[][] c = new int[aRow][bCol];

        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < bCol; j++) {
                int cValue = 0;
                for (int k = 0; k < aCol; k++) {
                    cValue += a[i][k]*newB[j][k];
                }
                c[i][j] = cValue;
            }
        }

        return c;
    }
}
