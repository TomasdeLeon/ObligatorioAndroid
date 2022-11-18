package com.example.obligatorioandroid;

public class Libro {

    private Integer libroId = 0;
    private String txtLibroTitulo = "";
    private String txtLibroAutor = "";
    private String txtLibroEditorial = "";
    private String txtLibroDescripcion = "";

    // Nombre de la tabla
    public static final String TABLA = "Libros";

    public static final String Libro_Id = "ID";
    public static final String Libro_Titulo = "Titulo";
    public static final String Libro_Autor = "Autor";
    public static final String Libro_Editorial = "Editorial";
    public static final String Libro_Descripcion = "Descripcion";

    public Libro(){
    }

    public Libro (int libroId, String txtLibroTitulo, String txtLibroAutor, String txtLibroEditorial, String txtLibroDescripcion){
        this.libroId = libroId;
        this.txtLibroTitulo = txtLibroTitulo;
        this.txtLibroAutor = txtLibroAutor;
        this.txtLibroEditorial = txtLibroEditorial;
        this.txtLibroDescripcion = txtLibroDescripcion;
    }

    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }

    public Integer getLibroId() {
        return libroId;
    }

    public void setTxtLibroTitulo(String txtLibroTitulo) {
        this.txtLibroTitulo = txtLibroTitulo;
    }

    public String getTxtLibroTitulo() {
        return txtLibroTitulo;
    }

    public String getTxtLibroAutor() {
        return txtLibroAutor;
    }

    public void setTxtLibroAutor(String txtLibroAutor) {
        this.txtLibroAutor = txtLibroAutor;
    }

    public void setTxtLibroEditorial(String txtLibroEditorial) {
        this.txtLibroEditorial = txtLibroEditorial;
    }

    public String getTxtLibroEditorial() {
        return txtLibroEditorial;
    }

    public void setTxtLibroDescripcion(String txtLibroDescripcion) {
        this.txtLibroDescripcion = txtLibroDescripcion;
    }

    public String getTxtLibroDescripcion() {
        return txtLibroDescripcion;
    }
}