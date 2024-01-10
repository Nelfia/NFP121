import afficheur.AfficheurSVG;
import afficheur.Ecran;

import java.awt.*;

/** Construire le schéma proposé dans le sujet de TP avec des points,
  * et des segments.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.7 $
  */
public class ExempleSchema1 {

	/** Construire le schéma et le manipuler.
	  * Le schéma est affiché.
	  * @param args les arguments de la ligne de commande
	  */
	public static void main(String[] args)
	{
		// Créer les trois segments
		Point p1 = new Point(3, 2);
		Point p2 = new Point(6, 9);
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(p1, p2);
		Segment s23 = new Segment(p2, p3);
		Segment s31 = new Segment(p3, p1);

		// Créer le barycentre
		double sx = p1.getX() + p2.getX() + p3.getX();
		double sy = p1.getY() + p2.getY() + p3.getY();
		Point barycentre = new Point(sx / 3, sy / 3);

		// Afficher le schéma
		System.out.println("Le schéma est composé de : ");
		s12.afficher();		System.out.println();
		s23.afficher();		System.out.println();
		s31.afficher();		System.out.println();
		barycentre.afficher();	System.out.println();

		// Dessiner le shéma sur un écran
		Ecran ecran = new Ecran("ExempleSchema1",600, 400, 20);
		ecran.dessinerAxes();
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
		barycentre.dessiner(ecran);

		// Translater le schéma
		p1.translater(4,-3);
		p2.translater(4,-3);
		p3.translater(4,-3);
		barycentre.translater(4,-3);

		// Redessiner le schéma après translation
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
		barycentre.dessiner(ecran);

		// Afficher le schéma au format SVG dans un fichier
		AfficheurSVG afficheurSVG = new AfficheurSVG("afficheurSVG", "affiche l'exempleSchema du TP05", 600, 400);
		afficheurSVG.dessinerLigne(p1.getX(), p1.getY(), p2.getX(), p2.getY(), s12.getCouleur());
		afficheurSVG.dessinerLigne(p2.getX(), p2.getY(), p3.getX(), p3.getY(), s23.getCouleur());
		afficheurSVG.dessinerLigne(p3.getX(), p3.getY(), p1.getX(), p1.getY(), s31.getCouleur());
		afficheurSVG.dessinerPoint(barycentre.getX(), barycentre.getY(), barycentre.getCouleur());
		afficheurSVG.ecrire("schema.svg");

		// Créer un Point et une Ligne
		Point p4 = new Point(1, 2);
		p4.setCouleur(Color.green);
		Segment ligne = new Segment(new Point(6,2), new Point(11, 9));
		ligne.setCouleur(Color.red);

		// Affiche le schéma au format texte
		AfficheurTexte afficheurTexte = new AfficheurTexte();
		p4.dessiner(afficheurTexte);
		ligne.dessiner(afficheurTexte);
		afficheurTexte.dessinerCercle(4, 4, 2.5, Color.yellow);
	}

}
