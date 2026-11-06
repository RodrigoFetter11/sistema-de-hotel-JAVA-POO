package hotel.service;

import hotel.model.Cliente;
import hotel.model.Hotel;
import hotel.model.Quarto;
import hotel.model.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private final Hotel hotel;

    public ReservaService (Hotel hotel){
        this.hotel = hotel;
    }

    public void cadastrarReserva(Reserva reserva) {
        if (reserva == null) {
            return;
        }

        Cliente cliente = reserva.getCliente();
        Quarto quarto = reserva.getQuarto();

        if (cliente == null || quarto == null) {
            return;
        }

        if (!hotel.getClientes().contains(cliente) || !hotel.getQuartos().contains(quarto)) {
            return;
        }

        if (reserva.getDataEntrada() == null || reserva.getDataSaida() == null){
            return;
        }

        if (!reserva.getDataSaida().isAfter(reserva.getDataEntrada())) {
            return;
        }

        for (Reserva reservaExistente : hotel.getReservas()){
            if (reservaExistente.getQuarto().getNumero() == quarto.getNumero()){
                if (reserva.getDataEntrada().isBefore(reservaExistente.getDataSaida())
                        && reserva.getDataSaida().isAfter(reservaExistente.getDataEntrada())){
                    return;
                }
            }
        }

        hotel.getReservas().add(reserva);
    }

    public List<Reserva> buscarReservaPorQuarto (int numero){
        List<Reserva> reservasEncontradas = new ArrayList<>();

        for (Reserva reserva : hotel.getReservas()){
            if (reserva.getQuarto().getNumero() == numero){
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

    public List<Reserva> buscarReservaPorCpf (String cpf){
        List<Reserva> reservasEncontradas = new ArrayList<>();

        for (Reserva reserva : hotel.getReservas()){
            if (reserva.getCliente().getCpf().equals(cpf)){
                reservasEncontradas.add(reserva);
            }
        }
        return reservasEncontradas;
    }

    public boolean removerReserva(int numeroQuarto, String cpf, LocalDate dataEntrada){
        Reserva reservaEncontrada = null;

        for (Reserva reserva : hotel.getReservas()) {
            if (reserva.getQuarto().getNumero() == numeroQuarto
                    && reserva.getCliente().getCpf().equals(cpf)
                    && reserva.getDataEntrada().equals(dataEntrada)) {
                        reservaEncontrada = reserva;
                        break;
            }
        }

            if (reservaEncontrada != null){
                hotel.getReservas().remove(reservaEncontrada);
                return true;
            }
            return false;
    }
}
