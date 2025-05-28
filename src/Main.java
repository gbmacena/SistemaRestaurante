import dao.Database;
import dao.Seeder;
import service.ClienteInterface;

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

         Seeder.seedPratosAndBebidas();

        ClienteInterface.consultarCardapio();

        Database.closeConnection();
    }
}
