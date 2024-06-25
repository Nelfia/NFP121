import java.util.Objects;

import static java.util.AbstractMap.SimpleImmutableEntry;

/** Réaliser un traitement sur les données d'une source. */
public class Analyseur {
	private final Traitement traitement;

	public Analyseur(Traitement traitement) {
		Objects.requireNonNull(traitement);

		this.traitement = traitement;
	}

	/** Charger l'analyseur avec les données de la source. */
	public void traiter(Iterable<SimpleImmutableEntry<Position, Double>> source, String nom) {
		traitement.gererDebutLot(nom);
		for (SimpleImmutableEntry<Position, Double> info : source) {
			Position p = info.getKey();
			Double valeur = info.getValue();
			traitement.traiter(p, valeur);
		}
		traitement.gererFinLot(nom);
	}
}
