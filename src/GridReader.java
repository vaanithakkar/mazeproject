import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridReader {

    public static Maze readMaze(String filename) throws FileNotFoundException{

        Scanner file = new Scanner(new File(filename));

        String[] lines = new String[50];
        int rowCount=0;

        while (file.hasNextLine()) {
            lines[rowCount++] = file.nextLine();
        }

        String[] firstRow = lines[0].split(",");
        int colCount = firstRow.length;

        for (int i=0; i<rowCount; i++){
            String[] temp = lines[i].split(",");
            if (temp.length != colCount){
                System.out.println("Error: rows are not the same length.");
                return null;
            }
        }

        Space[][] grid = new Space [rowCount][colCount];

        Space start = null;
        Space end = null;

        for (int r=0; r<rowCount; r++){

            String[] tokens = lines[r].split(",");

            for (int c=0; c<colCount; c++){
                String val = tokens[c];
                Space s = new Space(val,r,c);

                grid[r][c] = s;
                if (val.equals("S")) {
                    start = s;
                } else if (val.equals("E")) {
                    end = s;
                }
            }
        }

        if (start == null || end == null) {
            System.out.println("Error: Maze must have exactly on Start and one Exit");
            return null;
        }
        for (int r = 0; r<rowCount; r++){
            for (int c=0; c<colCount; c++){
                Space current = grid[r][c];
                if (current.isBlocked()) continue;

                //go up
                if (r > 0 && !grid[r - 1][c].isBlocked()){
                    current.addNeighbor(grid[r - 1][c]);
                }

                //go down
                if (r < rowCount - 1 && !grid[r + 1][c].isBlocked()) {
                    current.addNeighbor(grid[r + 1][c]);
                }

                //go left
                if (c > 0 && !grid[r][c - 1].isBlocked()) {
                    current.addNeighbor(grid[r][c - 1]);
                }

                //go right
                if (c < colCount - 1 && !grid[r][c + 1].isBlocked()) {
                    current.addNeighbor(grid[r][c + 1]);
                }
            }
        }

        Maze maze = new Maze(start, end);
        return maze;
    }

}
