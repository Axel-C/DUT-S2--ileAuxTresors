import javax.swing.JOptionPane;
/**
 * le coeur de notre projet: Le main
 * Il permet de lancer une nouvelle partie, d'un recommencer une
 * Sélectionner le mode de jeu (Sauf les options qui sont le deuxième procédé pour y arriver)
 * Et enfin déterminer un gagnant
 * @author Ulysse,Axel,Alexis,Benjamin
 * @see Option
 */
public class Main {
	/**
	 * La carte de jeu 
	 * @see Carte#grille
	 */
	public static Carte carte ;
	
	/**
	 * Le personnage qui va jouer
	 * @see Personnage#definirCommeActif()
	 */
	public static Personnage actif ;
	
	/**
	 * L'équipe qui joue actuellement 
	 */
	public static Equipe equipeActive ;
	
	/**
	 * Le plateau pour l'affichage
	 */
	public static PlateauDeluxe p = new PlateauDeluxe();
	
	/**
	 * défini si une partie est en déroulement ou non
	 * @return true si une partie est en cours
	 * @return false si une partie n'est pas en cours
	 */
	public static boolean partieEnCours = false ;
	public static void main(String[] args) {}
	
	/**
	 * Lance une nouvelle partie et ferme la partie actuelle si il y en a une
	 */
	public static void nouvellePartie(){
		if(partieEnCours){
			fermerPartie();
		}
		partieEnCours = true ;
		carte = new Carte(Options.longueur,Options.largeur);
		for(int i = 0 ; i < Options.nbEquipe ; i++){
			int nbr = i + 1 ;
			@SuppressWarnings("unused")
			Equipe e =new Equipe("Equipe " + nbr);
			
		}
		for(int i = 0 ; i < Options.nbIA ; i++){
			int nbr = i + 1 ;
			Equipe e =new Equipe("Equipe (IA) " + nbr);
			e.ia = true ;
			
		}
		carte.generer(Options.pourcentage);
		for(Equipe e : Equipe.liste){
			e.ajouterPersonnage(new Explorateur());
		}
		p.initialiser();
		tourSuivant();
	}
	/**
	 * Lance une partie rapide avec un joueur face à l'ia et une carte de base
	 */
	public static void partieRapide(){
		if(partieEnCours){
			fermerPartie();
		}
		partieEnCours = true ;
		carte = new Carte(Options.longueur,Options.largeur);
		Equipe eq1 = new Equipe("Joueur");
		Equipe eq2 = new Equipe("IA");
		carte.generer(10);
		Explorateur Indiana = new Explorateur();
		eq2.ajouterPersonnage(new Explorateur());
		eq1.ajouterPersonnage(Indiana);
		p.initialiser();
		eq2.ia = true ;
		tourSuivant();
	}
	
	/**
	 * Lance une partie avec 4 joueur et une carte 20*10 mode longue soirée
	 */
	public static void longueSoiree(){
		if(partieEnCours){
			fermerPartie();
		}
		Options.nbPointAction = 3 ;
		partieEnCours = true ;
		carte = new Carte(20,15);
		Equipe eq1 = new Equipe("France");
		Equipe eq2 = new Equipe("Angleterre");
		Equipe eq3 = new Equipe("Allemagne");
		Equipe eq4 = new Equipe("Espagne");
		carte.generer(10);
		eq1.ajouterPersonnage(new Explorateur("Napoléon"));
		eq2.ajouterPersonnage(new Explorateur("Mr. Bean"));
		eq3.ajouterPersonnage(new Explorateur("Albert Einstein"));
		eq4.ajouterPersonnage(new Explorateur("Christophe Colomb"));
		p.initialiser();
		tourSuivant();
	}
	
	/**
	 * Ferme la parie en cours si elle existe
	 */
	public static void fermerPartie(){
		Equipe.suprimerTous();
		partieEnCours = false;
		carte = null ;
		p.setVisible(false); // rend la fenetre invisible
		p.dispose(); //Detruit la fenetre 
		p = new PlateauDeluxe(); // On ouvre le menu principal
	}
	
	/**
	 * Lance un nouveau tour de jeu
	 */
	public static void tourSuivant(){
		if(Equipe.liste.size() == 1){
			victoire(Equipe.liste.get(0));
		}else{
		equipeActive = Equipe.aQuiLeTour() ;
		equipeActive.reinitialiser();
		actif = equipeActive.membre.get(0);
		p.afficher();
		if(equipeActive.ia){
			IA.jouer(equipeActive);
		}
		}
	}
	
	/**
	 * Donne la victoire à une équipe 
	 * @param Equipe equipeVictorieuse Equipe qui gagne
	 */
	public static void victoire(Equipe equipeVictorieuse){
		JOptionPane.showMessageDialog(p, "Bravo à l'equipe " + equipeVictorieuse.nom ,"Victoire !" , 1);
		fermerPartie();
	}
}
