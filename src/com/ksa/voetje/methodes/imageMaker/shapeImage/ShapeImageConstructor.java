package com.ksa.voetje.methodes.imageMaker.shapeImage;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.debug.graphicsContextDisplay.ImageFrame;
import com.ksa.voetje.instellingen.DebugInstellingen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShapeImageConstructor {
    // streepjes en curves
    private final int width;
    private final int height;
    private int numberOfLines = 0;

    // Image writer
    private final BufferedImage bufferedImage;

    // Graphics context
    private final Graphics2D g2d;

    // Shape color
    private Color color = Color.BLACK;

    /**
     * Creates an image constructor that displays shapes on a white background
     * @param width of the image
     * @param height of the image
     */
    public ShapeImageConstructor(int width, int height) {
        System.out.println("== ShapeImageConstructor initialized ==");
        this.width = width;
        this.height = height;

        // Constructs a BufferedImage of one of the predefined image types.
        this.bufferedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        if (DebugInstellingen.showGraphicsWindow)
            new ImageFrame(bufferedImage); // debug
        // Create a graphics which can be used to draw into the buffered image
        this.g2d = this.bufferedImage.createGraphics();

        // fill all the image with white
        this.g2d.setColor(Color.WHITE);
        this.g2d.fillRect(0, 0, this.width, this.height);

        this.g2d.setColor(Color.BLACK);
        this.g2d.setStroke(new BasicStroke(5));
    }


    /**
     *
     * @param shapeType 0 = stripe, 1 = slope
     * @param x1 x coordinate 1
     * @param y1 y coordinate 1
     * @param x2 x coordinate 2
     * @param y2 y coordinate 2
     */
    public void drawShape(int shapeType, int x1, int y1, int x2, int y2) {
        System.out.println("Drawing shape...");
        if (shapeType == 0)
            g2d.drawLine(x1,y1,x2,y2);
        else if (shapeType == 1) {
            /*
            (x1, x2) = upper left corner
            x2 = width
            y2 = height
             */
            g2d.drawArc(x1, y1, x2, y2, 0, 90);
            g2d.drawArc(x1, y1, x2, y2, 0, -90);
        }
        else
            Voetje.getLogWindow().addToLog("invalid shape type");
        System.out.println("Shape drawn.");
    }

    /**
     * Sets the color of the shapes to be drawn
     * @param color the color of the shapes
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Disposes of the graphics context so that it does not take up
     * any resources.
     */
    public void disposeOfGraphicsContext() {
        g2d.dispose();
    }

    /**
     * Checks if a file defined by a pathname already exists
     * @param pathname of the file
     * @return true if the file exists, otherwise false
     */
    public boolean checkIfFileExists(String pathname) {
        File file = new File(pathname);
        return file.exists();
    }

    /**
     * Saves the image as a png file
     * @param pathname path of the image to be saved (contains .png)
     * @throws IOException
     */
    public void saveAsPng(String pathname) throws IOException {
        File file = new File(pathname);
        ImageIO.write(bufferedImage, "png", file);
    }

    /**
     * Saves the image as a jpg file
     * @param pathname path of the image to be saved (contains .jpg)
     * @throws IOException
     */
    public void saveAsJpg(String pathname) throws IOException {
        File file = new File(pathname);
        ImageIO.write(bufferedImage, "jpg", file);
    }

    // Getters for width and height of the image
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
