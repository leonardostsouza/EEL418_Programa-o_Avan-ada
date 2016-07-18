/*
 * Este codigo faz parte do primeiro trabalho desenvolvido para o curso
 * EEL 418 - Programacao Avanacada, ministrado no primeiro semestre
 * de 2016 pelo professor Jorge Lopes de Souza Leao
 * Autor: Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 */
package com.eel418.trab2.Servlet;

import com.eel418.trab2.DAO.ReferenciasDAO;
import com.eel418.trab2.DTO.ReferenciaBibliografica;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo
 */
public class Controller extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BufferedReader br = new BufferedReader(
                                  new  InputStreamReader(
                                           request.getInputStream(),"UTF8"));
        String clientRequest = br.readLine();
        
        JsonObject jo = null;
        try (   
                JsonReader jr = Json.createReader(new StringReader(clientRequest))) {
                jo = jr.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        ReferenciasDAO refDAO = new ReferenciasDAO();
        ReferenciaBibliografica ref = new ReferenciaBibliografica();
        
        ArrayList<ReferenciaBibliografica> refResult = new ArrayList<>();
        ReferenciaBibliografica refResultItem;
        
        ref.parseJson(jo); // compy JSON attibutes to ReferenciaBibliografica object
        Boolean isSuccessful = true;
        
        switch(jo.getString("op")){
            case "search":
                if(jo.getString("patrimonioOpt").equals("true")){
                    refResultItem = refDAO.readById(ref.getPatrimonio());
                    if (!refResultItem.getPatrimonio().equals("")){
                        refResult.add(refResultItem);
                    }
                } else {
                    refResult = refDAO.readByAttributes(jo);
                }
                break;
            case "update":
                isSuccessful = refDAO.update(ref);
                break;
            case "delete":
                isSuccessful = refDAO.delete(ref.getPatrimonio());
                break;
            case "create":
                isSuccessful = refDAO.create(ref);
                break;
            default:
                System.out.println("[BController] ERRO - Operação inválida");
        }
                
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //out.print(dto.toString());
        out.print("{\"isSuccessful\":" + isSuccessful.toString() +",\"size\":" + refResult.size() + ",\"data\":" + refResult.toString() + "}");
        out.flush();
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
