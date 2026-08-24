package ies.piobaroja.dam2.accesoadatos.studyapp.vista;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.CajaConsultarControlador;

public class CajaConsultarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonConsultarCaja;
	private CajaConsultarControlador controlador;
	
	private JTextField textFieldIdCajaConsultarCajaVista;
	private JTextArea textAreaResultadoConsultarCajaVista;

	public CajaConsultarVista() {
		setLayout(null);
		controlador = new CajaConsultarControlador(this);

		JLabel lblConsultarCaja = new JLabel("Consultar Caja");
		lblConsultarCaja.setBounds(189, 15, 120, 17);
		add(lblConsultarCaja);
		
		JLabel lblIdCajaVista = new JLabel("ID Caja: ");
		lblIdCajaVista.setBounds(53, 50, 70, 17);
		add(lblIdCajaVista);
		
		textFieldIdCajaConsultarCajaVista = new JTextField();
		textFieldIdCajaConsultarCajaVista.setColumns(10);
		textFieldIdCajaConsultarCajaVista.setBounds(120, 48, 110, 21);
		add(textFieldIdCajaConsultarCajaVista);
		
		btnNewButtonConsultarCaja = new JButton("Consultar");
		btnNewButtonConsultarCaja.setBounds(245, 45, 110, 25);
		add(btnNewButtonConsultarCaja);
		//conexion de boton con controlador
		btnNewButtonConsultarCaja.addActionListener(controlador);
		
		
		JLabel lblResultado = new JLabel("Datos de la Caja:");
		lblResultado.setBounds(53, 85, 120, 17);
		add(lblResultado);
		
		textAreaResultadoConsultarCajaVista = new JTextArea();
		textAreaResultadoConsultarCajaVista.setBounds(53, 105, 340, 168);
		textAreaResultadoConsultarCajaVista.setEditable(false); // no modificable por el usuario
		textAreaResultadoConsultarCajaVista.setLineWrap(true);   // ajuste automático de línea
		add(textAreaResultadoConsultarCajaVista);
	}
	
	//Metodos que recogen datos relacionados con más clases que la vista
	
	public String getIdCajaConsultarCajaVista() {			
		return textFieldIdCajaConsultarCajaVista.getText();
	}
	
	// Este único método set se encargará de pintar toda la información formateada que mande el controlador
	public void setTextAreaResultadoConsultarCajaVista(String textoResultado) {
		textAreaResultadoConsultarCajaVista.setText(textoResultado);
	}

	//Metodos de error y acierto
	
	public void OK() {
		// uso de 'this' para que se centre en esta ventana.
		JOptionPane.showMessageDialog(this, "Consulta realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	public void error(String mensaje) {
		// Mostramos el mensaje de error que nos mande el controlador
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
		// Si falla, limpiamos el área de texto anterior
		textAreaResultadoConsultarCajaVista.setText("");
	}
}