import java.util.*;

public class EnsembleOrdonneChaine implements EnsembleOrdonne {

    private final Cellule premiereCellule;
    private Cellule derniereCelluleInseree;
    private int size = 0;

    public EnsembleOrdonneChaine() {
        this.premiereCellule = new Cellule(0);
    }

    public void ajouterTous(int... elements) {
        Arrays.sort(elements);
        for(int e: elements){
            Cellule cellule = new Cellule(e);
            if(this.premiereCellule.getCelluleSuivante() == null) {
                this.premiereCellule.setCelluleSuivante(cellule);
            }
            else {
                this.derniereCelluleInseree.setCelluleSuivante(cellule);
            }
            this.derniereCelluleInseree = cellule;
            this.size++ ;
        }
    }

    @Override
    public String toString() {
        StringBuilder listeStr = new StringBuilder();
        Cellule celluleEnCours = this.premiereCellule.getCelluleSuivante();

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
    public boolean contient(int x) {
        if(x < this.getMin()) return false;
        Cellule c = this.premiereCellule.getCelluleSuivante();
        while (true){
            if(c.getValeur() == x) return true;
            if(c.getCelluleSuivante() == null) return false;
            c = c.getCelluleSuivante();
        }
    }

    @Override
    public void ajouter(int x) {
        if(this.contient(x)) return;
        Cellule c = this.premiereCellule.getCelluleSuivante();
        int temp;
        while (true){
            if (x <= c.getValeur()) {
                temp = c.getValeur();
                c.setValeur(x);
                x = temp;
                if (c.getCelluleSuivante() == null) {
                    Cellule nc = new Cellule(x);
                    c.setCelluleSuivante(nc);
                    this.size++;
                    return;
                }
            }
            c = c.getCelluleSuivante();
        }
    }

    @Override
    public void supprimer(int x) {
        if(!this.contient(x)) return;
        Cellule c = this.premiereCellule;
        while (true) {
            if(c.getCelluleSuivante().getValeur() == x) {
                if(c.getCelluleSuivante().getCelluleSuivante() == null) c.setCelluleSuivante(null);
                c.setCelluleSuivante(c.getCelluleSuivante().getCelluleSuivante());
                this.size--;
                return;
            }
            c = c.getCelluleSuivante();
        }
    }

    @Override
    public int getMin() {
        return this.premiereCellule.getCelluleSuivante().getValeur();
    }

    public static void main(String[] args) {
        EnsembleOrdonneChaine eC = new EnsembleOrdonneChaine();
        eC.ajouterTous(10,18,15,-5, -15);
        System.out.println(eC);
        System.out.println("contient:" + eC.contient(10));
        eC.ajouter(4);
        System.out.println(eC);
        System.out.println(eC.contient(18));
        eC.supprimer(4);
        System.out.println(eC);
        System.out.println(eC.contient(4));
        System.out.println(eC.getMin());
    }
}
