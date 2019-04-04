public class Node {
    double x,y;
    int status;
    int ID;
    double nodeTemp;
// status oznacza czy są zadawane warunki brzegowe czy nie
// B,H kolejno oznaczają szerokość przekroju, oraz wysokość przekroju

    public Node(double x, double y, FileData fileData) {
        this.x = x;
        this.y = y;
        this.nodeTemp = fileData.initialTemperature;

        //this.x == 0. || this.y == 0. || this.x >=fileData.B ||
        //this.y >=fileData.H
        // last this.x >=fileData.B //todo przywrócić stare :D
        if(this.x >=fileData.B){
            status=1;
        }else { status=0; }
    }

    public void showNode(){
        System.out.println("Node:, x: "+x+", y: "+y+", boundary condition: "+status+", node temp: "+nodeTemp);
    }
}
