public class Space {
    private String letter;
    private int row;
    private int column;
    private Space up;
    private Space down;
    private Space left;
    private Space right;
    private Space[] neighbors;
    private int neighborCount;
    public Space(String letter, int row, int column) {
        this.letter = letter;
        this.row = row;
        this.column = column;

        neighbors = new Space[4];
        neighborCount = 0;
    }
    public String getType() {
        return letter;
    }
    public boolean isBlocked() {
        return letter == "X" || letter == "H";
    }
    public void addNeighbor(Space s){
        if (neighborCount<4){
            neighbors[neighborCount++] = s;
        }
    };
    public void up(Space s){
        up = s;
    }
    public void down(Space s){
        down = s;
    }
    public void left(Space s){
        left = s;
    }
    public void right(Space s){
        right = s;
    }
    public Space goUp(){
        return up;
    }
    public Space goDown(){
        return down;
    }
    public Space goLeft(){
        return left;
    }
    public Space goRight(){
        return right;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
}