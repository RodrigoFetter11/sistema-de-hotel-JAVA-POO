package hotel.model;

import java.math.BigDecimal;

public class Pagamento {
    private Reserva reserva;
    private BigDecimal valor;
    private FormaPagamento formaPagamento;
    private StatusPagamento status;

    public Pagamento(Reserva reserva, FormaPagamento formaPagamento) {
        if (reserva == null){
            throw new IllegalArgumentException("A reserva é obrigatória");
        }

        if (formaPagamento == null){
            throw new IllegalArgumentException("A forma de pagamento é obrigatória");
        }

        BigDecimal valorCalculado = reserva.calcularValorTotal();

        if (valorCalculado.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor incorreto");
        }

        this.reserva = reserva;
        this.valor = valorCalculado;
        this.formaPagamento = formaPagamento;
        this.status = StatusPagamento.PENDENTE;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void processarPagamento() {
        if (status != StatusPagamento.PENDENTE) {
            throw new IllegalStateException("O pagamento não pode ser processado.");
        }

        formaPagamento.pagar(valor);
        status = StatusPagamento.APROVADO;
    }
}
