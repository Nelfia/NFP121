import java.util.HashMap;
import java.util.Map;

/**
  * SommeParPosition 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class SommeParPosition extends Traitement {

	private final Map<Position, Double> sommeParPositions;

    public SommeParPosition() {
        sommeParPositions = new HashMap<>();
    }

    public Map<Position, Double> getSommeParPositions() {
        return sommeParPositions;
    }

    @Override
    public void traiter(Position position, double valeur) {
        if(sommeParPositions.containsKey(position)) {
            sommeParPositions.put(position, sommeParPositions.get(position) + valeur);
        } else {
            sommeParPositions.put(position, valeur);
        }
        super.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        for (Map.Entry<Position, Double> entry : sommeParPositions.entrySet()) {
            System.out.println("\t - " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("Fin " + this.getClass().getName());
    }
}
