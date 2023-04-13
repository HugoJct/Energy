package fr.jacototlefranc.energy.controller;

import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.TileComponent;
import fr.jacototlefranc.energy.model.tile.info.TileShape;

public class PlayController extends MouseAdapter {

    private Level lvl;

    public PlayController(Level l) {
        this.lvl = l;
        lvl.updateTilesProperties();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

            for (int i = 0; i < tileCount; i++) {

                if(i > 0) {

                    if(i % ymax == 0) {
                        if(driftedDown) {
                            ph.translate((-91) * (ymax-1), 52);
                            driftedDown = false;
                        } else {
                            ph.translate((-91) * (ymax-1), 104);
                        }
                    } else if((i % ymax) % 2 == 0) {
                        if(driftedDown) {
                            ph.translate(91, -52);
                            driftedDown = false;
                        } else {
                            ph.translate(91, 0);
                        }
                    } else if((i % ymax) % 2 == 1) {
                        if(driftedDown) {
                            ph.translate(91, 0);
                        } else {
                            ph.translate(91, 52);
                            driftedDown = true;
                        }
                    }
                }

                if (ph.contains(e.getPoint())) {
                    Tile t = lvl.getTiles().get(i);
                    if(t.getContent() != TileComponent.OUTLET)
                        t.rotate();
                    return;
                }
            }
            return;
        }

        Polygon p = new Polygon();
        p.addPoint(0, 0);
        p.addPoint(120, 0);
        p.addPoint(120, 120);
        p.addPoint(0, 120);

        for (int i = 0; i < tileCount; i++) {

            if (i > 0) {
                if (i % ymax == 0) {
                    p.translate((-120) * (ymax - 1), 120);
                } else
                    p.translate(120, 0);
            }

            if (p.contains(e.getPoint())) {
                Tile t = lvl.getTiles().get(i);
                if(t.getContent() != TileComponent.OUTLET)
                    t.rotate();
                lvl.updateTilesProperties();
                if (lvl.isWinning())
                    System.out.println("You win !");
                return;
            }

        }
    }
}
