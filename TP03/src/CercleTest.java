import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Classe Test complémentaire de la classe Cercle.
 */
public class CercleTest {

    // Précision pour les comparaisons réelles
    public final static double EPSILON = 0.001;

    // Points du Test
    private Point A, B, C, D;

    // Cercles du Test
    private Cercle C1, C2, C3, C4, C5;

    @Before public void setUp() {
        // Construire les points
        A = new Point(0, 0);
        B = new Point(3, 5);
        C = new Point(1.0,2.0);
        D = new Point(4,2);


        // Construire les cercles
        C1 = new Cercle(A, B);
        C2 = new Cercle(A, C, Color.black);
        C3 = Cercle.creerCercle(C, D);
    }

    /** Vérifier si deux points ont mêmes coordonnées.
     * @param p1 le premier point
     * @param p2 le deuxième point
     */
    static void memesCoordonnees(String message, Point p1, Point p2) {
        assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
        assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
    }
    @Test public void testerE12() {
        assertEquals("E12: Initialisation de la couleur de C1 incorrecte", Color.blue, C1.getCouleur());
        memesCoordonnees("E12: centre de C1 incorrect", new Point(1.5,2.5), C1.getCentre());
        assertEquals("E12: Initialisation du diamètre de C1 incorrecte", A.distance(B), C1.getDiametre(), EPSILON);
        assertEquals("E12: Initialisation du rayon de C1 incorrecte", A.distance(B)/2, C1.getRayon(), EPSILON);
    }
    @Test public void testeE13() {
        assertEquals("E13: Initialisation de la couleur de C2 incorrecte", Color.black, C2.getCouleur());
        memesCoordonnees("E13: centre de C2 incorrect", new Point(0.5,1.0), C2.getCentre());
        assertEquals("E13: Initialisation du diamètre de C2 incorrecte", A.distance(C), C2.getDiametre(), EPSILON);
        assertEquals("E13: Initialisation du rayon de C2 incorrecte", A.distance(C)/2, C2.getRayon(), EPSILON);
    }
    @Test public void testeE14() {
        assertEquals("E14: Initialisation de la couleur de C3 incorrecte", Color.blue, C3.getCouleur());
        memesCoordonnees("E14: centre de C3 incorrect", C, C3.getCentre());
        assertEquals("E14: Initialisation du diamètre de C3 incorrecte", C.distance(D)*2, C3.getDiametre(), EPSILON);
        assertEquals("E14: Initialisation du rayon de C3 incorrecte", C.distance(D), C3.getRayon(), EPSILON);
    }
}