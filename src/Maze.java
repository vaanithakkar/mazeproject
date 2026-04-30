public class Maze {
    private Space start;
    private Space end;
    public Maze(Space start, Space end) {
        this.start = start;
        this.end = end;
    }
    public Space getStart() {
        return start;
    }
    public Space getEnd() {
        return end;
    }
}