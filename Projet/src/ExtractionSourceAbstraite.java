/**
 * ExtractionSourceAbstraite spécifie l'extraction des données à partir d'un fichier quelconque.
 *
 * @author	Quiterie JULIEN <Prenom.Nom.auditeur@lecnam.net>
 */

abstract public class ExtractionSourceAbstraite {
    private final String nomFichier;
    private final Donnees donnees;

    public ExtractionSourceAbstraite(String nomDocument){
        this.nomFichier = nomDocument;
        this.donnees = new Donnees();
    }

    /** Obtenir le nom de la source */
    public final String getNomFichier() {
        return this.nomFichier;
    }

    /** Obtenir les données extraites de la source */
    public final Donnees getDonneesExtraites() {
        return this.donnees;
    }

    /**
     * Insère une nouvelle donnée dans Donnees à partir des donnees extraites de la source.
     * @param x l'abcisse extraite de la source
     * @param y l'ordonnee extraite de la source
     * @param valeur la valeur à la Position donnée
     */
    public final void addDonnee(int x, int y, double valeur) {
        Position position = new Position(x, y);
        this.donnees.traiter(position, valeur);
    }

    /** Obtenir l'abcisse (int), l'ordonnée (int) et la valeur(double). */
    public abstract void extraireDonnees();
}
