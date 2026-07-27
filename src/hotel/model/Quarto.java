package hotel.model;

public class Quarto {
    private int numero;
    private String tipo;
    private double precoDiaria;
    private boolean disponivel;

    public Quarto (int numero, String tipo, double precoDiaria, boolean disponivel){
        this.numero = numero;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.disponivel = disponivel;
    }

    public int getNumero(){
        return numero;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
