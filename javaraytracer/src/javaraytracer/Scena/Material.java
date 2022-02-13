/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer.Scena;

import javaraytracer.Strukture.Color;

/**
 *
 * @author Bijesni Patuljak
 */
public class Material
    {
  
        public Color diffusion;

        public double reflection;

        public double power;

       public Material(Color diffusion, double reflection, double power)
        {
            this.diffusion = diffusion;
            this.reflection = reflection;
            this.power = power;
        }
    }
