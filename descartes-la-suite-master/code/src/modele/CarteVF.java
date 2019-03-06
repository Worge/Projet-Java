package modele;

import bd.H2Cartes;


public class CarteVF extends Carte{
    private int id; //comment l'utiliser ?? cf base de donnée
    private String question;
    private String reponse; //Vrai ou Faux
    private String theme;
    public String type = "VF";

    public CarteVF(String question, String reponse, String theme){
        this.question=question;
        this.reponse=reponse;
        this.theme=theme;

    }
    public CarteVF(){

        this.question="";
        this.reponse="";
    }

    public void ajouterCarteVFBD(){
        H2Cartes c = new H2Cartes();
        c.ajouterCarteVf(this.question,this.reponse,this.theme,true);
    }
    public void editQuestion(String question){
        this.question=question;
    }
    public void editReponse(String reponse){
        this.reponse=reponse;
    }
    public void editTheme(String reponse) { this.theme = theme; }

    public String getQuestion(){ return this.question; }
    public String getReponse(){
        return this.reponse;
    }
    public String getTheme() { return this.theme; }
    public String getType() { return type; }

    public int getId(){
        return this.id;
    }

    @Override
    public String getFausseReponse1() {
        return null;
    }

    @Override
    public String getFausseReponse2() {
        return null;
    }

    @Override
    public String getFausseReponse3() {
        return null;
    }

    public void removeCarte(){
        //utilisation de la BD
    }
}
