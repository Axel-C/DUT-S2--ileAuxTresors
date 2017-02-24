import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 * Cette classe fait partie de nos 5 classes disponible en jeu : Le guerrier
 * C'est ici que l'on trouve de quoi construire notre classe , sa méthode pour définir ces mouvements possible
 * La caractéristique du guerrier est qu'il peut attaquer un ennemi et possèdent 50 d'énergie de plus que tous les autres.
 * Son attaque lui coûte 10 en énergie.
 * 
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Personnage
 */
public class Guerrier extends Personnage{
	/**
	 * Constructeur de la méthode Guerrier qui créer un guerrier avec un nom voulu,et lui attribu un inventaire. il lui fixe aussi son energie à 150
	 * @param nom Nom du guerrier
	 * @see Personnage
	 */
	@SuppressWarnings("deprecation")
	public Guerrier(String nom) {
		icone=new ImageIcon("img/guerrierPetit.png");
		this.nom = nom;
		this.energie=150;
		inventaire = new ArrayList<String>();
		System.out.println("Nouveau Personnage");
		classe = 'g' ;
		surligne = new ImageIcon("img/guerrierSurligne.png");
	}
	
	/**
	 * Constructeur qui créer un guerrier avec "Guerrier" comme nom et lui donne un inventaire.
	 * @see Guerrier#Guerrier(String)
	 */
	public Guerrier() {
		this("Guerrier");
		inventaire = new ArrayList<String>();
		classe = 'g' ;
	}
	
	/**
	 * Retourne true si la case ciblé accessible pour le personnage selon ses critères de mouvements
	 * @return true si la case ciblé est  accessible
	 * @return false si la case ciblé n'est pas accesible
	 */
	public boolean estAccessible(Case c){
		if((c.x == occupe.x +1 && c.y == occupe.y)|| (c.x == occupe.x -1 && c.y == occupe.y) || (c.x == occupe.x  && c.y == occupe.y + 1) 
				|| (c.x == occupe.x  && c.y == occupe.y - 1) || Main.p.deplacementLibre.getState()){ 
		return true ;
		}
		return false ;
	}
	
}
