/**
 * InterfacesDOM : Analyser un fichier de description des interfaces réseau
 */

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.util.ArrayList;

public class InterfacesDOM {

	/** Afficher le nombre d'interfaces automatiques. */
	public static void afficherNombreInterfacesAutomatiques(Document doc) {
	    // Principe : récupérer les << name >>, puis les compter
	    System.out.println("Nb interfaces automatiques = "
	            + doc.getElementsByTagName("name").getLength());
	}

	/** Afficher le nombre d'interfaces spécifiées. */
	public static void afficherNombreInterfacesSpecifiees(Document doc) {
		// A COMPLETER...
	}

	/** Afficher les noms des interfaces automatiques. */
	public static void afficherNomsInterfacesAutomatiques(Document doc) {
		// A COMPLETER...
	}

	/**
	 * Afficher les noms des interfaces qui utilisent la passerelle
	 * 147.127.18.200
	 */
	public static void afficherNomsInterfacesPasserelle(Document doc) {
		// A COMPLETER...
	}

	/** Afficher les noms des interfaces qui sont définies mais non automatiques */
	public static void afficherInterfacesDefiniesNonAutomatiques(Document doc) {
		// A COMPLETER...
	}

	/**
	 * Méthode principale.
	 * @param args le nom du fichier xml et les options
	 */
	public static void main(String[] args) {
	    // Instancier la << factory >>
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    try {
	        boolean erreur = args.length < 2;
	        if (!erreur) {
	            String fichier = args[0];

	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(fichier);

	            for (int indice = 1; indice < args.length; indice++) {
	                String option = args[indice];
	                if (option.equals("nbInterfacesAutomatiques")) {
	                    afficherNombreInterfacesAutomatiques(document);
	                } else if (option.equals("nbInterfacesSpécifiées")) {
	                    afficherNombreInterfacesSpecifiees(document);
	                } else if (option.equals("nomInterfacesAutomatiques")) {
	                    afficherNomsInterfacesAutomatiques(document);
	                } else if (option.equals("nomInterfacesPasserelle")) {
	                    afficherNomsInterfacesPasserelle(document);
	                } else if (option.equals("interfacesDefiniesNonAutomatiques")) {
	                    afficherInterfacesDefiniesNonAutomatiques(document);
	                } else if (option.equals("tout")) {
	                    afficherNombreInterfacesAutomatiques(document);
	                    afficherNombreInterfacesSpecifiees(document);
	                    afficherNomsInterfacesAutomatiques(document);
	                    afficherNomsInterfacesPasserelle(document);
	                    afficherInterfacesDefiniesNonAutomatiques(document);
	                } else {
	                    System.out.println("Information inconnue : " + option);
	                    erreur = true;
	                }

	            }
	        }

	        if (erreur) {
	            System.out.println();
	            System.out.println("Usage : java " + "InterfacesDOM"
	                    + " fichier.xml information...");
	            System.out.println();
	            System.out.println("Information : ");
	            System.out.println("   nbInterfacesAutomatiques : "
						+ "nombre d'interfaces automatiques");
	            System.out.println("   nbInterfacesSpécifiées : "
						+ "nombre d'interfaces spécifiées");
	            System.out.println("   nomInterfacesAutomatiques : "
						+ "noms des interfaces automatiques");
	            System.out.println("   nomInterfacesPasserelle : "
						+ "noms des interfaces qui utilisent la passerelle "
						+ "147.127.18.200");
	            System.out.println("   interfacesDefiniesNonAutomatiques : "
						+ "noms des interfaces définies mais non automatiques");
	        }
	    } catch (SAXParseException e) {
	        System.out.println("Erreur au niveau SAX (DOM utilise SAX) : " + e);
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
