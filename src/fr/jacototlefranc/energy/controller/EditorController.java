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

    private Tile previousTile = null;

    public EditorController(BoardView bv, Level l) {
        this.lvl = l;

        bv.addMouseListener(this);
        bv.addMouseMotionListener(this);
        l.addObserver(bv);
    }

    private Tile getTileInteracted(MouseEvent e) {

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
                    return lvl.getTiles().get(i);
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
                return lvl.getTiles().get(i);
            }
        }

        return null;
    } 

    @Override
    public void mousePressed(MouseEvent e) {

        Tile t = getTileInteracted(e);
        if(t == null) {
            return;
        }

        if(SwingUtilities.isRightMouseButton(e)) {
            t.setContent(TileComponent.values()[(t.getContent().ordinal() + 1) % TileComponent.values().length]);
            t.notifyObserver();
            return;
        }

        previousTile = t;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        Tile t = getTileInteracted(e);
        if(t == null) {
            return;
        }

        if(t.equals(previousTile)) {
            return;
        }

        if(lvl.getTilesShape() == TileShape.SQUARE) {
            
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        previousTile = null;
    }

}
