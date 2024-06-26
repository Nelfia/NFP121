import java.util.Objects;

/** Définir une position.  */
public class Position {
	private final int x;
	private final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		// System.out.println("...appel à Position(" + x + "," + y + ")" + " --> " + this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override public String toString() {
		return super.toString() + "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return x == position.x && y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}
