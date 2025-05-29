import dao.Database;
import dao.Seeder;
import service.ClienteInterface;
import service.GarcomInterface;
import service.GerenteInterface;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver SQLite carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLite não encontrado!");
            e.printStackTrace();
            return;
        }
        
        Database.initializeDatabase();
        Seeder.limparTabelaPrato(); 
        Seeder.seedPratosAndBebidas();
        
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE GESTÃO DE PEDIDOS - RESTAURANTE ===");
            System.out.println("1. Interface do Cliente");
            System.out.println("2. Sistema do Garçom");
            System.out.println("3. Interface do Gerente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    ClienteInterface.menuCliente();
                    break;
                case 2:
                    GarcomInterface.menuGarcom();
                    break;
                case 3:
                    GerenteInterface.menuGerente();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
        Database.closeConnection();
    }
}