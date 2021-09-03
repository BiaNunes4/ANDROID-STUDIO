package com.example.ex1_dmiot;

public class Vacina {
    private Integer id;
    private String nome_vacina;
    private String data_vacina;
    private String unidade;
    private String dose;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNome_vacina(){
        return nome_vacina;
    }
    public void setNome_vacina(String nome_vacina){
        this.nome_vacina = nome_vacina;
    }
    public String getUnidade(){
        return unidade;
    }
    public void setUnidade(String unidade){
        this.unidade = unidade;
    }
    public String getData_vacina(){
        return data_vacina;
    }
    public  void setData_vacina(String data_vacina){
        this.data_vacina = data_vacina;
    }
    public String getDose (){
        return dose;
    }
    public void setDose(String dose){
        this.dose = dose;
    }
}
