
package hotel.service;

import hotel.model.Cliente;
import hotel.model.Hotel;

import java.util.Optional;

public class ClienteService {

    private final Hotel hotel;

    public ClienteService(Hotel hotel) {
        this.hotel = hotel;
    }

    // cadastrarCliente()
    public void cadastrarCliente (Cliente cliente){
        if (cliente == null){
            return;
        }

        if (cliente.getNome() == null || cliente.getNome().isBlank()){
            return;
        }

        if (cliente.getCpf() == null || !cliente.getCpf().matches("\\d{11}")) {
            return;
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()){
            return;
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank() || !cliente.getEmail().contains("@")){
            return;
        }

        for (Cliente clienteExistente : hotel.getClientes()){
            if (clienteExistente.getCpf().equals(cliente.getCpf())){
                return;
            }
        }

        hotel.getClientes().add(cliente);
    }

    // listarClientes()
    public void listarCliente (){
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

        if (resultado.isPresent()) {
            hotel.getClientes().remove(resultado.get());
            return true;
        }

        return false;
    }

}
