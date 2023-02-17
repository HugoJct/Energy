package fr.jacototlefranc.energy.controller;

import java.io.File;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.LevelParser;
import fr.jacototlefranc.energy.view.Frame;

public class Game {
    private Frame gameView;
    private File gameFile;
    private Level gameLevel;
    private PlayController gameController;
    private MenuController menuController;

    public Game(String name) {
        this.gameFile = new File("res/lvls/level8.nrg");
        this.gameLevel = LevelParser.parse(this.gameFile);
        this.gameView = new Frame(name, this.gameLevel);

        this.gameLevel.shuffle();

        // this.gameController = new PlayController(this.gameLevel);

        //this.gameView.getContentPane().addMouseListener(this.gameController);
        this.menuController = new MenuController(this.gameView.getQuit());
    }
}
