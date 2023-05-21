package fr.jacototlefranc.energy.view.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class SettingsView extends JPanel {
    private JSpinner width;
    private JSpinner height;
    private JRadioButton square;
    private JRadioButton hexagon;
    private ButtonGroup shape;
    private JButton continu;

    public SettingsView() {
        super();
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.width = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        this.height = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        this.square = new JRadioButton(TextureManager.getIcon(TextureName.SQUARE_OUTLINE_POWERED, true));
        this.hexagon = new JRadioButton(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE, true));
        this.shape = new ButtonGroup();
        this.continu = new JButton("continuer");

        this.square.setBorder(BorderFactory.createEmptyBorder());
        this.square.setPreferredSize(new Dimension(TextureManager.getIcon(TextureName.SQUARE_OUTLINE, true).getIconWidth(), TextureManager.getIcon(TextureName.SQUARE_OUTLINE, true).getIconHeight()));
        this.square.setBackground(Color.BLACK);
        this.hexagon.setBorder(BorderFactory.createEmptyBorder());
        this.hexagon.setPreferredSize(new Dimension(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE, true).getIconWidth(), TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE, true).getIconHeight()));
        this.hexagon.setBackground(Color.BLACK);

        this.shape.add(this.square);
        this.shape.add(this.hexagon);

        this.shape.setSelected(this.square.getModel(), true);

        this.add(this.width);
        this.add(this.height);
        this.add(this.square, BorderLayout.CENTER);
        this.add(this.hexagon, BorderLayout.CENTER);
        this.add(this.continu);

        this.setVisible(true);

    }

    public int getX() {
        return (int) width.getValue();
    }

    public int getY() {
        return (int) height.getValue();
    }

    public JRadioButton getSquare() {
        return square;
    }
    public JRadioButton getHexagon() {
        return hexagon;
    }

    public JButton getContinu() {
        return continu;
    }
}
