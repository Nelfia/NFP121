import java.util.*;

/**
  * Donnees enregistre toutes les données reçues, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Donnees extends Traitement {

    private final List<AbstractMap.SimpleImmutableEntry<Position, Double>> donnees;

    public Donnees() {
        this.donnees = new ArrayList<>();
    }

    public List<AbstractMap.SimpleImmutableEntry<Position, Double>> getDonnees() {
        return donnees;
    }

    @Override
    public void traiter(Position position, double valeur) {
        AbstractMap.SimpleImmutableEntry<Position, Double> donnee = new AbstractMap.SimpleImmutableEntry<>(position, valeur);
        donnees.add(donnee);
        super.traiter(position, valeur);
    }

    @Override
    protected void gererDebutLotLocal(String nomLot) {
        super.gererDebutLotLocal(nomLot);
    }
}
