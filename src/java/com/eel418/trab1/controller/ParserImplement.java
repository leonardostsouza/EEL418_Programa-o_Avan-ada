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
        String forward_url = "index.jsp";
        //ReferenciasDAO refDao = new ReferenciasDAO();
        //List<Referencias> refList = new ArrayList();
        
        String clickedButton = request.getParameter("clickedButton");
        Referencias ref = new Referencias();
        //ref.setTitulo(request.getParameter("titulo"));
        //ref.setAutoria(request.getParameter("autoria"));
        
        switch (clickedButton) {
            case "read":{
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
        }
        return forward_url;
    }
}
