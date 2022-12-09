package modelo;

import java.util.HashMap;
import java.util.ArrayList;

public class Partido {
	
	private String dia;
	private String hora;
	private int golesLocal;
	private int golesVisitante;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private ArrayList<Reporte> reportes;
	private boolean reportado;
	private int fecha;
		
	public Partido(String dia, String hora, Equipo equipoLocal, Equipo equipoVisitante, int fecha) {
		this.dia = dia;
		this.hora = hora;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.reportado = false;
		this.fecha = fecha;
	}
	
	/**
	 * Recibe los resultados de un partido en forma de ArrayList y crea los reportes
	 * y los asocia a su jugador
	 * @param resultados
	 */
	public void reportarPartido(ArrayList<String[]> resultados) {
		HashMap<String,Jugador> locales = getLocales();
		HashMap<String,Jugador> visitantes = getVisitantes();
		for(int i = 1; i < resultados.size();i++) {
			boolean isLocal = true;
			String[] r = resultados.get(i);
			Jugador jugador = locales.get(r[0]);
			if(jugador == null) {
				isLocal = false;
				jugador = visitantes.get(r[0]);
			}
			Reporte reporte;
			if(isLocal && this.golesLocal>this.golesVisitante) {
				reporte = new Reporte(p(r[2])-p(r[1]),p(r[3]),p(r[4]),p(r[5]),p(r[8]),
						p(r[9]),p(r[10]),p(r[6]),p(r[7]),true);
			} else if (this.golesVisitante>this.golesLocal) {
				reporte = new Reporte(p(r[2])-p(r[1]),p(r[3]),p(r[4]),p(r[5]),p(r[8]),
						p(r[9]),p(r[10]),p(r[6]),p(r[7]),true);
			} else {
				reporte = new Reporte(p(r[2])-p(r[1]),p(r[3]),p(r[4]),p(r[5]),p(r[8]),
						p(r[9]),p(r[10]),p(r[6]),p(r[7]),false);
			}
			if(jugador!=null)
				jugador.asociarReporte(reporte);
		}
		//Se asegura que si hay un jugador que no aparece en los reportes (por no haber jugado) a este se le ponga un reporte vacio
		for(String jugador:locales.keySet()) {
			if(!locales.get(jugador).reportado(this.fecha)){
				Reporte vacio;
				if(this.golesLocal>this.golesVisitante)
					vacio = new Reporte(0,0,0,0,0,0,0,0,0,true);
				else
					vacio = new Reporte(0,0,0,0,0,0,0,0,0,false);
				locales.get(jugador).asociarReporte(vacio);
			}
		}
		for(String jugador:visitantes.keySet()) {
			if(!visitantes.get(jugador).reportado(this.fecha)){
				Reporte vacio;
				if(this.golesLocal<this.golesVisitante)
					vacio = new Reporte(0,0,0,0,0,0,0,0,0,true);
				else
					vacio = new Reporte(0,0,0,0,0,0,0,0,0,false);
				visitantes.get(jugador).asociarReporte(vacio);
			}
		}
		this.reportado=true;
	}
	
	private int p(String s) {
		return Integer.parseInt(s);
	}
	private HashMap<String,Jugador> getLocales(){
		return equipoLocal.getJugadores();
	}
	private HashMap<String,Jugador> getVisitantes(){
		return equipoVisitante.getJugadores();
	}
	public String getNombreLocal() {
		return equipoLocal.getNombre();
	}
	public String getNombreVisitante() {
		return equipoVisitante.getNombre();
	}
	public boolean isReportado() {
		return this.reportado;
	}
	public String getDia() {
		return dia;
	}
	public String getHora() {
		return hora;
	}
}
