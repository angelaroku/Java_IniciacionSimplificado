package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaEliminarVista;

public class CajaEliminarControlador implements ActionListener {
	private CajaEliminarVista vistaEliminarCaja;
	private DAO_StudyApp dao;
	
	public CajaEliminarControlador(CajaEliminarVista vistaEliminarCaja) {
		this.vistaEliminarCaja = vistaEliminarCaja;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String idCajaVista = vistaEliminarCaja.getIdCajaEliminarCajaVista();
		
		//Validacion y borrado por ID
		try {
			int id = Integer.parseInt(idCajaVista.trim());
			
			//Comprobacion de eliminacion
			if (dao.eliminarCaja(id)) {
				vistaEliminarCaja.OK();
			} else {
				vistaEliminarCaja.error("No existe ninguna caja con el ID especificado.");
			}
			
		} catch (NumberFormatException ex) {
			vistaEliminarCaja.error("El ID introducido debe ser un número válido.");
		}
	}
}
