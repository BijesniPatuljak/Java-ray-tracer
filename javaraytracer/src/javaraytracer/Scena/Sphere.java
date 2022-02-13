/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer.Scena;

import javaraytracer.Strukture.Point;

/**
 *
 * @author Bijesni Patuljak
 */
    public class Sphere
    {

        public Point center;
        public double radius;
        public Material material;

        public Sphere(Point center, double radius, Material material)
        {
            this.center = center;
            this.radius = radius;
            this.material = material;
        }
    }
