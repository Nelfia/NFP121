import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

/** Classe chargée de l'extraction des données d'une source fichier format .xml respectant la DTD donnees2.dtd */
public class ExtractionXmlDtd2 extends ExtractionSourceAbstraite {
    private final Document document;

    public ExtractionXmlDtd2(String nomDocument) {
        super(nomDocument);
        SAXBuilder sxb = new SAXBuilder();
        try {
            this.document = sxb.build(new File(nomDocument));
        } catch (IOException e) {
            System.out.println("Erreur d'entrée/sortie : " + e);
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            System.out.println("Erreur JDOM : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void extraireDonnees() {
        try {
            Position pos;
            double valeur;
            for (Element e: this.document.getDescendants(new ElementFilter("donnee"))){
                pos = new Position(e.getAttribute("x").getIntValue(), e.getAttribute("y").getIntValue());
                for (Element val: e.getDescendants(new ElementFilter("valeur"))){
                    valeur = Double.parseDouble(val.getText());
                    this.getDonneesExtraites().traiter(pos, valeur);
                }
            }
        } catch (DataConversionException e) {
            System.out.println("Erreur Typage : " + e);
            throw new RuntimeException(e);
        }
    }

}
