package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ConsultaPorClaseVista;

public class ConsultaPorClaseControlador implements ActionListener {
	private ConsultaPorClaseVista vistaConsulta;
	private DAO_StudyApp dao;

	public ConsultaPorClaseControlador(ConsultaPorClaseVista vistaConsulta) {
		this.vistaConsulta = vistaConsulta;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Identificacion del boton pulsado
		String botonPulsado = e.getActionCommand();

		//Accion Consultar datos por Clase
		if (botonPulsado.equals("Consultar")) {
			int claseAEvaluar = vistaConsulta.getClaseSeleccionada();
			String[][] resultadoMatriz = null;
			String[] cabeceraTabla = null;

			// Procesamiento según la clase seleccionada
			if (claseAEvaluar == 0) {
				// Caso Colección
				cabeceraTabla = new String[]{"ID Colección", "Tema"};
				resultadoMatriz = dao.obtenerMatrizColecciones(); 
			} 
			else if (claseAEvaluar == 1) {
				// Caso Caja
				cabeceraTabla = new String[]{"ID Caja", "Periodicidad"};
				resultadoMatriz = dao.obtenerMatrizCajas();
			} 
			else if (claseAEvaluar == 2) {
				// Caso Ficha
				cabeceraTabla = new String[]{"ID Ficha", "Pregunta", "Respuesta"};
				resultadoMatriz = dao.obtenerMatrizFichas();
			}

			// Inyección de la información procesada en la vista
			if (resultadoMatriz != null) {
				vistaConsulta.setTablaDatos(resultadoMatriz, cabeceraTabla);
			} else {
				vistaConsulta.error("No se encontraron registros o el origen de datos está vacío.");
			}
		}
	}
}