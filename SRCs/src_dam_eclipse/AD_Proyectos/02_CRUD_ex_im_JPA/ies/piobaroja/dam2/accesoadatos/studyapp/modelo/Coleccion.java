package ies.piobaroja.dam2.accesoadatos.studyapp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "colecciones")
@XmlRootElement(name = "coleccion")
public class Coleccion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id_coleccion;
	private String tema;
	
	// cambio de ArrayList a List para que Hibernate pueda gestionar la colección
	@OneToMany(mappedBy = "coleccion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Caja> cajas;
	
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

	public List<Caja> getCajas() {
		return cajas;
	}

	public void setCajas(List<Caja> cajas) {
		this.cajas = cajas;
	}
}
