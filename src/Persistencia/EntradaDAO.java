/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Beans.EntradaBeans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class EntradaDAO {

    private Connection conn;
    private PreparedStatement selecionarEntradas = null;
    private PreparedStatement incluirRegistro;
    private PreparedStatement excluirRegistro;

    public EntradaDAO() throws SQLException {
        Conexao conexao = new Conexao();
        conn = conexao.getConexao();
        incluirRegistro = conn.prepareStatement("INSERT INTO registrador.entrada (data_entrada, hora_entrada, nome_Empresa, motorista, placa, colaborador, local_destino,data_saida,hora_saida)VALUES (?, ?, ?, ?, ?, ?, ?,?,?);");
        excluirRegistro = conn.prepareStatement("DELETE FROM registrador.entrada WHERE  id_entrada=?;");
        selecionarEntradas = conn.prepareStatement("SELECT \n"
                + "	e.id_entrada,\n"
                + "	e.data_entrada,\n"
                + "	e.hora_entrada,\n"
                + "	e.nome_Empresa,\n"
                + "	e.motorista,\n"
                + "	e.placa,\n"
                + "	e.colaborador,\n"
                + "	e.local_destino,\n"
                + "	e.data_saida,\n"
                + "	e.hora_saida\n"
                + "FROM entrada e\n"
                + "WHERE e.data_entrada = ? or e.nome_Empresa like ? OR  e.motorista LIKE ? \n"
                + "	or e.placa = ? or e.colaborador LIKE ? or e.data_saida = ? ORDER BY e.data_saida IS NULL;");
    }

    public int incluirEntrada(EntradaBeans entradaBeans) throws SQLException {
        int incluiu;
        incluirRegistro.setString(1, entradaBeans.getData_entrada());
        incluirRegistro.setString(2, entradaBeans.getHora_entrada());
        incluirRegistro.setString(3, entradaBeans.getNome_empresa());
        incluirRegistro.setString(4, entradaBeans.getMotorista());
        incluirRegistro.setString(5, entradaBeans.getPlaca());
        incluirRegistro.setString(6, entradaBeans.getColaborador());
        incluirRegistro.setString(7, entradaBeans.getLocal_destino());
        incluirRegistro.setString(8, entradaBeans.getData_saida());
        incluirRegistro.setString(9, entradaBeans.getHora_saida());
        incluiu = incluirRegistro.executeUpdate();
        //System.out.println("Incluiu " + incluiu);
        return incluiu;
    }

    public int excluirEntrada(EntradaBeans entradaBeans) throws SQLException {
        int excluiu;
        excluirRegistro.setInt(1, entradaBeans.getId_Entrada());
        excluiu = excluirRegistro.executeUpdate();
        return excluiu;
    }
    
    public ArrayList selecionarEntradas(String data_entrada, String empresa, String motorista, String placa, String colaborador, String data_final) throws SQLException {
        ArrayList listaEntradas = new ArrayList();
        if (!data_entrada.isEmpty()) {
            selecionarEntradas.setString(1, data_entrada);
        } else {
            selecionarEntradas.setString(1, "null");
        }

        if (!empresa.isEmpty()) {
            selecionarEntradas.setString(2, "%" + empresa + "%");
        } else {
            selecionarEntradas.setString(2, "null");
        }

        if (!motorista.isEmpty()) {
            selecionarEntradas.setString(3, "%" + motorista + "%");
        } else {
            selecionarEntradas.setString(3, "null");
        }
        if (!placa.isEmpty()) {
            selecionarEntradas.setString(4, placa);
        } else {
            selecionarEntradas.setString(4, "null");
        }
        if (!colaborador.isEmpty()) {
            selecionarEntradas.setString(5, "%" + colaborador + "%");
        } else {
            selecionarEntradas.setString(5, "null");
        }
        if (!data_final.isEmpty()) {
            selecionarEntradas.setString(6, data_final);
        } else {
            selecionarEntradas.setString(6, "null");
        }
        ResultSet resultados = selecionarEntradas.executeQuery();
        System.out.println("teste " + resultados);
        while (resultados.next()) {
            EntradaBeans entradaBeans = new EntradaBeans();
            entradaBeans.setId_Entrada(resultados.getInt(1));
            System.out.println(resultados.getInt(1) + " ID");
            entradaBeans.setData_entrada(resultados.getString(2));
            System.out.println(resultados.getString(2) + " DATA");
            entradaBeans.setHora_entrada(resultados.getString(3));
            entradaBeans.setNome_empresa(resultados.getString(4));
            entradaBeans.setMotorista(resultados.getString(5));
            entradaBeans.setPlaca(resultados.getString(6));
            entradaBeans.setColaborador(resultados.getString(7));
            entradaBeans.setLocal_destino(resultados.getString(8));
            entradaBeans.setData_saida(resultados.getString(9));
            entradaBeans.setHora_saida(resultados.getString(10));
            listaEntradas.add(entradaBeans);

        }
        return listaEntradas;
    }

}
