package ies.piobaroja.dam2.accesoadatos.studyapp.modelo;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient; 

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "fichas")
@XmlRootElement(name = "ficha")
public class Ficha implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id_ficha;
	private String pregunta;
	private String respuesta;
	
	@ManyToOne
	@JoinColumn(name = "id_caja") 
	private Caja caja;
	
	//Constructor vacio para uso de JAXB
	public Ficha() {	
	}
	
	//Constructor fichas nuevas
	public Ficha(String pregunta, String respuesta) {
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}
	
	//Constructor fichas existentes
	public Ficha(int id_ficha, String pregunta, String respuesta) {
		this.id_ficha = id_ficha;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}
	
//GETTER Y SETTER
	public int getId_ficha() {
		return id_ficha;
	}

	public void setId_ficha(int id_ficha) {
		this.id_ficha = id_ficha;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@XmlTransient // Evita que JAXB intente serializar la caja asociada creando un bucle
	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
}
