/**
 * Moyenne calcule la moyenne des valeurs reçues.
 */
public class Moyenne extends MoyenneAbstrait {
    private final Somme total;
    private int nbValeurs;

    public Moyenne() {
        this.total = new Somme();
        this.nbValeurs = 0;
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
