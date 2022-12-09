package controlador;
import java.util.ArrayList;

import modelo.Admin;
import modelo.Jugador;
import modelo.Participante;
import modelo.TemporadaFantasia;

public class ControladorPrincipal {
	private ArrayList<Admin> admins;
	private ArrayList<Participante> users;
	public ControladorPrincipal() {
		admins = new ArrayList<Admin>();
		users = new ArrayList<Participante>();
	}
	public void crearUsuario(String nombre, String contrasena) throws Exception{
		boolean esta = false;
		for(Participante user:users) {
			if(user.isUser(nombre, contrasena)) 
				esta = true;
		}
		if(!esta) {
			Participante nuevo = new Participante(nombre, contrasena);
			users.add(nuevo);
		} else
			throw new Exception("Usuario ya existe");
		
	}
	public void crearAdmin(String nombre, String contrasena) throws Exception{
		boolean esta = false;
		for(Admin admin:admins) {
			if(admin.isUser(nombre, contrasena)) 
				esta = true;
		}
		if(!esta) {
			Admin nuevo = new Admin(nombre, contrasena);
			admins.add(nuevo);
		} else
			throw new Exception("Admin ya existe");
	}
	public Participante getUser(String nombre, String contrasena) {
		Participante buscado = null;
		for(Participante user:users) {
			if(user.isUser(nombre, contrasena)) 
				buscado = user;
		}
		return buscado;
	}
	public Admin getAdmin(String nombre, String contrasena) {
		Admin buscado = null;
		for(Admin admin:admins) {
			if(admin.isUser(nombre, contrasena)) 
				buscado = admin;
		}
		return buscado;
	}
	public TemporadaFantasia encontrarTemporada(String nombre) {
		for (Admin a: admins) {
			if(a.getNombreTemporada().equals(nombre)){
				return a.getTemporada();
			}
		}
		return null;
	}
	public ArrayList<Jugador> encontrarJugadores(TemporadaFantasia temp, ArrayList<String> jugadores){
		return temp.encontrarJugadores(jugadores);
	}
}
