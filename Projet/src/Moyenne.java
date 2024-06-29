/**
 * Moyenne calcule la moyenne de toutes les valeurs lues par lot.
 */
public class Moyenne extends MoyenneAbstrait {
    private Somme total;
    private int nbValeurs;

    public Moyenne() {}

    @Override
    protected void gererDebutLotLocal(String nomLot) {
        this.total = new Somme();
        this.nbValeurs = 0;
        System.out.println("Début Moyenne sur les données " + nomLot);
    }

    @Override
    public void traiter(Position position, double valeur) {
        this.total.traiter(position, valeur);
        this.nbValeurs++;
        super.traiter(position, valeur);
    }

    @Override
    public double moyenne() {
        return moyenneArrondie2chiffresApresVirgule(this.total.somme() / this.nbValeurs);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(nomLot + ": moyenne = " + this.moyenne());
    }
}
