import javax.swing.ImageIcon;

/**
 * Cette classe gère les vue entre les deux personnes. En effet , on ne doit pas supperposer la vue des deux camps.
 * C'est donc ici que l'on gère les méthodes pour retirer le brouillard et actualiser la vue
 * 
 * @author Ulysse,Axel,Alexis,Benjamin
 *
 */
public class Vue extends Carte {
	/**
	 * Type Equipe qui défini une équipe (joueur ou IA)
	 */
	Equipe equipe ;
	
	/** 
	 * Tableau à double entier qui montre seulement la partie de tableau découvert
	 */
	boolean decouverte[][] ;
	
	/**
	 * boolean qui indique si le coffre nous est montré ou non
	 */
	boolean coffreDecouvert = false ;
	
	/**
	 * Boolean qui indique si le coffre nous est montré ou non
	 */
	boolean clefDecouvert = false ;
	
	/**
	 * Ce constructeur permet de construire la vue d'une équipe associé
	 * Cette vue nous est nécessaire pour l'affichage 
	 * @param Equipe choisis l'équipe a qui on associe une vue
	 */
	public Vue(Equipe e){
		super(Main.carte.longeur , Main.carte.largeur);
		equipe =  e ;
		decouverte = new boolean[Main.carte.largeur][Main.carte.longeur];
		for(int i = 0 ; i < largeur ; i++){
			for(int j = 0 ; j < longeur ; j++){
				if(i == 0 || j == 0 || i == largeur - 1 || j == longeur - 1){
					placer(new Eau(j, i));
				}else{
					placer(new Brouillard(j, i));
				}
					
			}
		}
		
	}
	
	/**
	 * Après un mouvement il est nécessaire de mettre à jour la vue sur les découvertes de la carte
	 * ainsi que de rafraîchir les images surlignées afin de montrer les nouvelles cases disponible à nos personnages
	 * @see Vue#decouvrirZone(Case)
	 */
	public void actualiser(){
		decouvrirZone(equipe.bateau);
		for(int i = 0 ; i < largeur ; i++){
			for(int j = 0 ; j < longeur ; j++){
				// On surligne les cases accessibles
					if(Main.actif.estAccessible(get(j,i)) &&  Main.actif.peutJouer() 
							|| (get(j,i).contientPersonnage && get(j,i).perso.equipe.equals(equipe) )){
						get(j,i).surligner();
					}else{
						get(j,i).desurligner();
					}
				// On definis les images des bateaux
					if(get(j,i).id == 2 ){
						if(get(j,i).equals(equipe.bateau)){
							get(j,i).setIcon(new ImageIcon("img/navire1.jpg"));
							if(Main.actif.estAccessible(get(j,i)) &&  Main.actif.peutJouer()){
								get(j,i).surligner();
							}
						}else{
							get(j,i).setIcon(new ImageIcon("img/navire2.jpg"));
						}
						
					}
					// On affiche l'image du coffre si il est d�couvert
					if(coffreDecouvert && get(j,i).coffre){
						get(j,i).setIcon(new ImageIcon("img/coffre.png"));
					}else if(!coffreDecouvert && get(j,i).coffre){
						get(j,i).setIcon(Rocher.defaultIcon);
					}
					// On afficle la clef si elle a �t� d�couverte 
					if(clefDecouvert && get(j,i).cle){
						get(j,i).setIcon(new ImageIcon("img/clef.png"));
					}
			}
		}
	}
	
	/**
	 * Fonction qui permet de découvrir une case en particulier sur la carte.
	 * @see Case#Case(int, int)
	 */
	public void decouvrir(Case c){
		if(Main.partieEnCours){
		decouvrir(c.x, c.y);
		}
	}
	
	/**
	 * Découvre une case en particulier en fournissant ses coordonnées x et y
	 * @param x coordonnée x de la case
	 * @param y coordonnée Y de la case
	 */
	public void decouvrir(int x , int y){
		if(Main.partieEnCours){
		grille[y][x] = Main.carte.grille[y][x];
		}
	}
	
	/**
	 * Découvre une zone de cases
	 * @param Case la case sur laquel on veut découvrir ce qui il y a en plus de tous ses alentours
	 */
	public void decouvrirZone(Case c){
		decouvrirZone(c.x, c.y);
	}
	
	/**
	 * Découvre une case et toutes les cases adjacantes à celle-ci
	 * @param x abcisse de la case à découvir
	 * @param y ordonnée de la case à découvrir
	 */
	public void decouvrirZone(int x , int y){
		decouvrir(x + 1, y);
		decouvrir(x - 1, y);
		decouvrir(x, y + 1);
		decouvrir(x, y - 1);
		decouvrir(x, y);
		decouvrir(x+ 1, y + 1);
		decouvrir(x + 1, y - 1);
		decouvrir(x - 1, y + 1);
		decouvrir(x- 1, y- 1);
	}
	/**
	 * Découvre toutes les cases du plateau. Elle est utilise dans le mode triche afin de pouvoir effectuer nos test
	 * Elle permet d'avoir l'affichage du plateau au complet pour compter par exemple le pourcentage de rocher.
	 */
	public void toutDecouvrir(){
		for(int i = 0 ; i < largeur ; i++){
			for(int j = 0 ; j < longeur ; j++){
				decouvrir(get(j,i));
					
			}
		}
	}
	
}
