package fr.jacototlefranc.energy.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.view.TileView;

public class TileController extends MouseAdapter {

    private Tile t;

    public TileController(Tile t, TileView tv) {
        this.t = t;

        tv.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (t.getContent() != Component.OUTLET) {
                t.rotate();
            }
        }
    }
}
