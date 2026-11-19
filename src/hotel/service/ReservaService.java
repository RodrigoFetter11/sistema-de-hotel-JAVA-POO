package hotel.service;

import hotel.exception.ReservaInvalidaException;
import hotel.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private final Hotel hotel;

    public ReservaService(Hotel hotel) {
        this.hotel = hotel;
    }

    public void cadastrarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new ReservaInvalidaException("A reserva não pode ser nula.");
        }

        Cliente cliente = reserva.getCliente();
        Quarto quarto = reserva.getQuarto();

        if (cliente == null || quarto == null) {
            throw new ReservaInvalidaException("O cliente e o quarto são obrigatórios para realizar uma reserva.");
        }

        if (!hotel.getClientes().contains(cliente)) {
            throw new ReservaInvalidaException("O cliente informado não está cadastrado no hotel.");
        }

        if (!hotel.getQuartos().contains(quarto)) {
            throw new ReservaInvalidaException("O quarto informado não está cadastrado no hotel.");
        }

        if (reserva.getDataEntrada() == null || reserva.getDataSaida() == null) {
            throw new ReservaInvalidaException("As datas de entrada e saída são obrigatórias.");
        }

        if (!reserva.getDataSaida().isAfter(reserva.getDataEntrada())) {
            throw new ReservaInvalidaException("A data de saída deve ser posterior à data de entrada.");
        }

        for (Reserva reservaExistente : hotel.getReservas()) {

            if (reservaExistente.getStatus() != StatusReserva.CANCELADA
                    && reservaExistente.getQuarto().getNumero() == quarto.getNumero()) {

                if (reserva.getDataEntrada().isBefore(reservaExistente.getDataSaida())
                        && reserva.getDataSaida().isAfter(reservaExistente.getDataEntrada())) {

                    throw new ReservaInvalidaException(
                            "O quarto já possui uma reserva para o período informado."
                    );
                }
            }
        }

        hotel.getReservas().add(reserva);
    }

    public List<Reserva> buscarReservaPorQuarto(int numero) {
        List<Reserva> reservasEncontradas = new ArrayList<>();

        for (Reserva reserva : hotel.getReservas()) {
            if (reserva.getQuarto().getNumero() == numero) {
                reservasEncontradas.add(reserva);
            }
        }
        return reservasEncontradas;
    }

    public void listarReservas() {
        if (hotel.getReservas().isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        for (Reserva reserva : hotel.getReservas()) {
            System.out.println(reserva);
        }
    }

    public List<Reserva> buscarReservaPorCpf(String cpf) {
        List<Reserva> reservasEncontradas = new ArrayList<>();

        for (Reserva reserva : hotel.getReservas()) {
            if (reserva.getCliente().getCpf().equals(cpf)) {
                reservasEncontradas.add(reserva);
            }
        }
        return reservasEncontradas;
    }

    public boolean cancelarReserva(int numeroQuarto, String cpf, LocalDate dataEntrada) {
        Reserva reservaEncontrada = null;

        for (Reserva reserva : hotel.getReservas()) {
            if (reserva.getQuarto().getNumero() == numeroQuarto
                    && reserva.getCliente().getCpf().equals(cpf)
                    && reserva.getDataEntrada().equals(dataEntrada)) {
                reservaEncontrada = reserva;
                break;
            }
        }

        if (reservaEncontrada != null) {
            reservaEncontrada.cancelar();
            return true;
        }

        return false;
    }

    public boolean confirmarReserva(int numeroQuarto, String cpf, LocalDate dataEntrada) {
        Reserva reservaEncontrada = null;

        for (Reserva reserva : hotel.getReservas()) {
            if (reserva.getQuarto().getNumero() == numeroQuarto
                    && reserva.getCliente().getCpf().equals(cpf)
                    && reserva.getDataEntrada().equals(dataEntrada)) {

                reservaEncontrada = reserva;
                break;
            }
        }

        if (reservaEncontrada != null) {
            reservaEncontrada.confirmar();
            return true;
        }

        return false;
    }
}