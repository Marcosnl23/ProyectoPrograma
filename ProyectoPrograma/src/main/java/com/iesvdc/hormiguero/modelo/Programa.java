package com.iesvdc.hormiguero.modelo;

import org.bson.Document;
import java.util.List;
import java.util.stream.Collectors;

public class Programa {
    private int id;
    private String nombre;
    private String categoria;
    private Horario horario;
    private List<Audiencia> audiencias;
    private List<Colaborador> colaboradores;

    public Programa(int id, String nombre, String categoria, Horario horario, List<Audiencia> audiencias, List<Colaborador> colaboradores) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.horario = horario;
        this.audiencias = audiencias;
        this.colaboradores = colaboradores;
    }

    public Programa(List<Colaborador> colaboradores, List<Audiencia> audiencias, Horario horario, String categoria, String nombre) {
        this.colaboradores = colaboradores;
        this.audiencias = audiencias;
        this.horario = horario;
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Audiencia> getAudiencias() {
        return audiencias;
    }

    public void setAudiencias(List<Audiencia> audiencias) {
        this.audiencias = audiencias;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    //Metodo para convertir un programa a un documento de MongoDB
    public Document toDocument() {
        return new Document("id", id)
                .append("nombre", nombre)
                .append("categoria", categoria)
                .append("horario", horario.toDocument())
                .append("audiencias", audiencias.stream().map(a -> a.toDocument()).collect(Collectors.toList()))
                .append("colaboradores", colaboradores.stream().map(c -> c.toDocument()).collect(Collectors.toList()));
    }

    //Metodo para crear un programa a partir de un documento de MongoDB
    public static Programa fromDocument(Document document) {
        int id = document.getInteger("id");
        String nombre = document.getString("nombre");
        String categoria = document.getString("categoria");
        Horario horario = Horario.fromDocument((Document) document.get("horario"));
        List<Audiencia> audiencias = ((List<Document>) document.get("audiencias")).stream().map(Audiencia::fromDocument).collect(Collectors.toList());
        List<Colaborador> colaboradores = ((List<Document>) document.get("colaboradores")).stream().map(Colaborador::fromDocument).collect(Collectors.toList());
        return new Programa(id, nombre, categoria, horario, audiencias, colaboradores);
    }

    @Override
    public String toString() {
        return "Programa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", horario=" + horario +
                ", audiencias=" + audiencias +
                ", colaboradores=" + colaboradores +
                '}';
    }
}
