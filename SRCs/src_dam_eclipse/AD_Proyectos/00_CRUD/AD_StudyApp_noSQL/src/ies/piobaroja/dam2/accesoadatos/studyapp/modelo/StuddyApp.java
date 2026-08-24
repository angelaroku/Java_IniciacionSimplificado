package ies.piobaroja.dam2.accesoadatos.studyapp.modelo;

import java.util.ArrayList;
import java.util.Iterator;


public class StuddyApp {
	private ArrayList<Ficha> fichas;
	private ArrayList<Caja> cajas;
	private ArrayList<Coleccion> colecciones;
	
	public StuddyApp(ArrayList<Ficha> fichas, ArrayList<Caja> cajas, ArrayList<Coleccion> colecciones) {
		this.fichas = fichas;
		this.cajas = cajas;
		this.colecciones = colecciones;
	}
	
	//Metodos CRUD de cada array
	// Métodos ALTA
	public void altaFicha(Ficha fichaNueva) {
		fichas.add(fichaNueva);
	}
	
	public void altaCaja(Caja cajaNueva) {
		cajas.add(cajaNueva);
	}
	
	public void altaColeccion(Coleccion coleccionNueva) {
		colecciones.add(coleccionNueva);
	}
	
	// Métodos BAJA
	public boolean bajaFicha(int id_ficha) {
		Iterator<Ficha> it = fichas.iterator();
		while(it.hasNext()){
			if(it.next().getId_ficha() == id_ficha) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean bajaCaja(int id_caja) {
		Iterator<Caja> it = cajas.iterator();
		while(it.hasNext()) {
			if(it.next().getId_caja() == id_caja) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean bajaColeccion(int id_coleccion) {
		Iterator<Coleccion> it = colecciones.iterator();
		while(it.hasNext()) {
			if(it.next().getId_coleccion() == id_coleccion) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	// Métodos CONSULTA
	public Ficha consultaFicha(int id_ficha) {
		for (Ficha fichaConsultada: fichas) {
			if (fichaConsultada.getId_ficha() == id_ficha) {
				return fichaConsultada;
			}
		}
		return null;
	}
	
	public Caja consultaCaja(int id_caja) {
		for (Caja cajaConsultada: cajas) {
			if (cajaConsultada.getId_caja() == id_caja) {
				return cajaConsultada;
			}
		}
		return null;
	}
	
	public Coleccion consultaColeccion(int id_coleccion) {
		for (Coleccion coleccionConsultada: colecciones) {
			if (coleccionConsultada.getId_coleccion() == id_coleccion) {
				return coleccionConsultada;
			}
		}
		return null;
	}
	
	// Métodos MODIFICAR 
	public boolean modificarFicha (Ficha fichaOriginal) {
		for (Ficha fichaModificada : fichas) {
			if (fichaModificada.getId_ficha() == fichaOriginal.getId_ficha()) {
				fichaModificada.setPregunta(fichaOriginal.getPregunta());
				fichaModificada.setRespuesta(fichaOriginal.getRespuesta());
				return true;
			}
		}
		return false;
	}
	
	public boolean modificarCaja(Caja cajaOriginal) {
		for (Caja cajaModificada : cajas) {
			if (cajaModificada.getId_caja() == cajaOriginal.getId_caja()) {
				cajaModificada.setFichas(cajaOriginal.getFichas());
				cajaModificada.setPeriocidad(cajaOriginal.getPeriocidad());
				return true;
			}
		}
		return false;
	}
	
	public boolean modificarColeccion(Coleccion coleccionOriginal) {
		for (Coleccion coleccionModificado : colecciones) {
			if (coleccionModificado.getId_coleccion() == coleccionOriginal.getId_coleccion()){
				coleccionModificado.setTema(coleccionOriginal.getTema());
				coleccionModificado.setCajas(coleccionOriginal.getCajas());
				return true;
			}
		}
		return false;
	}

	// GETTERS Y SETTERS
	public ArrayList<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(ArrayList<Ficha> fichas) {
		this.fichas = fichas;
	}

	public ArrayList<Caja> getCajas() {
		return cajas;
	}

	public void setCajas(ArrayList<Caja> cajas) {
		this.cajas = cajas;
	}

	public ArrayList<Coleccion> getColecciones() {
		return colecciones;
	}

	public void setColecciones(ArrayList<Coleccion> colecciones) {
		this.colecciones = colecciones;
	}
}