package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorAdmin;

public class PAdminMain extends JPanel {
	ControladorAdmin controlador;
	PAdmin origin;
	public PAdminMain(ControladorAdmin controlador,PAdmin origin) {
		this.controlador = controlador;
		this.origin = origin;
		this.setLayout(new GridLayout(1,2));
		
		JPanel panelIz = new JPanel();
		panelIz.setLayout(new GridLayout(3, 1));
		panelIz.setBorder(new TitledBorder(new EtchedBorder()));
		
		JPanel panelIz2 = new JPanel();
		panelIz2.setLayout(new BorderLayout());
		panelIz2.setBorder(new TitledBorder(new EtchedBorder(), "Ingresar archivo"));
		JTextField archivo = new JTextField();
		JButton enviarArchivo = new JButton("Enviar");
		enviarArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean si = controlador.registrarPartido(archivo.getText());
				archivo.setText("");
				if (si)
					mensajeBasado("Datos cargados exitosamente");
				else
					mensajeError("No se pudieron cargar los datos");
			}
		});
		
		panelIz2.add(archivo, BorderLayout.CENTER);
		panelIz2.add(enviarArchivo, BorderLayout.LINE_END);
		
		JPanel panelIz3 = new JPanel();
		panelIz3.setLayout(new GridLayout(1,2));
		panelIz3.setBorder(new TitledBorder(new EtchedBorder(), "Control de temporada"));
		JButton sigMatch = new JButton("Siguiente partido");
		sigMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mensajeBasado(controlador.getSiguientePartido());
			}
		});
		JButton finDia = new JButton("Terminar fecha");
		finDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean t = controlador.concluirFecha();
				if (t) {
					mensajeBasado("Fecha terminada");
					origin.refresh();
				}
				else {
					mensajeError("La temporada no se pudo terminar");
				}
			}
			
		});
		panelIz3.add(sigMatch);
		panelIz3.add(finDia);
		
		JPanel panelIz4 = new JPanel();
		panelIz4.setBorder(new TitledBorder(new EtchedBorder(), "Reportes y graficas"));
		panelIz4.setLayout(new GridLayout(1,2));
		JButton progresoComparativo = new JButton("Progreso comparativo");
		JButton fuentesDePuntos = new JButton("Fuentes de puntos");
		panelIz4.add(progresoComparativo);
		panelIz4.add(fuentesDePuntos);
		
		panelIz.add(panelIz2);
		panelIz.add(panelIz3);
		panelIz.add(panelIz4);
		
		JPanel panelDer = new JPanel();
		panelDer.setLayout(new GridLayout(4,1));
		panelDer.setBorder(new TitledBorder(new EtchedBorder(), "Datos de la Temporada"));
		panelDer.add(new JLabel("Nombre: " + controlador.getNombreTemporadas()));
		panelDer.add(new JLabel("Numero equipos: " + controlador.getNumEquipos()));
		panelDer.add(new JLabel("Fecha actual: " + controlador.getNumFecha()));
		panelDer.add(new JLabel("Dia actual: "));
		
		
		this.add(panelIz);
		this.add(panelDer);
	}
	private void mensajeBasado(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje, "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
	}
	private void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje,"Error", JOptionPane.ERROR_MESSAGE); 					
	}
}
