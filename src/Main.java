public class Main {
    public static void main(String[] args) {

        String file = "sampleMaze1";
        if (file.contains("grid")) {
            Maze maze = MazeReader.readGridMaze(file);
        } else {
            Maze maze = MazeReader.readGraphMaze(file);
        }

    }
}