package bd;

import java.sql.*;
import java.util.ArrayList;

import static bd.GenererBD.*;

public class H2Contient {


    public void createTableContient(){

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS CONTIENT" +
                    "(question VARCHAR2(255)," +
                    "nomPile VARCHAR(30)," +
                    "CONSTRAINT Pk_contient PRIMARY KEY (question, nomPile))";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    public ArrayList<String> visualiserCont(){Connection conn = null;
        Statement stmt = null;

        ArrayList<String> res = new ArrayList<String>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM CONTIENT";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String question  = rs.getString("question");
                String nom = rs.getString("nomPile");
                res.add(question);


            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return res;
    }

    public void ajouterCartePile(String question,String nom){

        Connection conn = null;
        Statement stmt = null;

        if(question.contains("\'") ) {
            question = question.replace("\'","\'\'");
        }
        if(nom.contains("\'") ) {
            nom = nom.replace("\'","\'\'");
        }

        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO CONTIENT (question,nomPile) " + "VALUES ('"+question+"', '"+nom+"')";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }

    public void editCartePile(String question,String newQuestion){

        Connection conn = null;
        Statement stmt = null;

        if(question.contains("\'") ) {
            question = question.replace("\'","\'\'");
        }
        if(newQuestion.contains("\'") ) {
            newQuestion = newQuestion.replace("\'","\'\'");
        }

        try{

            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "UPDATE CONTIENT SET question='"+newQuestion+"' WHERE question ='"+question+"'";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }

    public ArrayList<String> getCarteDePile(String nom){

        Connection conn = null;
        Statement stmt = null;

        ArrayList<String> res = new ArrayList<String>();
        if(nom.contains("\'") ) {
            nom = nom.replace("\'","\'\'");
        }

        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT question FROM CONTIENT WHERE nomPile='"+nom+"'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                String q = rs.getString("question");
                res.add(q);
            }

            stmt.close();
            conn.close();
            return res;
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return res;
    }

    public void supprimerCarteDePile(String quest){
        Connection conn = null ;
        Statement stmt = null ;

        if (quest.contains("\'")) {
            quest = quest.replace("\'", "\'\'");
        }

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "DELETE FROM CONTIENT WHERE question='"+quest+"'";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

    }

    public void ajouterCPB(){
        Connection conn = null ;
        Statement stmt = null ;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO CONTIENT VALUES ('Quelle est la capitale de la Pologne ?','Capitales européennes')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quelle est la capitale de la Russie ?','Capitales européennes')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quelle est la capitale de la Lituanie ?','Capitales européennes')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quelle est la capitale de la Suisse ?','Capitales européennes')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quelle est la capitale du Portugal ?','Capitales européennes')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Qui a décrit la caverne comme un obstacle à la vérité ?','Philosophie')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quel philosophe a déclaré : ’’Je pense donc je suis’’ ?','Philosophie')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Aristote disait : ’’L''homme est un ... politique ’’. (à compléter)','Philosophie')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quel célèbre philosophe a écrit l''ouvrage : Critique de la raison pure ?','Philosophie')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Qui était l''élève de Socrate ?','Philosophie')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quel célèbre philosophe a écrit l''ouvrage : Discours de la méthode ?','Philosophie')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('En quelle année a été couronné Charlemagne ?','Histoire de France')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quand s''est terminée la Première Guerre mondiale ? (jj/mm/aaaa)','Histoire de France')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quelle est la date de la Prise de la Bastille ?  (jj/mm/aaaa)','Histoire de France')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quel roi est mort pendant la Révolution Française ?','Histoire de France')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('Quel président de la République française a succédé à François Mitterand ?','Histoire de France')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTIENT VALUES ('En quelle année a commencé la Cinquième République ?','Histoire de France')";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

    }

}
