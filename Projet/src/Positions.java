import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.frequency;

/**
  * Positions enregistre toutes les positions, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Positions extends PositionsAbstrait {

    private final List<Position> POSITIONS;

    public Positions(){
        this.POSITIONS = new ArrayList<>();
    }

    @Override
    public Position position(int indice) {
        return POSITIONS.get(indice);
    }

    @Override
    public int nombre() {
        return POSITIONS.size();
    }

    @Override
    public int frequence(Position position) {
        return frequency(this.POSITIONS, position);
    }

    @Override
    public void traiter(Position position, double valeur) {
        this.POSITIONS.add(position);
        super.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        for(Position pos: this.POSITIONS) {
            System.out.println("\t - " + pos);
        }
        System.out.println("Fin " + this.getClass().getName());
    }
}
