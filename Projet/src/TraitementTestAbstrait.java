import org.junit.*;
import static org.junit.Assert.*;

/**
  * TraitementTestAbstrait 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public abstract class TraitementTestAbstrait {

	private Dernier dernier;
	private Dernier dernier2;
	private Traitement unTraitement;
	private Object setUpAppelee;
	
	@Before
	public void setUp() {
		this.dernier = new Dernier();
		this.dernier2 = new Dernier();
		this.unTraitement = nouveauTraitement();
		this.unTraitement.ajouterSuivants(dernier, dernier2);
		this.setUpAppelee = this;
	}

	protected abstract Traitement nouveauTraitement();

	@Test
	public void tester0SetUpCorrect() {
		assertNotNull("Erreur setUp() doit être redéfinie et appeler super.setUp()", this.setUpAppelee);
	}

	private int testerChainageLot(String nom, int nb, boolean transmission, boolean conservatif) {
		this.unTraitement.gererDebutLot(nom);
		assertEquals("Mauvaise transmission de gererDebutLot()", nom, dernier.nomDebut);

		Position p1 = new Position(1, 2);
		this.unTraitement.traiter(p1, 11.0);
		nb++;
		if (transmission) {
			assertEquals("Appel à super.traiter(...) manquant ?", nb, this.dernier.nbDonnees);
			if (conservatif) {
				assertEquals("La valeur n'a pas été transmise. Appel à super.traiter(...) manquant ?", 11.0, this.dernier.valeur, 0.0);
				assertSame("La valeur n'a pas été transmise. Appel à super.traiter(...) manquant ?", p1, this.dernier.position);
				assertEquals("La valeur n'a pas été transmise. Appel à super.traiter(...) manquant ?", 11.0, this.dernier2.valeur, 0.0);
				assertSame("La valeur n'a pas été transmise. Appel à super.traiter(...) manquant ?", p1, this.dernier2.position);
			}
		}

		Position p2 = new Position(0, 0);
		this.unTraitement.traiter(p2, 7.0);
		nb++;
		if (transmission) {
			assertEquals("Appel à super.traiter(...) manquant ?", nb, this.dernier.nbDonnees);
			if (conservatif) {
				assertEquals(7.0, this.dernier.valeur, 0.0);
				assertEquals(7.0, this.dernier2.valeur, 0.0);
				assertSame(p2, this.dernier.position);
				assertSame(p2, this.dernier2.position);
			}
		}

		this.unTraitement.gererFinLot(nom);
		assertEquals("Mauvaise transmission de gererFinLot()", nom, dernier.nomFin);

		return nb;
	}

	@Test
	final public void testerChainageGerer() {
		testerChainage(false, false);
	}

	protected void testerChainage(boolean transmission, boolean conservatif) {
		int nb = 0;	// nombre d'appels à traiter
		nb = testerChainageLot("lot1", nb, transmission, conservatif);
		nb = testerChainageLot("lot2", nb, transmission, conservatif);
	}


	static protected class Dernier extends Traitement {
		protected int nbDonnees;
		protected Position position;
		protected double valeur;
		protected String nomDebut;
		protected String nomFin;

		@Override
		public void traiter(Position position, double valeur) {
			assertNotNull("gererDebutLot doit être appelée avant traiter !", this.nomDebut);
			this.position = position;
			this.valeur = valeur;
			this.nbDonnees++;
		}

		@Override
		protected void gererDebutLotLocal(String nomLot) {
			assertNotNull("Le nom d'un lot doit être défini.", nomLot);
			this.nomDebut = nomLot;
		}

		@Override
		protected void gererFinLotLocal(String nomLot) {
			assertNotNull("Le nom d'un lot doit être défini.", nomLot);
			assertNotNull("gererDebutLot doit être appelée avant gererFinLot.", this.nomDebut);
			this.nomFin = nomLot;
			assertEquals("Le nom du lot au début et à la fin doit être le même.", this.nomDebut, this.nomFin);
			this.nomDebut = null;
		}

	}

}
