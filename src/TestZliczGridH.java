public class TestZliczGridH {
    FileData fileData = new FileData();
    MatrixHGlobal matrixHGlobal = new MatrixHGlobal(fileData);
    MatrixCGlobal matrixCGlobal = new MatrixCGlobal(fileData);
    VectorPGlobal vectorPGlobal = new VectorPGlobal(fileData);
    Fem_Gride fem_gride;
    int typ=4;

    //fileData.ne
    public TestZliczGridH(int typ){
        fem_gride = new Fem_Gride(typ);
        for (int i=0; i<1;i++){

            Jakobian2D jakobian2D = new Jakobian2D(fem_gride.elements[i]);

            MatrixHLocal matrixHLocal = new MatrixHLocal(fem_gride.elements[i],jakobian2D,fileData);
            MatrixCLocal matrixCLocal = new MatrixCLocal(fem_gride.elements[i],jakobian2D,fileData);

            matrixHGlobal.agregateHLocalToHGlocal(matrixHLocal);
            matrixHGlobal.agregateHBCLocalToHGlocal(matrixHLocal);

            matrixCGlobal.agregateCLocalToCGlocal(matrixCLocal);

            matrixHLocal.showVectorP();
            matrixHLocal.showSumP();

            vectorPGlobal.agregatePLocalToPGlocal(matrixHLocal);
        }
        matrixHGlobal.agregateCdTGlobalToHGlocal(matrixCGlobal);
        vectorPGlobal.agregateCGlobaldTtZeroToPGlobal(matrixCGlobal, typ);
//        matrixHGlobal.showMatrixHGlobal();
//        matrixCGlobal.showMatrixCGlobal();
        vectorPGlobal.showVectorPGlobal();
    }
}
