package fr.jacototlefranc.energy.controller;

import javax.swing.KeyStroke;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.view.Frame;
import fr.jacototlefranc.energy.view.editor.SettingsView;
import fr.jacototlefranc.energy.view.mainmenu.MainMenu;
import fr.jacototlefranc.energy.view.mainmenu.SelectMenu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuController {
    
    public MenuController(Frame frame, HashMap<String, Level> levels) {

        MainMenu menu = new MainMenu();
        SettingsView settingsView = new SettingsView();
        SelectMenu select = new SelectMenu(new ArrayList<String>(levels.keySet()));
        new SelectController(frame, menu, levels, select);
        new SettingsController(frame, settingsView);
        frame.setPanel(menu);

        menu.getPlayButton().addActionListener(e -> {
            frame.setPanel(select);
        });

        menu.getEditButton().addActionListener(e -> {
            frame.setPanel(settingsView);
        });

        frame.getQuit().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        frame.getQuit().addActionListener(e -> {
            System.exit(0);
        });
   
    }
}
