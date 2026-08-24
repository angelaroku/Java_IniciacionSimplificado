package ies.piobaroja.dam2.accesoadatos.studyapp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlRootElement;

import jakarta.xml.bind.annotation.XmlTransient;
import javax.persistence.*; 

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
@Table(name = "cajas")
@XmlRootElement(name = "caja")
public class Caja implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id_caja;
	private int periocidad;
	
	// cambio de ArrayList a List para que Hibernate pueda gestionar la colección
	@OneToMany(mappedBy = "caja", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Ficha> fichas;
	
	@ManyToOne
	@JoinColumn(name = "id_coleccion") 
	private Coleccion coleccion;
	
	//Constructor vacio para uso de JAXB
		public Caja() {	
			this.fichas = new ArrayList<>();
		}
	//Constructor cajas nuevas
	public Caja( int periocidad, ArrayList<Ficha> fichas) {
		this.periocidad = periocidad;
		this.fichas = fichas;
	}
	
	//Constructor cajas existentes
	public Caja(int id_caja,  int periocidad, ArrayList<Ficha> fichas) {
		this.id_caja = id_caja;
		this.periocidad = periocidad;
		this.fichas = fichas;
	}
	
	// GETTERS Y SETTERS
	public int getId_caja() {
		return id_caja;
	}

	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}


	public int getPeriocidad() {
		return periocidad;
	}

	public void setPeriocidad(int periocidad) {
		this.periocidad = periocidad;
	}

	public List<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}	

	@XmlTransient // Evita que JAXB intente serializar la colección contenedora creando un bucle
	public Coleccion getColeccion() {
		return coleccion;
	}

	public void setColeccion(Coleccion coleccion) {
		this.coleccion = coleccion;
	}
}