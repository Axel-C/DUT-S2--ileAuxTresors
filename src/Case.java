import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
@SuppressWarnings("serial")

/** 
 *  Classe est la base de carte. En effet il existe plusieurs types de cases : Eau,Brouillard,Case avec rocher, Case simple,Case avec un personnage
 *  ils vont nous permettre de modéliser une carte
 *  C'est donc ici ou se situe les méthodes gérant les cases
 * 
 * @author Ulysse,Axel,Alexis,Benjamin
 *
 */
public class Case extends JButton implements ActionListener{
	/**
	 * La coordonnée de la case sur le carte en x
	 */
	public int x ;
	
	/**
	 *  La coordonnée de la case sur le carte en y
	 */
	public int y ;
	
	/**
	 * Chaque numéro d'id est associé a un type de case. Nous indique le type de la case
	 */
	int id = 0 ;
	
	/**
	 * nous indique si sur cette case se situe le coffre dévoiler
	 */
	public boolean coffre = false ;
	
	/**
	 * nous indique si sur cette case se situe la clef dévoiler
	 */
	public boolean cle = false ;
	
	/**
	 * Nous indique si la case contient un obstacle
	 * @return true si la case est traversable
	 * @return false si la case n'est pas traversable
	 */
	boolean estTraversable ;
	
	/**
	 * Image standard de la case pour l'affichage
	 */
	ImageIcon icone = new ImageIcon("img/sable.jpg");
	
	/**
	 * L'image en mode surligner afin de montrer que la case est accesible au joueur proche.
	 */
	public static ImageIcon surligne = new ImageIcon("img/case.png");
	
	/**
	 * L'image en mode standard pour l'affichage disponible pour plusieurs classes
	 */
	public static ImageIcon defaultIcon = new ImageIcon("img/sable.jpg");
	
	/**
	 * Un personne P pour pouvoir essayer de le placer/deplacer 
	 */
	Personnage perso ;
	
	/**
	 * une boolean qui nous indique si un personnage est présent sur la case ou pas
	 * @return true si la case est occupée par un personnage
	 * @return false si la case n'est pas occupée par un personnage
	 */
	boolean contientPersonnage = false ;

	/**
	 * Constructeur qui réer une case de sable, soit un case sur laquelle on peut se déplacer car il n'y a aucun obstacle 
	 * @param x coordonnée abcisse de la case 
	 * @param y coordonnée ordonnée de la case 
	 */
	public Case(int x , int y){
		this.x = x ;
		this.y = y ;
		setIcon(icone);
		addActionListener(this);
		estTraversable = true ;
	}

	/**
	 * Méthode qui nous indique si la case est vide ou non pour savoir si on peut ou pas autoriser le déplacement
	 * @return true si la case est vide 
	 * @return false si la case n'est pas vide.
	 */
	public boolean estVide() {
		return (id == 0);
	}

	/**
	 * Retourne l'id de la case
	 * @return String avec l'id de la case, c'est à dire le type (eau,brouillard..)
	 */
	public String toString(){
		return "" + id ;
	}

	/**
	 * Place un personnage sur cette case (système de copier/coller de personnage : On prend un personnage , on le retire et on le place sur une autre case)
	 * @param p Personnage a placer sur une case
	 */
	public void placerPersonnage(Personnage p){
		this.perso = p ;
		icone = p.icone ;
		setIcon(icone);
		contientPersonnage =  true ;
		estTraversable = false ;
	}

	/**
	 * Méthode lancée lors d'une clique sur cette case 
	 * @see Case#interaction()
	 */
	public void actionPerformed(ActionEvent e) {
		interaction();
	}

	/**
	 * Enlève le personnage présent dans la case, on s'en sert quand le personnage se déplace (on le prend , on l'enleve et on le colle sur une autre case)
	 *  
	 */
	public void enleverPersonnage(){
		setIcon(defaultIcon);
		icone = defaultIcon ;
		perso = null ;
		contientPersonnage = false ;
		estTraversable = true ;
	}

	/**
	 * Cette méthode laisse deux possibilité. 
	 * Si la case est prise par un personnage , elle effectuera l'action du personnage (par exemple un piègeur se transformera en pierre)
	 * Si la case n'est pas prise par un personnage, le personnage se déplacera dessus
	 */
	public void interaction(){
		if(contientPersonnage){
			perso.interaction();
		}else{
			if(Main.actif.seDeplacer(this)){
				placerPersonnage(Main.actif);
			}
		}
	}

	/**
	 * Compare deux cases sur l'abscisse, l'ordonnée et leur nature (Rocher, Bateau etc..)
	 * @param Case que l'on veut comparer.
	 * @return true si les cases sont de même nature 
	 * @return false si les cases sont différentes
	 */
	public boolean equals(Case c){
		return (x == c.x && y == c.y && id == c.id);
	}

	/**
	 * Méthodes qui nous indique si la case séléctionner est traversable
	 * @return true si la case est traversale(donc si il n'y a pas d'eau , ni de personnage deja dessus ou de rochers)
	 * @return false si la case est deja occupée par quelque chose
	 */
	public boolean estTraversable(){
		return estTraversable ;
	}

	/**
	 * Change l'icône de la case pour indiquer qu'elle est accessible au personnage actif
	 */
	public void surligner(){
		if(contientPersonnage){
			setIcon(perso.surligne);
		}else{
			setIcon(Case.surligne);
		}
	}

	/**
	 * Change l'icône de la case pour indiquer qu'elle n'est pas accessible au personnage actif
	 * Modifie l'affichage
	 */
	public void desurligner(){
		if(contientPersonnage){
			setIcon(icone);
		}else{
			setIcon(Case.defaultIcon);
		}
	}
}
