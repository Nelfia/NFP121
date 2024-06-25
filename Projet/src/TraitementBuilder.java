import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * TraitementBuilder
 *
 * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
 */
public class TraitementBuilder {

	/** Retourne un objet de type Class correspondant au nom en paramètre.
	 * Exemples :
	 *   - int donne int.class
	 *   - Normaliseur donne Normaliseur.class
	 */
	Class<?> analyserType(@NotNull String nomType) throws ClassNotFoundException {
		return switch (nomType) {
			case "int" -> int.class;
			case "double" -> double.class;
			case "boolean" -> boolean.class;
			default -> Class.forName(nomType);
		};
	}

	/** Crée l'objet java qui correspond au type formel en exploitant le « mot » suivant du scanner.
	 * Exemple : si formel est int.class, le mot suivant doit être un entier et le résulat est l'entier correspondant.
	 * Ici, on peut se limiter aux types utlisés dans le projet : int, double et String.
	 */
	static Object decoderEffectif(Class<?> formel, Scanner in) {
		if (formel == int.class) return Integer.parseInt(in.next());
		else if (formel == double.class) return Double.parseDouble(in.next());
		else if (formel == String.class) return in.next();
		return null;
	}

	/** Définition de la signature, les paramètres formels, mais aussi les paramètres effectifs.  */
	static class Signature {
		Class<?>[] formels;
		Object[] effectifs;

		public Signature(Class<?>[] formels, Object[] effectifs) {
			this.formels = formels;
			this.effectifs = effectifs;
		}
	}

	/** Analyser une signature pour retrouver les paramètres formels et les paramètres effectifs.
	 * Exemple « 3 double 0.0 java.lang.String xyz int -5 » donne
	 *   - [double.class, String.class, int.class] pour les paramètres effectifs et
	 *   - [0.0, "xyz", -5] pour les paramètres formels.
	 */
	Signature analyserSignature(Scanner in) throws ClassNotFoundException {
		int nbParams = in.nextInt();
		Class<?>[] formels = new Class[nbParams];
		Object[] effectifs = new Object[nbParams];
		for(int i = 0; i < nbParams; i++){
			formels[i] = analyserType(in.next());
			effectifs[i] = decoderEffectif(formels[i], in);
		}
		return new Signature(formels, effectifs);
	}

	/** Analyser la création d'un objet.
	 * Exemple : « Normaliseur 2 double 0.0 double 100.0 » consiste à charger
	 * la classe Normaliseur, trouver le constructeur qui prend 2 double, et
	 * l'appeler en lui fournissant 0.0 et 100.0 comme paramètres effectifs.
	 */
	Object analyserCreation(Scanner in)
			throws ClassNotFoundException, InvocationTargetException,
			IllegalAccessException, NoSuchMethodException,
			InstantiationException
	{
		String nomClasse = in.next();	// Récupérer la 1ère String qui doit correspondre au nom de la classe
		Class<?> classe = analyserType(nomClasse);	// Récupérer la classe correspondante
		Signature signature = analyserSignature(in);	// Récupère les types et les valeurs du Scanner
		Constructor<?> constructeur = classe.getConstructor(signature.formels);	// Récupère le bon constructeur en fonction des paramètres formels
		return constructeur.newInstance(signature.effectifs);	// Instancie la poignée à partir des effectifs
	}

	/** Analyser un traitement.
	 * Exemples :
	 *   - « Somme 0 0 »
	 *   - « SupprimerPlusGrand 1 double 99.99 0 »
	 *   - « Somme 0 1 Max 0 0 »
	 *   - « Somme 0 2 Max 0 0 SupprimerPlusGrand 1 double 20.0 0 »
	 *   - « Somme 0 2 Max 0 0 SupprimerPlusGrand 1 double 20.0 1 Positions 0 0 »
	 * @param in le scanner à utiliser
	 * @param env l'environnement oÃ¹ enregistrer les nouveaux traitements
	 */
	Traitement analyserTraitement(Scanner in, Map<String, Traitement> env)
			throws ClassNotFoundException, InvocationTargetException,
			IllegalAccessException, NoSuchMethodException,
			InstantiationException
	{
		String id = null;
		if(in.hasNext("id")) {
			in.next();
			id = in.next();
		}

		Traitement traitement = (Traitement) analyserCreation(in);
		if(id != null)
			env.put(id, traitement);
		int nbTraitements = in.nextInt(); // Nb traitements suivants

		for(int i = 0; i < nbTraitements; i++) {
			Traitement t = analyserTraitement(in, env);
			traitement.ajouterSuivants(t); // TODO: finir cette méthode
		}
		return traitement;
	}

	/** Analyser un traitement.
	 * @param in le scanner à utiliser
	 * @param env l'environnement oÃ¹ enregistrer les nouveaux traitements
	 */
	public Traitement traitement(Scanner in, Map<String, Traitement> env)
	{
		try {
			return analyserTraitement(in, env);
		} catch (Exception e) {
			throw new RuntimeException("Erreur sur l'analyse du traitement, "
					+ "voir la cause ci-dessous", e);
		}
	}

}
