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
    
    public SelectController(Frame frame, MainMenu menu, HashMap<String, Level> levels, SelectMenu select) {

        frame.getMenu().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
        frame.getMenu().addActionListener(e -> {
            frame.setPanel(menu);
            //frame.getContentPane().removeMouseListener(pc);
        });

        int i = 0;
        for (String s : levels.keySet()) {
            select.getButton(i).addActionListener(e -> {
                frame.setPanel(new BoardView(levels.get(s)));
                frame.getContentPane().addMouseListener(new PlayController(levels.get(s)));
            });
            i++;
        }
    }
}
