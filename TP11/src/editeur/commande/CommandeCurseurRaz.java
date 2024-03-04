package editeur.commande;

import editeur.Ligne;

public class CommandeCurseurRaz extends CommandeLigne {

    /** Initialiser la ligne sur laquelle travaille
     * cette commande.
     * @param l la ligne
     */
    //@ requires l != null;	// la ligne doit être définie
    public CommandeCurseurRaz(Ligne l) {
        super(l);
    }

    @Override
    public void executer() {
        ligne.raz();
    }

    @Override
    public boolean estExecutable() {
        return ligne.getCurseur() > 1;
    }
}
