package fr.jacototlefranc.energy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import fr.jacototlefranc.energy.controller.TileController;
import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;

public class BoardView extends JPanel {

    public BoardView(Level lvl) {

        GridLayout gl = new GridLayout(lvl.getSizeX(), lvl.getSizeY());
        this.setLayout(gl);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(lvl.getSizeY() * 120, lvl.getSizeX() * 120));

        for(Tile t : lvl.getTiles()) {
            TileView tv = new TileView(t);
            new TileController(t, tv);
            this.add(tv);
        }
    }
}
