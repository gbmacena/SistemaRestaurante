import dao.Database;
import dao.GarcomSeeder;
import dao.Seeder;
import service.ClienteInterface;
import service.GarcomService;

import java.util.Scanner;

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
        GarcomSeeder.seedGarcoms();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== SISTEMA RESTAURANTE ===");
            System.out.println("1. Interface do Cliente");
            System.out.println("2. Sistema do Garçom");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    ClienteInterface.consultarCardapio();
                    System.out.print("Digite o ID do pedido para consultar status: ");
                    int idPedido = scanner.nextInt();
                    ClienteInterface.consultarStatusPedido(idPedido);
                    break;
                case 2:
                    GarcomService garcomService = new GarcomService();
                    garcomService.loginGarcom();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
        Database.closeConnection();
    }
}
