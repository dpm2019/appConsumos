package com.example.appconsumos;

public class Categorias {
    private String id_categoria;
    private String desc_categoria;

    public Categorias(String id_categoria, String desc_categoria) {
        this.id_categoria = id_categoria;
        this.desc_categoria = desc_categoria;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDesc_categoria() {
        return desc_categoria;
    }

    public void setDesc_categoria(String desc_categoria) {
        this.desc_categoria = desc_categoria;
    }
}
