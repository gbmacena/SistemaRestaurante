package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:restaurante.db";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initializeDatabase() {
        String createClienteTable = """
            CREATE TABLE IF NOT EXISTS Cliente (
                id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                telefone TEXT,
                email TEXT
            );
        """;

        String createPratoTable = """
            CREATE TABLE IF NOT EXISTS Prato (
                id_prato INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                descricao TEXT,
                preco REAL NOT NULL,
                categoria TEXT NOT NULL
            );
        """;

        String createPedidoTable = """
            CREATE TABLE IF NOT EXISTS Pedido (
                id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,
                id_cliente INTEGER,
                data_hora TEXT,
                status TEXT NOT NULL,
                FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
            );
        """;

        String createPedidoPratoTable = """
            CREATE TABLE IF NOT EXISTS PedidoPrato (
                id_pedido INTEGER,
                id_prato INTEGER,
                quantidade INTEGER NOT NULL,
                PRIMARY KEY (id_pedido, id_prato),
                FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
                FOREIGN KEY (id_prato) REFERENCES Prato(id_prato)
            );
        """;

        try (var conn = getConnection();
             var stmt = conn.createStatement()) {
            stmt.execute(createClienteTable);
            stmt.execute(createPratoTable);
            stmt.execute(createPedidoTable);
            stmt.execute(createPedidoPratoTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
