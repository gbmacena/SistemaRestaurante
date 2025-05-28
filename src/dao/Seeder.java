package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Seeder {
    public static void seedPratosAndBebidas() {
        String sql = "INSERT INTO Prato(nome, descricao, preco, categoria) VALUES(?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Pizza Margherita");
            stmt.setString(2, "Pizza de mussarela com manjericão");
            stmt.setDouble(3, 25.0);
            stmt.setString(4, "Principal");
            stmt.executeUpdate();

            stmt.setString(1, "Salada Caesar");
            stmt.setString(2, "Salada com molho Caesar e croutons");
            stmt.setDouble(3, 15.0);
            stmt.setString(4, "Entrada");
            stmt.executeUpdate();

            stmt.setString(1, "Burger Clássico");
            stmt.setString(2, "Hambúrguer com queijo, alface, tomate e molho especial");
            stmt.setDouble(3, 20.0);
            stmt.setString(4, "Principal");
            stmt.executeUpdate();

            stmt.setString(1, "Refrigerante Cola");
            stmt.setString(2, "Lata de refrigerante cola");
            stmt.setDouble(3, 5.0);
            stmt.setString(4, "Bebida");
            stmt.executeUpdate();
            
            stmt.setString(1, "Suco de Laranja");
            stmt.setString(2, "Copo de suco natural de laranja");
            stmt.setDouble(3, 7.0);
            stmt.setString(4, "Bebida");
            stmt.executeUpdate();

            System.out.println("Seeder executado: Pratos e Bebidas inseridos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro no seeder: " + e.getMessage());
        }
    }
}