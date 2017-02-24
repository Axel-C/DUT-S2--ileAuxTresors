import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Voici l'un de nos 5 classes de personnage jouable :Le piègeur
 * C'est donc ici que l'on dispose des méthodes de création d'un piègeur,et sa vérification du déplacement.
 * La particularité du piègeur est qu'il passe en mode camouflage,se cachant sous un rocher.Si un explorateur fouille sous le rocher, il se fait tuer instantanément.
 * Passer en mode rocher lui consomme 10 points d'énergie.
 * @author Ulysse,Axel,Alexis,Benjamin
 * @se Personnage
 */
public class Piegeur extends Personnage{
	
	/**
	 * Constructeur de la classe Piegeur qui permet de créer un nouveau piegeur avec un nom et un inventaire
	 * @param nom Nom du voleur
	 * @see Personnage
	 */
	@SuppressWarnings("deprecation")
	public Piegeur(String nom) {
		icone = new ImageIcon("img/piegeur.png");
		this.nom = nom ;
		inventaire = new ArrayList<String>();
		classe = 'p';
		surligne = new ImageIcon("img/piegeurSurligne.png");
	}
	
	/**
	 * Constructeur qui permet de créer un nouveau voleur avec "Piegeur" comme nom 
	 * @see Piegeur#Piegeur()
	 */
	public  Piegeur() {
		this("Piegeur");
	}
	
	/**
	 * méthode qui défini si la case est accesible au piègeur selon ses critères de mouvements
	 * @return true si la case ciblé est accesible
	 * @return false si la case ciblé n'est pas accesible
	 * 
	 */
	public boolean estAccessible(Case c){
		if(enRocher){
			return false ;
		}
		if((c.x == occupe.x +1 && c.y == occupe.y)|| (c.x == occupe.x -1 && c.y == occupe.y) || (c.x == occupe.x  && c.y == occupe.y + 1) 
				|| (c.x == occupe.x  && c.y == occupe.y - 1) || Main.p.deplacementLibre.getState()){ 
		return true ;
		}
		return false ;
	}
	
	/**
	 * C'est la caractéristique spécial du piègeur. Quan on lui clique dessus , il consomme son point d'action afin de prendre l'apparence d'un rocher.
	 * Il consomme également 10 points d'énergie et l'immobilise . 
	 * Si le personnage est deja sous forme rocher , il revient en mode normal et peut se déplacer.
	 * cette méthode gère aussi l'image à afficher sur le plateau
	 * 
	 */
	public void modeRocher(){
		pa-- ;
		if(enRocher){
			enRocher = false ;
			icone = new ImageIcon("img/piegeur.png");
			Main.p.afficher();
			surligne = new ImageIcon("img/piegeurSurligne.png");
		}else{
			enRocher = true ;
			energie=energie-10;
			surligne = new ImageIcon("img/rocherSurligne.png");
			icone = new ImageIcon("img/pierre.jpg");
			occupe.icone = icone ;
			occupe.setIcon(icone);
			Main.p.afficher();
		}
	}
}


