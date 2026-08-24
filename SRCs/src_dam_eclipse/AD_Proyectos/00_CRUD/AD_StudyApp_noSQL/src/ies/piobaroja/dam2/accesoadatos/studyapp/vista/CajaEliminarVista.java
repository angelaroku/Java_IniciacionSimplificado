package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.CajaEliminarControlador;

public class CajaEliminarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonEliminarCaja;
	private CajaEliminarControlador controlador;
	
	private JTextField textFieldIdCajaEliminarCajaVista;
	
	public CajaEliminarVista() {
		setLayout(null);
		controlador = new CajaEliminarControlador(this);

		JLabel lblEliminarCaja = new JLabel("Eliminar Caja");
		lblEliminarCaja.setBounds(189, 34, 120, 17);
		add(lblEliminarCaja);
		
		JLabel lblIdCajaVista = new JLabel("ID Caja a eliminar: ");
		lblIdCajaVista.setBounds(53, 82, 120, 17);
		add(lblIdCajaVista);
		
		textFieldIdCajaEliminarCajaVista = new JTextField();
		textFieldIdCajaEliminarCajaVista.setColumns(10);
		textFieldIdCajaEliminarCajaVista.setBounds(185, 80, 182, 21);
		add(textFieldIdCajaEliminarCajaVista);

		btnNewButtonEliminarCaja = new JButton("Eliminar");
		btnNewButtonEliminarCaja.setBounds(172, 196, 105, 27);
		add(btnNewButtonEliminarCaja);
		//conexion de boton con controlador
		btnNewButtonEliminarCaja.addActionListener(controlador);
	}
	
	//Metodos que recogen datos relacionados con más clases que la vista
	
	public String getIdCajaEliminarCajaVista() {			
		return textFieldIdCajaEliminarCajaVista.getText();
	}

	//Metodos de error y acierto
	
	public void OK() {
		// uso de 'this' para que se centre en esta ventana.
		JOptionPane.showMessageDialog(this, "Caja eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		// vaciado de campos para poder crear la siguiente ficha más rápido
		textFieldIdCajaEliminarCajaVista.setText("");
	}
	
	public void error(String mensaje) {
		// Mostramos el mensaje de error que nos mande el controlador
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
	}
}