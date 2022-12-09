package modelo;
//HMMMM
public class Arquero extends Jugador {
	public Arquero(String nombre, int precio, int numero) {
		super(nombre, precio, numero);
	}
	@Override
	public int calcularPuntos(int fecha) {
		try {
			Reporte reporte = reportes.get(fecha-1);
			return reporte.calcularPuntosArquero();
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public String darTipo() {
		return "Arquero";
	}
}
