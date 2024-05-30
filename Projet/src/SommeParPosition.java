import java.util.HashMap;
import java.util.Map;

/**
  * SommeParPosition 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class SommeParPosition extends Traitement {

	private Map<Position, Double> positions;

    public SommeParPosition() {
        positions = new HashMap<>();
    }

    @Override
    public void traiter(Position position, double valeur) {
        if(positions.containsKey(position)) positions.put(position, positions.get(position) + valeur);
        else positions.put(position, valeur);
        super.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        for (Position key : positions.keySet()) {
            System.out.println("\t - " + key + " -> " + positions.get(key));
        }
        System.out.println("Fin " + this.getClass().getName());
    }
}
