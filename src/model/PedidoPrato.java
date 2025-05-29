package model;

public class PedidoPrato {
    private int idPedido;
    private int idPrato;
    private int quantidade;

    public PedidoPrato() {}

    public PedidoPrato(int idPedido, int idPrato, int quantidade) {
        this.idPedido = idPedido;
        this.idPrato = idPrato;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    
    public int getIdPrato() { return idPrato; }
    public void setIdPrato(int idPrato) { this.idPrato = idPrato; }
    
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "PedidoPrato{" +
                "idPedido=" + idPedido +
                ", idPrato=" + idPrato +
                ", quantidade=" + quantidade +
                '}';
    }
}