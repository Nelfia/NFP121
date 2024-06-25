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

	/** Ajouter des traitements à la suite de celui-ci.
	 * @param suivants les traitements à ajouter
	 */
	final public Traitement ajouterSuivants(Traitement... suivants) {
		for(Traitement suivant : suivants) {
			if (isCycle(suivant))
				throw new CycleException(suivant.getClass().getName());
		}
		Collections.addAll(this.suivants, suivants);
		return this;
	}

	@Override
	public String toString() {
		return this.toString("");
	}

	/** Afficher ce traitement et les suivants sous forme d'un arbre horizontal.
	 * @param prefixe le préfixe à afficher après un retour à la ligne
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

	/** Décrire les paramètres du traitement. */
	protected String toStringComplement() {
		return null;
	}

	public void traiter(Position position, double valeur) {
		for (Traitement suivant : this.suivants) {
			suivant.traiter(position, valeur);
		}
	}

	final public void gererDebutLot(String nomLot) {
		Objects.requireNonNull(nomLot, "nomLot doit être défini.");

		this.gererDebutLotLocal(nomLot);
		for (Traitement suivant : this.suivants) {
			suivant.gererDebutLot(nomLot);
		}
	}

	protected void gererDebutLotLocal(String nomLot) {
	}

	final public void gererFinLot(String nomLot) {
		Objects.requireNonNull(nomLot, "nomLot doit Ãªtre défini.");

		this.gererFinLotLocal(nomLot);
		for (Traitement suivant : this.suivants) {
			suivant.gererFinLot(nomLot);
		}
	}

	protected void gererFinLotLocal(String nomLot) {
	}

	/**
	 * Vérifier s'il y a un cycle dans un traitement.
	 *
	 * @param traitement le traitement à vérifier
	 * @return true s'il y a un Cycle, false sinon
	 */
	private boolean isCycle(Traitement traitement) {
		if(this == traitement)
			return true;
		for(Traitement suivant : traitement.suivants) {
			if (suivant == this || isCycle(suivant))
				return true;
		}
		return false;
	}
}
