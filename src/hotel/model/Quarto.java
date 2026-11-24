package hotel.model;

import java.math.BigDecimal;

public class Quarto {
    private final int numero;
    private final TipoQuarto tipo;
    private BigDecimal precoDiaria;
    private final int capacidade;

    public Quarto (int numero, TipoQuarto tipo, BigDecimal precoDiaria, int capacidade){
        this.numero = numero;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.capacidade = capacidade;
    }

    public int getNumero(){
        return numero;
    }

    public TipoQuarto getTipo(){
        return tipo;
    }

    public BigDecimal getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(BigDecimal precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public int getCapacidade (){
        return capacidade;
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "numero=" + numero +
                ", tipo=" + tipo +
                ", precoDiaria=" + precoDiaria +
                ", capacidade=" + capacidade +
                '}';
    }

}
