import java.util.Objects;

public class EnsembleChaine<T> implements Ensemble<T>{

    private final Cellule<T> premiereCellule;
    private Cellule<T> derniereCelluleInseree;
    private int size = 0;

    public EnsembleChaine() {
        this.premiereCellule = new Cellule<>(null);
    }

    @Override
    public String toString() {
        StringBuilder listeStr = new StringBuilder();
        Cellule<T> celluleEnCours = this.premiereCellule.getCelluleSuivante();

        for(int i = 0; i < size; i++){
            listeStr.append(celluleEnCours.getValeur());
            if(celluleEnCours.getCelluleSuivante() != null) listeStr.append(", ");
            celluleEnCours = celluleEnCours.getCelluleSuivante();
        }
        return "EnsembleChaine contenant " + size +
                " éléments : [" +
                listeStr +
                "]";
    }

    @Override
    public int cardinal() {
        return this.size;
    }

    @Override
    public boolean estVide() {
        return this.size == 0;
    }

    /**
     * Vérifie la présence d'une cellule contenant la valeur passée en paramètre
     * @param x valeur de l'élément recherché
     * @return TRUE si une cellule contient la valeur, sinon FALSE.
     */
    @Override
    public boolean contient(T x) {
        return this.getCellule(x) != null;
    }

    /**
     * Ajoute une cellule à la fin de l'ensemble
     * @param x valeur de la cellule
     */
    @Override
    public void ajouter(T x) {
        if(this.contient(x)) return;
        Cellule<T> cellule = new Cellule<>(x);
        if(this.premiereCellule.getCelluleSuivante() == null) this.premiereCellule.setCelluleSuivante(cellule);
        else this.derniereCelluleInseree.setCelluleSuivante(cellule);
        this.derniereCelluleInseree = cellule;
        size++;
    }

    /**
     * Supprime toutes les Cellules dont la valeur est égale à celle passée en paramètre
     * @param x l'élément à supprimer
     */
    @Override
    public void supprimer(T x) {
        if(this.contient(x)) {
            Cellule<T> celluleprecedente = this.premiereCellule;
            Cellule<T> celluleEnCours = this.premiereCellule.getCelluleSuivante();
            while (true) {
                if(Objects.equals(celluleEnCours.getValeur(), x)) {
                    if(celluleEnCours.getCelluleSuivante() != null) celluleprecedente.setCelluleSuivante(celluleEnCours.getCelluleSuivante());
                    else celluleprecedente.setCelluleSuivante(null);
                    this.size--;
                }
                if( celluleEnCours.getCelluleSuivante() == null) break;
                celluleprecedente = celluleEnCours;
                celluleEnCours = celluleEnCours.getCelluleSuivante();
            }
        }
    }

    /**
     * Récupère la première cellule de l'ensemble dont la valeur correspond à celle recherchée.
     * @param x valeur cherchée
     */
    public Cellule<T> getCellule(T x) {
        Cellule<T> celluleEnCours = this.premiereCellule;
        while (true) {
            if(celluleEnCours.getCelluleSuivante() == null) return null;
            celluleEnCours = celluleEnCours.getCelluleSuivante();
            if (Objects.equals(celluleEnCours.getValeur(), x)) return celluleEnCours;
            if (celluleEnCours.getCelluleSuivante() == null) return null;
        }
    }
}
