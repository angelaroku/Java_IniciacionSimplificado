package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.FicherosExportarControlador;

public class FicherosExportarVista extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldNombreFicheroExportarVista;
	private JButton btnExportarFicheroExportarVista;
	private FicherosExportarControlador controlador;
	
	private ButtonGroup grupoFormatosExportarVista;
	private ButtonGroup grupoEntidadesExportarVista;
	
	private JRadioButton rbXmlExportarVista;
	private JRadioButton rbJsonExportarVista;
	private JRadioButton rbObjExportarVista;
	
	private JRadioButton rbFichaExportarVista;
	private JRadioButton rbCajaExportarVista;
	private JRadioButton rbColeccionExportarVista;
	private JRadioButton rbStudyAppExportarVista;

	public FicherosExportarVista() {
		setLayout(null);
		controlador = new FicherosExportarControlador(this);

		JLabel lblTitulo = new JLabel("EXPORTAR DATOS A FICHERO");
		lblTitulo.setBounds(150, 15, 250, 17);
		add(lblTitulo);

		JLabel lblNombre = new JLabel("Nombre del fichero (sin extensión):");
		lblNombre.setBounds(53, 50, 250, 17);
		add(lblNombre);

		textFieldNombreFicheroExportarVista = new JTextField();
		textFieldNombreFicheroExportarVista.setBounds(53, 75, 300, 21);
		add(textFieldNombreFicheroExportarVista);

		JLabel lblFormatos = new JLabel("Selecciona el Formato:");
		lblFormatos.setBounds(53, 115, 200, 17);
		add(lblFormatos);

		rbXmlExportarVista = new JRadioButton("XML"); rbXmlExportarVista.setBounds(53, 140, 60, 23); add(rbXmlExportarVista);
		rbJsonExportarVista = new JRadioButton("JSON"); rbJsonExportarVista.setBounds(120, 140, 70, 23); add(rbJsonExportarVista);
		rbObjExportarVista = new JRadioButton("OBJ"); rbObjExportarVista.setBounds(200, 140, 60, 23); add(rbObjExportarVista);

		grupoFormatosExportarVista = new ButtonGroup();
		grupoFormatosExportarVista.add(rbXmlExportarVista); 
		grupoFormatosExportarVista.add(rbJsonExportarVista); 
		grupoFormatosExportarVista.add(rbObjExportarVista);
		rbXmlExportarVista.setSelected(true);

		JLabel lblEntidades = new JLabel("¿Qué deseas exportar?:");
		lblEntidades.setBounds(53, 180, 200, 17);
		add(lblEntidades);

		rbFichaExportarVista = new JRadioButton("Fichas"); rbFichaExportarVista.setBounds(53, 205, 80, 23); add(rbFichaExportarVista);
		rbCajaExportarVista = new JRadioButton("Cajas"); rbCajaExportarVista.setBounds(140, 205, 80, 23); add(rbCajaExportarVista);
		rbColeccionExportarVista = new JRadioButton("Colecciones"); rbColeccionExportarVista.setBounds(230, 205, 120, 23); add(rbColeccionExportarVista);
		
		rbStudyAppExportarVista = new JRadioButton("StudyApp"); 
		rbStudyAppExportarVista.setBounds(355, 205, 100, 23); 
		add(rbStudyAppExportarVista);

		grupoEntidadesExportarVista = new ButtonGroup();
		grupoEntidadesExportarVista.add(rbFichaExportarVista); 
		grupoEntidadesExportarVista.add(rbCajaExportarVista); 
		grupoEntidadesExportarVista.add(rbColeccionExportarVista);
		grupoEntidadesExportarVista.add(rbStudyAppExportarVista); 
		
		rbFichaExportarVista.setSelected(true); 

		btnExportarFicheroExportarVista = new JButton("Exportar");
		btnExportarFicheroExportarVista.setBounds(150, 260, 120, 30);
		add(btnExportarFicheroExportarVista);
		//conexion de boton con controlador
		btnExportarFicheroExportarVista.addActionListener(controlador);
	}
	// Métodos que recogen datos de la vista
	public String getNombreFicheroExportarVista() { 
		return textFieldNombreFicheroExportarVista.getText().trim(); 
	}
	
	public String getFormatoSeleccionadoExportarVista() {
		if (rbXmlExportarVista.isSelected()) return "xml";
		if (rbJsonExportarVista.isSelected()) return "json";
		return "obj";
	}

	public String getEntidadSeleccionadaExportarVista() {
		if (rbFichaExportarVista.isSelected()) return "Ficha";
		if (rbCajaExportarVista.isSelected()) return "Caja";
		if (rbStudyAppExportarVista.isSelected()) return "StuddyApp";
		return "Coleccion";
	}
	
	
	// Métodos de error y acierto
	public void OK() {
		JOptionPane.showMessageDialog(this, "Datos exportados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		textFieldNombreFicheroExportarVista.setText("");
	}

	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error en Exportación", JOptionPane.ERROR_MESSAGE);
	}
}