/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer;

import javaraytracer.Strukture.Ray;
import javaraytracer.Strukture.Vector;
import javaraytracer.Scena.Sphere;

/**
 *
 * @author Bijesni Patuljak
 */
    public class HitResult
    {

        double dist;
        //u dist specificiramo maksimalnu udaljenost za koju provjeravamo sijece li zraka sferu (udaljenost od izvora zrake)
        boolean hit;


        HitResult(double distance, boolean hit)
        {
            this.dist = distance;
            this.hit = hit;
        }

       public static HitResult hitSphere(Ray ray, Sphere sphere, double distance)
       {
           Vector d = new Vector(ray.origin, sphere.center);

           double a = Vector.dotProdukt(ray.direction, d);

           double delta = a * a - Vector.dotProdukt(d, d) + sphere.radius * sphere.radius;

           if (delta < 0.0) {
               return new HitResult(distance, false);
           }

           double root0 = a - Math.sqrt(delta);
           double root1 = a + Math.sqrt(delta);

           if (root0 > 0.1 && root0 < distance) {
               return new HitResult(root0, true);
           }

           if (root1 > 0.1 && root1 < distance) {
               return new HitResult(root1, true);
           }

           return new HitResult(distance, false);
       }
    }
