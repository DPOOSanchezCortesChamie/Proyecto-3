package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorAdmin;
import modelo.Admin;

public class PCreador extends JPanel {
	ControladorAdmin controlador;
	JButton crear;
	JLabel title;
	JTextField nombre;
	JTextField fechas;
	JTextField nominas;
	JTextField presupuesto;
	PAdmin origin;
	public PCreador(ControladorAdmin controlador, PAdmin origin) {
		this.controlador = controlador;
		this.origin = origin;
		this.setLayout(new GridLayout(1,6));
		this.setBorder(new TitledBorder(new EtchedBorder()));
		
		JDialog creador = new JDialog();
		creador.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		creador.setSize(700,300);
		creador.setLayout(new BorderLayout());
		
		JPanel opciones = new JPanel();
		opciones.setLayout(new GridLayout(4,2));
		this.nombre = new JTextField();
		this.fechas = new JTextField();
		this.nominas = new JTextField();
		this.presupuesto = new JTextField();
		opciones.add(new JLabel("Nombre: "));
		opciones.add(nombre);
		opciones.add(new JLabel("Archivo fechas: "));
		opciones.add(fechas);
		opciones.add(new JLabel("Archivo nominas: "));
		opciones.add(nominas);
		opciones.add(new JLabel("Presupuesto: "));
		opciones.add(presupuesto);
		creador.add(opciones, BorderLayout.CENTER);	
		
		JButton crearTemporada = new JButton("Crear Temporada");
		crearTemporada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nombre.getText().equals("")) {
					if (!fechas.getText().equals("")) {
						if (!nominas.getText().equals("")) {
							if (!presupuesto.getText().equals("")) {
								try {
									controlador.crearTemporada(nominas.getText(),fechas.getText(),Integer.parseInt(presupuesto.getText()),nombre.getText());
									creador.setVisible(false);
									mensajeBasado("Temporada creada exitosamente");
									origin.refresh();
									
								} catch(Exception e2){  
									mensajeError("Los valores no fueron aceptados");
								}  
							}
						}
					}
				}
			}
		});
		creador.add(crearTemporada, BorderLayout.LINE_END);

		
		this.crear = new JButton("Crear nueva temporada");
		this.title = new JLabel("Temporada: " + this.controlador.getNombreTemporadas());
		this.add(this.title);
		this.add(this.crear);
		
		this.crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!controlador.hayTemporada())
					creador.setVisible(true);
				else 
					mensajeError("No puedes tener más de una temporada como administrador");
			}
		});
		
	}	
		
	private void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje,"Error", JOptionPane.ERROR_MESSAGE); 					
	}
	private void mensajeBasado(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje, "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
	}
}
