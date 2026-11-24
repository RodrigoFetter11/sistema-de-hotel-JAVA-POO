package hotel.model;

import java.math.BigDecimal;

public abstract class Funcionario extends Pessoa{
    private String cargo;
    private BigDecimal salario;

    public Funcionario(String nome, String cpf, String telefone, String email, String cargo, BigDecimal salario) {
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public abstract void executarTarefa();

    @Override
    public String toString() {
        return super.toString()
                + ", cargo='" + cargo + '\''
                + ", salario='" + salario + "'}";
    }
}
