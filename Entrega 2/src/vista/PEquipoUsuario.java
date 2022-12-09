package vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controlador.ControladorUsuario;

public class PEquipoUsuario extends JPanel{
	ControladorUsuario controlador;
	PJugador origin;
	public PEquipoUsuario(ControladorUsuario controlador, PJugador origin) {
		this.origin = origin;
		this.setLayout(new GridLayout(1,2));
		JPanel sustituciones = new JPanel();
		sustituciones.setLayout(new GridLayout(5, 3));
		this.controlador = controlador;
		JPanel pDel = new JPanel(new FlowLayout());
		JPanel pSDel = new JPanel(new FlowLayout());
		JPanel pMed = new JPanel(new FlowLayout());
		JPanel pSMed = new JPanel(new FlowLayout());
		JPanel pDef = new JPanel(new FlowLayout());
		JPanel pSDef = new JPanel(new FlowLayout());
		JPanel pPor = new JPanel(new FlowLayout());
		JPanel pSPor = new JPanel(new FlowLayout());
		
		JLabel lblPosicion = new JLabel("Posición");
		JLabel lblTitulares = new JLabel("Titulares");
		JLabel lblSuplentes = new JLabel("Suplentes");
		
		sustituciones.add(lblPosicion);
		sustituciones.add(lblTitulares);
		sustituciones.add(lblSuplentes);
		
		sustituciones.add(new JLabel("DEL"));
		sustituciones.add(pDel);
		sustituciones.add(pSDel);
		
		sustituciones.add(new JLabel("MED"));	
		sustituciones.add(pMed);
		sustituciones.add(pSMed);
		
		sustituciones.add(new JLabel("DEF"));	
		sustituciones.add(pDef);
		sustituciones.add(pSDef);
		
		sustituciones.add(new JLabel("POR"));	
		sustituciones.add(pPor);
		sustituciones.add(pSPor);
		
		JButton btnDel1;
		JButton btnDel2;
		JButton btnSDel1;
		ArrayList<String> jugadores = this.controlador.getDelanteros();	
		if (jugadores!=null) {
			btnDel1 = new JButton(jugadores.get(0));
			btnDel2 = new JButton(jugadores.get(1));
			btnSDel1 = new JButton(jugadores.get(2));
		} else {
			btnDel1 = new JButton("DEL1");
			btnDel2 = new JButton("DEL2");
			btnSDel1 = new JButton("DEL suplente");
		}
		
		jugadores = this.controlador.getMedCampistas();
		
		JButton btnMed1;
		JButton btnMed2;
		JButton btnMed3;
		JButton btnMed4;
		JButton btnSMed1;
		if(jugadores!= null) {
			btnMed1 = new JButton(jugadores.get(0));
			btnMed2 = new JButton(jugadores.get(1));
			btnMed3 = new JButton(jugadores.get(2));
			btnMed4 = new JButton(jugadores.get(3));
			btnSMed1 = new JButton(jugadores.get(4));
		} else {
			btnMed1 = new JButton("MED1");
			btnMed2 = new JButton("MED2");
			btnMed3 = new JButton("MED3");
			btnMed4 = new JButton("MED4");
			btnSMed1 = new JButton("MED suplente");
		}
		JButton btnDef1;
		JButton btnDef2;
		JButton btnDef3;
		JButton btnDef4;
		JButton btnSDef1;
		jugadores = this.controlador.getDefensas();
		if(jugadores!= null) {
			btnDef1 = new JButton(jugadores.get(0));
			btnDef2 = new JButton(jugadores.get(1));
			btnDef3 = new JButton(jugadores.get(2));
			btnDef4 = new JButton(jugadores.get(3));
			btnSDef1 = new JButton(jugadores.get(4));
		} else {
			btnDef1 = new JButton("DEF1");
			btnDef2 = new JButton("DEF2");
			btnDef3 = new JButton("DEF3");
			btnDef4 = new JButton("DED4");
			btnSDef1 = new JButton("DEF suplente");
		}
		
		JButton btnPor1;
		JButton btnSPor1;
		jugadores = this.controlador.getArqueros();
		if(jugadores!=null) {
			btnPor1 = new JButton(jugadores.get(0));
			btnSPor1 = new JButton(jugadores.get(1));
		} else {
			btnPor1 = new JButton("POR1");
			btnSPor1 = new JButton("POR suplente");
		}
		
		pDel.add(btnDel1);
		pDel.add(btnDel2);
		pSDel.add(btnSDel1);
		pMed.add(btnMed1);
		pMed.add(btnMed2);
		pMed.add(btnMed3);
		pMed.add(btnMed4);
		pSMed.add(btnSMed1);
		pDef.add(btnDef1);
		pDef.add(btnDef2);
		pDef.add(btnDef3);
		pDef.add(btnDef4);
		pSDef.add(btnSDef1);	
		pPor.add(btnPor1);
		pSPor.add(btnSPor1);
		
		this.add(sustituciones);
		
		JPanel compraventa = new JPanel();
		compraventa.setLayout(new GridLayout(7,1));
		compraventa.add(new JLabel("Compra/Venta"));
		JButton hacer = new JButton("Hacer transaccion");
		
		compraventa.add(hacer);
		compraventa.add(new JLabel("Comprar"));
		JTextField comprar = new JTextField();
		JTextField vender = new JTextField();
		hacer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprar.getText().equals("") || vender.getText().equals(""))
					mensajeError("Ambos espacios deben llenarse");
				else {
					controlador.venderComprarJugadores("", vender.getText(), comprar.getText());
					origin.refreshinfo();
				}
				
			}
			
		});
		compraventa.add(comprar);
		compraventa.add(new JLabel("Vender"));
		compraventa.add(vender);
		compraventa.add(new JLabel("Dinero disponible: " + controlador.getMonto()));
		
		this.add(compraventa);
	}
	private void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje,"Error", JOptionPane.ERROR_MESSAGE); 					
	}
}
