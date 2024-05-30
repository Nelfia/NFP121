import org.junit.*;
import static org.junit.Assert.*;

/**
  * PositionsTest teste le traitement Positions.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class PositionsTest extends TraitementTestAbstrait {

	private PositionsAbstrait positions;
	private Position p1, p2;

	@Override
	protected PositionsAbstrait nouveauTraitement() {
		return new FabriqueTraitementConcrete().positions();
	}

	@Override
	public void setUp() {
		super.setUp();
		this.positions = nouveauTraitement();
		this.p1 = new Position(1, 2);
		this.p2 = new Position(-3, 5);
		this.positions.gererDebutLot("Lot1");
		this.positions.traiter(p1, 11);
		this.positions.traiter(p2, 7);
		this.positions.traiter(p1, 3);
		this.positions.gererFinLot("Lot1");
	}

	@Test
	public void testerChainage() {
		testerChainage(true, true);
	}

	@Test
	public void testerNominalPositions() {
		assertSame(p1, this.positions.position(0));
		assertSame(p2, this.positions.position(1));
		assertSame(p1, this.positions.position(2));
	}

	@Test
	public void testerNominalFrequence() {
		assertEquals(2, this.positions.frequence(this.p1));
		assertEquals(1, this.positions.frequence(this.p2));
		assertEquals(0, this.positions.frequence(new Position(15, 1)));
	}

	@Test
	public void testerNominalFrequenceNouvellesPositions() {
		assertEquals(2, this.positions.frequence(new Position(1, 2)));
		assertEquals(1, this.positions.frequence(new Position(-3, 5)));
		assertEquals(0, this.positions.frequence(new Position(15, 1)));
	}

	@Test
	public void testerLimitereurFrequence() {
		assertEquals(0, this.positions.frequence(null));
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testerErreurIndexTropGrand() {
		assertSame(p1, this.positions.position(3));
	}
	

	@Test(expected=IndexOutOfBoundsException.class)
	public void testerErreurIndexTropPetit() {
		this.positions.position(-1);
	}
	



}
