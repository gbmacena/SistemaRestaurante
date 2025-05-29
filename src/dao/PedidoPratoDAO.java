package dao;

import model.PedidoPrato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoPratoDAO {
    public void inserirPedidoPrato(PedidoPrato pedidoPrato) throws SQLException {
        String sql = "INSERT INTO PedidoPrato (id_pedido, id_prato, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoPrato.getIdPedido());
            stmt.setInt(2, pedidoPrato.getIdPrato());
            stmt.setInt(3, pedidoPrato.getQuantidade());
            stmt.executeUpdate();
        }
    }
    
    public void excluirPorPedido(int idPedido) throws SQLException {
        String sql = "DELETE FROM PedidoPrato WHERE id_pedido = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        }
    }
    
    public List<PedidoPrato> buscarPorPedido(int idPedido) throws SQLException {
        String sql = "SELECT * FROM PedidoPrato WHERE id_pedido = ?";
        List<PedidoPrato> itens = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PedidoPrato item = new PedidoPrato(rs.getInt("id_pedido"), rs.getInt("id_prato"), rs.getInt("quantidade"));
                itens.add(item);
            }
        }
        return itens;
    }
}