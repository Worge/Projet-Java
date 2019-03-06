package bd;

import controlleur.creation.ControllerCreateurCartes;

import java.sql.*;
import java.util.ArrayList;

import static bd.GenererBD.*;
import static java.lang.Boolean.TRUE;

public class H2Cartes {



    public void createTableGlobale(){
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS CARTE" +
                    "(question VARCHAR2(255) PRIMARY KEY," +
                    "reponse VARCHAR2(100)," +
                    "theme VARCHAR2(16)," +
                    "qcm BOOLEAN DEFAULT FALSE," +
                    "vf BOOLEAN DEFAULT FALSE,"+
                    "repfausse1 VARCHAR2(100),"+
                    "repfausse2 VARCHAR2(100),"+
                    "repfausse3 VARCHAR2(100),"+
                    "chemin VARCHAR2(100))";
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

    public void ajouterCarteDeBase(){
        Connection conn = null;
        Statement stmt = null;

        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO CARTE (question,reponse,theme) " + "VALUES ('En quelle année a été couronné Charlemagne ?', '800','Histoire')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO CARTE (question,reponse,theme) " + "VALUES ('Quand s''est terminée la Première Guerre mondiale ? (jj/mm/aaaa)', '11/11/1918','Histoire')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO CARTE (question,reponse,theme) " + "VALUES ('Quelle est la date de la Prise de la Bastille ?  (jj/mm/aaaa)', '14/07/1789', 'Histoire')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO CARTE (question,reponse,theme) " + "VALUES ('Quel roi est mort pendant la Révolution Française ?', 'Louis XVI','Histoire')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO CARTE (question,reponse,theme) " + "VALUES ('Quel président de la République française a succédé à François Mitterand ?', 'Jacques Chirac','Histoire')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO CARTE (question,reponse,theme) " + "VALUES ('En quelle année a commencé la Cinquième République ?', '1958', 'Histoire')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO CARTE (question,reponse,theme,vf) " + "VALUES ('Le Portugal était du cöté de l''Axe pendant la Première Guerre Mondiale', 'Faux','Histoire', true)";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO CARTE (question,reponse,theme,qcm,repfausse1,repfausse2,repfausse3) "+"VALUES ('Quel écrivain a écrit Notre-Dame de Paris ?', 'Victor Hugo','Français', true , 'Honoré de Blazac','Molière','Gustave Flaubert')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quelle est la capitale de la Pologne ?', 'Varsovie','Géographie')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quelle est la capitale du Portugal ?', 'Lisbonne','Géographie')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quelle est la capitale de la Russie ?', 'Moscou','Géographie')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quelle est la capitale de l''Espagne ?', 'Madrid','Géographie')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quelle est la capitale de la Lituanie ?', 'Vilnius','Géographie')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quelle est la capitale de la Suisse ?', 'Berne','Géographie')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Qui a décrit la caverne comme un obstacle à la vérité ?' , 'Platon','Autres')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quel philosophe a déclaré : ’’Je pense donc je suis’’ ?', 'Descartes','Autres')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Aristote disait : ’’L''homme est un ... politique ’’. (à compléter)', 'animal','Autres')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quel célèbre philosophe a écrit l''ouvrage : Critique de la raison pure ?', 'Kant','Autres')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Qui était l''élève de Socrate ?', 'Platon','Autres')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO CARTE (question,reponse,theme) "+"VALUES ('Quel célèbre philosophe a écrit l''ouvrage : Discours de la méthode ?', 'Descartes','Autres')";
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
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

    public ArrayList<String> visualiserCartes(){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<String> res = new ArrayList<String>();

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM CARTE";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String question = rs.getString("question");
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

    public boolean isInCarte(String nom){
        Connection conn = null;
        Statement stmt = null;

        String res = new String();

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            if(nom.contains("\'")) {
                nom = nom.replace("\'","\'\'");
            }
            String sql = "SELECT 1 FROM CARTE WHERE question='"+nom+"'";
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

    public void ajouterCarteQCM(String quest, String rep, String them, Boolean q,String rp1,String rp2,String rp3 ){

        if(rep.contains("\'") ) {
            rep = rep.replace("\'","\'\'");
        }

        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
        }

        if(rp1.contains("\'")) {
            rp1 = rp1.replace("\'","\'\'");
        }

        if(rp2.contains("\'")) {
            rp2 = rp2.replace("\'","\'\'");
        }

        if(rp3.contains("\'")) {
            rp3 = rp3.replace("\'","\'\'");
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
            String sql = "INSERT INTO CARTE (question,reponse,theme,qcm,repfausse1,repfausse2,repfausse3) " + "VALUES ('"+quest+"', '"+rep+"', '"+them+"', "+q+",'"+rp1+"' ,'"+rp2+"', '"+rp3+"')";
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

    public void ajouterCarteNormale(String quest, String rep, String them ){
        if(rep.contains("\'") ) {
            rep = rep.replace("\'","\'\'");
        }

        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
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
            String sql = "INSERT INTO CARTE (question,reponse,theme) " + "VALUES ('"+quest+"', '"+rep+"', '"+them+"')";
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

    public void ajouterCarteVf(String quest, String rep, String them, Boolean vf ){
        if(rep.contains("\'") ) {
            rep = rep.replace("\'","\'\'");
        }

        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
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
            String sql = "INSERT INTO CARTE (question,reponse,theme,vf) " + "VALUES ('"+quest+"', '"+rep+"', '"+them+"', "+vf+")";
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


    public String getRepCarteByQuest(String quest){
        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT reponse FROM CARTE WHERE question='"+quest+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String reponse  = rs.getString("reponse");
                return reponse ;
            }

            // STEP 5: Clean-up environment
            rs.close();
            return "" ;
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
        return "";
    }

    public String getThemCarteByQuest(String quest){
        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT theme FROM CARTE WHERE question='"+quest+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String theme  = rs.getString("theme");
                return theme ;
            }

            // STEP 5: Clean-up environment
            rs.close();
            return "" ;
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
        return "";
    }

    public String getTypeCarteByQuest(String quest){
        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT qcm,vf FROM CARTE WHERE question='"+quest+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                Boolean qcm  = rs.getBoolean("qcm");
                Boolean vf = rs.getBoolean("vf");

                if (qcm)
                    return "QCM";
                if (vf)
                    return "V/F";
                else
                    return "Classique";
            }

            // STEP 5: Clean-up environment
            rs.close();
            return "" ;
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
        return "";
    }

    public String getRep1ByQuest(String quest){
        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT repfausse1 FROM CARTE WHERE question='"+quest+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String rp1  = rs.getString("repfausse1");
                return rp1;
            }

            // STEP 5: Clean-up environment
            rs.close();
            return "" ;
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
        return "";
    }

    public String getRep2ByQuest(String quest){
        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT repfausse2 FROM CARTE WHERE question='"+quest+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String rp2  = rs.getString("repfausse2");
                return rp2;
            }

            // STEP 5: Clean-up environment
            rs.close();
            return "" ;
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
        return "";
    }

    public String getRep3ByQuest(String quest){
        if(quest.contains("\'")) {
            quest = quest.replace("\'","\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT repfausse3 FROM CARTE WHERE question='"+quest+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String rp3  = rs.getString("repfausse3");
                return rp3;
            }

            // STEP 5: Clean-up environment
            rs.close();
            return "" ;
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
        return "";
    }


    public int getTypeCarteById(int id){
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT qcm,vf FROM CARTE WHERE idCarte="+id;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String qcm  = rs.getString("qcm");
                String vf = rs.getString("vf");
                if (qcm.equals("TRUE")){
                    return 2;
                }
                if (vf.equals("TRUE")){
                    return 3;
                }
                else
                    return 1 ;
            }

            // STEP 5: Clean-up environment
            rs.close();
            return 0 ;
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
        return 0;
    }

    public ArrayList<String> getQuestionByTheme(String them){
        Connection conn = null;
        Statement stmt = null;

        ArrayList<String> res = new ArrayList<String>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT question FROM CARTE WHERE theme='"+them+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String quest  = rs.getString("question");
                res.add(quest);
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
        return res ;
    }



    public ArrayList<String> getQuestionByType(String type){
        Connection conn = null;
        Statement stmt = null;

        ArrayList<String> res = new ArrayList<String>();

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            if (type.equals("QCM")) {
                String sql = "SELECT question FROM CARTE WHERE qcm=true";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    // Retrieve by column name
                    String quest  = rs.getString("question");
                    res.add(quest);
                }
                rs.close();
            }

            if(type.equals("V/F")){
                String sql = "SELECT question FROM CARTE WHERE vf=true";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    // Retrieve by column name
                    String quest  = rs.getString("question");
                    res.add(quest);
                }
                rs.close();
            }

            if(type.equals("Classique")){
                String sql = "SELECT question FROM CARTE WHERE vf=false AND qcm=false";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    // Retrieve by column name
                    String quest  = rs.getString("question");
                    res.add(quest);
                }
                rs.close();
            }
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
        return res ;
    }

    public void editCarteQuestion(String quest, String newQuest){
        if(newQuest.contains("\'") ) {
            newQuest = newQuest.replace("\'","\'\'");
        }

        if(quest.contains("\'") ) {
            quest = quest.replace("\'","\'\'");
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
            String sql = "UPDATE CARTE SET question='"+newQuest+"' WHERE question ='"+quest+"'";
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

    public void editRep1(String quest, String newRep){
        if(newRep.contains("\'") ) {
            newRep = newRep.replace("\'","\'\'");
        }

        if(quest.contains("\'") ) {
            quest = quest.replace("\'","\'\'");
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
            String sql = "UPDATE CARTE SET repfausse1='"+newRep+"' WHERE question ='"+quest+"'";
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

    public void editRep2(String quest, String newRep){
        if(newRep.contains("\'") ) {
            newRep = newRep.replace("\'","\'\'");
        }

        if(quest.contains("\'") ) {
            quest = quest.replace("\'","\'\'");
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
            String sql = "UPDATE CARTE SET repfausse2='"+newRep+"' WHERE question ='"+quest+"'";
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

    public void editRep3(String quest, String newRep){
        if(newRep.contains("\'") ) {
            newRep = newRep.replace("\'","\'\'");
        }

        if(quest.contains("\'") ) {
            quest = quest.replace("\'","\'\'");
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
            String sql = "UPDATE CARTE SET repfausse3='"+newRep+"' WHERE question ='"+quest+"'";
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

    public void editCarteReponse(String quest, String newRep){
        if(newRep.contains("\'") ) {
            newRep = newRep.replace("\'","\'\'");
        }

        if(quest.contains("\'") ) {
            quest = quest.replace("\'","\'\'");
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
            String sql = "UPDATE CARTE SET reponse='"+newRep+"' WHERE question ='"+quest+"'";
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

    public void editCarteType(String quest, String newType) {
        if (quest.contains("\'")) {
            quest = quest.replace("\'", "\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            if (newType.equals("QCM")) {
                String sql = "UPDATE CARTE SET qcm=TRUE AND vf=FALSE WHERE question ='" + quest + "'";
                stmt.executeUpdate(sql);
            }

            if (newType.equals("Vrai/Faux")) {
                String sql = "UPDATE CARTE SET vf=TRUE AND qcm=FALSE WHERE question ='" + quest + "'";
                stmt.executeUpdate(sql);
            } else {
                String sql = "UPDATE CARTE SET vf=FALSE AND qcm=FALSE WHERE question ='" + quest + "'";
                stmt.executeUpdate(sql);
            }

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

    public void editCarteTheme(String quest, String newTheme) {
        if (quest.contains("\'")) {
            quest = quest.replace("\'", "\'\'");
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "UPDATE CARTE SET theme='"+newTheme+"' WHERE question ='"+quest+"'";
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
            String sql = "SELECT 1 FROM CARTE LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int i = rs.getInt(1);
                return false ;
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

    public void supprimerCarte(String quest){
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
            String sql = "DELETE FROM CARTE WHERE question='"+quest+"'";
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

        //refaire pareil pour contient

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
}






