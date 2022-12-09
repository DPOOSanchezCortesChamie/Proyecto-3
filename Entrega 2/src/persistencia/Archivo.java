package persistencia;

import java.util.HashMap;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Archivo {
	
	public Archivo() {
		
	}
	public HashMap<String,ArrayList<String[]>> cargarEquipos(String nombreArchivo) {
        HashMap<String,ArrayList<String[]>> datos = new HashMap<String,ArrayList<String[]>>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("datos/"+nombreArchivo));
            String linea = br.readLine();
	        String[] equipos = linea.split(",");
            for (String equipo: equipos) {
            	datos.put(equipo, new ArrayList<String[]>());
            }
            int c = 0;
            while ((linea = br.readLine()) != null) {
            	String infoJugadores[] = linea.split(",");
            	int cantidadJugadores = infoJugadores.length/4;
            	datos.put(equipos[c], new ArrayList<String[]>());
            	for(int i = 0; i<cantidadJugadores;i++) {
            		String jugador[] = {infoJugadores[i*4],infoJugadores[i*4+1],infoJugadores[i*4+2],infoJugadores[i*4+3]};
            		datos.get(equipos[c]).add(jugador);
            	}
            	c++;
            }
            br.close();
            return datos;
        } catch (Exception e) {
            System.out.println("El archivo " + nombreArchivo + " no se pudo cargar");
        }
        return datos;
    }
	
	public HashMap<String,ArrayList<String[]>> cargarFechas(String nombreArchivo){
		HashMap<String,ArrayList<String[]>> datos = new HashMap<String,ArrayList<String[]>>();
		try {
            BufferedReader br = new BufferedReader(new FileReader("datos/"+nombreArchivo));
            String linea = null;
            while ((linea = br.readLine()) != null) {
            	String info[] = linea.split(",");
            	if(datos.get(info[0])==null) 
            		datos.put(info[0], new ArrayList<String[]>());
            	String fecha[] = {info[1],info[2],info[3],info[4]};
            	datos.get(info[0]).add(fecha);
            }
            br.close();
        } catch (Exception e) {
        	System.out.println("El archivo " + nombreArchivo + " no se pudo cargar");
        }
        return datos;
	}
	
	public ArrayList<String[]> cargarReportes(String nombreArchivo){
		ArrayList<String[]> reporte = new ArrayList<String[]>();
		try {
            BufferedReader br = new BufferedReader(new FileReader("datos/"+nombreArchivo));
            String linea = null;
            while ((linea = br.readLine()) != null) {
            	String info[] = linea.split(",");
            	reporte.add(info);
            }
            br.close();
        } catch (Exception e) {
        	System.out.println("El archivo " + nombreArchivo + " no se pudo cargar");
        }
		return reporte;
	}
}




