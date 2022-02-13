/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer.Strukture;

import javaraytracer.Strukture.Point;
import javaraytracer.Strukture.Vector;

/**
 *
 * @author Bijesni Patuljak
 */
public class Ray {

    //zraka je vektor za kojeg sigurno znamo pocetnu tocku
    
    public Point origin;

    public Vector direction;


    public Ray(Point origin, Vector direction)
    {
        this.origin = origin;
        this.direction = direction;
    }
}
