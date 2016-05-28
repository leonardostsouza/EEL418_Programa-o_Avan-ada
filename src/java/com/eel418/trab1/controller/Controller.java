/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package com.eel418.trab1.controller;

import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eel418.trab1.model.Referencias;
import com.eel418.trab1.dao.ReferenciasDAO;

/**
 * @author 
 * Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 * Engenharia de Computação e Informação
 * Universidade Federal do Rio de Janeiro
 */

@WebServlet("/controller")
public class Controller extends HttpServlet{
    //private final ReferenciasDAO refDao;
    /*private static final String CREATE_OR_UPDATE = "/catalogar.jsp";
    private static final String VIEW_RESULT = "/index.jsp";*/
    
    
    /*
    public Controller(){
        super();
        //refDao = new ReferenciasDAO();
    }
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspURL = "error.jsp";
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        try {
            String pageParser = request.getParameter("parser");
            Parser parser = (Parser)Class.forName(pageParser).newInstance();
            jspURL = parser.process(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher(jspURL).forward(request, response);
        }
    }
    
    /*
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("parser");
 
        if(action.equalsIgnoreCase("create")) {
            jspURL = CREATE_OR_UPDATE;
        }
        else if( action.equalsIgnoreCase("delete")) {
            int refId = Integer.parseInt(req.getParameter("serialno"));
            refDao.delete(refId);
            jspURL = VIEW_RESULT;
            req.setAttribute("referencias", refDao.getAll());
        }
        else if(action.equalsIgnoreCase("update")) { 
            int refId = Integer.parseInt(req.getParameter("serialno"));
            Referencias ref = refDao.readById(refId);
            jspURL = CREATE_OR_UPDATE;
            req.setAttribute("referencias", ref);
        } else if(action.equalsIgnoreCase("list")) { 
            jspURL = VIEW_RESULT;
            req.setAttribute("referencias", refDao.getAll());
        }else {
            jspURL = VIEW_RESULT;
            req.setAttribute("referencias", refDao.getAll());
        }
        RequestDispatcher view = req.getRequestDispatcher(jspURL);
        view.forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Referencias ref = new Referencias();
        ref.setTitulo(req.getParameter("titulo"));
        ref.setAutoria(req.getParameter("autoria"));
        String serial = req.getParameter("serialno");
 
        if( serial == null || serial.isEmpty() )
            refDao.create(ref);
        else {
            ref.setSerialno(Integer.parseInt(serial));
            refDao.update(ref);
        }
        RequestDispatcher view = req.getRequestDispatcher(VIEW_RESULT);
        req.setAttribute("referencias", refDao.getAll());
        view.forward(req, resp);
    }
*/
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Trabalho 1 - EEL 418 - Programaçao Avançada";
    }// </editor-fold>
}
