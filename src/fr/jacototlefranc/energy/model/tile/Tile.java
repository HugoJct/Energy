package fr.jacototlefranc.energy.model.tile;

import java.util.ArrayList;
import java.util.List;

import fr.jacototlefranc.energy.model.tile.info.TileComponent;
import fr.jacototlefranc.energy.model.tile.info.TileEdge;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observable;
import fr.jacototlefranc.energy.observer.Observer;

public class Tile implements Observable{

    private List<Observer> observers = new ArrayList<>();

    private TileShape shape;
    private TileComponent content;
    private TileEdge[] sides;
    private boolean powered;

    public Tile(TileBuilder tb) {
        this.shape = tb.shape;
        this.content = tb.content;
        this.powered = false;

        switch (this.shape) {
            case HEXAGON:
                sides = new TileEdge[6];
                break;
            case SQUARE:
                sides = new TileEdge[4];
                break;
        }

        for(int i=0; i<sides.length; i++) {
            sides[i] = new TileEdge(false);
        }
        
        if(this.content == TileComponent.OUTLET) {
            powered = true;
        }
    }

    public void rotate() {
        TileEdge tmp = null;
        for (int i = sides.length - 1; i >= 0 ; i--) {
            if(i == sides.length - 1) {
                tmp = sides[i];
                sides[i] = sides[i - 1];
            } else if(i == 0) {
                sides[i] = tmp;
            } else {
                sides[i] = sides[i - 1];
            }
        }

        notifyObserver();
    }

    @Override
    public String toString() {
        String s = "";
        if(this.shape == TileShape.SQUARE) {
            s += "T: " + sides[0] + "\nR: " + sides[1] + "\nB: " + sides[2] + "\nL: " + sides[3];
        } else {
            s += "T: " + sides[0] + "\nTR: " + sides[1] + "\nBR: " + sides[2] + "\nB: " + sides[3] + "\nBL: " + sides[4] + "\nTL: " + sides[5];
        }

        return s;
    }

    public boolean isPowered() {
        return this.powered;
    }

    public TileShape getShape() {
        return this.shape;
    }

    public TileComponent getContent() {
        return this.content;
    }

    public TileEdge[] getSides() {
        return this.sides;
    }

    public void setPowered(boolean newState) {
        this.powered = newState;
    }

    public void setContent(TileComponent newContent) {
        this.content = newContent;
    }

    public static class TileBuilder {

        private TileShape shape;
        private TileComponent content;

        public TileBuilder() {
            this.shape = TileShape.SQUARE;
            this.content = TileComponent.LIGHTBULB;
        }

        public TileBuilder setShape(TileShape shape) {
            this.shape = shape;
            return this;
        }

        public TileBuilder setContent(TileComponent component) {
            this.content = component;
            return this;
        }

        public Tile build() {
            return new Tile(this);
        }
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
}
