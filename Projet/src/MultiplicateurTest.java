import org.junit.*;


import static org.junit.Assert.*;

/**
  * MultiplicateurTest 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class MultiplicateurTest extends TraitementTestAbstrait {

	private static final double EPSILON = 1e-8;

	private Multiplicateur multiplicateur;
	private Dernier dernier;

	@Override
	protected Multiplicateur nouveauTraitement() {
		return new FabriqueTraitementConcrete().multiplicateur(10.0);
	}

	@Override
	public void setUp() {
		super.setUp();
		this.multiplicateur = nouveauTraitement();
		this.dernier = new Dernier();
		this.multiplicateur.ajouterSuivants(this.dernier);
	}

	@Test
	public void testerMultiplicateurNominal() {
		Position p1 = new Position(1, 2);
		this.multiplicateur.gererDebutLot("Lot1");
		this.multiplicateur.traiter(p1, 11);
		assertEquals(110, this.dernier.valeur, EPSILON);
		assertSame(p1, this.dernier.position);

		this.multiplicateur.traiter(p1, 7);
		assertEquals(70, this.dernier.valeur, EPSILON);
		assertSame(p1, this.dernier.position);

		this.multiplicateur.gererFinLot("Lot1");
	}

}
