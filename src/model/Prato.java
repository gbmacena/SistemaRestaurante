package model;

public class Prato {
    private int idPrato;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;

    public Prato(String nome, String descricao, double preco, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }
    
    public Prato(int idPrato, String nome, String descricao, double preco, String categoria) {
        this.idPrato = idPrato;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }
    
    // Getters e setters
    public int getIdPrato() { return idPrato; }
    public void setIdPrato(int idPrato) { this.idPrato = idPrato; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    
    @Override
    public String toString() {
        return "Prato{" +
               "idPrato=" + idPrato +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", preco=" + preco +
               ", categoria='" + categoria + '\'' +
               '}';
    }
}