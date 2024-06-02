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

		// Autres données manuelles
		main.gererDebutLot("manuelles2");
		main.traiter(new Position(1, 1), 5.0);
		main.traiter(new Position(1, 2), 2.0);
		main.traiter(new Position(1, 1), -1.0);
		main.traiter(new Position(1, 3), 4.2);
		main.traiter(new Position(2, 5), 3.7);
		main.traiter(new Position(3, 7), 5.1);
		main.gererFinLot("manuelles");

		// Lot numéro 2
		main.gererDebutLot("test2");
		main.traiter(new Position(100, 105), 5.0);
		main.traiter(new Position(100, 102), 2.0);
		main.traiter(new Position(100, 105), -1.0);
		main.gererFinLot("test2");

		// Extraction du fichier donnees.txt
		ExtractionTxtT1 source1 = new ExtractionTxtT1("src/donnees.txt");
		source1.extraireDonnees();
		main.gererDebutLot("donnees.txt");
		for(AbstractMap.SimpleImmutableEntry<Position, Double> donnee: source1.getDonnees().getDonnees()){
			main.traiter(donnee.getKey(), donnee.getValue());
		}
		main.gererFinLot("donnees.txt");

		// Extraction du fichier donnees2-f2.txt
		ExtractionTxtT2 source2 = new ExtractionTxtT2("src/donnees2-f2.txt");
		source2.extraireDonnees();
		main.gererDebutLot("donnees2-f2.txt");
		for(AbstractMap.SimpleImmutableEntry<Position, Double> donnee: source2.getDonnees().getDonnees()){
			main.traiter(donnee.getKey(), donnee.getValue());
		}
		main.gererFinLot("donnees2-f2.txt");

		// Extraction du fichier donnees2.xml
		ExtractionXmlDtd2 source = new ExtractionXmlDtd2("src/donnees2.xml");
		source.extraireDonnees();
		main.gererDebutLot("donnees2.xml");
		for(AbstractMap.SimpleImmutableEntry<Position, Double> donnee: source.getDonnees().getDonnees()){
			main.traiter(donnee.getKey(), donnee.getValue());
		}
		main.gererFinLot("donnees2.xml");


		// Extraction du fichier donnees2-f2.txt
		ExtractionTxtT2 source3 = new ExtractionTxtT2("src/donnees-erreurs.txt");
		source2.extraireDonnees();
		main.gererDebutLot("donnees-erreurs");
		for(AbstractMap.SimpleImmutableEntry<Position, Double> donnee: source3.getDonnees().getDonnees()){
			main.traiter(donnee.getKey(), donnee.getValue());
		}
		main.gererFinLot("donnees-erreurs");

		// Construire l'analyseur
		Analyseur analyseur = new Analyseur(main);

		// Traiter les autres sources de données : "donnees.txt", etc.
	}

	public static void main(String[] args) throws FileNotFoundException {
		exemple1();
		exemple2("Somme 0 1 Positions 0 1 Normaliseur 2 double 0.0 double 100.0 0");
		exemple2("GenerateurXML 1 java.lang.String newfile.xml 0");

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
