package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.CajaCrearControlador;
public class CajaCrearVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonCrearCaja;
	private CajaCrearControlador controlador;
	private JTextField textFieldPeriocidadCrearCajaVista;
	private JTextField textFieldIdsFichasCajaVista;
	
	public CajaCrearVista() {
		setLayout(null);
		controlador = new CajaCrearControlador(this);

		JLabel lblAltaCaja = new JLabel("Alta Caja");
		lblAltaCaja.setBounds(189, 20, 120, 17);
		add(lblAltaCaja);
		
		JLabel lblPeriocidadCajaVista = new JLabel("Numero periodicidad: ");
		lblPeriocidadCajaVista.setBounds(53, 69, 141, 17);
		add(lblPeriocidadCajaVista);
		
		textFieldPeriocidadCrearCajaVista = new JTextField();
		textFieldPeriocidadCrearCajaVista.setColumns(10);
		textFieldPeriocidadCrearCajaVista.setBounds(201, 67, 161, 21);
		add(textFieldPeriocidadCrearCajaVista);
		
		JLabel lblIdsFichasCajaVista = new JLabel("IDs Fichas asociadas a la caja separados por comas (1,2,3): ");
		lblIdsFichasCajaVista.setBounds(53, 113, 326, 17);
		add(lblIdsFichasCajaVista);
		
		textFieldIdsFichasCajaVista = new JTextField();
		textFieldIdsFichasCajaVista.setColumns(10);
		textFieldIdsFichasCajaVista.setBounds(53, 141, 326, 94);
		add(textFieldIdsFichasCajaVista);
		
		btnNewButtonCrearCaja = new JButton("Crear");
		btnNewButtonCrearCaja.setBounds(165, 250, 105, 27);
		add(btnNewButtonCrearCaja);
		//conexion de boton con controlador
		btnNewButtonCrearCaja.addActionListener(controlador);
	}
	
	//Metodos que recogen datos relacionados con más clases que la vista
	
	public String getPeriocidadCrearCajaVista() {
		return textFieldPeriocidadCrearCajaVista.getText();
	}

	public String getTextFieldIdsFichasCajaVista() {
		return textFieldIdsFichasCajaVista.getText();
	}

	//Metodos de error y acierto
	
	public void OK() {
		// uso de 'this' para que se centre en esta ventana.
		JOptionPane.showMessageDialog(this, "Caja creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		// vaciado de campos para poder crear la siguiente ficha más rápido
		textFieldPeriocidadCrearCajaVista.setText("");
		textFieldIdsFichasCajaVista.setText("");
	}
	
	public void error(String mensaje) {
		// Mostramos el mensaje de error que nos mande el controlador
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
	}
}