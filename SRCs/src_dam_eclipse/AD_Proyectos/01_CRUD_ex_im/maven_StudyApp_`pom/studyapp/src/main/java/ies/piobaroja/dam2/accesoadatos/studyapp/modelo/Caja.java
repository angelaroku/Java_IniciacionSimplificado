package ies.piobaroja.dam2.accesoadatos.studyapp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "caja")
public class Caja implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id_caja;
	private int periocidad;
	private ArrayList<Ficha> fichas;
	
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

	public ArrayList<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(ArrayList<Ficha> fichas) {
		this.fichas = fichas;
	}	
}