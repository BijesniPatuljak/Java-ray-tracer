/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaraytracer.DB;

import java.sql.*;


/**
 *
 * @author Bijesni Patuljak
 */
public class databasehandler {
    
    private static Connection con;
    PreparedStatement p = null;
    //ResultSet res = null;
    public int i = 0;
    
    public databasehandler(){
        
    }
    
    public void addLight(int x, int y, int z, int r, String color){
        try{
        Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e){
           System.out.println("Greska pri spajanju" + e);
        }
        
        try{
            con = DriverManager.getConnection("jdbc:sqlite:database.db");
        }
        catch(SQLException s){
            System.out.println("Greska pri spajanju 2" + s);
        }
        
        String values= "";
        values += i;
        values += ", " + "'Light', " + x + ", " + y + ", " + z + ", " + r + ", " + color + ", 'Dodaj'";
        
        try{
            p = con.prepareStatement("INSERT INTO Postupak VALUES ("+ values + ");");
            p.clearParameters();
            
            p.executeUpdate();
            
        }
        catch(SQLException s){
            System.out.println("Greska pri upisu" + s);
        }
        i++;
    }
    
        public void addSphere(int x, int y, int z, int r, String color){
        try{
        Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e){
           System.out.println("Greska pri spajanju" + e);
        }
        
        try{
            con = DriverManager.getConnection("jdbc:sqlite:database.db");
        }
        catch(SQLException s){
            System.out.println("Greska pri spajanju 2" + s);
        }
        
        String values= "";
        values += i;
        values += ", " + "'Sphere', " + x + ", " + y + ", " + z + ", " + r + ", " + color + ", 'Dodaj'";
        
        try{
            p = con.prepareStatement("INSERT INTO Postupak VALUES ("+ values + ");");
            p.clearParameters();
            
            p.executeUpdate();
            
        }
        catch(SQLException s){
            System.out.println("Greska pri upisu" + s);
        }
        i++;
    }
        
            public void removeLight(int x, int y, int z, int r, String color){
        try{
        Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e){
           System.out.println("Greska pri spajanju" + e);
        }
        
        try{
            con = DriverManager.getConnection("jdbc:sqlite:database.db");
        }
        catch(SQLException s){
            System.out.println("Greska pri spajanju 2" + s);
        }
        
        String values= "";
        values += i;
        values += ", " + "'Light', " + x + ", " + y + ", " + z + ", " + r + ", " + color + ", 'Izbaci'";
        
        try{
            p = con.prepareStatement("INSERT INTO Postupak VALUES ("+ values + ");");
            p.clearParameters();
            
            p.executeUpdate();
            
        }
        catch(SQLException s){
            System.out.println("Greska pri upisu " + s);
        }
        i++;
    }
        public void removeSphere(int x, int y, int z, int r, String color){
        try{
        Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e){
           System.out.println("Greska pri spajanju " + e);
        }
        
        try{
            con = DriverManager.getConnection("jdbc:sqlite:database.db");
        }
        catch(SQLException s){
            System.out.println("Greska pri spajanju 2 " + s);
        }
        
        String values= "";
        values += i;
        values += ", " + "'Sphere', " + x + ", " + y + ", " + z + ", " + r + ", " + color + ", 'Izbaci'";
        
        try{
            p = con.prepareStatement("INSERT INTO Postupak VALUES ("+ values + ");");
            p.clearParameters();
            
            p.executeUpdate();
            
        }
        catch(SQLException s){
            System.out.println("Greska pri upisu" + s);
        }
        i++;
    }


    
}
