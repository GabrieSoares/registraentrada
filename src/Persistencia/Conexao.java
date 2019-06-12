package Persistencia;

import java.awt.HeadlessException;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Conexao {

    static Connection conexao;

    public Conexao() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrador", "root", "");

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Conectar no BD: " + ex.getMessage(), "Conectar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Connection getConexao() {
        return conexao;
    }

}
