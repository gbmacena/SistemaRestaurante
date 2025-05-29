package dao;

import model.Pedido;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public int inserirPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (id_cliente, data_hora, status) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setString(2, pedido.getDataHora().toString());
            stmt.setString(3, pedido.getStatus());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erro ao inserir pedido.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("ID do pedido não obtido.");
                }
            }
        }
    }

    public Pedido buscarPorId(int pedidoId) throws SQLException {
        String sql = "SELECT * FROM Pedido WHERE id_pedido = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setIdPedido(rs.getInt("id_pedido"));
                    pedido.setIdCliente(rs.getInt("id_cliente"));
                    pedido.setDataHora(LocalDateTime.parse(rs.getString("data_hora")));
                    pedido.setStatus(rs.getString("status"));
                    return pedido;
                }
            }
        }
        return null;
    }
    
    public void atualizarPedido(Pedido pedido) throws SQLException {
        String sql = "UPDATE Pedido SET id_cliente = ?, data_hora = ?, status = ? WHERE id_pedido = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setString(2, pedido.getDataHora().toString());
            stmt.setString(3, pedido.getStatus());
            stmt.setInt(4, pedido.getIdPedido());
            stmt.executeUpdate();
        }
    }
    
    public void excluirPedido(int pedidoId) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            stmt.executeUpdate();
        }
    }
    
    public List<Pedido> listarTodos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setDataHora(LocalDateTime.parse(rs.getString("data_hora")));
                pedido.setStatus(rs.getString("status"));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }
    
    public List<Pedido> consultarPedidos(String status, int idCliente) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Pedido WHERE 1=1");
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND status = ?");
        }
        if (idCliente > 0) {
            sql.append(" AND id_cliente = ?");
        }
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            if (status != null && !status.trim().isEmpty()) {
                stmt.setString(index++, status);
            }
            if (idCliente > 0) {
                stmt.setInt(index++, idCliente);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()){
                    Pedido pedido = new Pedido();
                    pedido.setIdPedido(rs.getInt("id_pedido"));
                    pedido.setIdCliente(rs.getInt("id_cliente"));
                    pedido.setDataHora(LocalDateTime.parse(rs.getString("data_hora")));
                    pedido.setStatus(rs.getString("status"));
                    pedidos.add(pedido);
                }
            }
        }
        return pedidos;
    }
}