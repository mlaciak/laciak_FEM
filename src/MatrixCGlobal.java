import java.text.DecimalFormat;

public class MatrixCGlobal{
    double[][] matrixCGlobal;
    FileData fileData;
    public MatrixCGlobal(FileData fileData){
        matrixCGlobal = new double[fileData.nh][fileData.nh];
        this.fileData = fileData;
        for(int i=0; i<fileData.nh; i++){
            for (int j=0; j<fileData.nh; j++){
                matrixCGlobal[i][j] = 0;
            }
        }
    }
    void showMatrixCGlobal(){
        System.out.println("--------Początek Matrix C Global----------------------\n");
        DecimalFormat decimalFormat = new DecimalFormat("#0.000");
        for(int i=0; i<fileData.nh; i++){
            for(int j=0; j<fileData.nh; j++){
                System.out.print(decimalFormat.format(matrixCGlobal[i][j])+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Matrix C Global-----------------------------------------\n");

    }
    void agregateCLocalToCGlocal(MatrixCLocal matrixCLocal){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                matrixCGlobal[matrixCLocal.element.node[i].ID][matrixCLocal.element.node[j].ID] += matrixCLocal.matrixCLocal[i][j];
            }
        }
    }
}
