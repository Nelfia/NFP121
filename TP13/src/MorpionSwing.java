import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/** Programmation d'un jeu de Morpion avec une interface graphique Swing.
 *
 * REMARQUE : Dans cette solution, le patron MVC n'a pas été appliqué !
 * On a un modèle (?), une vue et un contrôleur qui sont fortement liés.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.4 $
 */

public class MorpionSwing {

	// les images à utiliser en fonction de l'état du jeu.
	private static final Map<ModeleMorpion.Etat, ImageIcon> images
			= new HashMap<ModeleMorpion.Etat, ImageIcon>();
	static {
		images.put(ModeleMorpion.Etat.VIDE, new ImageIcon("src/blanc.jpg"));
		images.put(ModeleMorpion.Etat.CROIX, new ImageIcon("src/croix.jpg"));
		images.put(ModeleMorpion.Etat.ROND, new ImageIcon("src/rond.jpg"));
	}

	// Choix de réalisation :
	// ----------------------
	//
	//  Les attributs correspondant à la structure fixe de l'IHM sont définis
	//	« final static » pour montrer que leur valeur ne pourra pas changer au
	//	cours de l'exécution.  Ils sont donc initialisés sans attendre
	//  l'exécution du constructeur !

	private ModeleMorpion modele;	// le modèle du jeu de Morpion

	//  Les éléments de la vue (IHM)
	//  ----------------------------

	/** Fenêtre principale */
	private JFrame fenetre;

	/** Bouton pour quitter */
	private final JButton boutonQuitter = new JButton("Q");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonNouvellePartie = new JButton("N");

	/** Cases du jeu */
	private final JLabel[][] cases = new JLabel[3][3];

	/** Zone qui indique le joueur qui doit jouer */
	private final JLabel joueur = new JLabel();


	// Le constructeur
	// ---------------

	/** Construire le jeu de morpion */
	public MorpionSwing() {
		this(new ModeleMorpionSimple());
	}

	/** Construire le jeu de morpion */
	public MorpionSwing(ModeleMorpion modele) {
		// Initialiser le modèle
		this.modele = modele;

		// Créer les cases du Morpion
		JPanel grille = new JPanel(new GridLayout(3, 3));
		// Construction de la grille
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = new JLabel();
				grille.add(this.cases[i][j]);

				// Ajout de l'événement listener de la souris
				final int x = i;
				final int y = j;
				this.cases[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) { actionCaseClicked(x, y);}
				});
			}
		}

		// Initialiser le jeu
		this.recommencer();

		// Construire la vue (présentation)
		//	Définir la fenêtre principale
		this.fenetre = new JFrame("Morpion");
		this.fenetre.setLocation(1600, 200);
		this.fenetre.setIconImage(new ImageIcon(Objects.requireNonNull(MorpionSwing.class.getResource("croix.jpg"))).getImage());

		// Construction du Menu
		JMenuBar menuBar = new JMenuBar();	// Barre de menu
		this.fenetre.setJMenuBar(menuBar);

		JMenu menu = new JMenu("Jeu");	// Onglet Jeu
		menuBar.add(menu);

		JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");	// Item NouvellePartie
		nouvellePartie.addActionListener(this::actionNouvellePartie);
		menu.add(nouvellePartie);
		JMenuItem quitter = new JMenuItem("Quitter");					// Item Quitter
		quitter.addActionListener(this::actionQuitter);
		menu.add(quitter);

		// Construction du container
		Container contenu = fenetre.getContentPane();
		contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
		// Ajout de la grille de Morpion
		contenu.add(grille);
		// Ajout de la dernière ligne
		JPanel derniereLigne = new JPanel(new GridLayout(1,3));
		derniereLigne.add(boutonNouvellePartie);
		boutonNouvellePartie.addActionListener(this::actionNouvellePartie);
		derniereLigne.add(joueur);
		boutonQuitter.addActionListener(this::actionQuitter);
		derniereLigne.add(boutonQuitter);
		contenu.add(derniereLigne);

		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// afficher la fenêtre
		this.fenetre.pack();			// redimmensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
	}


	// Quelques réactions aux interactions de l'utilisateur
	// ----------------------------------------------------

	/** Recommencer une nouvelle partie. */
	public void recommencer() {
		this.modele.recommencer();

		// Vider les cases
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j].setIcon(images.get(this.modele.getValeur(i, j)));
			}
		}

		// Mettre à jour le joueur
		joueur.setIcon(images.get(modele.getJoueur()));
	}

	/** Déclenche la demande de nouvelle partie. */
	public void actionNouvellePartie(ActionEvent ev) {
		this.recommencer();
	}

	/** Déclenche l'action de quitter. */
	public void actionQuitter(ActionEvent ev) {
		this.fenetre.dispose();
	}

	/** Déclenche l'action au click de la souris sur la case aux coordonnées [i][j]. */
	private void actionCaseClicked(int i, int j) {
		if(!modele.estGagnee() && !modele.estTerminee()) {
			try {
				// Si l'image de la case est vide
				if (this.cases[i][j].getIcon().toString().equals("src/blanc.jpg")) {
					// Modifier l'image par celle du joueur en cours
					this.cases[i][j].setIcon(images.get(modele.getJoueur()));
				}
				this.modele.cocher(i, j);

				if (modele.estGagnee())			// Si la partie est gagnée
					JOptionPane.showMessageDialog(fenetre, this.modele.getJoueur() + " a gagné.");
				else if (modele.estTerminee())	// Si la partie est terminée
					JOptionPane.showMessageDialog(fenetre, "Match nul. La partie est terminée.");
				else
					this.joueur.setIcon(images.get(modele.getJoueur()));
			} catch (CaseOccupeeException e) {
				JOptionPane.showMessageDialog(fenetre, e.getMessage());
			}
		}
	}

	// La méthode principale
	// ---------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MorpionSwing();
			}
		});
	}

}