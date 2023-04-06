package fr.jacototlefranc.energy.controller;

import javax.swing.KeyStroke;

import fr.jacototlefranc.energy.view.Frame;
import fr.jacototlefranc.energy.view.ingame.BoardView;
import fr.jacototlefranc.energy.view.mainmenu.MainMenu;

import java.awt.event.KeyEvent;

public class MenuController {
    
    public MenuController(Frame frame, BoardView bv, PlayController pc) {

        MainMenu menu = new MainMenu();

        frame.setPanel(menu);

        menu.getPlayButton().addActionListener(e -> {
            frame.setPanel(bv);
            frame.getContentPane().addMouseListener(pc);
        });

        frame.getQuit().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        frame.getQuit().addActionListener(e -> {
            System.exit(0);
        });
        frame.getMenu().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
        frame.getMenu().addActionListener(e -> {
            frame.setPanel(menu);
            frame.getContentPane().removeMouseListener(pc);
        });
   

    }
}
