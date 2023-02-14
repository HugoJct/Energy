package fr.jacototlefranc.energy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.TileProps;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observer;

public class BoardView extends JPanel implements Observer {

    private Level level;
    private List<TileView> tvs = new ArrayList<>();

    public BoardView(Level lvl) {
        this.level = lvl;
        this.setBackground(Color.BLACK);

        if (level.getTilesShape() == TileShape.SQUARE) {
            this.setPreferredSize(new Dimension(level.getSizeY() * TileProps.TILE_SIZE, level.getSizeX() * TileProps.TILE_SIZE));
        } else {
            this.setPreferredSize(new Dimension(level.getSizeY() * TileProps.TILE_SIZE  , level.getSizeX() * 104 + 52));
        }

        for(Tile t : lvl.getTiles()) {
            TileView tv = new TileView(t);
            tv.addObserver(this);
            tvs.add(tv);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < level.getTiles().size(); i++) {

            TileView tv = tvs.get(i);

            int x = i % level.getSizeY();
            int y = i / level.getSizeY();

            if (tv.getTile().getShape() == TileShape.HEXAGON) {
                if (x % 2 == 1) {
                    g.drawImage(tv, x * 91, y * 104 + 52, null);
                } else {
                    g.drawImage(tv, x * 91, y * 104, null);
                }
            } else {
                g.drawImage(tv, x * TileProps.TILE_SIZE, y * TileProps.TILE_SIZE, null);
            }
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
