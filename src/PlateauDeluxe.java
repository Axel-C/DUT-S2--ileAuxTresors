import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.* ;
@SuppressWarnings("serial")

/**
 * Notre propre classe plateau que nous avons mise en place car le plateau original été devenu obsoleté
 * Nous l'avons améliorer des menus ,des fenêtres,et l'affichage.
 * @author Ulysse,Axel,Alexis,Benjamin
 *
 */

public class PlateauDeluxe extends JFrame{
	/**
	 * défini le menu principal
	 * avec une fenêtre
	 * une bar avec plusieurs options ou on stock les aides, la triche
	 */
	private JLabel menuPrincipal = new JLabel(new ImageIcon("img/fond.jpg"));
	// PLATEAU 
	JPanel plateau ;
	GridLayout grille ;
	// BAR DE MENU
	private JMenuBar bar = new JMenuBar();
	// MENU DE LA BAR
	//Menu jeu
	private JMenu jeu = new JMenu("Jeu");
	private  JMenuItem nouvPartie = new JMenuItem("Nouvelle partie");
	private  JMenuItem quitter = new JMenuItem("Quitter");
	//Menu triche
	public JCheckBoxMenuItem paInfinie = new JCheckBoxMenuItem("Point d'action infinie");
	public JCheckBoxMenuItem deplacementLibre = new JCheckBoxMenuItem("Deplacement libre");
	public JCheckBoxMenuItem energieInfini = new JCheckBoxMenuItem("Energie infinie");
	//Menu personnage
	private  JMenu MenuPersonnage = new JMenu("Personnage");
	//Menu aide
	private  JMenu aide = new JMenu("Aide");
	private  JMenuItem regles = new JMenuItem("Regles");
	private  JMenuItem aPropos = new JMenuItem("A propos ...");
	//BAR D'ACTION
	JComboBox<Personnage> choixPersonnage ;
	
	/**
	 * Créer une nouvelle fenêtre avec le menu principal
	 */
	public PlateauDeluxe(){
		// ON INITIALISE LA FENETRE 
		super("Carte au tresor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setSize(942, 900);
		setResizable(false);

		// ON ORGANISE LE POSITIONEMENT DES ELEMENTS
		setLayout(new BorderLayout());
		// MENU PRINCIPAL
		//partie rapide
		JButton partieRapide = new JButton();
		partieRapide.setIcon(new ImageIcon("img/PartieRapide.png"));
		partieRapide.setBounds(150, 200, 200, 250);
		partieRapide.addActionListener(new ActionListener() {
			
			/**
			 * Action effectuer quand il y a un clique sur la frame,lancement d'une partie rapide
			 */
			public void actionPerformed(ActionEvent e) {
				Main.partieRapide();
				
			}
		});
		menuPrincipal.add(partieRapide);
		//longue soiree
		JButton longueSoiree = new JButton();
		longueSoiree.setIcon(new ImageIcon("img/LongueSoiree.png"));
		longueSoiree.setBounds(400, 200, 200, 250);
		longueSoiree.addActionListener(new ActionListener() {
			
			/**
			 * Action effectuer quand il y a un clique la seconde frame, lancement d'une partie longue soirée
			 */
			public void actionPerformed(ActionEvent e) {
				Main.longueSoiree();
				
			}
		});
		menuPrincipal.add(longueSoiree);
		//options
		JButton options = new JButton();
		options.setIcon(new ImageIcon("img/options.png"));
		options.setBounds(650, 200, 200, 100);
		options.addActionListener(Options.ouvrirOption);
		menuPrincipal.add(options);
		//jouer
		JButton jouer = new JButton();
		jouer.setIcon(new ImageIcon("img/jouer.png"));
		jouer.setBounds(650, 350, 200, 100);
		jouer.addActionListener(new ActionListener() {
			
			/**
			 * Action qui se passe quand tu clique sur la frame
			 */
			public void actionPerformed(ActionEvent e) {
				Main.nouvellePartie();
				
			}
		});
		menuPrincipal.add(jouer);
		


		add(menuPrincipal , BorderLayout.CENTER);
		pack();

		// MENU JEU
		setJMenuBar(bar);

		bar.add(jeu);
		nouvPartie.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.nouvellePartie();

			}
		});
		jeu.add(nouvPartie);
		JMenuItem option = new JMenuItem("Option");
		option.addActionListener(Options.ouvrirOption);
		jeu.add(option);
		quitter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		jeu.add(quitter);




		// MENU AIDE 

		bar.add(aide);

		
		aide.add(regles);
		JDialog fenetreReg = new JDialog(Main.p, "Règles");
		regles.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(fenetreReg, "Bienvenue dans Chasse au Trésor !\n\n Le but du jeu est de trouver le trésor, caché sous un rocher de l'île, avant l'équipe adverse.\n"
						+ " Pour cela vous devrez trouver la clef, cachée elle aussi sous un rocher, trouver le coffre pour l'ouvrir et ramener le trésor à votre bateau.\n"
						+ " Votre équipe est composée:\n  - d'un explorateur qui peut soulever les rochers et prendre la clef et le tr�sor."
						+ "  - d'un voleur pouvant voler la clef à l'explorateur adversaire et la donner à son explorateur\n"
						+ "  - d'un guerrier pouvant attaquer les autres personnages et leur faire perdre de l'énergie\n"
						+ " L'énergie d'un personnage diminue pour chaque déplacement ou action qu'il réalise, alors retournez à votre bateau quand cela est necessaire !\n\n"
						+ " Amusez vous bien ;) et que le meilleur gagne !");
			}
		});

		aide.add(aPropos);
		JDialog fenetreAPropos = new JDialog(Main.p, "A propos");
		aPropos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(fenetreAPropos, "Nous sommes quatre étudiants de DUT informatique à avoir réalisé ce jeu, dans le cadre de notre projet de fin d'année.\n"
						+ "Voici nos noms et nos adresses mail :\n"
						+ " - Magnier Benjamin : benjmag@neuf.fr\n"
						+ " - Coeuret Axel : axel.coeuret@live.fr\n"
						+ " - Bels Alexis :tchobels@gmail.com \n"
						+ " - Brehon Ulysse :ulysse.brehon@gmail.com \n");
			}
		});




		// ON AFFICHE LA FENETRE
		setVisible(true);
	}
	/**
	 * Initialise la fenêtre de jeu
	 */
	public void initialiser(){
		//BAR DE MENU
		//Equipe
		JMenu MenuEquipe = new JMenu("Equipe");
		bar.add(MenuEquipe);
		JMenuItem nouvelleEquipe = new JMenuItem("Nouvelle Equipe");
		nouvelleEquipe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nouveauNom = JOptionPane.showInputDialog("Saisissez un nom pour votre équipe" , Main.equipeActive.nom);
				Equipe eq = new Equipe(nouveauNom);
				eq.ajouterPersonnage(new Explorateur());
				Main.p.afficher(); 
				
			}
		});
		MenuEquipe.add(nouvelleEquipe);
		JMenuItem renomerEquipe = new JMenuItem("Renomer Equipe");
		renomerEquipe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nouveauNom = JOptionPane.showInputDialog("Saisissez un nouveau nom pour votre é" , Main.equipeActive.nom);
				Main.equipeActive.nom = nouveauNom ;
				
			}
		});
		MenuEquipe.add(renomerEquipe);
		
		JMenuItem abandonner = new JMenuItem("Abandonner");
		abandonner.addActionListener(new ActionListener() {
			
			/**
			 * action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Main.equipeActive.suprimerEquipe();
				
				
			}
		});
		MenuEquipe.add(abandonner);
		
		
		
		//Personnage
		bar.add(MenuPersonnage,2);
		JMenuItem renommer = new JMenuItem("Renommer");
		JMenuItem nouveau = new JMenuItem("Nouveau");
		nouveau.addActionListener(nouveauPersonnage);
		JMenuItem tuer = new JMenuItem("Tuer");
		tuer.addActionListener(new ActionListener() {

			/**
			 * Action qui se passe au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Main.actif.tuer();
			

			}
		});
		renommer.addActionListener(new ActionListener() {

			/**
			 * Action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Personnage p = Main.actif ;
				String nouveauNom = JOptionPane.showInputDialog("Saisissez un nouveau nom pour votre personnage", p.nom);
				p.renommer(nouveauNom);
				Main.p.afficher();
				p.definirCommeActif();


			}
		});
		MenuPersonnage.add(nouveau);
		MenuPersonnage.add(renommer);
		MenuPersonnage.add(tuer);
		JMenuItem fermer = new JMenuItem("Fermer la partie");
		fermer.addActionListener(new ActionListener() {

			/**
			 * action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Main.fermerPartie();

			}
		});
		jeu.add(fermer, 1);
		// TRICHE 
		JMenu triche = new JMenu("Triche");
		triche.add(paInfinie);
		triche.add(deplacementLibre);
		deplacementLibre.addActionListener(new ActionListener() {
			
			/**
			 * action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Main.p.afficher(); 
				
			}
		});
		paInfinie.addActionListener(new ActionListener() {
			
			/**
			 * action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Main.p.afficher(); 
				
			}
		});
		triche.add(energieInfini);
		JMenuItem enleverBrouillard = new JMenuItem("Enlever brouillard");
		enleverBrouillard.addActionListener(new ActionListener() {
			
			/**
			 * action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Main.equipeActive.vue.toutDecouvrir();
				Main.p.afficher();
				
			}
		});
		triche.add(enleverBrouillard);
		// trouver coffre
		JMenuItem trouverCoffre = new JMenuItem("Trouver le coffre");
		trouverCoffre.addActionListener(new ActionListener() {
			
			/**
			 * action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				Main.equipeActive.vue.coffreDecouvert = true ;
				Main.p.afficher();
				
			}
		});
		triche.add(trouverCoffre);
		// trouver clef
		JMenuItem trouverClef = new JMenuItem("Trouver la clef");
		trouverClef.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.equipeActive.vue.clefDecouvert = true ;
				Main.p.afficher();
				
			}
		});
		triche.add(trouverClef);
		bar.add(triche);
		
		// BAR D'ACTION (EN BAS)
		JButton nouveauPersonnage = new JButton("Nouveau Personnage");
		nouveauPersonnage.addActionListener(this.nouveauPersonnage);
		choixPersonnage = new JComboBox<Personnage>();
		
		JButton finDuTour = new JButton("Fin du tour");
		JPanel barAction = new JPanel();
		barAction.setLayout(new BoxLayout(barAction, BoxLayout.X_AXIS));
		barAction.add(nouveauPersonnage);
		barAction.add(choixPersonnage);
		barAction.add(finDuTour);
		add(barAction, BorderLayout.SOUTH);

		finDuTour.addActionListener(new ActionListener() {

			/**
			 * action au clique
			 */
			public void actionPerformed(ActionEvent e) {
				if(Main.equipeActive.aJoue()){
				Main.tourSuivant();
				}else{
					message("Vous devez déplacer au moins un de vos personnage");
				}

			}
		});

		//PLATEAU DE JEU 
		remove(menuPrincipal);
		plateau = new JPanel();
		add(plateau, BorderLayout.CENTER);

		grille = new GridLayout(Main.carte.largeur, Main.carte.longeur);
		setSize(new Dimension(Main.carte.longeur * 50, Main.carte.largeur * 50));
		plateau.setLayout(grille);
		setResizable(false);

		setVisible(true);
	}
	ItemListener setActif =new ItemListener() {

		/**
		 * action au clique
		 */
		public void itemStateChanged(ItemEvent e) {
			if(choixPersonnage.getSelectedIndex() == -1){
			}else{
				Main.equipeActive.membre.get(choixPersonnage.getSelectedIndex()).definirCommeActif();
				Main.p.afficher();
			}
			
		}
	};
	/**
	 * Actualise l'affichage 
	 */
	public void afficher(){
		if(Main.partieEnCours){
		Personnage actif = Main.actif ;
		Equipe equipe = Main.equipeActive;
		equipe.vue.actualiser();
		setTitle("Carte au Tresor ("+ equipe + ")");
		plateau.removeAll();
		for(int i = 0 ; i < equipe.vue.largeur ; i++){
			for(int j = 0 ; j < equipe.vue.longeur ; j++){
				plateau.add(equipe.vue.grille[i][j]);
			}
		}
		plateau.updateUI();
		choixPersonnage.removeAllItems();
		choixPersonnage.removeItemListener(setActif);
		for(Personnage perso : equipe.membre){
			choixPersonnage.addItem(perso);
			choixPersonnage.setSelectedItem(actif);
		}
		choixPersonnage.addItemListener(setActif);
		equipe.vue.actualiser();
		}

	}

	// FENETRE NOUVEAU PERSONNAGE
	JLabel image ;
	String choix[] =  {"Explorateur", "Voleur", "Guerrier","Eclaireur","Piegeur"}; 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox choixClasse = new JComboBox(choix);
	JTextField nomPersonnage ;
	JFrame fenetre ;
	public  ActionListener nouveauPersonnage = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Main.equipeActive.nbPersonnage < Options.nbPersonnage){
			fenetre = new JFrame("Nouveau personnage");
			fenetre.setSize(500, 650);
			fenetre.setResizable(true);
			fenetre.setVisible(true);

			//IMAGE
			JPanel contenu = new JPanel();
			fenetre.add(contenu);
			BoxLayout organisateur = new BoxLayout(contenu, BoxLayout.Y_AXIS);
			contenu.setLayout(organisateur);
			image = new JLabel(new ImageIcon("img/Explorateur.png"));
			contenu.add(image);
			JLabel text = new JLabel("Classe du personnage :");
			contenu.add(text);
			
			choixClasse.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if(choixClasse.getSelectedItem().equals("Voleur")){
						image.setIcon(new ImageIcon("img/Voleur.png"));
					}else if (choixClasse.getSelectedItem().equals("Guerrier")){
						image.setIcon(new ImageIcon("img/guerrier.png"));
					}else if(choixClasse.getSelectedItem().equals("Eclaireur")){
						image.setIcon(new ImageIcon("img/Eclaireur.png"));
					}else if(choixClasse.getSelectedItem().equals("Piegeur")){
						image.setIcon(new ImageIcon("img/pi.png"));
					}else{
						image.setIcon(new ImageIcon("img/Explorateur.png"));
					}
				}
			});
			contenu.add(choixClasse);
			JLabel text2 =  new JLabel("Nom du personnage :");
			contenu.add(text2);
			nomPersonnage = new JTextField();
			nomPersonnage.setSize(200, 50);
			contenu.add(nomPersonnage);
			JButton ajouter = new JButton("Créer personnage");
			choixPersonnage.setSelectedIndex(0);
			ajouter.addActionListener(new ActionListener() {

				/**
				 * met à jour l'image et sélection un personnage quand on change de sélèction à la souris
				 */
				public void actionPerformed(ActionEvent e) {
					String nom = nomPersonnage.getText();
					if(nom.equals("")){
						JOptionPane.showMessageDialog(fenetre, "Vous n'avez pas saisi de nom de personnage \n Son nom sera donc Francis !");
						nom = "Francis";
					}
					
					Personnage perso = null ;
					switch(choixClasse.getSelectedIndex()){
					case 0: perso = new Explorateur(nom);
					break ;
					case 1: perso = new Voleur(nom);
					break ;
					case 2: perso = new Guerrier(nom) ;
					break ;
					case 3: perso = new Eclaireur(nom) ;
					break ;
					case 4: perso = new Piegeur(nom);
					break ;
					}
					
					Main.equipeActive.ajouterPersonnage(perso);
					fenetre.setVisible(false);
					fenetre.dispose();
					perso.pa = Options.nbPointAction ;
					if(perso.classe == 'c'){
						perso.pa = perso.pa * 2 ;
					}
					Main.p.afficher();
					perso.definirCommeActif();
					Main.p.afficher();
					
				
				}
				
			});
			contenu.add(ajouter);
			}else{
				Main.p.message("Vous avez atteint la limite de personnage.");
			}

		}
	};
	/**
	 * Envoie un message sous forme d'un JOptionPane, simplification de JOptionPane qui est utilisée plusieurs fois dans le projet
	 * @param message Message à envoyer à l'utilisateur
	 */
	public void message(String message){
		if(!Main.equipeActive.ia){
		JOptionPane.showMessageDialog(this, message);
		}
	}
	
}

