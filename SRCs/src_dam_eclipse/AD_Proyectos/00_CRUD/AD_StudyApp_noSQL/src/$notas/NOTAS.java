package $notas;

public class NOTAS {
 /*
  --> mvc LISTO CON METODOS EN MODELO
  		-Generar modelos
  			Ficha [+] Coleccion [+] Caja [+]
  		- Generar documentos vistas
  			GENprincipal[+] Ficha [+] Coleccion [+] Caja [+]
  		- generar documentos controladores
  			Ficha [+ ,falta actionPerform] Coleccion [+,falta actionPerform] Caja [+ ,falta actionPerform]
  		- CONECTAR vista - contolador
  		 	Ficha [+] 	Coleccion [+] Caja [+]
  		
  	- Taller Mecanico:
		- metodos CRUD del modelo  "Taller Mecanico"[+]
			--> CRUD  y export e import funcional
				- metodos de elementos que recogen datos en vistas
					Vehiculo [ CRUD + ], Averia [ alta , baja, modificacion, consulta ], 
					Mecanico [ alta , baja, modificacion, consulta ], 
					Exportar [ alta , baja, modificacion, consulta], 
					Importar [alta , baja, modificacion, consulta ] 
					
				- Armar ActionPerformed en controladores
					Averia [ ], Mecanico [ ], Vehiculo [ ], Exportar [ ], Importar [ ] 
	
	
		- metodos IMPORTAR EXPORT fichero[]
		- metodos gestiones g1[] g2[] g3[]
	
  		
  		
  		
  		
  		
  		
  --> CRUD
  
  		- Funcional en Ficha
  		- Funcional en Caja
  		- Funcional en Colecciones
  		
  		
  --> exportar importar (NO HACE FALTA)
  --> BBDD SQL
 
  * */
}



/* TALLER MECANICO
 
--> mvc LISTO CON METODOS EN MODELO
	-Generar modelos
		Averia [+], Mecanico [+], Vehiculo [+] Taller [ ], 
		Fichero[+], Gestion[+]
	
	- Generar documentos vistas
		GENprincipal[+], Averia [+], Mecanico [+], Vehiculo [+]
		Fichero[+], Gestion[+]
		
	- generar documentos controladores(revisar)
		Averia [ +,falta actionPerform]
		Mecanico [+ ,falta actionPerform]
		Vehiculo [+,falta actionPerform]
		Fichero[+,falta actionPerform]
		Gestion[+,falta actionPerform]
	
	- CONECTAR vista - contolador
		Averia [+], Mecanico [+], Vehiculo [+]
		Fichero[+], Gestion[+]
		
		
	- Taller Mecanico:
		- metodos CRUD del modelo  "Taller Mecanico"[+]
			--> CRUD  y export e import funcional
				- metodos de elementos que recogen datos en vistas
					Vehiculo [ CRUD + ], Averia [ alta , baja, modificacion, consulta ], 
					Mecanico [ alta , baja, modificacion, consulta ], 
					Exportar [ alta , baja, modificacion, consulta], 
					Importar [alta , baja, modificacion, consulta ] 
					
				- Armar ActionPerformed en controladores
					Averia [ ], Mecanico [ ], Vehiculo [ ], Exportar [ ], Importar [ ] 
	
	
		- metodos IMPORTAR EXPORT fichero[]
		- metodos gestiones g1[] g2[] g3[]
	
--> exportar importar metodos en Fichero
	- Exportar()[]
	- Importar()[]
		

	
--> BBDD SQL(JPA)- MavenProject - trabajo en DAO
	- agregar dependencias pom.xml[ ]
	- diseñar dao (llama solo a Modelo)JPA[ ]
		- Recoger metodos exportar e importar en "Fichero"[ ]
		- Recoger metodos de cada gestion
					-
					-
					-
		- Recoger metodos del crud en "TallerMecanico" [ ]


* */