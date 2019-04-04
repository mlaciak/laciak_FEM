import java.util.Scanner;

public class Fem_Gride {
    Node[] nodes;
    Element[] elements;
    FileData fileData;
    int typ;
    FileData miedz = new FileData(); // miedź
    FileData nikiel = new FileData("FEM_DATA2.txt"); //nikiel
    FileData kryonaut = new FileData("FEM_DATA3.txt"); //thermal gryzlly
    FileData air = new FileData("FEM_DATA4.txt"); //powietrze
    FileData conductonaut = new FileData("FEM_DATA5.txt"); //ciekly metal

    public Fem_Gride(int typ){
//        System.out.println("thermal grizzly kryonaut: 1\n" +
//                "liquid metal: 2\n" +
//                "air: 3");
//        Scanner scanner = new Scanner(System.in);
//        typ = scanner.nextInt();
        this.typ=typ;
        fileData = new FileData();

        elements = new Element[fileData.ne]; //Tworzymy tablicę elementów dla ilości (ne) <-- number _of_ elements
        nodes = new Node[fileData.nh];

        int pom=0;
        for(int i=0; i<(fileData.n_H - 1); i++){
            for(int j=0; j<(fileData.n_B - 1); j++){

                if(pom>998){ fileData=nikiel; }
                if(pom<=998) { fileData=miedz; }
////                if(pom>872 && pom<889 && typ == 1){ fileData=kryonaut; }
////                if(pom>869 && pom<900 && typ == 2){ fileData=conductonaut; }
////                if(pom>869 && pom<900 && typ == 3){ fileData=air; }
//
//                if((pom==887 || pom==888) && typ == 1){ fileData=kryonaut; }
                if((pom>1341 && pom<=1358) && typ == 1){ fileData=kryonaut; }
                if((pom>1341 && pom<=1358) && typ == 2){ fileData=conductonaut; }
                if((pom>1341 && pom<=1358) && typ == 3){ fileData=air; }

                elements[pom++] = new Element(i * fileData.dB, j * fileData.dH, fileData);
            }
        }

        pom=0;
        for(int i=0; i<(fileData.n_H); i++){
            for(int j=0; j<(fileData.n_B); j++){

                if(pom>1063){ fileData=nikiel; }
                if(pom<=1063) { fileData=miedz; }
////                if(pom>898 && pom<961 && typ == 1){ fileData=kryonaut; }
////                if(pom>898 && pom<961 && typ == 2){ fileData=conductonaut; }
////                if(pom>898 && pom<961 && typ == 3){ fileData=air; }
//
//                if((pom==916 || pom==917 || pom==918 || pom==919 || pom==947 || pom==948) && typ == 1){ fileData=kryonaut; }
                if(((pom>1377 && pom<=1395) || ((pom>1415 && pom<=1433))) && typ == 1){ fileData=kryonaut; }
                if((pom>1367 && pom<=1443) && typ == 2){ fileData=conductonaut; }
                if((pom>1367 && pom<=1443) && typ == 3){ fileData=air; }

                nodes[pom++] = new Node(i * fileData.dB, j * fileData.dH, fileData);
            }
        }
        setIDNodes();
    }

    public void showOverview(){
        System.out.println("ELEMENTS: \n" +
                "|----|----|----|\n" +
                "| 3  | 6  | 9  |\n" +
                "|----|----|----|\n" +
                "| 2  | 5  | 8  |\n" +
                "|----|----|----|\n" +
                "| 1  | 4  | 7  |\n" +
                "|----|----|----|\n");

        System.out.println("NODES: \n" +
                "3----7----11---15\n" +
                "|    |    |    |\n" +
                "2----6----10---14\n" +
                "|    |    |    |\n" +
                "1----5----9----13\n" +
                "|    |    |    |\n" +
                "0----4----8----12\n");
    }

    void setIDNodes(){
        for(int i=0; i<fileData.ne;i++) {
            for (int j = 0; j < 4; j++) {
                for(int z=0; z<fileData.nh;z++){
                    if(elements[i].node[j].x==nodes[z].x && elements[i].node[j].y==nodes[z].y){elements[i].node[j].ID=z;}
                }
            }
        }
    }
    void setTempNodes(double[] tempDTal){
        for(int i=0; i<fileData.ne;i++) {
            for (int j = 0; j < 4; j++) {
                for(int z=0; z<fileData.nh;z++){
                    if(elements[i].node[j].x==nodes[z].x && elements[i].node[j].y==nodes[z].y){
//                        if(tempDTal[z]>60) {
//                            elements[i].node[j].nodeTemp=60;
//                            nodes[z].nodeTemp=60;
//                        }else {
                            elements[i].node[j].nodeTemp = tempDTal[z];
                            nodes[z].nodeTemp = tempDTal[z];
//                        }
                    }
                }
            }
        }
    }

    public void showElements(){
        for (int i=0; i<fileData.ne; i++){
            System.out.println("Element numer : "+(i+1));
            elements[i].showElement();
            System.out.println("Koniec element numer: "+(i+1)+'\n');
        }
    }

    public void showNodes() {
        for (int i = 0; i < fileData.nh; i++) {
            System.out.println("Wezel numer : " + (i));
            nodes[i].showNode();
            System.out.println("Koniec wezla numer: " + (i) + '\n');
        }
    }
}
