package service;

import dao.PedidoDAO;
import dao.PedidoPratoDAO;
import model.Pedido;
import model.PedidoPrato;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class GarcomService {

    private PedidoDAO pedidoDAO;
    private PedidoPratoDAO pedidoPratoDAO;
    private Scanner scanner;
    
    public GarcomService() {
        pedidoDAO = new PedidoDAO();
        pedidoPratoDAO = new PedidoPratoDAO();
        scanner = new Scanner(System.in);
    }
    
    public void registrarPedido() {
        try {
            System.out.println("\n--- Registrar Novo Pedido ---");
            System.out.print("ID do Cliente: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine();
            
            Pedido pedido = new Pedido();
            pedido.setIdCliente(idCliente);
            pedido.setDataHora(LocalDateTime.now());
            pedido.setStatus("PENDENTE");
            
            int pedidoId = pedidoDAO.inserirPedido(pedido);
            
            String continuar;
            do {
                System.out.print("ID do Prato: ");
                int idPrato = scanner.nextInt();
                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();
                PedidoPrato item = new PedidoPrato(pedidoId, idPrato, quantidade);
                pedidoPratoDAO.inserirPedidoPrato(item);
                System.out.print("Deseja adicionar outro prato? (s/n): ");
                continuar = scanner.nextLine();
            } while (continuar.equalsIgnoreCase("s"));
            
            System.out.println("Pedido registrado com sucesso! ID do pedido: " + pedidoId);
        } catch (SQLException e) {
            System.out.println("Erro ao registrar pedido: " + e.getMessage());
        }
    }
    
    public void alterarPedido() {
        try {
            System.out.println("\n--- Alterar Pedido ---");
            System.out.print("Digite o ID do pedido: ");
            int pedidoId = 0;
            if (scanner.hasNextInt()) {
                pedidoId = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Valor inválido para o ID do pedido!");
                scanner.nextLine();
                return;
            }
            
            Pedido pedido = pedidoDAO.buscarPorId(pedidoId);
            if (pedido == null) {
                System.out.println("Pedido não encontrado!");
                return;
            }
            System.out.println("Status atual: " + pedido.getStatus());
            System.out.print("Novo status: ");
            String novoStatus = scanner.nextLine();
            pedido.setStatus(novoStatus);
            pedidoDAO.atualizarPedido(pedido);
            System.out.println("Pedido atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar pedido: " + e.getMessage());
        }
    }
    
    public void excluirPedido() {
        try {
            System.out.println("\n--- Excluir Pedido ---");
            System.out.print("Digite o ID do pedido: ");
            int pedidoId = scanner.nextInt();
            scanner.nextLine();
            Pedido pedido = pedidoDAO.buscarPorId(pedidoId);
            if (pedido == null) {
                System.out.println("Pedido não encontrado!");
                return;
            }
            pedidoPratoDAO.excluirPorPedido(pedidoId);
            pedidoDAO.excluirPedido(pedidoId);
            System.out.println("Pedido excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir pedido: " + e.getMessage());
        }
    }
    
    public void listarPedidos() {
        try {
            System.out.println("\n--- Listar Pedidos ---");
            List<Pedido> pedidos = pedidoDAO.listarTodos();
            if (pedidos.isEmpty()) {
                System.out.println("Nenhum pedido encontrado.");
            } else {
                for (Pedido pedido : pedidos) {
                    System.out.println("ID: " + pedido.getIdPedido() +
                                       " | Cliente: " + pedido.getIdCliente() +
                                       " | Status: " + pedido.getStatus() +
                                       " | Data/Hora: " + pedido.getDataHora());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
    }
}