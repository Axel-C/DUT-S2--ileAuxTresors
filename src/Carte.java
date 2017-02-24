import java.util.Random;
/**
 * Cette classe est nécessaire a l'affichage . En effet elle génére une carte sur laquel nous allons placer tous nos objets nécessaire au jeu
 * C'est donc ici que la plupart des méthodes de Eau,Rocher,Bateau par exemple
 * C'est donc l'une des bases de ce projet. Cette carte est composée de Cases
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Case
 */

public class Carte {
	/**
	 * Largeur voulue pour la carte
	 */
	public int largeur ;
	
	/**
	 * Longueur voulue pour la carte
	 */
	public int longeur ;
	
	/**
	 *La carte que nous allons utiliser le long du projet
	 */
	public Case grille[][];

	/**
	 *  Constructeur qui initialise la carte avec des paramètres de longeur et de largeur fournit. 
	 * @param largeur de la carte 
	 * @param longeur longeur de la carte 
	 */
	public Carte(int longueur , int largeur){
		this.largeur = largeur ;
		this.longeur = longueur ;
		grille = new Case [largeur][longueur];
		for(int i = 0 ; i < largeur ; i++){
			for(int j = 0 ; j < longeur ; j++){
				if(i == 0 || j == 0 || i == largeur - 1 || j == longeur - 1){
					placer(new Eau(j, i));
				}else{
					placer(new Case(j,i));
				}
			}
		}
	}
	
	/**
	 * Créer une carte avec 10 de largeur , 10 de longeur en appelant le constructeur du dessus
	 * @see Carte#Carte(int, int)
	 */
	public Carte() {
		this(10,10);
	}
	
	/**
	 * Méthode qui ajoute des rochers et fais apparaitre un bateau pour chaque équipe sur la carte. 
	 * @param pourcent Pourcentage de rocher voulu.
	 */
	public void generer(int pourcent){
		boolean placement;
		do{
			genereRochers(pourcent);
			for(int i = 0 ; i < Options.nbrCles ; i++){
				placerCles();
			}
			for(int i = 0 ; i < Options.nbCoffre ; i++){
				placerCoffre();
			}
			placement = placementOK();
		}while(placement == false);
	}
	
	/**
	 * Retourne une chaîne avec tous les id de toutes les cases contenue dans la carte
	 * les id sont les types de la case . Chaque type correspont a un type de case.
	 * @return String nous donne le types des cases dans une chaîne
	 */
	public String toString(){
		String retour = "" ;
		for(int i = 0 ; i < largeur ; i++){
			for(int j = 0 ; j < longeur ; j++){
				retour += grille[i][j].id;
			}
			retour += "\n" ;
		}
		return retour ;
	}
	
	/**
	 * Méthode qui permer de place le bateau d'une équipe à une position aléatoire sur une rive
	 * ainsi que de le placer sur la carte
	 */
	public void placerBateau(Equipe equipe){
		int x  = 0 ;
		int y  = 0;
		int c = new Random().nextInt(4) ;
		switch (c) {
		case 0: 
			x =new Random().nextInt(longeur - 2) +1  ;
			y = 1 ;
			break;
		case 1: 
			y = new Random().nextInt(largeur - 2) +1 ;
			x = 1 ;
			break;
		case 2:
			x =new Random().nextInt(longeur - 2) +1 ;
			y = largeur - 2 ;
			break;
		case 3:
			y =new Random().nextInt(largeur - 2)  +1;
			x = longeur - 2 ;
		}
		if(grille[y][x].estVide()){
			Bateau boat= new Bateau(x, y , equipe);
			equipe.bateau = boat ;
			this.placer(boat);
		}else{
			placerBateau(equipe);
		}
	}
	
	/**
	 * Place la case de votre choix aux coordonnées x et y de la case
	 * @param Case une case de notre choix.
	 */
	public void placer(Case c) {
		grille[c.y][c.x] = c ;
	}
	
	/**
	 * Générer l'apparition aléatoire des rochers sur la carte
	 * @param pourcent Porcentage de rocher voulue
	 *  
	 */
	private void genereRochers(int pourcent){
			double rocherMax = ((longeur-2)*(largeur-2)*(pourcent/100.0)) - (Options.nbCoffre + Options.nbrCles);
			int x;
			int y;
			for(int i = 0 ; i < rocherMax ; i++){
				x = new Random().nextInt(longeur - 2)+1 ;
				y = new Random().nextInt(largeur - 2)+1 ;
				if(grille[y][x].estVide()){
					placer(new Rocher(x,y));
				}else{
					i-- ;
				}
			}
	}
	
	/**
	 * Place un rocher avec une clé à des coordonnées aléatoires sur la carte
	 * En effet c'est un rocher spécial que l'on décompte de genereRochers
	 * @see Carte#genereRochers(int)
	 */
	private void placerCles(){
		Random r = new Random();
		int y = r.nextInt(largeur-2)+1; // pour aller de 1 à ligne-2
		int x = r.nextInt(longeur-2)+1;
		if(grille[y][x].estVide()){
			placer(new Rocher(x, y , false , true ));
		}else{
			placerCles();

		}
	}
	
	/**
	 * Place un rocher avec un coffre à des coordonnées aléatoires sur la carte
	 * C'est un rocher spécial que l'on décompte de genreRochers
	 * @see Carte#genereRochers(int)
	 * @see Carte#placerCles()
	 */
	private void placerCoffre(){
		Random r = new Random();
		int y = r.nextInt(largeur-2)+1; // pour aller de 1 � ligne-2
		int x = r.nextInt(longeur-2)+1;
		if(grille[y][x].estVide()){
			placer(new Rocher(x, y , true , false ));
		}else{
			placerCoffre();

		}
	}
	
	/**
	 * 	Vérifie la validité de carte en vérifiant si toutes les cases sont accessibles pour le joueur
	 *  Pour cela on se sert des ID des cases
	 *  Comme la méthode est compliqué à comprendre, je vous invite à lire les commentaires
	 * @return true si la carte est conforme et donc jouable
	 * @return false si la carte n'est pas conforme et donc pas jouable
	 */
	private boolean placementOK(){
		
		final short VIDE = 0; // vide = on ignore ce que c'est.
		final short SABLE = 1;
		final short ROCHER = 2;
		final short BATEAU = 3;// constante pour identifier les differentes cases (short pour prendre moins de memoire)
		final short EAU = 4;
		int xVerif=1, yVerif=1, cptDeVide, cptBateau; // pour les boucles for, pour reprendre la on s etait arrete + cptdevide pour compter le vide
		
		boolean verifFinie;
		
		short[][] carteVerif = new short[largeur][longeur]; // carte qui nous permet de traiter des int et pas tripoter la carte de cases
		
		for(int a = 0; a < largeur; a++){ 
			for(int b = 0; b < longeur; b++){
				if( grille[a][b] instanceof Eau){
					carteVerif[a][b] = EAU;
					
				}else if( grille[a][b] instanceof Bateau){
					carteVerif[a][b] = BATEAU;
					
				}else {
					carteVerif[a][b] = VIDE;
				}
			}
		}
		
		do {
			cptDeVide = 0;
			verifFinie = true;
			
			for(int i = xVerif; i < largeur-1; i++){ //on rempli le tableau (-1 pour eviter d'aller sur les cases d'eau)
				for(int j = yVerif; j < longeur-1; j++){ //on traite que l'ile on se preoccupe pas de l'eau autour -> on rempli le tableau de int
					if(grille[i][j].estTraversable() ){ // tout ce qui une case vide
						carteVerif[i][j] = SABLE; //cases traversable autres que bateau
					} else if(!grille[i][j].estTraversable()) { //le reste
						carteVerif[i][j] = ROCHER; //5 pour rochers & autres non traversable
						if(i < largeur-2){
							i++;
							j = 0;
						}
					}
				}
			}
			
			for(int a = 1; a < largeur-1; a++){
				for(int b = 1; b < longeur-1; b++){

					cptBateau = 0;
					
					if( (carteVerif[a][b] == BATEAU) && carteVerif[a-1][b] == ROCHER)  {cptBateau++;}
					if ( (carteVerif[a][b] == BATEAU) && carteVerif[a+1][b] == ROCHER) {cptBateau++;}
					if ( (carteVerif[a][b] == BATEAU) && carteVerif[a][b-1] == ROCHER) {cptBateau++;}
					if ( (carteVerif[a][b] == BATEAU) && carteVerif[a][b+1] == ROCHER) {cptBateau++;} // Ici on regarde s'il y a un rocher a coté d'un bateau
					
					if(cptBateau > 3){return false;}
					
					if(carteVerif[a][b] == SABLE && carteVerif[a+1][b] == VIDE){// si c'est un traversable && on regarde s'il reste des cases non verifiées mais atteignable
						xVerif = a+1; // On chope les coordonnées pour continuer le remplissage a partir de là
						yVerif = b;
						verifFinie = false;
					} else if(carteVerif[a][b] == SABLE && carteVerif[a-1][b] == VIDE){
						xVerif = a-1;
						yVerif = b;
						verifFinie = false;
					} else if(carteVerif[a][b] == SABLE && carteVerif[a][b+1] == VIDE){
						xVerif = a;
						yVerif = b+1;
						verifFinie = false;
					} else if(carteVerif[a][b] == SABLE && carteVerif[a][b-1] == VIDE){
						xVerif = a;
						yVerif = b-1;
						verifFinie = false;
					}

					if(carteVerif[a][b] == VIDE){//on regarde s'il reste des cases non verifées 
						cptDeVide++;
					}	
				}

			}
			
		}while(verifFinie == false);
		
		
		if(cptDeVide > 0){
			return false;
		}
		return true;
		
		
	}
	/**
	 * Getter: Donne la case aux coordonnées entrées en paramètre
	 * @param x coordonnees x de la case voulue
	 * @param y coorodnnes y de la case voulue
	 * @return la case aux coordonnees donnees
	 */
	public Case get(int x , int y){
		return grille[y][x] ;
	}
}

