package ies.piobaroja.dam2.accesoadatos.studyapp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ies.piobaroja.dam2.accesoadatos.studyapp.dao.DAO_StudyApp;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Caja;
import ies.piobaroja.dam2.accesoadatos.studyapp.modelo.Ficha;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaModificarVista;

public class CajaModificarControlador implements ActionListener {
	private CajaModificarVista vistaModificarCaja;
	private DAO_StudyApp dao;
	
	public CajaModificarControlador(CajaModificarVista vistaModificarCaja) {
		this.vistaModificarCaja = vistaModificarCaja;	
		//Conexion con la instancia DAO
		this.dao = DAO_StudyApp.getInstancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Identificacion del boton pulsado
		String botonPulsado = e.getActionCommand();
		
		//Accion Buscar Caja
		if (botonPulsado.equals("Buscar")) {
			String idCajaVista = vistaModificarCaja.getIdCajaModificarCajaVista();
			try {
				int id = Integer.parseInt(idCajaVista.trim());
				Caja cajaEncontrada = dao.buscarCaja(id);
				
				//Datos en los campos de texto de la vista
				if (cajaEncontrada != null) {
					vistaModificarCaja.setPeriocidadModificarCajaVista(String.valueOf(cajaEncontrada.getPeriocidad()));
					
					//Convertir lista de objetos Ficha a texto separado por comas
					List<Ficha> fichas = cajaEncontrada.getFichas();
					StringBuilder idsFichasCaja = new StringBuilder();
					for (int i = 0; i < fichas.size(); i++) {
						idsFichasCaja.append(fichas.get(i).getId_ficha());
						if (i < fichas.size() - 1) {
							idsFichasCaja.append(",");
						}
					}
					vistaModificarCaja.setTextFieldIdsFichasCajaVista(idsFichasCaja.toString());
				} else {
					vistaModificarCaja.error("No se encontró ninguna caja con ese ID.");
				}
			} catch (NumberFormatException ex) {
				vistaModificarCaja.error("El ID para buscar debe ser numérico.");
			}
		} 
		
		//Accion Modificar Caja
		else if (botonPulsado.equals("Modificar")) {
			String idTexto = vistaModificarCaja.getIdCajaModificarCajaVista();
			String periodicidadTxt = vistaModificarCaja.getPeriocidadModificarCajaVista();
			String idsFichasTxt = vistaModificarCaja.getTextFieldIdsFichasCajaVista();
			
			try {
				int id = Integer.parseInt(idTexto.trim());
				
				//Validacion de campos vacios básicos
				if (periodicidadTxt.trim().isEmpty()) {
					vistaModificarCaja.error("Los campos de número y periodicidad no pueden estar vacíos.");
					return;
				}
				
				int periodicidad = Integer.parseInt(periodicidadTxt.trim());
				
				//reasociacion IDs de fichas
				ArrayList<Ficha> fichasAsociadas = new ArrayList<>();
				if (!idsFichasTxt.trim().isEmpty()) {
					String[] tokensIds = idsFichasTxt.split(",");
					for (String token : tokensIds) {
						int idFicha = Integer.parseInt(token.trim());
						Ficha fichaEncontrada = dao.buscarFicha(idFicha);
						
						//Verificación de existencia de la ficha
						if (fichaEncontrada != null) {
							fichasAsociadas.add(fichaEncontrada);
						} else {
							vistaModificarCaja.error("La ficha con ID " + idFicha + " no existe. No se guardaron cambios.");
							return;
						}
					}
				}
				
				//Actualizacion en el DAO usando constructor de caja existente
				Caja cajaModificada = new Caja(id, periodicidad, fichasAsociadas);
				if (dao.actualizarCaja(cajaModificada)) {
					vistaModificarCaja.OK();
				} else {
					vistaModificarCaja.error("No se pudo actualizar. Comprueba si el ID sigue existiendo.");
				}
			} catch (NumberFormatException ex) {
				vistaModificarCaja.error("El ID, periodicidad e IDs de fichas deben ser valores numéricos.");
			}
		}
	}
}