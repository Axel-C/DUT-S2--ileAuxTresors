import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 * Cette classe est l'une de nos 5 personnages jouable . C'est aussi la plus importante : celle de l'explorateur
 * C'est ici que l'on trouve de quoi construire notre classe , sa méthode pour définir ces mouvements possible
 * La caractéristique de l'explorateur est qu'il peut soulever un rocher.
 * Soulever un rocher lui coût 5 en énergie
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Personnage
 */
public class Explorateur extends Personnage{
	
	/**
	 * Constructeur de la calsse qui permet d'avori un explorateur avec un nom spécifique ainsi qu'un inventaire 
	 * @param nom Nom du personnage voulu
	 * @see Personnage
	 */
	@SuppressWarnings("deprecation")
	public Explorateur(String nom) {
		icone = new ImageIcon("img/explo.jpg");
		this.nom = nom;
		inventaire = new ArrayList<String>();
		classe = 'e' ;
		surligne = new ImageIcon("img/exploSurligne.png");
	}
	
	/**
	 * Constructeur qui permet de créer un explorateur avec "Explorateur" comme nom et de lui donner un inventaire
	 * @see Explorateur#Explorateur()
	 */
	public Explorateur(){
		this("Explorateur");
		inventaire = new ArrayList<String>();
	}
	
	/**
	 * méthode qui nous indique si on peut se rendre sur la case ciblée avec les restrictions de mouvements de la classe explorateur
	 * @return false si la case ciblée n'est pas accesible
	 * @return true si la case ciblée est accessible
	 */
	public boolean estAccessible(Case c){
		if((c.x == occupe.x +1 && c.y == occupe.y)|| (c.x == occupe.x -1 && c.y == occupe.y) || (c.x == occupe.x  && c.y == occupe.y + 1) 
				|| (c.x == occupe.x  && c.y == occupe.y - 1) || Main.p.deplacementLibre.getState()){ 
		return true ;
		}
		return false ;
	}
	
	
}