import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Eclaireur est l'une de nos classes personnages disponible dans le jeu.
 * C'est donc ici que l'on dispose des méthodes de création d'un éclaireur,et sa vérification du déplacement.
 * La particularité de cette classe est qu'elle possèdent deux points d'actions , lui permettant de bouger 2 fois par tour
 * 
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Personnage
 */

public class Eclaireur extends Personnage {
	
	/**
	 * Constructeur d'éclaireur qui permet de créer un éclaireur et de lui donner un inventaire.
	 * @param nom Le nom du personnage qui sert à l'indentifier parmis les autres éclaireur si il y en a.
	 * @see Personnage
	 */
	@SuppressWarnings("deprecation")
	public Eclaireur(String nom) {
		icone=new ImageIcon("img/eclaireurPetit.png");
		this.nom =nom;
		inventaire = new ArrayList<String>();
		classe = 'c' ;
		surligne = new ImageIcon("img/eclaireurSurligne.png");
	}

	/**
	 * Créer un nouvel éclaireur avec "Eclaireur" comme nom et lui donne un inventaire.
	 * @see Eclaireur#Eclaireur()
	 */
	public Eclaireur() {
		this("Eclaireur");
		inventaire = new ArrayList<String>();
		classe = 'c' ;
	}
	
	/**
	 * Méthode qui nous dit si la case est acessible pour l'éclaireur avec ses possibilités de mouvements.
	 * @return true si la case ciblée est acessible
	 * @return false si la case ciblée n'est pas accesible
	 * 
	 */
	public boolean estAccessible(Case c){
		if((c.x == occupe.x +1 && c.y == occupe.y)|| (c.x == occupe.x -1 && c.y == occupe.y) || (c.x == occupe.x  && c.y == occupe.y + 1) 
				|| (c.x == occupe.x  && c.y == occupe.y - 1) || Main.p.deplacementLibre.getState()){ 
		return true ;
		}
		return false ;
	}

}
