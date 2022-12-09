package modelo;

import java.util.List;

public class Admin {
	
	TemporadaFantasia temporada;
	private String nombre;
	private String contrasena;
	private int numFecha;
	
	public Admin(String nombre, String contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.numFecha = 0;
	}
	public int getNumFecha() {
		return this.numFecha;
	}
	public List<EquipoFantasia> getMejoresTres() {
		return temporada.mejoresTresEquipos();
	}
	public TemporadaFantasia crearTemporada(String nominas, String partidos, int presupuesto, String nombre) {
		temporada = new TemporadaFantasia(presupuesto, nombre);
		temporada.crearTemporadaReal(nominas, partidos);
		return temporada;
	}
	public TemporadaFantasia getTemporada() {
		return this.temporada;
	}
	public boolean registrarResultadoPartido(String resultado) {
		boolean sePudo = temporada.hacerReportePartido(resultado);
		if (sePudo) {
		actualizarDatosTemporada();
		}
		return sePudo;
	}
	public boolean isUser(String nombre,String contrasena) {
		if(this.nombre.equals(nombre) && this.contrasena.equals(contrasena))
			return true;
		return false;
	}
	public boolean concluirFecha() {
		boolean t = temporada.concluirFecha();
		if (t)
			this.numFecha++;
		return t;
	}
	private void actualizarDatosTemporada() {
		temporada.actualizarDatos();
	}
	public String getNombreTemporada() {
		return temporada==null ? "N/A": this.temporada.getNombre();
	}	
}
