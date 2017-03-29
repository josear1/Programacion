package Ejercicio2_9;

import java.io.IOException;
import java.util.Scanner;

/*
Se pretende realizar un programa para gestionar una base de datos con la lista de estudiantes de una asignatura y sus notas de los trimestres. El número de alumnos es de 10. 

Diseñar el programa que muestre las siguientes opciones:
1- altas.
2- bajas.
3- modificaciones.
4- consulta.
5- listado.
6- salir del programa

Si se selecciona 1, se introducirá el nombre de un nuevo estudiante y tres notas correspondientes a cada uno de los trimestres.
Si se elige la opción 2, se debe borrar al estudiante que pida el usuario, también se borrarán sus notas. Todos los datos posteriores se moverán una posición hacia delante.
La opción 3 modificará los datos de un estudiante.
La opción 4 muestra por pantalla las notas de un estudiante, además de obtener su nota media
La opción 5 muestra por pantalla un listado de todos los alumnos junto con sus notas, además de obtener sus notas medias
Tras procesar cada opción, se debe mostrar de nuevo el menú inicial, hasta que se seleccione laopción 6, que terminará el programa.

Cada una de las opciones se deberá codificar en procedimientos diferentes
*/

public class GestionAlumnos
{
	private Scanner sc = new Scanner(System.in);
	private Alumno[] alumnos = new Alumno[10];
	
	private int cantidadAlumnos()
	{
		for (int i = 0; i < alumnos.length; i++)
		{
			if (alumnos[i] == null)
				return i;
		}
		
		return 0;
	}
	
	public void mostrarMenu()
	{
		System.out.println("******** MENU ********");
		System.out.println("* 1 - Altas          *");
		System.out.println("* 2 - Bajas          *");
		System.out.println("* 3 - Modificaciones *");
		System.out.println("* 4 - Consultas      *");
		System.out.println("* 5 - Listado        *");
		System.out.println("* 6 - Salir          *");
		System.out.println("**********************");
		
		System.out.print("Que desea hacer? ");
		int opcion = sc.nextInt();
		switch (opcion)
		{
			case 1:
				darAltaAlumno();
				break;
			case 2:
				darBajaAlumno();
				break;
			case 3:
				modificarAlumno();
				break;
			case 4:
				hacerConsultas();
				break;
			case 5:
				mostrarListado();
				break;
			case 6:
				sc.close();
				System.out.println("Saliendo.");
				break;
			default:
				System.out.println("Opción no válida.");
				mostrarMenu();
				break;
		}
	}
	
	private void darAltaAlumno()
	{
		int id = cantidadAlumnos();
		alumnos[id] = new Alumno();
		
		System.out.print("Nombre alumno #" + (id + 1) + ": ");
		alumnos[id].nombre = sc.next();
		
		for (int i = 0; i < 3; i++)
		{
			System.out.print("Nota #" + (i + 1) + " de alumno #" + (id + 1) + ": ");
			alumnos[id].notas[i] = sc.nextInt();
		}
		
		System.out.println("Alumno \"" + alumnos[id].nombre + "\" dado de alta correctamente.");
		
		esperarTecla();
		mostrarMenu();
	}
	
	private void darBajaAlumno()
	{
		int max = cantidadAlumnos();
		if (max > 0)
		{
			System.out.print("Id del alumno a dar de baja (1-" + max + "): ");
			int id = sc.nextInt();
			id -= 1;
			
			if (id >= max)
			{
				System.out.println("Id no válida!");
				darBajaAlumno();
				return;
			}
			
			String nombre = alumnos[id].nombre;
			
			for (int i = id; i < max; i++)
				alumnos[i] = alumnos[i + 1];
			
			alumnos[max] = null;
			
			System.out.println("Alumno \"" + nombre + "\" dado de baja correctamente.");
		}
		else
			System.out.println("Se debe dar de alta a algún alumno.");
		
		esperarTecla();
		mostrarMenu();
	}
	
	private void modificarAlumno()
	{
		int max = cantidadAlumnos();
		if (max > 0)
		{
			System.out.print("Id del alumno a modificar (1-" + max + "): ");
			int id = sc.nextInt();
			id -= 1;
			
			if (id >= max)
			{
				System.out.println("Id no válida!");
				modificarAlumno();
				return;
			}
			
			System.out.print("Nombre alumno #" + (id + 1) + ": ");
			alumnos[id].nombre = sc.next();
			
			for (int i = 0; i < 3; i++)
			{
				System.out.print("Nota #" + (i + 1) + " de alumno #" + (id + 1) + ": ");
				alumnos[id].notas[i] = sc.nextInt();
			}
			
			System.out.println("Alumno \"" + alumnos[id].nombre + "\" modificado correctamente.");
		}
		else
			System.out.println("Se debe dar de alta a algún alumno.");
		
		esperarTecla();
		mostrarMenu();
	}
	
	private void hacerConsultas()
	{
		int max = cantidadAlumnos();
		if (max > 0)
		{
			System.out.print("Id del alumno a consultar (1-" + max + "): ");
			int id = sc.nextInt();
			id -= 1;
			
			if (id >= max)
			{
				System.out.println("Id no válida!");
				hacerConsultas();
				return;
			}
			
			System.out.println("Nombre alumno #" + (id + 1) + ": " + alumnos[id].nombre);
			
			int media = 0;
			for (int i = 0; i < 3; i++)
				media += alumnos[id].notas[i];
			
			System.out.println("  Primer trimestre: " + alumnos[id].notas[0]);
			System.out.println("  Segundo trimestre: " + alumnos[id].notas[1]);
			System.out.println("  Tercer trimestre: " + alumnos[id].notas[2]);
			System.out.println("  Media: " + (media / 3) + ".");
		}
		else
			System.out.println("Se debe dar de alta a algún alumno.");
		
		esperarTecla();
		mostrarMenu();
	}
	
	private void mostrarListado()
	{
		for (int i = 0; i < cantidadAlumnos(); i++)
		{
			System.out.println("Alumno \"" + alumnos[i].nombre + "\" (#" + (i + 1) + "):");
			System.out.println("  Primer trimestre: " + alumnos[i].notas[0]);
			System.out.println("  Segundo trimestre: " + alumnos[i].notas[1]);
			System.out.println("  Tercer trimestre: " + alumnos[i].notas[2]);
			
			int media = 0;
			for (int j = 0; j < 3; j++)
				media += alumnos[i].notas[j];
			
			System.out.println("  Media: " + (media / 3));
			System.out.println("________________");
		}
		
		esperarTecla();
		mostrarMenu();
	}
	
	private void esperarTecla()
	{
		System.out.println("Pulsar cualquier tecla para continuar...");
		
		try
		{
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
