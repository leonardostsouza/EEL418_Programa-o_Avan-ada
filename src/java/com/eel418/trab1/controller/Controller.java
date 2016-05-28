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
    private final ReferenciasDAO refDao;
    private static final String CREATE_OR_UPDATE = "/catalogar.jsp";
    private static final String VIEW_RESULT = "/index.jsp";
    
    public Controller(){
        super();
        refDao = new ReferenciasDAO();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        resp.setCharacterEncoding("UTF8");
        
        String jspURL;
        String action = req.getParameter("action");
 
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
}
