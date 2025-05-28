package model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private int idPedido;
    private int idCliente;
    private int idGarcom;
    private LocalDateTime dataHora;
    private StatusPedido status;
    private List<PedidoPrato> itens;

    public enum StatusPedido {
        PENDENTE("PENDENTE"),
        EM_PREPARO("EM_PREPARO"),
        ENTREGUE("ENTREGUE"),
        CANCELADO("CANCELADO");

        private final String valor;

        StatusPedido(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }

        public static StatusPedido fromString(String texto) {
            for (StatusPedido status : StatusPedido.values()) {
                if (status.valor.equalsIgnoreCase(texto)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Status não encontrado: " + texto);
        }

        @Override
        public String toString() {
            return valor;
        }
    }

    public Pedido() {}

    public Pedido(int idCliente, int idGarcom, StatusPedido status) {
        this.idCliente = idCliente;
        this.idGarcom = idGarcom;
        this.dataHora = LocalDateTime.now();
        this.status = status;
    }

    // Getters e Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    
    public int getIdGarcom() { return idGarcom; }
    public void setIdGarcom(int idGarcom) { this.idGarcom = idGarcom; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    
    public List<PedidoPrato> getItens() { return itens; }
    public void setItens(List<PedidoPrato> itens) { this.itens = itens; }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", idGarcom=" + idGarcom +
                ", dataHora=" + dataHora +
                ", status=" + status +
                '}';
    }
}