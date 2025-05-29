package service;

import dao.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteInterface {

    public static void menuCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n=== INTERFACE DO CLIENTE ===");
            System.out.println("1. Consultar Cardápio");
            System.out.println("2. Consultar Status do Pedido");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    consultarCardapio();
                    break;
                case 2:
                    System.out.print("Digite o ID do pedido: ");
                    int idPedido = scanner.nextInt();
                    scanner.nextLine();
                    consultarStatusPedido(idPedido);
                    break;
                case 0:
                    System.out.println("Voltando para o menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void consultarCardapio() {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT nome, descricao, preco, categoria FROM Prato");
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n---- CARDÁPIO ----");
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Descrição: " + rs.getString("descricao"));
                System.out.println("Preço: R$" + rs.getDouble("preco"));
                System.out.println("Categoria: " + rs.getString("categoria"));
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cardápio: " + e.getMessage());
        }
    }

    public static void consultarStatusPedido(int idPedido) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT status FROM Pedido WHERE id_pedido = ?")) {

            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("\nStatus do Pedido #" + idPedido + ": " + rs.getString("status"));
            } else {
                System.out.println("\nPedido não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar status do pedido: " + e.getMessage());
        }
    }
}