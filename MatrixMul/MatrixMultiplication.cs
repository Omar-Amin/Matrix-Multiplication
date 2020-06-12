using System;
public class MatrixMultiplication
{
    /// <summary>Normal matrix multiplication.</summary>
    public int[,] normal(int[,] a, int[,] b)
    {
        int aRow = a.GetLength(0);
        int aCol = a.GetLength(1);
        int bCol = b.GetLength(1);

        int[,] c = new int[aRow, bCol];

        for (int i = 0; i < aRow; i++)
        {
            for (int j = 0; j < bCol; j++)
            {
                int cValue = 0;
                for (int k = 0; k < aCol; k++)
                {
                    cValue += a[i, k] * b[k, j];
                }
                c[i, j] = cValue;
            }
        }

        return c;
    }

    /// <summary>Transposed matrix multiplication.</summary>
    public int[,] transposed(int[,] a, int[,] b)
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

        for (int i = 0; i < aRow; i++)
        {
            for (int j = 0; j < bCol; j++)
            {
                int cValue = 0;
                for (int k = 0; k < aCol; k++)
                {
                    cValue += a[i, k] * newB[j, k];
                }
                c[i, j] = cValue;
            }
        }

        return c;
    }

}
