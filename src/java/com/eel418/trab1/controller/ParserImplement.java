/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package com.eel418.trab1.controller;

import com.eel418.trab1.dao.ReferenciasDAO;
import com.eel418.trab1.model.Referencias;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 
 * Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 * Engenharia de Computação e Informação
 * Universidade Federal do Rio de Janeiro
 */
public class ParserImplement implements Parser {
    
    @Override
    public String process(HttpServletRequest request,HttpServletResponse response) {
        String forward_url = "error.jsp";
        
        String clickedButton = request.getParameter("clickedButton");
        //Referencias ref = new Referencias();
        switch (clickedButton) {
            case "read":{
                forward_url = "index.jsp";
                String tipo_de_busca = request.getParameter("tipo_de_busca");
                String parametro = request.getParameter("parametro");
                List<Referencias> refList = new ArrayList();
                ReferenciasDAO refDao = new ReferenciasDAO();
                
                if (parametro.equals("")){
                    refList = refDao.getAll();
                }else if (tipo_de_busca.equalsIgnoreCase("titulo")){
                    refList = refDao.readByTitulo(parametro);
                } else if (tipo_de_busca.equalsIgnoreCase("autoria")){
                    refList = refDao.readByAutoria(parametro);
                } else {
                    forward_url = "error.jsp";
                }
                
                request.getSession().setAttribute("qtd_resultados", Integer.toString(refList.size()));
                request.getSession().setAttribute("resultados", refList);
                break;
            }    
            case "update":{
                forward_url = "edit.jsp";
                
                List<Referencias> refList = new ArrayList();
                ReferenciasDAO refDao = new ReferenciasDAO();
                
                int serialno = Integer.parseInt(request.getParameter("serialno"));
                Referencias ref = refDao.readById(serialno);
                refList.add(ref);
                
                request.getSession().setAttribute("referencia", refList);
                break;                
            }
        }
        return forward_url;
    }
}
