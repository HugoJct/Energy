package fr.jacototlefranc.energy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observable;
import fr.jacototlefranc.energy.observer.Observer;

public class Level implements Observer, Observable {
    
    private int sizeX;
    private int sizeY;
    private int[][] adjacencyMatrix;
    private List<Tile> tiles;
    private TileShape tileShape;
    private ArrayList<Observer> observers = new ArrayList<>();

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
        t.addObserver(this);
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

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer o : observers) {
            o.update();
        }
    }

    @Override
    public void update() {
        notifyObserver();
    }
}
