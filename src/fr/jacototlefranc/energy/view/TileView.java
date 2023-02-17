package fr.jacototlefranc.energy.view;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
        boolean powered = t.isPowered();

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();

        g2d.setComposite(AlphaComposite.Clear); 
        g2d.fillRect(0, 0, 120, 120); 
        g2d.setComposite(AlphaComposite.SrcOver);

        if (t.getShape() == TileShape.SQUARE) {

            g.drawImage(tm.getTexture(TextureName.SQUARE_OUTLINE, powered), 0, 0, null);

            if (t.getContent() == Component.NONE) {

                if (t.getSides()[0].isConnected() && t.getSides()[1].isConnected()) {
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK, powered), 0, 0, null);
                }
                if (t.getSides()[1].isConnected() && t.getSides()[2].isConnected()) {
                    transform.rotate(Math.toRadians(90), 60, 60);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-90), 60, 60);
                }
                if (t.getSides()[2].isConnected() && t.getSides()[3].isConnected()) {
                    transform.rotate(Math.toRadians(180), 60, 60);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-180), 60, 60);
                }
                if (t.getSides()[3].isConnected() && t.getSides()[0].isConnected()) {
                    transform.rotate(Math.toRadians(-90), 60, 60);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(90), 60, 60);
                }

                if ((t.getSides()[0].isConnected() && t.getSides()[2].isConnected()) &&
                        !(t.getSides()[0].isConnected() &&
                                t.getSides()[1].isConnected() &&
                                t.getSides()[2].isConnected())
                        &&
                        !(t.getSides()[0].isConnected() &&
                                t.getSides()[3].isConnected() &&
                                t.getSides()[2].isConnected())) {
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_LINK_LONG, powered), 0, 0, null);
                }
                if (t.getSides()[1].isConnected() && t.getSides()[3].isConnected() &&
                        !(t.getSides()[1].isConnected() &&
                                t.getSides()[2].isConnected() &&
                                t.getSides()[3].isConnected())
                        &&
                        !(t.getSides()[0].isConnected() &&
                                t.getSides()[3].isConnected() &&
                                t.getSides()[1].isConnected())) {

                    transform.rotate(Math.toRadians(90), 60, 60);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_LINK_LONG, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-90), 60, 60);
                }

            } else {
                if (t.getSides()[0].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK, powered), 0, 0, null);
                }
                if (t.getSides()[1].isConnected()) {
                    transform.rotate(Math.toRadians(90), 60, 60);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-90), 60, 60);
                }
                if (t.getSides()[2].isConnected()) {
                    transform.rotate(Math.toRadians(180), 60, 60);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-180), 60, 60);
                }
                if (t.getSides()[3].isConnected()) {
                    transform.rotate(Math.toRadians(-90), 60, 60);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(90), 60, 60);
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

            if (t.getContent() != Component.NONE) {

                if (t.getSides()[0].isConnected()) {
                    g.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), 0, 0, null);
                }
                if (t.getSides()[1].isConnected()) {
                    transform.rotate(Math.toRadians(60), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-60), 60, 52);
                }
                if (t.getSides()[2].isConnected()) {
                    transform.rotate(Math.toRadians(120), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-120), 60, 52);
                }
                if (t.getSides()[3].isConnected()) {
                    transform.rotate(Math.toRadians(180), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-180), 60, 52);
                }
                if (t.getSides()[4].isConnected()) {
                    transform.rotate(Math.toRadians(-120), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(120), 60, 52);
                }
                if (t.getSides()[5].isConnected()) {
                    transform.rotate(Math.toRadians(-60), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(60), 60, 52);
                }
            } else {
                //Top to top right
                if(t.getSides()[0].isConnected() && t.getSides()[1].isConnected()) {
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), 0, 0, null);
                }
                //Top right to bottom right
                if(t.getSides()[1].isConnected() && t.getSides()[2].isConnected()) {
                    transform.rotate(Math.toRadians(60), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-60), 60, 52);
                }
                //Bottom left to bottom
                if(t.getSides()[2].isConnected() && t.getSides()[3].isConnected()) {
                    transform.rotate(Math.toRadians(120), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-120), 60, 52);

                }
                //Bottom to to bottom left
                if(t.getSides()[3].isConnected() && t.getSides()[4].isConnected()) {
                    transform.rotate(Math.toRadians(180), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(-180), 60, 52);
                }
                //Bottom left to top left
                if(t.getSides()[4].isConnected() && t.getSides()[5].isConnected()) {
                    transform.rotate(Math.toRadians(-120), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(120), 60, 52);
                }
                //TOp left to top
                if(t.getSides()[5].isConnected() && t.getSides()[0].isConnected()) {
                    transform.rotate(Math.toRadians(-60), 60, 52);
                    g2d.setTransform(transform);
                    g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), 0, 0, null);
                    transform.rotate(Math.toRadians(60), 60, 52);
                }
            }

            g2d.setTransform(transform);

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
        for (Observer o : observers) {
            o.update();
        }
    }
}
