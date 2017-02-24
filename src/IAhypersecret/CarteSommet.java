
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe qui permet d obtenir les plus courts chemins vers un objectif donnee en fonction d un point de depart donne en repliquant le plateau de jeu
 * @author Ulysse, Alexis, Axel, Benjamin
 *
 */
public class CarteSommet {

	public Sommet[][] carteSommet;
	private final int GAUCHE = 1;
	private final int DROITE = 2;
	private final int HAUT = 3;
	private final int BAS = 4;
	public List<Sommet> listeSommets = new ArrayList<Sommet>();

	
	/**
	 * Cree un tableau de deux dimensions qui est une replique du plateau de jeu avec, a la place des cases, des sommets
	 * @param carteCase Plateau de jeu a repliquer
	 * @see Sommet
	 * @see Carte
	 */
	public CarteSommet(Carte carteCase){
		listeSommets.clear();
		carteSommet = new Sommet[carteCase.largeur][carteCase.longeur];
		Case stock;
		int id = 0;
		
		for(int i =0; i < carteCase.largeur; i++){
			for(int j = 0; j < carteCase.longeur; j++){
				stock = carteCase.grille[i][j];
				carteSommet[i][j] = new Sommet(stock, id);
				listeSommets.add(carteSommet[i][j]);
				id++;
			}
		}
	}
	
	/**
	 * Modifie la carte de sommet pour une autre carte de cases : utile pour recharger les vues de l equipe
	 * @param carteCase Plateau de jeu a repliquer
	 * @see Sommet
	 * @see Carte
	 * @see Vue
	 */
	public void refaireCarteSommet(Carte carteCase){
		listeSommets.clear();
		Case stock;
		int id = 0;
		
		for(int i =0; i < carteCase.largeur; i++){
			for(int j = 0; j < carteCase.longeur; j++){
				stock = carteCase.grille[i][j];
				carteSommet[i][j] = new Sommet(stock, id);
				listeSommets.add(carteSommet[i][j]);
				id++;
			}
		}
	}

	/**
	 * Fonction qui calcul le chemin le plus court possible avec l'aide des valeurs des sommets calcule
	 * @param depart Le sommet de depart, là où le perso se situe 
	 * @param arrivee L'objectif a atteindre
	 * @return Retourne un tableau de boolean avec le chemin marque avec des true
	 * 
	 * @see calculPoids
	 * @see orientation
	 */

	public boolean[][] pathFinding(Sommet depart, Sommet arrivee){
		calculPoids(depart); //On calcule les poids en fonction du depart sur toute la carte

		Sommet morceauDuChemin = depart;
		Sommet stock = new Sommet(); // sommet de stock pour revenir en arriere si necessaire
		int[] orientation;
		int compteurSommet = 0; //compteur qui regarde si on a vu tout les voisins

		boolean[][] marque = new boolean[carteSommet.length][carteSommet[0].length]; // carte qui permet de savoir si on deja passee par un mauvais sommet
		boolean[][] path = new boolean[carteSommet.length][carteSommet[0].length]; // le chemin retourne

		Arrays.fill(marque, false);
		Arrays.fill(path, false);
		
		while(morceauDuChemin.equals(arrivee) == false){

			orientation = orientation(morceauDuChemin, arrivee); // on regarde si l obkectif est soit en haut soit en bas soit a droite 

			for(Sommet s : morceauDuChemin.voisins){ // on parcourt les voisins

				compteurSommet++;

				if(s.getValeur() == morceauDuChemin.getValeur()+1){ // on regarde si le voisin est un sommet qui avance 
					if(marque[s.getX()][s.getY()] == false){ // et s il n a pas ete marque : sinon on y est deja passe

						if(orientation[0] == orientation(morceauDuChemin, s)[0]){ // si le voisin a la bonne direction

							path[s.getX()][s.getY()] = true;
							stock = morceauDuChemin; // on stocke le sommet d avant
							morceauDuChemin = s;// on actualise l ancien sommet avec le voisin adequat
							compteurSommet = 0;
							break;

						} else if(orientation[1] == orientation(morceauDuChemin, s)[1]){ // si la premiere direction n est pas bonne on tente la deuxieme

							path[s.getX()][s.getY()] = true;
							stock = morceauDuChemin; // on stocke le sommet d avant
							morceauDuChemin = s;// on actualise l ancien sommet avec le voisin adequat
							compteurSommet = 0;
							break;

						} 
					}
				}
			}


			if(compteurSommet == morceauDuChemin.voisins.size()){ //si on a vu tt les sommet sans en choper un de bon
				if(!morceauDuChemin.equals(depart)){ //si il n'y a pas de sommet avec la bonne orientation et que le sommet actuel n est pas le sommet de depart

					marque[morceauDuChemin.getX()][morceauDuChemin.getY()] = true; // on le marque
					path[morceauDuChemin.getX()][morceauDuChemin.getY()] = false; //on le retire du chemin
					morceauDuChemin = stock; //on rebrousse chemin

				} else { // si on arrive c est qu on est au point de depart et que les voisins de conviennent pas avec les orientations

					for(Sommet s : morceauDuChemin.voisins){//on parcourt les voisins
						if(marque[s.getX()][s.getY()] == false){ // on prend un sommet pas marque, le premier qui vient

							path[morceauDuChemin.getX()][morceauDuChemin.getY()] = true;
							stock = morceauDuChemin;
							morceauDuChemin =s;
							break;
						}
					}

				}
			}
		}
		
		return path;
		
	}

	/**
	 * Fonction qui calcule l orientation entre le sommet de depart et le sommet d arrivee afin de savoir quel voisin prendre
	 * @param depart Sommet de reference : on calcule l orientation en fonction de lui
	 * @param arrivee Sommet qui fait office d objectif, on regarde dans sa direction pour 
	 * @return un tableau de int de deux places avec en premiere position droite ou gauche et en deuxieme position haut ou bas
	 * Si l objectif et directement vers une direction, le tableau sera rempli deux fois de cette direction
	 */
	private int[] orientation(Sommet depart, Sommet arrivee){
		int[] resultat = new int[2];
		resultat[0] = 0;
		resultat[1] = 0;

		if(arrivee.getX() > depart.getX()){
			resultat[0] = DROITE;
		} else if(arrivee.getX() < depart.getX()){
			resultat[0] = GAUCHE;
		}

		if(arrivee.getY() > depart.getY()){
			resultat[1] = BAS;
		} else if (arrivee.getY() < depart.getY()){
			resultat[1] = HAUT;
		}

		if(resultat[0] == 0){
			resultat[0] = resultat[1];
		} else if (resultat[1] == 0){
			resultat[1] = resultat[0];
		}

		return resultat;

	}

	/**
	 * Cette fonction calcule le poids en fonction du sommet de depart qui commence a zero.
	 * Ensuite les voisins du sommet actif (c est a dire celui qui a ses voisins checke) acquierent la poids du sommet actifs plus 1.
	 * @param depart le Sommet ou la fonction commence la propagation des poids.
	 * @see rencontreVoisin
	 */
	public void calculPoids(Sommet depart){
		for(Sommet s : listeSommets){ // on remplis les listes de voisins
			s.voisins = rencontreVoisin(s);
		}

		List<Sommet> actifs;
		List<Sommet> voisinAMettreActif = new ArrayList<Sommet>();
		boolean[] marque = SommetAMarquer();
		
		Arrays.fill(marque, false);

		depart.setValeur(0);
		marque[depart.id] = true;

		actifs = new ArrayList<Sommet>();
		actifs.add(depart);

		while( resteAMarquer(marque) == true ){

			for(Sommet actif : actifs){
				for(Sommet voisin : actif.voisins){
					if(marque[voisin.id] == false){
						voisin.setValeur(actif.getValeur()+1);
						marque[voisin.id] = true;
						voisinAMettreActif.add(voisin);
					}
				}
			}

			actifs.clear();
			actifs = remplirListe(voisinAMettreActif);
			voisinAMettreActif.clear();
		}
	}
	
	/**
	 * Rempli une liste du contenu d une liste mis en parametre
	 * @param voisinAmettreActif Liste qu on souhaite repliquer dans la liste retournee
	 * @return Une liste contenant les sommets de la liste en parametre
	 */
	private List<Sommet> remplirListe(List<Sommet> voisinAmettreActif){
		
		List<Sommet> res = new ArrayList<Sommet>();
		
		for(Sommet s : voisinAmettreActif){
			System.out.println("coucou remplir");
			res.add(s);
		}
		
		return res;
		
	}

	/**
	 * Fonction qui permet d'avoir un tableau de boolean avec les sommet a marquer
	 * @return un tableau de boolean rempli de false sauf les non traversables, qui n ont pas de voisins et donc qui ne sont pas a marquer
	 */
	private boolean[] SommetAMarquer(){
		boolean[] resultat = new boolean[listeSommets.size()];

		Arrays.fill(resultat, false);
		
		for(Sommet s : listeSommets){
			if(!s.estTraversable()){
				resultat[s.id] = true;
			}
		}

		return resultat;

	}
	/**
	 * Regarde si tout les sommets ont ete passe a true
	 * @param marque Tableau de boolean representant les sommets marques ou pas
	 * @return true si il en reste a marquer, 
	 * @return false s il n en reste pas
	 */
	private boolean resteAMarquer(boolean[] marque){

		for(int i = 0; i < marque.length; i++){
			if(marque[i] == false){
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Fonction qui rempli la liste de voisin d'un sommet en sachant qu un non traversable n a pas de voisin et n est pas considere comme un voisin
	 * @param quartier Sommet dont on souhaite voir sa liste de sommet rempli
	 * @return une liste de quatre voisins maximum
	 */
	
	private List<Sommet> rencontreVoisin(Sommet quartier){

		List<Sommet> resultat = new ArrayList<Sommet>();

		if(quartier.estTraversable()){

			for(Sommet s : listeSommets){

				if(s.estTraversable()){
					if(s.getX() == quartier.getX() && s.getY() == quartier.getY()+1){
						resultat.add(s);
					} else if(s.getX() == quartier.getX() && s.getY() == quartier.getY()-1){
						resultat.add(s);
					} else if(s.getX() == quartier.getX()+1 && s.getY() == quartier.getY()){
						resultat.add(s);
					} else if(s.getX() == quartier.getX()-1 && s.getY() == quartier.getY()){
						resultat.add(s);
					}

				}

			}

		}
		return resultat;
	}

}
