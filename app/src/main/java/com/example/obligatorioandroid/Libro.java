package com.example.obligatorioandroid;

public class Libro {

    private String txtLibroTitulo = "";
    private String txtLibroAutor = "";
    private String txtLibroEditorial = "";

    // Nombre de la tabla
    public static final String TABLA = "Libros";

    public static final String Libro_Titulo = "Titulo";
    public static final String Libro_Autor = "Autor";
    public static final String Libro_Editorial = "Editorial";

    public Libro(){
    }

    public Libro (String txtLibroTitulo, String txtLibroAutor, String txtLibroEditorial){
        this.txtLibroTitulo = txtLibroTitulo;
        this.txtLibroAutor = txtLibroAutor;
        this.txtLibroEditorial = txtLibroEditorial;
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
}
