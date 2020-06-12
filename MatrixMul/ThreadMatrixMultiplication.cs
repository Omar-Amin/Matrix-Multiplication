using System;
using System.Collections.Generic;
using System.Threading.Tasks;
public class ThreadMatrixMultiplication
{
    public int[,] threadTiledTransposedMultiplication(int[,] a, int[,] b, int tileSize)
    {
        int aRow = a.GetLength(0);
        int aCol = a.GetLength(1);
        int bCol = b.GetLength(1);

        int[,] newB = new int[b.GetLength(1), b.GetLength(0)];

        for (int i = 0; i < b.GetLength(0); i++)
        {
            for (int j = 0; j < bCol; j++)
            {
                newB[j, i] = b[i, j];
            }
        }

        int[,] c = new int[aRow, bCol];

        int acolTiles = aCol % tileSize == 0 ? aCol / tileSize : aCol / tileSize + 1;
        int rowTiles = aRow % tileSize == 0 ? aRow / tileSize : aRow / tileSize + 1;
        int bcolTiles = bCol % tileSize == 0 ? bCol / tileSize : bCol / tileSize + 1;

        Func<object, int> action = (object obj) =>
         {
             int rowT = (int)obj;
             for (int bcolT = 0; bcolT < bcolTiles; bcolT++)
             {
                 for (int acolT = 0; acolT < acolTiles; acolT++)
                 {

                     int currentaRow = Math.Min((rowT + 1) * (tileSize), aRow);
                     int currentbCol = Math.Min((bcolT + 1) * (tileSize), bCol);
                     int currentAcol = Math.Min((acolT + 1) * (tileSize), aCol);

                     for (int i = rowT * tileSize; i < currentaRow; i++)
                     {
                         for (int j = bcolT * tileSize; j < currentbCol; j++)
                         {
                             int cValue = 0;
                             for (int k = acolT * tileSize; k < currentAcol; k++)
                             {
                                 cValue += a[i, k] * newB[j, k];
                             }
                             c[i, j] += cValue;

                         }
                     }

                 }
             }
             return 1;
         };

        List<Task<int>> tasks = new List<Task<int>>();

        for (int rowT = 0; rowT < rowTiles; rowT++)
        {
            tasks.Add(Task<int>.Factory.StartNew(action, rowT));

        }


        Task.WaitAll(tasks.ToArray());

        return c;
    }

}
