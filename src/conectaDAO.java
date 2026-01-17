import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection connectDB() {
        Connection conn = null;
        
        try {
            // Conecta ao banco "uc11" usando seu usuário root e senha fornecida
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/uc11?useSSL=false&user=root&password=M@2532#1579"
            );
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }
        return conn;
    }
    
}
