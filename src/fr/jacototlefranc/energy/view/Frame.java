package fr.jacototlefranc.energy.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Frame extends JFrame {
    private JMenuBar menubar;
    private JMenu files;
    private JMenuItem quit;
    private JMenuItem menu;

    public Frame(String title) {
        super(title);

        this.menubar = new JMenuBar();
        this.files = new JMenu("Fichier");
        this.quit = new JMenuItem("Quitter");
        this.menu = new JMenuItem("Menu");

        this.files.add(quit);
        this.files.add(menu);
        this.menubar.add(files);

        this.setJMenuBar(menubar);
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
    public JMenuItem getMenu() {
        return menu;
    }

    public void setPanel(JPanel p) {
        this.getContentPane().removeAll();
        this.getContentPane().add(p);
        this.pack();
    }

}
