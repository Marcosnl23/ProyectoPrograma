package com.iesvdc.hormiguero.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {
    private final MongoCollection<Document> coleccion;
    private final MongoClient mongoClient;

    public MongoDBConnection() {
        MongoCollection<Document> tempColeccion = null;
        MongoClient tempMongoClient = null;

        try {
            // Conectar a MongoDB en localhost:27017 usando MongoClients.create()
            tempMongoClient = MongoClients.create("mongodb://localhost:27017");

            // Conectar a la base de datos "Hormiguerodb"
            MongoDatabase database = tempMongoClient.getDatabase("Hormiguerodb");

            // Conectar a la colección 'Programa'
            tempColeccion = database.getCollection("Programa");

            // Mostrar mensaje de éxito de la conexión
            System.out.println("Conexión a MongoDB realizada con éxito a la base de datos 'Hormiguerodb'.");

        } catch (Exception e) {
            System.out.println("Error al conectar a MongoDB: " + e.getMessage());
        } finally {
            // Asignar los valores inicializados a las variables de instancia
            this.mongoClient = tempMongoClient;
            this.coleccion = tempColeccion;
        }
    }

    // Método para obtener la colección
    public MongoCollection<Document> getColeccion() {
        return coleccion;
    }

    // Método para cerrar la conexión
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }
}
