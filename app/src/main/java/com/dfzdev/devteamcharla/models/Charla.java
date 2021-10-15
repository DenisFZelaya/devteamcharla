package com.dfzdev.devteamcharla.models;

public class Charla {
    private int Id;
    private  String Tema;
    private  String Descripcion;
    private  String Fecha;

    //1.2 Contructores vacio y nulo
    public Charla() {
    }

    public Charla(int id, String tema, String descripcion, String fecha) {
        this.Id = id;
        this.Tema = tema;
        this.Descripcion = descripcion;
        this.Fecha = fecha;
    }

    //1.3 Metodos Set y Get
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTema() {
        return Tema;
    }

    public void setTema(String tema) {
        Tema = tema;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }


}
