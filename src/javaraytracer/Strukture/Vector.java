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
public class Vector {
    
    //vektor u smislu "strelice" u 3 dimenzije, ne racunarski vektor ili opceniti algebarski vektor
    //sto bi se reklo: vektor u uzem smislu
    
    public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    
    public Vector(Point p1, Point p2)
    {
        //vektor od tocke p1 do p2
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
        this.z = p2.z - p1.z;
    }


    public double duljina()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }


    public void normaliziraj()
    {
        double n = Math.sqrt(x * x + y * y + z * z);
        if (n == 0) {
            throw new ArithmeticException("Norma je 0");
        }
        x /= n;
        y /= n;
        z /= n;
    }


    public static Point kraj(Point p, Vector v)
    {
        return new Point(p.x + v.x, p.y + v.y, p.z + v.z);
    }


    public static Vector add(Vector v1, Vector v2)
    {
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }


    public static Vector sub(Vector v1, Vector v2)
    {
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }


    public static Vector multiply(double c, Vector v)
    {
        return new Vector(c * v.x, c * v.y, c * v.z);
    }


    public static double dotProdukt(Vector v1, Vector v2)
    {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
}
