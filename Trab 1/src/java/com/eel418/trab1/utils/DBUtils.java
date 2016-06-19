/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package com.eel418.trab1.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 
 * Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 * Engenharia de Computação e Informação
 * Universidade Federal do Rio de Janeiro
 */
public class DBUtils {
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DATABASE = "referenciasbibliograficas";
    
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
    
    private static Connection conn;
    
    public static Connection getConnection(){
        if (conn == null){
            try{
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
    }
}
