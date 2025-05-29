import dao.Database;
import dao.GarcomSeeder;
import dao.Seeder;
import service.ClienteInterface;
import service.GarcomService;
import service.ClienteService;
import service.GerenteInterface;

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
        Seeder.limparTabelaPrato();
        GarcomSeeder.seedGarcoms();
        
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n=== SISTEMA RESTAURANTE ===");
            System.out.println("1. Interface do Cliente");
            System.out.println("2. Sistema do Garçom");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Interface do Gerente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    ClienteInterface.consultarCardapio();
                    System.out.print("Digite o ID do pedido para consultar status: ");
                    int idPedido = scanner.nextInt();
                    scanner.nextLine();
                    // método para consultar status
                    ClienteInterface.consultarStatusPedido(idPedido);
                    break;
                case 2:
                    GarcomService garcomService = new GarcomService();
                    garcomService.loginGarcom();
                    break;
                case 3:
                    ClienteService clienteService = new ClienteService();
                    clienteService.cadastrarCliente();
                    break;
                case 4:
                    GerenteInterface.menuGerente();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
        Database.closeConnection();
    }
}