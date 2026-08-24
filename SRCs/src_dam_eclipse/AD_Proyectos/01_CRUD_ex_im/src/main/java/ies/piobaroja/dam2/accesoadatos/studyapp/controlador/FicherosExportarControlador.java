package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FicherosExportarVista;

public class FicherosExportarControlador implements ActionListener {
	private FicherosExportarVista vistaExportar;
	private DAO_StudyApp dao;

	public FicherosExportarControlador(FicherosExportarVista vistaExportar) {
		this.vistaExportar = vistaExportar;
		// conexion con la instancia del DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Recogemos los datos de la vista
		String nombreFichero = vistaExportar.getNombreFicheroExportarVista();
		String formato = vistaExportar.getFormatoSeleccionadoExportarVista();
		String entidad = vistaExportar.getEntidadSeleccionadaExportarVista();

		// Validacion de campo vacio
		if (nombreFichero.isEmpty()) {
			vistaExportar.error("El nombre del fichero no puede estar vacío.");
			return;
		}

		//Control de formato 
		if (formato.equals("csv")) {
			vistaExportar.error("El formato CSV no está implementado en el DAO (usa XML, JSON u OBJ).");
			return;
		}

		try {
			// exportacion segun entidad seleccionada
			if (entidad.equals("Ficha")) {
				dao.exportarFichas(nombreFichero, formato);
			} else if (entidad.equals("Caja")) {
				dao.exportarCajas(nombreFichero, formato);
			} else if (entidad.equals("Coleccion")) {
				dao.exportarColecciones(nombreFichero, formato);
			} else if (entidad.equalsIgnoreCase("StuddyApp") ) {
				// opcion de exportar todo el programa junto
				dao.exportarStuddyApp(nombreFichero, formato);
			}

			//si todo ha ido bien, aviso de éxito
			vistaExportar.OK();

		} catch (Exception ex) {
			vistaExportar.error("Error crítico al exportar: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}