import java.io.*;

/** Analyser des données d'un fichier, une donnée par ligne avec 4 informations
 * séparées par des blancs : x, y, ordre (ignorée), valeur.
 */
public class AnalyseurInitial {
	/** Charger l'analyseur avec les données du fichier "donnees.java". */
	public void traiter() {
		try (BufferedReader in = new BufferedReader(new FileReader("./src/donnees.txt"))) {
			double somme = 0.0;
			String ligne;
			while ((ligne = in.readLine()) != null) {
				String[] mots = ligne.split("\\s+");
				assert mots.length == 4;	// 4 mots sur chaque ligne
				double valeur = Double.parseDouble(mots[3]);
				int x = Integer.parseInt(mots[0]);
				int y = Integer.parseInt(mots[1]);
				Position p = new Position(x, y);
				somme += valeur;
			}
			System.out.println(somme);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		new AnalyseurInitial().traiter();
	}

}
