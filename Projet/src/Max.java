/**
  * Max calcule le max des valeurs vues, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class Max extends Traitement {

    private double max;

    public Max(){
        this.max = -Double.MAX_VALUE;
    }

    public double getMax() {
        return max;
    }

    @Override
    public void traiter(Position position, double valeur) {
        if(valeur > max) max = valeur;
        super.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(nomLot + " : max = " + max);
    }
}
