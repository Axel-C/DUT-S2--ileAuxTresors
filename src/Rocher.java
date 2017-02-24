import javax.swing.ImageIcon;

@SuppressWarnings("serial")
/**
 * La classe rocher est une case spéciale.
 * En effet le rocher est posé sur une case. Il peut soit caché une clef , soit un coffre ou bien ne rien caché.
 * C'est donc ici que nous définissons la création de rocher, ainsi que l'interaction avec le rocher et gérer son affichage.
 * @author Ulsse,Axel,Alexis,Benjamin
 * @see Case
 */
public class Rocher extends Case{
	/**
	 * Cette boolean nous sert a indique si le rocher contient le trésor ou non
	 * @return true si le rocher contient la clef
	 * @return false si le rocher ne contient pas la clef
	 */
	boolean tresor = false ;
	
	/**
	 * Image d'un rocher en surbrillance , image qui s'affiche lorsqu'un explorateur est juste a coté et veux effectuer une action
	 * @see Rocher#surligner()
	 */
	public static ImageIcon surligne = new ImageIcon("img/rocherSurligne.png");
	
	/**
	 * Image d'un rocher par défaut , image qui s'affiche lorsque l'explorateur n'est pas a proximité
	 * @see Rocher#desurligner()
	 */
	public static ImageIcon defaultIcon = new ImageIcon("img/pierre.jpg");
	
	/**
	 * Constructeur de la classe rocher créant un rocher ne camouflant rien 
	 * @param x l'abscisse de la case ou l'on veut placer un rocher
	 * @param y l'ordonnée de la case ou l'on veut placer un rocher
	 * @see Rocher#Rocher(int, int, boolean, boolean)
	 */
	public Rocher(int x, int y) {
		this(x,y,false,false);
	}
	
	/**
	 * Constructeur précis de rocher : en effet , on précise cette fois si on veut cacher la clef ou le coffre en dessous en utilisant le constructeur de case. 
	 * @param x abscisse de l'ou on souhaite placé ce rocher sur la carte
	 * @param y ordonnée de l'ou on souhaite placé ce rocher sur la carte
	 * @param coffre Cette boolean nous permet de créer le rocher avec un coffre caché
	 * @param cle Cette boolean nous permet de créer le rocher avec une clef cachée
	 * @see Case#Case(int, int)
	 */
	public Rocher(int x , int y , boolean coffre ,boolean cle){
		super(x, y);
		this.cle = cle ;
		if(coffre){
		this.coffre = coffre ;
		tresor = true ;
		}
		id = 3 ;
		estTraversable = false ;
		icone = new ImageIcon("img/pierre.jpg");
		setIcon(icone);
	}
	
	/**
	 * Cette méthode défini l'action a accomplir en cas de clique sur le rocher si le personnage actif est un explorateur.
	 * Elle nous diras ce qui se cache sous le rocher.
	 */
	public void interaction(){
		Personnage actif = Main.actif ;
		if(actif.estAccessible(this) && actif.classe == 'e' && actif.peutJouer()){
		actif.souleverRocher(this);
		}
		Main.p.afficher();
	}
	
	/**
	 * Méthode qui sert à retirer la clef et le trésor de sous le rocher.
	 * Elle est appelée seulement si l'explorateur souleve le rocher et possède une clef 
	 * @see Personnage#souleverRocher(Rocher) Exemple d'utilisation
	 */
	public void vider(){
		tresor = false ;
		cle = false ;
	}
	
	/**
	 * Surligne la case pour montrer qu'elle est accessible a l'explorateur qui est à coté
	 * @see Case#surligner()
	 */
	public void surligner(){
		
		setIcon(surligne);
	}
	
	/**
	 * Change l'icone de la case pour montrer qu'elle n'est accessible et donc que l'explorateur est trop loin 
	 * @see Case#surligner()
	 */
	public void desurligner(){
		setIcon(icone);
	}
}
