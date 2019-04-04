import java.text.DecimalFormat;
import java.util.Arrays;

public class VectorPGlobal {
    double[] vectorPGlobal;
    FileData fileData;
    int typ;
    FileData miedz = new FileData(); // miedź
    FileData nikiel = new FileData("FEM_DATA2.txt"); //nikiel
    FileData kryonaut = new FileData("FEM_DATA3.txt"); //thermal gryzlly
    FileData air = new FileData("FEM_DATA4.txt"); //powietrze
    FileData conductonaut = new FileData("FEM_DATA5.txt"); //ciekly metal

    public VectorPGlobal(FileData fileData){
        this.fileData = fileData;
        vectorPGlobal = new double[fileData.nh];
        zerosVectorP();
    }

    void zerosVectorP(){
        for(int i=0; i<fileData.nh; i++){
            vectorPGlobal[i]=0;
        }
    }
    void showVectorPGlobal(){
        System.out.println("--------Początek Vector P Global----------------------\n");
        DecimalFormat decimalFormat = new DecimalFormat("#0.000");
        for(int i=0; i<fileData.nh; i++){
                System.out.print(decimalFormat.format(vectorPGlobal[i])+" \t");
        }
//        showMinMax();
        System.out.println("\n--------Koniec Vector P Global-----------------------------------------\n");

    }
    void agregatePLocalToPGlocal(MatrixHLocal matrixHLocal){
        for(int i=0; i<4; i++){
            vectorPGlobal[matrixHLocal.element.node[i].ID] += matrixHLocal.vectorP[i];
        }
    }
    void agregateCGlobaldTtZeroToPGlobal(MatrixCGlobal matrixCGlobal, int typ){
        for(int i=0; i<fileData.nh; i++) {
            for (int j = 0; j < fileData.nh; j++) {
                //{P} = {P} + {[C] / dTał} * {T0}

                if(i>1063){ fileData=nikiel; }
                if(i<=1063) { fileData=miedz; }
//                if((i==916 || i==917 || i==918 || i==919 || i==947 || i==948) && typ == 1){ fileData=kryonaut; }
                if(((i>1377 && i<=1395) || ((i>1415 && i<=1433))) && typ == 1){ fileData=kryonaut; }
                if(((i>1377 && i<=1395) || ((i>1415 && i<=1433))) && typ == 2){ fileData=conductonaut; }
                if(((i>1377 && i<=1395) || ((i>1415 && i<=1433))) && typ == 3){ fileData=air; }

                vectorPGlobal[i] += (matrixCGlobal.matrixCGlobal[i][j]/fileData.simulationStepTime) * fileData.initialTemperature;
            }
        }
    }
    void agregateCGlobaldTtFromGAUSSToPGlobal(MatrixCGlobal matrixCGlobal, double[] tempDTal){
        for(int i=0; i<fileData.nh; i++) {
            for (int j = 0; j < fileData.nh; j++) {
                //{P} = {P} + {[C] / dTał} * {Tn} <---- gdzie Tn to tempDTal z gaussa!
                vectorPGlobal[i] += (matrixCGlobal.matrixCGlobal[i][j]/fileData.simulationStepTime) * tempDTal[j];
            }
        }
    }
    void showMinMax(){
        double[] sort = vectorPGlobal;
        Arrays.sort(sort);
        System.out.println("\n---\nVector P Max value: "+sort[sort.length-1]+" \t Vector P Min value: "+sort[0]);
    }
}
