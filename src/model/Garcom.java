package model;

import java.time.LocalDate;

public class Garcom {
    private int idGarcom;
    private String nome;
    private String cpf;
    private String telefone;
    private double salario;
    private LocalDate dataContratacao;

    public Garcom() {}

    public Garcom(String nome, String cpf, String telefone, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.salario = salario;
        this.dataContratacao = LocalDate.now();
    }

    public Garcom(int idGarcom, String nome, String cpf, String telefone, double salario, LocalDate dataContratacao) {
        this.idGarcom = idGarcom;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }

    // Getters e Setters
    public int getIdGarcom() { return idGarcom; }
    public void setIdGarcom(int idGarcom) { this.idGarcom = idGarcom; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    
    public LocalDate getDataContratacao() { return dataContratacao; }
    public void setDataContratacao(LocalDate dataContratacao) { this.dataContratacao = dataContratacao; }

    @Override
    public String toString() {
        return "Garcom{" +
                "idGarcom=" + idGarcom +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                '}';
    }
}