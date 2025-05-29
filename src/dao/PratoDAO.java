package dao;

import model.Prato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoDAO {

    public int inserirPrato(Prato prato) throws SQLException {
        String sql = "INSERT INTO Prato (nome, descricao, preco, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setString(4, prato.getCategoria());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Falha ao inserir prato.");
            }
            try (ResultSet keys = stmt.getGeneratedKeys()){
                if(keys.next()){
                    return keys.getInt(1);
                } else {
                    throw new SQLException("ID do prato não obtido.");
                }
            }
        }
    }
    
    public boolean atualizarPrato(Prato prato) throws SQLException {
        String sql = "UPDATE Prato SET nome = ?, descricao = ?, preco = ?, categoria = ? WHERE id_prato = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setString(4, prato.getCategoria());
            stmt.setInt(5, prato.getIdPrato());
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluirPrato(int id) throws SQLException {
        String sql = "DELETE FROM Prato WHERE id_prato = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<Prato> listarTodos() throws SQLException {
        List<Prato> pratos = new ArrayList<>();
        String sql = "SELECT * FROM Prato";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                Prato prato = new Prato();
                prato.setIdPrato(rs.getInt("id_prato"));
                prato.setNome(rs.getString("nome"));
                prato.setDescricao(rs.getString("descricao"));
                prato.setPreco(rs.getDouble("preco"));
                prato.setCategoria(rs.getString("categoria"));
                pratos.add(prato);
            }
        }
        return pratos;
    }
}