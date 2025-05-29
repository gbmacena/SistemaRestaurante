package service;

import java.util.Scanner;

public class GerenteInterface {
    
    public static void menuGerente() {
        Scanner scanner = new Scanner(System.in);
        GerenteService service = new GerenteService();
        int opcao;
        do {
            System.out.println("\n=== INTERFACE DO GERENTE ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Excluir Cliente");
            System.out.println("4. Cadastrar Prato");
            System.out.println("5. Modificar Prato");
            System.out.println("6. Excluir Prato");
            System.out.println("7. Consultar Pedidos (com filtros)");
            System.out.println("8. Consultar Cardápio");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    service.cadastrarCliente();
                    break;
                case 2:
                    service.modificarCliente();
                    break;
                case 3:
                    service.excluirCliente();
                    break;
                case 4:
                    service.cadastrarPrato();
                    break;
                case 5:
                    service.modificarPrato();
                    break;
                case 6:
                    service.excluirPrato();
                    break;
                case 7:
                    service.consultarPedidos();
                    break;
                case 8:
                    service.consultarCardapio();
                    break;
                case 0:
                    System.out.println("Voltando para o menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}