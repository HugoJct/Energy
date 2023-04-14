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

    private boolean areEdgesConnected(TileEdge te1, TileEdge te2) {
        return te1.isConnected() && te2.isConnected();
    }

    private void spreadSignal(Tile currentTile, int currentIndex, int lastIndex, int firstIndex) {

        if (tileShape == TileShape.SQUARE) {

            Tile[] adjacentTiles = { 
                isIndexOfList(currentIndex + 1) && areOnTheSameAxe(currentIndex, currentIndex + 1) ? tiles.get(currentIndex + 1) : null,
                isIndexOfList(currentIndex - 1) && areOnTheSameAxe(currentIndex, currentIndex - 1) ? tiles.get(currentIndex - 1) : null,
                isIndexOfList(currentIndex + sizeY) ? tiles.get(currentIndex + sizeY) : null,
                isIndexOfList(currentIndex - sizeY) ? tiles.get(currentIndex - sizeY) : null
            };
        
            for (Tile tile : adjacentTiles) {

                // If the tile is null or already powered, skip it
                if (tile == null || tile.isPowered()) {
                    continue;
                }
        
                // Get the index of the tile in the list
                int tileIndex = tiles.indexOf(tile);

                // Get the edges of the current tile and the adjacent tile
                TileEdge[] edges = currentTile.getSides();

                // 0 = top, 1 = right, 2 = bottom, 3 = left
                TileEdge[] adjacentEdges = tile.getSides();

                int edgeIndex = -1;
        
                // Get the index of the edge that is connected to the adjacent tile
                if (tileIndex == currentIndex + 1) {
                    edgeIndex = 1;
                } else if (tileIndex == currentIndex - 1) {
                    edgeIndex = 3;
                } else if (tileIndex == currentIndex + sizeY) {
                    edgeIndex = 2;
                } else if (tileIndex == currentIndex - sizeY) {
                    edgeIndex = 0;
                }
        
                if (areEdgesConnected(edges[edgeIndex], adjacentEdges[(edgeIndex + 2) % 4])) {
                    tile.setPowered(true);
                    spreadSignal(tile, tileIndex, currentIndex, firstIndex);
                }
            }
        } else {
                
            int row = currentIndex/sizeY;
            boolean isEvenRow = (row + currentIndex) % 2  == 0;
            int[] adjacentIndices;
            System.out.println("Tile " + currentIndex + " : " + isEvenRow);

            if (isEvenRow) {
                adjacentIndices = new int[] {
                    currentIndex + 1, // bottom right
                    currentIndex + sizeY - 1, // top right
                    currentIndex - 1, // bottom left
                    currentIndex - sizeY - 1, // top left
                    currentIndex - sizeY, // top
                    currentIndex + sizeY // bottom
                };
            } else {
                adjacentIndices = new int[] {
                    currentIndex + sizeY + 1, // bottom right
                    currentIndex + 1, // top right
                    currentIndex + sizeY - 1, // bottom left
                    currentIndex - 1, // top left
                    currentIndex - sizeY, // top
                    currentIndex + sizeY // bottom
                };
            }
    
            for (int adjacentIndex : adjacentIndices) {
                if (!isIndexOfList(adjacentIndex)) {
                    continue;
                }
    
                Tile adjacentTile = tiles.get(adjacentIndex);
                if (adjacentTile == null || adjacentTile.isPowered()) {
                    continue;
                }
    
                TileEdge[] edges = currentTile.getSides();
                TileEdge[] adjacentEdges = adjacentTile.getSides();
    
                int edgeIndex = -1;

                // Get the index of the edge that is connected to the adjacent tile
                if (adjacentIndex == currentIndex - sizeY) {
                    edgeIndex = 0; // top
                } else if (adjacentIndex == currentIndex + sizeY) {
                    edgeIndex = 3; // bottom
                } else if (!isEvenRow && adjacentIndex == currentIndex + 1) {
                    edgeIndex = 1; // top right
                } else if (!isEvenRow && adjacentIndex == currentIndex + sizeY + 1) {
                    edgeIndex = 2; // bottom right
                } else if (!isEvenRow && adjacentIndex == currentIndex + sizeY - 1) {
                    edgeIndex = 4; // bottom left
                } else if (!isEvenRow && adjacentIndex == currentIndex - 1) {
                    edgeIndex = 5; // top left
                } else if (isEvenRow && adjacentIndex == currentIndex + sizeY - 1) {
                    edgeIndex = 1; // top right
                } else if (isEvenRow && adjacentIndex == currentIndex + 1) {
                    edgeIndex = 2; // bottom right
                } else if (isEvenRow && adjacentIndex == currentIndex - 1) {
                    edgeIndex = 4; // bottom left
                } else if (isEvenRow && adjacentIndex == currentIndex - sizeY - 1) {
                    edgeIndex = 5; // top left
                } else {
                    System.out.println("Error: adjacentIndex is not adjacent to currentIndex");
                }
    
                if (areEdgesConnected(edges[edgeIndex], adjacentEdges[(edgeIndex + 3) % 6])) {
                    System.out.println("Tile " + currentIndex + " is connected to tile " + adjacentIndex);
                    adjacentTile.setPowered(true);
                    spreadSignal(adjacentTile, adjacentIndex, currentIndex, firstIndex);
                }
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
