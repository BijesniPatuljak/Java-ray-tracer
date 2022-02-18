/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
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
    

    private static final int kamera = -1000;
    

    
    public static void draw(String outputFile, Scene scene) throws IOException
    {
        
        long start = System.nanoTime();
        BufferedOutputStream f = new BufferedOutputStream(new FileOutputStream(outputFile));
        


        int size = (scene.width * 3 + (4 - scene.width * 3 % 4) % 4) * scene.height + 54;
        //(sirina+padding)*visina + 54 bytea za header BMP

        

        // Header BMP-a
        f.write(new byte[] {'B', 'M'});
        f.write(size & 0x000000FF);
        f.write((size & 0x0000FF00) >>> 8);
        f.write((size & 0x00FF0000) >>> 16);
        f.write((size & 0xFF000000) >>> 24);
        f.write(new byte[] {0, 0, 0, 0});
        f.write(new byte[] {0x36, 0, 0, 0});
        f.write(new byte[] {0x28, 0, 0, 0});
        f.write(scene.width & 0x000000FF);
        f.write((scene.width & 0x0000FF00) >>> 8);
        f.write((scene.width & 0x00FF0000) >>> 16);
        f.write((scene.width & 0xFF000000) >>> 24);
        f.write(scene.height & 0x000000FF);
        f.write((scene.height & 0x0000FF00) >>> 8);
        f.write((scene.height & 0x00FF0000) >>> 16);
        f.write((scene.height & 0xFF000000) >>> 24);
        f.write(new byte[] {1, 0});
        f.write(new byte[] {0x18, 0});
        f.write(new byte[] {0, 0, 0, 0});
        f.write(size-54 & 0x000000FF);
        f.write((size-54 & 0x0000FF00) >>> 8);
        f.write((size-54 & 0x00FF0000) >>> 16);
        f.write((size-54 & 0xFF000000) >>> 24);
        f.write(new byte[] {0x13, 0x0B, 00, 00});
        f.write(new byte[] {0x13, 0x0B, 00, 00});
        f.write(new byte[] {0, 0, 0, 0});
        f.write(new byte[] {0, 0, 0, 0});

        
        //Odredivanje boje svakog pixela
        for (int y = 0; y < scene.height; y++) {
            for (int x = 0; x < scene.width; x++) {
                
                Color outputColor = new Color(0, 0, 0);

                // Antialiasing
                for (double fx = x; fx < x + 1; fx += 0.5) {
                    for (double fy = y; fy < y + 1; fy += 0.5) {

                        double sampleRatio = 0.25;

  
                        Ray viewRay = new Ray(new Point(fx, fy, kamera),new Vector(0, 0, 1));
                        Color color = getColor(viewRay, scene);

                        color.alterAsPerExposure(1.0);

                        outputColor.add(Color.multiply(sampleRatio, color));
                    }
                }

                outputColor.srgbEncode();
                
                f.write(outputColor.toBytesForBMP());
            }

            for (int i = 0; i < (4 - scene.width * 3 % 4) % 4; i++) {
                f.write(0);
            }

        }

        f.flush();
        f.close();
        File input = new File("output.bmp");  

        BufferedImage image = ImageIO.read(input);

        File output = new File("output.jpg");
        ImageIO.write(image, "jpg", output);
        long end = System.nanoTime();
        System.out.println("Vrijeme crtanja u nanosekundama:");
        System.out.println(end-start);
    }
}
