/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Persistencia;
//Integrantes: Manceñido Xenia, Ortiz Lourdes, Ortiz Santiago, Ozan Santiago
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Lulim
 */
public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/universidadulp";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    // Constructor privado para que no se pueda instanciar
    private Conexion() {}

    public static Connection getConexion() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                connection.setAutoCommit(true);
                // Opcional: mensaje solo para pruebas
                JOptionPane.showMessageDialog(null, "Conexión exitosa");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar Driver: " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la BD: " + ex.getMessage());
            }
        }
        return connection;
    }
    
}
