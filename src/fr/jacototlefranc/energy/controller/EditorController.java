package fr.jacototlefranc.energy.controller;

import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.TileComponent;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.view.ingame.BoardView;

public class EditorController extends MouseAdapter {

    private Level lvl;

    private int previousTile = -1;

    public EditorController(BoardView bv, Level l) {
        this.lvl = l;

        bv.addMouseListener(this);
        bv.addMouseMotionListener(this);
        l.addObserver(bv);
    }

    private int getTileInteracted(MouseEvent e) {

        int xmax = lvl.getSizeX();
        int ymax = lvl.getSizeY();

        int tileCount = xmax * ymax - 1;

        if (lvl.getTilesShape() == TileShape.HEXAGON) {
            Polygon ph = new Polygon();
            ph.addPoint(30, 0);
            ph.addPoint(90, 0);
            ph.addPoint(120, 52);
            ph.addPoint(90, 104);
            ph.addPoint(30, 104);
            ph.addPoint(0, 52);

            boolean driftedDown = false;

            for (int i = 0; i <= tileCount; i++) {

                if (i > 0) {

                    if (i % ymax == 0) {
                        if (driftedDown) {
                            ph.translate((-91) * (ymax - 1), 52);
                            driftedDown = false;
                        } else {
                            ph.translate((-91) * (ymax - 1), 104);
                        }
                    } else if ((i % ymax) % 2 == 0) {
                        if (driftedDown) {
                            ph.translate(91, -52);
                            driftedDown = false;
                        } else {
                            ph.translate(91, 0);
                        }
                    } else if ((i % ymax) % 2 == 1) {
                        if (driftedDown) {
                            ph.translate(91, 0);
                        } else {
                            ph.translate(91, 52);
                            driftedDown = true;
                        }
                    }
                }

                if (ph.contains(e.getPoint())) {
                    return i;
                }
            }
        }

        Polygon p = new Polygon();
        p.addPoint(0, 0);
        p.addPoint(120, 0);
        p.addPoint(120, 120);
        p.addPoint(0, 120);

        for (int i = 0; i <= tileCount; i++) {

            if (i > 0) {
                if (i % ymax == 0) {
                    p.translate((-120) * (ymax - 1), 120);
                } else
                    p.translate(120, 0);
            }

            if (p.contains(e.getPoint())) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int i = getTileInteracted(e);
        if (i == -1) {
            return;
        }

        Tile t = lvl.getTiles().get(i);

        if (SwingUtilities.isRightMouseButton(e)) {
            t.setContent(TileComponent.values()[(t.getContent().ordinal() + 1) % TileComponent.values().length]);
            t.notifyObserver();
            return;
        }

        previousTile = i;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int i = getTileInteracted(e);
        if (i == -1 || previousTile == -1) {
            return;
        }

        Tile pTile = lvl.getTiles().get(previousTile);
        Tile t = lvl.getTiles().get(i);

        if (t.equals(pTile)) {
            return;
        }

        if (lvl.getTilesShape() == TileShape.SQUARE) {

            if (i == (previousTile + lvl.getSizeX())) { // i sous previous

                t.getSides()[0].setConnected(true);
                pTile.getSides()[2].setConnected(true);
                t.notifyObserver();
            } else if (i == (previousTile - lvl.getSizeX())) { // previous sous i

                t.getSides()[2].setConnected(true);
                pTile.getSides()[0].setConnected(true);
                t.notifyObserver();
            } else if (i == (previousTile + 1) && (i % lvl.getSizeX()) > 0) {

                t.getSides()[3].setConnected(true);
                pTile.getSides()[1].setConnected(true);
                t.notifyObserver();
            } else if (i == (previousTile - 1) && (previousTile % lvl.getSizeX()) > 0) {

                t.getSides()[1].setConnected(true);
                pTile.getSides()[3].setConnected(true);
                t.notifyObserver();
            }
            return;
        }

        if (i == (previousTile + lvl.getSizeX())) { // i sous previous

            t.getSides()[0].setConnected(true);
            pTile.getSides()[3].setConnected(true);
            t.notifyObserver();
        } else if (i == (previousTile - lvl.getSizeX())) { // previous sous i

            t.getSides()[3].setConnected(true);
            pTile.getSides()[0].setConnected(true);
            t.notifyObserver();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        previousTile = -1;
    }

}
