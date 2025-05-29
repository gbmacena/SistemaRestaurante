package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Seeder {
    
    public static void limparTabelaPrato() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
             
            int rows = stmt.executeUpdate("DELETE FROM Prato;");
            System.out.println("Tabela Prato limpa com sucesso. Registros removidos: " + rows);
        } catch (SQLException e) {
            System.out.println("Erro ao limpar a tabela Prato: " + e.getMessage());
        }
    }
    
    public static void seedPratosAndBebidas() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
             
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM Prato;");
            rs.next();
            int count = rs.getInt("count");
            if (count > 0) {
                System.out.println("Tabela Prato já populada. Seeding ignorado.");
                return;
            }
            
            stmt.executeUpdate("INSERT INTO Prato (nome, descricao, preco, categoria) VALUES " +
                               "('Pizza Margherita', 'Pizza de mussarela com manjericão', 25.0, 'Principal')");
            
            stmt.executeUpdate("INSERT INTO Prato (nome, descricao, preco, categoria) VALUES " +
                               "('Salada Caesar', 'Salada com molho Caesar e croutons', 15.0, 'Entrada')");
            
            stmt.executeUpdate("INSERT INTO Prato (nome, descricao, preco, categoria) VALUES " +
                               "('Burger Clássico', 'Hambúrguer com queijo, alface, tomate e molho especial', 20.0, 'Principal')");
            
            stmt.executeUpdate("INSERT INTO Prato (nome, descricao, preco, categoria) VALUES " +
                               "('Refrigerante Cola', 'Lata de refrigerante cola', 5.0, 'Bebida')");
            
            stmt.executeUpdate("INSERT INTO Prato (nome, descricao, preco, categoria) VALUES " +
                               "('Suco de Laranja', 'Copo de suco natural de laranja', 7.0, 'Bebida')");
            
            System.out.println("Seeder executado: Pratos e Bebidas inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro no seeder: " + e.getMessage());
        }
    }
}