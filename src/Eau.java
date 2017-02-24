import javax.swing.ImageIcon;
@SuppressWarnings("serial")
/**
 * Eau hérite de Case.
 * C'est donc une case particulière qui délimite le terrain, elle est inaccessible par tous les personnages.
 * C'est donc une classe qui sert pour l'affichage d'un plateau.
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Case
 */
public class Eau extends Case{
 /**
  * Créer une case d'eau infranchissable par n'importe quel personnage
  * @param x abcisse ou l'on veut mettre la case d'eau
  * @param y ordonnée
  * @see Case
  */
	public Eau(int x, int y) {
		super(x, y);
		estTraversable = false ;
		id = 1 ;
		icone = new ImageIcon("img/mer.jpg");
		setIcon(icone);
	}
	
	/**
	 * @see Case#surligner()
	 */
	public void surligner(){}
	
	/**
	 * @see Case#desurligner()
	 */
	public void desurligner(){}

}
