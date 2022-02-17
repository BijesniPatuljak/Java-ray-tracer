/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer.Strukture;

/**
 *
 * @author Bijesni Patuljak
 */
public class Color {

    public double R;
    public double G;
    public double B;


    public Color(double red, double green, double blue)
    {
        this.R = red;
        this.G = green;
        this.B = blue;
    }


    public void add(Color color)
    {
        R += color.R;
        G += color.G;
        B += color.B;
    }


    public void alterAsPerExposure(double exposure) {
        B = 1.0 - Math.exp(-B * exposure);
        R = 1.0 - Math.exp(-R * exposure);
        G = 1.0 - Math.exp(-G * exposure);
    }


    public void srgbEncode() {

        //ove vrijednosti sam pronasao na internetu kao konstante za prebacivanje rgb-a u format pogodaan bmp-u
        
        if (R <= 0.0031308) {
            R *= 12.92;
        } else {
            R = 1.055 * Math.pow(R, 0.4166667) - 0.055;
        }
        
        if (G <= 0.0031308) {
            G *= 12.92;
        } else {
            G = 1.055 * Math.pow(G, 0.4166667) - 0.055;
        }
       
        if (B <= 0.0031308) {
            B *= 12.92;
        } else {
            B = 1.055 * Math.pow(B, 0.4166667) - 0.055;
        }
    }

    public byte[] toBytesForBMP() {
        return new byte[] {

            (byte) Math.min(B * 255, 255),
            (byte) Math.min(G * 255, 255),
            (byte) Math.min(R * 255, 255)
        };
    }



    public static Color multiply(double c, Color color)
    {
        return new Color(c * color.R, c * color.G, c * color.B);
    }


    public static Color multiply(Color c1, Color c2)
    {
        return new Color(c1.R * c2.R, c1.G * c2.G, c1.B * c2.B);
    }



}
