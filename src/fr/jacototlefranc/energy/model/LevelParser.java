package fr.jacototlefranc.energy.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.Component;
import fr.jacototlefranc.energy.model.tile.info.TileShape;

public class LevelParser {

    public static Level parse(File f) {
        Level lvl = null;
        try {
            Scanner sc = new Scanner(f);

            int sizeX = sc.nextInt();
            int sizeY = sc.nextInt();
            TileShape shape;

            switch (sc.next().charAt(0)) {
                case 'S':
                    shape = TileShape.SQUARE;
                    break;
                case 'H':
                    shape = TileShape.HEXAGON;
                    break;
                default:
                    shape = TileShape.SQUARE;
            }

            lvl = new Level(sizeX, sizeY, shape);

            Tile t = null;
            while (sc.hasNext()) {
                int content = sc.next().charAt(0);
                if (content == '.') {

                    if (t != null)
                    lvl.addTile(t);
                    t = new Tile.TileBuilder().setShape(shape).setContent(Component.NONE).build();

                } else if (content == 'W') {

                    if (t != null)
                    lvl.addTile(t);
                    t = new Tile.TileBuilder().setShape(shape).setContent(Component.WIFI).build();

                } else if (content == 'S') {

                    if (t != null)
                    lvl.addTile(t);
                    t = new Tile.TileBuilder().setShape(shape).setContent(Component.OUTLET).build();

                } else if (content == 'L') {

                    if (t != null) {
                        lvl.addTile(t);
                    }
                    t = new Tile.TileBuilder().setShape(shape).setContent(Component.LIGHTBULB).build();

                } else {
                    t.getSides()[content - 48].setConnected(true);
                }
            }

            if (t != null)
            lvl.addTile(t);

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lvl;
    }

}
