package fr.jacototlefranc.energy.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;

public class PlayController extends MouseAdapter {

    private Level level;

    public PlayController(Level lvl) {
        this.level = lvl;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int x = e.getX() / 120;
        int y = e.getY() / 120;

        int index = x;

        if(y != 0) {
            index = (y * level.getSizeY()) + x;
        }

        Tile t = level.getTiles().get(index);

        if(t.getContent() != Component.OUTLET)
            t.rotate();
    }
}
