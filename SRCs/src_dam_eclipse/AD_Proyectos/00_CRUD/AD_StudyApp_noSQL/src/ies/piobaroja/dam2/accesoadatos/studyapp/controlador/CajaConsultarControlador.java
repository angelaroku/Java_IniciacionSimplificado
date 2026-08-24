package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaConsultarVista;

public class CajaConsultarControlador implements ActionListener {
	private CajaConsultarVista vistaConsultarCaja;
	private DAO_StudyApp dao;
	
	public CajaConsultarControlador(CajaConsultarVista vistaConsultarCaja) {
		this.vistaConsultarCaja = vistaConsultarCaja;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String idCajaVista = vistaConsultarCaja.getIdCajaConsultarCajaVista();
		
		//Validacion y busqueda por ID
		try {
			int id = Integer.parseInt(idCajaVista.trim());
			Caja cajaEncontrada = dao.buscarCaja(id);
			
			//Formateo y envio de resultados a la vista
			if (cajaEncontrada != null) {
				
				//Extraccion limpia delos IDs de las fichas para el reporte visual
				ArrayList<Ficha> fichas = cajaEncontrada.getFichas();
				StringBuilder idsFichasCaja = new StringBuilder();
				if (fichas.isEmpty()) {
					idsFichasCaja.append("Ninguna");
				} else {
					for (int i = 0; i < fichas.size(); i++) {
						idsFichasCaja.append(fichas.get(i).getId_ficha());
						if (i < fichas.size() - 1) {
							idsFichasCaja.append(", ");
						}
					}
				}
				
				//Construcción de la cadena final para la salida de la vista
				String resultadoFormateado = "ID Caja: " + cajaEncontrada.getId_caja() + "\n"
						+ "Periodicidad: " + cajaEncontrada.getPeriocidad() + " días\n"
						+ "IDs Fichas asociadas: " + idsFichasCaja.toString();
				
				vistaConsultarCaja.setTextAreaResultadoConsultarCajaVista(resultadoFormateado);
				vistaConsultarCaja.OK();
			} else {
				vistaConsultarCaja.error("No se encontró la caja solicitada.");
			}
			
		} catch (NumberFormatException ex) {
			vistaConsultarCaja.error("Por favor, introduce un ID numérico válido.");
		}
	}
}