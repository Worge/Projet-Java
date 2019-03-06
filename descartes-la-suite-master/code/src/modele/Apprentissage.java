package modele;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apprentissage {

    private ArrayList<Carte> pileDeCartes;
    private ArrayList<Double> probasDesCartes = new ArrayList<Double>();
    private ArrayList<Integer> nbrErreursDesCartes = new ArrayList<Integer>();
    private ArrayList<Boolean> dernierSuccesDesCartes = new ArrayList<Boolean>();
    private ArrayList<String> nomPilesSelectionnees = new ArrayList<String>();
    private ArrayList<Integer> nombreElementsParPile = new ArrayList<Integer>();
    private int numeroCarteActive ;
    private int taille;
    private int nbrCartesDifferentesDeZero;
    private boolean fini;
    private boolean apprentissage;

    //initialisation de l'apprentissage (pour l'instant, il lui faut une pile en paramètre)
    public Apprentissage(ArrayList<Carte> pile, boolean apprentissage) {
        this.apprentissage=apprentissage;
        this.pileDeCartes = pile;
        this.taille=pile.size();
        for (int i = 0; i<taille; i++){
            this.probasDesCartes.add((double) (1.0/taille));
            this.nbrErreursDesCartes.add(0);
            if (taille>6) {
                this.dernierSuccesDesCartes.add(true);
            } else {
                this.dernierSuccesDesCartes.add(false);
            }
        }
        this.numeroCarteActive=0;
        this.nbrCartesDifferentesDeZero=taille;
        if (taille != 0) {
            this.fini = false;
        } else {
            this.fini=true;
        }
    }
    //A appeler dès qu'on passe à la carte suivante, après avoir appeler le getteur fini
    public void AvancerUneCarte(boolean reponse) {

        //on gère la réponse
        double probabiliteDeLaDerniereCarte=probasDesCartes.get(this.numeroCarteActive);
        double probabiliteAEnlever = 0.0;
        double newValeur;
        int nbErreursPrecedents=this.nbrErreursDesCartes.get(this.numeroCarteActive);

        if(apprentissage) {
            //s'il a bien répondu du premier coup ou qu'il a bien répondu 2 fois d'affilées
            if (reponse && this.dernierSuccesDesCartes.get(this.numeroCarteActive)) {
                probasDesCartes.set(this.numeroCarteActive, 0.0);
                this.nbrCartesDifferentesDeZero--;
                if (this.nbrCartesDifferentesDeZero == 0) {
                    this.fini = true;
                    return;
                } else {
                    probabiliteAEnlever = probabiliteDeLaDerniereCarte / this.nbrCartesDifferentesDeZero;
                }

                for (int i = 0; i < taille; i++) {
                    if (i != this.numeroCarteActive && this.probasDesCartes.get(i) != 0.0) {
                        newValeur = this.probasDesCartes.get(i) + probabiliteAEnlever;
                        this.probasDesCartes.set(i, newValeur);
                    }
                }
            } else if (reponse && !this.dernierSuccesDesCartes.get(this.numeroCarteActive)) {//s'il a bien répondu pour la première fois d'affilé mais a déjà eu des erreurs
                this.dernierSuccesDesCartes.set(this.numeroCarteActive, true);
            } else { //s'il n'a pas bien répondu
                this.nbrErreursDesCartes.set(this.numeroCarteActive, nbErreursPrecedents + 1);
                this.dernierSuccesDesCartes.set(this.numeroCarteActive, false);
            }
        } else {

            probasDesCartes.set(this.numeroCarteActive, 0.0);
            this.nbrCartesDifferentesDeZero--;
            if (!reponse) {
                this.nbrErreursDesCartes.set(this.numeroCarteActive, nbErreursPrecedents + 1);
            }
            if (this.nbrCartesDifferentesDeZero == 0) {
                this.fini = true;
                return;
            } else {
                probabiliteAEnlever = probabiliteDeLaDerniereCarte / this.nbrCartesDifferentesDeZero;
            }
            for (int i = 0; i < taille; i++) {
                if (i != this.numeroCarteActive && this.probasDesCartes.get(i) != 0.0) {
                    newValeur = this.probasDesCartes.get(i) + probabiliteAEnlever;
                    this.probasDesCartes.set(i, newValeur);
                }
            }

        }


        //Choix de la prochaine carte
        double probabilite = Math.random();
        double somme=0.0;
        boolean notFini = true;
        int i = 0;
        while (notFini) {
            if(i>=taille){ //Si erreur
                notFini=false;
                return;
            }
            somme = somme + probasDesCartes.get(i);
            if (somme > probabilite) {
                notFini = false;

                //prochaine carte active :
                this.numeroCarteActive = i;
            }
            i++;
        }
    }


    public void setNomPilesSelectionnees(ArrayList<String> res) {
        this.nomPilesSelectionnees=res;
    }
    public void setNombreElementsParPile(ArrayList<Integer> res) {
        this.nombreElementsParPile=res;
    }
    public ArrayList<String> getNomPilesSelectionnees() {
        return nomPilesSelectionnees;
    }
    public ArrayList<Integer> getNombreElementsParPile() {
        return nombreElementsParPile;
    }

    public ArrayList<Carte> getPileDeCartes() { return this.pileDeCartes; }
    public boolean getFini() {
        return this.fini;
    }
    public String getCarteQuestion() {
        return pileDeCartes.get(this.numeroCarteActive).getQuestion();
    }
    public String getCarteReponse() {
        return pileDeCartes.get(this.numeroCarteActive).getReponse();
    }
    public String getCarteTheme() {
        return pileDeCartes.get(this.numeroCarteActive).getTheme();
    }
    public String getCarteType() {return pileDeCartes.get(this.numeroCarteActive).getType(); }

    public String getFausseReponse1() { return pileDeCartes.get(this.numeroCarteActive).getFausseReponse1(); }
    public String getFausseReponse2() { return pileDeCartes.get(this.numeroCarteActive).getFausseReponse2(); }
    public String getFausseReponse3() { return pileDeCartes.get(this.numeroCarteActive).getFausseReponse3(); }
    public int getNbrCartesDifferentesDeZero(){return nbrCartesDifferentesDeZero;}

    //getteurs pour les stats

    public ArrayList<Integer> getNbrErreursDesCartes() { return this.nbrErreursDesCartes; } //renvoie la liste des nombres d'erreures de chaque carte

}
