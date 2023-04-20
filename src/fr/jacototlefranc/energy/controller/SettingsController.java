package fr.jacototlefranc.energy.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.jacototlefranc.energy.view.editor.SettingsView;
import fr.jacototlefranc.energy.view.textures.TextureManager;
import fr.jacototlefranc.energy.view.textures.TextureName;

public class SettingsController {
    
    private String shapeSelected;

    public SettingsController(SettingsView settingsView) {

        shapeSelected = "square";

        settingsView.getSquare().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (shapeSelected != "square") {
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE_POWERED, true));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (shapeSelected != "square") {
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE, false));
            
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (shapeSelected != "square") {
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE_POWERED, true));
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE, false));
                    shapeSelected = "square";
                }
            }
        });

        settingsView.getHexagon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (shapeSelected != "hexagon") {
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE_POWERED, true));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (shapeSelected != "hexagon") {
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE, false));
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (shapeSelected != "hexagon") {
                    settingsView.getHexagon().setIcon(TextureManager.getIcon(TextureName.HEXAGONAL_OUTLINE_POWERED, true));
                    settingsView.getSquare().setIcon(TextureManager.getIcon(TextureName.SQUARE_OUTLINE, false));
                    shapeSelected = "hexagon";
                }
            }
        });

        settingsView.getContinu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("continuer");
            }
        });
    }
}
