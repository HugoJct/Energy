package fr.jacototlefranc.energy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observer;
import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class TileView extends JPanel implements Observer {

    private Tile t;
    private TextureManager tm;

    public TileView(Tile t) {
        super();
        this.t = t;
        tm = new TextureManager();

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(120, 120));
        t.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean powered = t.getSides()[0].isPowered() || t.getSides()[1].isPowered() || t.getSides()[2].isPowered() || t.getSides()[3].isPowered();

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
                g.drawImage(tm.getTexture(TextureName.SQUARE_COMPONENT_LINK_TOP, powered), 0, 0, null);
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
        this.repaint();
    }
}
