/**
 * Classe de Cellules génériques utilisées dans le cadre d'Ensembles chaînés.
 * Dans le cas d'une utilisation avec un ensemble chaîné ordonné, il FAUT que les valeurs des cellules soient comparables.
 * Si le type de ces valeurs n'est pas comparable nativement, la méthode compareTo DOIT être redéfinie ici.
 * @param <T> type des valeurs insérées dans les cellules
 */
public class Cellule<T> implements Comparable<T> {
    private T valeur;
    private Cellule<T> celluleSuivante;

    public Cellule(T valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "[" +
                "valeur: " + valeur +
                ", cellule suivante: " + celluleSuivante +
                ']';
    }

    public T getValeur() {
        return this.valeur;
    }

    public void setValeur(T valeur) {
        this.valeur = valeur;
    }

    public Cellule<T> getCelluleSuivante() {
        return celluleSuivante;
    }

    public void setCelluleSuivante(Cellule<T> celluleSuivante) {
        this.celluleSuivante = celluleSuivante;
    }

    @Override
    public int compareTo(T o) {
        int compareToInteger = (int) o;
        if((Integer) this.valeur == compareToInteger) return 0;
        if((Integer) this.valeur > compareToInteger) return 1;
        return -1;
    }
}
