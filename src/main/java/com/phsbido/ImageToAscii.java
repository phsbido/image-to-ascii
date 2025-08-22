package com.phsbido;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class ImageToAscii {
    public static void main(String[] args) {
        String imagePath = "C:/Users/pedro/Documents/Projeto Java/image-to-ascii/src/main/java/com/phsbido/images/image4.jpg";
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));

            int newWidth = 80;
            int newHeight = (int) Math.round((double) newWidth / image.getWidth() * image.getHeight());

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);

            convertImageToAscii(resizedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertImageToAscii(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        char[] asciiChars = {' ', '.', '*', ':', 'o', '&', '8', '#', '@'};

        for (int col = 0; col < height; col += 2) {
            for (int row = 0; row < width; row++) {
                int pixel = image.getRGB(row, col);
                int grayscale = getGrayscale(pixel);

                int index = (int) Math.round((double) grayscale / 255.0 * (asciiChars.length - 1));
                System.out.print(asciiChars[index]);
            }
            System.out.println();
        }
    }

    private static int getGrayscale(int pixel) {
        Color color = new Color(pixel);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        //0xff = 11111111
        red = (pixel >> 16) & 0xff;
        green = (pixel >> 8) & 0xff;
        blue = pixel & 0xff;

        // Luminosidade
        return (int) (0.21 * red + 0.72 * green + 0.07 * blue);
    }
}
