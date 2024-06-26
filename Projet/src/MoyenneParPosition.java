import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * MoyenneParPosition calcule la moyenne des valeurs par position.
 * @author	Quiterie JULIEN <Prenom.Nom.auditeur@lecnam.net>
 */
public class MoyenneParPosition extends Traitement {
    private final Map<Position, Double> moyenneParPosition;
    private final SommeParPosition sommeParPosition;
    private final Positions positions;

    public MoyenneParPosition() {
        this.moyenneParPosition = new HashMap<>();
        this.sommeParPosition = new SommeParPosition();
        this.positions = new Positions();
    }

    public Map<Position, Double> getMoyenneParPosition() {
        return this.moyenneParPosition;
    }

    @Override
    public void traiter(Position position, double valeur) {
        this.sommeParPosition.traiter(position, valeur);
        this.positions.traiter(position, valeur);
        super.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        System.out.println(this.getClass().getName() + " " + nomLot + " :");
        for (Position key : this.sommeParPosition.getSommeParPositions().keySet()) {
            double somme = this.sommeParPosition.getSommeParPositions().get(key);
            int frequence = this.positions.frequence(key);
            double moyenne = moyenneArrondie2chiffresApresVirgule(somme / frequence);
            this.moyenneParPosition.put(key, moyenne);
            System.out.println("\t - " + key + " -> " + this.moyenneParPosition.get(key));
        }
    }

    private double moyenneArrondie2chiffresApresVirgule(double valeur) {
        return new BigDecimal(valeur).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
