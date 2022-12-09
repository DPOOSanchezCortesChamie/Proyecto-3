package persistencia;

import java.util.Comparator;

import modelo.EquipoFantasia;

public class CompararEquipos implements Comparator<EquipoFantasia> {

	@Override
	public int compare(EquipoFantasia e1, EquipoFantasia e2) {
		
		if(e1.getPuntos()>e2.getPuntos()) {
			return -1;
		}else if(e1.getPuntos()==e2.getPuntos()) {
			return 0;
		}else {
			return 1;
		}
	}
	
}