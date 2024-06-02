import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe qui permet la génération de fichiers texte de type 1 à l'aide d'une interface Swing.
 * Valider: permet d'ajouter une nouvelle donnée si elle est correctement saisie.
 * Effacer: permet de supprimer toutes les données insérées dans les champs.
 * Terminer: enregistre le fichier généré à l'emplacement: src/fichiersGeneres avec le nom passé en paramètre lors de
 * l'exécution de la commande.
 */
public class GenererTxtT1 extends JPanel {
    final String nomFichierSortie;
    String content;
    int numeroOrdre;
    final private JTextField abcisse = new JTextField(1);
    final private JTextField ordonnnee = new JTextField(1);
    final private JTextField valeur = new JTextField(1);
    final private JButton btnValider = new JButton("Valider");
    final private JButton btnEffacer = new JButton("Effacer");
    final private JButton btnTerminer = new JButton("Terminer");

    public GenererTxtT1(String nomFichierSortie) {
        super();
        this.nomFichierSortie = nomFichierSortie;
        this.content = "";
        this.numeroOrdre = 1;

        // 1ère ligne
        JPanel premiereLigne = new JPanel();
        premiereLigne.setLayout(new GridLayout(1,2));
        premiereLigne.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        JLabel labelAbcisse = new JLabel("Abcisse : ");
        labelAbcisse.setHorizontalAlignment(SwingConstants.RIGHT);
        premiereLigne.add(labelAbcisse);
        premiereLigne.add(abcisse);

        // 2ème ligne
        JPanel deuxiemeLigne = new JPanel();
        deuxiemeLigne.setLayout(new GridLayout(1,2));
        deuxiemeLigne.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        JLabel labelOrdonnee = new JLabel("Ordonnée : ");
        deuxiemeLigne.add(labelOrdonnee);
        labelOrdonnee.setHorizontalAlignment(SwingConstants.RIGHT);
        deuxiemeLigne.add(ordonnnee);

        // 3ème ligne
        JPanel troisiemeLigne = new JPanel();
        troisiemeLigne.setLayout(new GridLayout(1,2));
        troisiemeLigne.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        JLabel labelValeur = new JLabel("Valeur : ");
        labelValeur.setHorizontalAlignment(SwingConstants.RIGHT);
        troisiemeLigne.add(labelValeur);
        troisiemeLigne.add(valeur);

        // 4ème ligne
        JPanel quatriemeLigne = new JPanel();
        quatriemeLigne.setLayout(new BoxLayout(quatriemeLigne, BoxLayout.X_AXIS));
        quatriemeLigne.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        quatriemeLigne.add(btnValider);
        quatriemeLigne.add(Box.createHorizontalGlue());
        quatriemeLigne.add(btnEffacer);
        quatriemeLigne.add(Box.createHorizontalGlue());
        quatriemeLigne.add(btnTerminer);

        // Construction du Layout Manager
        this.setLayout(new GridLayout(4, 3));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(premiereLigne);
        this.add(deuxiemeLigne);
        this.add(troisiemeLigne);
        this.add(quatriemeLigne);

        // Ajout d'écouteurs d'événements sur les boutons
        ActionTrace trace = new ActionTrace();
        btnValider.addMouseListener(trace);
        btnEffacer.addMouseListener(trace);
        btnTerminer.addMouseListener(trace);

    }

    private class ActionTrace extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent ev) {
            System.out.println("Appui sur "
                    + ((JButton) ev.getSource()).getText());

            // Click sur le bouton Valider
            if(ev.getSource() == btnValider) {
                boolean erreur = false;
                int x = 0;
                int y = 0;
                double val = 0;
                try {
                    abcisse.setBackground(Color.WHITE);
                    x = Integer.parseInt(abcisse.getText());
                } catch (NumberFormatException e) {
                    abcisse.setBackground(Color.RED);
                    erreur = true;
                    System.out.println("Le type de l'abcisse n'est pas entier.");
                }
                try {
                    ordonnnee.setBackground(Color.WHITE);
                    y = Integer.parseInt(ordonnnee.getText());
                } catch (NumberFormatException e) {
                    ordonnnee.setBackground(Color.RED);
                    erreur = true;
                    System.out.println("Le type de l'ordonnée n'est pas entier.");
                }
                try {
                    valeur.setBackground(Color.WHITE);
                    val = Double.parseDouble(valeur.getText());
                } catch (NumberFormatException e) {
                    valeur.setBackground(Color.RED);
                    erreur = true;
                    System.out.println("Le type de l'abcisse n'est pas double.");
                }

                if(!erreur) {
                    content += x + " " + y + " " + numeroOrdre + " " + val + "\n";
                    numeroOrdre++;
                    System.out.println(content);
                }
            }

            // Click sur le bouton Effacer
            if(ev.getSource() == btnEffacer) {
                abcisse.setText("");
                ordonnnee.setText("");
                valeur.setText("");
            }

            // Click sur le bouton Terminer
            if (ev.getSource() == btnTerminer) {
                // Créer le répertoire dans lequel seront mis les fichiers XML générés
                File dir = new File("fichiersGeneres");
                if (!dir.exists()) dir.mkdirs();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichierSortie.startsWith("fichiersGeneres/")? nomFichierSortie : "fichiersGeneres/" + nomFichierSortie))) {
                    writer.write(content);
                    System.out.println("Le fichier a été écrit avec succès.");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GenererTxtT1.this);
                    frame.dispose();
                } catch (IOException e) {
                    System.err.println("Une erreur s'est produite lors de l'écriture du fichier: " + e.getMessage());
                }
            }
        }
    }

    public static void runSwing(String nomFichierSortie){
        JFrame fenetre = new JFrame("Saisie données");
        GenererTxtT1 generateurTxt = new GenererTxtT1(nomFichierSortie);
        fenetre.getContentPane().add(generateurTxt);
        fenetre.setSize(300, 180);
        fenetre.setLocation(800, 300);
        fenetre.dispose();
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fenetre.setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            String arg = args[0];
            EventQueue.invokeLater(() -> GenererTxtT1.runSwing(arg));
        } else if (args.length == 0) {
            System.out.println("Veuillez ajouter le nom du fichier en sortie.");
        } else {
            System.out.println("Vous avez passé trop d'arguments.");
        }
    }
}
