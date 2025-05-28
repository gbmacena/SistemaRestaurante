package dao;

import model.Pedido;
import model.Pedido.StatusPedido;
import util.DateTimeUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public int inserirPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (id_cliente, id_garcom, data_hora, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getIdCliente());
            stmt.setInt(2, pedido.getIdGarcom());
            stmt.setString(3, DateTimeUtil.formatDateTime(pedido.getDataHora()));
            stmt.setString(4, pedido.getStatus().getValor());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir pedido, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Falha ao inserir pedido, ID não obtido.");
                }
            }
        }
    }

    public void atualizarPedido(Pedido pedido) throws SQLException {
        String sql = "UPDATE Pedido SET id_cliente = ?, id_garcom = ?, status = ? WHERE id_pedido = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getIdCliente());
            stmt.setInt(2, pedido.getIdGarcom());
            stmt.setString(3, pedido.getStatus().getValor());
            stmt.setInt(4, pedido.getIdPedido());

            stmt.executeUpdate();
        }
    }

    public void excluirPedido(int idPedido) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        }
    }

    public Pedido buscarPorId(int idPedido) throws SQLException {
        String sql = "SELECT * FROM Pedido WHERE id_pedido = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdGarcom(rs.getInt("id_garcom"));
                pedido.setDataHora(DateTimeUtil.parseDateTime(rs.getString("data_hora")));
                pedido.setStatus(StatusPedido.fromString(rs.getString("status")));
                return pedido;
            }
        }
        return null;
    }

    public List<Pedido> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Pedido ORDER BY data_hora DESC";
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdGarcom(rs.getInt("id_garcom"));
                pedido.setDataHora(DateTimeUtil.parseDateTime(rs.getString("data_hora")));
                pedido.setStatus(StatusPedido.fromString(rs.getString("status")));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    public List<Pedido> buscarPorGarcom(int idGarcom) throws SQLException {
        String sql = "SELECT * FROM Pedido WHERE id_garcom = ? ORDER BY data_hora DESC";
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idGarcom);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdGarcom(rs.getInt("id_garcom"));
                pedido.setDataHora(DateTimeUtil.parseDateTime(rs.getString("data_hora")));
                pedido.setStatus(StatusPedido.fromString(rs.getString("status")));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }
}
