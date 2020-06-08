import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiplicationThreads {

    /**
     * Matrix multiplication by transposing the matrix and using threads.
     * */
    public static int[][] threadTransposedNormal(int[][] a, int[][] b){
        final int aCol, aRow, bCol;
        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

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
        CountDownLatch countDownLatch = new CountDownLatch(aRow);

        for (int i = 0; i < aRow; i++) {
            int finalI = i;
            executorService.execute(() -> {
                for (int j = 0; j < bCol; j++) {
                    int cValue = 0;
                    for (int k = 0; k < aCol; k++) {
                        // changed because it is transposed
                        cValue += a[finalI][k]*newB[j][k];
                    }
                    c[finalI][j] = cValue;
                }
                countDownLatch.countDown();
            });

        }

        try {
            countDownLatch.await();
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return c;
    }

    /**
     * Tiled version using threads.
     * */
    public static int[][] threadTiledMultiplication(int[][] a,int[][] b,int tileSize){
        final int aCol, aRow, bCol, acolTiles, rowTiles, bcolTiles;
        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        aCol = a[0].length;
        aRow = a.length;
        bCol = b[0].length;

        acolTiles = aCol % tileSize == 0 ? aCol / tileSize : aCol / tileSize + 1;
        rowTiles = aRow % tileSize == 0 ? aRow / tileSize : aRow / tileSize + 1;
        bcolTiles = bCol % tileSize == 0 ? bCol / tileSize : bCol / tileSize + 1;

        int[][] c = new int[aRow][bCol];
        CountDownLatch countDownLatch = new CountDownLatch(rowTiles);

        // divided into blocks, where it calculates the matrix multiplication in blocks
        for (int rowT = 0; rowT < rowTiles; rowT++) {
            int finalRowT = rowT;

            executorService.execute(() -> {
                for (int bcolT = 0; bcolT < bcolTiles; bcolT++) {
                    for (int acolT = 0; acolT < acolTiles; acolT++) {
                        // to make sure that it calculates the rest of the matrix without causing an out of loop error
                        // e.g. if size is 20/3, then the last block is 2 (the remainder of the division)
                        int currentaRow = Math.min((finalRowT +1)*(tileSize),aRow);
                        int currentbCol = Math.min((bcolT+1)*(tileSize), bCol);
                        int currentAcol = Math.min((acolT+1)*(tileSize),aCol);

                        for (int i = finalRowT *tileSize; i < currentaRow; i++) {
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
                countDownLatch.countDown();
            });

        }

        try {
            countDownLatch.await();
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Matrix multiplication using threads and transposed tiles.
     * */
    public static int[][] threadTiledTransposedMultiplication(int[][] a,int[][] b,int tileSize){
        final int aCol, aRow, bCol, acolTiles, rowTiles, bcolTiles;

        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        int[][] newB = new int[b[0].length][b.length];

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length ; j++) {
                newB[j][i] = b[i][j];
            }
        }

        aCol = a[0].length;
        aRow = a.length;
        bCol = newB.length;

        acolTiles = aCol % tileSize == 0 ? aCol / tileSize : aCol / tileSize + 1;
        rowTiles = aRow % tileSize == 0 ? aRow / tileSize : aRow / tileSize + 1;
        bcolTiles = bCol % tileSize == 0 ? bCol / tileSize : bCol / tileSize + 1;

        // each thread has to calculate a row of tiles, thus shutting down after all
        // the rows have been calculated
        CountDownLatch countDownLatch = new CountDownLatch(rowTiles);

        int[][] c = new int[aRow][bCol];

        for (int rowT = 0; rowT < rowTiles; rowT++) {
            int finalRowT = rowT;

            // giving each thread to execute a row of tiles
            executorService.execute(() -> {
                for (int bcolT = 0; bcolT < bcolTiles; bcolT++) {
                    for (int acolT = 0; acolT < acolTiles; acolT++) {
                        // multiplication inside tile
                        int currentaRow = Math.min((finalRowT +1)*(tileSize),aRow);
                        int currentbCol = Math.min((bcolT +1)*(tileSize), bCol);
                        int currentAcol = Math.min((acolT +1)*(tileSize),aCol);

                        for (int i = finalRowT *tileSize; i < currentaRow; i++) {
                            for (int j = bcolT *tileSize; j < currentbCol; j++) {
                                int cValue = 0;
                                for (int k = acolT *tileSize; k < currentAcol; k++) {
                                    cValue += a[i][k]*newB[j][k];
                                }

                                // doesn't need synchronization since each thread does
                                // the incrementation inside its own thread
                                c[i][j] += cValue;

                            }
                        }
                    }

                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return c;
    }
}
