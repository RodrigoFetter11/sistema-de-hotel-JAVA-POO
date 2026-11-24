package hotel.service;

import hotel.exception.QuartoInvalidoException;
import hotel.model.Cliente;
import hotel.model.Hotel;
import hotel.model.Quarto;
import hotel.model.Reserva;

import java.math.BigDecimal;
import java.util.Optional;

public class QuartoService {
    private final Hotel hotel;

    public QuartoService (Hotel hotel){
        this.hotel = hotel;
    }


    public void cadastrarQuarto(Quarto quarto) {
        if (quarto == null) {
            throw new QuartoInvalidoException("O quarto não pode ser nulo.");
        }

        if (quarto.getNumero() <= 0) {
            throw new QuartoInvalidoException("O número do quarto deve ser maior que zero.");
        }

        for (Quarto quartoExistente : hotel.getQuartos()) {
            if (quarto.getNumero() == quartoExistente.getNumero()) {
                throw new QuartoInvalidoException("Já existe um quarto cadastrado com esse número.");
            }
        }

        if (quarto.getTipo() == null) {
            throw new QuartoInvalidoException("O tipo do quarto é obrigatório.");
        }

        if (quarto.getPrecoDiaria() == null) {
            throw new QuartoInvalidoException("O preço da diária é obrigatório.");
        }

        if (quarto.getPrecoDiaria().compareTo(BigDecimal.ZERO) <= 0) {
            throw new QuartoInvalidoException("O preço da diária deve ser maior que zero.");
        }

        if (quarto.getCapacidade() <= 0) {
            throw new QuartoInvalidoException("A capacidade do quarto deve ser maior que zero.");
        }

        hotel.getQuartos().add(quarto);
    }

    public Optional<Quarto> buscarQuartoPorNumero (int numero){
        for (Quarto numeroQuarto : hotel.getQuartos()){
            if (numeroQuarto.getNumero() == numero){
                return Optional.of(numeroQuarto);
            }
        }
        return Optional.empty();
    }

    public boolean removerQuarto(int numero) {
        Optional<Quarto> resultado = buscarQuartoPorNumero(numero);

        if (resultado.isEmpty()) {
            return false;
        }

        for (Reserva reservaEncontrada : hotel.getReservas()){
            if (reservaEncontrada.getQuarto() == resultado.get()){
                return false;
            }
        }

        hotel.getQuartos().remove(resultado.get());
        return true;
    }


    public void listarQuarto(){
        if (hotel.getQuartos().isEmpty()){
            System.out.println("Nenhum quarto cadastrado.");
            return;
        }

        for (Quarto quarto : hotel.getQuartos()) {
            System.out.println(quarto);
        }
    }

}
