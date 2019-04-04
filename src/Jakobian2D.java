import java.text.DecimalFormat;

public class Jakobian2D {
    Universal_Element universal_element;
     double[][] Jakobian;
     double[] detJ;
     double[][] JakobianDet;
     double[][] dNdx;
     double[][] dNdy;

    public Jakobian2D(Element element){
        universal_element = new Universal_Element();
        Jakobian = new double[4][4];
        detJ = new double[4];
        JakobianDet = new double[4][4];
        dNdx = new double[4][4];
        dNdy = new double[4][4];

        //dNde <-- dN / dEta ..... dNdn <--- dN / dKsi !!!!!!!!
        for(int i=0; i<4; i++){
            // dla x'a z dN / dKsi
            Jakobian[0][i] = universal_element.dNdn[0][i] * element.node[0].x + universal_element.dNdn[1][i] * element.node[1].x
                    + universal_element.dNdn[2][i] * element.node[2].x + universal_element.dNdn[3][i] * element.node[3].x;
//            System.out.println(Jakobian[0][i]);
            // dla y'a z dN / dKsi
            Jakobian[1][i] = universal_element.dNdn[0][i] * element.node[0].y + universal_element.dNdn[1][i] * element.node[1].y
                    + universal_element.dNdn[2][i] * element.node[2].y + universal_element.dNdn[3][i] * element.node[3].y;
//            System.out.println(Jakobian[1][i]);
            // dla x'a z dN / dEta
            Jakobian[2][i] = universal_element.dNde[0][i] * element.node[0].x + universal_element.dNde[1][i] * element.node[1].x
                    + universal_element.dNde[2][i] * element.node[2].x + universal_element.dNde[3][i] * element.node[3].x;
//            System.out.println(Jakobian[2][i]);
            // dla y'a z dN / dEta
            Jakobian[3][i] = universal_element.dNde[0][i] * element.node[0].y + universal_element.dNde[1][i] * element.node[1].y
                    + universal_element.dNde[2][i] * element.node[2].y + universal_element.dNde[3][i] * element.node[3].y;
//            System.out.println(Jakobian[3][i]);
        }

        for(int i=0; i<4; i++) {
            detJ[i] = 1.0*((Jakobian[0][i] * Jakobian[3][i]) - (Jakobian[1][i] * Jakobian[2][i]));
        }

        for(int i=0; i<4; i++){
            JakobianDet[0][i] = Jakobian[3][i] / detJ[i];
            JakobianDet[1][i] = Jakobian[1][i] / detJ[i];
            JakobianDet[2][i] = Jakobian[2][i] / detJ[i];
            JakobianDet[3][i] = Jakobian[0][i] / detJ[i];
        }

//z excela (matrix_H)
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                dNdx[j][i] = JakobianDet[0][i] * universal_element.dNdn[i][j] + JakobianDet[1][i] * universal_element.dNde[i][j];
                dNdy[j][i] = JakobianDet[2][i] * universal_element.dNdn[i][j] + JakobianDet[3][i] * universal_element.dNde[i][j];
            }
        }
    }

    //------------------------------------------------------------------------------------METODY !!!!!!!!!!!!!! --------
    public void showdNdx(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00000");
        System.out.println("--------Początek dNdx----------------------");
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                System.out.print(decimalFormat.format(dNdx[i][j])+" \t");
            }
            System.out.println();
        }
        System.out.println("--------Koniec dNdx-----------------------------------------\n");
    }

    public void showdNdy(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00000");
        System.out.println("--------Początek dNdy----------------------");
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                System.out.print(decimalFormat.format(dNdy[i][j])+" \t");
            }
            System.out.println();
        }
        System.out.println("--------Koniec dNdy-----------------------------------------\n");
    }

    public void showJakobianDet(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00000");
        System.out.println("--------Początek Jakobian Det----------------------");
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                System.out.print(decimalFormat.format(JakobianDet[i][j])+" \t");
            }
            System.out.println();
        }
        System.out.println("--------Koniec Jakobian Det-----------------------------------------\n");
    }

    public void showDetJ(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00000");
        System.out.println("--------Początek DetJ----------------------");
        for(int i=0; i<4; i++) {
            System.out.print(decimalFormat.format(detJ[i]) + " \t");
        }
        System.out.println("\n--------Koniec DetJ-----------------------------------------\n");
    }

    public void showJakobian(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00000");
        System.out.println("--------Początek Jakobian----------------------");
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                System.out.print(decimalFormat.format(Jakobian[i][j])+" \t");
            }
            System.out.println();
        }
        System.out.println("--------Koniec Jakobian-----------------------------------------\n");
    }
}
