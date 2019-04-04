public class GaussSolver {
    final double EPSILON = 1e-10;

    public double[] gaussSolver(MatrixHGlobal matrixHGlobal, VectorPGlobal vectorPGlobal, FileData fileData){

        int n = vectorPGlobal.vectorPGlobal.length;

        double[][] A =new double[fileData.nh][fileData.nh];

        for(int i=0;i<fileData.nh;i++){

            for(int j=0;j<fileData.nh;j++){
                A[i][j]= matrixHGlobal.matrixHGlobal [i][j];
            }
        }

        for (int p = 0; p < n; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = vectorPGlobal.vectorPGlobal[p]; vectorPGlobal.vectorPGlobal[p] = vectorPGlobal.vectorPGlobal[max]; vectorPGlobal.vectorPGlobal[max] = t;

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                vectorPGlobal.vectorPGlobal[i] -= alpha * vectorPGlobal.vectorPGlobal[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (vectorPGlobal.vectorPGlobal[i] - sum) / A[i][i];
        }
        return x;
    }
}
