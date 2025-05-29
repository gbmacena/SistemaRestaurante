package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Seeder {
    // Método para limpar a tabela Prato
    public static void limparTabelaPrato() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
             
            int rows = stmt.executeUpdate("DELETE FROM Prato;");
            System.out.println("Tabela Prato limpa com sucesso. Registros removidos: " + rows);
        } catch (SQLException e) {
            System.out.println("Erro ao limpar tabela Prato: " + e.getMessage());
        }
    }
    
    public static void seedPratosAndBebidas() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {

            // Verifica se a tabela Prato já possui registros
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM Prato");
            rs.next();
            int count = rs.getInt("count");
            if (count > 0) {
                System.out.println("Tabela Prato já populada. Seeding ignorado.");
                return;
            }

            // Ou execute uTRUNCATE se preferir limpar e semear novamente
            // stmt.executeUpdate("DELETE FROM Prato");

            // Agora insira os pratos
            stmt.executeUpdate("INSERT INTO Prato (nome, descricao, preco, categoria) VALUES ('Prato 1', 'Descrição', 10.0, 'Principal')");
            stmt.executeUpdate("INSERT INTO Prato(nome, descricao, preco, categoria) VALUES(?, ?, ?, ?)");
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