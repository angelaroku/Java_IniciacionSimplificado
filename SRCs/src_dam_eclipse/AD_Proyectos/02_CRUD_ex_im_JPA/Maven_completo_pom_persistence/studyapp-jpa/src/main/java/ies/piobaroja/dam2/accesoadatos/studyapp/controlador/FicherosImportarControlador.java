package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FicherosImportarVista;

public class FicherosImportarControlador implements ActionListener {
	private FicherosImportarVista vistaImportar;
	private DAO_StudyApp dao;

	public FicherosImportarControlador(FicherosImportarVista vistaImportar) {
		this.vistaImportar = vistaImportar;
		// conexion con la instancia del DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Recogemos los datos de la vista
		String nombreFichero = vistaImportar.getNombreFicheroImportarVista();
		String formato = vistaImportar.getFormatoSeleccionadoImportarVista();
		String entidad = vistaImportar.getEntidadSeleccionadaImportarVista();

		// Validacion de campo vacio
		if (nombreFichero.isEmpty()) {
			vistaImportar.error("El nombre del fichero no puede estar vacío.");
			return;
		}

		//Control de formato 
		if (formato.equals("csv")) {
			vistaImportar.error("El formato CSV no está soportado para importar (usa XML, JSON u OBJ).");
			return;
		}

		try {
			// exportacion segun entidad seleccionada
			if (entidad.equals("Ficha")) {
				dao.importarFichas(nombreFichero, formato);
			} else if (entidad.equals("Caja")) {
				dao.importarCajas(nombreFichero, formato);
			} else if (entidad.equals("Coleccion")) {
				dao.importarColecciones(nombreFichero, formato);
			} else if (entidad.equalsIgnoreCase("StuddyApp")) {
				// opcion de exportar todo el programa junto
				dao.importarStuddyApp(nombreFichero, formato);
			} else {
				vistaImportar.error("Destino de importación no reconocido.");
				return;
			}

			//si todo ha ido bien, aviso de éxito
			vistaImportar.OK();

		} catch (Exception ex) {
			vistaImportar.error("Error crítico al importar: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}