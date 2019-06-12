/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author gabri
 */
public class EntradaBeans {

    public EntradaBeans() {

    }

    private int id_Entrada;
    private String data_entrada;
    private String hora_entrada;
    private String nome_empresa;
    private String motorista;
    private String placa;
    private String colaborador;
    private String local_destino;
    private String data_saida;
    private String hora_saida;
    private String peso_carga;

    public int getId_Entrada() {
        return id_Entrada;
    }

    public void setId_Entrada(int id_Entrada) {
        this.id_Entrada = id_Entrada;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getLocal_destino() {
        return local_destino;
    }

    public void setLocal_destino(String local_destino) {
        this.local_destino = local_destino;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }

    public String getPeso_carga() {
        return peso_carga;
    }

    public void setPeso_carga(String peso_carga) {
        this.peso_carga = peso_carga;
    }
    
}
