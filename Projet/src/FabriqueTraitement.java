import org.jdom2.JDOMException;

import java.io.IOException;

/**
  * FabriqueTraitement permet de construire les traitments demandés.
  * Cette classe peut être complétée si nécessaire.
  * Son seul intérêt est de pouvoir écrire les classes fournies sans
  * qu'elles provoquent des erreurs.  Les erreurs seront pour l'essentiel
  * localisées dans FabriqueTraitementConcrete.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public interface FabriqueTraitement {

	SommeAbstrait somme();
	PositionsAbstrait positions();
	Donnees donnees();
	Multiplicateur multiplicateur(double facteur);
	SommeParPosition sommeParPosition();
	SupprimerPlusGrand supprimerPlusGrand(double seuil);
	SupprimerPlusPetit supprimerPlusPetit(double seuil);
	Max max();
	Normaliseur normaliseur(double debut, double fin);
	GenerateurXML generateurXML(String nomFichier) throws IOException, JDOMException;
	Moyenne moyenne();
	MoyenneParPosition moyenneParPosition();
}
