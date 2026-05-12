import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class MazeReader {
    private static Space[][] gridStorage;
    public static Maze readGridMaze (String filename){
        try {
            Scanner file = new Scanner(new File(filename));

            String[] lines = new String[100];
            int rowCount = 0;

            while (file.hasNextLine()) {
                lines[rowCount] = file.nextLine();
                rowCount++;
            }
            file.close();

            gridStorage = new Space[rowCount][];

            Maze maze = new Maze(1000);
            Space start = null;
            Space end = null;

            for (int r = 0; r < rowCount; r++){
                gridStorage [r] = new Space [lines[r].length()];

                for(int c = 0; c < lines[r].length(); c++){
                    char ch = lines[r].charAt(c);
                    if(ch != ',') {
                        Space s = new Space ("" + ch);
                        gridStorage[r][c] = s;
                        maze.addSpace(s);

                        if(ch == 'S') start = s;
                        if(ch == 'E') end = s;
                    }
                }
            }

            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < gridStorage[r].length; c++) {
                    Space current = gridStorage[r][c];
                    if (current == null) continue;
                    connectGridNeighbors(current, r, c);
                }
            }

            maze.setStart(start);
            maze.setEnd(end);

            return maze;


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return null;
    }

    private static void connectGridNeighbors(Space current, int r, int c) {
        if (canMoveUp(r,c))
            current.addNeighbor(gridStorage[r-1][c],1);
        if (canMoveDown(r,c))
            current.addNeighbor(gridStorage[r+1][c],1);
        if (canMoveLeft(r,c))
            current.addNeighbor(gridStorage[r][c-1],1);
        if (canMoveRight(r,c))
            current.addNeighbor(gridStorage[r][c+1],1);

    }

    private static boolean canMoveUp(int r, int c) {
        return r > 0 && gridStorage[r-1][c]!=null;
    }

    private static boolean canMoveDown (int r, int c){
        return r < gridStorage.length - 1 && gridStorage[r+1][c]!=null;
    }

    private static boolean canMoveLeft (int r, int c){
        return c > 0 && gridStorage[r][c-1] != null;
    }

    private static boolean canMoveRight (int r, int c){
        return c < gridStorage[r].length - 1 && gridStorage[r][c+1] != null;
    }

    public static Maze readGraphMaze (String filename){
        try {
            Scanner file = new Scanner(new File(filename));

            Maze maze = new Maze (1000);

            while (file.hasNextLine()) {

                String line = file.nextLine();
                String[] parts = line.split(",");

                String fromName = parts[0];
                String toName = parts[1];
                int distance = Integer.parseInt(parts[2]);

                Space from = getOrCreate(maze, fromName);
                Space to = getOrCreate(maze, toName);

                from.addNeighbor(to, distance);
                to.addNeighbor(from, distance);
            }

            file.close();

            return maze;

        } catch (FileNotFoundException e) {
            System.out.println("Graph file not found");
        }

        return null;
    }

    private static Space getOrCreate(Maze maze, String name) {
        Space s = maze.findSpace(name);

        if(s == null){
            s = new Space (name);
            maze.addSpace(s);
        }

        return s;
    }

    public static void runDijkstra (Maze maze) {
        Space start = maze.getStart();

        Space[] all = maze.getSpaces();
        int size = maze.getSize();

        int[] dist = new int[size];
        boolean[] visited = new boolean[size];

        for(int i = 0; i < size; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        dist[indexOf(all,size,start)] = 0;

        for(int i = 0; i < size; i++) {
            int u = minDistance(dist, visited, size);
            visited[u] = true;
            Space current = all[u];

            for (int j = 0; j < current.getNeighborCount(); j++){
                Space.Neighbor n = current.getNeighbors()[j];

                int v = indexOf(all, size, n.node);

                if(!visited[v] && dist[u] != Integer.MAX_VALUE) {
                    int newDist = dist[u] + n.distance;

                    if (newDist < dist[v]){
                        dist[v] = newDist;
                    }
                }
            }
        }

        System.out.println("Dijkstra complete. ");
    }

    private static int minDistance (int[] dist, boolean[] visited, int size){
        int min = Integer.MAX_VALUE;
        int index = -1;

        for(int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

    private static int indexOf (Space[] arr, int size, Space s) {
        for(int i = 0; i < size; i++) {
            if (arr[i]==s){
                return i;
            }
        }
        return -1;
    }


}
