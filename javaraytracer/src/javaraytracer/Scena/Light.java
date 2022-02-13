/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer.Scena;

import javaraytracer.Strukture.Color;
import javaraytracer.Strukture.Point;

/**
 *
 * @author Bijesni Patuljak
 */
    public class Light
    {

        public Point origin;

        public Color intensity;

        public Light(Point origin, Color intensity)
        {
            this.origin = origin;
            this.intensity = intensity;
        }
    }
