import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.frequency;

/**
  * Positions enregistre toutes les positions, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Positions extends PositionsAbstrait {

    private final List<Position> positions;

    public Positions(){
        this.positions = new ArrayList<>();
    }

    @Override
    public Position position(int indice) {
        return positions.get(indice);
    }

    @Override
    public int nombre() {
        return positions.size();
    }

    @Override
    public int frequence(Position position) {
        return frequency(this.positions, position);
    }

    @Override
    public void traiter(Position position, double valeur) {
        this.positions.add(position);
        super.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        for(Position pos: this.positions) {
            System.out.println("\t - " + pos);
        }
        System.out.println("Fin " + this.getClass().getName());
    }
}
