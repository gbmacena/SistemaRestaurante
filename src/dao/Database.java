package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:restaurante.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }
    
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
             
            stmt.execute("PRAGMA foreign_keys = ON;");
            
            String sqlCliente = "CREATE TABLE IF NOT EXISTS Cliente (" +
                                "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "nome TEXT NOT NULL, " +
                                "telefone TEXT, " +
                                "email TEXT" +
                                ")";
            stmt.execute(sqlCliente);
            
            String sqlPrato = "CREATE TABLE IF NOT EXISTS Prato (" +
                              "id_prato INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "nome TEXT NOT NULL, " +
                              "descricao TEXT, " +
                              "preco REAL NOT NULL, " +
                              "categoria TEXT NOT NULL" +
                              ")";
            stmt.execute(sqlPrato);
            
            String sqlPedido = "CREATE TABLE IF NOT EXISTS Pedido (" +
                               "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT, " +
                               "id_cliente INTEGER, " +
                               "data_hora TEXT NOT NULL, " +
                               "status TEXT NOT NULL DEFAULT 'PENDENTE', " +
                               "FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente)" +
                               ")";
            stmt.execute(sqlPedido);
            
            String sqlPedidoPrato = "CREATE TABLE IF NOT EXISTS PedidoPrato (" +
                                    "id_pedido INTEGER, " +
                                    "id_prato INTEGER, " +
                                    "quantidade INTEGER NOT NULL, " +
                                    "PRIMARY KEY (id_pedido, id_prato), " +
                                    "FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido), " +
                                    "FOREIGN KEY (id_prato) REFERENCES Prato(id_prato)" +
                                    ")";
            stmt.execute(sqlPedidoPrato);
            
            System.out.println("Banco de dados inicializado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão com o banco fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}