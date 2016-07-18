package com.eel418.trab2.DTO;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;

public class ReferenciaBibliografica implements Serializable {

    private String patrimonio;
    private String titulo;
    private String autoria;
    private String veiculo;
    private String dataPublicacao;
    private String nomeArquivo;
    private String nomeOriginalArquivo;
    private String palchave;

    private JsonObject JSONobj;

    public JsonObject toJSON() {

        JSONobj = Json.createObjectBuilder()
                .add("patrimonio", "\""+patrimonio+"\"")
                .add("titulo", "\""+titulo+"\"")
                .add("autoria", "\""+autoria+"\"")
                .add("veiculo", "\""+veiculo+"\"")
                .add("dataPublicacao", "\""+dataPublicacao+"\"")
                .add("nomeArquivo", "\""+nomeArquivo+"\"")
                .add("nomeOriginalArquivo", "\""+nomeOriginalArquivo+"\"")
                .add("palchave", "\""+palchave+"\"")
                .build();

        return JSONobj;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public void parseJson(JsonObject jo) {
        try {
            this.patrimonio = jo.getString("patrimonio");
            this.titulo = jo.getString("titulo");
            this.autoria = jo.getString("autoria");
            this.veiculo = jo.getString("veiculo");
            this.palchave = jo.getString("palchave");
            this.dataPublicacao = jo.getString("dataPublicacao");
            this.nomeArquivo = jo.getString("nomeArquivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReferenciaBibliografica() {
        patrimonio = "";
        titulo = "";
        autoria = "";
        veiculo = "";
        dataPublicacao = "";
        nomeArquivo = "";
        nomeOriginalArquivo = "";
        palchave = "";
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutoria() {
        return autoria;
    }

    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String data_publicacao) {
        this.dataPublicacao = data_publicacao;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getNomeOriginalArquivo() {
        return nomeOriginalArquivo;
    }

    public void setNomeOriginalArquivo(String nomeOriginalArquivo) {
        this.nomeOriginalArquivo = nomeOriginalArquivo;
    }

    public String getPalchave() {
        return palchave;
    }

    public void setPalchave(String palchave) {
        this.palchave = palchave;
    }
}
