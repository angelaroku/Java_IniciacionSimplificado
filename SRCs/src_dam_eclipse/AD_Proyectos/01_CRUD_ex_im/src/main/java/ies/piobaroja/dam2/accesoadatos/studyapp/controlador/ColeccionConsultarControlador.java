package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Coleccion;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionConsultarVista;

public class ColeccionConsultarControlador implements ActionListener {
	private ColeccionConsultarVista vistaConsultarColeccion;
	private DAO_StudyApp dao;
	
	public ColeccionConsultarControlador(ColeccionConsultarVista vistaConsultarColeccion) {
		this.vistaConsultarColeccion = vistaConsultarColeccion;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String idColeccionVista = vistaConsultarColeccion.getIdColeccionConsultarColeccionVista();
		
		//Validacion y busqueda por ID
		try {
			int id = Integer.parseInt(idColeccionVista.trim());
			Coleccion coleccionEncontrada = dao.buscarColeccion(id);
			
			//Formateo y envio de resultados a la vista
			if (coleccionEncontrada != null) {
				
				//Extraccion limpia de los IDs de las cajas para el reporte visual
				ArrayList<Caja> cajas = coleccionEncontrada.getCajas();
				StringBuilder idsCajasColeccion = new StringBuilder();
				if (cajas.isEmpty()) {
					idsCajasColeccion.append("Ninguna");
				} else {
					for (int i = 0; i < cajas.size(); i++) {
						idsCajasColeccion.append(cajas.get(i).getId_caja());
						if (i < cajas.size() - 1) {
							idsCajasColeccion.append(", ");
						}
					}
				}
				
				//Construcción de la cadena final para la salida de la vista
				String resultadoFormateado = "ID Colección: " + coleccionEncontrada.getId_coleccion() + "\n"
						+ "Nombre: " + coleccionEncontrada.getTema() + "\n"
						+ "IDs Cajas asociadas: " + idsCajasColeccion.toString();
				
				vistaConsultarColeccion.setTextAreaResultadoConsultarColeccionVista(resultadoFormateado);
				vistaConsultarColeccion.OK();
			} else {
				vistaConsultarColeccion.error("No se encontró la colección solicitada.");
			}
			
		} catch (NumberFormatException ex) {
			vistaConsultarColeccion.error("Por favor, introduce un ID numérico válido.");
		}
	}
}