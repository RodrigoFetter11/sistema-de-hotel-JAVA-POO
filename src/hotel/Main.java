package hotel;

import hotel.model.Quarto;
import hotel.model.Reserva;
import hotel.model.Funcionario;
import hotel.model.Cliente;
import hotel.model.Hotel;
import hotel.service.ClienteService;
import hotel.service.FuncionarioService;
import hotel.service.QuartoService;
import hotel.service.ReservaService;
import java.util.Optional;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        ClienteService clienteService = new ClienteService(hotel);
        FuncionarioService funcionarioService = new FuncionarioService(hotel);

        Scanner scanner = new Scanner(System.in);

        int opcao;

        // MENU EXTERNO
        do {
            System.out.println("\n===== SISTEMA DE HOTEL =====");
            System.out.println("1 - Gerenciar clientes");
            System.out.println("2 - Gerenciar funcionários");
            System.out.println("3 - Gerenciar quartos");
            System.out.println("4 - Gerenciar reservas");
            System.out.println("0 - Encerrar programa");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {

                case 1:
                    menuClientes(scanner, clienteService);
                    break;

                case 2:
                    menuFuncionarios(scanner, funcionarioService);
                    break;

                case 3:
                    // Menu de quartos
                    break;

                case 4:
                    // Menu de reservas
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // MENU INTERNO DE CLIENTES
    public static void menuClientes(Scanner scanner, ClienteService clienteService) {

        int opcao;

        do {
            System.out.println("\n===== MENU DE CLIENTES =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar cliente por CPF");
            System.out.println("4 - Remover cliente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {

                case 1: {
                    System.out.println("\n===== CADASTRO DE CLIENTE =====");

                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Digite o e-mail: ");
                    String email = scanner.nextLine();

                    Cliente cliente = new Cliente(nome, cpf, telefone, email);

                    clienteService.cadastrarCliente(cliente);
                    break;
                }

                case 2: {
                    System.out.println("\n===== LISTA DE CLIENTES =====");
                    clienteService.listarClientes();
                    break;
                }

                case 3: {
                    System.out.println("\n===== BUSCAR CLIENTE POR CPF =====");

                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    Optional<Cliente> clienteEncontrado =
                            clienteService.buscarClientePorCpf(cpf);

                    if (clienteEncontrado.isPresent()) {
                        System.out.println(clienteEncontrado.get());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }

                    break;
                }

                case 4: {
                    System.out.println("\n===== REMOVER CLIENTE =====");

                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = scanner.nextLine();

                    boolean removido = clienteService.removerCliente(cpf);

                    if (removido) {
                        System.out.println("Cliente removido com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }

                    break;
                }

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void menuFuncionarios(
            Scanner scanner,
            FuncionarioService funcionarioService) {

        int opcao;

        do {
            System.out.println("\n===== MENU DE FUNCIONÁRIOS =====");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar funcionário por CPF");
            System.out.println("4 - Remover funcionário");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1: {
                    System.out.println("\n===== CADASTRO DE FUNCIONÁRIO =====");

                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Digite o e-mail: ");
                    String email = scanner.nextLine();

                    System.out.print("Digite o cargo: ");
                    String cargo = scanner.nextLine();

                    System.out.print("Digite o salário: ");
                    double salario = Double.parseDouble(scanner.nextLine());

                    Funcionario funcionario = new Funcionario(
                            nome, cpf, telefone, email, cargo, salario
                    );

                    break;
                }

                case 2: {
                    funcionarioService.listarFuncionarios();
                    break;
                }

                case 3: {
                    System.out.println("\n===== BUSCAR FUNCIONÁRIO POR CPF =====");

                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    Optional<Funcionario> funcionarioEncontrado =
                            funcionarioService.buscarFuncionarioPorCpf(cpf);

                    if (funcionarioEncontrado.isPresent()) {
                        System.out.println(funcionarioEncontrado.get());
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }

                    break;
                }

                case 4: {
                    System.out.println("\n===== BUSCAR FUNCIONÁRIO POR CPF =====");

                    System.out.println("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    boolean removido = funcionarioService.removerFuncionario(cpf);

                    if (removido) {
                        System.out.println("Funcionário removido com sucesso");
                    } else {
                        System.out.println("Funcionário não encontrado");
                    }

                    break;
                }

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
