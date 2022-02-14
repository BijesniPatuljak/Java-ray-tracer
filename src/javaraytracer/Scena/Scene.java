/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer.Scena;

/**
 *
 * @author Bijesni Patuljak
 */

import java.util.Collection;
import java.util.ArrayList;
import javaraytracer.Strukture.Color;
import javaraytracer.Strukture.Point;

public class Scene {

    public ArrayList<Sphere> spheres;
    public ArrayList<Light> lights;

 
    public int width;

    public int height;


    public Scene()
    {
        spheres = new ArrayList<>();
        lights = new ArrayList<>();

        sceneDefinition();
    }
    
    public void addSphere(Sphere S){
        spheres.add(S);
    }
    
    public void addLight(Light S){
        lights.add(S);
    }


    private void sceneDefinition()
    {
        width = 800;
        height = 600;

        // Materials
        Material whiteMaterial = new Material(
            new Color(1.0, 1.0, 1.0),  // Diffusion
            1.0,                       // Reflection
            60                         // Specular power
        );

        Material redMaterial = new Material(
            new Color(1.0, 0.0, 0.0),  // Diffusion
            0.5,                       // Reflection
            60                         // Specular power
        );
                                      
        Material greenMaterial = new Material(
            new Color(0.0, 1.0, 0.0),  // Diffusion
            0.5,                       // Reflection
            60                         // Specular power
        );

        Material blueMaterial = new Material(
            new Color(0.0, 0.0, 1.0),  // Diffusion
            0.5,                       // Reflection
            60                         // Specular power
        );

        Material yellowMaterial = new Material(
            new Color(1.0, 1.0, 0.0),  // Diffusion
            0.5,                       // Reflection
            60                         // Specular power
        );

        Material cyanMaterial = new Material(
            new Color(0.0, 1.0, 1.0),  // Diffusion
            2,                       // Reflection
            60                         // Specular power
        );

        Material magentaMaterial = new Material(
            new Color(1.0, 0.0, 1.0),  // Diffusion
            0.5,                       // Reflection
            60                         // Specular power
        );

        Material blackMaterial = new Material(
            new Color(0.01, 0.01, .01), // Diffusion
            1.0,                        // Reflection
            60                          // Specular power
        );

        // White sphere
        spheres.add(new Sphere(
            new Point(400.0, 250.0, 0.0),    // Center
            100.0,                           // Radius
            whiteMaterial                    // Material
        ));

        // Red sphere
        spheres.add(new Sphere(
            new Point(400.0, 350.0, 0.0), // Center
            100.0,                           // Radius
            redMaterial                      // Material
        ));
        
        spheres.add(new Sphere(
            new Point(700.0, 300.0, 200.0), // Center
            100.0,                           // Radius
            cyanMaterial                      // Material
        ));


        // Left light
        lights.add(new Light(
            new Point(0.0, 200.0, -100.0),     // Origin
            new Color(2.0, 2.0, 2.0)           // Intensity
        ));

        // Light behind the camera
        lights.add(new Light(
            new Point(640.0, 240.0, -10000.0), // Origin
            new Color(0.4, 0.4, 0.5)           // Intensity
        ));
        
        // Light behind the white sphere.
        lights.add(new Light(
            new Point(640.0, 240.0, 100.0),   // Origin
            new Color(0.2, 0.2, 0.5)          // Intensity
        ));
    }
}
