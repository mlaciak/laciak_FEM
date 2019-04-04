import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileData {

//    nH (nH)- liczba węzłów na wysokość siatki
//    nB (nB)- liczba węzłów na szerokość siatki
//    H (H)- wysokość przekroju
//    B (B)- szerokość przekroju
//    nh (nh) - liczba węzłów
//    ne (ne) - liczba elementów
//    dH, dB (dH, dB)- odległości między węzłami

    int nh;
    int ne;
    int simulationTime;
    int simulationStepTime;
    int n_H;
    int n_B;
    double dH, dB;
    double initialTemperature;
    double ambientTemperature;
    double alfa;
    double H;
    double B;
    double specificHeat;
    double conductivity;
    double density;

    public FileData() {
        try {
            Scanner scanner = new Scanner(new File("FEM_DATA.txt"));
            this.initialTemperature = scanner.nextDouble(); scanner.nextLine();
            this.simulationTime = scanner.nextInt(); scanner.nextLine();
            this.simulationStepTime = scanner.nextInt(); scanner.nextLine();
            this.ambientTemperature = scanner.nextDouble(); scanner.nextLine();
            this.alfa = scanner.nextDouble(); scanner.nextLine();
            this.H = scanner.nextDouble(); scanner.nextLine();
            this.B = scanner.nextDouble(); scanner.nextLine();
            this.n_H = scanner.nextInt(); scanner.nextLine();
            this.n_B = scanner.nextInt(); scanner.nextLine();
            this.specificHeat = scanner.nextDouble(); scanner.nextLine();
            this.conductivity = scanner.nextDouble(); scanner.nextLine();
            this.density = scanner.nextDouble();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        nh = n_H*n_B; // Ilość węzłów
        ne = (n_H - 1)*(n_B - 1); // ilość elementów
        dB = B / (n_B  - 1); // odległości między węzłami po szerokości
        dH = H / (n_H - 1); // odległości między węzłami po wysokości
    }

    public FileData(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            this.initialTemperature = scanner.nextDouble(); scanner.nextLine();
            this.simulationTime = scanner.nextInt(); scanner.nextLine();
            this.simulationStepTime = scanner.nextInt(); scanner.nextLine();
            this.ambientTemperature = scanner.nextDouble(); scanner.nextLine();
            this.alfa = scanner.nextDouble(); scanner.nextLine();
            this.H = scanner.nextDouble(); scanner.nextLine();
            this.B = scanner.nextDouble(); scanner.nextLine();
            this.n_H = scanner.nextInt(); scanner.nextLine();
            this.n_B = scanner.nextInt(); scanner.nextLine();
            this.specificHeat = scanner.nextDouble(); scanner.nextLine();
            this.conductivity = scanner.nextDouble(); scanner.nextLine();
            this.density = scanner.nextDouble();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        nh = n_H*n_B; // Ilość węzłów
        ne = (n_H - 1)*(n_B - 1); // ilość elementów
        dB = B / (n_B  - 1); // odległości między węzłami po szerokości
        dH = H / (n_H - 1); // odległości między węzłami po wysokości
    }

    public void printData(){
        System.out.println("" +
                "-----------------------PROCESS PARAMETERS--------------------\n" +
                "Initial Temperature: "+initialTemperature+" [C]\n" +
                "Simulation Time: "+simulationTime+" [s]\n" +
                "Simulation Step Time: "+simulationStepTime+" [s]\n" +
                "Ambient Temperature: "+ambientTemperature+" [C]\n" +
                "Alfa: "+alfa+" [W/m^2k]\n" +
                "H (Height of gauge): "+H+" [m]\n" +
                "B (Width of gauge): "+B+" [m]\n" +
                "n_H (Number of nodes - Height): "+n_H+"\n" +
                "n_B (Number of nodes - Width): "+n_B+"\n" +
                "Specific Heat: "+specificHeat+" [J/kg*C]\n" +
                "Conductivity: "+conductivity+" [W/m*C]\n" +
                "Density: "+density+" [kg/m^3]\n" +
                "Number of nodes (combined): "+nh+"\n" +
                "Number of elements: "+ne+"\n" +
                "Distance between nodes horisontal: "+dB+"\n" +
                "Distance between nodes vertival: "+dH+"\n" +
                "-------------------------------------------------------------");
    }
}
