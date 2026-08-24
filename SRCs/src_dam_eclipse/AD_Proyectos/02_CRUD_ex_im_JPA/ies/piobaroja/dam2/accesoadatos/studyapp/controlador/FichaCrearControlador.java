package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaCrearVista;

public class FichaCrearControlador implements ActionListener {
	private FichaCrearVista vistaCrearFicha;
	private DAO_StudyApp dao;
	
	public FichaCrearControlador(FichaCrearVista vistaCrearFicha) {
		this.vistaCrearFicha = vistaCrearFicha;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String pregunta = vistaCrearFicha.getContenidoPreguntaFichaVista();
		String respuesta = vistaCrearFicha.getContenidoRespuestaFichaVista();
		
		//Validacion de campos vacios
		if (pregunta.trim().isEmpty() || respuesta.trim().isEmpty()) {
			vistaCrearFicha.error("La pregunta y la respuesta no pueden estar vacías.");
			return;
		}
		
		//Creacion de objeto y guardado en DAO
		Ficha fichaNew = new Ficha(pregunta, respuesta);
		dao.guardarFicha(fichaNew);
		
		//Confirmacion en vista
		vistaCrearFicha.OK();
	}
}
