package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Tests for class ImageWriter
 */
class ImageWriterTest {
    /**
     * Test to create an image 800*500 of a grid 16*10 with red background and black lines
     */
    @Test
    void CreateImage() {
        ImageWriter image = new ImageWriter("red_black", 800, 500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                if (i % 50 == 0) image.writePixel(i, j, Color.BLACK);
                else if (j % 50 == 0) image.writePixel(i, j, Color.BLACK);
                else image.writePixel(i, j, new Color(255, 0, 0));
            }
        }
        image.writeToImage();
    }
}