package modele;

public abstract class Carte {
    protected int id; //comment l'utiliser ?? cf base de donnée
    protected String question;
    protected String reponse;
    protected String theme;
    protected String type;

    abstract void editQuestion(String question);
    abstract void editReponse(String reponse);
    abstract void editTheme(String theme);
    public abstract String getQuestion();
    public abstract String getReponse();
    public abstract String getTheme();
    public abstract String getType();
    public abstract int getId();

    public abstract String getFausseReponse1();
    public abstract String getFausseReponse2();
    public abstract String getFausseReponse3();
    abstract void removeCarte(); //utilisation de la BD
}