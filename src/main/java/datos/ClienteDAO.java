package datos;

import static datos.Conexion.close;
import dominio.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String SQL_SELECT = "SELECT * FROM cliente";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM cliente WHERE id_cliente = ?";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) VALUE (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";

    public List<Cliente> listar() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cl = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                cl = new Cliente(id_cliente, nombre, apellido, email, telefono, saldo);
                clientes.add(cl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return clientes;
    }

    public Cliente encontrar(Cliente cl) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_SELECT_BY_ID);
            ps.setInt(1, cl.getIdCliente());
            rs = ps.executeQuery();
            rs.absolute(1);

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");
            
            cl.setNombre(nombre);
            cl.setApellido(apellido);
            cl.setEmail(email);
            cl.setTelefono(telefono);
            cl.setSaldo(saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return cl;
    }
    
    public int insertar(Cliente cl){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getApellido());
            ps.setString(3, cl.getEmail());
            ps.setString(4, cl.getTelefono());
            ps.setDouble(5, cl.getSaldo());
            
            rows = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(ps);
            close(conn);
        }
        
        return rows;
    }
    
    public int actualizar(Cliente cl){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getApellido());
            ps.setString(3, cl.getEmail());
            ps.setString(4, cl.getTelefono());
            ps.setDouble(5, cl.getSaldo());
            ps.setInt(6, cl.getIdCliente());
            
            rows = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(ps);
            close(conn);
        }
        
        return rows;
    }
    
        public int eliminar(Cliente cl){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, cl.getIdCliente());
            
            rows = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(ps);
            close(conn);
        }
        
        return rows;
    }
}
