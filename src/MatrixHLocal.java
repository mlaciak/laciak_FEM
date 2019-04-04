public class MatrixHLocal {
    Element element;
    double[][] matrixHLocal;
    double[][] matrixHBC;

    double[][] firstPCDNdxdNdxT;
    double[][] secondPCDNdxdNdxT;
    double[][] thirdPCDNdxdNdxT;
    double[][] fourthPCDNdxdNdxT;

    double[][] firstPCDNdydNdyT;
    double[][] secondPCDNdydNdyT;
    double[][] thirdPCDNdydNdyT;
    double[][] fourthPCDNdYdNdyT;

    double[][] firstPCDNdxdNdxTDetJ;
    double[][] secondPCDNdxdNdxTDetJ;
    double[][] thirdPCDNdxdNdxTDetJ;
    double[][] fourthPCDNdxdNdxTDetJ;

    double[][] firstPCDNdydNdyTDetJ;
    double[][] secondPCDNdydNdyTDetJ;
    double[][] thirdPCDNdydNdyTDetJ;
    double[][] fourthPCDNdYdNdyTDetJ;

    double[][] kFirstdNdxdNdxTdNdydNdyTDetJ;
    double[][] kSeconddNdxdNdxTdNdydNdyTDetJ;
    double[][] kThirddNdxdNdxTdNdydNdyTDetJ;
    double[][] kFourthdNdxdNdxTdNdydNdyTDetJ;

    double[][] sum1;
    double[][] sum2;
    double[][] sum3;
    double[][] sum4;
    double[] sum1p;
    double[] sum2p;
    double[] sum3p;
    double[] sum4p;

    double[][] pc1;
    double[][] pc2;

    double[][] sideLength;
    double[][] ksiEta;
    double[][] pcN1; // punkty całkowania i n'ki do liczenia povierhni
    double[][] pcN2; // punkty całkowania i n'ki do liczenia povierhni
    double[][] pcN3; // punkty całkowania i n'ki do liczenia povierhni
    double[][] pcN4; // punkty całkowania i n'ki do liczenia povierhni
    double[] vectorP;



    public MatrixHLocal(Element element, Jakobian2D jakobian2D, FileData fileData){
        this.element=element;
        matrixHLocal = new double[4][4];
        matrixHBC = new double[4][4];
        firstPCDNdxdNdxT = new double[4][4];
        firstPCDNdydNdyT = new double[4][4];
        secondPCDNdxdNdxT = new double[4][4];
        secondPCDNdydNdyT = new double[4][4];
        thirdPCDNdxdNdxT = new double[4][4];
        thirdPCDNdydNdyT = new double[4][4];
        fourthPCDNdxdNdxT = new double[4][4];
        fourthPCDNdYdNdyT = new double[4][4];

        firstPCDNdxdNdxTDetJ = new double[4][4];
        firstPCDNdydNdyTDetJ = new double[4][4];
        secondPCDNdxdNdxTDetJ = new double[4][4];
        secondPCDNdydNdyTDetJ = new double[4][4];
        thirdPCDNdxdNdxTDetJ = new double[4][4];
        thirdPCDNdydNdyTDetJ = new double[4][4];
        fourthPCDNdxdNdxTDetJ = new double[4][4];
        fourthPCDNdYdNdyTDetJ = new double[4][4];

        kFirstdNdxdNdxTdNdydNdyTDetJ = new double[4][4];
        kSeconddNdxdNdxTdNdydNdyTDetJ = new double[4][4];
        kThirddNdxdNdxTdNdydNdyTDetJ = new double[4][4];
        kFourthdNdxdNdxTdNdydNdyTDetJ = new double[4][4];

        sum1 = new double[4][4];
        sum2 = new double[4][4];
        sum3 = new double[4][4];
        sum4 = new double[4][4];
        sum1p = new double[4];
        sum2p = new double[4];
        sum3p = new double[4];
        sum4p = new double[4];
        pc1 = new double[4][4];
        pc2 = new double[4][4];

        sideLength = new double[4][2];
        ksiEta = new double[2][8];
        pcN1 = new double[2][4];
        pcN2 = new double[2][4];
        pcN3 = new double[2][4];
        pcN4 = new double[2][4];

        vectorP = new double[]{0, 0, 0, 0};

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                //{dN/dx}{dN/dx}T
                firstPCDNdxdNdxT[i][j] = jakobian2D.dNdx[0][i] * jakobian2D.dNdx[0][j];
                firstPCDNdydNdyT[i][j] = jakobian2D.dNdy[0][i] * jakobian2D.dNdy[0][j];

                secondPCDNdxdNdxT[i][j] = jakobian2D.dNdx[1][i] * jakobian2D.dNdx[1][j];
                secondPCDNdydNdyT[i][j] = jakobian2D.dNdy[1][i] * jakobian2D.dNdy[1][j];

                thirdPCDNdxdNdxT[i][j] = jakobian2D.dNdx[2][i] * jakobian2D.dNdx[2][j];
                thirdPCDNdydNdyT[i][j] = jakobian2D.dNdy[2][i] * jakobian2D.dNdy[2][j];

                fourthPCDNdxdNdxT[i][j] = jakobian2D.dNdx[3][i] * jakobian2D.dNdx[3][j];
                fourthPCDNdYdNdyT[i][j] = jakobian2D.dNdy[3][i] * jakobian2D.dNdy[3][j];
            }
        }
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                //{dN/dx}{dN/dx}T
                firstPCDNdxdNdxTDetJ[i][j] = firstPCDNdxdNdxT[i][j] * jakobian2D.detJ[0];
                firstPCDNdydNdyTDetJ[i][j] = firstPCDNdydNdyT[i][j] * jakobian2D.detJ[0];

                secondPCDNdxdNdxTDetJ[i][j] = secondPCDNdxdNdxT[i][j] * jakobian2D.detJ[1];
                secondPCDNdydNdyTDetJ[i][j] = secondPCDNdydNdyT[i][j] * jakobian2D.detJ[1];

                thirdPCDNdxdNdxTDetJ[i][j] = thirdPCDNdxdNdxT[i][j] * jakobian2D.detJ[2];
                thirdPCDNdydNdyTDetJ[i][j] = thirdPCDNdydNdyT[i][j] * jakobian2D.detJ[2];

                fourthPCDNdxdNdxTDetJ[i][j] = fourthPCDNdxdNdxT[i][j] * jakobian2D.detJ[3];
                fourthPCDNdYdNdyTDetJ[i][j] = fourthPCDNdYdNdyT[i][j] * jakobian2D.detJ[3];
            }
        }

        //K*({dN/dx}{dN/dx}T + {dN/dy}{dN/dY}T)*DetJ
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                kFirstdNdxdNdxTdNdydNdyTDetJ[i][j] = fileData.conductivity * (firstPCDNdxdNdxTDetJ[i][j] + firstPCDNdydNdyTDetJ[i][j]);
                kSeconddNdxdNdxTdNdydNdyTDetJ[i][j] = fileData.conductivity * (secondPCDNdxdNdxTDetJ[i][j] + secondPCDNdydNdyTDetJ[i][j]);
                kThirddNdxdNdxTdNdydNdyTDetJ[i][j] = fileData.conductivity * (thirdPCDNdxdNdxTDetJ[i][j] + thirdPCDNdydNdyTDetJ[i][j]);
                kFourthdNdxdNdxTdNdydNdyTDetJ[i][j] = fileData.conductivity * (fourthPCDNdxdNdxTDetJ[i][j] + fourthPCDNdYdNdyTDetJ[i][j]);
            }
        }

        //matrix H local:
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                matrixHLocal[i][j] = kFirstdNdxdNdxTdNdydNdyTDetJ[i][j] + kSeconddNdxdNdxTdNdydNdyTDetJ[i][j] + kThirddNdxdNdxTdNdydNdyTDetJ[i][j]+
                        kFourthdNdxdNdxTdNdydNdyTDetJ[i][j];
            }
        }
//        -------------------------------------------------OBLICZANIE WARUNKU BRZEGOWEGO DO H -------------------------
        // liczymy dlugosc bokow:
        for(int i=0; i<3; i++) {
            sideLength[i][0] = Math.sqrt(Math.pow((element.node[i+1].x - element.node[i].x),2) + Math.pow((element.node[i+1].y - element.node[i].y),2));
            sideLength[i][1] = sideLength[i][0] /2.0;
        }
        sideLength[3][0] = Math.sqrt(Math.pow((element.node[3].x - element.node[0].x),2) + Math.pow((element.node[3].y - element.node[0].y),2));
        sideLength[3][1] = sideLength[3][0] / 2.0;

        //wypelniamy rozkad KSI ETA DO LICZENIA POW:
        ksiEta[0]= new double[]{(-1/Math.sqrt(3)),(1/Math.sqrt(3)), 1, 1, (1/Math.sqrt(3)), (-1/Math.sqrt(3)), -1, -1};
        ksiEta[1]= new double[]{-1, -1, (-1/Math.sqrt(3)), (1/Math.sqrt(3)), 1, 1, (1/Math.sqrt(3)), (-1/Math.sqrt(3))};

        //liczymy funkcje kształtu ndla povierchni:
        sumZeros();
//pow1
        pcZeros();
        pcN1[0][0] = 0.25*((1-ksiEta[0][0])*(1-ksiEta[1][0]));
        pcN1[0][1] = 0.25*((1+ksiEta[0][0])*(1-ksiEta[1][0]));
        pcN1[0][2] = 0.25*((1+ksiEta[0][0])*(1+ksiEta[1][0]));
        pcN1[0][3] = 0.25*((1-ksiEta[0][0])*(1+ksiEta[1][0]));

        pcN1[1][0] = 0.25*((1-ksiEta[0][1])*(1-ksiEta[1][1]));
        pcN1[1][1] = 0.25*((1+ksiEta[0][1])*(1-ksiEta[1][1]));
        pcN1[1][2] = 0.25*((1+ksiEta[0][1])*(1+ksiEta[1][1]));
        pcN1[1][3] = 0.25*((1-ksiEta[0][1])*(1+ksiEta[1][1]));

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                pc1[i][j] = pcN1[0][i] * pcN1[0][j] * fileData.alfa;
                pc2[i][j] = pcN1[1][i] * pcN1[1][j] * fileData.alfa;
                sum1[i][j] = sideLength[0][1]*(pc1[i][j]+pc2[i][j]);
            }
            //doliczamy sobie sumy do sumek pod wektor P
            sum1p[i] = (pcN1[0][i] + pcN1[1][i])*sideLength[0][1];
        }
//pow2
        pcZeros();
        pcN2[0][0] = 0.25*((1-ksiEta[0][2])*(1-ksiEta[1][2]));
        pcN2[0][1] = 0.25*((1+ksiEta[0][2])*(1-ksiEta[1][2]));
        pcN2[0][2] = 0.25*((1+ksiEta[0][2])*(1+ksiEta[1][2]));
        pcN2[0][3] = 0.25*((1-ksiEta[0][2])*(1+ksiEta[1][2]));

        pcN2[1][0] = 0.25*((1-ksiEta[0][3])*(1-ksiEta[1][3]));
        pcN2[1][1] = 0.25*((1+ksiEta[0][3])*(1-ksiEta[1][3]));
        pcN2[1][2] = 0.25*((1+ksiEta[0][3])*(1+ksiEta[1][3]));
        pcN2[1][3] = 0.25*((1-ksiEta[0][3])*(1+ksiEta[1][3]));

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                pc1[i][j] = pcN2[0][i] * pcN2[0][j] * fileData.alfa;
                pc2[i][j] = pcN2[1][i] * pcN2[1][j] * fileData.alfa;
                sum2[i][j] = sideLength[1][1]*(pc1[i][j]+pc2[i][j]);
            }
            //doliczamy sobie sumy do sumek pod wektor P
            sum2p[i] = (pcN2[0][i] + pcN2[1][i])*sideLength[1][1];
        }
//pow3
        pcZeros();
        pcN3[0][0] = 0.25*((1-ksiEta[0][4])*(1-ksiEta[1][4]));
        pcN3[0][1] = 0.25*((1+ksiEta[0][4])*(1-ksiEta[1][4]));
        pcN3[0][2] = 0.25*((1+ksiEta[0][4])*(1+ksiEta[1][4]));
        pcN3[0][3] = 0.25*((1-ksiEta[0][4])*(1+ksiEta[1][4]));

        pcN3[1][0] = 0.25*((1-ksiEta[0][5])*(1-ksiEta[1][5]));
        pcN3[1][1] = 0.25*((1+ksiEta[0][5])*(1-ksiEta[1][5]));
        pcN3[1][2] = 0.25*((1+ksiEta[0][5])*(1+ksiEta[1][5]));
        pcN3[1][3] = 0.25*((1-ksiEta[0][5])*(1+ksiEta[1][5]));

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                pc1[i][j] = pcN3[0][i] * pcN3[0][j] * fileData.alfa;
                pc2[i][j] = pcN3[1][i] * pcN3[1][j] * fileData.alfa;
                sum3[i][j] = sideLength[2][1]*(pc1[i][j]+pc2[i][j]);
            }
            //doliczamy sobie sumy do sumek pod wektor P
            sum3p[i] = (pcN3[0][i] + pcN3[1][i])*sideLength[2][1];
        }
//pow4
        pcZeros();
        pcN4[0][0] = 0.25*((1-ksiEta[0][6])*(1-ksiEta[1][6]));
        pcN4[0][1] = 0.25*((1+ksiEta[0][6])*(1-ksiEta[1][6]));
        pcN4[0][2] = 0.25*((1+ksiEta[0][6])*(1+ksiEta[1][6]));
        pcN4[0][3] = 0.25*((1-ksiEta[0][6])*(1+ksiEta[1][6]));

        pcN4[1][0] = 0.25*((1-ksiEta[0][7])*(1-ksiEta[1][7]));
        pcN4[1][1] = 0.25*((1+ksiEta[0][7])*(1-ksiEta[1][7]));
        pcN4[1][2] = 0.25*((1+ksiEta[0][7])*(1+ksiEta[1][7]));
        pcN4[1][3] = 0.25*((1-ksiEta[0][7])*(1+ksiEta[1][7]));

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                pc1[i][j] = pcN4[0][i] * pcN4[0][j] * fileData.alfa;
                pc2[i][j] = pcN4[1][i] * pcN4[1][j] * fileData.alfa;
                sum4[i][j] = sideLength[3][1]*(pc1[i][j]+pc2[i][j]);
            }
            //doliczamy sobie sumy do sumek pod wektor P
            // {P} = {N}T * S - alfa i temp otoczenia dodane później dla całego wektora P
            sum4p[i] = (pcN4[0][i] + pcN4[1][i])*sideLength[3][1];
        }

//        sprawdzanie warunków brzegowych i wpisywanie sum do HBC iiii do vektora P:
        if(element.node[0].status==1 && element.node[1].status==1){
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++) {
                    matrixHBC[i][j]+=sum1[i][j];
                }
                // dodawanie do vektora P:
                vectorP[i] += sum1p[i];
            }
        }

        if(element.node[1].status==1 && element.node[2].status==1){
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++) {
                    matrixHBC[i][j]+=sum2[i][j];
                }
                // dodawanie do vektora P:
                vectorP[i] += sum2p[i];
            }
        }

        if(element.node[2].status==1 && element.node[3].status==1){
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++) {
                    matrixHBC[i][j]+=sum3[i][j];
                }
                // dodawanie do vektora P:
                vectorP[i] += sum3p[i];
            }
        }

        if(element.node[3].status==1 && element.node[0].status==1){
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++) {
                    matrixHBC[i][j]+=sum4[i][j];
                }
                // dodawanie do vektora P:
                vectorP[i] += sum4p[i];
            }
        }

        //przemnożenia wektora P przez alfa i ambient temp:
        for(int i=0; i<4; i++){
            // {P} = {P} * alfa * T alfa<-- znana temperatura (przez S zostało pomnożone wcześniej)
            vectorP[i] = vectorP[i] * fileData.alfa * fileData.ambientTemperature;
//            vectorP[i] = vectorP[i] * fileData.alfa * 60.0;
        }
    }



    //------------------------------------------------------------ METODY ----------------------------------------------
     void showVectorP(){
        System.out.println("--------Początek Vector P----------------------\n");
        for(int i=0; i<4; i++){
            System.out.print(vectorP[i] + " \t");
        }
        System.out.println("\n--------Koniec Vector P-----------------------------------------\n");
    }
    void showSumP(){
        System.out.println("--------Początek sum1p----------------------\n");
        for(int i=0; i<4; i++){
            System.out.print(sum1p[i] + " \t");
        }
        System.out.println("\n--------Koniec sum1p-----------------------------------------\n");

        System.out.println("--------Początek sum2p----------------------\n");
        for(int i=0; i<4; i++){
            System.out.print(sum2p[i] + " \t");
        }
        System.out.println("\n--------Koniec sum2p-----------------------------------------\n");

        System.out.println("--------Początek sum3p----------------------\n");
        for(int i=0; i<4; i++){
            System.out.print(sum3p[i] + " \t");
        }
        System.out.println("\n--------Koniec sum3p-----------------------------------------\n");

        System.out.println("--------Początek sum4p----------------------\n");
        for(int i=0; i<4; i++){
            System.out.print(sum4p[i] + " \t");
        }
        System.out.println("\n--------Koniec sum4p-----------------------------------------\n");
    }
    void showMatrixHBC(){
        System.out.println("--------Początek Matrix H BC----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) {
                System.out.print(matrixHBC[i][j] + " \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Matrix H BC-----------------------------------------\n");
    }
    void showSum(){
        System.out.println("--------Początek sum1----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) {
                System.out.print(sum1[i][j] + " \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec sum1-----------------------------------------\n");

        System.out.println("--------Początek sum2----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) {
                System.out.print(sum2[i][j] + " \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec sum2-----------------------------------------\n");

        System.out.println("--------Początek sum3----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) {
                System.out.print(sum3[i][j] + " \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec sum3-----------------------------------------\n");

        System.out.println("--------Początek sum4----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) {
                System.out.print(sum4[i][j] + " \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec sum4-----------------------------------------\n");
    }
    void sumZeros(){
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                sum1[i][j]=0;
                sum2[i][j]=0;
                sum3[i][j]=0;
                sum4[i][j]=0;
                sum1p[i]=0;
                sum2p[i]=0;
                sum3p[i]=0;
                sum4p[i]=0;
                vectorP[i]=0;
                matrixHBC[i][j] = 0;
            }
        }
    }
    void pcZeros(){
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                pc1[i][j]=0;
                pc2[i][j]=0;
            }
        }
    }
    void showKsiEta(){
        System.out.println("--------Początek KsiEta----------------------\n");
        System.out.print("KSI: ");
        for(int i=0; i<2; i++){
            for(int j=0; j<8; j++) {
                System.out.print(ksiEta[i][j] + " \t");
            }
            System.out.println();
            if(i==0)System.out.print("ETA: ");
        }
        System.out.println("\n--------Koniec KsiEta-----------------------------------------\n");
    }
    void showSideLength(){
        System.out.println("--------Początek Side Length----------------------\n");
        for(int i=0; i<4; i++){
                System.out.print("side length: "+sideLength[i][0]+" \t DetJ: "+sideLength[i][1]);
            System.out.println();
        }
        System.out.println("\n--------Koniec Side Length-----------------------------------------\n");
    }
    void showMatrixHLocal(){
        System.out.println("--------Początek Matrix H Local----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(matrixHLocal[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec Matrix H Local-----------------------------------------\n");
    }

    void showFirstPCDNdxdNdxT(){
        System.out.println("--------Początek First PC {dN/dx}{dN/dx}T----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(firstPCDNdxdNdxT[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec First PC {dN/dx}{dN/dx}T-----------------------------------------\n");
    }

    void showFirstPCDNdydNdyT(){
        System.out.println("--------Początek First PC {dN/dy}{dN/dy}T----------------------\n");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(firstPCDNdydNdyT[i][j]+" \t");
            }
            System.out.println();
        }
        System.out.println("\n--------Koniec First PC {dN/dy}{dN/dy}T-----------------------------------------\n");
    }
}
