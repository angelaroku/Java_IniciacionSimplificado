package ies.piobaroja.dam2.accesoadatos.studyapp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coleccion")
public class Coleccion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id_coleccion;
	private String tema;
	private ArrayList<Caja> cajas;
	
	//Constructor vacio para uso de JAXB
	public Coleccion() {
		this.cajas = new ArrayList<>();
	}
	
	//Constructor colecciones nuevas
	public Coleccion(String tema, ArrayList<Caja> cajas) {
		this.tema = tema;
		this.cajas = cajas;
	}

	//Constructor colecciones existentes
	public Coleccion(int id_coleccion, String tema, ArrayList<Caja> cajas) {
		this.id_coleccion = id_coleccion;
		this.tema = tema;
		this.cajas = cajas;
	}

	// GETTERS Y SETTERS
	public int getId_coleccion() {
		return id_coleccion;
	}

	public void setId_coleccion(int id_coleccion) {
		this.id_coleccion = id_coleccion;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public ArrayList<Caja> getCajas() {
		return cajas;
	}

	public void setCajas(ArrayList<Caja> cajas) {
		this.cajas = cajas;
	}
}
