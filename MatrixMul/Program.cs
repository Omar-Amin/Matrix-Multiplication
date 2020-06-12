using System;
using System.Diagnostics;

namespace MatrixMul
{
    class Program
    {
        static void Main(string[] args)
        {
            int[,] a = generateMatrix(5000, 700);
            int[,] b = generateMatrix(700, 5000);

            ThreadMatrixMultiplication matrix = new ThreadMatrixMultiplication();
            Stopwatch time = new Stopwatch();
            time.Start();
            int[,] c = matrix.threadTiledTransposedMultiplication(a, b, 25);
            time.Stop();
            Console.WriteLine(time.ElapsedMilliseconds);
        }

        private static int[,] generateMatrix(int h, int w)
        {
            int[,] m = new int[h, w];
            Random random = new Random(11);
            for (int i = 0; i < h; i++)
            {
                for (int j = 0; j < w; j++)
                {
                    m[i, j] = random.Next(10);
                }
            }

            return m;
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

        private static void outputToFile(int[,] c, string path)
        {
            int row = c.GetLength(0);
            int col = c.GetLength(1);
            string[] lines = new string[row];

            for (int i = 0; i < row; i++)
            {
                string s = "";
                for (int j = 0; j < col; j++)
                {
                    s += String.Format("{0} ", c[i, j]);
                }
                lines[i] = s;
            }

            System.IO.File.WriteAllLines(path, lines);
        }
    }
}
