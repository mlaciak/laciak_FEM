import java.text.DecimalFormat;

public class MatrixHGlobal {
    double[][] matrixHGlobal;
    FileData fileData;
    public MatrixHGlobal(FileData fileData){
        matrixHGlobal = new double[fileData.nh][fileData.nh];
        this.fileData = fileData;
        for(int i=0; i<fileData.nh; i++){
            for (int j=0; j<fileData.nh; j++){
                matrixHGlobal[i][j] = 0;
            }
        }
    }
    void showMatrixHGlobal(){
        System.out.println("--------Początek Matrix H Global----------------------\n");
        DecimalFormat decimalFormat = new DecimalFormat("#0.000");
        for(int i=0; i<fileData.nh; i++){
            for(int j=0; j<fileData.nh; j++){
                System.out.print(decimalFormat.format(matrixHGlobal[i][j])+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Matrix H Global-----------------------------------------\n");

    }
    void agregateHLocalToHGlocal(MatrixHLocal matrixHLocal){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
               matrixHGlobal[matrixHLocal.element.node[i].ID][matrixHLocal.element.node[j].ID] += matrixHLocal.matrixHLocal[i][j];
            }
        }
    }

    void agregateCdTGlobalToHGlocal(MatrixCGlobal matrixCGlobal){
        for(int i=0; i<fileData.nh; i++){
            for(int j=0; j<fileData.nh; j++){
                //[H] = [H] + [C] / dT
                matrixHGlobal[i][j] += matrixCGlobal.matrixCGlobal[i][j]/fileData.simulationStepTime;
            }
        }
    }

    void agregateHBCLocalToHGlocal(MatrixHLocal matrixHLocal){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
//              Matrix [H] = [H] + [HBC]
                matrixHGlobal[matrixHLocal.element.node[i].ID][matrixHLocal.element.node[j].ID] += matrixHLocal.matrixHBC[i][j];
            }
        }
    }
}
