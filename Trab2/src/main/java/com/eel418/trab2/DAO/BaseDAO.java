package com.eel418.trab2.DAO;

import java.sql.Connection;
//import java.sql.DriverManager;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {
    
    private DataSource ds;
    protected Connection conn;
    
//------------------------------------------------------------------------------------------------------------
    public BaseDAO() {
        try {
            InitialContext cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:comp/env/jdbc/bibliopdf");
        } catch (Exception e) {
            System.out.println("[BaseDAO.constructor] Excessão: " + e.getMessage());
        }
    }
//------------------------------------------------------------------------------------------------------------
    public Connection getConnection(){
        try{
            if(ds!=null){
                return ds.getConnection();
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
//------------------------------------------------------------------------------------------------------------
    /*
    private static final String USER = "postgres";
    private static final String PASSWORD = "abracadabra";
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DATABASE = "bibliopdf";
    
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
    
    protected static Connection conn;
    
    public static Connection getConnection(){
        if (conn == null){
            try{
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return conn;
    }
    
    public static void closeConnection(Connection conn){
        if(conn != null){
            try{
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }*/
}
