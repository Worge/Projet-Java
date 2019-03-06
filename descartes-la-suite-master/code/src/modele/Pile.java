package modele;

import bd.H2Cartes;
import bd.H2Contient;
import bd.H2Piles;

import java.util.ArrayList;

public class Pile {

    //à voir avec la bd
    protected ArrayList<Carte> pile = new ArrayList<Carte>();


    private int id;
    private String nom;
    private String description;

    // Constructeur
    public Pile(String nom, String description) {

        this.nom = nom;
        this.description = description;
        H2Piles p = new H2Piles();
        p.ajouterPiles(this.nom,this.description);
    }

    public Pile() {

    }

    //éditer la pile
    public void editNom(String nom){
        this.nom=nom;
    }
    public void editDescription(String description){
        this.description=description;
    }

    // Ajouts de cartes dans la pile
    //utilisation de la bd
    public void addCarte(Carte c) {

        H2Contient cont = new H2Contient();
        this.pile.add(c);
    }

    //Suprression d'une carte dans la pile
    public void removeCarte(int i) {
        //utilisation de la bd
        this.pile.remove(i);
    }
    //Suppression de la pile
    public void removePile(Carte... c) {
        //utilisation de la bd
        for (Carte carte : c)
            this.pile.remove(carte);
    }

    //les getters
    public String getNom() { return this.nom; }
    public String getDescription() { return this.description; }
    public int getId() { return this.id; }
    public int getTaille() { return this.pile.size(); }
    public Carte getElement(int i) {return this.pile.get(i);}

}
