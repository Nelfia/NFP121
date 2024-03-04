package editeur.commande;

import editeur.Ligne;

public class CommandeCaractereSupprimer
        extends CommandeLigne{

    /** Initialiser la ligne sur laquelle travaille
     * cette commande.
     * @param l la ligne
     */
    //@ requires l != null;	// la ligne doit être définie
    public CommandeCaractereSupprimer(Ligne l){
        super(l);
    }

    @Override
    public void executer() {
        ligne.supprimer();
    }

    @Override
    public boolean estExecutable() {
        return ligne.getLongueur() > 0;
    }
}
