package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Coleccion;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionCrearVista;

public class ColeccionCrearControlador implements ActionListener {
	private ColeccionCrearVista vistaCrearColeccion;
	private DAO_StudyApp dao;
	
	public ColeccionCrearControlador(ColeccionCrearVista vistaCrearColeccion) {
		this.vistaCrearColeccion = vistaCrearColeccion;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String temaColeccionVista = vistaCrearColeccion.getTemaCrearColeccionVista();
		String idsCajasColeccionVista= vistaCrearColeccion.getTextFieldIdsCajasColeccionVista();
		
		//Validacion de campos vacios 
		if (temaColeccionVista.trim().isEmpty()) {
			vistaCrearColeccion.error("El nombre de la colección no puede estar vacío.");
			return;
		}
		
		try {
			//Procesamiento de IDs de cajas separadas por comas
			ArrayList<Caja> cajasAsociadas = new ArrayList<>();
			if (!idsCajasColeccionVista.trim().isEmpty()) {
				String[] tokensIds = idsCajasColeccionVista.split(",");
				for (String token : tokensIds) {
					int idCaja = Integer.parseInt(token.trim());
					Caja cajaEncontrada = dao.buscarCaja(idCaja);
					
					//Verificación de existencia de la caja
					if (cajaEncontrada != null) {
						cajasAsociadas.add(cajaEncontrada);
					} else {
						vistaCrearColeccion.error("La caja con ID " + idCaja + " no existe en el sistema.");
						return;
					}
				}
			}
			
			//Creacion de objeto y guardado en DAO
			Coleccion nuevaColeccion = new Coleccion(temaColeccionVista.trim(), cajasAsociadas);
			dao.guardarColeccion(nuevaColeccion);
			
			//Confirmacion en vista
			vistaCrearColeccion.OK();
			
		} catch (NumberFormatException ex) {
			vistaCrearColeccion.error("Los IDs de las cajas deben ser valores numéricos.");
		}
	}
}