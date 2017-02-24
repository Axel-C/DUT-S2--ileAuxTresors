import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 * Voici l'un de nos 5 classes de personnage jouable :Le voleur
 * C'est donc ici que l'on dispose des méthodes de création d'un voleur,et sa vérification du déplacement.
 * La particularité du voleur est qu'il peut se déplacer dans en diagonales en plus des 4 déplacements de base.
 * De plus il peux tenter de voler un ennemi avec 50% de chance de réussite.
 * C'est donc ici que la méthode vol est définie.
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Personnage
 */
public class Voleur extends Personnage{
	
	/**
	 * Constructeur de la classe Voleur , qui créer un voleur tout en lui donnant un inventaire
	 * @param nom Nom du voleur choissis
	 * @see Personnage
	 */
	@SuppressWarnings("deprecation")
	public Voleur(String nom) {
		icone = new ImageIcon("img/voleurPetit.png");
		this.nom = nom ;
		inventaire = new ArrayList<String>();
		classe = 'v';
		surligne = new ImageIcon("img/voleurSurligne.png");
	}
	
	/**
	 * Constructeur de la classe Voleur, qui créer un voleur nomée "Voleur" tout en lui accordant un inventaire
	 * il utilise le 1 er constructeur vu plus haut
	 * @see Voleur#Voleur(String)
	 * 
	 */
	public  Voleur() {
		this("Voleur");
		inventaire = new ArrayList<String>();
		classe = 'v';
	}
	/**
	 * Voici la vérification des mouvements que peut effectuer le voleur,et qui le valide
	 * @return true si la case choisis est accesible
	 * @return false si la case choisis n'est pas accesible
	 * 
	 */
	public boolean estAccessible(Case c){
		if((c.x == occupe.x +1 && c.y == occupe.y)|| 
				(c.x == occupe.x -1 && c.y == occupe.y) 
				|| (c.x == occupe.x  && c.y == occupe.y + 1) || 
				(c.x == occupe.x  && c.y == occupe.y - 1) ||
				(c.x == occupe.x + 1 && c.y == occupe.y +1) ||
				 (c.x == occupe.x - 1 && c.y == occupe.y -1)||
				 (c.x == occupe.x + 1 && c.y == occupe.y -1)||
				 (c.x == occupe.x - 1 && c.y == occupe.y +1)
				 || Main.p.deplacementLibre.getState()){ 
		return true ;
		}
		return false ;
	}
	/**
	 * Voici la méthode vol du voleur. Elle à une chance sur deux de réussir.
	 * elle coûte 10 énergie au voleur . Elle va soit retourner que le personnage ne possède pas la clef , ou bien la réussite/l'échec du vol.
	 * @deprecated remplacé par {@link Personnage#voler(Personnage)}
	 */
	public void volerClef(Personnage p) {
		Random r = new Random();
		int chance = r.nextInt(2);
		if((p.nom == "Explorateur" || p.nom == "Voleur") && p.inventaire.contains("clef")) {
			enleverEnergie(10);
			if(chance == 1) {
				inventaire.add("clef");
				p.inventaire.remove("clef");
				JOptionPane.showConfirmDialog(null, "Vous avez volé la clef ! :D");
			} else {
				JOptionPane.showConfirmDialog(null, "Vous n'avez pas réussi à voler la clef ! :(");
			}
		} else {
			JOptionPane.showConfirmDialog(null, "Ce personnage ne possède pas la clef.");
		}
	}
}

