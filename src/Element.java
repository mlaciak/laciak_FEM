public class Element {
    public Node[] node;
    double x,y;

    public Element(double x, double y, FileData fileData) {
        this.x = x;
        this.y = y;
        node = new Node[4];

        node[0] = new Node(x, y, fileData);
        node[1] = new Node(x + fileData.dB, y, fileData);
        node[2] = new Node(x + fileData.dB, y+fileData.dH, fileData);
        node[3] = new Node(x, y + fileData.dH, fileData);
    }

    public void showElement(){
        System.out.println("--------Początek Elemnt----------------------");
        System.out.println("Element grid reference x: "+x+", y: "+y);
        System.out.println("Node[0], x: "+node[0].x+", y: "+node[0].y+", boundary condition: "+node[0].status+", node temp: "+node[0].nodeTemp);
        System.out.println("Node[1], x: "+node[1].x+", y: "+node[1].y+", boundary condition: "+node[1].status+", node temp: "+node[1].nodeTemp);
        System.out.println("Node[2], x: "+node[2].x+", y: "+node[2].y+", boundary condition: "+node[2].status+", node temp: "+node[2].nodeTemp);
        System.out.println("Node[3], x: "+node[3].x+", y: "+node[3].y+", boundary condition: "+node[3].status+", node temp: "+node[3].nodeTemp);
        System.out.println("Node[0], ID: "+node[0].ID);
        System.out.println("Node[1], ID: "+node[1].ID);
        System.out.println("Node[2], ID: "+node[2].ID);
        System.out.println("Node[3], ID: "+node[3].ID);
        System.out.println("--------Koniec Element-----------------------------------------");
    }
}
