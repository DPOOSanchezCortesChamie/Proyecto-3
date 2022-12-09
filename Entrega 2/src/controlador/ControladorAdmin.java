package controlador;

import java.util.List;

import modelo.Admin;
import modelo.EquipoFantasia;
import modelo.TemporadaFantasia;

public class ControladorAdmin {
	Admin admin;
	TemporadaFantasia temporada;
	ControladorPrincipal controlador;
	public ControladorAdmin(Admin a, ControladorPrincipal controlador) {
		this.admin = a;
		this.temporada = admin.getTemporada();
		this.controlador = controlador;
	}
	public void crearTemporada(String nominas, String fechas, int presupuesto, String nombre) {
		temporada = admin.crearTemporada(nominas, fechas, presupuesto, nombre);
	}
	public List<EquipoFantasia> getMejoresTres() {
		return admin.getMejoresTres();
	}
	public void registrarResultadoPartido(String resultado) {
		admin.registrarResultadoPartido(resultado);
	}
	public String getNombreTemporadas() {
		return admin.getNombreTemporada();
	}
	public boolean hayTemporada() {
		return admin.getNombreTemporada().equals("N/A")?false:true;
	}
	public int getNumEquipos() {
		if(temporada!=null)
			return temporada.getNumEquipos();
		return 999;
	}
	public int getNumFecha() {
		if(temporada!=null)
			return temporada.getFechaSiguiente().getNumFecha()-1;
		return 999;
	}
	public boolean registrarPartido(String str) {
		if(temporada!=null)
			return this.admin.registrarResultadoPartido(str);
		return false;
	}
	public String getSiguientePartido() {
		if(temporada!= null)
			return temporada.getSiguientePartido();
		return "La temporada no existe";
	}
	public boolean concluirFecha() {
		if(temporada!=null)
			return temporada.concluirFecha();
		return false;
	}
}
