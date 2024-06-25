import static org.junit.Assert.assertNotNull;

/**
  * Somme calcule la sommee des valeurs, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Somme extends SommeAbstrait {

	private double somme;

	public Somme() {
		this.somme = 0;
	}

	@Override
	public void traiter(Position position, double valeur) {
		somme += valeur;
		super.traiter(position, valeur);
	}

	@Override
	public double somme() {
		return this.somme;
	}

	@Override
	protected void gererDebutLotLocal(String nomLot) {
		super.gererDebutLotLocal(nomLot);
	}

	@Override
	protected void gererFinLotLocal(String nomLot) {
		System.out.println(nomLot + ": somme = " + this.somme());
	}
}
