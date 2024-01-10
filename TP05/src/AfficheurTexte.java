import afficheur.Afficheur;

import java.awt.*;

/** Classe qui permet d'afficher un élément sous forme textuelle dans le terminal.  */
public class AfficheurTexte implements Afficheur {
    /** Affiche la description textuelle d'un Point
     * @param x coordonnées x du Point
     * @param y coordonnées y du Point
     * @param c couleur du Point
     */
    @Override
    public void dessinerPoint(double x, double y, Color c) {
        System.out.println( "Point {" +
                "\n\t\tx = " + x +
                "\n\t\ty = " + y +
                "\n\t\tcouleur = " + c +
                "\n}");
    }

    /**
     * Affiche une Ligne de manière textuelle
     * @param x1 coordonnées x du Point à l'extrémité 1
     * @param y1 coordonnées y du Point à l'extrémité 1
     * @param x2 coordonnées x du Point à l'extrémité 2
     * @param y2 coordonnées y du Point à l'extrémité 2
     * @param c couleur de la Ligne
     */
    @Override
    public void dessinerLigne(double x1, double y1, double x2, double y2, Color c) {
        System.out.println( "Point {" +
                "\n\t\tx1 = " + x1 +
                "\n\t\ty1 = " + y1 +
                "\n\t\tx2 = " + x2 +
                "\n\t\ty2 = " + y2 +
                "\n\t\tcouleur = " + c +
                "\n}");
    }

    /**
     * Affiche un Cercle de façon textuelle
     * @param x coordonnées x du Point situé au centre du Cercle
     * @param y coordonnées y du Point situé au centre du Cercle
     * @param rayon rayon du Cercle
     * @param c couleur du Cercle
     */
    @Override
    public void dessinerCercle(double x, double y, double rayon, Color c) {
        System.out.println("Cercle {" +
                "\n\t\tcentre x = " + x +
                "\n\t\tcentre y = " + y +
                "\n\t\trayon = " + rayon +
                "\n\t\tcouleur = " + c +
                "\n}");
    }

    /**
     * Affiche un texte à la position souhaitée.
     * @param x nombre de tabulations avant le texte (>= 0)
     * @param y nombre de lignes avant le texte (>= 0)
     * @param texte texte à afficher
     * @param c couleur du texte
     */
    @Override
    public void dessinerTexte(double x, double y, String texte, Color c) {
        assert x >= 0 && y >= 0;
        StringBuilder newTexte = new StringBuilder();
        for(int j = 0; j <= y; j++){
            newTexte.append("\n");
        }
        for(int i = 0; i <= x; i++){
            newTexte.append('\t');
        }
        newTexte.append(texte);
        System.out.println(newTexte);
    }
}
