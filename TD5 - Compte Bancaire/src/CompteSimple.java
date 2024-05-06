public class CompteSimple {
    private int solde;

    public CompteSimple(int montant){
        this.solde = montant;
    }

    /**
     * Ajoute le montant passé en paramètre
     * @param montant
     */
    public void crediter(int montant){
        this.solde += montant;
    }


    public void debiter(int montant){
        this.solde -= montant;
    }
}