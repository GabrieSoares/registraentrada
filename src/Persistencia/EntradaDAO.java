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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class EntradaDAO {

    private Connection conn;
    private Statement selecionarEntradas;
    private PreparedStatement incluirRegistro;
    private PreparedStatement alterarRegistro;
    private PreparedStatement excluirRegistro;
    private PreparedStatement SelecionarEntrada;

    public EntradaDAO() throws SQLException {
        Conexao conexao = new Conexao();
        conn = conexao.getConexao();
        incluirRegistro = conn.prepareStatement("INSERT INTO registrador.entrada (data_entrada, hora_entrada, nome_Empresa, motorista, placa, colaborador, local_destino,data_saida,hora_saida)VALUES (?, ?, ?, ?, ?, ?, ?,?,?);");
        alterarRegistro = conn.prepareStatement("UPDATE entrada SET data_entrada = ?, hora_entrada = ?, nome_Empresa = ?, motorista = ?, placa = ?, colaborador = ?, local_destino = ?, data_saida = ?, hora_saida =? WHERE id_entrada = ?;");
        excluirRegistro = conn.prepareStatement("DELETE FROM registrador.entrada WHERE  id_entrada=?;");
        selecionarEntradas = conn.createStatement();
        SelecionarEntrada = conn.prepareStatement("Select * from entrada where id_entrada = ?");
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
    
    public int alterarEntrada(EntradaBeans entradaBeans) throws SQLException {
        int alterou;
        alterarRegistro.setString(1, entradaBeans.getData_entrada());
        alterarRegistro.setString(2, entradaBeans.getHora_entrada());
        alterarRegistro.setString(3, entradaBeans.getNome_empresa());
        alterarRegistro.setString(4, entradaBeans.getMotorista());
        alterarRegistro.setString(5, entradaBeans.getPlaca());
        alterarRegistro.setString(6, entradaBeans.getColaborador());
        alterarRegistro.setString(7, entradaBeans.getLocal_destino());
        alterarRegistro.setString(8, entradaBeans.getData_saida());
        alterarRegistro.setString(9, entradaBeans.getHora_saida());
        alterarRegistro.setInt(10, entradaBeans.getId_Entrada());
        alterou = alterarRegistro.executeUpdate();
        //System.out.println("Incluiu " + incluiu);
        return alterou;
    }

    public int excluirEntrada(EntradaBeans entradaBeans) throws SQLException {
        int excluiu;
        excluirRegistro.setInt(1, entradaBeans.getId_Entrada());
        excluiu = excluirRegistro.executeUpdate();
        return excluiu;
    }

    public EntradaBeans selecionarEntrada(String idEntrada) throws SQLException {
        EntradaBeans entradaBeans = new EntradaBeans();
        SelecionarEntrada.setString(1, idEntrada);
        ResultSet resultado = SelecionarEntrada.executeQuery();
        if (resultado.next()) {
            entradaBeans.setId_Entrada(resultado.getInt(1));
            entradaBeans.setData_entrada(resultado.getString(2));
            entradaBeans.setHora_entrada(resultado.getString(3));
            entradaBeans.setNome_empresa(resultado.getString(4));
            entradaBeans.setMotorista(resultado.getString(5));
            entradaBeans.setPlaca(resultado.getString(6));
            entradaBeans.setColaborador(resultado.getString(7));
            entradaBeans.setLocal_destino(resultado.getString(8));
            entradaBeans.setData_saida(resultado.getString(9));
            entradaBeans.setHora_saida(resultado.getString(10));
        }
        return entradaBeans;
    }

    public ArrayList selecionarEntradas(int periodo, String dataInicial, String dataFinal, String empresa, String motorista, String placa, String colaborador) throws SQLException {
        ArrayList listaEntradas = new ArrayList();
        String where = new String();
        if (periodo == 0) {
            if (!dataInicial.isEmpty()) {
                where = "e.data_entrada between '" + dataInicial + "' and '" + dataFinal + "' ";
            }
        }

        if (!empresa.isEmpty()) {
            where += "and e.nome_Empresa like '%" + empresa + "%'";
        }
        if (!motorista.isEmpty()) {
            where += " and e.motorista like '%" + motorista + "%'";
        }


        /*if (!empresa.isEmpty()) {
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
        }*/
        String consulta = "SELECT e.id_entrada, e.data_entrada, e.hora_entrada, e.nome_Empresa, e.motorista,\n"
                + "e.placa, e.colaborador, e.local_destino, e.data_saida, e.hora_saida\n"
                + "FROM entrada e WHERE " + where;
        System.out.println("Consulta: " + consulta);
        ResultSet resultados = selecionarEntradas.executeQuery(consulta);
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
