package bd;

import modele.Pile;

import javax.swing.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Enregistrer {

    public void export(String nomPile)throws IOException{

            if (!nomPile.equals("")) {
                JFileChooser filechoose = new JFileChooser();
                filechoose.setCurrentDirectory(new File("."));  /* ouvrir la boite de dialogue dans répertoire courant */
                filechoose.setDialogTitle("Sélectionner un dossier"); /* nom de la boite de dialogue */

                filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); /* pour afficher seulement les répertoires */

                String approve = new String("Enregistrer"); /* Le bouton pour valider l’enregistrement portera la mention Enregistrer */
                int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
                if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) { /* Si l’utilisateur clique sur le bouton Enregistrer */
                    String fich = new String();
                    if (nomPile.contains(" ")) {
                        fich = nomPile.replace(" ", "_");
                    }
                    if (nomPile.contains(" ")) {
                        fich = nomPile.replace("\'", "");
                    }
                    String chemin = filechoose.getSelectedFile().getAbsolutePath() + "/" + fich + ".txt"; /* pour avoir le chemin absolu */

                    H2Contient c = new H2Contient();
                    H2Cartes ca = new H2Cartes();
                    H2Piles p = new H2Piles();

                    ArrayList<String> carte = c.getCarteDePile(nomPile);

                    File fichier = new File(chemin);
                    try {

                        fichier.createNewFile();

                        FileWriter writer = new FileWriter(fichier);
                        try {
                            writer.write(nomPile + "\n");
                            writer.write(p.getDescriptionByNom(nomPile) + "\n");
                            for (int i = 0; i < carte.size(); i++) {

                                writer.write(carte.get(i) + "\n");
                                writer.write(ca.getRepCarteByQuest(carte.get(i)) + "\n");
                                writer.write(ca.getThemCarteByQuest(carte.get(i)) + "\n");
                                writer.write(ca.getTypeCarteByQuest(carte.get(i)) + "\n");
                                if (ca.getTypeCarteByQuest(carte.get(i)) == "QCM") {
                                    writer.write(ca.getRep1ByQuest(carte.get(i)) + "\n");
                                    writer.write(ca.getRep2ByQuest(carte.get(i)) + "\n");
                                    writer.write(ca.getRep3ByQuest(carte.get(i)) + "\n");
                                }
                                writer.write("*$$*\n");

                            }
                            writer.write("**$$$**\n");
                        } finally {

                            writer.close();
                        }
                    } catch (Exception e) {
                        System.out.println("Impossible de creer le fichier");
                    }
                }
            }
    }

    public void importer()throws IOException {
        H2Contient cont = new H2Contient();
        H2Piles p = new H2Piles();
        H2Cartes c = new H2Cartes();
        JFileChooser filechoose = new JFileChooser();
        filechoose.setCurrentDirectory(new File("."));  /* ouvrir la boite de dialogue dans répertoire courant */
        filechoose.setDialogTitle("Choisir la pile à importer"); /* nom de la boite de dialogue */

        String approve = new String("Importer"); /* Le bouton pour valider l’enregistrement portera la mention Enregistrer */
        int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
        if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) { /* Si l’utilisateur clique sur le bouton Enregistrer */
            String chemin = filechoose.getSelectedFile().getAbsolutePath();
            //System.out.println(chemin); -------------------------------------   LE CHEMIN ICI    -----------------------
            BufferedReader br = new BufferedReader(new FileReader(chemin));
            String line = br.readLine();
            String nom = line ;
            if(!p.isInPile(nom)) {
                line = br.readLine();
                String desc = line;
                p.ajouterPiles(nom, desc);
                while (!(line.equals("**$$$**"))) {
                    int i = 0;
                    String q = new String();
                    String rep = new String();
                    String them = new String();
                    String type = new String();
                    String r1 = new String();
                    String r2 = new String();
                    String r3 = new String();
                    while (!(line = br.readLine()).equals("**$$$**") && !(line.equals("*$$*"))) {

                        //System.out.println(i);
                        //System.out.println(line);
                        if (i == 0)
                            q = line;
                        if (i == 1)
                            rep = line;
                        if (i == 2)
                            them = line;
                        if (i == 3)
                            type = line;
                        if (i == 4)
                            r1 = line;
                        if (i == 5)
                            r2 = line;
                        if (i == 6)
                            r3 = line;
                        i++;
                    }
                    if (type.equals("V/F")) {
                        c.ajouterCarteVf(q, rep, them, true);
                        cont.ajouterCartePile(q, nom);
                    }
                    if (type.equals("QCM")) {
                        c.ajouterCarteQCM(q, rep, them, true, r1, r2, r3);
                        cont.ajouterCartePile(q, nom);
                    }
                    if (type.equals("Classique")) {
                        c.ajouterCarteNormale(q, rep, them);
                        cont.ajouterCartePile(q, nom);
                    }

                }
            }
                br.close();

            }

    }
}
