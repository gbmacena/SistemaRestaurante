package service;

import java.util.Scanner;

public class GarcomInterface {
    public static void menuGarcom() {
        Scanner scanner = new Scanner(System.in);
        GarcomService service = new GarcomService();
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DO GARÇOM ===");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Alterar Pedido");
            System.out.println("3. Excluir Pedido");
            System.out.println("4. Listar Pedidos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch(opcao) {
                case 1:
                    service.registrarPedido();
                    break;
                case 2:
                    service.alterarPedido();
                    break;
                case 3:
                    service.excluirPedido();
                    break;
                case 4:
                    service.listarPedidos();
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