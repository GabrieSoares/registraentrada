package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Conexao {

    public static Connection conexao;

    public Conexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/registrador", "root", "zaqwsx");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao Conectar no BD: " + ex.getMessage(), "Conectar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Connection getConexao() {
        return conexao;
    }

}
