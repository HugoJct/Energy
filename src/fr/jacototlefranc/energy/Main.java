package fr.jacototlefranc.energy;

import java.io.File;

import fr.jacototlefranc.energy.controller.MenuController;
import fr.jacototlefranc.energy.controller.PlayController;
import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.LevelParser;
import fr.jacototlefranc.energy.view.Frame;
import fr.jacototlefranc.energy.view.ingame.BoardView;
import fr.jacototlefranc.energy.view.mainmenu.MainMenu;

public class Main {
    public static void main(String[] args) {
        Frame f = new Frame("Energy");

        new MenuController(f.getQuit());

        MainMenu menu = new MainMenu();
        f.setPanel(menu);

        Level lvl = LevelParser.parse(new File("res/lvls/level7.nrg")).shuffle();
        BoardView bv = new BoardView(lvl);
        PlayController pc = new PlayController(lvl);

        menu.getPlayButton().addActionListener(e -> {
            f.setPanel(bv);
            f.getContentPane().addMouseListener(pc);
        });
    }
}