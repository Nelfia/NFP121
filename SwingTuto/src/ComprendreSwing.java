import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComprendreSwing extends JPanel {

	// JPanel = container

	final private JLabel nomTxt = new JLabel("Nom : ");	// Label du champs
	final private JTextField nom = new JTextField(25);	// Input texte
	final private JButton bA = new JButton("A");			// Bouton
	final private JButton bB = new JButton("B");
	final private JButton bC = new JButton("C");
	final private JButton bQ = new JButton("Q");

	public ComprendreSwing() {
		super();

		// Insère le Layout Manager dans le container (Le Layout appliqué)
		JPanel premiereLigne = new JPanel();
		premiereLigne.setLayout(new FlowLayout());
		premiereLigne.add(bA);
		premiereLigne.add(bB);
		premiereLigne.add(bC);

		JPanel deuxiemeLigne = new JPanel();
		deuxiemeLigne.setLayout(new GridLayout(1,2));
		nomTxt.setHorizontalAlignment(SwingConstants.CENTER);
		deuxiemeLigne.add(nomTxt);
		deuxiemeLigne.add(nom);

		JPanel troisiemeLigne = new JPanel();
		troisiemeLigne.setLayout(new FlowLayout());
		troisiemeLigne.add(bQ);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(premiereLigne);
		this.add(deuxiemeLigne);
		this.add(troisiemeLigne);
		this.bQ.addActionListener(new ActionQuitter());
		// ajout d'un écouteur d'événements sur un bouton ou un menu


		ActionTrace trace = new ActionTrace();
		bA.addMouseListener(trace);		// Écouteur d'événement sur le passage de la souris
		bB.addMouseListener(trace);
		bC.addMouseListener(trace);
	}


	public class ActionQuitter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			System.out.println("Quitter");
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ComprendreSwing.this); // Trouver la fenêtre parente
			frame.dispose(); // Fermer la fenêtre parente
		} // Callback de l'eventListener
	}

	public class ActionTrace extends MouseAdapter {			// MouseAdapteur : Événements de la souris

		@Override
		public void mouseClicked(MouseEvent ev) {			// Au click sur la souris
			System.out.println("Appui sur "
					+ ((JButton) ev.getSource()).getText());
			if(ev.getSource() == bC) nom.setText("");
		}
		@Override
		public void mouseEntered(MouseEvent ev) {			// Entrée de la souris dans la zone "écoutée"
			JButton source = (JButton) ev.getSource();
			System.out.println("Entrée dans "
					+ source.getText());
		}
		@Override
		public void mouseExited(MouseEvent ev) {			// Sortie de la souris de la zone "écoutée"
			JButton source = (JButton) ev.getSource();
			System.out.println("Sortie de "
					+ source.getText());
		}
	}

	public static JFrame newJFrame(String titre) {
		JFrame fenetre = new JFrame(titre);						// Fenêtre principale application (Menu + Container)
		ComprendreSwing comprendre = new ComprendreSwing();
		fenetre.getContentPane().add(comprendre);				// getContentPane() : récupère le container add() ajoute le container dans le layout
		fenetre.pack();											// ajuste la fenêtre au contenu
		fenetre.dispose();
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Ferme toutes les fenêtres contenant le Layout
		return fenetre;
	}


	public static void exemple1() {
		JFrame frame1 = newJFrame("Tuto Swing");
		JFrame frame2 = newJFrame("Fenêtre2");
		frame2.setLocation(500, 100); 					// Position de la fenêtre
		frame1.setVisible(true);								// Rend visible
		frame2.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(ComprendreSwing::exemple1);
			// On demande au fil d'exécutin de Swing d'exécuter la méthode
			// exemple1 de la classe ComprendreSwing.
		System.out.println("Fin du programme principal !");
	}

}
