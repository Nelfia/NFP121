import java.util.Map;

/**
  * Normaliseur normalise les données d'un lot en utilisant une transformation affine.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Normaliseur extends Traitement {

    private final Donnees donnees;
    private final Max max;
    private final Max min;
    private final double debut;
    private final double fin;

    public Normaliseur(double debut, double fin){
        this.debut = debut;
        this.fin = fin;
        this.min = new Max();
        this.max = new Max();
        this.donnees = new Donnees();
    }

    @Override
    public void traiter(Position position, double valeur) {
        this.donnees.traiter(position, valeur);
        this.min.traiter(position, -valeur);
        this.max.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        double min = (-1) * this.min.getMax();
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