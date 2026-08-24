package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.FicherosImportarControlador;

public class FicherosImportarVista extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldNombreFicheroImportarVista;
	private JButton btnImportarFicheroImportarVista;
	private FicherosImportarControlador controlador;
	
	private ButtonGroup grupoFormatosImportarVista;
	private ButtonGroup grupoEntidadesImportarVista;
	
	private JRadioButton rbXmlImportarVista;
	private JRadioButton rbJsonImportarVista;
	private JRadioButton rbObjImportarVista;
	
	private JRadioButton rbFichaImportarVista;
	private JRadioButton rbCajaImportarVista;
	private JRadioButton rbColeccionImportarVista;
	private JRadioButton rbStudyAppImportarVista;

	public FicherosImportarVista() {
		setLayout(null);
		controlador = new FicherosImportarControlador(this);

		JLabel lblTitulo = new JLabel("IMPORTAR DATOS DESDE FICHERO");
		lblTitulo.setBounds(150, 15, 250, 17);
		add(lblTitulo);

		JLabel lblNombre = new JLabel("Nombre del fichero a importar (sin extensión):");
		lblNombre.setBounds(53, 50, 300, 17);
		add(lblNombre);

		textFieldNombreFicheroImportarVista = new JTextField();
		textFieldNombreFicheroImportarVista.setBounds(53, 75, 300, 21);
		add(textFieldNombreFicheroImportarVista);

		JLabel lblFormatos = new JLabel("Selecciona el Formato del archivo:");
		lblFormatos.setBounds(53, 115, 250, 17);
		add(lblFormatos);

		rbXmlImportarVista = new JRadioButton("XML"); rbXmlImportarVista.setBounds(53, 140, 60, 23); add(rbXmlImportarVista);
		rbJsonImportarVista = new JRadioButton("JSON"); rbJsonImportarVista.setBounds(120, 140, 70, 23); add(rbJsonImportarVista);
		rbObjImportarVista = new JRadioButton("OBJ"); rbObjImportarVista.setBounds(200, 140, 60, 23); add(rbObjImportarVista);

		grupoFormatosImportarVista = new ButtonGroup();
		grupoFormatosImportarVista.add(rbXmlImportarVista); 
		grupoFormatosImportarVista.add(rbJsonImportarVista); 
		grupoFormatosImportarVista.add(rbObjImportarVista);
		rbXmlImportarVista.setSelected(true);

		JLabel lblEntidades = new JLabel("¿Dónde deseas cargar los datos?:");
		lblEntidades.setBounds(53, 180, 250, 17);
		add(lblEntidades);

		rbFichaImportarVista = new JRadioButton("Fichas"); rbFichaImportarVista.setBounds(53, 205, 80, 23); add(rbFichaImportarVista);
		rbCajaImportarVista = new JRadioButton("Cajas"); rbCajaImportarVista.setBounds(140, 205, 80, 23); add(rbCajaImportarVista);
		rbColeccionImportarVista = new JRadioButton("Colecciones"); rbColeccionImportarVista.setBounds(230, 205, 120, 23); add(rbColeccionImportarVista);
		
		rbStudyAppImportarVista = new JRadioButton("StudyApp");
		rbStudyAppImportarVista.setBounds(355, 205, 100, 23);
		add(rbStudyAppImportarVista);

		grupoEntidadesImportarVista = new ButtonGroup();
		grupoEntidadesImportarVista.add(rbFichaImportarVista); 
		grupoEntidadesImportarVista.add(rbCajaImportarVista); 
		grupoEntidadesImportarVista.add(rbColeccionImportarVista);
		grupoEntidadesImportarVista.add(rbStudyAppImportarVista);
		
		rbFichaImportarVista.setSelected(true);

		btnImportarFicheroImportarVista = new JButton("Importar");
		btnImportarFicheroImportarVista.setBounds(150, 260, 120, 30);
		add(btnImportarFicheroImportarVista);
		//conexion de boton con controlador
		btnImportarFicheroImportarVista.addActionListener(controlador);
	}
	// Métodos que recogen datos de la vista
	public String getNombreFicheroImportarVista() { 
		return textFieldNombreFicheroImportarVista.getText().trim(); 
	}
	
	public String getFormatoSeleccionadoImportarVista() {
		if (rbXmlImportarVista.isSelected()) return "xml";
		if (rbJsonImportarVista.isSelected()) return "json";
		return "obj";
	}

	public String getEntidadSeleccionadaImportarVista() {
		if (rbFichaImportarVista.isSelected()) return "Ficha";
		if (rbCajaImportarVista.isSelected()) return "Caja";
		if (rbStudyAppImportarVista.isSelected()) return "StuddyApp"; 
		return "Coleccion";
	}
	
	// Métodos de error y acierto
	public void OK() {
		JOptionPane.showMessageDialog(this, "Datos importados e integrados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		textFieldNombreFicheroImportarVista.setText("");
	}

	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error en Importación", JOptionPane.ERROR_MESSAGE);
	}
}
