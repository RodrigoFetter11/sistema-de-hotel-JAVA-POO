package hotel.model;

public class Gerente extends Funcionario {

    public Gerente(String nome, String cpf, String telefone,
                   String email, String cargo, Double salario) {
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