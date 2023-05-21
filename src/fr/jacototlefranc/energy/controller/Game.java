package fr.jacototlefranc.energy.controller;

import java.io.File;

import javax.swing.JFrame;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.LevelParser;
import fr.jacototlefranc.energy.view.Frame;
import fr.jacototlefranc.energy.view.ingame.BoardView;
import fr.jacototlefranc.energy.view.mainmenu.MainMenu;

public class Game {
    private Frame gameView;
    private File gameFile;
    private Level gameLevel;
    private PlayController gameController;
    private MenuController menuController;

    public Game(String name) {

        JFrame test = new JFrame("Energy");
        MainMenu menu = new MainMenu();
        test.setVisible(true);
        test.getContentPane().add(menu);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.pack();

        menu.getPlayButton().addActionListener(e -> {

            test.getContentPane().remove(menu);

            this.gameFile = new File("res/lvls/level8.nrg");
            this.gameLevel = LevelParser.parse(this.gameFile);
            this.gameLevel.shuffle();

            BoardView bv = new BoardView(this.gameLevel);
            test.getContentPane().add(bv);
            test.pack();

            this.gameController = new PlayController(this.gameLevel);

            test.getContentPane().addMouseListener(this.gameController);
        });
    }
}
