package modelo;

import persistencia.Archivo;

import java.util.ArrayList;
import java.util.HashMap;

public class TemporadaReal {
	private HashMap<Integer,Fecha> fechas;
	private HashMap<String,Equipo> equipos;
	
	public TemporadaReal() {
		fechas = new HashMap<Integer,Fecha>();
		equipos = new HashMap<String,Equipo>();
	}
	public Fecha crearFechas(String partidos) {
		Archivo archivo = new Archivo();
		HashMap<String,ArrayList<String[]>> fechas = archivo.cargarFechas(partidos);
		for(String numFecha: fechas.keySet()) {
			Fecha fecha = new Fecha(Integer.parseInt(numFecha));
			for(String[] info: fechas.get(numFecha)) {
				try {
					fecha.crearPartido(info[2], info[3], equipos.get(info[0]), equipos.get(info[1]));
				} catch (Exception e) {
					System.out.println(info[0] +" o "+ info[1] + " no es un equipo cargado");
				}
			}
			this.fechas.put(Integer.parseInt(numFecha), fecha);
		}
		return this.fechas.get(1);
	}
	public void crearEquipos(String nominas) {
		Archivo archivo = new Archivo();
		HashMap<String,ArrayList<String[]>> equipos = archivo.cargarEquipos(nominas);
		for(String nombre: equipos.keySet()) {
			Equipo nuevoEquipo = new Equipo(nombre);
			for(String[] jugador: equipos.get(nombre)) {
				nuevoEquipo.agregarJugador(jugador[0], Integer.parseInt(jugador[2]), 
						jugador[1], Integer.parseInt(jugador[3]));
			}
			this.equipos.put(nombre,nuevoEquipo);
		}
	}
	public Fecha concluirFecha(Fecha fechaActual) {
		int pos = 0;
		for(int x: fechas.keySet()) {
			if(fechas.get(x).equals(fechaActual))
				pos = x;
		}
		return fechas.get(pos+1);
	}
	public Fecha getFecha(int i) {
		return fechas.get(i);
	}
	public int getCantidadFechas() {
		return fechas.size();
	}
	public HashMap<String, Equipo> getEquipos() {
		return this.equipos;
	}

	public ArrayList<Jugador> encontrarJugadores(ArrayList<String> jugadores){
		ArrayList<Jugador> encontrados = new ArrayList<Jugador>();
		for(String jugador: jugadores) {
			for(String e: this.equipos.keySet()) {
				Jugador xd = equipos.get(e).getJugadores().get(jugador);
				if (xd != null) {
					encontrados.add(xd);
				}
			}
		}
		return encontrados;
	}
	public Jugador encontrarJugador(String jugador) {
		Jugador j = null;
		for(String e: this.equipos.keySet()) {
			Jugador xd = equipos.get(e).getJugadores().get(jugador);
			if (xd != null) {
				j = xd;
			}
		}
		return j;
	}
}
