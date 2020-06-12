using System;
public class MatrixMultiplication
{
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

}
