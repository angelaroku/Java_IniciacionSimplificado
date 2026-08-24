package ies.piobaroja.dam2.accesoadatos.studyapp.dao;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;

// IMPORTS DE JPA 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

public class DAO_StudyApp {
	private static DAO_StudyApp instancia = null;
	private StuddyApp app;
	
	// Atributo para gestionar la base de datos con JPA
	private EntityManager em;
	
	private DAO_StudyApp() {
		this.app = new StuddyApp(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		
		// Inicializacion de JPA 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudyAppPU");
		this.em = emf.createEntityManager();
	}
	
	public static DAO_StudyApp getInstancia() {
		if (instancia == null) {
			instancia = new DAO_StudyApp();
		}
		return instancia;
	}
	
	//CRUD FICHA + asignacion id por BD
	public void guardarFicha(Ficha nuevaFicha) {
		// quitamos el contador manual porque por JPA gestionamos el id de forma automática
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(nuevaFicha);
		et.commit();
	}
	
	public Ficha buscarFicha(int id_ficha) {
		return em.find(Ficha.class, id_ficha);
	}
	
	public boolean eliminarFicha(int id_ficha) {
		Ficha ficha = em.find(Ficha.class, id_ficha);
		if (ficha != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(ficha);
			et.commit();
			return true;
		}
		return false;
	}
	
	public boolean actualizarFicha(Ficha fichaModificada) {
		Ficha ficha = em.find(Ficha.class, fichaModificada.getId_ficha());
		if (ficha != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			ficha.setPregunta(fichaModificada.getPregunta());
			ficha.setRespuesta(fichaModificada.getRespuesta());
			et.commit();
			return true;
		}
		return false;
	}
	
	//CRUD CAJA + asignacion id por BD
	public void guardarCaja(Caja nuevaCaja) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(nuevaCaja);
		et.commit();
	}
	
	public Caja buscarCaja(int id_caja) {
		return em.find(Caja.class, id_caja);
	}
	
	public boolean eliminarCaja(int id_caja) {
		Caja caja = em.find(Caja.class, id_caja);
		if (caja != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(caja);
			et.commit();
			return true;
		}
		return false;
	}
	
	public boolean actualizarCaja(Caja cajaModificada) {
		Caja caja = em.find(Caja.class, cajaModificada.getId_caja());
		if (caja != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			caja.setPeriocidad(cajaModificada.getPeriocidad());
			caja.setFichas(cajaModificada.getFichas());
			et.commit();
			return true;
		}
		return false;
	}
	
	//CRUD COLECCION + asignacion id por BD
	public void guardarColeccion(Coleccion nuevaColeccion) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(nuevaColeccion);
		et.commit();
	}
	
	public Coleccion buscarColeccion(int id_coleccion) {
		return em.find(Coleccion.class, id_coleccion);
	}
	
	public boolean eliminarColeccion(int id_coleccion) {
		Coleccion coleccion = em.find(Coleccion.class, id_coleccion);
		if (coleccion != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(coleccion);
			et.commit();
			return true;
		}
		return false;
	}
	
	public boolean actualizarColeccion(Coleccion coleccionModificada) {
		Coleccion coleccion = em.find(Coleccion.class, coleccionModificada.getId_coleccion());
		if (coleccion != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			coleccion.setTema(coleccionModificada.getTema());
			coleccion.setCajas(coleccionModificada.getCajas());
			et.commit();
			return true;
		}
		return false;
	}
	
	
	//EXPORTAR IMPORTAR
	// Export/Import - FICHAS
	public void exportarFichas(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);
		
		// Sincronizamos 'app' con los datos frescos de la base de datos antes de exportar
		List<Ficha> lasFichas = em.createQuery("SELECT f FROM Ficha f", Ficha.class).getResultList();
		app.setFichas(new ArrayList<>(lasFichas));

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
		
		// Guardamos en la base de datos las fichas que acabamos de importar del archivo
		EntityTransaction et = em.getTransaction();
		et.begin();
		for (Ficha f : app.getFichas()) {
			em.persist(f);
		}
		et.commit();
	}

	//  Export/Import - CAJAS
	public void exportarCajas(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);
		
		// Sincronizamos 'app' con los datos frescos de la base de datos antes de exportar
		List<Caja> lasCajas = em.createQuery("SELECT c FROM Caja c", Caja.class).getResultList();
		app.setCajas(new ArrayList<>(lasCajas));

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
		
		// guardamos en la base de datos las cajas importadas
		EntityTransaction et = em.getTransaction();
		et.begin();
		for (Caja c : app.getCajas()) {
			em.persist(c);
		}
		et.commit();
	}

	//   Export/Import - COLECCIONES
	public void exportarColecciones(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);
		
		// Sincronizamos 'app' con los datos frescos de la Base de Datos antes de exportar
		List<Coleccion> lasColecciones = em.createQuery("SELECT c FROM Coleccion c", Coleccion.class).getResultList();
		app.setColecciones(new ArrayList<>(lasColecciones));

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
		
		// guardamos en la base de datos las colecciones importadas
		EntityTransaction et = em.getTransaction();
		et.begin();
		for (Coleccion col : app.getColecciones()) {
			em.persist(col);
		}
		et.commit();
	}

	//  Export/Import - COMPLETO (StudyApp)
	public void exportarStuddyApp(String nombre, String formato) throws Exception {
		File archivo = prepararArchivo(nombre, formato);
		
		// traemos todo de la Base de Datos para empaquetarlo en app
		List<Ficha> f = em.createQuery("SELECT f FROM Ficha f", Ficha.class).getResultList();
		List<Caja> c = em.createQuery("SELECT c FROM Caja c", Caja.class).getResultList();
		List<Coleccion> col = em.createQuery("SELECT c FROM Coleccion c", Coleccion.class).getResultList();
		
		this.app = new StuddyApp(new ArrayList<>(f), new ArrayList<>(c), new ArrayList<>(col));

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
		
		// Persistimos todo el paquete importado en la Base de Datos
		EntityTransaction et = em.getTransaction();
		et.begin();
		if (app.getFichas() != null) {
			for (Ficha fi : app.getFichas()) em.persist(fi);
		}
		if (app.getCajas() != null) {
			for (Caja ca : app.getCajas()) em.persist(ca);
		}
		if (app.getColecciones() != null) {
			for (Coleccion co : app.getColecciones()) em.persist(co);
		}
		et.commit();
	}

	// Métodos de extracción matricial para ConsultaPorClaseControlador
	public String[][] obtenerMatrizColecciones() {
		List<Coleccion> lista = em.createQuery("SELECT c FROM Coleccion c", Coleccion.class).getResultList();
		String[][] matriz = new String[lista.size()][2];
		for (int i = 0; i < lista.size(); i++) {
			matriz[i][0] = String.valueOf(lista.get(i).getId_coleccion());
			matriz[i][1] = lista.get(i).getTema();
		}
		return matriz;
	}

	public String[][] obtenerMatrizCajas() {
		List<Caja> lista = em.createQuery("SELECT c FROM Caja c", Caja.class).getResultList();
		String[][] matriz = new String[lista.size()][2];
		for (int i = 0; i < lista.size(); i++) {
			matriz[i][0] = String.valueOf(lista.get(i).getId_caja());
			matriz[i][1] = String.valueOf(lista.get(i).getPeriocidad());
		}
		return matriz;
	}

	public String[][] obtenerMatrizFichas() {
		List<Ficha> lista = em.createQuery("SELECT f FROM Ficha f", Ficha.class).getResultList();
		String[][] matriz = new String[lista.size()][3];
		for (int i = 0; i < lista.size(); i++) {
			matriz[i][0] = String.valueOf(lista.get(i).getId_ficha());
			matriz[i][1] = lista.get(i).getPregunta();
			matriz[i][2] = lista.get(i).getRespuesta();
		}
		return matriz;
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