package ies.piobaroja.dam2.accesoadatos.studyapp.dao;

import java.util.ArrayList;

//IMPORTS NUEVOS OBLIGATORIOS PARA GESTIÓN DE FICHEROS (CE 1a, CE 1b)
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;

//LIBRERÍAS DE MAVEN (CE 1e)
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Coleccion;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.StuddyApp;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

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
	
	
	//EXPORTAR IMPORTAR
	// Export/Import - FICHAS
	public void exportarFichas(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
				oos.writeObject(app.getFichas());
			}
		} else if (formato.equals("json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try (FileWriter fw = new FileWriter(archivo)) {
				gson.toJson(app.getFichas(), fw);
			}
		} else if (formato.equals("xml")) {
			StuddyApp temp = new StuddyApp(app.getFichas(), null, null);
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			try (FileWriter fw = new FileWriter(archivo)) {
				m.marshal(temp, fw);
			}
		}
	}

	public void importarFichas(String nombre, String formato) throws Exception {
		File archivo = comprobarArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
				app.setFichas((ArrayList<Ficha>) ois.readObject());
			}
		} else if (formato.equals("json")) {
			Gson gson = new Gson();
			try (FileReader fr = new FileReader(archivo)) {
				Type tipo = new TypeToken<ArrayList<Ficha>>(){}.getType();
				app.setFichas(gson.fromJson(fr, tipo));
			}
		} else if (formato.equals("xml")) {
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Unmarshaller u = ctx.createUnmarshaller();
			try (FileReader fr = new FileReader(archivo)) {
				StuddyApp temp = (StuddyApp) u.unmarshal(fr);
				app.setFichas(temp.getFichas() != null ? temp.getFichas() : new ArrayList<>());
			}
		}
	}

	//  Export/Import - CAJAS
	public void exportarCajas(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
				oos.writeObject(app.getCajas());
			}
		} else if (formato.equals("json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try (FileWriter fw = new FileWriter(archivo)) {
				gson.toJson(app.getCajas(), fw);
			}
		} else if (formato.equals("xml")) {
			StuddyApp temp = new StuddyApp(null, app.getCajas(), null);
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			try (FileWriter fw = new FileWriter(archivo)) {
				m.marshal(temp, fw);
			}
		}
	}

	public void importarCajas(String nombre, String formato) throws Exception {
		File archivo = comprobarArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
				app.setCajas((ArrayList<Caja>) ois.readObject());
			}
		} else if (formato.equals("json")) {
			Gson gson = new Gson();
			try (FileReader fr = new FileReader(archivo)) {
				Type tipo = new TypeToken<ArrayList<Caja>>(){}.getType();
				app.setCajas(gson.fromJson(fr, tipo));
			}
		} else if (formato.equals("xml")) {
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Unmarshaller u = ctx.createUnmarshaller();
			try (FileReader fr = new FileReader(archivo)) {
				StuddyApp temp = (StuddyApp) u.unmarshal(fr);
				app.setCajas(temp.getCajas() != null ? temp.getCajas() : new ArrayList<>());
			}
		}
	}

	//   Export/Import - COLECCIONES
	public void exportarColecciones(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
				oos.writeObject(app.getColecciones());
			}
		} else if (formato.equals("json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try (FileWriter fw = new FileWriter(archivo)) {
				gson.toJson(app.getColecciones(), fw);
			}
		} else if (formato.equals("xml")) {
			StuddyApp temp = new StuddyApp(null, null, app.getColecciones());
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			try (FileWriter fw = new FileWriter(archivo)) {
				m.marshal(temp, fw);
			}
		}
	}

	public void importarColecciones(String nombre, String formato) throws Exception {
		File archivo = comprobarArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
				app.setColecciones((ArrayList<Coleccion>) ois.readObject());
			}
		} else if (formato.equals("json")) {
			Gson gson = new Gson();
			try (FileReader fr = new FileReader(archivo)) {
				Type tipo = new TypeToken<ArrayList<Coleccion>>(){}.getType();
				app.setColecciones(gson.fromJson(fr, tipo));
			}
		} else if (formato.equals("xml")) {
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Unmarshaller u = ctx.createUnmarshaller();
			try (FileReader fr = new FileReader(archivo)) {
				StuddyApp temp = (StuddyApp) u.unmarshal(fr);
				app.setColecciones(temp.getColecciones() != null ? temp.getColecciones() : new ArrayList<>());
			}
		}
	}

	//  Export/Import - COMPLETO (StudyApp)
	public void exportarStuddyApp(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
				oos.writeObject(app);
			}
		} else if (formato.equals("json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try (FileWriter fw = new FileWriter(archivo)) {
				gson.toJson(app, fw);
			}
		} else if (formato.equals("xml")) {
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			try (FileWriter fw = new FileWriter(archivo)) {
				m.marshal(app, fw);
			}
		}
	}

	public void importarStuddyApp(String nombre, String formato) throws Exception {
		File archivo = comprobarArchivo(nombre, formato);

		if (formato.equals("obj")) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
				this.app = (StuddyApp) ois.readObject();
			}
		} else if (formato.equals("json")) {
			Gson gson = new Gson();
			try (FileReader fr = new FileReader(archivo)) {
				this.app = gson.fromJson(fr, StuddyApp.class);
			}
		} else if (formato.equals("xml")) {
			JAXBContext ctx = JAXBContext.newInstance(StuddyApp.class);
			Unmarshaller u = ctx.createUnmarshaller();
			try (FileReader fr = new FileReader(archivo)) {
				this.app = (StuddyApp) u.unmarshal(fr);
			}
		}
	}

	//  MÉTODOS AUXILIARES 
	private File prepararArchivo(String nombre, String formato) {
		String rutaCarpeta = "ficheros/" + formato;
		File carpeta = new File(rutaCarpeta);
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		return new File(rutaCarpeta + "/" + nombre + "." + formato);
	}

	private File comprobarArchivo(String nombre, String formato) throws FileNotFoundException {
		File archivo = new File("ficheros/" + formato + "/" + nombre + "." + formato);
		if (!archivo.exists()) {
			throw new FileNotFoundException("El archivo '" + nombre + "." + formato + "' no existe.");
		}
		return archivo;
	}
}