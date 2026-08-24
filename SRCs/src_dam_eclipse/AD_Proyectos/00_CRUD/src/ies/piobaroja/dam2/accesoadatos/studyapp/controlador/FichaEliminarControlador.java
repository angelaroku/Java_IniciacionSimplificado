package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaEliminarVista;

public class FichaEliminarControlador implements ActionListener {

	private FichaEliminarVista vistaEliminarFicha;
	private DAO_StudyApp dao;
	
	public FichaEliminarControlador(FichaEliminarVista vistaEliminarFicha) {
		this.vistaEliminarFicha = vistaEliminarFicha;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String idFichaVista = vistaEliminarFicha.getIdFichaEliminarFichaVista();
		
		//Validacion y borrado por ID
		try {
			int id = Integer.parseInt(idFichaVista);
			
			//Comprobacion 
			if (dao.eliminarFicha(id)) {
				vistaEliminarFicha.OK();
			} else {
				vistaEliminarFicha.error("No existe ninguna ficha con el ID especificado.");
			}
			
		} catch (NumberFormatException ex) {
			vistaEliminarFicha.error("El ID introducido debe ser un número válido.");
		}
	}
}
