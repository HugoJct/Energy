package fr.jacototlefranc.energy.view.textures;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import fr.jacototlefranc.energy.model.tile.info.TileProps;

public final class TextureManager {

    // components
    private static BufferedImage square_outlet;
    private static BufferedImage square_wifi;
    private static BufferedImage square_lightbulb;
    private static BufferedImage square_wifi_powered;
    private static BufferedImage square_lightbulb_powered;

    private static BufferedImage hexagonal_outlet;
    private static BufferedImage hexagonal_wifi;
    private static BufferedImage hexagonal_wifi_powered;
    private static BufferedImage hexagonal_lightbulb;
    private static BufferedImage hexagonal_lightbulb_powered;

    // link from component to tile edge
    private static BufferedImage square_component_link;
    private static BufferedImage square_component_link_powered;

    private static BufferedImage square_curve_link;
    private static BufferedImage square_curve_link_powered;

    private static BufferedImage square_link_long;
    private static BufferedImage square_link_long_powered;

    private static BufferedImage hexagonal_component_link;
    private static BufferedImage hexagonal_component_link_powered;

    private static BufferedImage hexagonal_link_long;
    private static BufferedImage hexagonal_link_long_powered;

    private static BufferedImage hexagonal_curve_link_short;
    private static BufferedImage hexagonal_curve_link_long;
    private static BufferedImage hexagonal_curve_link_short_powered;
    private static BufferedImage hexagonal_curve_link_long_powered;

    // tile outline
    private static BufferedImage square_outline;
    private static BufferedImage hexagonal_outline;
    private static BufferedImage square_outline_powered;
    private static BufferedImage hexagonal_outline_powered;

    private static ImageIcon square_outline_icon;
    private static ImageIcon hexagonal_outline_icon;
    private static ImageIcon square_outline_powered_icon;
    private static ImageIcon hexagonal_outline_powered_icon;

    static {
        try {
            BufferedImage canva = ImageIO.read(new File("res/tex/tuiles.png"));

            square_outlet = canva.getSubimage(0, TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_outlet = canva.getSubimage(TileProps.TILE_SIZE * 3, TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            square_wifi = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            square_wifi_powered = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            hexagonal_wifi = canva.getSubimage(TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            hexagonal_wifi_powered = canva.getSubimage(TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE,
            TileProps.TILE_SIZE);

            square_lightbulb = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            square_lightbulb_powered = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE * 4,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);

            hexagonal_lightbulb = canva.getSubimage(TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            hexagonal_lightbulb_powered = canva.getSubimage(TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE,
            TileProps.TILE_SIZE);

            /***************************************
             * COMPONENT LINKS
             **********************************************/
            square_component_link = canva.getSubimage(0, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            square_component_link_powered = canva.getSubimage(0, TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            hexagonal_component_link = square_component_link;
            hexagonal_component_link_powered = square_component_link_powered;
            /**************************************************************************************************/

            /*****************************************
             * STRAIGHT LINES
             ********************************************/
            square_link_long = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            square_link_long_powered = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE * 5,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);

            hexagonal_link_long = canva.getSubimage(TileProps.TILE_SIZE * 6, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE,
            TileProps.TILE_SIZE);
            hexagonal_link_long_powered = canva.getSubimage(TileProps.TILE_SIZE * 6, TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE,
            TileProps.TILE_SIZE);
            /**************************************************************************************************/

            /***************************************
             * CURVED LINES
             ********************************************/
            square_curve_link = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            square_curve_link_powered = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE * 5,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);

            hexagonal_curve_link_short = canva.getSubimage(TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE * 2,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_curve_link_long = canva.getSubimage(TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE * 2,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_curve_link_short_powered = canva.getSubimage(TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE * 5,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_curve_link_long_powered = canva.getSubimage(TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE * 5,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            /**************************************************************************************************/

            /*******************************************
             * OUTLINES
             *********************************************/
            square_outline = canva.getSubimage(0, 0, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_outline = canva.getSubimage(TileProps.TILE_SIZE * 3, 0, TileProps.TILE_SIZE, TileProps.TILE_SIZE);

            square_outline_powered = canva.getSubimage(0, TileProps.TILE_SIZE * 3, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            hexagonal_outline_powered = canva.getSubimage(TileProps.TILE_SIZE * 3, TileProps.TILE_SIZE * 3,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            /**************************************************************************************************/

            square_outline_icon = new ImageIcon(square_outline);
            hexagonal_outline_icon = new ImageIcon(hexagonal_outline);
            square_outline_powered_icon = new ImageIcon(square_outline_powered);
            hexagonal_outline_powered_icon = new ImageIcon(hexagonal_outline_powered);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon getIcon(TextureName name, boolean powered) {
        if (name == TextureName.SQUARE_OUTLINE) {
            return square_outline_icon;
        } else if (name == TextureName.HEXAGONAL_OUTLINE) {
            return hexagonal_outline_icon;
        } else if (name == TextureName.SQUARE_OUTLINE_POWERED) {
            return square_outline_powered_icon;
        } else if (name == TextureName.HEXAGONAL_OUTLINE_POWERED) {
            return hexagonal_outline_powered_icon;
        }
        return null;
    }

    public static BufferedImage getTexture(TextureName name, boolean powered) {
        if (name == TextureName.SQUARE_OUTLET) {
            return square_outlet;
        } else if (name == TextureName.HEXAGONAL_OUTLET) {
            return hexagonal_outlet;
        }

        if (!powered) {
            switch (name) {
                case SQUARE_WIFI:
                    return square_wifi;
                case SQUARE_LIGHTBULB:
                    return square_lightbulb;
                case SQUARE_COMPONENT_LINK:
                    return square_component_link;
                case SQUARE_LINK_LONG:
                    return square_link_long;
                case SQUARE_CURVE_LINK:
                    return square_curve_link;
                case SQUARE_OUTLINE:
                    return square_outline;
                case HEXAGONAL_WIFI:
                    return hexagonal_wifi;
                case HEXAGONAL_LIGHTBULB:
                    return hexagonal_lightbulb;
                case HEXAGONAL_COMPONENT_LINK:
                    return hexagonal_component_link;
                case HEXAGONAL_LINK_LONG:
                    return hexagonal_link_long;
                case HEXAGONAL_CURVE_LINK_SHORT:
                    return hexagonal_curve_link_short;
                case HEXAGONAL_CURVE_LINK_LONG:
                    return hexagonal_curve_link_long;
                case HEXAGONAL_OUTLINE:
                    return hexagonal_outline;
                case HEXAGONAL_OUTLINE_POWERED:
                    return hexagonal_outline_powered;
                default:
                    break;
            }
        } else {
            switch (name) {
                case SQUARE_WIFI:
                    return square_wifi_powered;
                case SQUARE_LIGHTBULB:
                    return square_lightbulb_powered;
                case SQUARE_COMPONENT_LINK:
                    return square_component_link_powered;
                case SQUARE_LINK_LONG:
                    return square_link_long_powered;
                case SQUARE_CURVE_LINK:
                    return square_curve_link_powered;
                case SQUARE_OUTLINE:
                    return square_outline_powered;
                case HEXAGONAL_WIFI:
                    return hexagonal_wifi_powered;
                case HEXAGONAL_LIGHTBULB:
                    return hexagonal_lightbulb_powered;
                case HEXAGONAL_COMPONENT_LINK:
                    return hexagonal_component_link_powered;
                case HEXAGONAL_LINK_LONG:
                    return hexagonal_link_long_powered;
                case HEXAGONAL_CURVE_LINK_SHORT:
                    return hexagonal_curve_link_short_powered;
                case HEXAGONAL_CURVE_LINK_LONG:
                    return hexagonal_curve_link_long_powered;
                case HEXAGONAL_OUTLINE:
                    return hexagonal_outline_powered;
                default:
                    break;
            }
        }
        return null;
    }

}
