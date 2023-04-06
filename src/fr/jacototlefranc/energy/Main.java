package fr.jacototlefranc.energy;

import java.io.File;
import java.util.HashMap;

import fr.jacototlefranc.energy.controller.MenuController;
import fr.jacototlefranc.energy.controller.PlayController;
import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.LevelParser;
import fr.jacototlefranc.energy.view.Frame;
import fr.jacototlefranc.energy.view.ingame.BoardView;

public class Main {

    private static final HashMap<String, Level> levels;

    static {
        File dir = new File("res/lvls");
        File[] files = dir.listFiles();
        levels = new HashMap<String, Level>();
        for (File f : files) {
            if (f.getName().endsWith(".nrg")) {
                levels.put(f.getName(), LevelParser.parse(f).shuffle());
            }
        }
    }

    public static void main(String[] args) {
        Frame f = new Frame("Energy");
        
        BoardView bv = new BoardView(levels.get("level7.nrg"));
        PlayController pc = new PlayController(levels.get("level7.nrg"));

        new MenuController(f, bv, pc);
    }
}