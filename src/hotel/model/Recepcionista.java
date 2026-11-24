package hotel.model;

import java.math.BigDecimal;

public class Recepcionista extends Funcionario{

    public Recepcionista(String nome, String cpf, String telefone, String email, String cargo, BigDecimal salario){
        super(nome, cpf, telefone, email, cargo, salario);
    }

    @Override
    public void executarTarefa(){
        System.out.println("Realizando atendimento na recepção.");
    }
}