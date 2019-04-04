public class MatrixCLocal {
    // [C] = całka po objętości z ( c*p * {N}{N} ) dv
Element element;
    double[][] matrixCLocal;
    double[][] firstPCcpNN;
    double[][] secondPCcpNN;
    double[][] thirdPCcpNN;
    double[][] fourthPCcpNN;

    public MatrixCLocal(Element element, Jakobian2D jakobian2D, FileData fileData){
        this.element=element;
        matrixCLocal = new double[4][4];
        firstPCcpNN = new double[4][4];
        secondPCcpNN = new double[4][4];
        thirdPCcpNN = new double[4][4];
        fourthPCcpNN = new double[4][4];
        Universal_Element universal_element = new Universal_Element();

        // c p {N}{N}
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                firstPCcpNN[i][j] = universal_element.dN[0][i] * universal_element.dN[0][j] * jakobian2D.detJ[0] * fileData.specificHeat * fileData.density;
                secondPCcpNN[i][j] = universal_element.dN[1][i] * universal_element.dN[1][j] * jakobian2D.detJ[0] * fileData.specificHeat * fileData.density;
                thirdPCcpNN[i][j] = universal_element.dN[2][i] * universal_element.dN[2][j] * jakobian2D.detJ[0] * fileData.specificHeat * fileData.density;
                fourthPCcpNN[i][j] = universal_element.dN[3][i] * universal_element.dN[3][j] * jakobian2D.detJ[0] * fileData.specificHeat * fileData.density;
            }
        }

        //local C matrix:
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                matrixCLocal[i][j] = firstPCcpNN[i][j] + secondPCcpNN[i][j] + thirdPCcpNN[i][j] + fourthPCcpNN[i][j];
            }
        }



    }
//------------------------------------------------ METODY --------------------------------------

    void showMatrixCLocal(){
        System.out.println("--------Początek Matrix C Local----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(matrixCLocal[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Matrix C Local-----------------------------------------\n");
    }

    void showFirstPCcpNN(){
        System.out.println("--------Początek First PC cpNN----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(firstPCcpNN[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec First PC cpNN-----------------------------------------\n");
    }

    void showSecontPCcpNN(){
        System.out.println("--------Początek Secont PC cpNN----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(secondPCcpNN[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Second PC cpNN-----------------------------------------\n");
    }

    void showThirdPCcpNN(){
        System.out.println("--------Początek Third PC cpNN----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(thirdPCcpNN[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Third PC cpNN-----------------------------------------\n");
    }

    void showFourthPCcpNN(){
        System.out.println("--------Początek Fourth PC cpNN----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(fourthPCcpNN[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Fourth PC cpNN-----------------------------------------\n");
    }
}
