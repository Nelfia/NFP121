public class EnsembleChaine implements Ensemble{

    private final Cellule premiereCellule;
    private Cellule derniereCelluleInseree;
    private int size = 0;

    public EnsembleChaine() {
        this.premiereCellule = new Cellule(0);
    }

    public void ajouterTous(int... nombres) {
        for(int n: nombres){
            Cellule cellule = new Cellule(n);
            if(this.premiereCellule.getCelluleSuivante() == null) {
                this.premiereCellule.setCelluleSuivante(cellule);
            }
            else {
                this.derniereCelluleInseree.setCelluleSuivante(cellule);
            }
            this.derniereCelluleInseree = cellule;
            this.size++ ;
        }
        System.out.println(this);

    }

    @Override
    public String toString() {
        StringBuilder listeStr = new StringBuilder();
        Cellule celluleEnCours = this.premiereCellule.getCelluleSuivante();
        System.out.println(celluleEnCours);

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
    public boolean contient(int x) {
        return this.getCellule(x) != null;
    }

    /**
     * Ajoute une cellule à la fin de l'ensemble
     * @param x valeur de la cellule
     */
    @Override
    public void ajouter(int x) {
        if(this.contient(x)) return;
        Cellule cellule = new Cellule(x);
        if(this.premiereCellule.getCelluleSuivante() == null) this.premiereCellule.setCelluleSuivante(cellule);
        else this.derniereCelluleInseree.setCelluleSuivante(cellule);
        this.derniereCelluleInseree = cellule;
        size++;
        System.out.println("ajouter" + this);
    }

    /**
     * Supprime toutes les Cellules dont la valeur est égale à celle passée en paramètre
     * @param x l'élément à supprimer
     */
    @Override
    public void supprimer(int x) {
        if(this.contient(x)) {
            Cellule celluleprecedente = this.premiereCellule;
            Cellule celluleEnCours = this.premiereCellule.getCelluleSuivante();
            while (true) {
                if( celluleEnCours.getValeur() == x) {
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
    public Cellule getCellule(int x) {
        Cellule celluleEnCours = this.premiereCellule;
        while (true) {
            if(celluleEnCours.getCelluleSuivante() == null) return null;
            celluleEnCours = celluleEnCours.getCelluleSuivante();
            if (celluleEnCours.getValeur() == x) return celluleEnCours;
            if (celluleEnCours.getCelluleSuivante() == null) return null;
        }
    }

    public static void main(String[] args) {
        EnsembleChaine eC = new EnsembleChaine();
        eC.ajouterTous(10,18,15,-5);
        System.out.println("contient:" + eC.contient(10));
        System.out.println(eC.getCellule(-5));
        eC.supprimer(10);
        System.out.println("contient:" + eC.contient(10));
        System.out.println("contient:" + eC.contient(-5));
        System.out.println("contient:" + eC.contient(15));
        System.out.println(eC);
        eC.supprimer(-5);
        System.out.println(eC);
        System.out.println("contient:" + eC.contient(10));
        System.out.println("contient:" + eC.contient(-5));
        System.out.println("contient:" + eC.contient(15));

    }
}
