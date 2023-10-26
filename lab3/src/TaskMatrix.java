import java.util.Random;

public class TaskMatrix {
    private int[][] matrix;

    public TaskMatrix(int rows, int columns)
    {
        this.matrix = new int[rows][columns];
        this.randomValue();
    }
    public TaskMatrix(TaskMatrix taskMatrix) {this.matrix = copyMatrix(taskMatrix.matrix);}
    public TaskMatrix(int[][] matrix) {this.matrix = copyMatrix(matrix);}

     public int[][] getMatrix(){
        return this.matrix;
     }

     public void randomValue(){
         Random random = new Random();
         for(int i =0; i < matrix.length; i++)
         {
             for (int j =0; j< matrix[i].length;j ++) {
                 matrix[i][j] = random.nextInt(10) - 5;
             }
         }
     }
    public static int[][] copyMatrix(int[][] matrix)
    {
        int[][] copiedMatrix = new int[matrix[0].length][];

        for (int i = 0; i < matrix.length; i++)
        {
            copiedMatrix[i] = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++)
            {
                copiedMatrix[i][j] = matrix[i][j];
            }
        }
        return copiedMatrix;
    }

    public void printMatrix()
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public void modifiedMatrix() {
        float coef = 1;
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                coef = (float) matrix[j][i] / matrix[i][i];

                for (int z = i; z < matrix[j].length; z++) {
                    int temp = matrix[i][z];
                    temp *= coef;
                    matrix[j][z] -= temp;
                }
            }
        }
    }

    public void sum() {
        System.out.println("с) Найти сумму треугольной матрицы: ");
        int[][] matrix = this.matrix;
        int sum = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++) {
                if (i <= j) {
                    sum += matrix[i][j]  ;
                }
            }
        }

        System.out.print("Сумма: " + sum);
        System.out.println();
    }

    public void newString()
    {
        System.out.println("d) Сформировать строку из всех положительных элементов треугольной матрицы: ");
        String string = "";
        int a;

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                if (i <= j)
                {
                    a = matrix[i][j];
                    if (a > 0)
                    {
                        string += a + " ";

                    }
                }
            }
        }
        System.out.print("   " + string + " ");
        System.out.println();
    }
}
