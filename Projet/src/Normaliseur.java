import java.util.Map;

/**
  * Normaliseur normalise les données d'un lot en utilisant une transformation affine.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Normaliseur extends Traitement {

    private final Donnees DONNEES;
    private final Max MAX;
    private final Max MIN;
    private final double DEBUT;
    private final double FIN;

    public Normaliseur(double debut, double fin){
        this.DEBUT = debut;
        this.FIN = fin;
        this.MIN = new Max();
        this.MAX = new Max();
        this.DONNEES = new Donnees();
    }

    @Override
    public void traiter(Position position, double valeur) {
        this.DONNEES.traiter(position, valeur);
        this.MIN.traiter(position, -valeur);
        this.MAX.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        double min = (-1) * this.MIN.getMax();
        double max = this.MAX.getMax();
        double a = (this.FIN - this.DEBUT)/(max - min);
        double b = this.FIN - a * max;
        for(Map.Entry<Position, Double> donnee : this.DONNEES.getDonnees()){
            double valeurNormalisee = Math.round(a * donnee.getValue() + b);
            System.out.println("\t - " + donnee.getKey() + ", " + donnee.getValue() + " -> " +  valeurNormalisee);
            super.traiter(donnee.getKey(), valeurNormalisee);
        }
        System.out.println("Fin " + this.getClass().getName());
    }

}