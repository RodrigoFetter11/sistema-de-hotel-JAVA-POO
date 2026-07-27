package hotel.model;

import java.time.LocalDate;

public class Reserva {

    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Reserva(Cliente cliente,
                   Quarto quarto,
                   LocalDate dataEntrada,
                   LocalDate dataSaida) {

        this.cliente = cliente;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
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

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente.getNome() +
                ", quarto=" + quarto.getNumero() +
                ", entrada=" + dataEntrada +
                ", saida=" + dataSaida +
                '}';
    }
}
