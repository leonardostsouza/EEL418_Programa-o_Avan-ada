/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package com.eel418.trab1.model;

/**
 * @author 
 * Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 * Engenharia de Computação e Informação
 * Universidade Federal do Rio de Janeiro
 */

public class Referencias {
    private int serialno;
    private String titulo;
    private String autoria;
    
    public Referencias(){
        this.serialno = 0;
        this.titulo = null;
        this.autoria = null;
    }
  
    /**
     * @return the serialno
     */
    public int getSerialno() {
        return serialno;
    }

    /**
     * @param serialno the serialno to set
     */
    public void setSerialno(int serialno) {
        this.serialno = serialno;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autoria
     */
    public String getAutoria() {
        return autoria;
    }

    /**
     * @param autoria the autoria to set
     */
    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }
    
    @Override
    public String toString() {
        return "Referencia: [serialno =" + this.serialno 
                + ", titulo =" + this.titulo
                + ", autoria =" + this.autoria + "]";
    }       
}
