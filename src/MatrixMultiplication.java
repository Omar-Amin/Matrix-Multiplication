
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

    public int[][] tiledMultiplication(int[][] a,int[][] b,int tileSize){
        int aCol = a[0].length;
        int aRow = a.length;
        int bCol = b[0].length;

        int acolTiles = aCol % tileSize == 0 ? aCol / tileSize : aCol / tileSize + 1;
        int rowTiles = aRow % tileSize == 0 ? aRow / tileSize : aRow / tileSize + 1;
        int bcolTiles = bCol % tileSize == 0 ? bCol / tileSize : bCol / tileSize + 1;

        int[][] c = new int[aRow][bCol];

        // fori in tiles
        for (int rowT = 0; rowT < rowTiles; rowT++) {
            for (int bcolT = 0; bcolT < bcolTiles; bcolT++) {
                for (int acolT = 0; acolT < acolTiles; acolT++) {
                    // multiplication inside tile
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

    public int[][] tiledTransposedMultiplication(int[][] a,int[][] b,int tileSize){
        int[][] newB = new int[b[0].length][b.length];

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length ; j++) {
                newB[j][i] = b[i][j];
            }
        }

        int aCol = a[0].length;
        int aRow = a.length;
        int bCol = newB.length;

        int acolTiles = aCol % tileSize == 0 ? aCol / tileSize : aCol / tileSize + 1;
        int rowTiles = aRow % tileSize == 0 ? aRow / tileSize : aRow / tileSize + 1;
        int bcolTiles = bCol % tileSize == 0 ? bCol / tileSize : bCol / tileSize + 1;

        int[][] c = new int[aRow][bCol];

        // fori in tiles
        for (int rowT = 0; rowT < rowTiles; rowT++) {
            for (int bcolT = 0; bcolT < bcolTiles; bcolT++) {
                for (int acolT = 0; acolT < acolTiles; acolT++) {
                    // multiplication inside tile
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
