import java.util.Map;

/**
  * Normaliseur normalise les données d'un lot en utilisant une transformation affine.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Normaliseur extends Traitement {

    private Donnees donnees;
    private Max max;
    private Max min;
    private Multiplicateur oppose;
    private final double debut;
    private final double fin;

    public Normaliseur(double debut, double fin){
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    protected void gererDebutLotLocal(String nomLot) {
        this.donnees = new Donnees();
        this.min = new Max();
        this.max = new Max();
        this.oppose = new Multiplicateur(-1);
        this.oppose.ajouterSuivants(this.min);
    }

    @Override
    public void traiter(Position position, double valeur) {
        this.donnees.traiter(position, valeur);
        this.max.traiter(position, valeur);
        this.oppose.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        double min = this.min.getMax() == 0 ? this.min.getMax() : (-1) * this.min.getMax();
        double max = this.max.getMax();
        double a = (this.fin - this.debut)/(max - min);
        double b = this.fin - a * max;
        for(Map.Entry<Position, Double> donnee : this.donnees.getDonnees()){
            double valeurNormalisee = Math.round(a * donnee.getValue() + b);
            System.out.println("\t - " + donnee.getKey() + ", " + donnee.getValue() + " -> " +  valeurNormalisee);
            super.traiter(donnee.getKey(), valeurNormalisee);
        }
        System.out.println("Fin " + this.getClass().getName());
    }

    @Override
    protected String toStringComplement() {
        return "debut = " + this.debut + ", fin = " + this.fin;
    }
}