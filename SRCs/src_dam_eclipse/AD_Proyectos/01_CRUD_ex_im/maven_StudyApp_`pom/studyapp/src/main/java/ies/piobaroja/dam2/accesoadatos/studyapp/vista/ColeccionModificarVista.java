package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.ColeccionModificarControlador;

public class ColeccionModificarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonModificarColeccion;
	private JButton btnNewButtonBuscarColeccion;
	private ColeccionModificarControlador controlador;
	
	private JTextField textFieldIdColeccionModificarColeccionVista;
	private JTextField textFieldTemaModificarColeccionVista;
	private JTextField textFieldIdsCajasColeccionVista; 
	
	public ColeccionModificarVista() {
		setLayout(null);
		controlador = new ColeccionModificarControlador(this);

		JLabel lblModificarColeccion = new JLabel("Modificar Colección");
		lblModificarColeccion.setBounds(189, 15, 150, 17);
		add(lblModificarColeccion);
		
		JLabel lblIdColeccionVista = new JLabel("ID Colección: ");
		lblIdColeccionVista.setBounds(53, 50, 100, 17);
		add(lblIdColeccionVista);
		
		textFieldIdColeccionModificarColeccionVista = new JTextField();
		textFieldIdColeccionModificarColeccionVista.setColumns(10);
		textFieldIdColeccionModificarColeccionVista.setBounds(160, 48, 90, 21);
		add(textFieldIdColeccionModificarColeccionVista);
		
		btnNewButtonBuscarColeccion = new JButton("Buscar");
		btnNewButtonBuscarColeccion.setBounds(260, 45, 85, 25);
		add(btnNewButtonBuscarColeccion);
		// conexion de boton buscar con controlador
		btnNewButtonBuscarColeccion.addActionListener(controlador);
		
		JLabel lblTemaColeccionVista = new JLabel("Nuevo Tema: ");
		lblTemaColeccionVista.setBounds(53, 85, 100, 17);
		add(lblTemaColeccionVista);
		
		textFieldTemaModificarColeccionVista = new JTextField();
		textFieldTemaModificarColeccionVista.setColumns(10);
		textFieldTemaModificarColeccionVista.setBounds(165, 83, 245, 21);
		add(textFieldTemaModificarColeccionVista);
		
		JLabel lblIdsCajasColeccionVista = new JLabel("IDs Cajas Asociadas a la Colección: ");
		lblIdsCajasColeccionVista.setBounds(53, 120, 250, 17);
		add(lblIdsCajasColeccionVista);
		
		textFieldIdsCajasColeccionVista = new JTextField();
		textFieldIdsCajasColeccionVista.setColumns(10);
		textFieldIdsCajasColeccionVista.setBounds(53, 145, 357, 81);
		add(textFieldIdsCajasColeccionVista);

		btnNewButtonModificarColeccion = new JButton("Modificar");
		btnNewButtonModificarColeccion.setBounds(173, 240, 105, 27);
		add(btnNewButtonModificarColeccion);
		// conexion de boton modificar con controlador
		btnNewButtonModificarColeccion.addActionListener(controlador);
	}
	
	// Metodos GET para recuperar datos
	
	public String getIdColeccionModificarColeccionVista() {			
		return textFieldIdColeccionModificarColeccionVista.getText();
	}

	public String getTemaModificarColeccionVista() {			
		return textFieldTemaModificarColeccionVista.getText();
	}

	public String getTextFieldIdsCajasColeccionVista() {
		return textFieldIdsCajasColeccionVista.getText();
	}
	
	// Metodos SET que usará el controlador al encontrar la colección

	public void setTemaModificarColeccionVista(String tema) {
		textFieldTemaModificarColeccionVista.setText(tema);
	}

	public void setTextFieldIdsCajasColeccionVista(String idsCajas) {
		textFieldIdsCajasColeccionVista.setText(idsCajas);
	}

	// Metodos de error y acierto
	
	public void OK() {
		JOptionPane.showMessageDialog(this, "Operación realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		textFieldIdColeccionModificarColeccionVista.setText("");
		textFieldTemaModificarColeccionVista.setText("");
		textFieldIdsCajasColeccionVista.setText("");
	}
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);		
	}
}

