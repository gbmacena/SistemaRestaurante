package service;

import dao.PratoDAO;
import dao.ClienteDAO;
import model.Prato;
import model.Cliente;
import java.util.Scanner;

public class GerenteService {

    // Métodos de consulta (suponha já implementados, por exemplo, que executam SELECT com filtros)
    public static void consultarPedidos(String status, Integer idCliente) {
        // Chama métodos no PedidoDAO para consulta e imprime os resultados.
        System.out.println("[CONSULTA PEDIDOS] Status: " + status + ", Cliente: " + idCliente);
    }
    
    public static void consultarPratos(String categoria) {
        // Chama método no PratoDAO para consulta e imprime o cardápio filtrado.
        System.out.println("[CONSULTA PRATOS] Categoria: " + categoria);
    }
    
    // Adicionar prato
    public static void adicionarPrato() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- ADICIONAR PRATO ---");
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
        int id = PratoDAO.inserirPrato(prato);
        if(id > 0)
            System.out.println("Prato adicionado com sucesso! ID: " + id);
        else
            System.out.println("Erro ao adicionar prato.");
    }
    
    // Modificar prato
    public static void modificarPrato() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- MODIFICAR PRATO ---");
        System.out.print("ID do prato: ");
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
        
        Prato pratoAtualizado = new Prato(id, nome, descricao, preco, categoria);
        boolean atualizado = PratoDAO.atualizarPrato(pratoAtualizado);
        if (atualizado) {
            System.out.println("Prato atualizado com sucesso.");
        } else {
            System.out.println("Falha ao atualizar prato.");
        }
    }
    
    // Excluir prato
    public static void excluirPrato() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- EXCLUIR PRATO ---");
        System.out.print("ID do prato: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean excluido = PratoDAO.excluirPrato(id);
        if (excluido) {
            System.out.println("Prato excluído com sucesso.");
        } else {
            System.out.println("Falha ao excluir prato ou prato não encontrado.");
        }
    }
    
    // Adicionar cliente
    public static void adicionarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- ADICIONAR CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Cliente cliente = new Cliente(nome, telefone, email);
        int id = new ClienteDAO().inserirCliente(cliente);
        if (id > 0)
            System.out.println("Cliente adicionado com sucesso! ID: " + id);
        else
            System.out.println("Erro ao adicionar cliente.");
    }
    
    // Modificar cliente
    public static void modificarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- MODIFICAR CLIENTE ---");
        System.out.print("ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();
        
        Cliente clienteAtualizado = new Cliente(id, nome, telefone, email);
        boolean sucesso = new ClienteDAO().atualizarCliente(clienteAtualizado);
        if (sucesso) {
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Erro ao atualizar cliente ou cliente não encontrado.");
        }
    }
    
    // Excluir cliente
    public static void excluirCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- EXCLUIR CLIENTE ---");
        System.out.print("ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean sucesso = new ClienteDAO().excluirCliente(id);
        if (sucesso) {
            System.out.println("Cliente excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir cliente ou cliente não encontrado.");
        }
    }
}