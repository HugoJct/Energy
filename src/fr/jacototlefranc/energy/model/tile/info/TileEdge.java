package fr.jacototlefranc.energy.model.tile.info;

public class TileEdge {

    private boolean visited;
    private boolean connected; 

    public TileEdge(boolean isConnected) {
        this.visited = false;
        this.connected = isConnected;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void setVisited(boolean newState) {
        this.visited = newState;
    }

    public void setConnected(boolean newState) {
        this.connected = newState;
    }

    @Override
    public String toString() {
        return connected ? "Y" : "N";
    }
}
