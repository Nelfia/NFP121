/** Définition d'un ensemble ordonné d'entiers. */
public interface EnsembleOrdonne<T extends Comparable> extends Ensemble<T> {

	/** Obtenir la plus petite valeur dans l'ensemble.
	 * @return la valeur la plus petite  */
	/*@ pure helper @*/ T getMin();
}
