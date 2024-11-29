package com.iesvdc.hormiguero.dao;

import com.iesvdc.hormiguero.modelo.Programa;
import com.iesvdc.hormiguero.utils.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ProgramaDAOMongoDB implements ProgramaDAO {

    private MongoDBConnection mongoDBConnection;

    // Constructor que inicializa la conexión a MongoDB
    public ProgramaDAOMongoDB() {
        this.mongoDBConnection = new MongoDBConnection();  // Crear instancia de conexión
    }

    @Override
    public void crearPrograma(Programa programa) {
        try {
            // Obtiene la colección 'Programa' de la conexión
            MongoCollection<Document> collection = mongoDBConnection.getColeccion();

            // Crear un documento a partir del objeto 'Programa' usando su método 'toDocument()'
            Document doc = programa.toDocument();

            // Inserta el documento en la colección
            collection.insertOne(doc);
            System.out.println("Programa creado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al crear el programa: " + e.getMessage());
        }
    }

    @Override
    public Programa obtenerProgramaPorId(int id) {
        try {
            // Obtiene la colección 'Programa' de la conexión
            MongoCollection<Document> collection = mongoDBConnection.getColeccion();

            // Buscar el documento con el id
            Document result = collection.find(Filters.eq("id", id)).first();

            if (result != null) {
                // Convertir el documento de MongoDB a un objeto Programa usando un método estático 'fromDocument'
                Programa programa = Programa.fromDocument(result);
                return programa;
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el programa: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Programa> obtenerTodosProgramas() {
        try {
            // Obtiene la colección 'Programa' de la conexión
            MongoCollection<Document> collection = mongoDBConnection.getColeccion();

            // Obtener todos los documentos
            return collection.find()
                    .map(doc -> Programa.fromDocument(doc))  // Convertir cada documento a un objeto Programa
                    .into(new ArrayList<>());
        } catch (Exception e) {
            System.out.println("Error al obtener todos los programas: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public boolean actualizarPrograma(Programa programa) {
        try {
            // Obtiene la colección 'Programa' de la conexión
            MongoCollection<Document> collection = mongoDBConnection.getColeccion();

            // Convertir el objeto Programa a un Document
            Document doc = programa.toDocument();

            // Actualizar el programa en la base de datos por su ID
            long result = collection.updateOne(Filters.eq("id", programa.getId()), new Document("$set", doc)).getModifiedCount();

            return result > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar el programa: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminarPrograma(int id) {
        try {
            // Obtiene la colección 'Programa' de la conexión
            MongoCollection<Document> collection = mongoDBConnection.getColeccion();

            // Eliminar el programa por su ID
            long result = collection.deleteOne(Filters.eq("id", id)).getDeletedCount();

            return result > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar el programa: " + e.getMessage());
        }

        return false;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        mongoDBConnection.close();
    }
}
