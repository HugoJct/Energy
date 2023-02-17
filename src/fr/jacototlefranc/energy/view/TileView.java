package fr.jacototlefranc.energy.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileProps;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observable;
import fr.jacototlefranc.energy.observer.Observer;
import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class TileView extends JPanel implements Observer, Observable {

    private List<Observer> observers = new ArrayList<>();

    private Tile t;
    private TextureManager tm;
    private Polygon poly;

    public TileView(Tile t) {
        this.setPreferredSize(new Dimension(TileProps.TILE_SIZE, TileProps.TILE_SIZE));
        this.t = t;
        tm = new TextureManager();
        this.setOpaque(false);

        poly = new Polygon();
        switch (t.getShape()) {
            case HEXAGON:
                poly.addPoint(30, 0);
                poly.addPoint(90, 0);
                poly.addPoint(120, 52);
                poly.addPoint(90, 104);
                poly.addPoint(30, 104);
                poly.addPoint(0, 52);
                break;
            case SQUARE:
                poly.addPoint(0, 0);
                poly.addPoint(120, 0);
                poly.addPoint(120, 120);
                poly.addPoint(0, 120);
                break;
        }

        t.addObserver(this);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public Polygon getPolygon() {
        return poly;
    }

    public Tile getTile() {
        return t;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean powered = t.isPowered();

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        if (t.getShape() == TileShape.SQUARE) {

            g2d.drawImage(tm.getTexture(TextureName.SQUARE_OUTLINE, powered), 0, 0, null);

            if (t.getContent() == Component.NONE) {

                for (int i = 0; i < 4; i++) {
                    if (t.getSides()[i].isConnected() && t.getSides()[(i + 1) % 4].isConnected()) {
                        g2d.rotate(Math.toRadians(90 * i), 60, 60);
                        g2d.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK, powered), 0, 0, null);
                        g2d.setTransform(old);
                    } else if (t.getSides()[i].isConnected() &&
                            t.getSides()[(i + 2) % 4].isConnected() &&
                            !(t.getSides()[(i + 1) % 4].isConnected()) &&
                            !(t.getSides()[(i + 3) % 4].isConnected())) {
                        g2d.rotate(Math.toRadians(90 * i), 60, 60);
                        g2d.drawImage(tm.getTexture(TextureName.SQUARE_LINK_LONG, powered), 0, 0, null);
                        g2d.setTransform(old);
                    }
                }

            } else {

                for (int i = 0; i < 4; i++) {
                    if (t.getSides()[i].isConnected()) {
                        g2d.rotate(Math.toRadians(90 * i), 60, 60);
                        g2d.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK, powered), 0, 0, null);
                        g2d.setTransform(old);
                    }
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

                for (int i = 0; i < 6; i++) {
                    if (t.getSides()[i].isConnected()) {
                        g2d.rotate(Math.toRadians(60 * i), 60, 52);
                        g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), 0, 0, null);
                        g2d.setTransform(old);
                    }
                }
            } else {

                for (int i = 0; i < 6; i++) {
                    if (t.getSides()[i].isConnected() && t.getSides()[(i + 1) % 6].isConnected()) {
                        g2d.rotate(Math.toRadians(60 * i), 60, 52);
                        g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), 0, 0, null);
                        g2d.setTransform(old);
                    } else if (t.getSides()[i].isConnected() && t.getSides()[(i + 2) % 6].isConnected()) {
                        g2d.rotate(Math.toRadians(60 * i), 60, 52);
                        g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_LONG, powered), 0, 0, null);
                        g2d.setTransform(old);
                    } else if (t.getSides()[i].isConnected() &&
                            t.getSides()[(i + 3) % 6].isConnected() &&
                            !t.getSides()[(i + 1) % 6].isConnected() &&
                            !t.getSides()[(i + 2) % 6].isConnected() &&
                            !t.getSides()[(i + 4) % 6].isConnected() &&
                            !t.getSides()[(i + 5) % 6].isConnected()) {
                        g2d.rotate(Math.toRadians(60 * i), 60, 52);
                        g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_LINK_LONG, powered), 0, 0, null);
                        g2d.setTransform(old);
                    }
                }
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
        this.repaint();
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
