package fr.jacototlefranc.energy.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.jacototlefranc.energy.model.Level;

public class Frame extends JFrame {
    private JMenuBar menubar;
    private JMenu files;
    private JMenuItem quit;

    private BoardView boardView;

    public Frame(String title, Level gameLevel) {
        super(title);

        this.boardView = new BoardView(gameLevel);

        this.getContentPane().add(this.boardView);

        this.menubar = new JMenuBar();
        this.files = new JMenu("Fichier");
        this.quit = new JMenuItem("Quitter");

        this.files.add(quit);
        this.menubar.add(files);

        this.setJMenuBar(menubar);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JMenuBar getMenubar() {
        return menubar;
    }
    public JMenu getFiles() {
        return files;
    }

    public JMenuItem getQuit() {
        return quit;
    }

}
