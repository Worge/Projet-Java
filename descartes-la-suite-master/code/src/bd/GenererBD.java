package bd;

public class GenererBD {


    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/.descartes/data;";

    static final String USER = "valRoul";
    static final String PASS = "71ceKret";

    public void iniateBD(){

        H2Cartes cartes = new H2Cartes();
        cartes.createTableGlobale();

        H2Piles piles = new H2Piles();
        piles.createTablePile();
        
        H2Contient contient = new H2Contient();
        contient.createTableContient();

        H2Stat stat = new H2Stat();
        stat.createH2Stat();

        if (cartes.isEmpty()){
            cartes.ajouterCarteDeBase();

        }
        if (piles.isEmpty()) {
            piles.ajouterPileDeBase();
            contient.ajouterCPB();
        }

    }
}
