package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaModificarVista;

public class FichaModificarControlador implements ActionListener {
	private FichaModificarVista vistaModificarFicha;
	private DAO_StudyApp dao;
	
	public FichaModificarControlador(FichaModificarVista vistaModificarFicha) {
		this.vistaModificarFicha = vistaModificarFicha;	
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Identificacion del boton pulsado
		String botonPulsado = e.getActionCommand();
		
		//Accion Buscar Ficha
		if (botonPulsado.equals("Buscar")) {
			String idFichaVista = vistaModificarFicha.getIdFichaModificarFichaVista();
			try {
				int id = Integer.parseInt(idFichaVista);
				Ficha fichaEncontrada = dao.buscarFicha(id);
				
				//Datos en los campos de texto de la vista
				if (fichaEncontrada != null) {
					vistaModificarFicha.setPreguntaModificarFichaVista(fichaEncontrada.getPregunta());
					vistaModificarFicha.setRespuestaModificarFichaVista(fichaEncontrada.getRespuesta());
				} else {
					vistaModificarFicha.error("No se encontró ninguna ficha con ese ID.");
				}
			} catch (NumberFormatException ex) {
				vistaModificarFicha.error("El ID para buscar debe ser numérico.");
			}
		} 
		
		//Accion Modificar Ficha
		else if (botonPulsado.equals("Modificar")) {
			String idModificadoFichaVista = vistaModificarFicha.getIdFichaModificarFichaVista();
			String nuevaPreguntaFichaVista = vistaModificarFicha.getPreguntaModificarFichaVista();
			String nuevaRespuestaFichaVista = vistaModificarFicha.getRespuestaModificarFichaVista();
			
			try {
				int id = Integer.parseInt(idModificadoFichaVista);
				
				//Validacion de campos vacios
				if (nuevaPreguntaFichaVista.trim().isEmpty() || nuevaRespuestaFichaVista.trim().isEmpty()) {
					vistaModificarFicha.error("Los nuevos campos no pueden estar vacíos.");
					return;
				}
				
				//Actualizacion en el DAO
				Ficha fichaModificada = new Ficha(id, nuevaPreguntaFichaVista, nuevaRespuestaFichaVista);
				if (dao.actualizarFicha(fichaModificada)) {
					vistaModificarFicha.OK();
				} else {
					vistaModificarFicha.error("No se pudo actualizar. Comprueba si el ID sigue existiendo.");
				}
			} catch (NumberFormatException ex) {
				vistaModificarFicha.error("El ID debe ser un número válido.");
			}
		}
	}
}