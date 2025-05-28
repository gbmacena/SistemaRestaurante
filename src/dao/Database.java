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

            String createClienteTable = """
                CREATE TABLE IF NOT EXISTS Cliente (
                    id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    telefone TEXT,
                    email TEXT
                )
            """;

            String createGarcomTable = """
                CREATE TABLE IF NOT EXISTS Garcom (
                    id_garcom INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    cpf TEXT UNIQUE NOT NULL,
                    telefone TEXT,
                    salario REAL DEFAULT 0.0,
                    data_contratacao TEXT NOT NULL
                )
            """;

            String createPratoTable = """
                CREATE TABLE IF NOT EXISTS Prato (
                    id_prato INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    descricao TEXT,
                    preco REAL NOT NULL,
                    categoria TEXT NOT NULL
                )
            """;

            String createPedidoTable = """
                CREATE TABLE IF NOT EXISTS Pedido (
                    id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_cliente INTEGER NOT NULL,
                    id_garcom INTEGER NOT NULL,
                    data_hora TEXT NOT NULL,
                    status TEXT NOT NULL DEFAULT 'PENDENTE',
                    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
                    FOREIGN KEY (id_garcom) REFERENCES Garcom(id_garcom)
                )
            """;

            String createPedidoPratoTable = """
                CREATE TABLE IF NOT EXISTS PedidoPrato (
                    id_pedido INTEGER,
                    id_prato INTEGER,
                    quantidade INTEGER NOT NULL,
                    PRIMARY KEY (id_pedido, id_prato),
                    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
                    FOREIGN KEY (id_prato) REFERENCES Prato(id_prato)
                )
            """;

            stmt.execute(createClienteTable);
            stmt.execute(createGarcomTable);
            stmt.execute(createPratoTable);
            stmt.execute(createPedidoTable);
            stmt.execute(createPedidoPratoTable);

            System.out.println("Banco de dados inicializado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inicializar banco de dados: " + e.getMessage());
            e.printStackTrace();
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
