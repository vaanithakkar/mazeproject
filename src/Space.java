public class Space {
    private char letter;
    private int row;
    private int column;
    private Space up;
    private Space down;
    private Space left;
    private Space right;
    public Space(char letter, int row, int column) {
        this.letter = letter;
        this.row = row;
        this.column = column;
    }
    public char getType() {
        return letter;
    }
    public boolean isBlocked() {
        return letter == 'X' || letter == 'H';
    }
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