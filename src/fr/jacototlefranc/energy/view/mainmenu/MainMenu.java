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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class MainMenu extends JPanel {

    private JButton playButton;
    private JButton editButton;
    private JButton quitButton;

    public MainMenu() {
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
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        playButton = new JButton("Jouer");
        playButton.setBackground(Color.BLACK);
        playButton.setForeground(Color.WHITE);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setMinimumSize(new Dimension(200, 40));
        playButton.setPreferredSize(new Dimension(300, 60));
        playButton.setMaximumSize(new Dimension(400, 100));
        playButton.setFocusPainted(false);
        playButton.addMouseListener(hover);

        editButton = new JButton("Éditeur");
        editButton.setBackground(Color.BLACK);
        editButton.setForeground(Color.WHITE);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editButton.setMinimumSize(new Dimension(200, 40));
        editButton.setPreferredSize(new Dimension(300, 60));
        editButton.setMaximumSize(new Dimension(400, 100));
        editButton.setFocusPainted(false);
        editButton.addMouseListener(hover);

        quitButton = new JButton("Quitter");
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setMinimumSize(new Dimension(200, 40));
        quitButton.setPreferredSize(new Dimension(300, 60));
        quitButton.setMaximumSize(new Dimension(400, 100));
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(hover);
        quitButton.addActionListener(e -> System.exit(0));

        BufferedImage logo2 = null;
        try {
            logo2 = ImageIO.read(new File("res/tex/logo_white.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        box.add(playButton);
        box.add(Box.createVerticalGlue());
        box.add(editButton);
        box.add(Box.createVerticalGlue());
        box.add(quitButton);

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

        buttonsAndDeco.add(outletLabel1);
        box.add(Box.createHorizontalGlue());
        buttonsAndDeco.add(box);
        box.add(Box.createHorizontalGlue());
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

    public JButton getPlayButton() {
        return this.playButton;
    }

    public JButton getEditButton() {
        return this.editButton;
    }
}
