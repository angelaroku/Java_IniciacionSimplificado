package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.CajaModificarControlador;

public class CajaModificarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonModificarCaja;
	private JButton btnNewButtonBuscarCaja; 
	private CajaModificarControlador controlador;
	
	private JTextField textFieldIdCajaModificarCajaVista;
	private JTextField textFieldPeriocidadModificarCajaVista;
	private JTextField textFieldIdsFichasCajaVista;
	
	public CajaModificarVista() {
		setLayout(null);
		controlador = new CajaModificarControlador(this);

		JLabel lblModificarCaja = new JLabel("Modificar Caja");
		lblModificarCaja.setBounds(189, 15, 120, 17);
		add(lblModificarCaja);
		
		JLabel lblIdCajaVista = new JLabel("ID Caja: ");
		lblIdCajaVista.setBounds(53, 50, 80, 17);
		add(lblIdCajaVista);
		
		// Acortamos el campo para que quepa el botón Buscar al lado
		textFieldIdCajaModificarCajaVista = new JTextField();
		textFieldIdCajaModificarCajaVista.setColumns(10);
		textFieldIdCajaModificarCajaVista.setBounds(140, 48, 110, 21);
		add(textFieldIdCajaModificarCajaVista);
		
		btnNewButtonBuscarCaja = new JButton("Buscar");
		btnNewButtonBuscarCaja.setBounds(260, 45, 85, 25);
		add(btnNewButtonBuscarCaja);
		// conexion de boton buscar con controlador
		btnNewButtonBuscarCaja.addActionListener(controlador);
		
		JLabel lblPeriocidadCajaVista = new JLabel("Nueva Periodicidad: ");
		lblPeriocidadCajaVista.setBounds(53, 94, 130, 21);
		add(lblPeriocidadCajaVista);
		
		textFieldPeriocidadModificarCajaVista = new JTextField();
		textFieldPeriocidadModificarCajaVista.setColumns(10);
		textFieldPeriocidadModificarCajaVista.setBounds(192, 94, 182, 21);
		add(textFieldPeriocidadModificarCajaVista);
		
		JLabel lblIdsFichasCajaVista = new JLabel("IDs Fichas Asociadas a la Caja: ");
		lblIdsFichasCajaVista.setBounds(53, 128, 163, 17);
		add(lblIdsFichasCajaVista);
		
		textFieldIdsFichasCajaVista = new JTextField();
		textFieldIdsFichasCajaVista.setColumns(10);
		textFieldIdsFichasCajaVista.setBounds(53, 153, 357, 81);
		add(textFieldIdsFichasCajaVista);

		btnNewButtonModificarCaja = new JButton("Modificar");
		btnNewButtonModificarCaja.setBounds(173, 245, 105, 27);
		add(btnNewButtonModificarCaja);
		//conexion de boton modificar con controlador
		btnNewButtonModificarCaja.addActionListener(controlador);
	}
	
	//Metodos que recogen datos relacionados con más clases que la vista
	
	public String getIdCajaModificarCajaVista() {			
		return textFieldIdCajaModificarCajaVista.getText();
	}

	public String getPeriocidadModificarCajaVista() {
		return textFieldPeriocidadModificarCajaVista.getText();
	}

	public String getTextFieldIdsFichasCajaVista() {
		return textFieldIdsFichasCajaVista.getText();
	}
	
	// Métodos SET para que el controlador rellene los campos tras pulsar "Buscar"

	public void setPeriocidadModificarCajaVista(String periodicidad) {
		textFieldPeriocidadModificarCajaVista.setText(periodicidad);
	}

	public void setTextFieldIdsFichasCajaVista(String idsFichas) {
		textFieldIdsFichasCajaVista.setText(idsFichas);
	}

	//Metodos de error y acierto
	
	public void OK() {
		// uso de 'this' para que se centre en esta ventana.
		JOptionPane.showMessageDialog(this, "Operación realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		// vaciado de campos
		textFieldIdCajaModificarCajaVista.setText("");
		textFieldPeriocidadModificarCajaVista.setText("");
		textFieldIdsFichasCajaVista.setText("");
	}
	
	public void error(String mensaje) {
		// Mostramos el mensaje de error que nos mande el controlador
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);		
	}
}