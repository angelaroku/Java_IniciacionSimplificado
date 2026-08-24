package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.ColeccionCrearControlador;

public class ColeccionCrearVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonCrearColeccion;
	private ColeccionCrearControlador controlador;
	
	private JTextField textFieldTemaCrearColeccionVista;
	private JTextField textFieldIdsCajasColeccionVista; 
	
	public ColeccionCrearVista() {
		setLayout(null);
		controlador = new ColeccionCrearControlador(this);

		JLabel lblAltaColeccion = new JLabel("Alta Colección");
		lblAltaColeccion.setBounds(189, 20, 150, 17);
		add(lblAltaColeccion);
		
		JLabel lblTemaColeccionVista = new JLabel("Tema: ");
		lblTemaColeccionVista.setBounds(53, 60, 100, 17);
		add(lblTemaColeccionVista);
		
		textFieldTemaCrearColeccionVista = new JTextField();
		textFieldTemaCrearColeccionVista.setColumns(10);
		textFieldTemaCrearColeccionVista.setBounds(165, 58, 245, 21);
		add(textFieldTemaCrearColeccionVista);
		
		JLabel lblIdsCajasColeccionVista = new JLabel("IDs Cajas asociadas a la colección separados por comas (1,2,3): ");
		lblIdsCajasColeccionVista.setBounds(53, 100, 340, 17);
		add(lblIdsCajasColeccionVista);
		
		textFieldIdsCajasColeccionVista = new JTextField();
		textFieldIdsCajasColeccionVista.setColumns(10);
		textFieldIdsCajasColeccionVista.setBounds(53, 125, 357, 81);
		add(textFieldIdsCajasColeccionVista);
		
		btnNewButtonCrearColeccion = new JButton("Crear");
		btnNewButtonCrearColeccion.setBounds(173, 225, 105, 27);
		add(btnNewButtonCrearColeccion);
		// conexion de boton con controlador
		btnNewButtonCrearColeccion.addActionListener(controlador);
	}
	
	// Metodos que recogen datos
	
	public String getTemaCrearColeccionVista() {
		return textFieldTemaCrearColeccionVista.getText();
	}

	public String getTextFieldIdsCajasColeccionVista() {
		return textFieldIdsCajasColeccionVista.getText();
	}

	// Metodos de error y acierto
	
	public void OK() {
		JOptionPane.showMessageDialog(this, "Colección creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		textFieldTemaCrearColeccionVista.setText("");
		textFieldIdsCajasColeccionVista.setText("");
	}
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
	}
}