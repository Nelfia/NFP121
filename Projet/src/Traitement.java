import java.util.*;

/**
  * Traitement modélise un traitement et ses traitements suivants (donc
  * une chaîne de traitements).
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
abstract public class Traitement {

	/** Les traitements suivants. */
	private List<Traitement> suivants = new ArrayList<>();

	/** Ajouter des traitements Ã  la suite de celui-ci.
	 * @param suivants les traitements Ã  ajouter
	 */
	final public Traitement ajouterSuivants(Traitement... suivants) {
		Collections.addAll(this.suivants, suivants);
		return this;
	}

	@Override
	public String toString() {
		return this.toString("");
	}

	/** Afficher ce traitement et les suivants sous forme d'un arbre horizontal.
	 * @param prefixe le prÃ©fixe Ã  afficher après un retour Ã  la ligne
	 */
	private String toString(String prefixe) {
		String res =  this.getClass().getName();
		String complement = this.toStringComplement();
		if (complement != null && !complement.isEmpty()) {
			res += "(" + complement + ")";
		}
		if (this.suivants.size() <= 1) {
			for (Traitement s : this.suivants) {
				res += " --> " + s.toString(prefixe);
			}
		} else {
			for (Traitement s : this.suivants) {
				res += "\n" + prefixe + "\t" + "--> " + s.toString(prefixe + "\t");
			}
		}
		return res;
	}

	/** DÃ©crire les paramètres du traitement. */
	protected String toStringComplement() {
		return null;
	}

	public void traiter(Position position, double valeur) {
		for (Traitement suivant : this.suivants) {
			suivant.traiter(position, valeur);
		}
	}

	final public void gererDebutLot(String nomLot) {
		Objects.requireNonNull(nomLot, "nomLot doit Ãªtre dÃ©fini.");

		this.gererDebutLotLocal(nomLot);
		for (Traitement suivant : this.suivants) {
			suivant.gererDebutLot(nomLot);
		}
	}

	protected void gererDebutLotLocal(String nomLot) {
	}

	final public void gererFinLot(String nomLot) {
		Objects.requireNonNull(nomLot, "nomLot doit Ãªtre dÃ©fini.");

		this.gererFinLotLocal(nomLot);
		for (Traitement suivant : this.suivants) {
			suivant.gererFinLot(nomLot);
		}
	}

	protected void gererFinLotLocal(String nomLot) {
	}

}
