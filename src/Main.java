import dao.Database;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver SQLite carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLite NÃO encontrado!");
            e.printStackTrace();
        }

        Database.initializeDatabase();

        System.out.println("Tabelas criadas (ou já existiam) com sucesso!");

        Database.closeConnection();
    }
}
