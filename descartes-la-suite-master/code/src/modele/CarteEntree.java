package modele;

import bd.H2Cartes;


public class CarteEntree extends Carte{
    private int id; //comment l'utiliser ?? cf base de donnée
    private String question;
    private String reponse;
    private String theme;
    private String type="Classique";


    public CarteEntree(String question, String reponse, String theme){
        this.question=question;
        this.reponse=reponse;
        this.theme=theme;
        H2Cartes c = new H2Cartes();


    }

    public CarteEntree(){

        this.question="";
        this.reponse="";
    }

    public void ajouterCarteClassiqueBD(){
        H2Cartes c = new H2Cartes();
        c.ajouterCarteNormale(this.question,this.reponse,this.theme);
    }
    public void editQuestion(String question){
        this.question=question;
    }
    public void editReponse(String reponse){
        this.reponse=reponse;
    }
    public void editTheme(String theme) { this.theme = theme; }
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
        return "";
    }

    @Override
    public String getFausseReponse2() {
        return "";
    }

    @Override
    public String getFausseReponse3() {
        return "";
    }

    public void removeCarte(){
        //utilisation de la BD
    }


}
