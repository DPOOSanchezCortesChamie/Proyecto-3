package modelo;
//HMMMM
public class Mediocampista extends Jugador {
	
	public Mediocampista(String nombre, int precio, int numero) {
		super(nombre, precio, numero);
	}
	@Override
	public int calcularPuntos(int fecha) {
		try {
			Reporte reporte = reportes.get(fecha-1);
			return reporte.calcularPuntosMediocampista();
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public String darTipo() {
		return "Mediocampista";
	}
}
