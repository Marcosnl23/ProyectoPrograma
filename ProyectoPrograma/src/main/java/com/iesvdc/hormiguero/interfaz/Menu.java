package com.iesvdc.hormiguero.interfaz;

import com.iesvdc.hormiguero.dao.ProgramaDAOMongoDB;
import com.iesvdc.hormiguero.modelo.Programa;
import com.iesvdc.hormiguero.modelo.Horario;
import com.iesvdc.hormiguero.modelo.Audiencia;
import com.iesvdc.hormiguero.modelo.Colaborador;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    private static ProgramaDAOMongoDB programaDAO = new ProgramaDAOMongoDB(); // Conexión al DAO

    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner para leer la entrada del usuario

        boolean salir = false;
        while (!salir) {
            // Mostrar el menú
            System.out.println("Menú Principal");
            System.out.println("1. Crear Programa");
            System.out.println("2. Listar Programas");
            System.out.println("3. Buscar Programa por id");
            System.out.println("4. Actualizar Programa");
            System.out.println("5. Eliminar Programa");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();  // Leer la opción seleccionada por el usuario

            // Procesar la opción seleccionada
            switch (opcion) {
                case 1:
                    System.out.println("Crear Programa");
                    crearPrograma(scanner);
                    break;
                case 2:
                    System.out.println("Listar Programas");
                    listarProgramas();
                    break;
                case 3:
                    System.out.println("Buscar Programa por id");
                    buscarProgramaPorId(scanner);
                    break;
                case 4:
                    System.out.println("Actualizar Programa");
                    actualizarPrograma(scanner);
                    break;
                case 5:
                    System.out.println("Eliminar Programa");
                    eliminarPrograma(scanner);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        scanner.close();
    }

    private static void crearPrograma(Scanner scanner) {
        // Pedir los datos del programa
        System.out.print("Nombre del programa: ");
        String nombre = scanner.next();  // Usar nextLine() para permitir espacios

        System.out.print("Categoría del programa: ");
        String categoria = scanner.next();  // Usar nextLine() para permitir espacios

        System.out.print("Día de la semana (1-7): ");
        int diaDeLaSemana = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Hora de inicio (en formato 24h): ");
        int horaDeInicio = scanner.nextInt();
        scanner.nextLine();

        // Crear un objeto Horario
        Horario horario = new Horario(diaDeLaSemana, horaDeInicio);

        // Crear listas vacías para audiencias y colaboradores
        List<Audiencia> audiencias = new ArrayList<>();
        List<Colaborador> colaboradores = new ArrayList<>();

        // Agregar audiencias (puedes agregar tantas como necesites)
        System.out.print("¿Cuántas audiencias quieres agregar? ");
        int numAudiencias = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea pendiente
        for (int i = 0; i < numAudiencias; i++) {
            System.out.print("Ingrese la fecha de la audiencia (formato: YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();  // Usar nextLine() para leer la fecha completa
            LocalDate fecha = LocalDate.parse(fechaStr);

            System.out.print("Ingrese el número de espectadores: ");
            int espectadores = scanner.nextInt();
            scanner.nextLine();  // Limpiar el salto de línea pendiente

            // Crear la audiencia y agregarla a la lista
            Audiencia audiencia = new Audiencia(espectadores, fecha);
            audiencias.add(audiencia);
        }

        // Agregar colaboradores (puedes agregar tantos como necesites)
        System.out.print("¿Cuántos colaboradores quieres agregar? ");
        int numColaboradores = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea pendiente
        for (int i = 0; i < numColaboradores; i++) {
            System.out.print("Ingrese el nombre del colaborador: ");
            String nombreColaborador = scanner.nextLine();  // Usar nextLine() para permitir espacios

            System.out.print("Ingrese el rol del colaborador: ");
            String rolColaborador = scanner.nextLine();  // Usar nextLine() para permitir espacios

            // Crear el colaborador y agregarlo a la lista
            Colaborador colaborador = new Colaborador(nombreColaborador, rolColaborador);
            colaboradores.add(colaborador);
        }

        // Crear el programa con los colaboradores y audiencias
        Programa programa = new Programa(colaboradores, audiencias, horario, categoria, nombre);

        // Llamar al DAO para crear el programa
        programaDAO.crearPrograma(programa);

        System.out.println("Programa creado exitosamente.");
    }



    // Método para listar todos los programas
    private static void listarProgramas() {
        // Obtener todos los programas desde la base de datos
        List<Programa> programas = programaDAO.obtenerTodosProgramas();

        // Imprimir todos los programas
        if (programas.isEmpty()) {
            System.out.println("No hay programas disponibles.");
        } else {
            for (Programa programa : programas) {
                System.out.println(programa);
            }
        }
    }

    // Método para buscar un programa por su ID
    private static void buscarProgramaPorId(Scanner scanner) {
        // Pedir el ID del programa
        System.out.print("Ingrese el ID del programa: ");
        int id = scanner.nextInt();

        // Obtener el programa por su ID
        Programa programa = programaDAO.obtenerProgramaPorId(id);

        if (programa != null) {
            System.out.println("Programa encontrado: " + programa);
        } else {
            System.out.println("No se encontró el programa con ID " + id);
        }
    }

    // Método para actualizar un programa
    private static void actualizarPrograma(Scanner scanner) {
        // Pedir el ID del programa a actualizar
        System.out.print("Ingrese el ID del programa a actualizar: ");
        int id = scanner.nextInt();

        // Obtener el programa por ID
        Programa programa = programaDAO.obtenerProgramaPorId(id);

        if (programa != null) {
            // Mostrar los detalles actuales y permitir al usuario actualizar
            System.out.println("Programa actual: " + programa);

            System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
            String nuevoNombre = scanner.next();
            if (!nuevoNombre.isEmpty()) {
                programa.setNombre(nuevoNombre);
            }

            System.out.print("Nueva categoría (dejar vacío para no cambiar): ");
            String nuevaCategoria = scanner.next();
            if (!nuevaCategoria.isEmpty()) {
                programa.setCategoria(nuevaCategoria);
            }

            System.out.print("Nuevo día de la semana (dejar 0 para no cambiar): ");
            int nuevoDiaDeLaSemana = scanner.nextInt();
            if (nuevoDiaDeLaSemana != 0) {
                programa.getHorario().setDiaDeLaSemana(nuevoDiaDeLaSemana);
            }

            System.out.print("Nueva hora de inicio (dejar 0 para no cambiar): ");
            int nuevaHoraDeInicio = scanner.nextInt();
            if (nuevaHoraDeInicio != 0) {
                programa.getHorario().setHoraDeInicio(nuevaHoraDeInicio);
            }



            // Llamar al método para actualizar el programa
            if (programaDAO.actualizarPrograma(programa)) {
                System.out.println("Programa actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el programa.");
            }
        } else {
            System.out.println("No se encontró el programa con ID " + id);
        }
    }

    // Método para eliminar un programa
    private static void eliminarPrograma(Scanner scanner) {
        // Pedir el ID del programa a eliminar
        System.out.print("Ingrese el ID del programa a eliminar: ");
        int id = scanner.nextInt();

        // Llamar al DAO para eliminar el programa
        if (programaDAO.eliminarPrograma(id)) {
            System.out.println("Programa eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el programa con ID " + id);
        }
    }
}
