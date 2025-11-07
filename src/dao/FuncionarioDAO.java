package dao;

import java.sql.*;
import java.util.*;

import model.Funcionario;
import util.ConexionBD;

public class FuncionarioDAO {
    public void insertar(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionario (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, f.getTipoIdentificacion());
            pstmt.setString(2, f.getNumeroIdentificacion());
            pstmt.setString(3, f.getNombres());
            pstmt.setString(4, f.getApellidos());
            pstmt.setString(5, f.getEstadoCivil());
            pstmt.setString(6, f.getSexo());
            pstmt.setString(7, f.getDireccion());
            pstmt.setString(8, f.getTelefono());
            pstmt.setDate(9, new java.sql.Date(f.getFechaNacimiento().getTime()));
            pstmt.executeUpdate();
        } 
    }

    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                f.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setEstadoCivil(rs.getString("estado_civil"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                java.sql.Date sqlDate = rs.getDate("fecha_nacimiento");
                if (sqlDate != null) {
                    f.setFechaNacimiento(sqlDate);
                } else {
                    f.setFechaNacimiento(null);
                }
                funcionarios.add(f);
            }
        }
        return funcionarios;
    }
    public void actualizar(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionario SET tipo_identificacion=?, numero_identificacion=?, nombres=?, apellidos=?, estado_civil=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=? WHERE id_funcionario=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getTipoIdentificacion());
            ps.setString(2, f.getNumeroIdentificacion());
            ps.setString(3, f.getNombres());
            ps.setString(4, f.getApellidos());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getSexo());
            ps.setString(7, f.getDireccion());
            ps.setString(8, f.getTelefono());
            if (f.getFechaNacimiento() != null) {
                ps.setDate(9, new java.sql.Date(f.getFechaNacimiento().getTime()));
            } else {
                ps.setNull(9, java.sql.Types.DATE);
            }
            ps.setInt(10, f.getIdFuncionario());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id_funcionario=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}