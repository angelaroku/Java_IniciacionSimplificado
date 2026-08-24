package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Coleccion;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionModificarVista;

public class ColeccionModificarControlador implements ActionListener {
	private ColeccionModificarVista vistaModificarColeccion;
	private DAO_StudyApp dao;
	
	public ColeccionModificarControlador(ColeccionModificarVista vistaModificarColeccion) {
		this.vistaModificarColeccion = vistaModificarColeccion;	
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Identificacion del boton pulsado
		String botonPulsado = e.getActionCommand();
		
		//idsCajasColeccion
		//Accion Buscar Coleccion
		if (botonPulsado.equals("Buscar")) {
			String idColeccionVista = vistaModificarColeccion.getIdColeccionModificarColeccionVista();
			try {
				int id = Integer.parseInt(idColeccionVista.trim());
				Coleccion coleccionEncontrada = dao.buscarColeccion(id);
				
				//Datos en los campos de texto de la vista
				if (coleccionEncontrada != null) {
					vistaModificarColeccion.setTemaModificarColeccionVista(coleccionEncontrada.getTema());
					
					//Convertir lista de objetos Caja a texto separado por comas
					ArrayList<Caja> cajas = coleccionEncontrada.getCajas();
					StringBuilder idsCajasColeccion = new StringBuilder();
					for (int i = 0; i < cajas.size(); i++) {
						idsCajasColeccion.append(cajas.get(i).getId_caja());
						if (i < cajas.size() - 1) {
							idsCajasColeccion.append(",");
						}
					}
					vistaModificarColeccion.setTextFieldIdsCajasColeccionVista(idsCajasColeccion.toString());
				} else {
					vistaModificarColeccion.error("No se encontró ninguna colección con ese ID.");
				}
			} catch (NumberFormatException ex) {
				vistaModificarColeccion.error("El ID para buscar debe ser numérico.");
			}
		} 
		
		//Accion Modificar Coleccion
		else if (botonPulsado.equals("Modificar")) {
			String idColeccionVista = vistaModificarColeccion.getIdColeccionModificarColeccionVista();
			String temaColeccionVista = vistaModificarColeccion.getTemaModificarColeccionVista();
			String idsCajasColeccionVista = vistaModificarColeccion.getTextFieldIdsCajasColeccionVista();
			
			try {
				int id = Integer.parseInt(idColeccionVista.trim());
				
				//Validacion de campos vacios básicos
				if (temaColeccionVista.trim().isEmpty()) {
					vistaModificarColeccion.error("El campo de nombre no puede estar vacío.");
					return;
				}
				
				//reasociacion IDs de cajas
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
							vistaModificarColeccion.error("La caja con ID " + idCaja + " no existe. No se guardaron cambios.");
							return;
						}
					}
				}
				
				//Actualizacion en el DAO usando constructor de coleccion existente
				Coleccion coleccionModificada = new Coleccion(id, temaColeccionVista.trim(), cajasAsociadas);
				if (dao.actualizarColeccion(coleccionModificada)) {
					vistaModificarColeccion.OK();
				} else {
					vistaModificarColeccion.error("No se pudo actualizar. Comprueba si el ID sigue existiendo.");
				}
			} catch (NumberFormatException ex) {
				vistaModificarColeccion.error("El ID y los IDs de las cajas deben ser valores numéricos.");
			}
		}
	}
}
