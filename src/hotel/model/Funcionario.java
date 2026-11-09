package hotel.model;

public class Funcionario extends Pessoa{
    private String cargo;
    private Double salario;

    public Funcionario(String nome, String cpf, String telefone, String email, String cargo, Double salario) {
        super(nome, cpf, telefone, email);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
