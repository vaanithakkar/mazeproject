public class Maze {
    private Space[] spaces;
    private int size;

    private Space start;
    private Space end;


    //finish building constructor
    public Maze (int capacity) {
        spaces = new Space[capacity];
        size = 0;
    }

    public void addSpace (Space s) {
        spaces[size] = s;
        size++;
    }

    public Space findSpace(String letter) {
        for (int i=0; i<size; i++){
            if (spaces[i].getLetter().equals(letter)) {
                return spaces[i];
            }
        }
        return null;
    }

    public int getSize(){
        return size;
    }

    public Space[] getSpaces(){
        return spaces;
    }

    public void setStart(Space s){
        start = s;
    }

    public void setEnd(Space s){
        end = s;
    }

    public Space getStart(){
        return start;
    }

    public Space getEnd(){
        return end;
    }
}
