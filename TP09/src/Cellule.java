public class Cellule {
    private int valeur;
    private Cellule celluleSuivante;

    public Cellule(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "[" +
                "valeur: " + valeur +
                ", cellule suivante: " + celluleSuivante +
                ']';
    }

    public int getValeur() {
        return this.valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Cellule getCelluleSuivante() {
        return celluleSuivante;
    }

    public void setCelluleSuivante(Cellule celluleSuivante) {
        this.celluleSuivante = celluleSuivante;
    }
}
