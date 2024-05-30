import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

/**
  * TraitementBuilderTest 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class TraitementBuilderTest {

	static final double EPSILON = 1e-8;
	static Field suivantsField;
	TraitementBuilder builder;

	@BeforeClass
	public static void setUpClasse() throws Exception {
		suivantsField = Traitement.class.getDeclaredField("suivants");
		suivantsField.setAccessible(true);
	}

	@Before
	public void setUp() {
		this.builder = new TraitementBuilder();
	}

	@SuppressWarnings("unchecked")
	public static List<Traitement> suivants(Traitement t) throws IllegalAccessException {
		return (List<Traitement>) suivantsField.get(t);
	}

	/** Tester analyserType. */
	@Test
	public void testerAnalyserType() throws Exception {
		assertEquals(int.class, builder.analyserType("int"));
		assertEquals(double.class, builder.analyserType("double"));
		assertEquals(String.class, builder.analyserType("java.lang.String"));
	}

	/** Tester decoderEffectif. */
	@Test
	public void testerDecoderEffectif() throws Exception {
		Scanner s = new Scanner("13 12.5 texte fin");
		assertEquals(13, TraitementBuilder.decoderEffectif(int.class, s));
		assertTrue("decoderEffectif ne doit lire qu'un mot.", s.hasNext("12.5"));
		assertEquals(12.5, TraitementBuilder.decoderEffectif(double.class, s));
		assertTrue("decoderEffectif ne doit lire qu'un mot.", s.hasNext("texte"));
		assertEquals("texte", TraitementBuilder.decoderEffectif(String.class, s));
		assertTrue("decoderEffectif ne doit lire qu'un mot.", s.hasNext("fin"));
	}

	void verifierFormels(TraitementBuilder.Signature s, Class<?>... formels) {
		assertArrayEquals("Erreur sur les paramètres formels", formels, s.formels);
	}

	void verifierEffectifs(TraitementBuilder.Signature s, Object... effectifs) {
		assertArrayEquals("Erreur sur les paramètres effectifs", effectifs, s.effectifs);
	}

	/** Tester analyserSignature : 0 paramètre */
	@Test
	public void testerAnalyserSignature0() throws Exception {
		Scanner in = new Scanner("0 suite");
		TraitementBuilder.Signature s = builder.analyserSignature(in);
		verifierFormels(s);
		verifierEffectifs(s);
		assertTrue("Trop de mots lus sur le scanner", in.hasNext("suite"));
	}

	/** Tester analyserSignature : 1 paramètre */
	@Test
	public void testerAnalyserSignature1() throws Exception {
		Scanner in = new Scanner("1 int 7 suite");
		TraitementBuilder.Signature s = builder.analyserSignature(in);
		verifierFormels(s, int.class);
		verifierEffectifs(s, 7);
		assertTrue("Trop de mots lus sur le scanner", in.hasNext("suite"));
	}

	/** Tester analyserSignature : 3 paramètres */
	@Test
	public void testerAnalyserSignature3() throws Exception {
		Scanner in = new Scanner("3 double 0.0 java.lang.String xyz int -5 suite");
		TraitementBuilder.Signature s = builder.analyserSignature(in);
		verifierFormels(s, double.class, String.class, int.class);
		verifierEffectifs(s, 0.0, "xyz", -5);
			// XXX comparaison de double, mais construits de la mÃªme manière donc ok
		assertTrue("Trop de mots lus sur le scanner", in.hasNext("suite"));
	}

	/** Un traitement seul, sans paramètres. */
	@Test
	public void testerSimple() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("Somme 0 0"), null);
		assertEquals(Somme.class, resultat.getClass());
		assertEquals(0, suivants(resultat).size());
	}

	/** Un traitement avec un suivant. */
	@Test
	public void testerUnSuivant() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("Somme 0 1 Max 0 0"), null);
		assertEquals(Somme.class, resultat.getClass());
		assertEquals(1, suivants(resultat).size());
		Traitement s = suivants(resultat).get(0);
		assertEquals(Max.class, s.getClass());
		assertEquals(0, suivants(s).size());
	}

	/** Un traitement avec deux suivants. */
	@Test
	public void testerDeuxSuivants() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("Somme 0 2 Max 0 0 Positions 0 0"), null);
		assertEquals(Somme.class, resultat.getClass());
		assertEquals(2, suivants(resultat).size());

		Traitement s1 = suivants(resultat).get(0);
		assertEquals(Max.class, s1.getClass());
		assertEquals(0, suivants(s1).size());

		Traitement s2 = suivants(resultat).get(1);
		assertEquals(Positions.class, s2.getClass());
		assertEquals(0, suivants(s2).size());
	}


	/** Cas d'un traitement avec un paramètre. */
	@Test
	public void testerParametre1() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("SupprimerPlusPetit 1 double 1.0 0"), null);
		assertEquals(SupprimerPlusPetit.class, resultat.getClass());
		assertEquals(0, suivants(resultat).size());
		Field[] fields = resultat.getClass().getDeclaredFields();
		boolean ok = false;
		for (Field f : fields) {
			if (f.getType().equals(double.class)) {
				f.setAccessible(true);
				if (Math.abs(f.getDouble(resultat) - 1.0) < EPSILON) {
					ok = true;
				}
			}
		}
		assertTrue("Paramètre de SupprimerPlusPetit mal initialisé ?", ok);
	}

}
