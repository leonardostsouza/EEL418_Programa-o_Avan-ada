package com.eel418.trab2.DAO;

import com.eel418.trab2.DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import utils.*;

public class ReferenciasDAO extends BaseDAO{

    /**
     * creates new database entry
     *
     * @param ref
     */
    public Boolean create(ReferenciaBibliografica ref) {
        Boolean isSuccessful = true;
        try {
            conn = getConnection();
            // Check for duplicates
            PreparedStatement query0 = conn.prepareStatement(
                    "SELECT patrimonio FROM dadoscatalogo WHERE titulo = ? AND autoria = ?;");
            query0.setString(1, ref.getTitulo());
            query0.setString(2, ref.getAutoria());
            ResultSet result = query0.executeQuery();

            // if no duplicates are found
            if (!result.isBeforeFirst()) {
                result.close();

                // Insert entry in "referencias" table
                PreparedStatement query = conn.prepareStatement(
                        "INSERT INTO dadoscatalogo (titulo, autoria, veiculo, "
                                + "data_publicacao, nomearquivo, nomeoriginalarquivo)"
                                + " VALUES(?, ?, ?, ?, ?, ?);");
                query.setString(1, ref.getTitulo());
                query.setString(2, ref.getAutoria());
                query.setString(3, ref.getVeiculo());
                //java.util.Date today = new java.util.Date();
                query.setTimestamp(4, Timestamp.valueOf(ref.getDataPublicacao())); //Timestamp.valueOf(ref.getDataPublicacao()));
                query.setString(5, ref.getNomeArquivo());
                query.setString(6, ref.getNomeOriginalArquivo());
                query.executeUpdate();
                query.close();
                
                // get "patrimonio" attribute
                PreparedStatement getPatrimonio = conn.prepareStatement(
                    "SELECT patrimonio FROM dadoscatalogo WHERE titulo = ? AND autoria = ?;");
                getPatrimonio.setString(1, ref.getTitulo());
                getPatrimonio.setString(2, ref.getAutoria());
                ResultSet resultPatrimonio = getPatrimonio.executeQuery();
                resultPatrimonio.next();
                long refPatrimonio = resultPatrimonio.getLong("patrimonio");
                resultPatrimonio.close();

                // Insert entries in "palavras_chave" table
                List<String> keywords = Arrays.asList(ref.getPalchave().split(" "));
                for (String word : keywords) {
                    PreparedStatement query2 = conn.prepareStatement(
                            "INSERT INTO palavras_chave (palchave, patrimonio, palchavenormal) "
                                    + "VALUES (?, ?, ?);");
                    query2.setString(1, Utils.removeDiacriticals(word));
                    query2.setLong(2, refPatrimonio);
                    query2.setString(3, word);
                    query2.executeUpdate();
                    query2.close();
                }
                
                // Insert entries in "palavrasautorianormal" table
                List<String> autoria_words = Arrays.asList(ref.getAutoria().split(" "));
                for (String word : autoria_words) {
                    PreparedStatement query3 = conn.prepareStatement(
                            "INSERT INTO palavrasautorianormal "
                                    + "(palavra_autoria_normal, patrimonio) "
                                    + "VALUES (?, ?);");
                    query3.setString(1, Utils.removeDiacriticals(word));
                    query3.setLong(2, refPatrimonio);
                    query3.executeUpdate();
                    query3.close();
                }
                
                // Insert entries in "palavrastitulonormal" table
                List<String> titulo_words = Arrays.asList(ref.getTitulo().split(" "));
                for (String word : titulo_words) {
                    PreparedStatement query4 = conn.prepareStatement(
                            "INSERT INTO palavrastitulonormal "
                                    + "(palavra_titulo_normal, patrimonio) "
                                    + "VALUES (?, ?);");
                    query4.setString(1, Utils.removeDiacriticals(word));
                    query4.setLong(2, refPatrimonio);
                    query4.executeUpdate();
                    query4.close();
                }
                
                // Insert entries in "palavrasveiculonormal" table
                List<String> veiculo_words = Arrays.asList(ref.getVeiculo().split(" "));
                for (String word : veiculo_words) {
                    PreparedStatement query5 = conn.prepareStatement(
                            "INSERT INTO palavrasveiculonormal "
                                    + "(palavra_veiculo_normal, patrimonio) "
                                    + "VALUES (?, ?);");
                    query5.setString(1, Utils.removeDiacriticals(word));
                    query5.setLong(2, refPatrimonio);
                    query5.executeUpdate();
                    query5.close();
                }
                
            } else {
                result.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccessful = false;
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReferenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccessful;
    }

//------------------------------------------------------------------------------  
    /**
     * recover database entry using ID
     *
     * @param refId
     */
    public ReferenciaBibliografica readById(String refId) {
        ReferenciaBibliografica ref = new ReferenciaBibliografica();
        try {
            conn = getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "SELECT patrimonio, titulo, autoria, veiculo, "
                            + "data_publicacao, nomearquivo FROM dadoscatalogo "
                            + "WHERE patrimonio = ?");
            query.setLong(1, Long.parseLong(refId));
            ResultSet result = query.executeQuery();

            while (result.next()) {
                ref.setPatrimonio(result.getString("patrimonio"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
                ref.setVeiculo(result.getString("veiculo"));
                ref.setDataPublicacao(result.getString("data_publicacao"));
                ref.setNomeArquivo(result.getString("nomearquivo"));
            }
            result.close();
            query.close();
            
            PreparedStatement queryKeywords = conn.prepareStatement(
                    "SELECT palchavenormal FROM palavras_chave "
                            + "WHERE patrimonio = ?");
            
            queryKeywords.setLong(1, Long.parseLong(refId));
            ResultSet resultKeywords = queryKeywords.executeQuery();
            String keywords = "";
            
            while(resultKeywords.next()){
                keywords = keywords + resultKeywords.getString("palchavenormal") + " ";
            }
            
            ref.setPalchave(keywords);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReferenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ref;
    }
    
//------------------------------------------------------------------------------
    /**
     * Refovers Database entryes using titulo, autoria, veiculo 
     * and palavra-chave atributes
     *
     * @param jo
     */
    public ArrayList<ReferenciaBibliografica> readByAttributes(JsonObject jo){ //ReferenciaBibliografica ref){
        ArrayList<ReferenciaBibliografica> matches = new ArrayList<>();
        String statement = "SELECT dc.patrimonio, dc.titulo, "
                + "dc.autoria, dc.veiculo, "
                + "dc.data_publicacao, dc.nomearquivo, count(*) AS hits"
                + " FROM dadoscatalogo AS dc ";
        
        // Join tables for search
        if (!jo.getString("titulo").equals("")){
            statement += "INNER JOIN palavrastitulonormal AS pt "
                    + "ON dc.patrimonio = pt.patrimonio ";
        }
        
        if (!jo.getString("autoria").equals("")){
            statement += "INNER JOIN palavrasautorianormal AS pa "
                    + "ON dc.patrimonio = pa.patrimonio ";
        }
        
        if (!jo.getString("veiculo").equals("")){
            statement += "INNER JOIN palavrasveiculonormal AS pv "
                    + "ON dc.patrimonio = pv.patrimonio ";
        }
        
        if (!jo.getString("palchave").equals("")){
            statement += "INNER JOIN palavras_chave AS pc "
                    + "ON dc.patrimonio = pc.patrimonio ";
        }
        
        statement += "WHERE ";
        boolean isFilled = false;
        // separate search fields
        //titulo
        if ((!jo.getString("titulo").equals("")) && (!jo.getString("tituloOpt").equals("-"))){
            if(isFilled){
                statement += "AND ";
            } else {
                isFilled = true;
            }
            List<String> words = Arrays.asList(jo.getString("titulo").split(" "));
            statement += "(pt.palavra_titulo_normal"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";
             
            for (int i = 1; i < words.size(); i++) {
                statement += jo.getString("tituloOpt") + " " + "pt.palavra_titulo_normal"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";
            }
            statement += ") ";
        }
        
        //autoria
        if ((!jo.getString("autoria").equals("")) && (!jo.getString("autoriaOpt").equals("-"))){
            if(isFilled){
                statement += "AND ";
            } else {
                isFilled = true;
            }
            List<String> words = Arrays.asList(jo.getString("autoria").split(" "));
            statement += "(pa.palavra_autoria_normal"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";

            for (int i = 1; i < words.size(); i++) {
                statement += jo.getString("autoriaOpt") + " " + "pa.palavra_autoria_normal"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";;
            }
            statement += ") ";
        }
        
        // veiculo
        if ((!jo.getString("veiculo").equals("")) && (!jo.getString("veiculoOpt").equals("-"))){
            if(isFilled){
                statement += "AND ";
            } else {
                isFilled = true;
            }
            List<String> words = Arrays.asList(jo.getString("veiculo").split(" "));
            statement += "(pv.palavra_veiculo_normal"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";
            
            for (int i = 1; i < words.size(); i++) {
                statement += jo.getString("veiculoOpt") + " " + "pv.palavra_veiculo_normal"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";
            }
            statement += ") ";
        }
        
        // data_publicacao
        if (!(jo.getString("dataIni").equals(" 00:00:00.0")) && !(jo.getString("dataFim").equals(" 00:00:00.0"))){
            if(isFilled){
                statement += "AND ";
            } else {
                isFilled = true;
            }
            statement += "(dc.data_publicacao >= \'" + jo.getString("dataIni")
                    + "\' AND dc.data_publicacao <= \'" + jo.getString("dataFim") + "\') ";

        }
        //palavras_chave
        if (!jo.getString("palchave").equals("")){
            if(isFilled){
                statement += "AND ";
            } else {
                isFilled = true;
            }
            List<String> words = Arrays.asList(jo.getString("palchave").split(" "));
            statement += "(pc.palchave"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";

            for (int i = 1; i < words.size(); i++) {
                statement += "OR " + "pc.palchave"
                            + " LIKE \'%" + Utils.removeDiacriticals(words.get(0)) + "%\' ";
            }
            statement += ") ";
        }
        
        statement += "GROUP BY dc.patrimonio, dc.titulo, "
                + "dc.autoria ORDER BY hits DESC, titulo ASC;";
        
        // execute statement
        PreparedStatement query;
        try {
            conn = getConnection();
            query = conn.prepareStatement(statement);
            ResultSet result = query.executeQuery();
            
            
            while (result.next()) {
                ReferenciaBibliografica resultRef = new ReferenciaBibliografica();
                resultRef.setPatrimonio(result.getString("patrimonio"));
                resultRef.setTitulo(result.getString("titulo"));
                resultRef.setAutoria(result.getString("autoria"));
                resultRef.setVeiculo(result.getString("veiculo"));
                resultRef.setDataPublicacao(result.getString("data_publicacao"));
                resultRef.setNomeArquivo(result.getString("nomearquivo"));
                
                PreparedStatement queryKeywords = conn.prepareStatement(
                    "SELECT palchavenormal FROM palavras_chave "
                            + "WHERE patrimonio = ?");
            
                queryKeywords.setLong(1, Long.parseLong(result.getString("patrimonio")));
                ResultSet resultKeywords = queryKeywords.executeQuery();
                String keywords = "";

                while(resultKeywords.next()){
                    keywords = keywords + resultKeywords.getString("palchavenormal") + " ";
                }

                resultRef.setPalchave(keywords);
                
                matches.add(resultRef);
            }
            
            result.close();
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReferenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReferenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return matches;
    }

//------------------------------------------------------------------------------
    /**
     * edits database entry
     *
     * @param ref
     */
    public Boolean update(ReferenciaBibliografica ref) {
        Boolean isSuccessful = true;
        try {
            conn = getConnection();

            delete(ref.getPatrimonio());
            create(ref);
        } catch (Exception e) {
            e.printStackTrace();
            isSuccessful = false;
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReferenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccessful;
    }
//------------------------------------------------------------------------------
    /**
     * deletes database entry
     *
     * @param ref
     */
    public Boolean delete(String refId) {
        Boolean isSuccessful = true;
        try {
            conn = getConnection();

            // delete from "dadoscatalogo" table
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM dadoscatalogo WHERE patrimonio = ?;");
            query.setLong(1, Long.parseLong(refId));
            query.executeUpdate();
            query.close();

            // delete from "palavras_chave" table
            PreparedStatement query2 = conn.prepareStatement(
                    "DELETE FROM palavras_chave WHERE patrimonio = ?;");
            query2.setLong(1, Long.parseLong(refId));
            query2.executeUpdate();
            query2.close();
            
            // delete from "palavrasautorianormal" table
            PreparedStatement query3 = conn.prepareStatement(
                    "DELETE FROM palavrasautorianormal WHERE patrimonio = ?;");
            query3.setLong(1, Long.parseLong(refId));
            query3.executeUpdate();
            query3.close();
            
            // delete from "palavrastitulonormal" table
            PreparedStatement query4 = conn.prepareStatement(
                    "DELETE FROM palavrastitulonormal WHERE patrimonio = ?;");
            query4.setLong(1, Long.parseLong(refId));
            query4.executeUpdate();
            query4.close();
            
            // delete from "palavrasveiculonormal" table
            PreparedStatement query5 = conn.prepareStatement(
                    "DELETE FROM palavrasveiculonormal WHERE patrimonio = ?;");
            query5.setLong(1, Long.parseLong(refId));
            query5.executeUpdate();
            query5.close();

        } catch (Exception e) {
            e.printStackTrace();
            isSuccessful = false;
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReferenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccessful;
    }
}
        