/**
  * SupprimerPlusGrand supprime les valeurs plus grandes qu'un seuil.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class SupprimerPlusGrand extends Traitement {

    private final double seuil;

    public SupprimerPlusGrand(double seuil) {
        this.seuil = seuil;
    }

    @Override
    public void traiter(Position position, double valeur) {
        if(valeur < seuil) super.traiter(position, valeur);
    }

    @Override
    protected String toStringComplement() {
        return "seuil = " + this.seuil;
    }
}
