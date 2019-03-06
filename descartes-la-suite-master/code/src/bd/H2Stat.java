package bd;

import java.sql.*;
import java.util.ArrayList;

import static bd.GenererBD.*;

public class H2Stat {

    public void createH2Stat() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS STATISTIQUE" +
                    "(idStat INTEGER auto_increment PRIMARY KEY ," +
                    "nomPile VARCHAR2(30)," +
                    "reussi INTEGER," +
                    "rate1 INTEGER," +
                    "rate2 INTEGER," +
                    "rate3 INTEGER," +
                    "date DATE)";
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

    public void ajouterStat(String nom, int reussi, int rate1,int rate2,int rate3) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "INSERT INTO STATISTIQUE (nomPile,reussi,rate1,rate2,rate3,date) VALUES ('" + nom + "', " + reussi + ", " + rate1 + ","+rate2+", "+rate3+", CURRENT_DATE())";
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

    public ArrayList<String> getPiles() {
        Connection conn = null;
        Statement stmt = null;
        int j = 0;
        ArrayList<String> res = new ArrayList<String>();

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT nomPile FROM STATISTIQUE ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next() && j<15) {
                String nom = rs.getString("nomPile");
                res.add(nom);
                j++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res ;
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
        return res ;
    }

    public ArrayList<Integer> getReussiByNom(String nomPile) {
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,reussi FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next() && i<3) {
                Integer idStat = rs.getInt("idStat");
                Integer reussi = rs.getInt("reussi");

                res.add(reussi);
                i++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> getRate1ByNom(String nomPile) {
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,rate1 FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next() && i<3) {
                Integer idStat = rs.getInt("idStat");
                Integer rate = rs.getInt("rate1");

                res.add(rate);
                i++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> getRate2ByNom(String nomPile) {
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,rate2 FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next() && i<3) {
                Integer idStat = rs.getInt("idStat");
                Integer rate = rs.getInt("rate2");

                res.add(rate);
                i++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> getRate3ByNom(String nomPile) {
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,rate3 FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                Integer idStat = rs.getInt("idStat");
                Integer rate = rs.getInt("rate");

                res.add(rate);
                i++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Date> getDateByNom(String nomPile) {
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Date> res = new ArrayList<Date>();
        int i = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,nomPile, date FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next() && i<3) {
                Integer idStat = rs.getInt("idStat");
                String n = rs.getString("nomPile");
                Date date = rs.getDate("date");

                res.add(date);
                i++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> getPourcentageByNom(String nom){
        Connection conn = null;
        Statement stmt = null;
        int j = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT reussi,rate1,rate2,rate3 FROM STATISTIQUE WHERE nomPile='"+nom+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()&& j<3) {
                Integer r = rs.getInt("reussi");
                Integer r1 = rs.getInt("rate1");
                Integer r2 = rs.getInt("rate2");
                Integer r3 = rs.getInt("rate3");

                int i = (int)((double)r/(double)(r1+r2+r3+r));
                res.add(i);
                j++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> getPourcentage(){
        Connection conn = null;
        Statement stmt = null;
        int j = 0 ;
        ArrayList<Integer> res = new ArrayList<Integer>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT reussi,rate1,rate2,rate3 FROM STATISTIQUE ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next() && j<15) {
                Integer r = rs.getInt("reussi");
                Integer r1 = rs.getInt("rate1");
                Integer r2 = rs.getInt("rate2");
                Integer r3 = rs.getInt("rate3");

                int i = (int) (100.0*((double)r/(double)(r1+r2+r3+r)));
                res.add(i);
                j++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Date> getDates(){
        Connection conn = null;
        Statement stmt = null;
        int j = 0 ;
        ArrayList<Date> res = new ArrayList<Date>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT date FROM STATISTIQUE ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next() && j<15) {
                Date r = rs.getDate("date");

                res.add(r);
                j++;
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> get3Rate3ByNom(String nomPile,int i) {
        int iMoins1;
        if(i!=0){
            iMoins1=i-1;
        } else {
            iMoins1=0;
        }
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int j = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,rate3 FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                Integer idStat = rs.getInt("idStat");
                Integer rate = rs.getInt("rate3");
                j++;
                if(j>iMoins1 && j<=iMoins1+3)
                    res.add(rate);
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> get3Rate2ByNom(String nomPile,int i) {
        int iMoins1;
        if(i!=0){
            iMoins1=i-1;
        } else {
            iMoins1=0;
        }
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int j = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,rate2 FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                Integer idStat = rs.getInt("idStat");
                Integer rate = rs.getInt("rate2");
                j++;
                if(j>iMoins1 && j<=iMoins1+3)
                    res.add(rate);
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> get3Rate1ByNom(String nomPile,int i) {
        int iMoins1;
        if(i!=0){
            iMoins1=i-1;
        } else {
            iMoins1=0;
        }
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int j = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,rate1 FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                Integer idStat = rs.getInt("idStat");
                Integer rate = rs.getInt("rate1");
                j++;
                if(j>iMoins1 && j<=iMoins1+3)
                    res.add(rate);
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Integer> get3ReussiByNom(String nomPile,int i) {
        int iMoins1;
        if(i!=0){
            iMoins1=i-1;
        } else {
            iMoins1=0;
        }
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> res = new ArrayList<Integer>();
        int j = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT idStat,reussi FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                Integer idStat = rs.getInt("idStat");
                Integer rate = rs.getInt("reussi");

                j++;
                if(j>iMoins1 && j<=iMoins1+3) {
                    res.add(rate);
                }
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }

    public ArrayList<Date> get3DateByNom(String nomPile,int i) {
        int iMoins1;
        if(i!=0){
            iMoins1=i-1;
        } else {
            iMoins1=0;
        }
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Date> res = new ArrayList<Date>();
        int j = 0 ;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT date FROM STATISTIQUE WHERE nomPile='"+nomPile+"' ORDER BY idStat DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                Date rate = rs.getDate("date");
                j++;
                if(j>iMoins1 && j<=iMoins1+3)
                    res.add(rate);
            }
            // STEP 5: Clean-up environment
            rs.close();
            return res;
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
        return res;
    }
}





