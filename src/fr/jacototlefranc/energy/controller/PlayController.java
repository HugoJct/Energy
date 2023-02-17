package fr.jacototlefranc.energy.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.view.TileView;

public class PlayController extends MouseAdapter {

    private TileView tv;

    public PlayController(TileView tv) {
        this.tv = tv;
        tv.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        Tile t = tv.getTile();
        if(tv.getPolygon().contains(p) && t.getContent() != Component.OUTLET) {
            t.rotate();
        }
    }
}
