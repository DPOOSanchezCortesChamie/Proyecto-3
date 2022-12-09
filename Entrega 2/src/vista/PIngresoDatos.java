package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controlador.ControladorPrincipal;
import modelo.Admin;
import modelo.Participante;

public class PIngresoDatos extends JPanel{
	
	private JTextField txtNombreUsuario;
	private JTextField txtContrasena;
	private JRadioButton botonUser;
	private JRadioButton botonAdmin;
	private FPrincipal main;
	
	private ControladorPrincipal controlador;
	
	public PIngresoDatos(FPrincipal main, ControladorPrincipal c) {		
		this.controlador = c;
		this.main = main;
		this.setLayout(new GridLayout(4, 2, 10, 10));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.add(new JLabel("Nombre de usuario"));
		this.txtNombreUsuario = new JTextField();
		this.add(this.txtNombreUsuario);
		this.add(new JLabel("Contraseña"));
		this.txtContrasena = new JTextField();
		this.add(this.txtContrasena);
		
		botonUser = new JRadioButton("Usuario");
		botonAdmin = new JRadioButton("Admin");
			
		ButtonGroup grupoBotones = new ButtonGroup();
		
		grupoBotones.add(botonUser);
		grupoBotones.add(botonAdmin);
		
		this.add(botonUser);
		this.add(botonAdmin);
		
		JButton btnCrearUsuario = new JButton("Crear usuario");
		this.add(btnCrearUsuario);
		JButton btnIngresar = new JButton("Ingresar");
		this.add(btnIngresar);
		
		btnIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(botonUser.isSelected()) {
					if (!txtNombreUsuario.getText().isBlank()) {
						if (!txtContrasena.getText().isBlank()) {
							Participante p = controlador.getUser(txtNombreUsuario.getText(), txtContrasena.getText());
							if (p==null)
								mensajeError("Usuario y/o contraseña incorrectos");
							else {
								txtNombreUsuario.setText(null);
								txtContrasena.setText(null);
								main.iniciarUsuario(p);
							}
						} else {
							mensajeError("La contraseña no puede ser nula");
						}
					} else {
						mensajeError("El nombre de usuario no puede ser nulo");
					}
				} else if (botonAdmin.isSelected()) {
					if (!txtNombreUsuario.getText().isBlank()) {
						if (!txtContrasena.getText().isBlank()) {
							Admin a = controlador.getAdmin(txtNombreUsuario.getText(), txtContrasena.getText());
							if (a==null)
								mensajeError("Usuario y/o contraseña incorrectos");
							else {
								txtNombreUsuario.setText(null);
								txtContrasena.setText(null);
								main.iniciarAdmin(a);
							}
						} else {
							mensajeError("La contraseña no puede ser nula");
						}
					} else {
						mensajeError("El nombre de usuario no puede ser nulo");
					}
				} else {
					mensajeError("Debe seleccionar o usuario o admin");
				}
			}
			});
		
		btnCrearUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(botonUser.isSelected()) {
					if (!txtNombreUsuario.getText().isBlank()) {
						if (!txtContrasena.getText().isBlank()) {
							try {
								controlador.crearUsuario(txtNombreUsuario.getText(), txtContrasena.getText());
								txtNombreUsuario.setText("");
								txtContrasena.setText("");
								usuarioConfirmado("Usuario creado");
							} catch (Exception e1) {
								mensajeError("Usuario o contraseña ya existe");
							}
						} else {
							mensajeError("La contraseña no puede ser nula");
						}
					} else {
						mensajeError("El nombre de usuario no puede ser nulo");
					}
				} else if (botonAdmin.isSelected()) {
					if (!txtNombreUsuario.getText().isBlank()) {
						if (!txtContrasena.getText().isBlank()) {
							try {
								controlador.crearAdmin(txtNombreUsuario.getText(), txtContrasena.getText());
								txtNombreUsuario.setText("");
								txtContrasena.setText("");
								usuarioConfirmado("Admin creado");
							} catch (Exception e1) {
								mensajeError("Usuario o contraseña ya existe");
							}
						} else {
							mensajeError("La contraseña no puede ser nula");
						}
					} else {
						mensajeError("El nombre de usuario no puede ser nulo");
					}
				} else {
					mensajeError("Debe seleccionar o usuario o admin");
				}
			}
		});
	
	}
	private void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje, "Error", JOptionPane.ERROR_MESSAGE); 					
	}
	private void usuarioConfirmado(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje, "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
	}
}
