/*
 * Este código faz parte do primeiro trabalho desenvolvido para o curso 
 * EEL 418 - Programação Avançada, ministrado no primeiro semestre
 * de 2016 pelo professor Jorge Lopes de Souza Leão
 */
package com.eel418.trab1.dao;

/**
 * @author Leonardo dos Santos Teixeira de Souza DRE: 112086681 Engenharia de
 * Computação e Informação Universidade Federal do Rio de Janeiro
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.eel418.trab1.model.Referencias;
import com.eel418.trab1.utils.DBUtils;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class Tuple implements Comparable<Tuple> {

    public Tuple() {
        serialno = 0;
        count = 0;
    }

    @Override
    public int compareTo(Tuple otherTuple) {
        return this.count.compareTo(otherTuple.count);
    }

    public int serialno;
    public Integer count;
}

public class ReferenciasDAO {

    private Connection conn;

    public ReferenciasDAO() {
        conn = null;
    }

    /**
     * creates new database entry
     *
     * @param ref
     */
    public void create(Referencias ref) {
        try {
            conn = DBUtils.getConnection();
            // Check for duplicates
            PreparedStatement query0 = conn.prepareStatement(
                    "SELECT serialno FROM referencias WHERE titulo = ? AND autoria = ?;");
            query0.setString(1, ref.getTitulo());
            query0.setString(2, ref.getAutoria());
            ResultSet result = query0.executeQuery();

            // if no duplicates are found
            if (!result.isBeforeFirst()) {
                result.close();

                // Insert entry in "referencias" table
                PreparedStatement query = conn.prepareStatement(
                        "INSERT INTO referencias (titulo, autoria) VALUES(?, ?);");
                query.setString(1, ref.getTitulo());
                query.setString(2, ref.getAutoria());
                query.executeUpdate();
                query.close();

                // Insert entries in "palavrasdotitulo" table
                List<String> words = Arrays.asList(ref.getTitulo().split(" "));
                for (String word : words) {
                    PreparedStatement query2 = conn.prepareStatement(
                            "INSERT INTO palavrasdotitulo (palavra, serialno_referencias) "
                                    + "VALUES (?, (SELECT serialno FROM referencias "
                                    + "WHERE titulo = ? AND autoria = ?));");
                    query2.setString(1, word.toUpperCase());
                    query2.setString(2, ref.getTitulo());
                    query2.setString(3, ref.getAutoria());
                    query2.executeUpdate();
                    query2.close();
                }
            } else {
                result.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * reads database entry by ID
     *
     * @param refId
     * @return
     */
    public Referencias readById(int refId) {
        Referencias ref = new Referencias();
        try {
            conn = DBUtils.getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * FROM referencias "
                    + "WHERE serialno = ?");
            query.setInt(1, refId);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                ref.setSerialno(result.getInt("serialno"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
            }
            result.close();
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref;
    }

    /**
     * reads database entry by title
     *
     * @param titulo
     * @return
     */
    public List<Referencias> readByTitulo(String titulo) {
        Tuple tuple;
        ReferenciasDAO refDao = new ReferenciasDAO();
        List<Referencias> refList = new ArrayList<>();
        List<Tuple> tupleList = new ArrayList<>();

        try {
            List<String> words = Arrays.asList(titulo.split(" ")); //separate words to use in search
            conn = DBUtils.getConnection();

            // foreach word
            for (String word : words) {
                // run query 
                PreparedStatement query = conn.prepareStatement(
                        "SELECT serialno_referencias FROM palavrasdotitulo "
                                + "WHERE palavra = ?");
                query.setString(1, word.toUpperCase());
                ResultSet result = query.executeQuery();

                // populate tuple list
                while (result.next()) {
                    int targetSerialno = result.getInt("serialno_referencias"); //serialno to look for
                    boolean exists = false; // tells if target serialno is already inside tupleList
                    // look for targetSerialno inside tupleList
                    for (Iterator<Tuple> j = tupleList.iterator(); j.hasNext();) {
                        tuple = j.next();
                        if (targetSerialno == tuple.serialno) {
                            tupleList.get(tupleList.indexOf(tuple)).count = tuple.count + 1;
                            exists = true;
                        }
                    }
                    // if targetSerialno is not in tupleList
                    if (!exists) {
                        tuple = new Tuple();
                        tuple.serialno = targetSerialno;
                        tuple.count = 1;

                        tupleList.add(tuple);
                    }
                }
                query.close();
            }

            // order tupleList by count (descending)
            Collections.sort(tupleList);
            Collections.reverse(tupleList);
            
            // populate refList
            for (int i = 0; i < tupleList.size(); i++) {
                System.out.println(tupleList.get(i).count);
                Referencias ref = refDao.readById(tupleList.get(i).serialno);
                refList.add(ref);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refList;
    }

    /**
     * reads database entry by title
     *
     * @param autor
     * @return
     */
    public List<Referencias> readByAutoria(String autor) {
        List<Referencias> refList = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * FROM referencias "
                    + "WHERE autoria like ?");
            query.setString(1, "%" + autor + "%");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                Referencias ref = new Referencias();
                ref.setSerialno(result.getInt("serialno"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
                refList.add(ref);
            }
            result.close();
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refList;
    }

    /**
     * edits database entry
     *
     * @param ref
     */
    public void update(Referencias ref) {
        try {
            conn = DBUtils.getConnection();

            delete(ref.getSerialno());
            create(ref);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes database entry
     *
     * @param refId
     */
    public void delete(int refId) {
        try {
            conn = DBUtils.getConnection();

            // delete from "referencias" table
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM referencias WHERE serialno = ?;");
            query.setInt(1, refId);
            query.executeUpdate();
            query.close();

            // delete from "rpalavrasdotitulo" table
            PreparedStatement query2 = conn.prepareStatement(
                    "DELETE FROM palavrasdotitulo WHERE serialno_referencias = ?;");
            query2.setInt(1, refId);
            query2.executeUpdate();
            query2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * shows all database entries
     *
     * @return
     */
    public List<Referencias> getAll() {
        List<Referencias> references = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            Statement query = conn.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM referencias ");
            while (result.next()) {
                Referencias ref = new Referencias();
                ref.setSerialno(result.getInt("serialno"));
                ref.setTitulo(result.getString("titulo"));
                ref.setAutoria(result.getString("autoria"));
                references.add(ref);
            }
            result.close();
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return references;
    }
}
