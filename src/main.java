
public class main {
    public static void main(String[] args) throws InterruptedException{
        int n = 1000;
        Matrix matrix1 = new Matrix(n);
        Matrix matrix2 = new Matrix(n);
        Matrix res = new Matrix(n);
        res = res.multMatrixThread(matrix1, matrix2, 4, true);
        res = res.multMatrix(matrix1, matrix2, true);
        //matrix1.printMatrix();
        //System.out.println("---------------");
        //matrix2.printMatrix();
        //System.out.println("---------------");
        //res.printMatrix();
    }
}
