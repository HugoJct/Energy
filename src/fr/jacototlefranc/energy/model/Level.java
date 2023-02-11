package fr.jacototlefranc.energy.model;

import java.util.ArrayList;
import java.util.List;

import fr.jacototlefranc.energy.model.tile.Tile;

public class Level {
    
    private int sizeX;
    private int sizeY;
    private int[][] adjacencyMatrix;
    private List<Tile> tiles;

    public Level(int sizeX, int sizeY) {

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        adjacencyMatrix = new int[sizeX][sizeY];
        for(int i=0; i<sizeX; i++) {
            for(int j=0;j<sizeY;j++) {
                adjacencyMatrix[i][j] = -1;
            }
        }

        tiles = new ArrayList<>();
    }

    public void addTile(Tile t) {
        tiles.add(t);
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public boolean isWinning() {
        return true;
    }

    @Override
    public String toString() {
        String s = "";
        for(Tile t : tiles) {
            s += t + "\n";
        }
        return s;
    }
}
