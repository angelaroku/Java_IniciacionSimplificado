package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.FichaEliminarControlador;

public class FichaEliminarVista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButtonEliminarFicha;
	private FichaEliminarControlador controlador;
	
	private JTextField textFieldIdFichaEliminarFichaVista;
	
	public FichaEliminarVista() {
		setLayout(null);
		controlador = new FichaEliminarControlador(this);

		JLabel lblEliminarFicha = new JLabel("Eliminar Ficha");
		lblEliminarFicha.setBounds(189, 34, 120, 17);
		add(lblEliminarFicha);
		
		JLabel lblIdFichaVista = new JLabel("ID Ficha a eliminar: ");
		lblIdFichaVista.setBounds(53, 82, 130, 17);
		add(lblIdFichaVista);
		
		textFieldIdFichaEliminarFichaVista = new JTextField();
		textFieldIdFichaEliminarFichaVista.setColumns(10);
		textFieldIdFichaEliminarFichaVista.setBounds(185, 80, 182, 21);
		add(textFieldIdFichaEliminarFichaVista);

		btnNewButtonEliminarFicha = new JButton("Eliminar");
		btnNewButtonEliminarFicha.setBounds(172, 196, 105, 27);
		add(btnNewButtonEliminarFicha);
		// conexion de boton con controlador
		btnNewButtonEliminarFicha.addActionListener(controlador);
	}
	
	// Metodos que recogen datos
	
	public String getIdFichaEliminarFichaVista() {			
		return textFieldIdFichaEliminarFichaVista.getText();
	}

	// Metodos de error y acierto
	
	public void OK() {
		JOptionPane.showMessageDialog(this, "Ficha eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);		
		textFieldIdFichaEliminarFichaVista.setText("");
	}
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);		
	}
}

