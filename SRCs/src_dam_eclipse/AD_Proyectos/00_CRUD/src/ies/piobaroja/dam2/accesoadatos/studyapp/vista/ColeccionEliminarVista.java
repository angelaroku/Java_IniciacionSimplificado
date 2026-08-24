package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.ColeccionEliminarControlador;
public class ColeccionEliminarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonEliminarColeccion;
	private ColeccionEliminarControlador controlador;
	
	private JTextField textFieldIdColeccionEliminarColeccionVista;
	
	public ColeccionEliminarVista() {
		setLayout(null);
		controlador = new ColeccionEliminarControlador(this);

		JLabel lblEliminarColeccion = new JLabel("Eliminar Colección");
		lblEliminarColeccion.setBounds(189, 34, 150, 17);
		add(lblEliminarColeccion);
		
		JLabel lblIdColeccionVista = new JLabel("ID Colección a eliminar: ");
		lblIdColeccionVista.setBounds(53, 82, 150, 17);
		add(lblIdColeccionVista);
		
		textFieldIdColeccionEliminarColeccionVista = new JTextField();
		textFieldIdColeccionEliminarColeccionVista.setColumns(10);
		textFieldIdColeccionEliminarColeccionVista.setBounds(210, 80, 157, 21);
		add(textFieldIdColeccionEliminarColeccionVista);

		btnNewButtonEliminarColeccion = new JButton("Eliminar");
		btnNewButtonEliminarColeccion.setBounds(172, 196, 105, 27);
		add(btnNewButtonEliminarColeccion);
		// conexion de boton con controlador
		btnNewButtonEliminarColeccion.addActionListener(controlador);
	}
	
	// Metodos que recogen datos
	
	public String getIdColeccionEliminarColeccionVista() {			
		return textFieldIdColeccionEliminarColeccionVista.getText();
	}

	// Metodos de error y acierto
	
	public void OK() {
		JOptionPane.showMessageDialog(this, "Colección eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		textFieldIdColeccionEliminarColeccionVista.setText("");
	}
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
	}
}