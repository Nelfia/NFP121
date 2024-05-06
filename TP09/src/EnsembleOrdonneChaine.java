/**
 * Classe d'ensemble chaîné ordonné de cellules contenant des valeurs de type générique.
 * Pour assurer le bon fonctionnement de cette classe, les types qui seront utilisés DOIVENT être comparables.
 * Si les types ne sont pas nativement comparable, il FAUT implémenter la méthode compareTo et la définir dans la classe Cellule.
 * @param <T> Type des valeurs insérée dans les cellules de l'ensemble.
 */
public class EnsembleOrdonneChaine<T extends Comparable> implements EnsembleOrdonne<T> {

    private final Cellule<T> premiereCellule;
    private int size = 0;

    public EnsembleOrdonneChaine() {
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

    @Override
    public boolean contient(T x) {
        if(!this.estVide()) {
            if (this.premiereCellule.getCelluleSuivante().compareTo(x) < 0) return false;
            Cellule<T> c = this.premiereCellule.getCelluleSuivante();
            while (true) {
                if (c.getValeur() == x) return true;
                if (c.getCelluleSuivante() == null) return false;
                c = c.getCelluleSuivante();
            }
        }
        return false;
    }

    @Override
    public void ajouter(T x) {
        if(!this.contient(x)){
            Cellule<T> c = this.premiereCellule;
            T temp;
            if(this.estVide()) {
                c.setCelluleSuivante(new Cellule<>(x));
                this.size++;
                return;
            }
            c = c.getCelluleSuivante();
            while (true){
                if (c.compareTo(x) <= 0) {
                    temp = c.getValeur();
                    c.setValeur(x);
                    x = temp;
                    if (c.getCelluleSuivante() == null) {
                        c.setCelluleSuivante(new Cellule<>(x));
                        this.size++;
                        return;
                    }
                }
                if(c.getCelluleSuivante() ==null) {
                    c.setCelluleSuivante(new Cellule<>(x));
                    this.size++;
                    return;
                }
                c = c.getCelluleSuivante();
            }
        }
    }

    @Override
    public void supprimer(T x) {
        if(!this.contient(x) || this.estVide()) return;
        Cellule<T> c = this.premiereCellule;
        while (true) {
            if(c.getCelluleSuivante().getValeur() == x) {
                if(c.getCelluleSuivante().getCelluleSuivante() == null) c.setCelluleSuivante(null);
                else c.setCelluleSuivante(c.getCelluleSuivante().getCelluleSuivante());
                this.size--;
                return;
            }
            c = c.getCelluleSuivante();
        }
    }

    @Override
    public T getMin() {
        assert !this.estVide();
        return this.premiereCellule.getCelluleSuivante().getValeur();
    }

}
