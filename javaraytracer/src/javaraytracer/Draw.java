/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer;

import java.awt.image.BufferedImage;
import java.io.File;
import javaraytracer.Strukture.Ray;
import javaraytracer.Strukture.Point;
import javaraytracer.Strukture.Vector;
import javaraytracer.Strukture.Color;
import javaraytracer.Scena.Scene;
import java.io.FileOutputStream;
import java.io.IOException;
import static javaraytracer.Javaraytracer.getColor;
import javax.imageio.ImageIO;

/**
 *
 * @author Bijesni Patuljak
 */
public class Draw {
    
    /**
     * Position of the camera is given by the coordinate
     * (0, 0, CAMERA_POSITION).
     */
    private static final int CAMERA_POSITION = -1000;
    
        /**
     * Performs ray tracing and computes each pixel of the image that is
     * saved in BMP format in the output file.
     *
     * @param outputFile Name of the output BMP image file.
     * @param scene      Scene definition
     */
    
    public static void draw(String outputFile, Scene scene) throws IOException
    {
        FileOutputStream f = new FileOutputStream(outputFile);



        int size = (scene.width * 3 + (4 - scene.width * 3 % 4) % 4) * scene.height + 54;
        //(sirina+padding)*visina + 54 bytea za header BMP

        

        // Write the BMP magic number \x42\x4D into the BMP header.
        f.write(new byte[] {'B', 'M'});

        // Write the BMP file size into the BMP header.
        f.write(size & 0x000000FF);
        f.write((size & 0x0000FF00) >>> 8);
        f.write((size & 0x00FF0000) >>> 16);
        f.write((size & 0xFF000000) >>> 24);

        // Unused bytes in the header
        f.write(new byte[] {0, 0, 0, 0});

        // Write the offset of BMP data pixels into the BMP header.
        f.write(new byte[] {0x36, 0, 0, 0});
        f.write(new byte[] {0x28, 0, 0, 0});

        // Write the width of the image (in pixels) into the BMP header.
        f.write(scene.width & 0x000000FF);
        f.write((scene.width & 0x0000FF00) >>> 8);
        f.write((scene.width & 0x00FF0000) >>> 16);
        f.write((scene.width & 0xFF000000) >>> 24);

        // Write the height of the image (in pixels) into the BMP header.
        f.write(scene.height & 0x000000FF);
        f.write((scene.height & 0x0000FF00) >>> 8);
        f.write((scene.height & 0x00FF0000) >>> 16);
        f.write((scene.height & 0xFF000000) >>> 24);

        // Write the color plane, color depth and compression related
        // information in the BMP header.
        f.write(new byte[] {1, 0});    // 1 color plane
        f.write(new byte[] {0x18, 0}); // 24 bits per pixel
        f.write(new byte[] {0, 0, 0, 0});  // No compression

        // Write the size of the raw BMP data into the BMP header.
        f.write(size-54 & 0x000000FF);
        f.write((size-54 & 0x0000FF00) >>> 8);
        f.write((size-54 & 0x00FF0000) >>> 16);
        f.write((size-54 & 0xFF000000) >>> 24);

        // Write the resolution information in the BMP header.
        f.write(new byte[] {0x13, 0x0B, 00, 00}); // Horizontal resolution
        f.write(new byte[] {0x13, 0x0B, 00, 00}); // Vertical resolution

        // Write color palette information in the BMP header.
        f.write(new byte[] {0, 0, 0, 0});  // Number of colors in the palette
        f.write(new byte[] {0, 0, 0, 0});  // All colors are important

        
        // Send a ray from each pixel of the camera and compute the
        // color of this pixel.
        for (int y = 0; y < scene.height; y++) {
            for (int x = 0; x < scene.width; x++) {

                // We start with a black pixel and add colors to this
                // pixel as find rays that determine the color of this
                // pixel.
                Color outputColor = new Color(0, 0, 0);

                // Antialiasing by 4x supersampling.
                for (double fx = x; fx < x + 1; fx += 0.5) {
                    for (double fy = y; fy < y + 1; fy += 0.5) {

                        // Each sample contributes to 1/4th of the
                        // color.
                        double sampleRatio = 0.25;

                        // Cast a ray from a point on the camera and
                        // perpendicular to the plane of the camera,
                        // i.e. XY plane.
                        Ray viewRay = new Ray(new Point(fx, fy,
                                                        CAMERA_POSITION),
                                              new Vector(0, 0, 1));
                        Color color = getColor(viewRay, scene);

                        // Alter the pixel color depending on exposure.
                        color.alterAsPerExposure(1.0);

                        outputColor.add(Color.multiply(sampleRatio, color));
                    }
                }

                // Gamma correction
                outputColor.srgbEncode();

                // Write the bytes for the pixel into the BMP file.
                f.write(outputColor.toBytesForBMP());
            }

            // After the bytes for each row of pixels is written into
            // the BMP file, pad it with null bytes so as to make the
            // number of bytes per row a multiple of 4.
            for (int i = 0; i < (4 - scene.width * 3 % 4) % 4; i++) {
                f.write(0);
            }

        }

        
        f.close();
        File input = new File("output.bmp");  

        //Read the file to a BufferedImage  
        BufferedImage image = ImageIO.read(input);

        //Create a file for the output  
        File output = new File("output.jpg");
        ImageIO.write(image, "jpg", output);
    }
}
