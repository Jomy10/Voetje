package com.ksa.voetje.methodes.imageMaker.textImage;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.debug.graphicsContextDisplay.ImageFrame;
import com.ksa.voetje.instellingen.DebugInstellingen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.jetbrains.annotations.Debug;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TextImageConstructor {
    private final int width;
    private final int height;
    private int numberOfLines = 0;
    private int debug = 0;

    // Image writer
    private final BufferedImage bufferedImage;

    // Graphics context
    private final Graphics2D g2d;

    // Font
    private Font font;
    private Color color = Color.BLACK;

    /**
     * Creates an image constructor that displays text on a white background
     * @param width of the image
     * @param height of the image
     */
    public TextImageConstructor(int width, int height) {
        // height & width of the image
        this.width = width;
        this.height = height;

        // Constructs a BufferedImage of one of the predefined image types.
        this.bufferedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        if (DebugInstellingen.showGraphicsWindow)
            new ImageFrame(bufferedImage); // Creates and shows a JFrame that shows the current image being worked on

        // Create a graphics which can be used to draw into the buffered image
        this.g2d = this.bufferedImage.createGraphics();

        // fill all the image with white
        this.g2d.setColor(Color.WHITE);
        this.g2d.fillRect(0, 0, width, height);
    }

    /**
     * Types text on the next line
     * @param content the text to be typed onto the image
     */
    public void typeNextLine(String content) {
        int margin = 25;
        numberOfLines++;
        int temp = font.getSize() + margin * numberOfLines;
        System.out.println("typing on (" + margin + ", " + temp + ")");
        g2d.drawString(content, margin, (font.getSize() + margin) * numberOfLines);
    }

    /** Sets the font and the text color
     * @param font contains the font family, type and size
     * @param color color of the text
     */
    public void setFont(Font font, Color color) {
        this.font = font;
        g2d.setFont(font);
        this.color = color;
        g2d.setColor(color);
    }

    /**
     * Disposes of the graphics context so that it does not take up
     * any resources.
     */
    public void disposeOfGraphicsContext() {
        g2d.dispose();
    }

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

    public static void main(String[] args) throws IOException {
        TextImageConstructor textImageConstructor = new TextImageConstructor(2480, 3507);
        textImageConstructor.setFont(new Font("Raamgeheimschrift", Font.PLAIN, 90), Color.BLACK);
        // textImageConstructor.g2d.setFont(new Font("Raamgeheimschrift", Font.PLAIN, 90));
        // textImageConstructor.g2d.setColor(Color.black);
        textImageConstructor.g2d.drawString("content", 25, 115);

        textImageConstructor.disposeOfGraphicsContext();

        textImageConstructor.saveAsPng("testTextConstructor.png");

    }
}
