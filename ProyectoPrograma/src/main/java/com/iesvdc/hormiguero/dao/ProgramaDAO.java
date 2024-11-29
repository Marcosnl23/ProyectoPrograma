package com.iesvdc.hormiguero.dao;

import com.iesvdc.hormiguero.modelo.Programa;

import java.util.List;

public interface ProgramaDAO {
    //Crea un nuevo programa en la base de datos.
    void crearPrograma(Programa programa);

    //Obtiene un programa por su ID.
    Programa obtenerProgramaPorId(int id);

    //Obtiene todos los programas
    List<Programa> obtenerTodosProgramas();

    //Actualiza los detalles de un programa existente.
    boolean actualizarPrograma(Programa programa);

    //Elimina un programa por su ID.
    boolean eliminarPrograma(int id);
}
