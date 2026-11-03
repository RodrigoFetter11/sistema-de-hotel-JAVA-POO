package hotel.service;

import hotel.model.Funcionario;
import hotel.model.Hotel;

import java.util.Optional;

public class FuncionarioService {

    private final Hotel hotel;

    public FuncionarioService(Hotel hotel) {
        this.hotel = hotel;
    }

    public void cadastrarFuncionario (Funcionario funcionario) {
        if (funcionario == null){
            return;
        }

        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            return;
        }

        if (funcionario.getCpf() == null || !funcionario.getCpf().matches("\\d{11}")){
            return;
        }

        if (funcionario.getTelefone() == null || funcionario.getTelefone().isBlank()){
            return;
        }

        if (funcionario.getEmail() == null || funcionario.getEmail().isBlank() || !funcionario.getEmail().contains("@")){
            return;
        }

        if (funcionario.getCargo() == null || funcionario.getCargo().isBlank()) {
            return;
        }

        if (funcionario.getSalario() <= 0) {
            return;
        }

        for (Funcionario funcionarioExistente : hotel.getFuncionarios()){
            if (funcionarioExistente.getCpf().equals(funcionario.getCpf())){
                return;
            }
        }

        hotel.getFuncionarios().add(funcionario);
    }

        public void listarFuncionario () {
            if (hotel.getFuncionarios().isEmpty()){
                System.out.println("Nenhum funcionário cadastrado.");
                return;
            }

            for (Funcionario funcionario : hotel.getFuncionarios()){
                System.out.println(funcionario);
            }
        }

        public Optional<Funcionario> buscarFuncionarioPorCpf (String cpf){
            for (Funcionario funcionario : hotel.getFuncionarios()){
                if (funcionario.getCpf().equals(cpf)){
                    return Optional.of(funcionario);
                }
            }

            return Optional.empty();
        }

        public boolean removerFuncionario (String cpf) {
            Optional<Funcionario> resultado = buscarFuncionarioPorCpf(cpf);

            if (resultado.isPresent()){
                hotel.getFuncionarios().remove(resultado.get());
                return true;
            }

            return false;
        }
}