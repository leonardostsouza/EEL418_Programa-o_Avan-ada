/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package com.eel418.trab1.controller;

import com.eel418.trab1.dao.ReferenciasDAO;
import com.eel418.trab1.model.Referencias;

import java.sql.SQLException;
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
public class Parser {
    public String processar(HttpServletRequest request,HttpServletResponse response) {
        String forward_url = "index.jsp";
        ReferenciasDAO refDao = new ReferenciasDAO();
        List<Referencias> refList = null;
        
        String clickedButton = request.getParameter("clickedButton");        
        
        switch (clickedButton) {
            case "BUSCAR":{
                String tipo_de_busca = request.getParameter("tipo_de_busca");
                String parametro = request.getParameter("parametro");
                
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
