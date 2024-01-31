/** Définition d'un ensemble ordonné d'entiers. */
public interface EnsembleOrdonne extends Ensemble {

	/** Obtenir la plus petite valeur dans l'ensemble.
	 * @return la valeur la plus petite  */
	/*@ pure helper @*/ int min();

}
