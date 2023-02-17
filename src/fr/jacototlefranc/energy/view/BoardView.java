package fr.jacototlefranc.energy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import fr.jacototlefranc.energy.controller.PlayController;
import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.TileProps;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.observer.Observer;

public class BoardView extends JPanel implements Observer{

    private Level level;
    private List<TileView> tvs = new ArrayList<>();

    public BoardView(Level lvl) {
        this.level = lvl;
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        if (level.getTilesShape() == TileShape.SQUARE) {
            this.setPreferredSize(new Dimension(level.getSizeY() * TileProps.TILE_SIZE, level.getSizeX() * TileProps.TILE_SIZE + 10));
        } else {
            this.setPreferredSize(new Dimension(level.getSizeY() * TileProps.TILE_SIZE - (level.getSizeY() / 2 * 55), level.getSizeX() * 104 + 52 + 10));
        }

        for(Tile t : lvl.getTiles()) {
            TileView tv = new TileView(t);
            tv.addObserver(this);
            new PlayController(tv);
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

            this.add(tv);

            if (tv.getTile().getShape() == TileShape.HEXAGON) {
                if (x % 2 == 1) {
                    tv.setBounds(x * 91, y * 104 + 52, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
                } else {
                    tv.setBounds(x * 91, y * 104, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
                }
            } else {
                tv.setBounds(x * TileProps.TILE_SIZE, y * TileProps.TILE_SIZE, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            }
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
