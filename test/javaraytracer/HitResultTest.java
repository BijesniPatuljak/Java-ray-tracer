/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer;

import javaraytracer.Scena.Material;
import javaraytracer.Scena.Sphere;
import javaraytracer.Strukture.Color;
import javaraytracer.Strukture.Point;
import javaraytracer.Strukture.Ray;
import javaraytracer.Strukture.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Bijesni Patuljak
 */
public class HitResultTest {
    
    public HitResultTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHitSphere() {
        System.out.println("hitSphere");
        Ray ray = new Ray(new Point(300,100,100), new Vector(new Point(300,100,100), new Point(300,200,100)));
        Sphere sphere = new Sphere(new Point(100,100,100), 100.0, new Material(new Color(1,1,1), 1, 50));
        double distance = 100.0;
       
        HitResult result = HitResult.hitSphere(ray, sphere, distance);
        
        if(result.hit)fail("Ray didn't hit\n");
        // TODO review the generated test code and remove the default call to fail.
        
        
    }
    
    /*Dvije zrake koje trebaju fulat (jedna je predaleko), a druga samo fula*/
    @Test
    public void testHitSphere2() {
        System.out.println("hitSphere2");
        Ray ray = new Ray(new Point(300,100,100), new Vector(new Point(400,100,100), new Point(400,300,100)));
        Sphere sphere = new Sphere(new Point(400,400,100), 100.0, new Material(new Color(1,1,1), 1, 50));
        double distance = 50.0;
       
        HitResult result = HitResult.hitSphere(ray, sphere, distance);
        
        if(!result.hit)fail("Ray hit\n");
        // TODO review the generated test code and remove the default call to fail.
        
        
    }
    
    @Test
    public void testHitSphere3() {
        System.out.println("hitSphere3");
        Ray ray = new Ray(new Point(300,100,100), new Vector(new Point(100,100,100), new Point(0,0,0)));
        Sphere sphere = new Sphere(new Point(0,0,100), 100.0, new Material(new Color(1,1,1), 1, 50));
        double distance = 300.0;
       
        HitResult result = HitResult.hitSphere(ray, sphere, distance);
        
        if(!result.hit)fail("Ray hit\n");
        // TODO review the generated test code and remove the default call to fail.
        
        
    }
    
    /*Svjetlo iza kugle*/
        @Test
    public void testHitSphere4() {
        System.out.println("hitSphere4");
        Ray ray = new Ray(new Point(300,100,100), new Vector(new Point(100,100,300), new Point(100,100,200)));
        Sphere sphere = new Sphere(new Point(100,100,100), 100.0, new Material(new Color(1,1,1), 1, 50));
        double distance = 300.0;
       
        HitResult result = HitResult.hitSphere(ray, sphere, distance);
        
        if(result.hit)fail("Ray didn't hit\n");
        // TODO review the generated test code and remove the default call to fail.
        
        
    }
    /*Veca udaljenost*/
        @Test
    public void testHitSphere5() {
        System.out.println("hitSphere5");
        Ray ray = new Ray(new Point(0,0,0), new Vector(new Point(300,300,100), new Point(0,0,0)));
        Sphere sphere = new Sphere(new Point(100,100,100), 100.0, new Material(new Color(1,1,1), 1, 50));
        double distance = 1000.0;
       
        HitResult result = HitResult.hitSphere(ray, sphere, distance);
        
        if(result.hit)fail("Ray didn't hit\n");
        // TODO review the generated test code and remove the default call to fail.
        
        
    }
    
        @Test
    public void testHitSphere6() {
        System.out.println("hitSphere6");
        Ray ray = new Ray(new Point(300,100,100), new Vector(new Point(300,300,100), new Point(0,0,0)));
        Sphere sphere = new Sphere(new Point(0,0,100), 200.0, new Material(new Color(1,1,1), 1, 50));
        double distance = 300.0;
       
        HitResult result = HitResult.hitSphere(ray, sphere, distance);
        
        if(!result.hit)fail("Ray hit\n");
        // TODO review the generated test code and remove the default call to fail.
        // TODO review the generated test code and remove the default call to fail.
        
        
    }
    
}
