package hotel.service;

import hotel.model.Cliente;
import hotel.model.Hotel;
import hotel.model.Quarto;

import java.util.Optional;

public class QuartoService {
    private final Hotel hotel;

    public QuartoService (Hotel hotel){
        this.hotel = hotel;
    }


    public void cadastrarQuarto (Quarto quarto){
        if (quarto == null){
            return;
        }

        if (quarto.getNumero() <= 0){
            return;
        }

        for (Quarto quartoExistente : hotel.getQuartos()){
            if (quarto.getNumero() == quartoExistente.getNumero()){
                return;
            }
        }

        if (quarto.getTipo() == null || quarto.getTipo().isBlank()){
            return;
        }

        if (quarto.getPrecoDiaria() <= 0){
            return;
        }

        if (quarto.getCapacidade() <= 0){
            return;
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


    public boolean removerQuarto (int numero){
        Optional<Quarto> resultado = buscarQuartoPorNumero(numero);

        if (resultado.isPresent()){
            hotel.getQuartos().remove(resultado.get());
            return true;
        }

        return false;
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
