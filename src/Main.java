public class Main {
    public static void main(String[] args) {

//        Uniwersal element test ---------------------------------------------
//        Universal_Element universal_element=new Universal_Element();
//        universal_element.showKsiEta();
//        universal_element.showdN();
//        universal_element.showdNde();
//        universal_element.showdNdn();
//        universal_element.showAll();
//        koniec Univerwsal element test -------------------------------------


//        File Data ----------------------------------------------------------
//        FileData fileData = new FileData();
//        fileData.printData();
//        koniec File Data ---------------------------------------------------


//        Single Element ----------------------------------------------------------
//        Element element = new Element(0,0,fileData);
//        element.showElement();
//        koniec Single Element ----------------------------------------------------------

//        Gride Elements and nodes ----------------------------------------------------------
//        Fem_Gride fem_gride = new Fem_Gride();
//        fem_gride.setIDNodes();
//        fem_gride.showElements();
//        fem_gride.showNodes();
//        fem_gride.showOverview();
//        koniec Gride Elements and nodes ----------------------------------------------------------




//        Jakobian ----------------------------------------------------------------------------------------
//        Element Testowy ----------------------------------------------------------
//        FileData fileDataTestowy = new FileData();
//        fileDataTestowy.dB = 0.025;
//        fileDataTestowy.dH = 0.025;
//        Element elementTestowy = new Element(0,0,fileDataTestowy);
//        elementTestowy.showElement();
//        koniec  Element Testowy ----------------------------------------------------------

//        Jakobian2D jakobian2D = new Jakobian2D(elementTestowy);
//        jakobian2D.showJakobian();
//        jakobian2D.showDetJ();
//        jakobian2D.showJakobianDet();
//        jakobian2D.showdNdx();
//        jakobian2D.showdNdy();
//        koniec  Jakobian ------------------------------------------------------------------------------------

//        MatrixCLocal matrixCLocal = new MatrixCLocal(elementTestowy,jakobian2D,fileDataTestowy);
//        matrixCLocal.showMatrixCLocal();

//        MatrixHLocal matrixHLocal = new MatrixHLocal(elementTestowy,jakobian2D,fileDataTestowy);
//        MatrixHLocal matrixHLocal = new MatrixHLocal(fem_gride.elements[0],jakobian2D,fileData);
//        matrixHLocal.showMatrixHLocal();
//        matrixHLocal.showSideLength();
//        matrixHLocal.showKsiEta();
//        matrixHLocal.showSum();
//        matrixHLocal.showMatrixHBC();
//        matrixHLocal.showSumP();
//        matrixHLocal.showVectorP();

        // MATRIX H Global -----------------------------------------------------------------------------------
//        MatrixHGlobal matrixHGlobal = new MatrixHGlobal(fileData);
//      matrixHGlobal.agregateHLocalToHGlocal(matrixHLocal);
//      matrixHGlobal.showMatrixHGlobal();

//        TestZliczGridH testZliczGridH = new TestZliczGridH();
        Simulation simulation = new Simulation();
//        Fem_Gride fem_gride = new Fem_Gride();
//        fem_gride.showNodes();
    }
}
