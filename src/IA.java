import java.util.Random;
/**
 * Notre première IA : C'est un IA random
 * Il fait des actions en aléatoire . Notre IA supérieur ne marche pas , il est dispo dans le package IA hypersecret
 * @author Ulysse,Axel,Alexis,Benjamin
 *
 */
public class IA {

	/*private static CarteSommet carteSommet = new CarteSommet(Main.carte);
	private static CarteSommet carteSommetVue = new CarteSommet(Main.carte);
	private static int attitude = new Random().nextInt(3);
	private static final int DEATH = 0;
	private static final int EXPLO = 1;
	private static final int VARIE = 2;

	private static int cptRemplirEquipe = 1;

	public static void jouer(Equipe equipe){

		if(cptRemplirEquipe < 5){
			remplirEquipe(equipe);			
		}

		int cpt = 0;
		Personnage actif = equipe.membre.get(cpt);
		actif.definirCommeActif();
		carteSommetVue.refaireCarteSommet(equipe.vue);
		
		if(actif instanceof Explorateur){
			
			
			Sommet objectif = choperSommetRocherPlusProche(actif);

			//boolean[][] chemin = carteSommet.pathFinding(depart, arrivee);
		}





		if(Main.partieEnCours){
			Main.tourSuivant();
		}


	}*/

	/**
	 * Si l equipe possede moins de cinq personnage et que la limite n a pas ete atteinte remplirEquipe va remplir l equipe selon un attitude choisie au hasard
	 * @param equipe Equipe jouee
	 */
	/*private static void remplirEquipe(Equipe equipe){
		cptRemplirEquipe++;
		System.out.println("L'attitude est : "+attitude);

		if(equipe.membre.size() < 5){
			if(attitude == DEATH){
				equipe.ajouterPersonnage(new Guerrier("Conquistador"));
			} else if (attitude == EXPLO){
				equipe.ajouterPersonnage(new Explorateur("SuperMichel"));
			} else if(attitude == VARIE){
				int r = new Random().nextInt(3);
				if(r == 0){
					equipe.ajouterPersonnage(new Explorateur("SuperMichel"));
				} else if (r == 1){
					equipe.ajouterPersonnage(new Guerrier("Conquistador"));
				} else {
					equipe.ajouterPersonnage(new Voleur("Arsene Lupin"));
				}
			}
		}

	}*/

	/**
	 * Permet d'avoir le sommet, voisin d un rocher, le plus proche du personnage
	 * @param perso Le personnage joue a ce moment la
	 * @return Retourne le sommet voisin d un rocher et qui n a jamais ete vu
	 */
	/*private static Sommet choperSommetRocherPlusProche(Personnage perso){
		Sommet result = new Sommet();
		boolean breaken = false;

		for(Sommet s : carteSommetVue.listeSommets){ // on calcule le poids en fonction de la pos du perso et on prend son sommet comme reusltat au cas il n y aurait pas de rocher possibles
			if(s.equals(new Sommet(perso.occupe, s.id))){
				carteSommetVue.calculPoids(s);
				result = s;
				break;
			}
		}


		for(Sommet s : carteSommetVue.listeSommets){
			if(s.estRocher() && s.rocherDejaVu == false){
				result = s;
				break;
			}
		}

		for(Sommet s : carteSommetVue.listeSommets){
			if(s.estRocher() && !s.equals(result) && s.rocherDejaVu == false && s.getValeur() < result.getValeur()){
				s = result;
			}
		}

		for(int i = 0; i < carteSommetVue.carteSommet.length; i++){
			for(int j = 0; j < carteSommetVue.carteSommet[0].length; j++){

				if(carteSommetVue.carteSommet[i][j].equals(result)){
					result = carteSommetVue.carteSommet[i+1][j];

					if(carteSommetVue.carteSommet[i-1][j].getValeur() < result.getValeur()){
						result = carteSommetVue.carteSommet[i-1][j];
					}
					if(carteSommetVue.carteSommet[i][j+1].getValeur() < result.getValeur()){
						result = carteSommetVue.carteSommet[i][j+1];
					}
					if(carteSommetVue.carteSommet[i][j-1].getValeur() < result.getValeur()){
						result = carteSommetVue.carteSommet[i][j-1];
					}
					breaken = true;
					break;
				}
			}
			if(breaken == true){
				break;
			}
		}


		return result;
	}*/

	/**
	 * Joue un tour avec toute l'équipe de L'IA
	 * @param equipe Equipe qui joue
	 * @see Personnage#interaction()
	 */
	public static void jouer(Equipe equipe){
		Carte carte = Main.carte ;
		Personnage actif = equipe.membre.get(0) ; // je récupère le premier personnage de la liste
		actif.definirCommeActif(); // je selectionne le personage

		while(!equipe.membre.isEmpty() && actif.peutJouer() && Main.partieEnCours){
			int r = new Random().nextInt(4);
			switch(r){
			case 0 : 
				carte.get(actif.occupe.x + 1, actif.occupe.y).interaction();
				break ;
			case 1 :
				carte.get(actif.occupe.x - 1, actif.occupe.y).interaction();
				break ;
			case 2 :
				carte.get(actif.occupe.x, actif.occupe.y + 1).interaction();
				break ;
			case 3 :
				carte.get(actif.occupe.x, actif.occupe.y - 1).interaction();
				break ;	
			}
		}



	}
}
