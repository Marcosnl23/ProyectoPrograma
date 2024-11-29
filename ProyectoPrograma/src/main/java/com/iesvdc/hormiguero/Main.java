package com.iesvdc.hormiguero;


import com.iesvdc.hormiguero.interfaz.Menu;
import com.iesvdc.hormiguero.utils.MongoDBConnection;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de MongoDBConnection
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        Menu menu = new Menu();
        menu.menuPrincipal();
    }
}