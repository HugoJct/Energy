package fr.jacototlefranc.energy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.TileComponent;
import fr.jacototlefranc.energy.model.tile.info.TileEdge;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observable;
import fr.jacototlefranc.energy.observer.Observer;

public class Level implements Observer, Observable {
    
    private int sizeX;
    private int sizeY;
    private List<Tile> tiles;
    private TileShape tileShape;
    private ArrayList<Observer> observers = new ArrayList<>();

    public Level(int sizeX, int sizeY, TileShape shape) {

        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.tileShape = shape;

        tiles = new ArrayList<>();

    }

    private boolean isIndexOfList(int index) {
        return index >= 0 && index < tiles.size();
    }

    private boolean areOnTheSameAxe(int index1, int index2) {
        return index1 / sizeY == index2 / sizeY;
    }

    private boolean areSidesConnected(TileEdge te1, TileEdge te2) {
        return te1.isConnected() && te2.isConnected();
    }

    private void spreadSignal(Tile currentTile, int currentIndex, int lastIndex, int firstIndex) {

        Tile[] adjacentTiles = { 
            isIndexOfList(currentIndex + 1) && areOnTheSameAxe(currentIndex, currentIndex + 1) ? tiles.get(currentIndex + 1) : null,
            isIndexOfList(currentIndex - 1) && areOnTheSameAxe(currentIndex, currentIndex - 1) ? tiles.get(currentIndex - 1) : null,
            isIndexOfList(currentIndex + sizeY) ? tiles.get(currentIndex + sizeY) : null,
            isIndexOfList(currentIndex - sizeY) ? tiles.get(currentIndex - sizeY) : null
        };
    
        for (Tile tile : adjacentTiles) {
            if (tile == null || tile.isPowered()) {
                continue;
            }
    
            int tileIndex = tiles.indexOf(tile);
            TileEdge[] edges = currentTile.getSides();
            TileEdge[] adjacentEdges = tile.getSides();
            int edgeIndex = -1;
    
            if (tileIndex == currentIndex + 1) {
                edgeIndex = 1;
            } else if (tileIndex == currentIndex - 1) {
                edgeIndex = 3;
            } else if (tileIndex == currentIndex + sizeY) {
                edgeIndex = 2;
            } else if (tileIndex == currentIndex - sizeY) {
                edgeIndex = 0;
            }
    
            if (areSidesConnected(edges[edgeIndex], adjacentEdges[(edgeIndex + 2) % 4])) {
                tile.setPowered(true);
                spreadSignal(tile, tileIndex, currentIndex, firstIndex);
            }
        }

    }

    public void addTile(Tile t) {
        tiles.add(t);
        t.addObserver(this);
    }

    public Level shuffle() {
        Random random = new Random();
        for(Tile t : tiles) {
            if(t.getContent() == TileComponent.OUTLET)
                continue;
            int rand = random.nextInt(t.getSides().length) + 1;
            for(int i=0;i<rand;i++)
                t.rotate();
        } 
        return this;
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
        for (Tile t : tiles) {
            if (t.getContent() == TileComponent.LIGHTBULB && !t.isPowered()) {
                return false;
            }
        }
        return true;
    }

    public void updateTilesProperties() {
        System.out.println("Update");
        System.out.println(sizeY);
        for (Tile t : tiles) {
            if (t.getContent() != TileComponent.OUTLET) {
                t.setPowered(false);
            }
        }

        for (int i = 0; i < tiles.size(); i++) {
            Tile currentTile = tiles.get(i);

            if (currentTile.getContent() == TileComponent.OUTLET) {
                System.out.println("Tile " + i + " is an outlet");
                spreadSignal(currentTile, i, i, i);
            }

        }
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
