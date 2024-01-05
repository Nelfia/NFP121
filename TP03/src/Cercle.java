import java.lang.Math;
import java.awt.Color;

/**
 * Cercle modélise un cercle dans un plan équipé d'un repère cartésien.
 * @author Quiterie JULIEN
 */
class Cercle implements Mesurable2D {

    private final double PI = Math.PI;  // Constante PI
    private double rayon;                // Rayon du Cercle
    private double diametre;             // Diamètre du Cercle
    private Color couleur;              // Couleur du Cercle
    private Point centre;               // Point au centre du Cercle

    /**
     * Méthode de classe qui retourne un Cercle construit à partir de son centre et d'un point situé sur son périmètre.
     * @param centre point situé au centre du cercle
     * @param extremite point situé sur la périphérie du cercle
     * @return le Cercle créé à partir du centre et d'un Point sur sa périphérie.
     */
    public static Cercle creerCercle(Point centre, Point extremite) {
        if(centre == null) centre = new Point(0,0);
        else if (extremite == null) extremite = new Point(0,0);
        return new Cercle(centre, centre.distance(extremite));
    }

    /**
     * Construire un Cercle à partir de son centre et de son rayon.
     * @param centre centre du Cercle
     * @param rayon rayon du Cercle
     */
    public Cercle(Point centre, double rayon) {
        this.couleur = Color.blue;
        this.centre = centre;
        this.rayon = rayon;
    }

    /**
     * Construire un Cercle à partir de 2 points diamétralement opposés.
     *
     * @param a Point extrémité du diamètre A
     * @param b Point extrémité du diamètre B
     */
    public Cercle(Point a, Point b) {
        this.couleur = Color.blue;
        if(a != null && b!= null){
            this.diametre = a.distance(b);
            this.rayon = this.diametre/2;
            this.centre.setX(a.getX() + this.rayon);
            this.centre.setY(a.getY() + this.rayon);
        }
    }

    /**
     * Construire un cercle à partir de 2 points diamétralement opposés et de sa couleur.
     * @param a Point extrémité du diamètre A
     * @param b Point extrémité du diamètre B
     * @param couleur couleur du cercle
     */
    public Cercle(Point a, Point b, Color couleur) {
        if(a != null && b!= null) new Cercle(a,b);
        if(couleur != null) this.couleur = couleur;
    }

    /**
     * Changer le rayon du Cercle.
     * @param rayon nouveau rayon.
     */
    public void setRayon(double rayon) {
        this.rayon = rayon;
        this.diametre = rayon * 2;
    }

    /**
     * Changer le diamètre du Cercle
     * @param diametre nouveau diamètre
     */
    public void setDiametre(double diametre) {
        this.diametre = diametre;
        this.rayon = diametre / 2;
    }

    /**
     * Changer la couleur du Cercle.
     * @param couleur nouvelle couleur du cercle.
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /**
     * Obtenir la couleur du Cercle.
     * @return la couleur du Cercle.
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Obtenir le diamètre du Cercle.
     * @return Le diacmètre du Cercle.
     */
    public double getDiametre() {
        return diametre;
    }

    /**
     * Obtenir le Rayon du Cercle.
     * @return Rayon du Cercle.
     */
    public double getRayon() {
        return rayon;
    }

    /**
     * Obtenir le centre du Cercle;
     * @return Point centre du Cercle.
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Vérifie si un point est à l'intérieur (au sens large) du Cercle.
     * @param point point que l'on compare à la situation du Cercle.
     * @return true si le Point est à l'intérieur ou sur le cercle, false s'il est à l'extérieur.
     */
    public boolean contient(Point point) {
        if(point == null) point = new Point(0,0);
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