package vista;

import modelo.Admin;
import controlador.ControladorAdmin;
import controlador.ControladorPrincipal;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PAdmin extends JPanel{
	
	private static final long serialVersionUID = 1L;
	FPrincipal principal;
	PCreador panelTop;
	PAdminMain panelCenter;
	Admin a;
	
	public PAdmin(Admin a, FPrincipal principal, ControladorPrincipal c) {
		this.a = a;
		this.principal = principal;
		ControladorAdmin controlador = new ControladorAdmin(a,c);
		this.setLayout(new BorderLayout());
		
		this.panelTop = new PCreador(controlador,this);		
		this.panelCenter = new PAdminMain(controlador,this);
		
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelTop, BorderLayout.NORTH);
		
		JButton cerrarSesion  = new JButton("Cerrar Sesion");
		this.add(cerrarSesion,BorderLayout.SOUTH);
		
		cerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				principal.cerrarSesionAdmin();
			}
		});		
		
	}
	public void refresh() {
		principal.reiniciarAdmin(a);
	}
}
