package fr.jacototlefranc.energy.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileProps;

public class PlayController extends MouseAdapter {

    private Level level;

    public PlayController(Level lvl) {
        this.level = lvl;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int x = e.getX() / TileProps.TILE_SIZE;
        int y = e.getY() / TileProps.TILE_SIZE;

        int index = x;

        if(y != 0) {
            index = (y * level.getSizeY()) + x;
        }

        Tile t = level.getTiles().get(index);

        if(t.getContent() != Component.OUTLET)
            t.rotate();
    }
}
