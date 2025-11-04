import java.sql.*;

public class Conexion {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "011304";
    private static final String URL = "jdbc:mysql://localhost:3306/BDActividad";
    public static void main (String[] args) {
        Connection conn = null;
        try{
            conn= DriverManager.getConnection(URL,USER,PASS);

            if (conn != null) {
                System.out.println("¡Conexión establecida con éxito!");

            }

        }catch (SQLException e){
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // 3. Cierra la conexión SIEMPRE
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Cerrando la conexión.");
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }

    }
}}
