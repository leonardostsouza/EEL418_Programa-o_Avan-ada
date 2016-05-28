/*
 * Este código faz parte do primeiro trabalho desenvolvido para o curso 
 * EEL 418 - Programação Avançada, ministrado no primeiro semestre
 * de 2016 pelo professor Jorge Lopes de Souza Leão
 */
package com.eel418.trab1.dao;

/**
 * @author Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 * Engenharia de Computação e Informação
 * Universidade Federal do Rio de Janeiro
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.eel418.trab1.model.Referencias;
import com.eel418.trab1.utils.DBUtils;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReferenciasDAO {
    private Connection conn;
    
    public ReferenciasDAO(){
        conn = null;
    }

    /** creates new database entry */
    public void create(Referencias ref){
        try{
            conn = DBUtils.getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "INSERT INTO referencias (titulo, autoria) VALUES(?, ?);");
            query.setString(1, ref.getTitulo());
            query.setString(2, ref.getAutoria());
            
            query.executeUpdate();
            query.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /** reads database entry by ID */
    public Referencias readById(int refId) {
        Referencias ref = new Referencias();
        try{
            conn = DBUtils.getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * FROM referencias "
                            + "WHERE serialno = ?");
            query.setInt(1, refId);
            ResultSet result = query.executeQuery();
            
            while(result.next()){
                ref.setSerialno(result.getInt("serialno"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
            }
            result.close();
            query.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ref;
    }
    
    /** reads database entry by title */
    public List<Referencias> readByTitulo(String titulo) {
        List<Referencias> refList = new ArrayList<Referencias>();
        try{
            conn = DBUtils.getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * FROM referencias "
                            + "WHERE titulo = ?");
            query.setString(1, titulo);
            ResultSet result = query.executeQuery();
            
            while(result.next()) {
                Referencias ref = new Referencias();
                ref.setSerialno(result.getInt("serialno"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
                refList.add(ref);
            }
            result.close();
            query.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return refList;
    }
    
    /** reads database entry by title */
    public List<Referencias> readByAutoria(String autor) {
        List<Referencias> refList = new ArrayList<Referencias>();
        try{
            conn = DBUtils.getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * FROM referencias "
                            + "WHERE autoria = ?");
            query.setString(1, autor);
            ResultSet result = query.executeQuery();
            
            while(result.next()) {
                Referencias ref = new Referencias();
                ref.setSerialno(result.getInt("serialno"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
                refList.add(ref);
            }
            result.close();
            query.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return refList;
    }
    
    /** edits database entry */
    public void update(Referencias ref) {
        try{
            conn = DBUtils.getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "UPDATE referencias SET titulo = ?, autoria = ? "
                            + "WHERE serialno = ?;");
            query.setString(1, ref.getTitulo());
            query.setString(2, ref.getAutoria());
            query.setInt(3, ref.getSerialno());
            query.executeUpdate();
            query.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /** deletes database entry */
    public void delete(int refId) {
        try{
            conn = DBUtils.getConnection();            
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM referencias WHERE serialno = ?;");
            query.setInt(1, refId);
            query.executeUpdate();
            query.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /** shows all database entries */
    public List<Referencias> getAll() {
        List<Referencias> references = new ArrayList<Referencias>();
        try{
            conn = DBUtils.getConnection();            
            Statement query = conn.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM referencias ");
            while(result.next()) {
                Referencias ref = new Referencias();
                ref.setSerialno(result.getInt("serialno"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
                references.add(ref);
            }
            result.close();
            query.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return references;
    }
    
}
