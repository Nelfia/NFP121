package menu;

import menu.commande.CommandeNOP;
import util.Console;

import java.util.ArrayList;
import java.util.List;

/** Définition de sous-menus textuels avec les entrées non
  * sélectionnables désactivées.
  */
public class SousMenu extends Menu implements Commande {

	//@ public invariant 0 <= getNbEntrees();

	//@ private invariant titre != null;
	//@ private invariant selection != CMD_QUITTER && selection != null ==>
	//@ 	(\exists int i; i >= 0 && i <= getNbEntrees();
	//@			selection == getEntree(i).getCommande());
	//@ private invariant estQuitte() ==> selection == CMD_QUITTER;
	//@ private invariant getNbEntrees() == entrees.size();
	//@ private invariant entreeQuitter != null;

	private String titre;	// Le titre
	private List<Entree> entrees;	// Les entrées du menu
	static final private Commande CMD_QUITTER = new CommandeNOP();
	static final private Entree entreeQuitter = new Entree("Quitter", CMD_QUITTER);
	private Commande selection;	// Commande sélectionnée
	private boolean estQuitte;	// le menu a-t-il  été quitté ?

	/** Construire un menu vide (sans entrées).
	 * @param sonTitre le titre du menu
	 */
	//@ requires sonTitre != null;	// le titre existe
	//@ ensures getNbEntrees() == 0;	// le menu est vide
	//@ ensures estQuitte() == false;	// pas encore quitter !
	public SousMenu(String sonTitre) {
		super(sonTitre);
		this.entrees = new ArrayList<Entree>();
		this.titre = sonTitre;
		this.selection = null;
		this.estQuitte = false;
	}

	@Override
	public void executer() {
		do {
			// Afficher la ligne
			System.out.println();

			System.out.println();

			this.afficher();
			this.selectionner();
			this.valider();
		} while (!estQuitte());
	}

	@Override
	public boolean estExecutable() {
		return true;
	}
}
