package modelo;
//HMMMM
import java.util.ArrayList;

public abstract class Jugador {
	private String nombre;
	private int precio;
	private int numero;
	protected ArrayList<Reporte> reportes;
	
	public Jugador(String nombre, int precio, int numero) {
		this.nombre=nombre;
		this.precio=precio;
		this.numero=numero;
		this.reportes = new ArrayList<Reporte>();
	}	
	public String getNombre() {
		return this.nombre;
	}
	public int getPrecio() {
		return this.precio;
	}
	public int getNumero() {
		return this.numero;
	}
		
	public String print() {
		return darTipo() + " - " + this.nombre + " " + numero + " / $" + precio;
	}
	
	public boolean isEquals(Jugador j) {
		if((j.getNombre().equals(this.nombre)&&j.getPrecio()==this.precio&&j.getNumero()==this.numero)
				|| j == null)
			return true;
		return false;
	}
	
	public void asociarReporte(Reporte reporte) {
		reportes.add(reporte);
	}
	
	public boolean victoria(int fecha) {
		return reportes.get(fecha-1).getVictoria();
	}
	
	public boolean reportado(int fecha) {
		if(reportes.size()==fecha)
			return true;
		return false;
	}
	
	public abstract int calcularPuntos(int fecha);
	
	public abstract String darTipo();
}
