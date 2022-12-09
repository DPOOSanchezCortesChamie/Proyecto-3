package vista;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import controlador.ControladorUsuario;

public class POpcionesUsuario extends JPanel {
	
	private JButton btnCrearEquipo;
	private JTextField txtcompraJugador;
	private JTextField txtventaJugador;
	private JComboBox<String> seleccionEquipo;
	private ControladorUsuario controlador;
	private JDialog crearEquipo;
	private PJugador origin;
	
	public POpcionesUsuario(ControladorUsuario controlador, PJugador origin) {
		
		this.origin = origin;
			
		this.setLayout(new FlowLayout());
		this.crearEquipo = new JDialog();
		this.crearEquipo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.crearEquipo.setSize(700,400);
		this.controlador = controlador;
		this.seleccionEquipo = new JComboBox<>();
		this.seleccionEquipo.addItem("");
		for (String e: controlador.getEquipos()) {
			this.seleccionEquipo.addItem(e);
		}
		this.add(seleccionEquipo);
		this.seleccionEquipo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String seleccion = seleccionEquipo.getSelectedItem().toString();
				if(!seleccion.equals("")) {
					controlador.cambiarEquipo(seleccion);
					origin.refreshinfo();
				}
			}
			
		});
		
		this.btnCrearEquipo = new JButton("Crear Equipo");
		this.add(btnCrearEquipo);
		this.btnCrearEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crearEquipo.setVisible(true);
			}
		});
		
		JButton desComp = new JButton("Desempeño comparativo de jugadores");
		JButton historial = new JButton("Historial de compra y venta de jugadores");
		this.add(desComp);
		this.add(historial);
		
		crearEquipo.setLayout(new BorderLayout());
		JPanel input = new JPanel();
		input.setLayout(new GridLayout(3,2));
		input.add(new JLabel("Nombre temporada: "));
		JTextField tempName = new JTextField();
		input.add(tempName);
		input.add(new JLabel("Nombre equipo:"));
		JTextField equName = new JTextField();
		input.add(equName);
		input.add(new JLabel("Jugadores: "));
		JTextField jName  = new JTextField();
		input.add(jName);
		JPanel iz = new JPanel();
		iz.setLayout(new BorderLayout());
		iz.add(input, BorderLayout.CENTER);
		crearEquipo.add(iz, BorderLayout.CENTER);
		
		JButton create = new JButton("Crear equipo");		
		crearEquipo.add(create, BorderLayout.LINE_END);
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String jugadores[] = jName.getText().split(", ");
				if (tempName.getText()!=""&&equName.getText()!=""&&jugadores.length==15) {
					boolean xd = controlador.crearEquipo(tempName.getText(), equName.getText(), jugadores);
					if(!xd)
						mensajeError("No se pudo crear el equipo");
					else {
						mensajeBasado("Equipo creado");
						tempName.setText("");
						equName.setText("");
						jName.setText("");
						crearEquipo.setVisible(false);
						origin.refresh();
					}
				} else {
					mensajeError("No pueden haber valores sin llenar");
				}
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
