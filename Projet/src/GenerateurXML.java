import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * GenerateurXML écrit dans un fichier, Ã  chaque fin de lot, toutes
 * les données lues en indiquant le lot dans le fichier XML.
 *
 * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
 */
public class GenerateurXML extends Traitement {
    private final String DOCUMENT_NAME;
    private final Document DOCUMENT;
    private final Element RACINE;
    private Element lot;

    public GenerateurXML(String documentName) {
        this.DOCUMENT_NAME = documentName;
        this.RACINE = new Element("donnees");
        this.DOCUMENT = new Document(this.RACINE, new DocType("donnees", "generateur.dtd"));
    }

    private Element createDonnee(Position pos, double val){
        Element donnee = new Element("donnee");
        Element position = new Element("position");
        position.setAttribute("x", String.valueOf(pos.getX()));
        position.setAttribute("y", String.valueOf(pos.getY()));
        Element valeur = new Element("valeur");
        valeur.setText(String.valueOf(val));
        position.addContent(valeur);
        donnee.addContent(position);
        return donnee;
    }

    @Override
    protected void gererDebutLotLocal(String nomLot) {
        System.out.println("Début GenerateurXML " + nomLot);
        Element lot = new Element("lot");
        lot.setAttribute("name", nomLot);
        this.lot = lot;
        this.RACINE.addContent(this.lot);
    }

    @Override
    public void traiter(Position position, double valeur) {
        Element donnee = createDonnee(position, valeur);
        lot.addContent(donnee);
        super.traiter(position, valeur);
    }

    @Override
    protected void gererFinLotLocal(String nomLot) {
        // Afficher le document
        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());

        try {
            // Créer le répertoire dans lequel seront mis les fichiers XML générés
            File dir = new File("src/fichiersGeneres");
            if (!dir.exists()) dir.mkdirs();

            FileWriter fileWriter = new FileWriter("src/fichiersGeneres/" + this.DOCUMENT_NAME);
            sortie.output(this.DOCUMENT, fileWriter);

            System.out.println("Fin GenerateurXML " + nomLot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
