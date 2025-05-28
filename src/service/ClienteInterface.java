package service;
import dao.Database;
import java.sql.*;
import java.sql.Connection;

public class ClienteInterface {

    public static void consultarCardapio() {
        String sql = "SELECT nome, descricao, preco, categoria FROM Prato";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("---- CARDÁPIO ----");
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Descrição: " + rs.getString("descricao"));
                System.out.println("Preço: R$" + rs.getDouble("preco"));
                System.out.println("Categoria: " + rs.getString("categoria"));
                System.out.println("---------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar o cardápio: " + e.getMessage());
        }
    }

    public static void consultarStatusPedido(int idPedido) {
        String sql = "SELECT status FROM Pedido WHERE id_pedido = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Status do Pedido #" + idPedido + ": " + rs.getString("status"));
            } else {
                System.out.println("Pedido não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar status do pedido: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Database.initializeDatabase(); 

        consultarCardapio();

        consultarStatusPedido(1); 
    }
}
