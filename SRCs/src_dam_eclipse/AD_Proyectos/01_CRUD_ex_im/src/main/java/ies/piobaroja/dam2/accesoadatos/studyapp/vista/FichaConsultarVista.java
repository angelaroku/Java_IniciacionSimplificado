package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.FichaConsultarControlador;

public class FichaConsultarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonConsultarFicha;
	private FichaConsultarControlador controlador;
	
	private JTextField textFieldIdFichaConsultarFichaVista;
	private JTextArea textAreaResultadoConsultarFichaVista;

	public FichaConsultarVista() {
		setLayout(null);
		controlador = new FichaConsultarControlador(this);

		JLabel lblConsultarFicha = new JLabel("Consultar Ficha");
		lblConsultarFicha.setBounds(189, 15, 120, 17);
		add(lblConsultarFicha);
		
		JLabel lblIdFichaVista = new JLabel("ID Ficha: ");
		lblIdFichaVista.setBounds(53, 50, 80, 17);
		add(lblIdFichaVista);
		
		textFieldIdFichaConsultarFichaVista = new JTextField();
		textFieldIdFichaConsultarFichaVista.setColumns(10);
		textFieldIdFichaConsultarFichaVista.setBounds(140, 48, 110, 21);
		add(textFieldIdFichaConsultarFichaVista);
		
		btnNewButtonConsultarFicha = new JButton("Consultar");
		btnNewButtonConsultarFicha.setBounds(265, 45, 110, 25);
		add(btnNewButtonConsultarFicha);
		// conexion de boton con controlador
		btnNewButtonConsultarFicha.addActionListener(controlador);
		
		JLabel lblResultado = new JLabel("Datos de la Ficha:");
		lblResultado.setBounds(53, 85, 150, 17);
		add(lblResultado);
		
		textAreaResultadoConsultarFichaVista = new JTextArea();
		textAreaResultadoConsultarFichaVista.setBounds(53, 105, 340, 155);
		textAreaResultadoConsultarFichaVista.setEditable(false);
		textAreaResultadoConsultarFichaVista.setLineWrap(true);
		add(textAreaResultadoConsultarFichaVista);
	}
	
	// Metodos que recogen datos
	
	public String getIdFichaConsultarFichaVista() {			
		return textFieldIdFichaConsultarFichaVista.getText();
	}
	
	// Metodos SET que usará el controlador al encontrar la ficha
	public void setTextAreaResultadoConsultarFichaVista(String textoResultado) {
		textAreaResultadoConsultarFichaVista.setText(textoResultado);
	}

	// Metodos de error y acierto
	
	public void OK() {
		JOptionPane.showMessageDialog(this, "Consulta realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
		textAreaResultadoConsultarFichaVista.setText("");
	}
}