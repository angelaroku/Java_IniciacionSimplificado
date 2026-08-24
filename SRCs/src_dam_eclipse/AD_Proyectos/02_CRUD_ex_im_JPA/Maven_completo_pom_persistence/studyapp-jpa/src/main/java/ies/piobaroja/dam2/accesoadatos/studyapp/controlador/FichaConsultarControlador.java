package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaConsultarVista;

public class FichaConsultarControlador implements ActionListener {
	
	private FichaConsultarVista vistaConsultarFicha;
	private DAO_StudyApp dao;
	
	public FichaConsultarControlador(FichaConsultarVista vistaConsultarFicha) {
		this.vistaConsultarFicha = vistaConsultarFicha;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}
	  
	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String idFichaVista = vistaConsultarFicha.getIdFichaConsultarFichaVista();
		
		//Validacion y busqueda por ID
		try {
			int id = Integer.parseInt(idFichaVista);
			Ficha fichaEncontrada = dao.buscarFicha(id);
			
			//Formateo y envio de resultados a la vista
			if (fichaEncontrada != null) {
				String resultadoFormateado = "ID Ficha: " + fichaEncontrada.getId_ficha() + "\n"
						+ "Pregunta: " + fichaEncontrada.getPregunta() + "\n"
						+ "Respuesta: " + fichaEncontrada.getRespuesta();
				
				vistaConsultarFicha.setTextAreaResultadoConsultarFichaVista(resultadoFormateado);
				vistaConsultarFicha.OK();
			} else {
				vistaConsultarFicha.error("No se encontró la ficha solicitada.");
			}
			
		} catch (NumberFormatException ex) {
			vistaConsultarFicha.error("Por favor, introduce un ID numérico válido.");
		}
	}
}
