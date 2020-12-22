/**
 * Classe Polygone pour gérer un polygone quelconque
 * 	calcul du périmètre
 * 	calcul de l'aire
 * 	calcul du centre
 * 	gestion de la couleur
 * 	méthode toString
 * 
 */
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Polygone extends Courbe{
	
	// Les attributs	
	protected APoint[] points;

	/**
	 * Le constructeur
	 * @param le tableau de points décrivant chaque sommet
	 */ 
     
     public Polygone(){
    	super(); // Ici super() est facultatit car java ajoute cette instruction automatiquement
	} 
	 

	/**
	 * Un constructeur vide pour gérer les descendants avec une couleur
	 */ 
	public Polygone(Color uneCouleur){
    	// Pour appeler le constructeur de l'ancêtre et affecter au polygone la couleur par défaut
        super(uneCouleur); // Ici super() est facultatit car java ajoute cette instruction automatiquement
	} 
    
	public Polygone(APoint[] p){
		super(); // Ici super() est facultatit car java ajoute cette instruction automatiquement
		points = new APoint[p.length];
		for(int i=0; i<p.length; i++)
			points[i] = new APoint(p[i].x,p[i].y);
	}
	
	/**
	 * Le constructeur
	 * @param le tableau de points décrivant chaque sommet et une couleur
	 */ 
	public Polygone(APoint[] p, Color uneCouleur){
		// Pour appeler le constructeur de l'ancêtre et affecter au polygone la couleur par défaut
        super(uneCouleur);
		points = new APoint[p.length];
		for(int i=0; i<p.length; i++)
			points[i] = new APoint(p[i].x,p[i].y);
	}
	
	/**
	 * Pour calculer le périmètre d'un polygone
	 * @return le périmètre
	 */ 
	public double longueur(){
		double resultat=0;
		for(int i=1; i<points.length; i++){
			resultat += points[i-1].distance(points[i]);
		}
		// ajout de la distance entre dernier et premier point
		resultat += points[points.length-1].distance(points[0]);
		return resultat;
	}
	
	/**
	 * Pour calculer l'aire d'un polygone
	 * @return l'aire
	 */ 
	public double aire(){
		double a=0;
		int i;
		for(i=0; i<points.length-1; i++)
			a += points[i].x*points[i+1].y - points[i+1].x*points[i].y;
		// "i" a la valeur points.length-1 maintenant
		a += points[i].x*points[0].y - points[0].x*points[i].y;
		return 0.5*a;
	}
	
	/**
	 * Pour calculer le barycentre d'un polygone
	 * @return le barycentre sous forme d'un APoint
	 */ 
	public APoint barycentre(){
		APoint resultat = new APoint(0,0);
		int i;
		for(i=0; i<points.length-1; i++){
			resultat.x += (points[i].x+points[i+1].x) * (points[i].x*points[i+1].y - points[i+1].x*points[i].y);
			resultat.y += (points[i].y+points[i+1].y) * (points[i].x*points[i+1].y - points[i+1].x*points[i].y);
		}
		// "i" a la valeur points.length-1 maintenant
		resultat.x += (points[i].x+points[0].x) * (points[i].x*points[0].y - points[0].x*points[i].y);
		resultat.y += (points[i].y+points[0].y) * (points[i].x*points[0].y - points[0].x*points[i].y);
		double a = aire();
		resultat.x /= 6*a;
		resultat.y /= 6*a;
		return resultat;
    }
    
    public void dessine(Graphics g){
        g.setColor(maCouleur);
        int[] coordX = new int[points.length];
        int[] coordY = new int[points.length];
        for (int i = 0; i<points.length ; i++){
            coordX[i]=(int)points[i].x;
            coordY[i]=(int)points[i].y;
        }
        g.drawPolygon(coordX, coordY, points.length);
    }
    
    public void dessine1(Graphics g){
        g.setColor(maCouleur);
        int[] coordX = new int[points.length];
        int[] coordY = new int[points.length];
        for (int i = 0; i<points.length ; i++){
            coordX[i]=(int)points[i].x;
            coordY[i]=(int)points[i].y;
        }
        g.fillPolygon(coordX, coordY, points.length);
    }
  

	/**
	 * Pour afficher les données du polygone
	 * @return les coordonnées des sommets
	 */
	public String toString(){
		String res;
		res = "Polygone avec "+ points.length+ " sommets :";
		for(int i=0; i<points.length; i++)
			res += " (" + points[i].x + "," + points[i].y + ")";
		res+=". ";
		res+=super.toString();
		return res;
	}

}

