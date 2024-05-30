import java.io.FileNotFoundException;
import java.util.*;

/**
  * ExempleAnalyse 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class ExempleAnalyse {

	public static void exemple1() {
		System.out.println();
		System.out.println("=== exemple1() ===");

		FabriqueTraitement traitements = new FabriqueTraitementConcrete();

		// Construire le traitement
		SommeAbstrait somme = traitements.somme();
		PositionsAbstrait positions = traitements.positions();
		somme.ajouterSuivants(positions);

		System.out.println("Traitement : " + somme);

		// Traiter des données manuelles
		somme.gererDebutLot("manuelles");
		somme.traiter(new Position(1, 1), 5.0);
		somme.traiter(new Position(1, 2), 2.0);
		somme.traiter(new Position(1, 1), -1.0);
		somme.traiter(new Position(1, 2), 1.5);
		somme.gererFinLot("manuelles");

		// Exploiter les résultats
		System.out.println("Somme = " + somme.somme());
		System.out.println("Positions.frequence(new Position(1,2)) = " + positions.frequence(new Position(1, 2)));
	}

	public static void exemple2(String traitements) throws FileNotFoundException {
		System.out.println();
		System.out.println("=== exemple2(" + traitements + ") ===");

		// Construire les traitements
		TraitementBuilder builder = new TraitementBuilder();
		Traitement main = builder.traitement(new Scanner(traitements), null);

		System.out.println("Traitement : " + main);

		// Traiter des données manuelles
		main.gererDebutLot("manuelles");
		main.traiter(new Position(1, 1), 5.0);
		main.traiter(new Position(1, 2), 2.0);
		main.traiter(new Position(1, 1), -1.0);
		main.gererFinLot("manuelles");

		// Construire l'analyseur
		Analyseur analyseur = new Analyseur(main);

		// Traiter les autres sources de données : "donnees.txt", etc.
	}

	public static void main(String[] args) throws FileNotFoundException {
		exemple1();
		exemple2("Somme 0 1 Positions 0 1 Normaliseur 2 double 0.0 double 100.0 0");

		String calculs = "Positions 0 1 Max 0 1 Somme 0 1 SommeParPosition 0";
		String generateur = "GenerateurXML 1 java.lang.String NOM--genere.xml";
		String traitement1 = generateur.replaceAll("NOM", "brut") + " 3"
			+ " " + calculs + " 0"
			+ " " + "SupprimerPlusPetit 1 double 0.0 1 SupprimerPlusGrand 1 double 10.0 2"
				+ " " + generateur.replaceAll("NOM", "valides") + " 0"
				+ " " + calculs + " 0"
			+ " " + "Normaliseur 2 double 0.0 double 100.0 2"
				+ " " + generateur.replaceAll("NOM", "normalisees") + " 0"
				+ " " + calculs + " 0";

		exemple2(calculs + " 0");
		exemple2(traitement1);
	}

}
