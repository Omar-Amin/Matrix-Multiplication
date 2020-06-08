import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultiplication {

    /**
     * Normal matrix multiplication.
     * */
    public static int[][] normal(int[][] a, int[][] b){
        final int aCol, aRow, bCol;

        aCol = a[0].length;
        aRow = a.length;
        bCol = b[0].length;

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

    /**
     * Matrix multiplication by transposing the matrix.
     * */
    public static int[][] transposedNormal(int[][] a, int[][] b){
        final int aCol, aRow, bCol;

        int[][] newB = new int[b[0].length][b.length];

        // transposing the b matrix into a new one
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length ; j++) {
                newB[j][i] = b[i][j];
            }
        }

        aCol = a[0].length;
        aRow = a.length;
        bCol = newB.length;

        int[][] c = new int[aRow][bCol];

        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < bCol; j++) {
                int cValue = 0;
                for (int k = 0; k < aCol; k++) {
                    // changed because it is transposed
                    cValue += a[i][k]*newB[j][k];
                }
                c[i][j] = cValue;
            }
        }

        return c;
    }

    /**
     * Matrix multiplication using tiles. The tiled version is "primary" use is to optimize threading, however,
     * it was interesting to check on a singlethread compared to the other multiplications. Hence, a transposed version
     * of the tiled version has been created as seen below.
     * */
    public static int[][] tiledMultiplication(int[][] a,int[][] b,int tileSize){
        final int aCol, aRow, bCol, acolTiles, rowTiles, bcolTiles;

        aCol = a[0].length;
        aRow = a.length;
        bCol = b[0].length;

        acolTiles = aCol % tileSize == 0 ? aCol / tileSize : aCol / tileSize + 1;
        rowTiles = aRow % tileSize == 0 ? aRow / tileSize : aRow / tileSize + 1;
        bcolTiles = bCol % tileSize == 0 ? bCol / tileSize : bCol / tileSize + 1;

        int[][] c = new int[aRow][bCol];

        // divided into blocks, where it calculates the matrix multiplication in blocks
        for (int rowT = 0; rowT < rowTiles; rowT++) {
            for (int bcolT = 0; bcolT < bcolTiles; bcolT++) {
                for (int acolT = 0; acolT < acolTiles; acolT++) {
                    // to make sure that it calculates the rest of the matrix without causing an out of loop error
                    // e.g. if size is 20/3, then the last block is 2 (the remainder of the division)
                    int currentaRow = Math.min((rowT+1)*(tileSize),aRow);
                    int currentbCol = Math.min((bcolT+1)*(tileSize), bCol);
                    int currentAcol = Math.min((acolT+1)*(tileSize),aCol);

                    for (int i = rowT*tileSize; i < currentaRow; i++) {
                        for (int j = bcolT*tileSize; j < currentbCol; j++) {
                            int cValue = 0;
                            for (int k = acolT*tileSize; k < currentAcol; k++) {
                                cValue += a[i][k]*b[k][j];
                            }
                            c[i][j] += cValue;

                        }
                    }

                }

            }
        }


        return c;
    }

    /**
     * Matrix multiplication using transposed tiles.
     * */
    public static int[][] tiledTransposedMultiplication(int[][] a,int[][] b,int tileSize){
        final int aCol, aRow, bCol, acolTiles, rowTiles, bcolTiles;

        int[][] newB = new int[b[0].length][b.length];

        // transposing the b matrix into a new one
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length ; j++) {
                newB[j][i] = b[i][j];
            }
        }

        aCol = a[0].length;
        aRow = a.length;
        bCol = newB.length;
        int[][] c = new int[aRow][bCol];

        acolTiles = aCol % tileSize == 0 ? aCol / tileSize : aCol / tileSize + 1;
        rowTiles = aRow % tileSize == 0 ? aRow / tileSize : aRow / tileSize + 1;
        bcolTiles = bCol % tileSize == 0 ? bCol / tileSize : bCol / tileSize + 1;

        for (int rowT = 0; rowT < rowTiles; rowT++) {
            for (int bcolT = 0; bcolT < bcolTiles; bcolT++) {
                for (int acolT = 0; acolT < acolTiles; acolT++) {

                    int currentaRow = Math.min((rowT+1)*(tileSize),aRow);
                    int currentbCol = Math.min((bcolT+1)*(tileSize), bCol);
                    int currentAcol = Math.min((acolT+1)*(tileSize),aCol);

                    for (int i = rowT*tileSize; i < currentaRow; i++) {
                        for (int j = bcolT*tileSize; j < currentbCol; j++) {
                            int cValue = 0;
                            for (int k = acolT*tileSize; k < currentAcol; k++) {
                                cValue += a[i][k]*newB[j][k];
                            }
                            c[i][j] += cValue;

                        }
                    }

                }

            }
        }


        return c;
    }





}
