package hotel.service;

import hotel.exception.FuncionarioInvalidoException;
import hotel.model.Funcionario;
import hotel.model.Hotel;

import java.util.Optional;

public class FuncionarioService {

    private final Hotel hotel;

    public FuncionarioService(Hotel hotel) {
        this.hotel = hotel;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new FuncionarioInvalidoException("O funcionário não pode ser nulo.");
        }

        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new FuncionarioInvalidoException("O nome do funcionário é obrigatório.");
        }

        if (funcionario.getCpf() == null || !funcionario.getCpf().matches("\\d{11}")) {
            throw new FuncionarioInvalidoException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        if (funcionario.getTelefone() == null || funcionario.getTelefone().isBlank()) {
            throw new FuncionarioInvalidoException("O telefone do funcionário é obrigatório.");
        }

        if (funcionario.getEmail() == null || funcionario.getEmail().isBlank() || !funcionario.getEmail().contains("@")) {
            throw new FuncionarioInvalidoException("Informe um endereço de e-mail válido.");
        }

        if (funcionario.getCargo() == null || funcionario.getCargo().isBlank()) {
            throw new FuncionarioInvalidoException("O cargo do funcionário é obrigatório.");
        }

        if (funcionario.getSalario() <= 0) {
            throw new FuncionarioInvalidoException("O salário deve ser maior que zero.");
        }

        for (Funcionario funcionarioExistente : hotel.getFuncionarios()) {
            if (funcionarioExistente.getCpf().equals(funcionario.getCpf())) {
                throw new FuncionarioInvalidoException("Já existe um funcionário cadastrado com esse CPF.");
            }
        }

        hotel.getFuncionarios().add(funcionario);
    }

        public void listarFuncionarios () {
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