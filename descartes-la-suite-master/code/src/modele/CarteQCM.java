package modele;

import bd.H2Cartes;


public class CarteQCM extends Carte{

    private int id; //comment l'utiliser ?? cf base de donnée
    private String question;
    private String reponse;
    private String fausseReponse1;
    private String fausseReponse2;
    private String fausseReponse3;
    private String theme;
    private String type = "QCM";

    public CarteQCM(String question, String reponse, String fausseReponse1, String fausseReponse2, String fausseReponse3, String theme){
        this.question=question;
        this.reponse=reponse;
        this.fausseReponse1=fausseReponse1;
        this.fausseReponse2=fausseReponse2;
        this.fausseReponse3=fausseReponse3;
        this.theme=theme;

    }
    public CarteQCM(){

        this.question="";
        this.reponse="";
    }

    public void ajouterCarteQCMBD(){
        H2Cartes c = new H2Cartes();
        c.ajouterCarteQCM(this.question,this.reponse,this.theme,true,this.fausseReponse1,this.fausseReponse2,this.fausseReponse3);
    }

    public void editQuestion(String question){
    this.question=question;
}
    public void editReponse(String reponse){
    this.reponse=reponse;
}
    public void editFausseReponse1(String rep){ this.fausseReponse1=rep; }
    public void editFausseReponse2(String rep){ this.fausseReponse2=rep; }
    public void editFausseReponse3(String rep){ this.fausseReponse3=rep; }
    public void editTheme(String theme) { this.theme = theme; }

    public String getQuestion(){ return this.question; }
    public String getReponse(){
    return this.reponse;
}
    public String getFausseReponse1() { return fausseReponse1; }
    public String getFausseReponse2() { return fausseReponse2; }
    public String getFausseReponse3() { return fausseReponse3; }
    public String getTheme() { return this.theme; }
    public String getType() { return type; }

    public int getId(){ return this.id; }
    public void removeCarte(){
        //utilisation de la BD
    }
}
