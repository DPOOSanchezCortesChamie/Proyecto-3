package modelo;

import java.util.ArrayList;

public class Alineacion {
	
	private ArrayList<Jugador> jugadores;
	private Jugador capitan;
	private int puntos;
	private Fecha fecha;
	
	public Alineacion(ArrayList<Jugador> jugadores, Fecha fecha) {
		this.jugadores = jugadores;
		this.fecha = fecha;
	}
	
	public Jugador getCapitan() {
		return this.capitan;
	}

	public void setCapitan(Jugador jugador) {
		this.capitan = jugador;
	}
	
	public int getPuntos() {
		return this.puntos;
	}
	
	public ArrayList<Jugador> getJugadores(){
		return this.jugadores;
	}
	
	public void actualizarPuntos() {
		int total = 0;
		for(Jugador jugador: jugadores) {
			total += jugador.calcularPuntos(fecha.getNumFecha());
		}
		if(capitan.victoria(fecha.getNumFecha()))
			total+=5;
		puntos = total;
	}
}
