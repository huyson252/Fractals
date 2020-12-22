/**
 * Classe courbe permettant de gérer une collection de différentes courbes
 *  	classe mère de Polygone et Cercle
 */
 
import java.awt.Color;
import java.awt.Graphics;

public abstract class Courbe{

	// Attribut
	protected Color maCouleur;
    private final Color COULEUR_PAR_DEFAUT = Color.black; // La couleur par défaut est défini comme une constante
    
    
    /**
     * Le constructeur par défaut qui affecte la couleur noire
     */
    public Courbe(){
        maCouleur=COULEUR_PAR_DEFAUT;
    }
    
    /**
     * Le constructeur avec une couleur
     * @param la couleur de la courbe
     */ 
    public Courbe(Color uneCouleur){
        maCouleur = uneCouleur;
    }
	
	/**
	 * Pour afficher la couleur de la courbe
	 */
	public String toString(){
		return ("Ma longueur est "+longueur()+ " et mon aire est "+aire()+ " et ma couleur est "+maCouleur);
	}
  
	// Les méthodes abstraites
	/** 
	 * Pour calculer la longueur d'une courbe (quel que soit son type)
	 * @return la longueur
	 */
	public abstract double longueur();
	
	/** 
	 * Pour calculer l'aire d'une courbe (quel que soit son type)
	 * @return l'aire
	 */
	public abstract double aire();
	
	/** 
	 * Pour calculer le barycentre d'une courbe (quel que soit son type)
	 * @return le barycentre
	 */
	public abstract APoint barycentre();
    
    public abstract void dessine(Graphics g);
    
}

