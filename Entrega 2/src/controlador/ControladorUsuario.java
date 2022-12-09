package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import modelo.Jugador;
import modelo.Participante;
import modelo.TemporadaFantasia;

public class ControladorUsuario {
	private Participante usuario;
	private ControladorPrincipal controlador;
	private String timActual;
	public ControladorUsuario(Participante p, ControladorPrincipal controlador) {
		this.usuario = p;
		this.controlador = controlador;
	}
	public HashMap<String, Jugador> getJugadores(String equipo) {
		return this.usuario.getEquipo(equipo).getJugadores();
	}
	
	public void venderComprarJugadores(String equipo, String saliente, String entrante) {
		if (timActual != null)
			this.usuario.venderComprarJugadores(timActual, saliente, entrante);
	}
	public boolean crearEquipo(String temporada,String nombre,  String[] jugadores) {
		TemporadaFantasia tempFantasia = this.controlador.encontrarTemporada(temporada);
		ArrayList<String> jug = new ArrayList<String>();
		for (String j: jugadores) {
			jug.add(j);
		}
		if (tempFantasia != null) {
			ArrayList<Jugador> players=tempFantasia.encontrarJugadores(jug);
			if (jug.size()==players.size()) {
				this.usuario.crearEquipo(nombre, tempFantasia, players);
				this.timActual = nombre;
				return true;
			}
		}
		return false;
	}
	public void cambiarEquipo(String nombre) {
		this.timActual = nombre;
	}
	public Set<String> getEquipos(){
		return usuario.getEquipos();
	}
	public double getMonto() {
		if (timActual != null) {
			return usuario.getEquipo(timActual).getMonto();
		}
		return -1;
	}
	public ArrayList<String> getDelanteros() {
		if (timActual != null)
			return this.usuario.getEquipo(timActual).getDelanteros();
		return null;
	}
	public ArrayList<String> getMedCampistas() {
		if (timActual != null)
			return this.usuario.getEquipo(timActual).getMedCampistas();
		return null;
	}
	public ArrayList<String> getDefensas() {
		if (timActual != null)
			return this.usuario.getEquipo(timActual).getDefensas();
		return null;
	}
	public ArrayList<String> getArqueros() {
		if (timActual != null)
			return this.usuario.getEquipo(timActual).getArqueros();
		return null;
	}
}
