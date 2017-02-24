import javax.swing.ImageIcon;
@SuppressWarnings("serial")

/**
 * Cette classe représente une case avec un bateau.
 * C'est ici que nous gérons l'apparition du bateau sur la carte que nous sortons un perso du bateau afin de le faire apparaitre sur la carte
 * Ainsi que de gérer la surbrillance du bateau pour savoir si il est accessible de la case ou nous sommes.Bateau hérite de Case: c'est une case particulière
 * 
 * @see Case
 * @author Ulysse,Axel,Alexis,Benjamin
 * @version Jalon 4
 */
public class Bateau extends Case{
	
	public Equipe team ;
	public static ImageIcon surligne = new ImageIcon("img/navireSurligne.png");
	
	/**
	 * Constructeur de la classe bateau qui créer un nouveau bateau à des coordonnées précise (grâce au constructeur de Case)
	 * @param x Abcisse souhaitée
	 * @param y Ordonnée souhaitée
	 * @param team L'équipe détenteur du bateau
	 * @see Case#Case(int, int)
	 */
	public Bateau(int x, int y , Equipe team) {
		super(x, y);
		this.team = team ;
		estTraversable = true ;
		id = 2 ;
		icone = new ImageIcon("img/navire1.jpg");
		setIcon(icone);	
	}
	
	/**
	 * Cette méthode permet d'enlever les personnages du bateau. 
	 * Elle nous sert par exemple pour tuer un personnage ou pour le déplacer.
	 * @see Personnage#seDeplacer(Case)
	 * @see Personnage#tuer()
	 */
	public void enleverPersonnage(){
		setIcon(new ImageIcon("img/navire1.jpg"));
		perso = null ;
		estTraversable = true ;
		contientPersonnage = false ;
	}
	
	/**
	 * Une méthode qui permet l'interaction entre le personnage actif et la case du bateau.
	 * Elle sert a détecter si un personnage gagne sinon cette méthode appel la méthode interaction de Case
	 * @see Case#interaction()
	 */
	public void interaction(){
		if(Main.actif.inventaire.contains("Tresor") && Main.actif.estAccessible(this) && Main.equipeActive.equals(team)){
			Main.victoire(Main.equipeActive);
		}else if(team.equals(Main.actif.equipe)){
			super.interaction();
		}
	}
	
	/**
	 * Une méthode qui modifie l'image du bateau pour montrer qu'il est accessible.
	 * Elle sert exclusivement pour voir quel bateau nous appartient et le met en surbrillance
	 * @see Case#surligner()
	 */
public void surligner(){
		if(Main.actif.equipe.equals(team)){
			setIcon(surligne);
		}
	}

	/**
	 * Une méthode qui modifie l'image du bateau pour montrer qu'il n'est pas accessible.
	 * c'est le complément de la méthode surligner,qui sert enlever la surbrillance( grâce a l'héritage, on appel super.desurligner());
	 * @see Case#desurligner()
	 */
	public void desurligner(){}
}

