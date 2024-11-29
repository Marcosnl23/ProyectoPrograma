package com.iesvdc.hormiguero.modelo;
import org.bson.Document;

public class Colaborador {
    private String nombre;
    private String rol;

    public Colaborador(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    //Método para convertir un colaborador a un documento de MongoDB
    public Document toDocument() {
        return new Document("nombre", nombre)
                .append("rol", rol);
    }

    //Método para crear un colaborador a partir de un documento de MongoDB
    public static Colaborador fromDocument(Document document) {
        String nombre = document.getString("nombre");
        String rol = document.getString("rol");
        return new Colaborador(nombre, rol);
    }
    @Override
    public String toString() {
        return "Colaborador{" + "nombre=" + nombre + ", rol=" + rol + '}';
    }
}
