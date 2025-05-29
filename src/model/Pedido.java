package model;

import java.time.LocalDateTime;

public class Pedido {
    private int idPedido;
    private int idCliente;
    private LocalDateTime dataHora;
    private String status;
    
    public Pedido() {}
    
    public Pedido(int idCliente, String status) {
        this.idCliente = idCliente;
        this.dataHora = LocalDateTime.now();
        this.status = status;
    }
    
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}