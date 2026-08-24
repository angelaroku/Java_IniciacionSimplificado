package ies.piobaroja.dam2.accesoadatos.studyapp.vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ies.piobaroja.dam2.accesoadatos.studyapp.controlador.ConsultaPorClaseControlador;

public class ConsultaPorClaseVista extends JPanel {
	private static final long serialVersionUID = 1L;

	private JRadioButton rdbtnColeccion;
	private JRadioButton rdbtnCaja;
	private JRadioButton rdbtnFicha;
	private JButton btnConsultarClase;
	private ConsultaPorClaseControlador controlador;
	
	private JScrollPane scrollPaneTablaResultados;

	public ConsultaPorClaseVista() {
		setLayout(null);
		controlador = new ConsultaPorClaseControlador(this);

		JLabel lblModificarCaja = new JLabel("Consulta por Clase");
		lblModificarCaja.setFont(new Font("Dialog", Font.BOLD, 16));
		lblModificarCaja.setBounds(160, 15, 180, 20);
		add(lblModificarCaja);

		// Componentes de selección del tipo de clase
		rdbtnColeccion = new JRadioButton("Colección");
		rdbtnColeccion.setBounds(45, 50, 110, 25);
		rdbtnColeccion.setSelected(true);
		add(rdbtnColeccion);

		rdbtnCaja = new JRadioButton("Caja");
		rdbtnCaja.setBounds(185, 50, 90, 25);
		add(rdbtnCaja);

		rdbtnFicha = new JRadioButton("Ficha");
		rdbtnFicha.setBounds(315, 50, 90, 25);
		add(rdbtnFicha);

		ButtonGroup bgClasesEstudio = new ButtonGroup();
		bgClasesEstudio.add(rdbtnColeccion);
		bgClasesEstudio.add(rdbtnCaja);
		bgClasesEstudio.add(rdbtnFicha);

		btnConsultarClase = new JButton("Consultar");
		btnConsultarClase.setBounds(173, 90, 105, 27);
		add(btnConsultarClase);
		// conexion de boton consultar con controlador
		btnConsultarClase.addActionListener(controlador);

		JLabel lblResultados = new JLabel("Resultados de la consulta:");
		lblResultados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblResultados.setBounds(45, 135, 200, 17);
		add(lblResultados);
	}

	// Metodos que recogen datos relacionados con más clases que la vista
	
	public int getClaseSeleccionada() {
		if (rdbtnColeccion.isSelected()) {
			return 0; // Representa Colección
		} else if (rdbtnCaja.isSelected()) {
			return 1; // Representa Caja
		} else {
			return 2; // Representa Ficha
		}
	}

	// Métodos SET para que el controlador dibuje la tabla correspondiente

	public void setTablaDatos(String[][] datos, String[] cabecera) {
		// Limpieza del scroll anterior si existiera para refrescar contenido
		if (scrollPaneTablaResultados != null) {
			remove(scrollPaneTablaResultados);
		}

		JTable tablaResultados = new JTable(datos, cabecera) {
			private static final long serialVersionUID = 1L;
			// Se hacen las celdas no editables.
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaResultados.setGridColor(Color.BLACK);
		
		scrollPaneTablaResultados = new JScrollPane(tablaResultados);
		scrollPaneTablaResultados.setBounds(45, 165, 370, 140);
		add(scrollPaneTablaResultados);

		// Forzamos la actualización visual del panel
		revalidate();
		repaint();
	}

	// Metodos de error y acierto
	
	public void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}