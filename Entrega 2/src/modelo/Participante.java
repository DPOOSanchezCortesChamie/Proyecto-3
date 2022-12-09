package modelo;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class Participante {
	private String nombre;
	private String contrasena;
	private HashMap<String,EquipoFantasia> equipos;

	public Participante(String nombre, String contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.equipos = new HashMap<String,EquipoFantasia>();
	}

	public void crearEquipo(String nombre, TemporadaFantasia temporada, ArrayList<Jugador> jugadores) {
		EquipoFantasia equipo = new EquipoFantasia(nombre, temporada, jugadores);
		this.equipos.put(nombre,equipo);
		temporada.asociarEquipo(equipo);
	}
	
	public void venderComprarJugadores(String equipo, String saliente, String entrante) {
		equipos.get(equipo).comprarJugador(entrante);
		equipos.get(equipo).venderJugador(saliente);
	}
	
	public boolean isUser(String nombre,String contrasena) {
		if(this.nombre.equals(nombre) && this.contrasena.equals(contrasena))
			return true;
		return false;
	}
	
	public EquipoFantasia getEquipo(String nombre) {
		return equipos.get(nombre);
	}
	public HashMap<String,Jugador> BuscarJugadorPorEquipo(String nombreEquipo) {
		TemporadaReal temporada = new TemporadaReal();
		HashMap<String, Equipo> equipos = temporada.getEquipos();
		Equipo equipo = equipos.get(nombreEquipo);
		HashMap<String,Jugador> jugadores = equipo.getJugadores();
		return jugadores;
	}
	public Set<String> getEquipos() {
		return equipos.keySet();
	}

}
