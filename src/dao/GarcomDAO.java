package dao;

import model.Garcom;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GarcomDAO {

    public int inserirGarcom(Garcom garcom) throws SQLException {
        String sql = "INSERT INTO Garcom (nome, cpf, telefone, salario, data_contratacao) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, garcom.getNome());
            stmt.setString(2, garcom.getCpf());
            stmt.setString(3, garcom.getTelefone());
            stmt.setDouble(4, garcom.getSalario());
            stmt.setString(5, garcom.getDataContratacao().toString());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir garçom, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Falha ao inserir garçom, ID não obtido.");
                }
            }
        }
    }

    public void atualizarGarcom(Garcom garcom) throws SQLException {
        String sql = "UPDATE Garcom SET nome = ?, cpf = ?, telefone = ?, salario = ? WHERE id_garcom = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, garcom.getNome());
            stmt.setString(2, garcom.getCpf());
            stmt.setString(3, garcom.getTelefone());
            stmt.setDouble(4, garcom.getSalario());
            stmt.setInt(5, garcom.getIdGarcom());
            
            stmt.executeUpdate();
        }
    }

    public void excluirGarcom(int idGarcom) throws SQLException {
        String sql = "DELETE FROM Garcom WHERE id_garcom = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idGarcom);
            stmt.executeUpdate();
        }
    }

    public Garcom buscarPorId(int idGarcom) throws SQLException {
        String sql = "SELECT * FROM Garcom WHERE id_garcom = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idGarcom);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Garcom garcom = new Garcom();
                garcom.setIdGarcom(rs.getInt("id_garcom"));
                garcom.setNome(rs.getString("nome"));
                garcom.setCpf(rs.getString("cpf"));
                garcom.setTelefone(rs.getString("telefone"));
                garcom.setSalario(rs.getDouble("salario"));
                garcom.setDataContratacao(LocalDate.parse(rs.getString("data_contratacao")));
                return garcom;
            }
        }
        return null;
    }

    public Garcom buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM Garcom WHERE cpf = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Garcom garcom = new Garcom();
                garcom.setIdGarcom(rs.getInt("id_garcom"));
                garcom.setNome(rs.getString("nome"));
                garcom.setCpf(rs.getString("cpf"));
                garcom.setTelefone(rs.getString("telefone"));
                garcom.setSalario(rs.getDouble("salario"));
                garcom.setDataContratacao(LocalDate.parse(rs.getString("data_contratacao")));
                return garcom;
            }
        }
        return null;
    }

    public List<Garcom> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Garcom ORDER BY nome";
        List<Garcom> garcoms = new ArrayList<>();
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Garcom garcom = new Garcom();
                garcom.setIdGarcom(rs.getInt("id_garcom"));
                garcom.setNome(rs.getString("nome"));
                garcom.setCpf(rs.getString("cpf"));
                garcom.setTelefone(rs.getString("telefone"));
                garcom.setSalario(rs.getDouble("salario"));
                garcom.setDataContratacao(LocalDate.parse(rs.getString("data_contratacao")));
                garcoms.add(garcom);
            }
        }
        return garcoms;
    }
}