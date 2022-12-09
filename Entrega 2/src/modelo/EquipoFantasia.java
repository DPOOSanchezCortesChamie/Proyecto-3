package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipoFantasia {
	
	private String nombre;
	private double monto;
	private int puntos;
	private TemporadaFantasia temporada;
	private HashMap<String,Jugador> jugadores;
	private ArrayList<Alineacion> alineaciones;
	private Alineacion proxima;
	
	public EquipoFantasia(String nombre, TemporadaFantasia temporada, ArrayList<Jugador> jugadores) {
		this.nombre = nombre;
		this.monto = temporada.getPresupuesto();
		this.temporada = temporada;
		this.puntos = 0;
		this.jugadores = new HashMap<String,Jugador>();
		this.alineaciones = new ArrayList<Alineacion>();
		for(Jugador j: jugadores) {
			this.jugadores.put(j.getNombre(), j);
			this.monto -= j.getPrecio();
		}
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public void comprarJugador(String player) {
		Jugador jugador = temporada.encontrarJugador(player);
		jugadores.put(jugador.getNombre(),jugador);
		monto -= jugador.getPrecio();
	}
	public HashMap<String,Jugador> getJugadores(){
		return this.jugadores;
	}
	public int getPuntos() {
		return this.puntos;
	}
	public double getMonto() {
		return this.monto;
	}
	public void actualizarPuntos() {
		if (proxima != null)
			proxima.actualizarPuntos();
	}
	public void venderJugador(String player) {
		Jugador jugador = temporada.encontrarJugador(player);
		jugadores.remove(jugador.getNombre());
		double remuneracion = jugador.getPrecio()*0.97;
		monto += remuneracion;
	}
	public void concluirFecha() {
		if (proxima != null) {
			alineaciones.add(proxima);
			this.puntos+=proxima.getPuntos();
		}
	}
	public TemporadaFantasia getTemporada() {
		return this.temporada;
	}
	public Alineacion designarAlineacion(ArrayList<Jugador> jugadores) {
		this.proxima = new Alineacion(jugadores, temporada.getFechaSiguiente());
		return proxima;
	}
	public ArrayList<String>  getDelanteros() {
		ArrayList<String> delanteros = new ArrayList<String>();
		for(String jugador: this.jugadores.keySet()) {
			if(this.jugadores.get(jugador).darTipo().equals("Delantero"))
				delanteros.add(jugador);
		}
		return delanteros;
	}
	public ArrayList<String>  getMedCampistas() {
		ArrayList<String> delanteros = new ArrayList<String>();
		for(String jugador: this.jugadores.keySet()) {
			if(this.jugadores.get(jugador).darTipo().equals("Mediocampista"))
				delanteros.add(jugador);
		}
		return delanteros;
	}
	public ArrayList<String> getDefensas() {
		ArrayList<String> delanteros = new ArrayList<String>();
		for(String jugador: this.jugadores.keySet()) {
			if(this.jugadores.get(jugador).darTipo().equals("Defensa"))
				delanteros.add(jugador);
		}
		return delanteros;
	}
	public ArrayList<String> getArqueros() {
		ArrayList<String> delanteros = new ArrayList<String>();
		for(String jugador: this.jugadores.keySet()) {
			if(this.jugadores.get(jugador).darTipo().equals("Arquero"))
				delanteros.add(jugador);
		}
		return delanteros;
	}
}
