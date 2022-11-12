/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.teis.edu;

import es.teis.data.exceptions.LecturaException;
import es.teis.model.Partido;

import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author maria
 */
public class Main {

    private static final String ELECCIONES_INPUT_FILE = Paths.get("D:\\IntelliJ_Projects\\Tarea01.2-Enunciado-master\\Tarea01.2-Enunciado-master\\src\\docs\\elecciones.xml").toString();
    private static final String ELECCIONES_OUTPUT_FILE = Paths.get("D:\\IntelliJ_Projects\\Tarea01.2-Enunciado-master\\Tarea01.2-Enunciado-master\\src\\docs\\elecciones_output.dat").toString();

    private static final float UMBRAL_PORCENTAJE = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LecturaException {

        try{

            DOMXMLService dxmls = new DOMXMLService();

            ArrayList<Partido> partidos = dxmls.leerPartidos(ELECCIONES_INPUT_FILE, UMBRAL_PORCENTAJE);

            mostrar(partidos);

            PartidoObjectPersistencia pop = new PartidoObjectPersistencia();

            pop.escribir(partidos, ELECCIONES_OUTPUT_FILE);

            pop.leerTodo(ELECCIONES_OUTPUT_FILE);

        }catch(LecturaException le){

            le.printStackTrace();
        }

    }

    private static void mostrar(ArrayList<Partido> partidos) {
        System.out.println("Se han leído: ");
        for (Partido partido : partidos) {
            System.out.println(partido);

        }
    }

}
