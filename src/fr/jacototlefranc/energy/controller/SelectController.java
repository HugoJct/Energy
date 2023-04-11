package fr.jacototlefranc.energy.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.KeyStroke;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.view.Frame;
import fr.jacototlefranc.energy.view.ingame.BoardView;
import fr.jacototlefranc.energy.view.mainmenu.MainMenu;
import fr.jacototlefranc.energy.view.mainmenu.SelectMenu;

public class SelectController {
    private Frame frame;
    private HashMap<String, Level> levels;
    private PlayController playController;

    public SelectController(Frame frame, MainMenu menu, HashMap<String, Level> levels, SelectMenu select) {

        this.frame = frame;
        this.levels = levels;

        frame.getMenu().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
        frame.getMenu().addActionListener(e -> {
            if (playController != null) {
                frame.getContentPane().removeMouseListener(playController);
                frame.setPanel(menu);
            }
        });

        int i = 0;
        for (String s : levels.keySet()) {
            select.getButton(i).addActionListener(e -> {
                buttonClicked(s);
            });
            i++;
        }
    }

    private void buttonClicked(String s) {
        playController = new PlayController(levels.get(s));
        frame.setPanel(new BoardView(levels.get(s)));
        frame.getContentPane().addMouseListener(playController);
    }
}
