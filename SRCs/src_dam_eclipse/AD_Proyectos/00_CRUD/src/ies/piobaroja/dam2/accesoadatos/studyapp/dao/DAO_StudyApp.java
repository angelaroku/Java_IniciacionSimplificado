package ies.piobaroja.dam2.accesoadatos.studyapp.dao;

import java.util.ArrayList;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Coleccion;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.StuddyApp;

public class DAO_StudyApp {
	private static DAO_StudyApp instancia = null;
	private StuddyApp app;
	private int contadorFichas;
	private int contadorCajas;
	private int contadorColecciones;
	
	private DAO_StudyApp() {
		this.app = new StuddyApp(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		this.contadorFichas = 0;
		this.contadorCajas = 0;
		this.contadorColecciones = 0;
	}
	
	public static DAO_StudyApp getInstancia() {
		if (instancia == null) {
			instancia = new DAO_StudyApp();
		}
		return instancia;
	}
	
	//CRUD FICHA + asignacion id
	public void guardarFicha(Ficha nuevaFicha) {
		contadorFichas++;
		nuevaFicha.setId_ficha(contadorFichas);
		app.altaFicha(nuevaFicha);
	}
	
	public Ficha buscarFicha(int id_ficha) {
		return app.consultaFicha(id_ficha);
	}
	
	public boolean eliminarFicha(int id_ficha) {
		return app.bajaFicha(id_ficha);
	}
	
	public boolean actualizarFicha(Ficha fichaModificada) {
		return app.modificarFicha(fichaModificada);
	}
	
	//CRUD CAJA + asignacion id
	public void guardarCaja(Caja nuevaCaja) {
		contadorCajas++;
		nuevaCaja.setId_caja(contadorCajas); 
		app.altaCaja(nuevaCaja);
	}
	
	public Caja buscarCaja(int id_caja) {
		return app.consultaCaja(id_caja);
	}
	
	public boolean eliminarCaja(int id_caja) {
		return app.bajaCaja(id_caja);
	}
	
	public boolean actualizarCaja(Caja cajaModificada) {
		return app.modificarCaja(cajaModificada);
	}
	
	//CRUD COLECCION + asignacion id
	public void guardarColeccion(Coleccion nuevaColeccion) {
		contadorColecciones++;
		nuevaColeccion.setId_coleccion(contadorColecciones); 
		app.altaColeccion(nuevaColeccion);
	}
	
	public Coleccion buscarColeccion(int id_coleccion) {
		return app.consultaColeccion(id_coleccion);
	}
	
	public boolean eliminarColeccion(int id_coleccion) {
		return app.bajaColeccion(id_coleccion);
	}
	
	public boolean actualizarColeccion(Coleccion coleccionModificada) {
		return app.modificarColeccion(coleccionModificada);
	}
}
