import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Classe chargée de l'extraction des données d'une source fichier format .txt de type 1:
 *  Chaque ligne est organisée de la façon suivante: une abscisse (entier), une ordonnée (entier), un numéro d’ordre
 *  (ignoré) et une valeur réelle (double).*/
public class ExtractionTxtT1 extends ExtractionTxt{

    public ExtractionTxtT1(String nomFichier) {
         super(nomFichier, "T1");
     }

    @Override
    public void extraireDonnees() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.getNomFichier()))) {
            String ligne;
            while((ligne = in.readLine() ) != null) {
                String[] mots = ligne.split("\\s+");
                assert mots.length == 4;	// 4 mots sur chaque ligne
                int x = Integer.parseInt(mots[0]);
                int y = Integer.parseInt(mots[1]);
                double valeur = Double.parseDouble(mots[3]);
                this.setDonnees(x, y, valeur);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
