package com.example.appconsumos;

public class Supermercados {
    private String id_smercado;
    private String desc_smercado;

    public Supermercados(String id_smercado, String desc_smercado) {
        this.id_smercado = id_smercado;
        this.desc_smercado = desc_smercado;
    }

    public String getId_smercado() {
        return id_smercado;
    }

    public void setId_smercado(String id_smercado) {
        this.id_smercado = id_smercado;
    }

    public String getDesc_smercado() {
        return desc_smercado;
    }

    public void setDesc_smercado(String desc_smercado) {
        this.desc_smercado = desc_smercado;
    }
}
