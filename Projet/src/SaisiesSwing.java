import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe qui permet la génération de fichiers texte de type 1 à l'aide d'une interface Swing.
 * Valider: permet d'ajouter une nouvelle donnée si elle est correctement saisie.
 * Effacer: permet de supprimer toutes les données insérées dans les champs.
 * Terminer: enregistre le fichier généré à l'emplacement: src/fichiersGeneres avec le nom passé en paramètre lors de
 * l'exécution de la commande. Le nom du fichier doit contenir l'extension ".txt".
 */
public class SaisiesSwing extends JPanel {
    final private String nomFichierSortie;
    private String fileContent;
    private int numeroOrdre;
    final private JTextField abcisse = new JTextField(1);
    final private JTextField ordonnnee = new JTextField(1); // TODO: à vérifier
    final private JTextField valeur = new JTextField(1);

    public SaisiesSwing(String nomFichierSortie) {
        super();
        this.nomFichierSortie = nomFichierSortie;
        this.fileContent = "";
        this.numeroOrdre = 1;

        JPanel premiereLigne = getJPanelLineWithJTextFieldAndLabel(abcisse, "Abcisse");
        JPanel deuxiemeLigne = getJPanelLineWithJTextFieldAndLabel(ordonnnee, "Ordonnée");
        JPanel troisiemeLigne = getJPanelLineWithJTextFieldAndLabel(valeur, "Valeur");

        JPanel quatriemeLigne = new JPanel();
        JButton btnValider = new JButton("Valider");
        JButton btnEffacer = new JButton("Effacer");
        JButton btnTerminer = new JButton("Terminer");
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
        btnValider.addActionListener(new ActionValider());
        btnEffacer.addActionListener(new ActionEffacer());
        btnTerminer.addActionListener(new ActionTerminer());
    }

    private JPanel getJPanelLineWithJTextFieldAndLabel(JTextField input, String labelStr) {
        JPanel ligne = new JPanel();
        ligne.setLayout(new GridLayout(1,2));
        ligne.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        JLabel label = new JLabel(labelStr + " : ");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        ligne.add(label);
        ligne.add(input);
        return ligne;
    }

    private class ActionValider implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Integer x = getInt(abcisse);
            Integer y = getInt(ordonnnee);
            Double val = getDouble(valeur);

            if(x != null && y != null && val != null) {
                fileContent += x + " " + y + " " + numeroOrdre + " " + val + "\n";
                numeroOrdre++;
                System.out.println(fileContent);
            }
        }

        private Integer getInt(JTextField input){
            try {
                input.setBackground(Color.WHITE);
                return Integer.parseInt(input.getText());
            } catch (NumberFormatException e) {
                input.setBackground(Color.RED);
                System.out.println("Le type de l'abcisse n'est pas entier.");
                return null;
            }
        }

        private Double getDouble(JTextField input) {
            try {
                input.setBackground(Color.WHITE);
                return Double.parseDouble(valeur.getText());
            } catch (NumberFormatException e) {
                valeur.setBackground(Color.RED);
                System.out.println("Le type de l'abcisse n'est pas double.");
                return null;
            }
        }
    }

    private class ActionEffacer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            abcisse.setText("");
            ordonnnee.setText("");
            valeur.setText("");
        }
    }

    private class ActionTerminer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Créer le répertoire dans lequel seront mis les fichiers XML générés
            File dir = new File("fichiersGeneres");
            if (!dir.exists()) dir.mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichierSortie.startsWith("fichiersGeneres/")? nomFichierSortie : "fichiersGeneres/" + nomFichierSortie))) {
                writer.write(fileContent);
                System.out.println("Le fichier a été écrit avec succès.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SaisiesSwing.this);
                frame.dispose();
            } catch (IOException e) {
                System.err.println("Une erreur s'est produite lors de l'écriture du fichier: " + e.getMessage());
            }
        }
    }

    public static void runSwing(String nomFichierSortie){
        JFrame fenetre = new JFrame("Saisie données");
        SaisiesSwing saisiesSwing = new SaisiesSwing(nomFichierSortie);
        fenetre.getContentPane().add(saisiesSwing);
        fenetre.setSize(300, 180);
        fenetre.setLocation(800, 300);
        fenetre.dispose();
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fenetre.setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            String arg = args[0];
            EventQueue.invokeLater(() -> SaisiesSwing.runSwing(arg));
        } else if (args.length == 0) {
            System.out.println("Veuillez ajouter le nom du fichier en sortie.");
        } else {
            System.out.println("Vous avez passé trop d'arguments.");
            System.exit(-1);
        }
    }
}
