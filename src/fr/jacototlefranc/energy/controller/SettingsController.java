package fr.jacototlefranc.energy.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.jacototlefranc.energy.model.Level;
import fr.jacototlefranc.energy.model.LevelParser;
import fr.jacototlefranc.energy.model.tile.Tile;
import fr.jacototlefranc.energy.model.tile.info.TileComponent;
import fr.jacototlefranc.energy.model.tile.info.TileShape;
import fr.jacototlefranc.energy.view.Frame;
import fr.jacototlefranc.energy.view.editor.SettingsView;
import fr.jacototlefranc.energy.view.ingame.BoardView;
import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class SettingsController {
    
    private TileShape shapeSelected;

    public SettingsController(Frame f, SettingsView settingsView) {

        shapeSelected = TileShape.SQUARE;

        settingsView.getSquare().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (shapeSelected != TileShape.SQUARE) {
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE_POWERED, true));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (shapeSelected != TileShape.SQUARE) {
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE, false));
            
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (shapeSelected != TileShape.SQUARE) {
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE_POWERED, true));
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE, false));
                    shapeSelected = TileShape.SQUARE;
                }
            }
        });

        settingsView.getHexagon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (shapeSelected != TileShape.HEXAGON) {
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE_POWERED, true));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (shapeSelected != TileShape.HEXAGON) {
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE, false));
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (shapeSelected != TileShape.HEXAGON) {
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE_POWERED, true));
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE, false));
                    shapeSelected = TileShape.HEXAGON;
                }
            }
        });

        settingsView.getContinu().addActionListener(e -> {

            Level lvl = new Level(settingsView.getX(), settingsView.getY(), this.shapeSelected);

            for(int i=0; i < lvl.getSizeX() * lvl.getSizeY(); i++) {
                lvl.addTile(new Tile.TileBuilder().setContent(TileComponent.NONE).setShape(shapeSelected).build());
            }

            JPanel editorView = new JPanel();

            JButton save = new JButton("Save");

            BoardView bv = new BoardView(lvl);

            editorView.add(save);
            editorView.add(bv);

            new EditorController(bv, lvl);
            f.setPanel(editorView);

            save.addActionListener(e1 -> {
                LevelParser.saveLevel(lvl);
            });
        });
    }
}
