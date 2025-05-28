package service;

import dao.GarcomDAO;
import dao.PedidoDAO;
import dao.PedidoPratoDAO;
import model.Garcom;
import model.Pedido;
import model.Pedido.StatusPedido;
import model.PedidoPrato;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class GarcomService {
    private GarcomDAO garcomDAO;
    private PedidoDAO pedidoDAO;
    private PedidoPratoDAO pedidoPratoDAO;
    private Scanner scanner;
    private Garcom garcomLogado;

    public GarcomService() {
        this.garcomDAO = new GarcomDAO();
        this.pedidoDAO = new PedidoDAO();
        this.pedidoPratoDAO = new PedidoPratoDAO();
        this.scanner = new Scanner(System.in);
    }

    public void loginGarcom() {
        try {
            System.out.println("\n=== LOGIN GARÇOM ===");
            System.out.print("Digite seu ID: ");
            int idGarcom = scanner.nextInt();
            scanner.nextLine();

            garcomLogado = garcomDAO.buscarPorId(idGarcom);
            if (garcomLogado == null) {
                System.out.println("Garçom não encontrado!");
                return;
            }

            System.out.println("Bem-vindo, " + garcomLogado.getNome() + "!");
            menuGarcom();

        } catch (SQLException e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }
    }

    public void menuGarcom() {
        if (garcomLogado == null) {
            System.out.println("Você precisa fazer login primeiro!");
            return;
        }

        int opcao;
        do {
            System.out.println("\n=== SISTEMA DO GARÇOM - " + garcomLogado.getNome() + " ===");
            System.out.println("1. Registrar novo pedido");
            System.out.println("2. Alterar pedido existente");
            System.out.println("3. Excluir pedido");
            System.out.println("4. Listar todos os pedidos");
            System.out.println("5. Listar meus pedidos");
            System.out.println("6. Buscar pedido por ID");
            System.out.println("7. Gerenciar garçons");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarPedido();
                    break;
                case 2:
                    alterarPedido();
                    break;
                case 3:
                    excluirPedido();
                    break;
                case 4:
                    listarPedidos();
                    break;
                case 5:
                    listarMeusPedidos();
                    break;
                case 6:
                    buscarPedido();
                    break;
                case 7:
                    menuGerenciarGarcoms();
                    break;
                case 0:
                    System.out.println("Logout realizado com sucesso!");
                    garcomLogado = null;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void registrarPedido() {
        try {
            System.out.println("\n--- REGISTRAR NOVO PEDIDO ---");
            
            System.out.print("ID do Cliente: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = new Pedido(idCliente, garcomLogado.getIdGarcom(), StatusPedido.PENDENTE);
            int idPedido = pedidoDAO.inserirPedido(pedido);
            pedido.setIdPedido(idPedido);

            System.out.println("Pedido #" + idPedido + " criado com sucesso pelo garçom " + garcomLogado.getNome() + "!");
            
            adicionarPratosAoPedido(idPedido);

        } catch (SQLException e) {
            System.out.println("Erro ao registrar pedido: " + e.getMessage());
        }
    }

    private void adicionarPratosAoPedido(int idPedido) {
        try {
            String continuar;
            do {
                System.out.print("ID do Prato: ");
                int idPrato = scanner.nextInt();
                
                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                PedidoPrato item = new PedidoPrato(idPedido, idPrato, quantidade);
                pedidoPratoDAO.inserirPedidoPrato(item);
                
                System.out.println("Item adicionado ao pedido!");
                
                System.out.print("Deseja adicionar outro prato? (s/n): ");
                continuar = scanner.nextLine();
                
            } while (continuar.equalsIgnoreCase("s"));
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar pratos: " + e.getMessage());
        }
    }

    public void alterarPedido() {
        try {
            System.out.println("\n--- ALTERAR PEDIDO ---");
            
            System.out.print("ID do Pedido: ");
            int idPedido = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = pedidoDAO.buscarPorId(idPedido);
            if (pedido == null) {
                System.out.println("Pedido não encontrado!");
                return;
            }

            System.out.println("Pedido atual: " + pedido);
            
            System.out.println("Novo status:");
            System.out.println("1. PENDENTE");
            System.out.println("2. EM_PREPARO");
            System.out.println("3. ENTREGUE");
            System.out.println("4. CANCELADO");
            System.out.print("Escolha o novo status: ");
            
            int opcaoStatus = scanner.nextInt();
            scanner.nextLine();

            StatusPedido novoStatus;
            switch (opcaoStatus) {
                case 1: novoStatus = StatusPedido.PENDENTE; break;
                case 2: novoStatus = StatusPedido.EM_PREPARO; break;
                case 3: novoStatus = StatusPedido.ENTREGUE; break;
                case 4: novoStatus = StatusPedido.CANCELADO; break;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }

            pedido.setStatus(novoStatus);
            pedidoDAO.atualizarPedido(pedido);
            
            System.out.println("Pedido alterado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao alterar pedido: " + e.getMessage());
        }
    }

    public void excluirPedido() {
        try {
            System.out.println("\n--- EXCLUIR PEDIDO ---");
            
            System.out.print("ID do Pedido: ");
            int idPedido = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = pedidoDAO.buscarPorId(idPedido);
            if (pedido == null) {
                System.out.println("Pedido não encontrado!");
                return;
            }

            System.out.println("Pedido encontrado: " + pedido);
            System.out.print("Confirma a exclusão? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                pedidoPratoDAO.excluirPorPedido(idPedido);
                pedidoDAO.excluirPedido(idPedido);
                
                System.out.println("Pedido excluído com sucesso!");
            } else {
                System.out.println("Exclusão cancelada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir pedido: " + e.getMessage());
        }
    }

    public void listarPedidos() {
        try {
            System.out.println("\n--- LISTA DE TODOS OS PEDIDOS ---");
            
            List<Pedido> pedidos = pedidoDAO.listarTodos();
            
            if (pedidos.isEmpty()) {
                System.out.println("Nenhum pedido encontrado.");
                return;
            }

            for (Pedido pedido : pedidos) {
                System.out.println("ID: " + pedido.getIdPedido() + 
                                 " | Cliente: " + pedido.getIdCliente() + 
                                 " | Garçom: " + pedido.getIdGarcom() +
                                 " | Status: " + pedido.getStatus() + 
                                 " | Data/Hora: " + pedido.getDataHora());
                
                List<PedidoPrato> itens = pedidoPratoDAO.buscarPorPedido(pedido.getIdPedido());
                for (PedidoPrato item : itens) {
                    System.out.println("  - Prato ID: " + item.getIdPrato() + 
                                     " | Quantidade: " + item.getQuantidade());
                }
                System.out.println("---");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
    }

    public void listarMeusPedidos() {
        try {
            System.out.println("\n--- MEUS PEDIDOS - " + garcomLogado.getNome() + " ---");
            
            List<Pedido> pedidos = pedidoDAO.buscarPorGarcom(garcomLogado.getIdGarcom());
            
            if (pedidos.isEmpty()) {
                System.out.println("Você não possui pedidos registrados.");
                return;
            }

            for (Pedido pedido : pedidos) {
                System.out.println("ID: " + pedido.getIdPedido() + 
                                 " | Cliente: " + pedido.getIdCliente() + 
                                 " | Status: " + pedido.getStatus() + 
                                 " | Data/Hora: " + pedido.getDataHora());
                
                List<PedidoPrato> itens = pedidoPratoDAO.buscarPorPedido(pedido.getIdPedido());
                for (PedidoPrato item : itens) {
                    System.out.println("  - Prato ID: " + item.getIdPrato() + 
                                     " | Quantidade: " + item.getQuantidade());
                }
                System.out.println("---");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar seus pedidos: " + e.getMessage());
        }
    }

    public void buscarPedido() {
        try {
            System.out.println("\n--- BUSCAR PEDIDO ---");
            
            System.out.print("ID do Pedido: ");
            int idPedido = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = pedidoDAO.buscarPorId(idPedido);
            if (pedido == null) {
                System.out.println("Pedido não encontrado!");
                return;
            }

            System.out.println("Pedido encontrado:");
            System.out.println(pedido);
            
            List<PedidoPrato> itens = pedidoPratoDAO.buscarPorPedido(idPedido);
            System.out.println("Itens do pedido:");
            for (PedidoPrato item : itens) {
                System.out.println("- Prato ID: " + item.getIdPrato() + 
                                 " | Quantidade: " + item.getQuantidade());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pedido: " + e.getMessage());
        }
    }

    public void menuGerenciarGarcoms() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR GARÇONS ===");
            System.out.println("1. Listar garçons");
            System.out.println("2. Cadastrar novo garçom");
            System.out.println("3. Atualizar garçom");
            System.out.println("4. Excluir garçom");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarGarcoms();
                    break;
                case 2:
                    cadastrarGarcom();
                    break;
                case 3:
                    atualizarGarcom();
                    break;
                case 4:
                    excluirGarcom();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarGarcoms() {
        try {
            List<Garcom> garcoms = garcomDAO.listarTodos();
            
            if (garcoms.isEmpty()) {
                System.out.println("Nenhum garçom cadastrado.");
                return;
            }

            System.out.println("\n--- LISTA DE GARÇONS ---");
            for (Garcom garcom : garcoms) {
                System.out.println("ID: " + garcom.getIdGarcom() + 
                                 " | Nome: " + garcom.getNome() + 
                                 " | CPF: " + garcom.getCpf() + 
                                 " | Salário: R$ " + garcom.getSalario());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar garçons: " + e.getMessage());
        }
    }

    private void cadastrarGarcom() {
        try {
            System.out.println("\n--- CADASTRAR NOVO GARÇOM ---");
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            
            System.out.print("Salário: ");
            double salario = scanner.nextDouble();
            scanner.nextLine();

            Garcom novoGarcom = new Garcom(nome, cpf, telefone, salario);
            int id = garcomDAO.inserirGarcom(novoGarcom);
            
            System.out.println("Garçom cadastrado com sucesso! ID: " + id);

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar garçom: " + e.getMessage());
        }
    }

    private void atualizarGarcom() {
        try {
            System.out.println("\n--- ATUALIZAR GARÇOM ---");
            
            System.out.print("ID do garçom: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Garcom garcom = garcomDAO.buscarPorId(id);
            if (garcom == null) {
                System.out.println("Garçom não encontrado!");
                return;
            }

            System.out.println("Dados atuais: " + garcom);
            
            System.out.print("Novo nome (atual: " + garcom.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.trim().isEmpty()) {
                garcom.setNome(nome);
            }
            
            System.out.print("Novo telefone (atual: " + garcom.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.trim().isEmpty()) {
                garcom.setTelefone(telefone);
            }
            
            System.out.print("Novo salário (atual: " + garcom.getSalario() + "): ");
            String salarioStr = scanner.nextLine();
            if (!salarioStr.trim().isEmpty()) {
                garcom.setSalario(Double.parseDouble(salarioStr));
            }

            garcomDAO.atualizarGarcom(garcom);
            System.out.println("Garçom atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar garçom: " + e.getMessage());
        }
    }

    private void excluirGarcom() {
        try {
            System.out.println("\n--- EXCLUIR GARÇOM ---");
            
            System.out.print("ID do garçom: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Garcom garcom = garcomDAO.buscarPorId(id);
            if (garcom == null) {
                System.out.println("Garçom não encontrado!");
                return;
            }

            System.out.println("Garçom: " + garcom);
            System.out.print("Confirma a exclusão? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                garcomDAO.excluirGarcom(id);
                System.out.println("Garçom excluído com sucesso!");
            } else {
                System.out.println("Exclusão cancelada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir garçom: " + e.getMessage());
        }
    }
}