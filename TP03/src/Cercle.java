import java.lang.Math;
import java.awt.Color;

/**
 * Cercle modélise un cercle dans un plan équipé d'un repère cartésien.
 * @author Quiterie JULIEN
 */
class Cercle implements Mesurable2D {

    private final double PI = Math.PI;  // Constante PI
    private double rayon;               // Rayon du Cercle
    private double diametre;            // Diamètre du Cercle
    private Color couleur;              // Couleur du Cercle
    private Point centre;               // Point au centre du Cercle

    /**
     * Méthode de classe qui retourne un Cercle construit à partir de son centre et d'un point situé sur son périmètre.
     * @param centre Point situé au centre du cercle
     * @param extremite Point situé sur la périphérie du cercle
     * @return instance de Cercle
     */
    public static Cercle creerCercle(Point centre, Point extremite) {
        assert centre != null && extremite != null;
        return new Cercle(centre, (centre.distance(extremite)));
    }

    /**
     * Construire un Cercle à partir de son centre et de son rayon.
     * @param centre centre du Cercle
     * @param rayon rayon du Cercle
     */
    public Cercle(Point centre, double rayon) {
        assert centre != null && rayon > 0;

        this.couleur = Color.blue;
        this.centre = new Point(centre.getX(), centre.getY());
        this.rayon = rayon;
        this.diametre = rayon * 2;
    }

    /**
     * Construire un Cercle à partir de 2 points diamétralement opposés.
     *
     * @param a this extrémité A
     * @param b this extrémité B
     */
    public Cercle(Point a, Point b) {
        assert a != null && b != null && (a.getX() != b.getX() && a.getY() != b.getY());

        Point pointA = new Point(a.getX(), a.getY());
        Point pointB = new Point(b.getX(), b.getY());
        this.diametre = pointA.distance(pointB);
        this.rayon = this.diametre / 2;
        this.centre = new Point(((pointA.getX()+pointB.getX())/2), ((pointA.getY()+pointB.getY())/2));
        this.couleur = Color.blue;
    }

    /**
     * Construire un cercle à partir de 2 points diamétralement opposés et de sa couleur.
     * @param a this extrémité A
     * @param b this extrémité B
     * @param couleur this couleur
     */
    public Cercle(Point a, Point b, Color couleur) {
        this(a, b);
        assert couleur != null;
        this.couleur = couleur;
    }

    /**
     * Affiche un Cercle sous forme d'une string.
     * @return description du Cercle
     */
    @Override
    public String toString() {
        return  "C" + rayon +
                "@(" + centre.getX() +
                ", " + centre.getY() +
                ')';
    }

    /**
     * Changer le rayon du Cercle.
     * @param rayon nouveau rayon.
     */
    public void setRayon(double rayon) {
        assert rayon > 0;

        this.rayon = rayon;
        this.diametre = rayon * 2;
    }

    /**
     * Changer le diamètre du Cercle
     * @param diametre nouveau diamètre
     */
    public void setDiametre(double diametre) {
        assert diametre > 0;

        this.diametre = diametre;
        this.rayon = diametre / 2;
    }

    /**
     * Changer la couleur du Cercle.
     * @param couleur nouvelle couleur du cercle.
     */
    public void setCouleur(Color couleur) {
        assert couleur != null;

        this.couleur = couleur;
    }

    /**
     * Obtenir la couleur du Cercle.
     * @return la couleur du Cercle.
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Obtenir le diamètre du Cercle.
     * @return Le diacmètre du Cercle.
     */
    public double getDiametre() {
        return this.diametre;
    }

    /**
     * Obtenir le Rayon du Cercle.
     * @return Rayon du Cercle.
     */
    public double getRayon() {
        return this.rayon;
    }

    /**
     * Obtenir le centre du Cercle;
     * @return Point centre du Cercle.
     */
    public Point getCentre() {
        return new Point(this.centre.getX(), this.centre.getY());
    }

    /**
     * Vérifie si un point est à l'intérieur (au sens large) du Cercle.
     * @param point point que l'on compare à la situation du Cercle.
     * @return true si le Point est à l'intérieur ou sur le cercle, false s'il est à l'extérieur.
     */
    public boolean contient(Point point) {
        assert point != null;

        return point.distance(this.centre) <= this.rayon;
    }

    /**
     * Obtenir le périmètre du Cercle.
     * @return le périmètre du Cerlce.
     */
    @Override
    public double perimetre() {
        return 2 * this.PI * this.rayon;
    }

    /**
     * Obtenir l'aire du cercle.
     * @return l'aire du cercle.
     */
    @Override
    public double aire() {
        return this.PI * Math.pow(this.rayon, 2);
    }

    /**
     * Translate le cercle en fonction des coordonnées x et y.
     * @param x Translate le centre du Cercle sur la position x.
     * @param y Translate le centre du Cerlce sur la position y.
     */
    public void translater(double x, double y) {
        this.centre.translater(x, y);
    }
}