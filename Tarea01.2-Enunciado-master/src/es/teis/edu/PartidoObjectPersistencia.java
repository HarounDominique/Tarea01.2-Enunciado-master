package es.teis.edu;

import es.teis.data.IPersistencia;
import es.teis.model.Partido;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

public class PartidoObjectPersistencia implements IPersistencia {
    @Override
    public void escribir(ArrayList<Partido> partidos, String ruta) {



    }

    @Override
    public ArrayList<Partido> leerTodo(String ruta) {

        try(FileInputStream fis = new FileInputStream("t.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);){

            int i = ois.readInt();
            String today = (String) ois.readObject();
            Date date = (Date) ois.readObject();


        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        return null;
    }
}
