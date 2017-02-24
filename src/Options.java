import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Ceci est le deuxième moyenne de configurer une partie : en la configurant entièrement
 * elle ouvre une fenêtre ou l'on peut rentrer ses valeurs qui sont vérifier à l'aide d'un try catch.
 * la premiere méthode de jeu est dans le main
 * @see Main 
 * @author Ulysse,Axel,Alexis,Benjamin
 *
 */
public class Options {

	/**
	 * longueur de la carte choisis
	 */
	static int longueur = 10 ;
	
	/**
	 * largeur de la crte choisis
	 */
	static int largeur = 10 ;
	
	/**
	 * le pourcentage de rocher choisis
	 */
	static int pourcentage = 10 ;
	
	/**
	 * Le nombre de clef cachées sur la carte
	 */
	static int nbrCles = 1 ;
	
	/**
	 * Le nombre de coffre caché sur la carte
	 */
	static int nbCoffre = 1 ;
	
	/**
	 * Le nombre de point d'action des personnages par tour
	 */
	static int nbPointAction = 1 ;
	
	/**
	 * Le nombre d'équipes souhaités dans la partie
	 */
	static int nbEquipe = 2 ;
	
	/**
	 * Le nombre d'IA souhaités dans la partie 
	 */
	static int nbIA = 0 ;
	
	/**
	 * Le nombre de personnages autorisés par équipes
	 */
	static int nbPersonnage = 5 ;

	// PARTIE IHM
	
	/**
	 * La fenêtre principale
	 */
	static JDialog fenetre ;
	
	/**
	 * Champ a remplir par l'utilisateur 
	 * Longueur
	 * Largeur
	 * Pourcentage
	 * Nombre deClefs
	 * Nombre de coffres
	 * Nombre de points d'actions
	 * Nombre d'Equipes
	 * Nombre d'IA
	 * Nombre de personnages par équipes
	 */
	static JTextField champLongeur = new JTextField();
	static JTextField champLargeur = new JTextField();
	static JTextField champPourcentage = new JTextField();
	static JTextField champnbrClef = new JTextField();
	static JTextField champnbrCoffre = new JTextField();
	static JTextField champnbrAction = new JTextField();
	static JTextField champnbrEquipe = new JTextField();
	static JTextField champnbrIA = new JTextField();
	static JTextField champnbrPersonnage = new JTextField();

	/**
	 * Ajoute d'un actionlistener qui permet de détecter une action sur cette fenêtre
	 */
	static ActionListener ouvrirOption = new ActionListener() {
		
		/**
		 * Voici ce qu'il se passe quand la fenêtre reçoit un évènement
		 * il ouvre des options à configurer
		 */
		public void actionPerformed(ActionEvent e) {


			JPanel contenu = new JPanel() ;

			contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
			contenu.add(new JLabel("Longeur de la carte(minimum 4):"));

			champLongeur.setText(""+ Options.longueur);
			contenu.add(champLongeur);
			contenu.add(new JLabel("Largeur de la carte(minimum 4):"));

			champLargeur.setText(""+ Options.largeur);
			contenu.add(champLargeur);


			contenu.add(new JLabel("Pourcentage de rocher(maximum 30): "));
			champPourcentage.setText(""+ Options.pourcentage);
			contenu.add(champPourcentage);

			contenu.add(new JLabel("Nombre de clef : "));
			champnbrClef.setText(""+ Options.nbrCles);
			contenu.add(champnbrClef);

			contenu.add(new JLabel("Nombre de coffre : "));
			champnbrCoffre.setText(""+ Options.nbCoffre);
			contenu.add(champnbrCoffre);

			contenu.add(new JLabel("Nombre de points d'actions(minimum 1): "));
			champnbrAction.setText(""+ Options.nbPointAction);
			contenu.add(champnbrAction);

			contenu.add(new JLabel("Nombre d'équipe (Joueur): "));
			champnbrEquipe.setText(""+ Options.nbEquipe);
			contenu.add(champnbrEquipe);

			contenu.add(new JLabel("Nombre d'équipe (IA): "));
			champnbrIA.setText(""+ Options.nbIA);
			contenu.add(champnbrIA);

			contenu.add(new JLabel("Nombre de personnage par équipe(minimum 1): "));
			champnbrPersonnage.setText(""+ Options.nbPersonnage);
			contenu.add(champnbrPersonnage);






			JButton appliquer = new JButton("Appliquer");
			appliquer.addActionListener(new ActionListener() {
				
				/**
				 * test les entrées de l'utilisateur et le bloque si il rentre des mauvaises informations
				 */
				public void actionPerformed(ActionEvent e) {
					boolean verif=true;
					try{					
						if(Integer.parseInt(champPourcentage.getText()) >=30){
							JOptionPane.showMessageDialog(fenetre,"Le taux de rocher est incorrect");
							verif=false;
						}
						if((Integer.parseInt(champLongeur.getText()) <4) && (Integer.parseInt(champLargeur.getText()) <4)){
							JOptionPane.showMessageDialog(fenetre,"La taille de la carte est incorrect");
							verif=false;
						}
						if(Integer.parseInt(champnbrPersonnage.getText()) <1){
							JOptionPane.showMessageDialog(fenetre,"Le nombre de personnage est incorrect");
							verif=false;
						}
						if(Integer.parseInt(champnbrIA.getText())+Integer.parseInt(champnbrEquipe.getText()) <2){
							JOptionPane.showMessageDialog(fenetre,"Le nombre de participant est incorrect");
							verif=false;
						}
						if(Integer.parseInt(champnbrAction.getText())<0){
							JOptionPane.showMessageDialog(fenetre,"Le nombre de points d'actions est incorrect");
							verif=false;
						}
						if((Integer.parseInt(champnbrClef.getText()) <0 || (Integer.parseInt(champnbrCoffre.getText())<0))){
							JOptionPane.showMessageDialog(fenetre,"MODE DEATHMATCH");
							Options.nbCoffre=0;
							Options.nbrCles=0;
						}
						if(verif){
							Options.definir(Integer.parseInt(champLongeur.getText()),Integer.parseInt(champLargeur.getText()),Integer.parseInt(champPourcentage.getText()) 
									,Integer.parseInt(champnbrClef.getText()) , Integer.parseInt(champnbrCoffre.getText()) , Integer.parseInt(champnbrAction.getText()) , 
									Integer.parseInt(champnbrEquipe.getText()) , Integer.parseInt(champnbrIA.getText()) , Integer.parseInt(champnbrPersonnage.getText()));
							if(Main.partieEnCours){
								JOptionPane.showMessageDialog(fenetre, "Une partie est déja en cours :( \n Vos options seront pris en compte lors de la prochaine partie.");
							}
							fenetre.setVisible(false);
							fenetre.dispose();
						}
					}catch(NumberFormatException ef){
						Main.p.message("Saisie invalide");
					}
				}
			});
			contenu.add(appliquer);

			fenetre = new JDialog(Main.p, "Options", true);

			fenetre.add(contenu);
			fenetre.setSize(400, 450);
			fenetre.setVisible(true);
		}
	};
	/**
	 * Définie les options de jeu pour cette partie
	 * @param longueur Longeur de la carte
	 * @param largeur Largeur de la carte
	 * @param pourcentage Pourcentage de rocher
	 * @param nbCles Nombre de clef
	 * @param nbCoffre Nombre de coffre
	 * @param nbAction Nombre de point par personnage et par tour
	 * @param nbEquipe Nombre d'équipe incarner par un joueur
	 * @param nbIA Nombre d'ia incarner par une IA
	 */
	static void definir(int longueur , int largeur , int pourcentage , int nbCles , int nbCoffre , int nbAction , int nbEquipe , int nbIA , int nbPerso){
		Options.longueur = longueur ;
		Options.largeur = largeur ;
		Options.pourcentage = pourcentage ;
		Options.nbrCles = nbCles ;
		Options.nbCoffre = nbCoffre ;
		Options.nbPointAction = nbAction ;
		Options.nbEquipe = nbEquipe ;
		Options.nbIA = nbIA ;
		Options.nbPersonnage = nbPerso ;

	}
}
