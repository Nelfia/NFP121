import java.util.*;
import static java.util.AbstractMap.SimpleImmutableEntry;

/** Réaliser un traitement sur les données d'une source. */
public class Analyseur {
	private final Traitement TRAITEMENT;

	public Analyseur(Traitement traitement) {
		Objects.requireNonNull(traitement);

		this.TRAITEMENT = traitement;
	}

	/** Charger l'analyseur avec les données de la source. */
	public void traiter(Iterable<SimpleImmutableEntry<Position, Double>> source, String nom) {
		TRAITEMENT.gererDebutLot(nom);
		for (SimpleImmutableEntry<Position, Double> info : source) {
			Double valeur = info.getValue();
			Position p = info.getKey();
			TRAITEMENT.traiter(p, valeur);
		}
		TRAITEMENT.gererFinLot(nom);
	}

}
