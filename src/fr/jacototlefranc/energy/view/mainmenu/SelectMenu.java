package fr.jacototlefranc.energy.view.mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class SelectMenu extends JPanel {
    
    private JButton[] levelsListButtons;

    public SelectMenu(ArrayList<String> levelsList) {
        super();

        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.BLACK);

        JLabel outletLabel1 = new JLabel();
        JLabel outletLabel2 = new JLabel();

        TextureManager tm = new TextureManager();

        BufferedImage outletLit = tm.getTexture(TextureName.SQUARE_LIGHTBULB, true);
        ImageIcon outletIconLit = new ImageIcon(outletLit);

        BufferedImage outlet = tm.getTexture(TextureName.SQUARE_LIGHTBULB, false);
        ImageIcon outletIcon = new ImageIcon(outlet);

        /*
         * Button area
         */

         JPanel box = new JPanel();
         box.setBackground(Color.BLACK);
         box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        //  box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

         MouseAdapter hover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                outletLabel1.setIcon(outletIconLit);
                outletLabel2.setIcon(outletIconLit);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                outletLabel1.setIcon(outletIcon);
                outletLabel2.setIcon(outletIcon);
            }
        };

        this.levelsListButtons = new JButton[levelsList.size()];

        for (int i = 0 ; i < levelsList.size() ; i++) {
            this.levelsListButtons[i] = new JButton(levelsList.get(i));
            this.levelsListButtons[i].setBackground(Color.BLACK);
            this.levelsListButtons[i].setForeground(Color.WHITE);
            this.levelsListButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            this.levelsListButtons[i].setMinimumSize(new Dimension(150, 60));
            this.levelsListButtons[i].setPreferredSize(new Dimension(200, 60));
            this.levelsListButtons[i].setMaximumSize(new Dimension(200, 100));
            this.levelsListButtons[i].setFocusPainted(false);
            this.levelsListButtons[i].addMouseListener(hover);
            box.add(this.levelsListButtons[i]);
        }

        BufferedImage logo2 = null;
        try {
            logo2 = ImageIO.read(new File("res/tex/logo_white.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * decorations
         */

         outletLabel1.setIcon(outletIcon);
         outletLabel2.setIcon(outletIcon);


        /*
         * Reunion
         */
        JPanel buttonsAndDeco = new JPanel();
        buttonsAndDeco.setBackground(Color.BLACK);
        buttonsAndDeco.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        buttonsAndDeco.setLayout(new BoxLayout(buttonsAndDeco, BoxLayout.LINE_AXIS));

        JScrollPane scroll = new JScrollPane(box);

        buttonsAndDeco.add(outletLabel1);
        buttonsAndDeco.add(Box.createHorizontalGlue());
        buttonsAndDeco.add(scroll);
        buttonsAndDeco.add(Box.createHorizontalGlue());
        buttonsAndDeco.add(outletLabel2);

        /*
         * Copyrights area
         */

        JLabel copyrights = new JLabel("Hugo JACOTOT & Matthieu LE FRANC", JLabel.CENTER);
        copyrights.setForeground(Color.GRAY);

        ImageIcon logoIcon = new ImageIcon(logo2.getScaledInstance(logo2.getWidth() / 2, logo2.getHeight() / 2, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(logoLabel, BorderLayout.NORTH);
        this.add(buttonsAndDeco, BorderLayout.CENTER);
        this.add(copyrights, BorderLayout.SOUTH);
    
    }

    public JButton getButton(int i) {
        return this.levelsListButtons[i];
    }

}
