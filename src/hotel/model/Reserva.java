package hotel.model;

import java.time.LocalDate;

public class Reserva {

    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private StatusReserva status;

    public Reserva(Cliente cliente,
                   Quarto quarto,
                   LocalDate dataEntrada,
                   LocalDate dataSaida) {

        this.cliente = cliente;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.status = StatusReserva.PENDENTE;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void confirmar() {
        if (this.status != StatusReserva.PENDENTE) {
            throw new IllegalStateException(
                    "Somente reservas pendentes podem ser confirmadas."
            );
        }

        this.status = StatusReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (this.status == StatusReserva.CANCELADA) {
            throw new IllegalStateException(
                    "A reserva já está cancelada."
            );
        }

        this.status = StatusReserva.CANCELADA;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente.getNome() +
                ", quarto=" + quarto.getNumero() +
                ", entrada=" + dataEntrada +
                ", saida=" + dataSaida +
                ", status=" + status +
                '}';
    }
}
