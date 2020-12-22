/**
 * Classe Polygone Régulier (héritier de polygone)
 * calcul de l'aire et du périmètre
 */

import java.util.*;
import java.awt.Color;

public class PolygoneRegulier extends Polygone{
	APoint centre;
    APoint vertex;
	/**
	 * Le constructeur
	 * @param le centre, un sommet et le nombre de côtés
	 */
     
	public PolygoneRegulier(APoint leCentre, APoint unSommet, int nCotes){
		super(); // Ici super() est facultatit car java ajoute cette instruction automatiquement
		setPoints(leCentre, unSommet, nCotes);
        this.centre=leCentre;
        this.vertex=unSommet;
	}


	/**
	 * Le constructeur
	 * @param le centre, un sommet, le nombre de côtés et une couleur
	 */
	public PolygoneRegulier(APoint leCentre, APoint unSommet, int nCotes, Color uneCouleur){
		super(uneCouleur);
		setPoints(leCentre, unSommet, nCotes);
        this.centre=leCentre;
        this.vertex=unSommet;
	}
	
	
	
	/**
	 * Pour calculer les sommets du polygone régulier à partir des données
	 * @param le centre, un sommet et le nombre de côtés
	 */ 
	private void setPoints(APoint leCentre, APoint unSommet, int nCotes){
		points = new APoint[nCotes];
		// angle au centre du polygone
		double alpha;
		alpha = 2.0*Math.PI/nCotes;
		// position d'un sommet par rapport au centre
		double dx;
		double dy;
		dy = unSommet.y-leCentre.y;
		dx = unSommet.x-leCentre.x;
		// angle beta
		double beta;
		beta = Math.atan2(dy, dx);
		// le rayon
		double rayon = leCentre.distance(unSommet);
		// le tableau de points qui constituent le polygone
		points[0] = unSommet;
		for(int i=1; i<nCotes; i++){
			points[i] = new APoint((int)(rayon * Math.cos(i*alpha+beta)) + leCentre.x, (int)(rayon * Math.sin(i*alpha+beta)) + leCentre.y);
		}
	}

	/**
	 * Pour calculer l'aire d'un polygone régulier
	 * @return l'aire du polygone régulier
	 */ 
	public double aire(){
		double resultat;
		int n = points.length; // le nombre de côtés 
		double a = points[0].distance(points[1]); // la longueur d'un côté
		return (n*a*a)/(4.0*Math.tan(Math.PI/n));
	}
	
	/**
	 * Pour calculer le périmètre d'un polygone
	 * @return le périmètre
	 */ 
	public double longueur(){
		int n = points.length; // le nombre de côtés 
		double a = points[0].distance(points[1]); // la longueur d'un côté
		return (n*a);
	}
	
	/**
	 * Pour afficher des données particulières du polygone régulier
	 */ 
	 public String toString(){
		 String res;
		 res = ("Polygone régulier de "+points.length+" côtés. ");
		 res+= super.toString();
		 return res;
	 }
}

