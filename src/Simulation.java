import java.util.Scanner;

class Simulation {
    FileData fileData = new FileData();
    FileData miedz = new FileData(); // miedź
    FileData nikiel = new FileData("FEM_DATA2.txt"); //nikiel
    FileData kryonaut = new FileData("FEM_DATA3.txt"); //thermal gryzlly
    FileData air = new FileData("FEM_DATA4.txt"); //powietrze
    FileData conductonaut = new FileData("FEM_DATA5.txt"); //ciekly metal
    int typ;
    MatrixHGlobal matrixHGlobal = new MatrixHGlobal(fileData);
    MatrixCGlobal matrixCGlobal = new MatrixCGlobal(fileData);
    VectorPGlobal vectorPGlobal = new VectorPGlobal(fileData);
    Fem_Gride fem_gride;
    double[] tempDTal = new double[fileData.nh];
    int licznik;
//    double[][][] tempNodesSimulation = new double[fileData.nh][fileData.nh][fileData.nh];


    protected Simulation() {
        System.out.println("thermal grizzly kryonaut: 1\n" +
                "liquid metal: 2\n" +
                "air: 3");
        Scanner scanner = new Scanner(System.in);
        typ = scanner.nextInt();
        fem_gride=new Fem_Gride(typ);
//        typ=4;

        for (int i = 0; i < fileData.ne; i++) {

            if (i > 998) {
                fileData = nikiel;
            }
            if (i <= 998) {
                fileData = miedz;
            }
//
            if (typ == 1) {
//                if ((i==887 || i==888)) { // gryzli
                if ((i>1341 && i<=1358)) { // gryzli
                    fileData = kryonaut;
                }
            }

            if (typ == 2) {
                if ((i>1341 && i<=1358)) { // gryzli metal ciekly
                    fileData = conductonaut;
                }
            }

            if (typ == 3) {
                if ((i>1341 && i<=1358)) { // powierze
                    fileData = air;
                }
            }

            Jakobian2D jakobian2D = new Jakobian2D(fem_gride.elements[i]);
            MatrixHLocal matrixHLocal = new MatrixHLocal(fem_gride.elements[i], jakobian2D, fileData);
            MatrixCLocal matrixCLocal = new MatrixCLocal(fem_gride.elements[i], jakobian2D, fileData);
            matrixHGlobal.agregateHLocalToHGlocal(matrixHLocal);
            matrixHGlobal.agregateHBCLocalToHGlocal(matrixHLocal);
            matrixCGlobal.agregateCLocalToCGlocal(matrixCLocal);
            vectorPGlobal.agregatePLocalToPGlocal(matrixHLocal);

        }
        matrixHGlobal.agregateCdTGlobalToHGlocal(matrixCGlobal);
        vectorPGlobal.agregateCGlobaldTtZeroToPGlobal(matrixCGlobal, typ);

//        fem_gride.showNodes();
        for (int i = 0; i < fileData.nh; i++) {
            if (i % fileData.n_B == 0) System.out.println();
            System.out.format("%.2f\t", fem_gride.nodes[i].nodeTemp);

        }
        // symulacja:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // symulacja:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // symulacja:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        GaussSolver gaussSolver = new GaussSolver();
//        System.out.println("Time[s] \t MinTemp[s] \t MaxTemp[s]\n");
        for (int i = 0; i < fileData.simulationTime; i += fileData.simulationStepTime) { //------------------------------------------------------------------------------

            tempDTal = gaussSolver.gaussSolver(matrixHGlobal, vectorPGlobal, fileData);

            //wypelnienie temp node nowymi temperaturami
            fem_gride.setTempNodes(tempDTal);

            //wyzerowanie vektora P globalnego:
            vectorPGlobal.zerosVectorP();

            //ustawiamy nowy wektor P:
            for (int j = 0; j < fileData.ne; j++) {
                if (j > 998) {
                    fileData = nikiel;
                }
                if (j <= 998) {
                    fileData = miedz;
                }
//
                if (typ == 1) {
//                    if ((j==887 || j==888) ) { // gryzli
                    if ((j>1341 && j<=1358)) { // gryzli
                        fileData = kryonaut;
                    }
                }

                if (typ == 2) {
                    if ((j>1341 && j<=1358)) { // gryzli metal ciekly
                        fileData = conductonaut;
                    }
                }

                if (typ == 3) {
                    if ((j>1341 && j<=1358)) { // powierze
                        fileData = air;
                    }
                }

                Jakobian2D jakobian2D = new Jakobian2D(fem_gride.elements[j]);
                MatrixHLocal matrixHLocal = new MatrixHLocal(fem_gride.elements[j], jakobian2D, fileData);
                vectorPGlobal.agregatePLocalToPGlocal(matrixHLocal);

//                System.out.println(tempDTal[j]);
            }

            // wrzucamy do wzoru:
            //{P} = {P} + {[C] / dTał} * {Tn} <---- gdzie Tn to tempDTal z gaussa!
            vectorPGlobal.agregateCGlobaldTtFromGAUSSToPGlobal(matrixCGlobal, tempDTal);
//            vectorPGlobal.showVectorPGlobal();

            //wypisanie temperatur [min] [max]:
//            double[] sort = tempDTal;
//            Arrays.sort(sort);
//            System.out.println((i+50)+"  \t"+sort[0]+" \t"+sort[sort.length-1]);
//            fem_gride.elements[0].showElement();


//            int licznik=0;
            //TODO aaaaa
            System.out.println();
            for (int x = 0; x < fileData.nh; x++) {
                if (x % fileData.n_H == 0) {
                    System.out.println();
                }
                System.out.format("%.2f\t", fem_gride.nodes[x].nodeTemp); //wypisywanie
            }

//            if (licznik==960) {
//                System.out.println("\n\nczas sumulacji "+(i+fileData.simulationStepTime) + "[s]");
//                i=fileData.simulationTime-fileData.simulationStepTime;
//                break;
//            }

//            if(licznik==960)
//                i=fileData.simulationTime;//-fileData.simulationStepTime;

            licznik = 0;
            for (int z = 0; z < fileData.nh; z++) {
                if ((fem_gride.nodes[z].nodeTemp) >= 60) {
                    licznik++;
                }
            }
            if (licznik >= 1443) {
//                    i = fileData.simulationTime - fileData.simulationStepTime;
                System.out.println("\n\nSimulation time: " + (i + fileData.simulationStepTime) + "[s]");
                System.out.println();
                if(typ==1){ System.out.println("thermal grizzly kryonaut");}
                if(typ==2){ System.out.println("liquid metal");}
                if(typ==3){ System.out.println("air");}

            }
//            System.out.println(licznik);
            if (licznik >= 1443) break;

        }
//        System.out.println();
//        for(int i=0;i<fileData.nh;i++){
//            if(i%fileData.n_B==0) System.out.println();
//            System.out.format("%.2f\t",fem_gride.nodes[i].nodeTemp);
//
//        }

    }
}