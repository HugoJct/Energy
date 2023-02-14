package fr.jacototlefranc.energy.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileProps;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observable;
import fr.jacototlefranc.energy.observer.Observer;
import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class TileView extends BufferedImage implements Observer, Observable {

    private List<Observer> observers = new ArrayList<>();

    private Tile t;
    private TextureManager tm;

    public TileView(Tile t) {
        super(TileProps.TILE_SIZE, TileProps.TILE_SIZE, TYPE_INT_ARGB);
        this.t = t;
        tm = new TextureManager();

        t.addObserver(this);

        paint();
    }

    public Tile getTile() {
        return t;
    }

    protected void paint() {

        Graphics g = this.getGraphics();
        boolean powered = t.getSides()[0].isPowered() || t.getSides()[1].isPowered() || t.getSides()[2].isPowered() || t.getSides()[3].isPowered();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, TileProps.TILE_SIZE, TileProps.TILE_SIZE);

        if (t.getShape() == TileShape.SQUARE) {

            g.drawImage(tm.getTexture(TextureName.SQUARE_OUTLINE, powered), 0, 0, null);

            if (t.getContent() == Component.NONE) {

                if (t.getSides()[0].isConnected() && t.getSides()[1].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK_TOP_TO_RIGHT, powered), 0, 0, null);
                }
                if (t.getSides()[1].isConnected() && t.getSides()[2].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK_RIGHT_TO_BOTTOM, powered), 0, 0, null);
                }
                if (t.getSides()[2].isConnected() && t.getSides()[3].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK_BOTTOM_TO_LEFT, powered), 0, 0, null);
                }
                if (t.getSides()[3].isConnected() && t.getSides()[0].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK_LEFT_TO_TOP, powered), 0, 0, null);
                }

                if ((t.getSides()[0].isConnected() && t.getSides()[2].isConnected()) &&
                        !(t.getSides()[0].isConnected() &&
                                t.getSides()[1].isConnected() &&
                                t.getSides()[2].isConnected())
                        &&
                        !(t.getSides()[0].isConnected() &&
                                t.getSides()[3].isConnected() &&
                                t.getSides()[2].isConnected())) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_LINK_TOP_TO_BOTTOM, powered), 0, 0, null);
                }
                if (t.getSides()[1].isConnected() && t.getSides()[3].isConnected() &&
                        !(t.getSides()[1].isConnected() &&
                                t.getSides()[2].isConnected() &&
                                t.getSides()[3].isConnected())
                        &&
                        !(t.getSides()[0].isConnected() &&
                                t.getSides()[3].isConnected() &&
                                t.getSides()[1].isConnected())) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_LINK_LEFT_TO_RIGHT, powered), 0, 0, null);
                }

            } else {
                if (t.getSides()[0].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK_TOP, powered), 0, 0, null);
                }
                if (t.getSides()[1].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK_RIGHT, powered), 0, 0, null);
                }
                if (t.getSides()[2].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK_BOTTOM, powered), 0, 0, null);
                }
                if (t.getSides()[3].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK_LEFT, powered), 0, 0, null);
                }

                if (t.getContent() == Component.OUTLET) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_OUTLET, powered), 0, 0, null);
                } else if (t.getContent() == Component.WIFI) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_WIFI, powered), 0, 0, null);
                } else if (t.getContent() == Component.LIGHTBULB) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_LIGHTBULB, powered), 0, 0, null);
                }
            }

        } else if (t.getShape() == TileShape.HEXAGON) {

            g.drawImage(tm.getTexture(TextureName.HEXAGONAL_OUTLINE, powered), 0, 0, null);

            if (t.getSides()[0].isConnected()) {
                g.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK_TOP, powered), 0, 0, null);
            }
            if(t.getSides()[1].isConnected()) {
                g.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK_TOP_RIGHT, powered), 0, 0, null);
            }
            if (t.getSides()[3].isConnected()) {
                g.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK_BOTTOM, powered), 0, 0, null);
            }

            if (t.getContent() == Component.OUTLET) {
                g.drawImage(tm.getTexture(TextureName.HEXAGONAL_OUTLET, powered), 0, 0, null);
            } else if (t.getContent() == Component.WIFI) {
                g.drawImage(tm.getTexture(TextureName.HEXAGONAL_WIFI, powered), 0, 0, null);
            } else if (t.getContent() == Component.LIGHTBULB) {
                g.drawImage(tm.getTexture(TextureName.HEXAGONAL_LIGHTBULB, powered), 0, 0, null);
            }
        }
    }

    @Override
    public void update() {
        paint();
        notifyObserver();
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
