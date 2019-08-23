package com.example.appconsumos;

public class Alimentos {

    private String id_alimento;
    private String desc_alimento;
    private String fec_vencimiento;
    private String categoria;
    private String unidades;
    private String desc_peso;
    private String fotografia;
    private String estado;

    public Alimentos(String id_alimento, String desc_alimento, String fec_vencimiento, String categoria, String unidades, String desc_peso, String fotografia, String estado) {
        this.id_alimento = id_alimento;
        this.desc_alimento = desc_alimento;
        this.fec_vencimiento = fec_vencimiento;
        this.categoria = categoria;
        this.unidades = unidades;
        this.desc_peso = desc_peso;
        this.fotografia = fotografia;
        this.estado = estado;
    }

    public String getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(String id_alimento) {
        this.id_alimento = id_alimento;
    }

    public String getDesc_alimento() {
        return desc_alimento;
    }

    public void setDesc_alimento(String desc_alimento) {
        this.desc_alimento = desc_alimento;
    }

    public String getFec_vencimiento() {
        return fec_vencimiento;
    }

    public void setFec_vencimiento(String fec_vencimiento) {
        this.fec_vencimiento = fec_vencimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getDesc_peso() {
        return desc_peso;
    }

    public void setDesc_peso(String desc_peso) {
        this.desc_peso = desc_peso;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
