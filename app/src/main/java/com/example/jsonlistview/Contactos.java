package com.example.jsonlistview;

public class Contactos {

    private String Nombre;
    private String Email;
    public Contactos(String name, String email){
        Nombre = name; Email = email;
    }
    public String getTitulo(){
        return Nombre;
    }
    public String getSubtitulo(){
        return Email;
    }
}
