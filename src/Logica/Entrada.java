/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Beans.EntradaBeans;
import Persistencia.EntradaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Entrada {
    
    public Entrada(){
        
    }
    
    public int cadastrarEntrada(EntradaBeans  entradaBeans) {
        int cadastrou = 0;
        try {
            EntradaDAO EntradaDAO = new EntradaDAO();
            cadastrou = EntradaDAO.incluirEntrada(entradaBeans);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Cadastrar a Entrada: " + ex.getMessage(), "Cadastrar Entrada", JOptionPane.ERROR_MESSAGE);
        }
        return cadastrou;
    }
    
    public int excluirEntrada(EntradaBeans  entradaBeans) {
        int excluiu = 0;
        try {
            EntradaDAO EntradaDAO = new EntradaDAO();
            excluiu = EntradaDAO.excluirEntrada(entradaBeans);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Excluir a Entrada: " + ex.getMessage(), "Excluir Entrada", JOptionPane.ERROR_MESSAGE);
        }
        return excluiu;
    }
    
     public List selecionarEntradas(String data_entrada, String empresa, String motorista, String placa, String colaborador, String data_final) {
        List listaEntradas = null;
        try {
            EntradaDAO entradaDAO = new EntradaDAO();
            listaEntradas = entradaDAO.selecionarEntradas(data_entrada, empresa, motorista, placa, colaborador, data_final);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Listar as Entradas: " + ex.getMessage(), "Listar Entradas", JOptionPane.ERROR_MESSAGE);
        }
        return listaEntradas;
    }
}
