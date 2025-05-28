package dao;

import model.Garcom;

import java.sql.SQLException;
import java.time.LocalDate;

public class GarcomSeeder {
    private static GarcomDAO garcomDAO = new GarcomDAO();

    public static void seedGarcoms() {
        try {
            if (!garcomDAO.listarTodos().isEmpty()) {
                System.out.println("Garçons já existem no banco.");
                return;
            }

            Garcom garcom1 = new Garcom("João Silva", "123.456.789-01", "(11) 99999-1111", 2500.00);
            garcom1.setDataContratacao(LocalDate.of(2023, 1, 15));

            Garcom garcom2 = new Garcom("Maria Santos", "987.654.321-02", "(11) 99999-2222", 2700.00);
            garcom2.setDataContratacao(LocalDate.of(2023, 3, 10));

            Garcom garcom3 = new Garcom("Pedro Oliveira", "456.789.123-03", "(11) 99999-3333", 2600.00);
            garcom3.setDataContratacao(LocalDate.of(2023, 6, 5));

            garcomDAO.inserirGarcom(garcom1);
            garcomDAO.inserirGarcom(garcom2);
            garcomDAO.inserirGarcom(garcom3);

            System.out.println("Garçons de exemplo inseridos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir garçons de exemplo: " + e.getMessage());
        }
    }
}