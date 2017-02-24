import javax.swing.ImageIcon;
@SuppressWarnings("serial")

/**
 * Brouillard est une classe hérité de Case.
 * C'est une classe qui nous sert pour la classe Vue afin de pouvoir gérer les différentes vues nécessaire pour les deux joueurs
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Vue
 */

public class Brouillard extends Case {
	
	/**
	 * Construit une case de brouillard selon deux coordonnées qui appel le constructeur de Case.
	 * L'id sert pour l'affichage
	 * @param x abcisse de la case voulue
	 * @param y ordonnée de la case voulue
	 * @see Case#Case(int, int)
	 */
	public Brouillard(int x, int y ) {
		super(x, y);
		estTraversable = false ;
		id = 4 ;
		icone =  new ImageIcon("img/brouillard.png");
		setIcon(icone);
	}
	
	/**
	 * @see Case#interaction()
	 */
	public void interaction(){}
	
	/**
	 * @see Case#surligner()
	 */
	public void surligner(){}
	
	/**
	 * @see Case#desurligner()
	 */
	public void desurligner(){}
}
