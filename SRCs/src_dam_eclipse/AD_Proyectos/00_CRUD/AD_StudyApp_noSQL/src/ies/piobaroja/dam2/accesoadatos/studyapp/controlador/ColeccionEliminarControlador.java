package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionEliminarVista;

public class ColeccionEliminarControlador implements ActionListener {
	private ColeccionEliminarVista vistaEliminarColeccion;
	private DAO_StudyApp dao;
	
	public ColeccionEliminarControlador(ColeccionEliminarVista vistaEliminarColeccion) {
		this.vistaEliminarColeccion = vistaEliminarColeccion;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String idColeccionVista = vistaEliminarColeccion.getIdColeccionEliminarColeccionVista();
		
		//Validacion y borrado por ID
		try {
			int id = Integer.parseInt(idColeccionVista.trim());
			
			//Comprobacion de eliminacion
			if (dao.eliminarColeccion(id)) {
				vistaEliminarColeccion.OK();
			} else {
				vistaEliminarColeccion.error("No existe ninguna colección con el ID especificado.");
			}
			
		} catch (NumberFormatException ex) {
			vistaEliminarColeccion.error("El ID introducido debe ser un número válido.");
		}
	}
}
