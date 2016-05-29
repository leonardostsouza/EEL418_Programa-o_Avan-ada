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
 * @author Leonardo dos Santos Teixeira de Souza 
 * DRE: 112086681 
 * Engenharia de Computação e Informação 
 * Universidade Federal do Rio de Janeiro
 */
public class ParserImplement implements Parser {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String forward_url;

        String clickedButton = request.getParameter("clickedButton");
        switch (clickedButton) {
            case "read": {
                forward_url = "index.jsp";
                String tipo_de_busca = request.getParameter("tipo_de_busca");
                String parametro = request.getParameter("parametro");
                List<Referencias> refList = new ArrayList();
                ReferenciasDAO refDao = new ReferenciasDAO();

                if (parametro.equals("")) {
                    refList = refDao.getAll();
                } else if (tipo_de_busca.equalsIgnoreCase("titulo")) {
                    refList = refDao.readByTitulo(parametro);
                } else if (tipo_de_busca.equalsIgnoreCase("autoria")) {
                    refList = refDao.readByAutoria(parametro);
                } else {
                    forward_url = "error.jsp";
                }

                request.getSession().setAttribute("qtd_resultados", Integer.toString(refList.size()));
                request.getSession().setAttribute("resultados", refList);
                request.getSession().setAttribute("msg", "null");
                break;
            }
            case "update": {
                forward_url = "edit.jsp";

                List<Referencias> refList = new ArrayList();
                ReferenciasDAO refDao = new ReferenciasDAO();

                int serialno = Integer.parseInt(request.getParameter("serialno"));
                Referencias ref = refDao.readById(serialno);
                refList.add(ref);

                request.getSession().setAttribute("referencia", refList);
                request.getSession().setAttribute("serialno", refList.get(0).getSerialno());
                request.getSession().setAttribute("msg", "null");
                
                break;
            }
            case "update_confirm": {
                forward_url = "index.jsp";

                ReferenciasDAO refDao = new ReferenciasDAO();
                int serialno = Integer.parseInt(request.getParameter("serialno"));
                Referencias ref = refDao.readById(serialno);

                try {
                    String new_titulo = request.getParameter("titulo");
                    if (!new_titulo.isEmpty()) {
                        ref.setTitulo(new_titulo);
                    }

                    String new_autoria = request.getParameter("autoria");
                    if (!new_autoria.isEmpty()) {
                        ref.setAutoria(new_autoria);
                    }

                    refDao.update(ref);
                    request.getSession().setAttribute("msg", "Edição realizada com sucesso");
                } catch (Exception e) {
                    request.getSession().setAttribute("msg", "Um erro ocorreu");
                    forward_url = "error.jsp";
                    e.printStackTrace();
                }
                break;
            }
            case "create": {
                forward_url = "catalogar.jsp";

                ReferenciasDAO refDao = new ReferenciasDAO();
                Referencias ref = new Referencias();

                try {
                    String new_titulo = request.getParameter("titulo");
                    if (!new_titulo.isEmpty()) {
                        ref.setTitulo(new_titulo);
                    }

                    String new_autoria = request.getParameter("autoria");
                    if (!new_autoria.isEmpty()) {
                        ref.setAutoria(new_autoria);
                    }

                    refDao.create(ref);
                    request.getSession().setAttribute("msg", "Referencia criada com sucesso!");
                } catch (Exception e) {
                    request.getSession().setAttribute("msg", "Falha ao criar nova referencia");
                    e.printStackTrace();
                }
                break;
            }
            case "delete": {
                forward_url = "index.jsp";

                ReferenciasDAO refDao = new ReferenciasDAO();
                int serialno = Integer.parseInt(request.getParameter("serialno"));

                try {
                    refDao.delete(serialno);
                    request.getSession().setAttribute("msg", "Referencia apagada com sucesso");
                } catch (Exception e) {
                    request.getSession().setAttribute("msg", "Falha ao apagar entrada");
                    e.printStackTrace();
                }
                break;
            }
            case "catalogar": {
                forward_url = "catalogar.jsp";
                request.getSession().setAttribute("msg", "null");
                break;
            }
            case "busca": {
                forward_url = "index.jsp";
                request.getSession().setAttribute("msg", "null");
                break;
            }
            default: {
                forward_url = "error.jsp";
                request.getSession().setAttribute("msg", "null");
                break;
            }
        }
        return forward_url;
    }
}
