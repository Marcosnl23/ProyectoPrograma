package com.iesvdc.hormiguero.modelo;

import org.bson.Document;

public class Horario {
    private int diaDeLaSemana;
    private int horaDeInicio;

    public Horario(int diaDeLaSemana, int horaDeInicio) {
        this.diaDeLaSemana = diaDeLaSemana;
        this.horaDeInicio = horaDeInicio;
    }

    public int getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public void setDiaDeLaSemana(int diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
    }

    public int getHoraDeInicio() {
        return horaDeInicio;
    }

    public void setHoraDeInicio(int horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }

    //Método para convertir un horario a un documento de MongoDB
    public Document toDocument() {
        return new Document("diaDeLaSemana", diaDeLaSemana)
                .append("horaDeInicio", horaDeInicio);
    }

    //Método para crear un horario a partir de un documento de MongoDB
    public static Horario fromDocument(Document document) {
        int diaDeLaSemana = document.getInteger("diaDeLaSemana");
        int horaDeInicio = document.getInteger("horaDeInicio");
        return new Horario(diaDeLaSemana, horaDeInicio);
    }
    @Override
    public String toString() {
        return "Horario{" +
                "diaDeLaSemana=" + diaDeLaSemana +
                ", horaDeInicio=" + horaDeInicio +
                '}';
    }
}
