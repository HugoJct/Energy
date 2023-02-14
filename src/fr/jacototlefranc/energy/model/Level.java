package fr.jacototlefranc.energy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileShape;

public class Level {
    
    private int sizeX;
    private int sizeY;
    private int[][] adjacencyMatrix;
    private List<Tile> tiles;
    private TileShape tileShape;

    public Level(int sizeX, int sizeY, TileShape shape) {

        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.tileShape = shape;

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

    public void shuffle() {
        Random random = new Random();
        for(Tile t : tiles) {
            if(t.getContent() == Component.OUTLET)
                continue;
            int rand = random.nextInt(t.getSides().length) + 1;
            for(int i=0;i<rand;i++)
                t.rotate();
        } 
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

    public TileShape getTilesShape() {
        return tileShape;
    }

    public boolean isWinning() {

        for (int i = 0 ; i < tiles.size() ; i++) {
            Tile currentTile = tiles.get(i);

            switch(currentTile.getContent()) {
                case LIGHTBULB:  break;
                case OUTLET: break;
                case WIFI: break;
                default: break;
            }

            if (currentTile.getContent() == Component.OUTLET)
                return true;
    
            int currentTilePosX = i%sizeX;
            int currentTilePosY = i/sizeX;
        }
        
        return true;
    }

    private boolean isFullyConnected(Tile t) {
        if (t.getShape() == TileShape.SQUARE) {
            for (int i = 0 ; i < 4 ; i++) {
                if (!t.getSides()[i].isConnected()) {
                    return false;
                }
            }
        } else {
            for (int i = 0 ; i < 6 ; i++) {
                if (!t.getSides()[i].isConnected()) {
                    return false;
                }
            }
        }
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
