package hotel.model;

import java.math.BigDecimal;

public class Camareira extends Funcionario {

    public Camareira(String nome, String cpf, String telefone,
                     String email, String cargo, BigDecimal salario) {
        super(nome, cpf, telefone, email, cargo, salario);
    }

    @Override
    public void executarTarefa() {
        System.out.println("Realizando limpeza e organização dos quartos.");
    }

    @Override
    public String toString() {
        return super.toString() + ", especializacao='Camareira'}";
    }
}