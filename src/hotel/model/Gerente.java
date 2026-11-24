package hotel.model;

import java.math.BigDecimal;

public class Gerente extends Funcionario {

    public Gerente(String nome, String cpf, String telefone,
                   String email, String cargo, BigDecimal salario) {
        super(nome, cpf, telefone, email, cargo, salario);
    }

    @Override
    public void executarTarefa() {
        System.out.println("Gerenciando as operações do hotel.");
    }

    @Override
    public String toString() {
        return super.toString() + ", especializacao='Gerente'}";
    }
}