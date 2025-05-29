package dao;

import model.Prato;
import java.sql.*;

public class PratoDAO {

    public static int inserirPrato(Prato prato) {
        String sql = "INSERT INTO Prato (nome, descricao, preco, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setString(4, prato.getCategoria());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir prato, nenhuma linha afetada.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Falha ao inserir prato, ID não obtido.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir prato: " + e.getMessage());
            return -1;
        }
    }

    public static boolean atualizarPrato(Prato prato) {
        String sql = "UPDATE Prato SET nome = ?, descricao = ?, preco = ?, categoria = ? WHERE id_prato = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setString(4, prato.getCategoria());
            stmt.setInt(5, prato.getIdPrato());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar prato: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirPrato(int id) {
        String sql = "DELETE FROM Prato WHERE id_prato = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao excluir prato: " + e.getMessage());
            return false;
        }
    }
}