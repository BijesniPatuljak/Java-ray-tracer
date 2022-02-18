/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer;

/**
 *
 * @author Bijesni Patuljak
 * 
 *
 * 
 * 
 * 
 */


import javaraytracer.Strukture.Ray;
import javaraytracer.Strukture.Point;
import javaraytracer.Strukture.Vector;
import javaraytracer.Strukture.Color;
import javaraytracer.Scena.Scene;
import javaraytracer.Scena.Sphere;
import javaraytracer.Scena.Material;
import javaraytracer.Scena.Light;
import java.io.IOException;
import javaraytracer.GUI.NewJFrame;

public class Javaraytracer {
 
    public static Scene scene = new Scene();

 
    public static void main(String[] args) throws IOException
    {
        String outputFile = "output.bmp";

        Draw.draw(outputFile, scene);
        NewJFrame a = new NewJFrame();
        
        a.glavna(new String[0]);
    }



    public static Color getColor(Ray ray, Scene scene)
    {
        Color outputColor = new Color(0, 0, 0);

        double coef = 1;
        int level = 0;

        do {
            Sphere sphere = null;

            double distance = 2000.0f;

            for (Sphere s : scene.spheres) {
                HitResult h = HitResult.hitSphere(ray, s, distance);
                distance = h.dist;
                if (h.hit) {
                    sphere = s;
                }

            }

            if (sphere == null) {
               break;
            }

            Point hitPoint =
                    Vector.kraj(ray.origin,
                               Vector.multiply(distance, ray.direction));

            Vector normal = new Vector(sphere.center, hitPoint);

            try {
                normal.normaliziraj();
            } catch (ArithmeticException e) {
                break;
            }

            Material currentMaterial = sphere.material;

            for (Light light : scene.lights) {

                Ray lightRay = new Ray(hitPoint,
                        new Vector(hitPoint, light.origin));

                double lightProjection =
                        Vector.dotProdukt(lightRay.direction, normal);

                if (lightProjection <= 0.0)
                    continue;

                double lightDistance = lightRay.direction.duljina();
                lightRay.direction.normaliziraj();
                lightProjection /= lightDistance;

                boolean inShadow = false;
                for (Sphere s : scene.spheres) {
                    HitResult h = HitResult.hitSphere(lightRay, s, lightDistance);
                    if (h.hit) {
                        inShadow = true;
                        break;
                    }
                }

                if (!inShadow)
                {
                   
                    double lambert = Vector.dotProdukt(lightRay.direction,
                                                         normal) * coef;

                    outputColor.add(Color.multiply(lambert, Color.multiply(
                        light.intensity, currentMaterial.diffusion)));

                  
                    Vector v = Vector.sub(lightRay.direction,
                                                 ray.direction);
                    double norm = v.duljina();
                    if (norm != 0.0) {

                        double viewProjection =
                                Vector.dotProdukt(ray.direction, normal);

                        double blinnPhongTerm =
                                Math.max(lightProjection - viewProjection, 0.0)
                                / norm;

                        blinnPhongTerm =
                            coef * Math.pow(blinnPhongTerm,
                                            currentMaterial.power);
                        Color white = new Color(1.0, 1.0, 1.0);
                        Color c = Color.multiply(blinnPhongTerm, white);
                        c = Color.multiply(c, light.intensity);
                        outputColor.add(c);
                    }
                }
            }

            coef *= currentMaterial.reflection;

            Vector projectionAlongNormal = Vector.multiply(
                    Vector.dotProdukt(ray.direction, normal), normal);

            ray.origin = hitPoint;
            ray.direction = Vector.sub(ray.direction,
                    Vector.multiply(2, projectionAlongNormal));
            level++;
        } while ((coef > 0) && (level < 10));
        return outputColor;
    }

 
}
