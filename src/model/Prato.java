package model;

public class Prato {
    private int idPrato;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    
    public Prato() {}
    
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
    
    public int getIdPrato() { return idPrato; }
    public void setIdPrato(int idPrato) { this.idPrato = idPrato; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}