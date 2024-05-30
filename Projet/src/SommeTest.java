import org.junit.*;
import static org.junit.Assert.*;

/**
  * SommeTest 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class SommeTest extends TraitementTestAbstrait {

	private static final double EPSILON = 1e-8;

	private SommeAbstrait somme;

	@Override
	protected SommeAbstrait nouveauTraitement() {
		return new FabriqueTraitementConcrete().somme();
	}

	@Override
	public void setUp() {
		super.setUp();
		this.somme = nouveauTraitement();
	}

	@Test
	public void testerChainage() {
		testerChainage(true, true);
	}

	@Test
	public void testerSommeNominal() {
		Position p1 = new Position(1, 2);
		assertEquals(0, this.somme.somme(), 0.0);
		this.somme.gererDebutLot("Lot1");
		this.somme.traiter(p1, 11);
		assertEquals(11, this.somme.somme(), EPSILON);
		this.somme.traiter(p1, 7);
		assertEquals(18, this.somme.somme(), EPSILON);
		this.somme.gererFinLot("Lot1");
	}

}
