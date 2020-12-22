import java.util.LinkedList; 
import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;

public class SierpinskiTriangle{
    PolygoneRegulier sierpinskiTriangle;
    Color couleur;

    
    public SierpinskiTriangle(APoint centre,APoint sommet,Color colour){
        couleur = colour;
        sierpinskiTriangle = new  PolygoneRegulier(centre,sommet,3,colour);
    }
    
     public SierpinskiTriangle(APoint centre,APoint sommet){
        sierpinskiTriangle = new  PolygoneRegulier(centre,sommet,3);
    }
    
    public void display(Graphics g,int n){
        sierpinskiTriangle.dessine(g);
        if(n>0){
            innerTriangle(sierpinskiTriangle).dessine(g);
            this.recursion(g,sierpinskiTriangle,n);
        }
        
	}
    
    public void recursion(Graphics g, Polygone triangle, int n){
        if(n>1){
            APoint [] p1 = {triangle.points[0],innerTriangle(triangle).points[0],innerTriangle(triangle).points[2]};
            APoint [] p2 = {innerTriangle(triangle).points[0],triangle.points[1],innerTriangle(triangle).points[1]};
            APoint [] p3 = {innerTriangle(triangle).points[2],innerTriangle(triangle).points[1],triangle.points[2]}; 
            Polygone t1 = new Polygone(p1,couleur);
            Polygone t2 = new Polygone(p2,couleur);
            Polygone t3 = new Polygone(p3,couleur);
            innerTriangle(t1).dessine(g);
            innerTriangle(t2).dessine(g);
            innerTriangle(t3).dessine(g);
        
            this.recursion(g,t1,n-1);
            this.recursion(g,t2,n-1);
            this.recursion(g,t3,n-1);
             
        }
    }
    
    
    
    public Polygone innerTriangle(Polygone triangle){
        APoint [] points= new APoint[3];
        
        for(int i=0;i<triangle.points.length-1;i++){
            points[i]=triangle.points[i].midPoint(triangle.points[i+1]);
        }
        points[2]= (triangle.points[triangle.points.length-1].midPoint(triangle.points[0]));
        Polygone innerTriangle = new Polygone(points,couleur);
        return innerTriangle;
    }

}
