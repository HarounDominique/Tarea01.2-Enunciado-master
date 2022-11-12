package es.teis.edu;

import es.teis.data.IPersistencia;
import es.teis.model.Partido;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class PartidoObjectPersistencia implements IPersistencia {
    @Override
    public void escribir(ArrayList<Partido> partidos, String ruta) {

        try(FileOutputStream fos = new FileOutputStream(ruta);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            for(Partido p : partidos){
                oos.writeObject(p);
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Partido> leerTodo(String ruta) {

        try(FileInputStream fis = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(fis)){


            while (true){
                Partido p = (Partido) ois.readObject();

                System.out.println(p.toString());
            }


        } catch (EOFException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
