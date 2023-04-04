package fr.jacototlefranc.energy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileProps;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observer;
import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class BoardView extends JPanel implements Observer {

    private Level level;
    TextureManager tm = new TextureManager();

    public BoardView(Level lvl) {
        this.level = lvl;
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        lvl.addObserver(this);

        if (level.getTilesShape() == TileShape.SQUARE) {
            this.setPreferredSize(
                    new Dimension(level.getSizeY() * TileProps.TILE_SIZE, level.getSizeX() * TileProps.TILE_SIZE + 10));
        } else {
            this.setPreferredSize(new Dimension(level.getSizeY() * TileProps.TILE_SIZE - (level.getSizeY() / 2 * 55),
                    level.getSizeX() * 104 + 52 + 10));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int j = 0; j < level.getTiles().size(); j++) {

            int basex = j % level.getSizeY();
            int basey = j / level.getSizeY();
            int x = basex;
            int y = basey;
            Tile t = level.getTiles().get(j);

            if (t.getShape() == TileShape.HEXAGON) {
                if (basex % 2 == 1) {
                    x *= 91;
                    y = y * 104 + 52; 
                } else {
                    x *= 91;
                    y = y * 104; 
                }
            } else {
                x *= 120;
                y *= 120;
            }

            boolean powered = t.isPowered();
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform old = g2d.getTransform();

            if (t.getShape() == TileShape.SQUARE) {

                g2d.drawImage(tm.getTexture(TextureName.SQUARE_OUTLINE, powered), x, y, null);

                if (t.getContent() == Component.NONE) {

                    for (int i = 0; i < 4; i++) {
                        if (t.getSides()[i].isConnected() && t.getSides()[(i + 1) % 4].isConnected()) {
                            g2d.rotate(Math.toRadians(90 * i), x + 60, y + 60);
                            g2d.drawImage(tm.getTexture(TextureName.SQUARE_CURVE_LINK, powered), x, y, null);
                            g2d.setTransform(old);
                        } else if (t.getSides()[i].isConnected() &&
                                t.getSides()[(i + 2) % 4].isConnected() &&
                                !(t.getSides()[(i + 1) % 4].isConnected()) &&
                                !(t.getSides()[(i + 3) % 4].isConnected())) {
                            g2d.rotate(Math.toRadians(90 * i), x + 60, y + 60);
                            g2d.drawImage(tm.getTexture(TextureName.SQUARE_LINK_LONG, powered), x, y, null);
                            g2d.setTransform(old);
                        }
                    }

                } else {

                    for (int i = 0; i < 4; i++) {
                        if (t.getSides()[i].isConnected()) {
                            g2d.rotate(Math.toRadians(90 * i), x + 60, y + 60);
                            g2d.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK, powered), x, y, null);
                            g2d.setTransform(old);
                        }
                    }

                    if (t.getContent() == Component.OUTLET) {
                        g.drawImage(tm.getTexture(TextureName.SQUARE_OUTLET, powered), x, y, null);
                    } else if (t.getContent() == Component.WIFI) {
                        g.drawImage(tm.getTexture(TextureName.SQUARE_WIFI, powered), x, y, null);
                    } else if (t.getContent() == Component.LIGHTBULB) {
                        g.drawImage(tm.getTexture(TextureName.SQUARE_LIGHTBULB, powered), x, y, null);
                    }
                }

            } else if (t.getShape() == TileShape.HEXAGON) {

                g.drawImage(tm.getTexture(TextureName.HEXAGONAL_OUTLINE, powered), x, y, null);

                if (t.getContent() != Component.NONE) {

                    for (int i = 0; i < 6; i++) {
                        if (t.getSides()[i].isConnected()) {
                            g2d.rotate(Math.toRadians(60 * i), x + 60, y + 52);
                            g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_COMPONENT_LINK, powered), x, y, null);
                            g2d.setTransform(old);
                        }
                    }
                } else {

                    for (int i = 0; i < 6; i++) {
                        if (t.getSides()[i].isConnected() && t.getSides()[(i + 1) % 6].isConnected()) {
                            g2d.rotate(Math.toRadians(60 * i), x + 60, y + 52);
                            g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_SHORT, powered), x, y, null);
                            g2d.setTransform(old);
                        } else if (t.getSides()[i].isConnected() && t.getSides()[(i + 2) % 6].isConnected()) {
                            g2d.rotate(Math.toRadians(60 * i), x + 60, y + 52);
                            g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_CURVE_LINK_LONG, powered), x, y, null);
                            g2d.setTransform(old);
                        } else if (t.getSides()[i].isConnected() &&
                                t.getSides()[(i + 3) % 6].isConnected() &&
                                !t.getSides()[(i + 1) % 6].isConnected() &&
                                !t.getSides()[(i + 2) % 6].isConnected() &&
                                !t.getSides()[(i + 4) % 6].isConnected() &&
                                !t.getSides()[(i + 5) % 6].isConnected()) {
                            g2d.rotate(Math.toRadians(60 * i), x + 60, y + 52);
                            g2d.drawImage(tm.getTexture(TextureName.HEXAGONAL_LINK_LONG, powered), x, y, null);
                            g2d.setTransform(old);
                        }
                    }
                }

                if (t.getContent() == Component.OUTLET) {
                    g.drawImage(tm.getTexture(TextureName.HEXAGONAL_OUTLET, powered), x, y, null);
                } else if (t.getContent() == Component.WIFI) {
                    g.drawImage(tm.getTexture(TextureName.HEXAGONAL_WIFI, powered), x, y, null);
                } else if (t.getContent() == Component.LIGHTBULB) {
                    g.drawImage(tm.getTexture(TextureName.HEXAGONAL_LIGHTBULB, powered), x, y, null);
                }
            }
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
