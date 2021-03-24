package com.ksa.voetje.debug.graphicsContextDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFrame {

    public ImageFrame(BufferedImage image) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame editorFrame = new JFrame("Graphics Context");
                JPanel content = new JPanel();
                JScrollPane scrollableEditorFrame = new JScrollPane(content);
                editorFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                ImageIcon imageIcon = new ImageIcon(image);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                content.add(jLabel, BorderLayout.CENTER);
                editorFrame.getContentPane().add(scrollableEditorFrame);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            }
        });
    }
}
