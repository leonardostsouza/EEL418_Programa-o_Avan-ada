/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package teste;

import com.eel418.trab1.model.Referencias;
import com.eel418.trab1.dao.ReferenciasDAO;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Leonardo
 */
public class Main {
    public static void main(String[] args) {
        ReferenciasDAO refDao = new ReferenciasDAO();
        Referencias ref;
        
        //List<Referencias> list = refDao.getAll();
        ref = refDao.read(19);
        
        System.out.println("serialno: " + ref.getSerialno()); // Display the string.
        System.out.println("titulo: " + ref.getTitulo());
        System.out.println("autoria: " + ref.getAutoria());
    }
}
