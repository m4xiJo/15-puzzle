import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puzzle {
    protected int tiles[][];
    public Puzzle() {
         this.tiles = new int[][] { // Tile Number, Pos X, Pos Y
             {1, 0, 0}, {2, 1, 0}, {3, 2, 0}, {4, 3, 0},
             {5, 0, 1}, {6, 1, 1}, {7, 2, 1}, {8, 3, 1},
             {9, 0, 2}, {10, 1, 2}, {11, 2, 2}, {12, 3, 2},
             {13, 0, 3}, {14, 1, 3}, {15, 2, 3}, {16, 3, 3}
         };
    }

    public int[][] getTiles() {
        return this.tiles;
    }

    public void shuffle() {
        List<int[]> list = Arrays.asList(this.tiles);
        Collections.shuffle(list);
        this.tiles = list.toArray(new int[0][0]);
        this.resetPos();
    }

    public void resetPos() {
        int posX = 0;
        int posY = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            this.tiles[i][1] = posX;
            this.tiles[i][2] = posY;
            if(posX >= 3) {
                posX = 0;
                posY++;
            } else posX++;
        }
        System.out.println(Arrays.deepToString(this.tiles));
    }

    public void moveTile(int tileNr) {
        int emptyTile = this.findTile(16);
        int clickedTile = this.findTile(tileNr);
        if(this.getTiles()[clickedTile][1] == this.getTiles()[emptyTile][1] && (this.getTiles()[emptyTile][2] == this.getTiles()[clickedTile][2] - 1 || this.getTiles()[emptyTile][2] == this.getTiles()[clickedTile][2] + 1)) {
            this.tiles[emptyTile][0] = this.getTiles()[clickedTile][0];
            this.tiles[clickedTile][0] = 16;
            this.resetPos();
        }

        else if(this.getTiles()[clickedTile][2] == this.getTiles()[emptyTile][2] && (this.getTiles()[emptyTile][1] == this.getTiles()[clickedTile][1] - 1 || this.getTiles()[emptyTile][1] == this.getTiles()[clickedTile][1] + 1)) {
            this.tiles[emptyTile][0] = this.getTiles()[clickedTile][0];
            this.tiles[clickedTile][0] = 16;
            this.resetPos();
        }

        else System.out.println("Can't move the tile...");
    }

    public int findTile(int tileNr) {
        int tileIdx = 0;
        for(int i = 0; i < this.tiles.length; i++) {
            if(this.tiles[i][0] == tileNr) tileIdx = i;
        }
        return tileIdx;
    }
}
