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

        
        /*Material whiteMaterial = new Material(
            new Color(1.0, 1.0, 1.0),  
            1.0,                       
            60                         
        );

        Material redMaterial = new Material(
            new Color(1.0, 0.0, 0.0),  
            0.5,                       
            60                         
        );
                                      
        Material greenMaterial = new Material(
            new Color(0.0, 1.0, 0.0),  
            0.5,                       
            60                         
        );

        Material cyanMaterial = new Material(
            new Color(0.0, 1.0, 1.0),  
            2,                       
            60                         
        );


        Material blackMaterial = new Material(
            new Color(0.01, 0.01, .01), 
            3.0,                        
            100                          
        );

        
        spheres.add(new Sphere(
            new Point(400.0, 250.0, 0.0),    
            100.0,                           
            whiteMaterial                    
        ));

        
        spheres.add(new Sphere(
            new Point(400.0, 350.0, 0.0), 
            100.0,                           
            redMaterial                      
        ));
        
        spheres.add(new Sphere(
            new Point(700.0, 300.0, 200.0), 
            100.0,                           
            cyanMaterial                      
        ));
        
        spheres.add(new Sphere(
            new Point(100.0, 400.0, 500.0), 
            200.0,                           
            blackMaterial                      
        ));
        
        spheres.add(new Sphere(
            new Point(700.0, 300.0, -10.0), 
            30.0,                           
            greenMaterial                      
        ));
        spheres.add(new Sphere(
            new Point(650.0, 350.0, -10.0), 
            30.0,                           
            greenMaterial                      
        ));



        lights.add(new Light(
            new Point(0.0, 200.0, -100.0),     
            new Color(2.0, 2.0, 2.0)           
        ));


        lights.add(new Light(
            new Point(640.0, 240.0, -10000.0), 
            new Color(0.4, 0.4, 0.5)           
        ));*/
        

    }
}
