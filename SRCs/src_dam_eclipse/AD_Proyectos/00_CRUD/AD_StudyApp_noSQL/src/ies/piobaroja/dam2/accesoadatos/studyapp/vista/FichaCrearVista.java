package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JPanel;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.FichaCrearControlador;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FichaCrearVista extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonCrearFicha;
	private FichaCrearControlador controlador;
	
	private JTextField textFieldContenidoPreguntaFichaVista;
	private JTextField textFieldContenidoRespuestaFichaVista;
	
	public FichaCrearVista() {
		setLayout(null);
		controlador=new FichaCrearControlador(this);

		
		JLabel lblAltaFicha = new JLabel("Alta Ficha");
		lblAltaFicha.setBounds(189, 34, 63, 17);
		add(lblAltaFicha);
		
		JLabel lblPreguntaFichaVista = new JLabel("Pregunta: ");
		lblPreguntaFichaVista.setBounds(53, 82, 83, 17);
		add(lblPreguntaFichaVista);
		
		textFieldContenidoPreguntaFichaVista = new JTextField();
		textFieldContenidoPreguntaFichaVista.setColumns(10);
		textFieldContenidoPreguntaFichaVista.setBounds(147, 80, 220, 21);
		add(textFieldContenidoPreguntaFichaVista);
		
		JLabel lblRespuestaFichaVista = new JLabel("Respuesta: ");
		lblRespuestaFichaVista.setBounds(53, 121, 71, 17);
		add(lblRespuestaFichaVista);
		
		textFieldContenidoRespuestaFichaVista = new JTextField();
		textFieldContenidoRespuestaFichaVista.setColumns(10);
		textFieldContenidoRespuestaFichaVista.setBounds(147, 121, 220, 51);
		add(textFieldContenidoRespuestaFichaVista);
		
		btnNewButtonCrearFicha = new JButton("Crear");
		btnNewButtonCrearFicha.setBounds(172, 196, 105, 27);
		add(btnNewButtonCrearFicha);
		//conexion de boton con controlador
		btnNewButtonCrearFicha.addActionListener(controlador);
	}
	
	//Metodos que recogen datos relacionados con más clases que la vista
	
	 	public String getContenidoPreguntaFichaVista() {			
			return textFieldContenidoPreguntaFichaVista.getText();
		}
	

		public String getContenidoRespuestaFichaVista() {
			return textFieldContenidoRespuestaFichaVista.getText();
		}
	
	
		//Metodos de error y acierto
		
		public void OK() {
			// uso de 'this' para que se centre en esta ventana.
			JOptionPane.showMessageDialog(this, "Ficha creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
			// vaciado de campos para poder crear la siguiente ficha más rápido
			textFieldContenidoPreguntaFichaVista.setText("");
			textFieldContenidoRespuestaFichaVista.setText("");
		}
		
		public void error(String mensaje) {
			// Mostramos el mensaje de error que nos mande el controlador
			JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
		}
	 
}
