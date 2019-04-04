import static java.lang.Math.sqrt;

class Universal_Element {
//    stałe wartości ksi i eta!
    private static final double[] ksi = new double[]{((-1) / sqrt(3)), ((1) / sqrt(3)), ((1) / sqrt(3)), ((-1) / sqrt(3))};
    private static final double[] eta = new double[]{((-1) / sqrt(3)), ((-1) / sqrt(3)), ((1) / sqrt(3)), ((1) / sqrt(3))};

//--------------------------------------------------------------------------------------
//    Funkcje kształtu N(1)= 0.25*(1-ksi)*(1-eta)
//    Funkcje kształtu N(2)= 0.25*(1+ksi)*(1-eta)
//    Funkcje kształtu N(3)= 0.25*(1+ksi)*(1+eta)
//    Funkcje kształtu N(4)= 0.25*(1-ksi)*(1+eta)
    final double[][] dN = new double[4][4];

//    ------------------------------------------------------------------------------------
//    Pochodne po eta N1 / de = -0.25*(1-ksi)
//    Pochodne po eta N2 / de = -0.25*(1+ksi)
//    Pochodne po eta N3 / de = 0.25*(1+ksi)
//    Pochodne po eta N4 / de = 0.25*(1-ksi)
    final double[][] dNde = new double[4][4];

//    -------------------------------------------------------------------------------------
//    Pochodne po ksi N1 / dn = -0.25*(1-eta)
//    Pochodne po ksi N2 / dn = 0.25*(1-eta)
//    Pochodne po ksi N3 / dn = 0.25*(1+eta)
//    Pochodne po ksi N4 / dn = -0.25*(1+eta)
    final double[][] dNdn = new double[4][4];
//-----------------------------------------------------------------------------------------
public Universal_Element(){
//    Funkcje kształtu N1 do N4 (ze wzorów wyżej)
        for(int i=0; i<4; i++){
            dN[i][0]=0.25*((1.0-ksi[i])*(1.0-eta[i]));
            dN[i][1]=0.25*((1.0+ksi[i])*(1.0-eta[i]));
            dN[i][2]=0.25*((1.0+ksi[i])*(1.0+eta[i]));
            dN[i][3]=0.25*((1.0-ksi[i])*(1.0+eta[i]));
        }

//    Pochodne po eta
        for(int i=0; i<4; i++){
            dNde[0][i] = (-0.25*(1.0-ksi[i]));
            dNde[1][i] = (-0.25*(1.0+ksi[i]));
            dNde[2][i] = (0.25*(1.0+ksi[i]));
            dNde[3][i] = (0.25*(1.0-ksi[i]));
        }

//        Pochodna po ksi
        for(int i=0; i<4; i++){
            dNdn[0][i]=(-0.25*(1.0-eta[i]));
            dNdn[1][i]=(0.25*(1.0-eta[i]));
            dNdn[2][i]=(0.25*(1.0+eta[i]));
            dNdn[3][i]=(-0.25*(1.0+eta[i]));
        }
    }


    void showKsiEta(){
        System.out.println("--------Początek KsiEta-----------------");
        for (int k=0; k<4 ;k++) {
            System.out.println("ksi "+(k+1)+": "+ksi[k]);
        }
        System.out.println();
        for (int e=0; e<4; e++) {
            System.out.println("eta "+(e+1)+": "+eta[e]);
        }
        System.out.println("--------Koniec KsiEta------------------------------------\n");
    }

    void showdN(){
        System.out.println("--------Początek dN----------------------");
        for(int i=0; i<4; i++){
            System.out.println("---\nDla punktu całkowania: "+(i+1));
            for(int j=0; j<4; j++){
                System.out.println("Funkcja kształtu: "+(j+1)+" dN= "+dN[i][j]);

            }
            System.out.println();
//            Suma funkcji kształtu ma zawsze? DAĆ 1 !!!!!!!!!!!!
            double sumDoSprawdzenia=(dN[i][0]+dN[i][1]+dN[i][2]+dN[i][3]);
            System.out.println("Sprawdzenie, czy wychodzi 1?: "+String.format("%.2f",sumDoSprawdzenia)+"\n");
        }
        System.out.println("--------Koniec dN-----------------------------------------\n");
    }

    void showdNde(){
        System.out.println("--------Początek dNde (dN / dEta)---------------------");
        for(int i=0; i<4; i++){
            System.out.println("---\nNumer funkcji kształtu: "+(i+1));
            for(int j=0; j<4; j++){
                System.out.println("w punkcie całkowania: "+(j+1)+" dNde= "+dNde[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------Koniec dNde---------------------------------------\n");
    }

    void showdNdn(){
        System.out.println("--------Początek dNdn (dN / dKsi)---------------------");
        for(int i=0; i<4; i++){
            System.out.println("---\nNumer funkcji kształtu: "+(i+1));
            for(int j=0; j<4; j++){
                System.out.println("w punkcie całkowania: "+(j+1)+" dNdn= "+dNdn[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------Koniec dNdn----------------------------------------\n");
    }

    void showAll(){
        showKsiEta();
        showdN();
        showdNde();
        showdNdn();
    }
}
