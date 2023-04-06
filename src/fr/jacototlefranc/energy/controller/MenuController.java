package fr.jacototlefranc.energy.controller;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;

public class MenuController {
    
    public MenuController(JMenuItem quit) {

        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        quit.addActionListener(e -> {
            System.exit(0);
        });
    }
}
