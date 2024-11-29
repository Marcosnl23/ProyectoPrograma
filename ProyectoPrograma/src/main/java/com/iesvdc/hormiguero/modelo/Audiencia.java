package com.iesvdc.hormiguero.modelo;

import java.time.LocalDate;
import java.time.ZoneId;

import org.bson.Document;


public class Audiencia {
    private LocalDate fecha;
    private int espectadores;

    public Audiencia(int espectadores, LocalDate fecha) {
        this.espectadores = espectadores;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getEspectadores() {
        return espectadores;
    }

    public void setEspectadores(int espectadores) {
        this.espectadores = espectadores;
    }

    //Método para convertir una audiencia a un documento de MongoDB
    public Document toDocument() {
        return new Document("fecha", fecha)
                .append("espectadores", espectadores);
    }

    //Método para crear una audiencia a partir de un documento de MongoDB
    public static Audiencia fromDocument(Document document) {
        LocalDate fecha = document.getDate("fecha").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int espectadores = document.getInteger("espectadores");
        return new Audiencia(espectadores, fecha);
    }
    @Override
    public String toString() {
        return "Audiencia{" +
                "fecha=" + fecha +
                ", espectadores=" + espectadores +
                '}';
    }
}
