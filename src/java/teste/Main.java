/*
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */
package teste;

import com.eel418.trab1.model.Referencias;
import com.eel418.trab1.dao.ReferenciasDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Leonardo
 */
public class Main {
    public static void main(String[] args) {
        ReferenciasDAO refDao = new ReferenciasDAO();
        Referencias ref = new Referencias();
        
        System.out.println("comecou");
        
        //ref.setAutoria("testA2");
        //ref.setTitulo("Titulo2 Teste2");
        //refDao.create(ref);
        //ref.setTitulo("Titulo2 Teste2");
        //ref.setSerialno(57);
        //refDao.update(ref);
        //refDao.delete(49);
        //refDao.delete(38);
        //refDao.delete(36);
        //refDao.delete(35);
        //refDao.delete(34);
        //refDao.readById(13);
        List<Referencias> list = refDao.readByTitulo("Applied Probability");
        //System.out.println(list.size());
        //List<Referencias> list = refDao.getAll();
        for(Iterator<Referencias> i = list.iterator(); i.hasNext(); ) {
            ref = i.next();
            System.out.println(ref.toString());
        }
        
        
        //System.out.println(ref.toString());
    }
}
