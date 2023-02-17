package fr.jacototlefranc.energy.view.textures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import fr.jacototlefranc.energy.model.tile.info.TileProps;

public final class TextureManager {

    // components
    private BufferedImage square_outlet;
    private BufferedImage square_wifi;
    private BufferedImage square_lightbulb;
    private BufferedImage square_wifi_powered;
    private BufferedImage square_lightbulb_powered;

    private BufferedImage hexagonal_outlet;
    private BufferedImage hexagonal_wifi;
    private BufferedImage hexagonal_lightbulb;

    // link from component to tile edge
    private BufferedImage square_component_link;
    private BufferedImage square_component_link_powered;

    private BufferedImage square_curve_link;
    private BufferedImage square_curve_link_powered;

    private BufferedImage square_link_long;
    private BufferedImage square_link_long_powered;

    private BufferedImage hexagonal_component_link;

    private BufferedImage hexagonal_curve_link_short;
    private BufferedImage hexagonal_curve_link_long;
    private BufferedImage hexagonal_curve_link_short_powered;
    private BufferedImage hexagonal_curve_link_long_powered;

    // tile outline
    private BufferedImage square_outline;
    private BufferedImage hexagonal_outline;
    private BufferedImage square_outline_powered;
    private BufferedImage hexagonal_outline_powered;

    public TextureManager() {
        try {
            BufferedImage canva = ImageIO.read(new File("res/tex/tuiles.png"));

            square_outlet = canva.getSubimage(0, TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_outlet = canva.getSubimage(TileProps.TILE_SIZE * 3, TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            square_wifi = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            square_wifi_powered = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            hexagonal_wifi = canva.getSubimage(TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            square_lightbulb = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            square_lightbulb_powered = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE * 5,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_lightbulb = canva.getSubimage(TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            /***************************************
             * COMPONENT LINKS
             **********************************************/
            square_component_link = canva.getSubimage(0, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            square_component_link_powered = canva.getSubimage(0, TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            hexagonal_component_link = square_component_link;
            /**************************************************************************************************/

            /*****************************************
             * STRAIGHT LINES
             ********************************************/
            square_link_long = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);

            square_link_long_powered = canva.getSubimage(TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE * 5,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            /**************************************************************************************************/

            /***************************************
             * CURVED LINES
             ********************************************/
            square_curve_link = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE,
                    TileProps.TILE_SIZE);
            square_curve_link_powered = canva.getSubimage(TileProps.TILE_SIZE, TileProps.TILE_SIZE * 5,
                    TileProps.TILE_SIZE, TileProps.TILE_SIZE);

            hexagonal_curve_link_short = canva.getSubimage(TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_curve_link_long = canva.getSubimage(TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE * 2, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_curve_link_short_powered = canva.getSubimage(TileProps.TILE_SIZE * 4, TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
            hexagonal_curve_link_long_powered = canva.getSubimage(TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE * 5, TileProps.TILE_SIZE, TileProps.TILE_SIZE);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage rotateImage(BufferedImage img) {
        BufferedImage newImage = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                newImage.setRGB(img.getHeight() - 1 - j, i, img.getRGB(i, j));
            }
        }

        return newImage;
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        // g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
    }

    public BufferedImage getTexture(TextureName name, boolean powered) {
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
                    return hexagonal_wifi;
                case HEXAGONAL_LIGHTBULB:
                    return hexagonal_lightbulb;
                case HEXAGONAL_COMPONENT_LINK:
                    return hexagonal_component_link;
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
