import org.jdom2.JDOMException;

import java.io.IOException;

/**
  * FabriqueTraitementConcrete concrétise FabriqueTraitement.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class FabriqueTraitementConcrete implements FabriqueTraitement {

	@Override public Somme somme() {
		return new Somme();
	}

	@Override public Positions positions() {
		return new Positions();
	}

	@Override public Donnees donnees() {
		return new Donnees();
	}

	@Override public Multiplicateur multiplicateur(double facteur) {
		return new Multiplicateur(facteur);
	}

	@Override public SommeParPosition sommeParPosition() {
		return new SommeParPosition();
	}

	@Override public SupprimerPlusGrand supprimerPlusGrand(double seuil) {
		return new SupprimerPlusGrand(seuil);
	}

	@Override public SupprimerPlusPetit supprimerPlusPetit(double seuil) {
		return new SupprimerPlusPetit(seuil);
	}

	@Override public Max max() {
		return new Max();
	}

	@Override public Normaliseur normaliseur(double debut, double fin) {
		return new Normaliseur(debut, fin);
	}

	@Override public GenerateurXML generateurXML(String nomFichier) throws IOException, JDOMException {
		return new GenerateurXML(nomFichier);
	}
}
