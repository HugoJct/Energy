package fr.jacototlefranc.energy.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;

public class PlayController extends MouseAdapter {

    private Level lvl;

    public PlayController(Level l) {
        this.lvl = l;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Tile t = lvl.getTiles().get(5);
        t.rotate();
        System.out.println("oui");
    }
}
