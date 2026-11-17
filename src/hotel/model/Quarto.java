package hotel.model;

public class Quarto {
    private final int numero;
    private final TipoQuarto tipo;
    private double precoDiaria;
    private final int capacidade;

    public Quarto (int numero, TipoQuarto tipo, double precoDiaria, int capacidade){
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

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
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
