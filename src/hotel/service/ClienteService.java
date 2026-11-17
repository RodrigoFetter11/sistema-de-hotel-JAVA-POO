
package hotel.service;

import hotel.exception.ClienteInvalidoException;
import hotel.model.Cliente;
import hotel.model.Hotel;
import hotel.model.Reserva;

import java.util.Optional;

public class ClienteService {

    private final Hotel hotel;

    public ClienteService(Hotel hotel) {
        this.hotel = hotel;
    }

    // cadastrarCliente()
    public void cadastrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new ClienteInvalidoException("O cliente não pode ser nulo.");
        }

        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new ClienteInvalidoException("O nome do cliente é obrigatório.");
        }

        if (cliente.getCpf() == null || !cliente.getCpf().matches("\\d{11}")) {
            throw new ClienteInvalidoException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
            throw new ClienteInvalidoException("O telefone do cliente é obrigatório.");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank() || !cliente.getEmail().contains("@")) {
            throw new ClienteInvalidoException("Informe um endereço de e-mail válido.");
        }

        for (Cliente clienteExistente : hotel.getClientes()) {
            if (clienteExistente.getCpf().equals(cliente.getCpf())) {
                throw new ClienteInvalidoException("Já existe um cliente cadastrado com esse CPF.");
            }
        }

        hotel.getClientes().add(cliente);
    }

    // listarClientes()
    public void listarClientes (){
        if (hotel.getClientes().isEmpty()){
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : hotel.getClientes()){
            System.out.println(cliente);
        }
    }


    // buscarClientePorCpf()
    public Optional<Cliente> buscarClientePorCpf (String cpf){
        for (Cliente cliente : hotel.getClientes()){
            if (cliente.getCpf().equals(cpf)){
                return Optional.of(cliente);
            }
        }

        return Optional.empty();
    }


    // removerCliente()
    public boolean removerCliente(String cpf) {
        Optional<Cliente> resultado = buscarClientePorCpf(cpf);

        // Verifica se o cliente existe
        if (resultado.isEmpty()) {
            return false;
        }

        // Verifica se o cliente possui reservas
        for (Reserva reservaEncontrada : hotel.getReservas()) {
            if (reservaEncontrada.getCliente().equals(resultado.get())) {
                return false;
            }
        }

        // Se não possui reservas, remove o cliente
        hotel.getClientes().remove(resultado.get());
        return true;
    }

}
