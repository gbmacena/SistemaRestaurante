package service;

import dao.ClienteDAO;
import java.sql.SQLException;
import java.util.Scanner;
import model.Cliente;

public class ClienteService {
    private ClienteDAO clienteDAO;
    
    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }
    
    public void cadastrarCliente() {
        // ... implementação já existente
    }
    
    public void atualizarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- ATUALIZAR CLIENTE ---");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();
        
        Cliente clienteAtualizado = new Cliente(id, nome, telefone, email);
        boolean sucesso = clienteDAO.atualizarCliente(clienteAtualizado);
        if (sucesso) {
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Falha ao atualizar cliente ou cliente não encontrado.");
        }
    }
    
    public void excluirCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- EXCLUIR CLIENTE ---");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        boolean sucesso = clienteDAO.excluirCliente(id);
        if (sucesso) {
            System.out.println("Cliente excluído com sucesso.");
        } else {
            System.out.println("Falha ao excluir cliente ou cliente não encontrado.");
        }
    }
}