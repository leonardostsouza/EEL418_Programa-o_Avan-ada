/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package com.eel418.trab1.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author 
 * Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 * Engenharia de Computação e Informação
 * Universidade Federal do Rio de Janeiro
 */
public class DBUtils {
    private static Connection conn;
    
    public static Connection getConnection(){
        if (conn == null){
            InputStream is = DBUtils.class.getClassLoader().getResourceAsStream( "/db.properties" );
            Properties props = new Properties();
            
            try{
                props.load(is);
                String driver = props.getProperty("DRIVER");
                String user = props.getProperty("USER");
                String pwd = props.getProperty("PASSWORD");
                String host = props.getProperty("HOST");
                String port = props.getProperty("PORT");
                String db = props.getProperty("DATABASE");
                String url = driver + "://" + host + ":" + port + "/" + db;
                
                conn = DriverManager.getConnection(url, user, pwd);
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
    }
}
