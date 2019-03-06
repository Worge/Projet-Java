package bd;

import java.sql.*;
import java.util.ArrayList;

import static bd.GenererBD.*;

public class H2Piles {


    public void createTablePile() {

        H2Cartes cartes = new H2Cartes();

        Connection conn = null;
        Statement stmt = null;

        String sid = new String();

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS PILE" +
                    "(nom VARCHAR2(30) PRIMARY KEY," +
                    "description VARCHAR2(100))";
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
    
    public void ajouterPileDeBase(){
        Connection conn = null;
        Statement stmt = null;

        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO PILE (nom,description) " + "VALUES ('Capitales européennes','Pour devenir incollable sur la géographie de l''Europe !')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO PILE (nom,description) " + "VALUES ('Philosophie','Pour tout connaitre sur Descartes et sa clique !')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO PILE (nom,description) " + "VALUES ('Culture G.','Pour briller en soirée !')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PILE (nom,description) " + "VALUES ('Histoire de France','Pour bien connaitre l''Histoire de notre pays')";
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

    public void ajouterPiles(String nom,String desc){
        if(nom.contains("\'") ) {
            nom = nom.replace("\'","\'\'");
        }

        if(desc.contains("\'")) {
            desc = desc.replace("\'","\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO PILE (nom,description) " + "VALUES ('"+nom+"', '"+desc+"')";
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

    public ArrayList<String> visualiserPile(){
        Connection conn = null;
        Statement stmt = null;

        ArrayList<String> res = new ArrayList<String>();

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT nom FROM PILE";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name

                String nom = rs.getString("nom");
                res.add(nom);
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res ;
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

    public String getDescriptionByNom(String nom){
        Connection conn = null;
        Statement stmt = null;

        String res = new String();

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT description FROM PILE WHERE nom='"+nom+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name

                res = rs.getString("description");

            }
            // STEP 5: Clean-up environment
            rs.close();
            return res ;
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

    public boolean isInPile(String nom){
        Connection conn = null;
        Statement stmt = null;

        String res = new String();

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT 1 FROM PILE WHERE nom='"+nom+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                return true;

            }
            // STEP 5: Clean-up environment
            rs.close();
            return false ;
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
        return false;

    }

    public void supprimerPile(String nom){
        Connection conn = null ;
        Statement stmt = null ;

        if (nom.contains("\'")) {
            nom = nom.replace("\'", "\'\'");
        }

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "DELETE FROM PILE WHERE nom='"+nom+"'";
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

        //Puis on fait de même pour contient

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "DELETE FROM CONTIENT WHERE nomPile='"+nom+"'";
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

    public int getNbCarte(String nomPile){
        Connection conn = null ;
        Statement stmt = null ;

        if (nomPile.contains("\'")) {
            nomPile = nomPile.replace("\'", "\'\'");
        }

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) as total FROM CONTIENT WHERE nomPile='"+nomPile+"'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int i = rs.getInt("total");
                return i;
            }

            stmt.close();
            conn.close();
            return 0;
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
        return 0;
    }

    public boolean isEmpty(){
        Connection conn = null ;
        Statement stmt = null ;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT 1 FROM PILE LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int i = rs.getInt(1);
                return false;
            }

            stmt.close();
            conn.close();
            return true;
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
        return false;
    }
}
