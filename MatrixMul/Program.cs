using System;

namespace MatrixMul
{
    class Program
    {
        static void Main(string[] args)
        {
            int[,] a = { { 1, 2, 3 }, { 4, 5, 6 } };
            int[,] b = { { 7, 8 }, { 9, 10 }, { 11, 12 } };

            MatrixMultiplication matrix = new MatrixMultiplication();

            int[,] c = matrix.normal(a, b);
            printMatrix(c);
        }

        private static void printMatrix(int[,] c)
        {
            int row = c.GetLength(0);
            int col = c.GetLength(1);

            for (int i = 0; i < row; i++)
            {
                for (int j = 0; j < col; j++)
                {
                    Console.Write(String.Format("{0}\t", c[i, j]));
                }
                Console.WriteLine();
            }
        }
    }
}
