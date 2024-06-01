/**
 * ExtractionSourceAbstraite spécifie l'extraction des éléments d'un fichier.
 *
 * @author	Quiterie JULIEN <Prenom.Nom.auditeur@lecnam.net>
 */

abstract public class ExtractionSourceAbstraite {
    private final String NOM_FICHIER;
    private final Donnees DONNEES;

    public ExtractionSourceAbstraite(String nomDocument){
        this.NOM_FICHIER = nomDocument;
        this.DONNEES = new Donnees();
    }

    /** Obtenir le nom de la source */
    public final String getNomFichier() {
        return this.NOM_FICHIER;
    }

    /** Obtenir les données extraites de la source */
    public final Donnees getDonnees() {
        return this.DONNEES;
    }

    /**
     * Insère une nouvelle donnée dans Donnees à partir des donnees extraites de la source.
     * @param x l'abcisse extraite de la source
     * @param y l'ordonnee extraite de la source
     * @param valeur la valeur à la Position donnée
     */
    public final void setDonnees(int x, int y, double valeur) {
        Position position = new Position(x, y);
        this.DONNEES.traiter(position, valeur);
    }

    /** Obtenir l'abcisse (int), l'ordonnée (int) et la valeur(double). */
    public abstract void extraireDonnees();
}
