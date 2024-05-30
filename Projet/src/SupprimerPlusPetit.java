/**
  * SupprimerPlusPetit supprime les valeurs plus petites qu'un seuil.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class SupprimerPlusPetit extends Traitement {

    private final double SEUIL;

    public SupprimerPlusPetit(double seuil) {
        this.SEUIL = seuil;
    }

    @Override
    public void traiter(Position position, double valeur) {
        if(valeur > SEUIL) super.traiter(position, valeur);
    }
}
