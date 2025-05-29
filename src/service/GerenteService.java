package service;

import dao.ClienteDAO;
import dao.PratoDAO;
import dao.PedidoDAO;
import model.Cliente;
import model.Prato;
import model.Pedido;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GerenteService {

    private ClienteDAO clienteDAO;
    private PratoDAO pratoDAO;
    private PedidoDAO pedidoDAO;

    public GerenteService() {
        clienteDAO = new ClienteDAO();
        pratoDAO = new PratoDAO();
        pedidoDAO = new PedidoDAO();
    }

    public void cadastrarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Cadastrar Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        
        Cliente cliente = new Cliente(nome, telefone, email);
        try {
            int id = clienteDAO.inserirCliente(cliente);
            System.out.println("Cliente cadastrado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public void modificarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Modificar Cliente ---");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo e-mail: ");
        String email = scanner.nextLine();
        
        Cliente cliente = new Cliente(id, nome, telefone, email);
        try {
            boolean sucesso = clienteDAO.atualizarCliente(cliente);
            if (sucesso) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado ou erro ao atualizar!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluirCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Excluir Cliente ---");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            boolean sucesso = clienteDAO.excluirCliente(id);
            if (sucesso) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado ou erro ao excluir!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    public void cadastrarPrato() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Cadastrar Prato ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        
        Prato prato = new Prato(nome, descricao, preco, categoria);
        try {
            int id = pratoDAO.inserirPrato(prato);
            System.out.println("Prato cadastrado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar prato: " + e.getMessage());
        }
    }

    public void modificarPrato() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Modificar Prato ---");
        System.out.print("Digite o ID do prato: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Novo preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nova categoria: ");
        String categoria = scanner.nextLine();
        
        Prato prato = new Prato(id, nome, descricao, preco, categoria);
        try {
            boolean sucesso = pratoDAO.atualizarPrato(prato);
            if (sucesso) {
                System.out.println("Prato atualizado com sucesso!");
            } else {
                System.out.println("Prato não encontrado ou erro ao atualizar!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar prato: " + e.getMessage());
        }
    }

    public void excluirPrato() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Excluir Prato ---");
        System.out.print("Digite o ID do prato: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            boolean sucesso = pratoDAO.excluirPrato(id);
            if (sucesso) {
                System.out.println("Prato excluído com sucesso!");
            } else {
                System.out.println("Prato não encontrado ou erro ao excluir!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir prato: " + e.getMessage());
        }
    }

    public void consultarPedidos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Consultar Pedidos ---");
        System.out.print("Filtrar por status (deixe vazio para não filtrar): ");
        String status = scanner.nextLine();
        System.out.print("Filtrar por ID do cliente (0 para não filtrar): ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        try {
            List<Pedido> pedidos = pedidoDAO.consultarPedidos(status, idCliente);
            if (pedidos.isEmpty()) {
                System.out.println("Nenhum pedido encontrado com os filtros aplicados.");
            } else {
                for (Pedido pedido : pedidos) {
                    System.out.println("ID: " + pedido.getIdPedido() +
                                       " | Cliente: " + pedido.getIdCliente() +
                                       " | Status: " + pedido.getStatus() +
                                       " | Data/Hora: " + pedido.getDataHora());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar pedidos: " + e.getMessage());
        }
    }

    public void consultarCardapio() {
        System.out.println("\n---- CARDÁPIO ----");
        try {
            List<Prato> pratos = pratoDAO.listarTodos();
            if (pratos.isEmpty()) {
                System.out.println("Nenhum prato cadastrado.");
            } else {
                for (Prato prato : pratos) {
                    System.out.println("ID: " + prato.getIdPrato() +
                                       " | Nome: " + prato.getNome() +
                                       " | Preço: R$" + prato.getPreco());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cardápio: " + e.getMessage());
        }
    }
}