package ies.piobaroja.dam2.accesoadatos.studyapp.modelo;

public class Ficha {
	private int id_ficha;
	private String pregunta;
	private String respuesta;
	
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
}
