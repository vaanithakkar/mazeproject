public class Space {
    private String letter;

    private Neighbor[] neighbors;
    private int neighborCount;

    private boolean visited;
    private Space previous;

    public class Neighbor{
        Space node;
        int distance;

        public Neighbor (Space node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }


    public Space(String letter) {
        this.letter = letter;

        neighbors = new Neighbor[4];
        neighborCount = 0;

        visited = false;
        previous = null;
    }

    public String getLetter() {
        return letter;
    }

    public void addNeighbor(Space s, int distance){
        if (neighborCount == neighbors.length){
            Neighbor[]bigger = new Neighbor[neighbors.length * 2];

            for(int i = 0; i < neighborCount; i++){
                bigger[i] = neighbors[i];
            }

            neighbors = bigger;
        }

        neighbors[neighborCount++] = new Neighbor (s, distance);
    }

    public Neighbor[] getNeighbors(){
        return neighbors;
    }

    public int getNeighborCount(){
        return neighborCount;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setVisited(boolean v){
        visited = v;
    }

    public Space getPrevious(){
        return previous;
    }

    public void setPrevious(Space s){
        previous = s;
    }

}