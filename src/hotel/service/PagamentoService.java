package hotel.service;

import hotel.model.*;

public class PagamentoService {

    private final Hotel hotel;

    public PagamentoService(Hotel hotel) {
        this.hotel = hotel;
    }

    public void processarPagamento(Reserva reserva, FormaPagamento formaPagamento) {
        if (reserva == null || !hotel.getReservas().contains(reserva)) {
            throw new IllegalArgumentException("Reserva inválida ou não cadastrada.");
        }

        if (reserva.getPagamento() != null) {
            throw new IllegalStateException("Esta reserva já possui um pagamento.");
        }

        if (reserva.getStatus() != StatusReserva.CONFIRMADA){
            throw new IllegalStateException("Somente reservas confirmadas podem receber pagamentos.");
        }

        Pagamento pagamento = new Pagamento(reserva, formaPagamento);
        reserva.adicionarPagamento(pagamento);
        pagamento.processarPagamento();
    }
}