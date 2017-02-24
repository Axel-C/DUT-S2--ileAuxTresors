import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 * Cette classe est la base de tous les classes des personnages . C'est ici que l'on gère l'énergie des personnages
 * Que l'on selectionne un personnage comme étant actif, qu'on le déplace,et que l'on défini les spécificités des personnages
 * Selon leurs types . Par exemple c'est ici que l'on gère le nouveau vol d'objet du voleur ou bien que l'on gère l'échange de clef entre joueurs
 * C'est une méthode abstract , on devra redéfinir certains fonctions dans leurs classes associées.
 * @see Eclaireur
 * @see Explorateur
 * @see Voleur
 * @see Piegeur
 * @see Guerrier
 * 
 * @author Ulysse,Axel,Alexis,Benjamin
 */
public abstract class Personnage{
	/**
	 * Nom du personnage
	 */
	String nom ;
	
	/**
	 * Nombre de points d'actions disponible durant un tour
	 */
	int pa = 0 ;
	
	/**
	 * nombre de points d'energie disponible sur le personnage
	 */
	int energie = 100 ;
	
	/**
	 * Défini l'occupation d'une case par un personnage
	 */
	public Case occupe ;
	
	/**
	 * Défini l'équipe dans lequel le personne se situe
	 */
	public Equipe equipe ;
	
	/**
	 * Image du personnage de base nécessaire pour l'affichage:L'explorateur
	 */
	ImageIcon icone = new ImageIcon("img/explo.jpg");
	
	/**
	 * Image du personnage quand il est sélectionner
	 * @see Personnage#surligne
	 */
	ImageIcon surligne = new ImageIcon("img/exploSurligne.jpg");
	
	/**
	 * Défini l'inventaire du personnage
	 */
	ArrayList<String> inventaire;
	
	/**
	 * Ce caractère défini le type du personnage : 'e' pour Explorateur ,'v' pour voleur ,'g' pour guerrier,'c' pour éclaireur,'p' pour piégeur
	 */
	char classe ;
	
	/**
	 * Variable spécial pour le piègeur, défini si son été est camoufler ou non
	 */
	public boolean enRocher = false ;
	
	/**
	 * Constructeur de la classe Personnage.
	 * @deprecated 
	 */
	Personnage(){}
	
	/**
	 * Sélectionne un personnage comme personnage actif, on indique au programme que nous allons effectuer des actions avec le personnage choisis
	 *
	 */
	public void definirCommeActif(){
		if(Main.p.choixPersonnage.getSelectedIndex() != equipe.membre.indexOf(this)){
			Main.p.choixPersonnage.setSelectedIndex(equipe.membre.indexOf(this));
		}
		if(!Main.actif.equals(this)){
			Main.actif = this ;
		}
	}
	/**
	 * Déplace un personnage choisis sur une case a condition que le personnage peut jouer et que la case est traversable
	 * @param  Case sur laquel le personnage veut se déplace
	 * @return true si le personnage peut se déplacer sur la case.
	 * @return false si le personnage ne peut pas se déplacer sur la case.
	 */
	public boolean seDeplacer(Case c){
		if(c.estTraversable() && (estAccessible(c) || Main.p.deplacementLibre.getState()) && peutJouer()){

			if(enleverEnergie(1)){
				occupe.enleverPersonnage();
				c.placerPersonnage(this);
				occupe = c ;
				pa--;
				equipe.vue.decouvrirZone(c);
				Main.p.afficher();
			}else{
				return false ;
			}
			return true ;

		}else{
			return false ;
		}
	}
	
	/**
	 * Envoie une chaine de caractére sous forme  : "nom (nombre de points de vie ) objet" 
	 * qui sert à donner l'information au joueur et à stocker l'information dans la barre de sélection des personnages
	 */
	public String toString(){
		String retour = nom + " ("+energie+") ";
		for(String e : inventaire){
			retour += e ;
		}
		return retour ;
	}
	
	/**
	 * Méthode abstract qui défini si la case est accesible au personnage
	 * 
	 * @param Case
	 * @return true si la case est accessible
	 * @return false si la case n'est pas accessible
	 */
	public abstract boolean estAccessible(Case c);
	
	/**
	 * Renomme le personnage afin de mieux le reconnaître dans son équipe
	 * @see Personnage#nom
	 * @param String Nouveau nom à attribuer
	 */
	public void renommer(String nouveauNom){
		nom = nouveauNom ;
	}
	
	/**
	 * Tue un personnage : méthode utilisé quand le personnage n'as plus d'énergie ou que le piègeur arrive à piéger l'explorateur
	 */
	public void tuer(){
		occupe.enleverPersonnage();
		equipe.membre.remove(this);
		occupe = null ;
		if(equipe.membre.size() == 0){
			equipe.suprimerEquipe();
		}else{
			Main.p.afficher();
			Main.p.choixPersonnage.setSelectedIndex(0);
		}


	}
	/**
	 * Enlève de l'énergie au personnage: Méthode que l'on utilise quand le guerrier attaque 
	 * @param energieEnleve ,l'énergie que l'on retire a l'ennemi
	 * @return true si le personnage est toujours vivants
	 * @return false si le personnage est mort
	 */
	public boolean enleverEnergie(int energieEnleve){
		if(Main.p.energieInfini.getState()){
			energie = 100 ;

		}else{
			energie -= energieEnleve ;
			Main.p.afficher();
			if(energie < 0){
				tuer();
				return false ;
			}
		}
		return true ;
	}
	
	/**
	 * Ajoute de l'énergie au personnage : Méthode qui est appeler quand un personnage passe un tour dans un bateau
	 * @param int  energie à ajouter 
	 */
	public void ajouterEnergie(int energieAjoute){
		energie += energieAjoute ;
		if(energie > 100){
			energie = 100 ;
		}
	}
	
	/**
	 * Soulever un rocher(disponible uniquement pour l'explorateur) et nous renvoie un affichage nous disant ce qui se cacher sous le rocher 
	 * @param Rocher à soulever
	 */
	public void souleverRocher(Rocher r){
		enleverEnergie(5);
		pa-- ;
		if (r.cle){
			Main.p.message("Cool ! La clef du tresor ! :)");
			r.vider();
			inventaire.add("clef");
			Main.p.afficher(); 
		} else if (r.coffre){
			if(inventaire.contains("clef")){
				if(r.tresor){
					Main.p.message("Vous avez le trésor ! \n Dirigez-vous vers votre bateau pour gagner la partie.");
					Main.equipeActive.vue.coffreDecouvert = true ;
					Main.p.afficher();
					inventaire.add("Tresor");
					inventaire.remove("clef");
					r.vider();
				}else{
					Main.p.message("Le coffre est vide O_O \n Quelqu'un l'a déjà récupéré.");
					Main.equipeActive.vue.coffreDecouvert = true ;
					Main.p.afficher();
				}

				Main.p.afficher();
			}else{
				Main.p.message("Vous avez trouvé le coffre ! \n Il ne reste plus qu'à trouver la clef et revenir ici");
				Main.equipeActive.vue.coffreDecouvert = true ;
				Main.p.afficher();
			}


		} else {
			Main.p.message("Il n'y a rien ici :(");
		}
	}
	/**
	 * Interaction lorsque le personnage actif clique sur celui-ci: tout dépends de la classe du personnage
	 * Le piégeur se camoufle en mode rocher
	 * L'explorateur peut lancer un échange et donner sa clef
	 * @see Piegeur#modeRocher()
	 */
	public void interaction(){
		Personnage actif = Main.actif ;
		if(actif.equals(this) && classe == 'p'){
			Piegeur ac = (Piegeur) actif ;
			ac.modeRocher();
		}
		if(actif.equipe.equals(equipe)){
			if(actif.estAccessible(occupe) && !actif.inventaire.isEmpty() && !this.equals(actif) ){
				int b = JOptionPane.showConfirmDialog(Main.p, "Voulez vous transf�rer vos objets vers le personnage : " + nom +" ?"
						+ "\n \n Oui : Transférer les objets"
						+ "\n Non : Sélectionner "+ nom 
						+ "\n Annuler : Ne rien faire");
				if(b == JOptionPane.YES_OPTION){
					String objet = "" ;
					for(String s :actif.inventaire){
						objet = s ;
						inventaire.add(s);

					}
					actif.inventaire.remove(objet);
					Main.p.afficher();
				}else if(b == JOptionPane.NO_OPTION){
					definirCommeActif();
				}
			}else{
				definirCommeActif();
				Main.p.afficher();
			}
		}else{
			if(actif.estAccessible(occupe) && actif.peutJouer()  && actif.classe == 'v'){
				actif.voler(this);
			}else if(actif.estAccessible(occupe) && actif.peutJouer() && actif.classe == 'g'){
				actif.attaquer(this,20);
			}else if(actif.estAccessible(occupe) && actif.peutJouer() && this.classe == 'p' && enRocher){
				Main.p.message("Vous soulevez la pierre, et vous voyez une dague se diriger vers vous... Vous avez été piéger.");
				this.attaquer(actif,100);
			}
		}
	}
	/**
	 * Vole un personnage : action spécial du voleur
	 * @param p personnage � voler
	 * @see Voleur#volerClef(Personnage)
	 */
	public void voler(Personnage p){
		int r = new Random().nextInt(2) ;
		enleverEnergie(10);
		pa-- ;
		if(r == 1){
			if(p.inventaire.contains("clef") || p.inventaire.contains("Tresor")){

				if(p.inventaire.contains("clef")){
					inventaire.add("clef");
					p.inventaire.remove("clef");
					Main.p.message("Vous avez trouvez la clef");
				}
				if(p.inventaire.contains("Tresor")){
					inventaire.add("Tresor");
					p.inventaire.remove("Tresor");
					Main.p.message("Vous avez trouvez le trésor !");
				}
				Main.p.afficher(); 
			}else{
				Main.p.message("Aucun objet trouvé");
			}
		}else{
			Main.p.message("Votre tentative à échoué");
		}
	}
	
	/**
	 * Méthode qui indique si il reste des points d'actions à un personnage
	 * @return true si il peux jouer
	 * @return false si il ne peux plus jouer
	 */
	boolean peutJouer(){
		return (pa > 0 || Main.p.paInfinie.getState());
	}
	
	/**
	 * Attaque un autre personnage: méthode spécial du guerrier
	 * Si il tue l'ennemi , et que l'ennemie possède la clef il la récupére
	 * Si l'ennemi possède le trésor ,il récupère le trésor 
	 * @param p personnage à attaquer
	 */
	public void attaquer(Personnage p , int degat) {
		p.enleverEnergie(degat);
		enleverEnergie(10);
		if(p.energie < 0){
			@SuppressWarnings("unused")
			String objet = "" ;
			for(String s :p.inventaire){
				objet = s ;
				this.inventaire.add(s);
				if(this.inventaire.contains("clef")){
					Main.p.message("Vous avez trouvé la clef dans le sac de votre ennemi ");
				}
				if(this.inventaire.contains("Tresor")){
					Main.p.message("Vous avez trouvé le trésor dans le sac de votre ennemi");
				}
			}
			
		}
		pa-- ;
		Main.p.afficher();
	}
}