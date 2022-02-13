/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer;

/**
 *
 * @author Bijesni Patuljak
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
 
    

 
    public static void main(String[] args) throws IOException
    {
        String outputFile = "output.bmp";

        Scene scene = new Scene();
        Draw.draw(outputFile, scene);
        NewJFrame a = new NewJFrame();
        a.main(new String[0]);
    }


    /**
     * Get the color of the pixel on the camera from where the specified
     * <code>ray</code> originated.
     *
     * @param ray   Ray originating from the camera.
     * @param scene Scene defintion object.
     */
    public static Color getColor(Ray ray, Scene scene)
    {
        Color outputColor = new Color(0, 0, 0);

        double coef = 1;
        int level = 0;

        do {
            // This variable stores the first object that the ray hits.
            Sphere sphere = null;

            double distance = 2000.0f;

            // Find the first object with which the ray intersects.
            for (Sphere s : scene.spheres) {
                HitResult h = HitResult.hitSphere(ray, s, distance);
                distance = h.dist;
                if (h.hit) {
                    sphere = s;
                }

            }

            // If the ray is not intersecting any object, stop processing
            // this ray.
            if (sphere == null) {
               break;
            }

            // Compute the point where the ray intersected the object.
            Point hitPoint =
                    Vector.kraj(ray.origin,
                               Vector.multiply(distance, ray.direction));

            // Calculate a vector normal to the surface of the object at
            // the point of intersection of the ray and the object.
            Vector normal = new Vector(sphere.center, hitPoint);

            // Normalize the normal vector.
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
                    // Lambertian coeffecient
                    double lambert = Vector.dotProdukt(lightRay.direction,
                                                         normal) * coef;

                    outputColor.add(Color.multiply(lambert, Color.multiply(
                        light.intensity, currentMaterial.diffusion)));

                    // Blinn-Phong specular term
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

            // If i is incident ray vector and n is the normal vector
            // (both normalized) then the reflected ray direction is
            // given by i - 2 * d.n * n
            Vector projectionAlongNormal = Vector.multiply(
                    Vector.dotProdukt(ray.direction, normal), normal);

            // Compute the reflected ray.
            ray.origin = hitPoint;
            ray.direction = Vector.sub(ray.direction,
                    Vector.multiply(2, projectionAlongNormal));
            level++;
        } while ((coef > 0) && (level < 10));
        return outputColor;
    }

 
}
