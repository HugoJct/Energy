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
        return true;
    }

    private boolean isIndexOfList(int index) {
        return index >= 0 && index < tiles.size();
    }

    private boolean areOnTheSameAxe(int index1, int index2) {
        if (index1 < index2) {
            return index2%sizeY > index1;
        }
        else {
            return index2%sizeY < index1;
        }
    }

    private boolean hasBeenVisited(int index, int lastIndex, int firstIndex) {
        return index == lastIndex || index == firstIndex;
    }

    private boolean areSidesConnected(TileEdge te1, TileEdge te2) {
        return te1.isConnected() && te2.isConnected();
    }

    private void updateConnections(Tile currentTile, int currentIndex, int lastIndex, int firstIndex) {
        boolean isConnectedWithNeighbour = false;

        // évaluation à droite
        if (isIndexOfList(currentIndex+1) && areOnTheSameAxe(currentIndex, currentIndex+1)) {
            System.out.println("Tiles " + currentIndex + " évaluation à droite");
            if (areSidesConnected(currentTile.getSides()[1], tiles.get(currentIndex+1).getSides()[3])) {
                System.out.println("Tiles " + currentIndex+1 + " powering");
                tiles.get(currentIndex+1).setPowered(true);
                isConnectedWithNeighbour = true;
                if (!hasBeenVisited(currentIndex+1, lastIndex, firstIndex)) {
                    updateConnections(tiles.get(currentIndex+1), currentIndex+1, currentIndex, firstIndex);
                }
            }
        }

        // évaluation à gauche
        if (isIndexOfList(currentIndex-1) && areOnTheSameAxe(currentIndex, currentIndex-1)) {
            System.out.println("Tiles " + currentIndex + " évaluation à gauche");
            if (areSidesConnected(currentTile.getSides()[3], tiles.get(currentIndex-1).getSides()[1])) {
                System.out.println("Tiles " + (currentIndex-1) + " powering");
                tiles.get(currentIndex-1).setPowered(true);
                isConnectedWithNeighbour = true;
                if (!hasBeenVisited(currentIndex-1, lastIndex, firstIndex)) {
                    updateConnections(tiles.get(currentIndex-1), currentIndex-1, currentIndex, firstIndex);
                }
            }
        }

        // évaluation en bas
        if (isIndexOfList(currentIndex+sizeY)) {
            System.out.println("Tiles " + currentIndex + " évaluation en bas");
            if (areSidesConnected(currentTile.getSides()[2], tiles.get(currentIndex+sizeY).getSides()[0])) {
                System.out.println("Tiles " + (currentIndex+sizeY) + " powering");
                tiles.get(currentIndex+sizeY).setPowered(true);
                isConnectedWithNeighbour = true;
                if (!hasBeenVisited(currentIndex+sizeY, lastIndex, firstIndex)) {
                    updateConnections(tiles.get(currentIndex+sizeY), currentIndex+sizeY, currentIndex, firstIndex);
                }
            }
        }

        // évaluation en haut
        if (isIndexOfList(currentIndex-sizeY)) {
            System.out.println("Tiles " + currentIndex + " évaluation en haut");
            if (areSidesConnected(currentTile.getSides()[0], tiles.get(currentIndex-sizeY).getSides()[2])) {
                System.out.println("Tiles " + (currentIndex-sizeY) + " powering");
                tiles.get(currentIndex-sizeY).setPowered(true);
                isConnectedWithNeighbour = true;
                if (!hasBeenVisited(currentIndex-sizeY, lastIndex, firstIndex)) {
                    updateConnections(tiles.get(currentIndex-sizeY), currentIndex-sizeY, currentIndex, firstIndex);
                }
            }
        }

        if (!isConnectedWithNeighbour && currentTile.getContent() != TileComponent.OUTLET) {
            System.out.println("Tiles " + currentIndex + " unpowering");
            currentTile.setPowered(false);
        }
    }

    public void updateTilesProperties() {
        System.out.println("Update");

        for (int i = 0; i < tiles.size(); i++) {
            Tile currentTile = tiles.get(i);

            if (currentTile.isPowered()) {
                System.out.println("Tile " + i + " is powered");
                updateConnections(currentTile, i, i, i);
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
