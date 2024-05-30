import java.util.*;

/**
  * Donnees enregistre toutes les données reçues, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Donnees extends Traitement {

    private final List<AbstractMap.SimpleImmutableEntry<Position, Double>> DONNEES;

    public Donnees() {
        this.DONNEES = new ArrayList<>();
    }

    public List<AbstractMap.SimpleImmutableEntry<Position, Double>> getDonnees() {
        return DONNEES;
    }

    @Override
    public void traiter(Position position, double valeur) {
        AbstractMap.SimpleImmutableEntry<Position, Double> donnee = new AbstractMap.SimpleImmutableEntry<>(position, valeur);
        DONNEES.add(donnee);
        super.traiter(position, valeur);
    }
}
