package fr.jacototlefranc.energy.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.TileComponent;
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
                    t = new Tile.TileBuilder().setShape(shape).setContent(TileComponent.NONE).build();

                } else if (content == 'W') {

                    if (t != null)
                    lvl.addTile(t);
                    t = new Tile.TileBuilder().setShape(shape).setContent(TileComponent.WIFI).build();

                } else if (content == 'S') {

                    if (t != null)
                    lvl.addTile(t);
                    t = new Tile.TileBuilder().setShape(shape).setContent(TileComponent.OUTLET).build();

                } else if (content == 'L') {

                    if (t != null) {
                        lvl.addTile(t);
                    }
                    t = new Tile.TileBuilder().setShape(shape).setContent(TileComponent.LIGHTBULB).build();

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

    public static void saveLevel(Level level) {
        int sizeX = level.getSizeX();
        int sizeY = level.getSizeY();
        TileShape shape = level.getTilesShape();
        String content = sizeX + " " + sizeY + " " + shape.toString().charAt(0) + "\n";
        int indexPosX = 0;
        for (Tile tile : level.getTiles()) {
            if (indexPosX % sizeY == 0) {
                content += "\n";
            }
            if (tile.getContent() == TileComponent.NONE) {
                content += ". ";
            }
            else if (tile.getContent() == TileComponent.WIFI) {
                content += "W ";
            }
            else if (tile.getContent() == TileComponent.OUTLET) {
                content += "S ";
            }
            else if (tile.getContent() == TileComponent.LIGHTBULB) {
                content += "L ";
            }
            for (int i = 0 ; i < tile.getSides().length ; i++) {
                if (tile.getSides()[i].isConnected()) {
                    content += i + " ";
                }
            }
            indexPosX++;
        }

        File dir = new File("res/lvls");
        int nbrFiles = dir.listFiles().length;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("res/lvls/level" + (nbrFiles + 1) + ".nrg"));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
