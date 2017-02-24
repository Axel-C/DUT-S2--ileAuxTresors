import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Classe qui permet de gérer les équipes,de créer des équipes, d'ajouter un personnage à l'équipe,de définir le tour de jouer
 * et aussi éliminé une équipe
 * Classe essentiel car c'est celle qui va permettre de constituer au joueur sa propre équipe 
 * @author Ulysse,Alexis,Axel,Benjamin
 *
 */

public class Equipe {
	
	/**
	 * nom de l'équipe
	 */
	public String nom ;
	
	/**
	 * Liste des personnages d'une equipe
	 */
	public List<Personnage> membre = new ArrayList<Personnage>();
	
	/**
	 * Liste des équipes qui contiennent une liste des personnages
	 * @see Equipe#membre
	 */
	public static List<Equipe> liste = new ArrayList<Equipe>() ;
	
	/**
	 *Un iterateur pour savoir qui doit jouer 
	 */
	private static Iterator<Equipe> i ;
	
	/**
	 * Donne la position du bateau de l'équipe
	 */
	public Bateau bateau ;
	
	/**
	 * Donne une vue à l'équipe
	 */
	public Vue vue ;
	
	/**
	 * le nombre de personnage dans la liste des personnages
	 */
	public int nbPersonnage = 0 ;
	
	/**
	 * le nombre de personnage dans la liste des personnages
	 */
	int id ;
	
	/**
	 * Cette booléan nous indique si il y a un ia dans la partie ou pas
	 * @return true si il y a un ia
	 * @return false si il n'y as plus d'ia
	 */
	boolean ia ;
	
	/**
	 * Compte le nombre d'id
	 */
	private static int cptid  = 0 ;
	
	/**
	 * Constructeur d'équipe, créer une nouvelle équipe en choissisant son nom,lui attribue une vue et un bateau et une liste de personnages vide
	 * @param String nom de l'équipe
	 */
	Equipe(String nom){
		this.nom = nom ;
		id = cptid++ ;
		liste.add(this);
		
		vue = new Vue(this);
		Main.carte.placerBateau(this);
		i = liste.iterator();
	}
	
	/**
	 * Ajoute un Personnage à une équipe et le met dans le bateau
	 * @param Personnage p le personnage à ajouter à la liste
	 */
	public void ajouterPersonnage(Personnage p) {
		membre.add(p);
		nbPersonnage++ ;
		p.equipe = this ;
		p.occupe = bateau ;
	}
	
	/**
	 * Itérateur de le liste d'équipe qui détermine qui doit jouer
	 * @return L'équipe qui doit jouer
	 */
	public static Equipe aQuiLeTour(){
		if(!i.hasNext()){
			i = liste.iterator() ;
		}
		return i.next();
	}
	
	/**
	 * Supprime toutes les équipes 
	 */
	public static void suprimerTous(){
		liste.removeAll(liste);
		i = liste.iterator();
	}
	
	/**
	 * Méthode toString 
	 * @return String nom de l'équipe
	 */
	public String toString(){
		return nom ;
	}
	
	/**
	 * Compare les compositions de deux équipes
	 * @param Equipe Equipe à comparer
	 * @return true si la composition des équipes sont les mêmes
	 * @return false si la composition des équipes sont différentes
	 */
	public boolean equals(Equipe equipe){
		return (this.id == equipe.id) ;
	}
	
	/**
	 * 
	 * reinitialise les points d'actions des personnages et donne 10 d'energie en plus si un personnage est sur un bateau
	 * Méthode qu'on appel au début du tour
	 */
	public void reinitialiser(){
		for(Personnage p :membre){
			if(p.classe == 'c'){
				p.pa = Options.nbPointAction * 2 ;
			}else{
			p.pa = Options.nbPointAction ;
			}
			if(p.occupe.equals(bateau)){
				p.ajouterEnergie(10);
			}
		}
	}
	
	/**
	 * Suprime une équipe quand elle est éliminé
	 */
	public void suprimerEquipe(){
		Equipe.liste.remove(this);
		i = liste.iterator();
		 if(this.equals(Main.equipeActive)){
			Main.tourSuivant();
		}
		 
	}
	
	/**
	 * Méthode qui permet de contrôler que tous les personnages ont joués ce tour
	 * elle est appeler au moment ou le joueur tente de finir son tour
	 * @return true si tout le monde à jouer
	 * @return false si tout le monde n'as pas jouer
	 */
	public boolean aJoue(){
        boolean retour = true ;
        for(Personnage p : membre){
            if(p.pa == Options.nbPointAction && (p.classe != 'p')){
                retour = false ;
            }
        }
        return (retour || Main.p.paInfinie.isSelected());
    }
}
