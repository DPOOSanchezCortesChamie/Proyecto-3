package modelo;
//HMMMM
public class Reporte {
	private int minutosJugados;
	private int golesAnotados;
	private int autogoles;
	private int asistencias;
	private int penaltisErrados;
	private int tarjetasAmarillas;
	private int tarjetasRojas;
	private int golesRecibidos;
	private int penaltisDetenidos;
	private boolean victoria;
	
	public Reporte(int minutosJugados, int golesAnotados, int autogoles, int asistencias,
			int penaltisErrados, int tarjetasAmarillas, int tarjetasRojas, int golesRecibidos, 
			int penaltisDetenidos, boolean victoria) {

		this.minutosJugados = minutosJugados;
		this.golesAnotados = golesAnotados;
		this.autogoles = autogoles;
		this.asistencias = asistencias;
		this.penaltisErrados = penaltisErrados;
		this.tarjetasAmarillas = tarjetasAmarillas;
		this.tarjetasRojas = tarjetasRojas;
		this.golesRecibidos = golesRecibidos;
		this.penaltisDetenidos = penaltisDetenidos;
		this.victoria = victoria;
	}
	public boolean getVictoria() {
		return this.victoria;
	}
	
	public int calcularPuntosArquero() {
		int puntos = (golesAnotados)*6+(asistencias*3)+(penaltisErrados*-2)+
				(tarjetasAmarillas*-1)+(tarjetasRojas*-3)+(autogoles*-2)+(penaltisDetenidos*5);
		if (minutosJugados >= 60)
			puntos+=2;
		else if (minutosJugados != 0)
			puntos+=1;	
		if (golesRecibidos == 0)
			puntos +=4;
		return puntos;
	}
	public int calcularPuntosDefensa() {
		int puntos = (golesAnotados)*6+(asistencias*3)+(penaltisErrados*-2)+
			(tarjetasAmarillas*-1)+(tarjetasRojas*-3)+(autogoles*-2);
		if (minutosJugados >= 60)
			puntos+=2;
		else if (minutosJugados != 0)
			puntos+=1;	
		if (golesRecibidos == 0)
			puntos +=4;
		return puntos;
	}
	public int calcularPuntosDelantero() {
		int puntos = (golesAnotados)*4+(asistencias*3)+
		(penaltisErrados*-2)+(tarjetasAmarillas*-1)+(tarjetasRojas*-3)+(autogoles*-2);
		if (minutosJugados >= 60) 
			puntos+=2;
		else if (minutosJugados != 0)
			puntos+=1;
		return puntos;
	}
	public int calcularPuntosMediocampista() {
		int puntos = (golesAnotados)*5+(asistencias*3)+
		(penaltisErrados*-2)+(tarjetasAmarillas*-1)+(tarjetasRojas*-3)+(autogoles*-2);
		if (minutosJugados >= 60) 
			puntos+=2;
		else if (minutosJugados != 0)
			puntos+=1;
		return puntos;
	}
}
