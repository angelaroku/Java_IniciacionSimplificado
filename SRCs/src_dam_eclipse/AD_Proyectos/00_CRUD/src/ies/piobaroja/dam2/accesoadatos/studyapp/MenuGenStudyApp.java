package ies.piobaroja.dam2.accesoadatos.studyapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaConsultarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaCrearVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaEliminarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.CajaModificarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionConsultarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionCrearVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionEliminarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.ColeccionModificarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaConsultarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaCrearVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaEliminarVista;
import ies.piobaroja.dam2.accesoadatos.studyapp.vista.FichaModificarVista;

public class MenuGenStudyApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JMenuItem mntmCrearFicha;
	private JMenuItem mntmEliminarFicha;
	private JMenuItem mntmModificarFicha;
	private JMenuItem mntmConsultarFicha;

	private JMenuItem mntmCrearCaja;
	private JMenuItem mntmEliminarCaja;
	private JMenuItem mntmModificarCaja;
	private JMenuItem mntmConsultarcaja;

	private JMenuItem mntmCrearColeccion;
	private JMenuItem mntmEliminarColeccion;
	private JMenuItem mntmModificarColeccion;
	private JMenuItem mntmConsultarColeccion;

	public MenuGenStudyApp() {

		getContentPane().setLayout(null);
		setTitle("Study App");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFicha = new JMenu("Ficha");
		menuBar.add(mnFicha);

		mntmCrearFicha = new JMenuItem("Crear Ficha");
		mnFicha.add(mntmCrearFicha);
		mntmCrearFicha.addActionListener(this);
		mntmEliminarFicha = new JMenuItem("Eliminar Ficha");
		mnFicha.add(mntmEliminarFicha);
		mntmEliminarFicha.addActionListener(this);
		mntmModificarFicha = new JMenuItem("Modificar Ficha");
		mnFicha.add(mntmModificarFicha);
		mntmModificarFicha.addActionListener(this);
		mntmConsultarFicha = new JMenuItem("Consultar Ficha");
		mnFicha.add(mntmConsultarFicha);
		mntmConsultarFicha.addActionListener(this);

		JMenu mnCaja = new JMenu("Caja");
		menuBar.add(mnCaja);

		mntmCrearCaja = new JMenuItem("Crear Caja");
		mnCaja.add(mntmCrearCaja);
		mntmCrearCaja.addActionListener(this);
		mntmEliminarCaja = new JMenuItem("Eliminar Caja");
		mnCaja.add(mntmEliminarCaja);
		mntmEliminarCaja.addActionListener(this);
		mntmModificarCaja = new JMenuItem("Modificar Caja");
		mnCaja.add(mntmModificarCaja);
		mntmModificarCaja.addActionListener(this);
		mntmConsultarcaja = new JMenuItem("ConsultarCaja");
		mnCaja.add(mntmConsultarcaja);
		mntmConsultarcaja.addActionListener(this);

		JMenu mnColeccion = new JMenu("Coleccion");
		menuBar.add(mnColeccion);

		mntmCrearColeccion = new JMenuItem("Crear Coleccion");
		mnColeccion.add(mntmCrearColeccion);
		mntmCrearColeccion.addActionListener(this);
		mntmEliminarColeccion = new JMenuItem("Eliminar Coleccion");
		mnColeccion.add(mntmEliminarColeccion);
		mntmEliminarColeccion.addActionListener(this);
		mntmModificarColeccion = new JMenuItem("Modificar Coleccion");
		mnColeccion.add(mntmModificarColeccion);
		mntmModificarColeccion.addActionListener(this);
		mntmConsultarColeccion = new JMenuItem("Consultar Coleccion");
		mnColeccion.add(mntmConsultarColeccion);
		mntmConsultarColeccion.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// CRUD "FICHA" - aviso por consola + conexion con vista

		if (e.getSource() == mntmCrearFicha) {
			System.out.println("Pantalla para crear Ficha");
			this.setContentPane(new FichaCrearVista());
			setVisible(true);

		} else if (e.getSource() == mntmEliminarFicha) {
			System.out.println("Pantalla para eliminar Ficha");
			this.setContentPane(new FichaEliminarVista());
			setVisible(true);

		} else if (e.getSource() == mntmModificarFicha) {
			System.out.println("Pantalla para modificar Ficha");
			this.setContentPane(new FichaModificarVista());
			setVisible(true);

		} else if (e.getSource() == mntmConsultarFicha) {
			System.out.println("Pantalla para consultar Ficha");
			this.setContentPane(new FichaConsultarVista());
			setVisible(true);
		}

		// CRUD "CAJA" - aviso por consola + conexion con vista

		if (e.getSource() == mntmCrearCaja) {
			System.out.println("Pantalla para crear una Caja");
			this.setContentPane(new CajaCrearVista());
			setVisible(true);

		} else if (e.getSource() == mntmEliminarCaja) {
			System.out.println("Pantalla para eliminar una Caja");
			this.setContentPane(new CajaEliminarVista());
			setVisible(true);

		} else if (e.getSource() == mntmModificarCaja) {
			System.out.println("Pantalla para modificar una Caja");
			this.setContentPane(new CajaModificarVista());
			setVisible(true);

		} else if (e.getSource() == mntmConsultarcaja) {
			System.out.println("Pantalla para consultar una Caja");
			this.setContentPane(new CajaConsultarVista());
			setVisible(true);

		}

		// CRUD "COLECCION" - aviso por consola + conexion con vista
		if (e.getSource() == mntmCrearColeccion) {
			System.out.println("Pantalla para crear Coleccion");
			this.setContentPane(new ColeccionCrearVista());
			setVisible(true);

		} else if (e.getSource() == mntmEliminarColeccion) {
			System.out.println("Pantalla para eliminar Coleccion");
			this.setContentPane(new ColeccionEliminarVista());
			setVisible(true);

		} else if (e.getSource() == mntmModificarColeccion) {
			System.out.println("Pantalla para modificar Coleccion");
			this.setContentPane(new ColeccionModificarVista());
			setVisible(true);

		} else if (e.getSource() == mntmConsultarColeccion) {
			System.out.println("Pantalla para consultar Coleccion");
			this.setContentPane(new ColeccionConsultarVista());
			setVisible(true);

		}
	
		// reajuste de los componentes al nuevo panel
		this.revalidate(); 
		this.repaint();
	}
}
