package ies.piobaroja.dam2.accesoadatos.studyapp.vista;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.FichaModificarControlador;

public class FichaModificarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonModificarFicha;
	private JButton btnNewButtonBuscarFicha; // Botón para la búsqueda previa
	private FichaModificarControlador controlador;
	
	private JTextField textFieldIdFichaModificarFichaVista;
	private JTextField textFieldPreguntaModificarFichaVista;
	private JTextField textFieldRespuestaModificarFichaVista;
	
	public FichaModificarVista() {
		setLayout(null);
		controlador = new FichaModificarControlador(this);

		JLabel lblModificarFicha = new JLabel("Modificar Ficha");
		lblModificarFicha.setBounds(189, 15, 120, 17);
		add(lblModificarFicha);
		
		JLabel lblIdFichaVista = new JLabel("ID Ficha: ");
		lblIdFichaVista.setBounds(53, 50, 80, 17);
		add(lblIdFichaVista);
		
		textFieldIdFichaModificarFichaVista = new JTextField();
		textFieldIdFichaModificarFichaVista.setColumns(10);
		textFieldIdFichaModificarFichaVista.setBounds(140, 48, 110, 21);
		add(textFieldIdFichaModificarFichaVista);
		
		btnNewButtonBuscarFicha = new JButton("Buscar");
		btnNewButtonBuscarFicha.setBounds(260, 45, 85, 25);
		add(btnNewButtonBuscarFicha);
		// conexion de boton buscar con controlador
		btnNewButtonBuscarFicha.addActionListener(controlador);
		
		JLabel lblPreguntaFichaVista = new JLabel("Nueva Pregunta: ");
		lblPreguntaFichaVista.setBounds(53, 85, 130, 17);
		add(lblPreguntaFichaVista);
		
		textFieldPreguntaModificarFichaVista = new JTextField();
		textFieldPreguntaModificarFichaVista.setColumns(10);
		textFieldPreguntaModificarFichaVista.setBounds(185, 83, 225, 21);
		add(textFieldPreguntaModificarFichaVista);
		
		JLabel lblRespuestaFichaVista = new JLabel("Nueva Respuesta: ");
		lblRespuestaFichaVista.setBounds(53, 120, 130, 17);
		add(lblRespuestaFichaVista);
		
		textFieldRespuestaModificarFichaVista = new JTextField();
		textFieldRespuestaModificarFichaVista.setColumns(10);
		textFieldRespuestaModificarFichaVista.setBounds(185, 118, 225, 99);
		add(textFieldRespuestaModificarFichaVista);

		btnNewButtonModificarFicha = new JButton("Modificar");
		btnNewButtonModificarFicha.setBounds(169, 250, 105, 27);
		add(btnNewButtonModificarFicha);
		// conexion de boton modificar con controlador
		btnNewButtonModificarFicha.addActionListener(controlador);
	}
	
	// Metodos que recogen datos
	
	public String getIdFichaModificarFichaVista() {			
		return textFieldIdFichaModificarFichaVista.getText();
	}

	public String getPreguntaModificarFichaVista() {			
		return textFieldPreguntaModificarFichaVista.getText();
	}

	public String getRespuestaModificarFichaVista() {
		return textFieldRespuestaModificarFichaVista.getText();
	}
	
	// Metodos SET que usará el controlador al encontrar la ficha

	public void setPreguntaModificarFichaVista(String pregunta) {
		textFieldPreguntaModificarFichaVista.setText(pregunta);
	}

	public void setRespuestaModificarFichaVista(String respuesta) {
		textFieldRespuestaModificarFichaVista.setText(respuesta);
	}

	// Metodos de error y acierto
	
	public void OK() {
		JOptionPane.showMessageDialog(this, "Operación realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		textFieldIdFichaModificarFichaVista.setText("");
		textFieldPreguntaModificarFichaVista.setText("");
		textFieldRespuestaModificarFichaVista.setText("");
	}
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);		
	}
}