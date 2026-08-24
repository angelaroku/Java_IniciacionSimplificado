package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.ColeccionConsultarControlador;

public class ColeccionConsultarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonConsultarColeccion;
	private ColeccionConsultarControlador controlador;
	
	private JTextField textFieldIdColeccionConsultarColeccionVista;
	private JTextArea textAreaResultadoConsultarColeccionVista;

	public ColeccionConsultarVista() {
		setLayout(null);
		controlador = new ColeccionConsultarControlador(this);

		JLabel lblConsultarColeccion = new JLabel("Consultar Colección");
		lblConsultarColeccion.setBounds(189, 15, 150, 17);
		add(lblConsultarColeccion);
		
		JLabel lblIdColeccionVista = new JLabel("ID Colección: ");
		lblIdColeccionVista.setBounds(53, 50, 100, 17);
		add(lblIdColeccionVista);
		
		textFieldIdColeccionConsultarColeccionVista = new JTextField();
		textFieldIdColeccionConsultarColeccionVista.setColumns(10);
		textFieldIdColeccionConsultarColeccionVista.setBounds(160, 48, 90, 21);
		add(textFieldIdColeccionConsultarColeccionVista);
		
		btnNewButtonConsultarColeccion = new JButton("Consultar");
		btnNewButtonConsultarColeccion.setBounds(265, 45, 110, 25);
		add(btnNewButtonConsultarColeccion);
		// conexion de boton con controlador
		btnNewButtonConsultarColeccion.addActionListener(controlador);
		
		JLabel lblResultado = new JLabel("Datos de la Colección:");
		lblResultado.setBounds(53, 85, 150, 17);
		add(lblResultado);
		
		textAreaResultadoConsultarColeccionVista = new JTextArea();
		textAreaResultadoConsultarColeccionVista.setBounds(53, 105, 340, 75);
		textAreaResultadoConsultarColeccionVista.setEditable(false);
		textAreaResultadoConsultarColeccionVista.setLineWrap(true);
		add(textAreaResultadoConsultarColeccionVista);
	}
	
	// Metodos que recogen datos
	
	public String getIdColeccionConsultarColeccionVista() {			
		return textFieldIdColeccionConsultarColeccionVista.getText();
	}
	
	// Metodos SET que usará el controlador al encontrar la colección
	public void setTextAreaResultadoConsultarColeccionVista(String textoResultado) {
		textAreaResultadoConsultarColeccionVista.setText(textoResultado);
	}

	// Metodos de error y acierto
	
	public void OK() {
		JOptionPane.showMessageDialog(this, "Consulta realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
		textAreaResultadoConsultarColeccionVista.setText("");
	}
}


	
