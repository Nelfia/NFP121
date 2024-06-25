import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
  * ExempleAnalyse 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class ExempleAnalyse {
	private static final Map<String, Traitement> stockageTraitements = new HashMap<>();

	/** Récupère tous les traitements réalisés. */
	public static Map<String, Traitement> getStockageTraitements() {
		return stockageTraitements;
	}

	/**
	 * Récupère un Traitement réalisé à l'aide de son id.
	 * @param id du traitement recherché
	 * @return le traitement réalisé
	 */
	public static Traitement getTraitementById(String id) {
		return stockageTraitements.get(id);
	}


	public static void exemple1() {
		System.out.println();
		System.out.println("=== exemple1() ===");

		FabriqueTraitement traitements = new FabriqueTraitementConcrete();

		// Construire le traitement
		SommeAbstrait somme = traitements.somme();
		PositionsAbstrait positions = traitements.positions();
		Max max = traitements.max();
		Multiplicateur multiplicateur = traitements.multiplicateur(2.0);
		MoyenneParPosition moyenneParPosition = traitements.moyenneParPosition();
		Donnees donnees = traitements.donnees();
		somme.ajouterSuivants(positions);
		positions.ajouterSuivants(max);
		max.ajouterSuivants(donnees);
		donnees.ajouterSuivants(multiplicateur);
		multiplicateur.ajouterSuivants(moyenneParPosition);
		moyenneParPosition.ajouterSuivants(somme);


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

	public static void exemple2(String traitements) {
		System.out.println();
		System.out.println("=== exemple2(" + traitements + ") ===");
		// Construire les traitements
		TraitementBuilder builder = new TraitementBuilder();
		Traitement main = builder.traitement(new Scanner(traitements), stockageTraitements);

		System.out.println("Traitement : " + main);

		// Traiter des données manuelles
		main.gererDebutLot("manuelles");
		main.traiter(new Position(1, 1), 5.0);
		main.traiter(new Position(1, 2), 2.0);
		main.traiter(new Position(1, 2), 8.0);
		main.traiter(new Position(1, 2), 20.0);
		main.traiter(new Position(1, 1), -1.0);
		main.gererFinLot("manuelles");

		System.out.println("ANALYSEUR");
		// Construire l'analyseur
		Analyseur analyseur = new Analyseur(main);

		// Traiter les autres sources de données : "donnees.txt", etc.

		// Extraction du fichier donnees.txt
		ExtractionTxtT1 source = new ExtractionTxtT1("src/donnees.txt");
		source.extraireDonnees();
		analyseur.traiter(source.getDonneesExtraites().getDonnees(), "donnees.txt");

		// Extraction du fichier donnees2-f2.txt
		ExtractionTxtT2 source2 = new ExtractionTxtT2("src/donnees2-f2.txt");
		source2.extraireDonnees();
		analyseur.traiter(source2.getDonneesExtraites().getDonnees(), "donnees2-f2.txt");

		// Extraction du fichier donnees2.xml
		ExtractionXmlDtd2 source3 = new ExtractionXmlDtd2("src/donnees2.xml");
		source3.extraireDonnees();
		analyseur.traiter(source3.getDonneesExtraites().getDonnees(), "donnees2.xml");

		// Extraction du fichier donnees-erreurs.txt
		ExtractionTxtT2 source4 = new ExtractionTxtT2("src/donnees-erreurs.txt");
		source4.extraireDonnees();
		analyseur.traiter(source4.getDonneesExtraites().getDonnees(), "donnees-erreurs.txt");

		// Extraction des données du nouveau fichier
		ExtractionTxtT1 source5 = new ExtractionTxtT1("src/fichiersGeneres/donneesGenereesParSwing.txt");
		source5.extraireDonnees();
		analyseur.traiter(source5.getDonneesExtraites().getDonnees(), "donneesGenereesParSwing.txt");
	}

	public static void main(String[] args) {
//		exemple1();
//		exemple2("Somme 0 1 Positions 0 1 MoyenneParPosition 0 1 Normaliseur 2 double 0.0 double 100.0 0");
//		exemple2("GenerateurXML 1 java.lang.String newfile.xml 0");

		String calculs = "id X-p Positions 0 1 id X-m Max 0 1 id X-s Somme 0 1 id X-M Moyenne 0 1 id X-S SommeParPosition 0 1 id X-mp MoyenneParPosition 0";
		String generateur = "id X-g GenerateurXML 1 java.lang.String NOM--genere.xml";
		String traitement1 = generateur.replaceAll("NOM", "brut").replaceAll("X-", "1") + " 3"
			+ " " + calculs.replaceAll("X-", "brut") + " 0"
			+ " " + "id X-sp SupprimerPlusPetit 1 double 0.0 1 id X-sg SupprimerPlusGrand 1 double 10.0 2".replaceAll("X-", "brut")
				+ " " + generateur.replaceAll("NOM", "valides").replaceAll("X-", "valides") + " 0"
				+ " " + calculs.replaceAll("X-", "valides") + " 0"
			+ " " + "id X-N Normaliseur 2 double 0.0 double 100.0 2".replaceAll("X-", "valides")
				+ " " + generateur.replaceAll("NOM", "normalisees").replaceAll("X-", "3") + " 0"
				+ " " + calculs.replaceAll("X-", "normalisees") + " 0";

//		exemple2(calculs + " 0");
		exemple2(traitement1);

		System.out.println("=== Stockage des traitements ===");
		System.out.println(getStockageTraitements());
		System.out.println(((MoyenneParPosition) getTraitementById("brutmp")).getMoyenneParPosition());
		System.out.println("=== FIN ===");
	}

}
