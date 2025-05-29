package service;

import java.util.Scanner;

public class GerenteInterface {
    public static void menuGerente() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n=== INTERFACE GERENTE ===");
            System.out.println("1. Consultar pedidos (filtros: status, cliente)");
            System.out.println("2. Consultar pratos (filtro: categoria)");
            System.out.println("3. Adicionar prato");
            System.out.println("4. Modificar prato");
            System.out.println("5. Excluir prato");
            System.out.println("6. Adicionar cliente");
            System.out.println("7. Modificar cliente");
            System.out.println("8. Excluir cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Status (ou vazio): ");
                    String status = scanner.nextLine();                    
                    System.out.print("ID do cliente (0 para ignorar): ");
                    int idCli = scanner.nextInt();
                    scanner.nextLine();
                    if (idCli == 0) {
                        GerenteService.consultarPedidos(status, null);
                    } else {
                        GerenteService.consultarPedidos(status, idCli);
                    }
                    break;
                case 2:
                    System.out.print("Categoria (ou vazio para todos): ");
                    String categoria = scanner.nextLine();
                    GerenteService.consultarPratos(categoria);
                    break;
                case 3:
                    GerenteService.adicionarPrato();
                    break;
                case 4:
                    GerenteService.modificarPrato();
                    break;
                case 5:
                    GerenteService.excluirPrato();
                    break;
                case 6:
                    GerenteService.adicionarCliente();
                    break;
                case 7:
                    GerenteService.modificarCliente();
                    break;
                case 8:
                    GerenteService.excluirCliente();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        scanner.close();
    }
    
    public static void main(String[] args) {
        menuGerente();
    }
}