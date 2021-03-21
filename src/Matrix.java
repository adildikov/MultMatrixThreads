
public class Matrix {
    int lenMatr1;
    int lenMatr2;
    int matrix[][];
    int matrixLen;
    Matrix res;
    Matrix matr1;
    Matrix matr2;

    public class MyThread extends Thread {
        int start;
        int stop;

        public MyThread(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public void run() {
            for (int i = start; i < stop; i++) {
                for (int j = 0; j < lenMatr2; j++) {
                    for (int k = 0; k < lenMatr2; k++) {
                        res.matrix[i][j] += matr1.matrix[i][k] * matr2.matrix[k][j];
                    }
                }
            }
        }
    }

    public Matrix(int n) {
        matrix = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = (int)(Math.random() * (5 + 0));
            }
        }
        matrixLen = matrix.length;
    }

    public void printMatrix() {
        for (int i = 0; i < matrixLen; i++){
            for (int j = 0; j < matrixLen; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println('\n');
        }
    }

    public Matrix multMatrix(Matrix matrix1, Matrix matrix2, boolean flagTime) {
        matr1 = matrix1;
        matr2 = matrix2;
        lenMatr1 = matr1.matrixLen;
        lenMatr2 = matr2.matrixLen;
        res = new Matrix(matrixLen);
        for (int i = 0; i < lenMatr1; i++) {
            for (int j = 0; j < lenMatr2; j++) {
                res.matrix[i][j] = 0;
            }
        }
        double startTime = System.currentTimeMillis();
        for (int i = 0; i < lenMatr1; i++) {
            for (int j = 0; j < lenMatr2; j++) {
                for (int k = 0; k < lenMatr2; k++) {
                    res.matrix[i][j] += matr1.matrix[i][k] * matr2.matrix[k][j];
                }
            }
        }
        if (flagTime) {
            System.out.println("Time mult: " + (System.currentTimeMillis() - startTime) / 1000 + " sec");
        }
        return res;
    }

    public Matrix multMatrixThread(Matrix matrix1, Matrix matrix2, int numThreads, boolean flagTime) throws InterruptedException {
        matr1 = matrix1;
        matr2 = matrix2;
        lenMatr1 = matr1.matrixLen;
        lenMatr2 = matr2.matrixLen;
        res = new Matrix(matrixLen);
        for (int i = 0; i < lenMatr1; i++) {
            for (int j = 0; j < lenMatr2; j++) {
                res.matrix[i][j] = 0;
            }
        }

        double startTime = System.currentTimeMillis();
        int i = 0;
        Thread threads[] = new MyThread[numThreads];
        int maxForOneThread = lenMatr1 / numThreads;
        for (i = 0; i < numThreads - 1; i++){
            threads[i] = new MyThread(i * maxForOneThread, (i + 1) * maxForOneThread - 1);
        }
        threads[i] = new MyThread(i * maxForOneThread, lenMatr1);
        for (int j = 0; j < numThreads; j++){
            threads[j].start();
        }
        for (int j = 0; j < numThreads; j++){
            threads[j].join();
        }

        if (flagTime) {
            System.out.println("Time multThreads: " + (System.currentTimeMillis() - startTime) / 1000 + " sec");
        }
        return res;
    }
}
