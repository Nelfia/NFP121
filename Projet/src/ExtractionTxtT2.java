import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Classe chargée de l'extraction des données d'une source fichier format .txt de type 2:
 *  Chaque ligne est organisée de la façon suivante: un identifiant (ignoré), une abscisse (entier), une ordonnée (entier),
 * un mot (ignoré), une valeur (réel) et, enfin, une lettre (ignorée).*/
public class ExtractionTxtT2 extends ExtractionSourceAbstraite{

    public ExtractionTxtT2(String nomDocument) {
        super(nomDocument);
    }

    @Override
    public void extraireDonnees() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.getNomFichier()))) {
            String ligne;
            while((ligne = in.readLine() ) != null) {
                String[] mots = ligne.split("\\s+");
                assert mots.length == 6;	// 6 mots sur chaque ligne
                int x = Integer.parseInt(mots[1]);
                int y = Integer.parseInt(mots[2]);
                double valeur = Double.parseDouble(mots[4]);
                this.addDonnee(x, y, valeur);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
