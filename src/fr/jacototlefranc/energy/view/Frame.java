package fr.jacototlefranc.energy.view;

import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.jacototlefranc.energy.controller.PlayController;
import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.LevelParser;

public class Frame extends JFrame {

    public Frame(String title) {
        super(title);

        File f = new File("res/lvls/level6.nrg");
        Level lvl = LevelParser.parse(f);
        lvl.shuffle();

        BoardView bv = new BoardView(lvl);
        PlayController ctrl = new PlayController(lvl);
        this.getContentPane().addMouseListener(ctrl);

        this.getContentPane().add(bv);

        JMenuBar menubar = new JMenuBar();
        JMenu files = new JMenu("Fichier");
        JMenuItem quit = new JMenuItem("Quitter");

        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        quit.addActionListener(e -> {
            System.exit(0);
        });

        files.add(quit);
        menubar.add(files);

        this.setJMenuBar(menubar);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
