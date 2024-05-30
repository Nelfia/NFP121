/**
  * PositionsAbstrait spécifie un traitement qui mémorise
  * toutes les positions traitées pour ensuite y accéder
  * par leur indice ou obtenir la fréquence d'une position.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

abstract public class PositionsAbstrait extends Traitement {

	/** Obtenir le nombre de positions mémorisées.
	 * @return le nombre de positions mémorisées
	 */
	public abstract int nombre();

	/** Obtenir la ième position enregistrée.
	 * @param indice indice de la position souhaitée (0 pour la première)
	 * @return la position de numéro d'ordre `numero`
	 * @exception IndexOutOfBoundsException si le numero est incorrect
	 */
	public abstract Position position(int indice);

	/** Obtenir la fréquence d'une position dans les positions traitées.
	 * @param position la position dont on veut connaÃ®tre la fréquence
	 * @return la fréquence de la position en paramètre
	 */
	public abstract int frequence(Position position);

}
