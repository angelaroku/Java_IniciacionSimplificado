package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaCrearVista;

public class CajaCrearControlador implements ActionListener {
	private CajaCrearVista vistaCrearCaja;
	private DAO_StudyApp dao;
	
	public CajaCrearControlador(CajaCrearVista vistaCrearCaja) {
		this.vistaCrearCaja = vistaCrearCaja;
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Recogida de datos de la vista
		String periodicidadCajaVista = vistaCrearCaja.getPeriocidadCrearCajaVista();
		String idsFichasCajaVista = vistaCrearCaja.getTextFieldIdsFichasCajaVista();
		
		//Validacion de campos vacios 
		if (periodicidadCajaVista.trim().isEmpty()) {
			vistaCrearCaja.error("El número de periodicidad no pueden estar vacío.");
			return;
		}
		
		try {
			//Conversión de datos numéricos
			int periodicidad = Integer.parseInt(periodicidadCajaVista.trim());
			
			//Procesamiento de IDs de fichas separadas por comas
			ArrayList<Ficha> fichasAsociadas = new ArrayList<>();
			if (!idsFichasCajaVista.trim().isEmpty()) {
				String[] tokensIds = idsFichasCajaVista.split(",");
				for (String token : tokensIds) {
					int idFicha = Integer.parseInt(token.trim());
					Ficha fichaEncontrada = dao.buscarFicha(idFicha);
					
					//Verificación de existencia de la ficha
					if (fichaEncontrada != null) {
						fichasAsociadas.add(fichaEncontrada);
					} else {
						vistaCrearCaja.error("La ficha con ID " + idFicha + " no existe en el sistema.");
						return;
					}
				}
			}
			
			//Creacion de objeto y guardado en DAO
			Caja cajaNew = new Caja( periodicidad, fichasAsociadas);
			dao.guardarCaja(cajaNew);
			
			//Confirmacion en vista
			vistaCrearCaja.OK();
			
		} catch (NumberFormatException ex) {
			vistaCrearCaja.error("El número de caja, periodicidad e IDs de fichas deben ser valores numéricos.");
		}
	}
}
