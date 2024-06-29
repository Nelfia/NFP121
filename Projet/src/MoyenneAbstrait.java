import java.math.BigDecimal;
import java.math.RoundingMode;

/**
  * Moyenne Abstrait
  *
  * @author	Quiterie JULIEN <Prenom.Nom.auditeur@lecnam.net>
  */

abstract public class MoyenneAbstrait extends Traitement {
	
	public abstract double moyenne();

	/** Moyenne arrondie à 2 chiffres après la virgule */
	protected final double moyenneArrondie2chiffresApresVirgule(double valeur) {
		return new BigDecimal(valeur).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
