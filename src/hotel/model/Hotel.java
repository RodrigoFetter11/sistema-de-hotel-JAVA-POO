package hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final List<Cliente> clientes;
    private final List<Funcionario> funcionarios;
    private final List<Quarto> quartos;
    private final List<Reserva> reservas;

    public Hotel() {
        this.clientes = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.quartos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
