package hotel;

import hotel.exception.ReservaInvalidaException;
import hotel.model.Quarto;
import hotel.model.Reserva;
import hotel.model.Funcionario;
import hotel.model.Cliente;
import hotel.model.Hotel;
import hotel.service.ClienteService;
import hotel.service.FuncionarioService;
import hotel.service.QuartoService;
import hotel.service.ReservaService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        ClienteService clienteService = new ClienteService(hotel);
        FuncionarioService funcionarioService = new FuncionarioService(hotel);
        QuartoService quartoService = new QuartoService(hotel);
        ReservaService reservaService = new ReservaService(hotel);

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
                    menuQuartos(scanner, quartoService);
                    break;

                case 4:
                    menuReservas(scanner, reservaService, clienteService, quartoService);
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

                    funcionarioService.cadastrarFuncionario(funcionario);

                    break;
                }

                case 2: {
                    System.out.println("\n===== LISTA DE FUNCIONÁRIOS =====");
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
                    System.out.println("\n===== REMOVER FUNCIONÁRIO =====");

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
    public static void menuQuartos(
            Scanner scanner,
            QuartoService quartoService) {

        int opcao;

        do {
            System.out.println("\n===== MENU DE QUARTOS =====");
            System.out.println("1 - Cadastrar quarto");
            System.out.println("2 - Listar quartos");
            System.out.println("3 - Buscar quarto por número");
            System.out.println("4 - Remover quarto");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {

                case 1: {
                    System.out.println("\n===== CADASTRO DO QUARTO =====");

                    System.out.print("Digite o número do quarto: ");
                    int numero = Integer.parseInt(scanner.nextLine());

                    System.out.print("Digite o tipo do quarto: ");
                    String tipo = scanner.nextLine();

                    System.out.print("Digite o preço da diária: ");
                    double precoDiaria = Double.parseDouble(scanner.nextLine());

                    System.out.print("Digite a capacidade do quarto: ");
                    int capacidade = Integer.parseInt(scanner.nextLine());

                    Quarto quarto = new Quarto(numero, tipo, precoDiaria, capacidade);

                    quartoService.cadastrarQuarto(quarto);

                    break;
                }

                case 2: {
                    System.out.println("\n===== lista de quartos =====");
                    quartoService.listarQuarto();

                    break;
                }

                case 3: {
                    System.out.println("\n===== PROCURAR QUARTO =====");
                    System.out.print("Digite o número do quarto");

                    int numero = Integer.parseInt(scanner.nextLine());

                    Optional<Quarto> quartoEncontrado = quartoService.buscarQuartoPorNumero(numero);

                    if (quartoEncontrado.isPresent()){
                        System.out.println("Quarto encontrado");
                        System.out.println(quartoEncontrado.get());
                    }else {
                        System.out.println("Esse quarto não existe");
                    }

                    break;
                }

                case 4: {
                    System.out.println("\n===== REMOVER QUARTO =====");

                    System.out.print("Digite o número do quarto: ");
                    int numero = Integer.parseInt(scanner.nextLine());

                    boolean removido = quartoService.removerQuarto(numero);

                    if (removido) {
                        System.out.println("Quarto removido com sucesso");
                    } else {
                        System.out.println("Quarto não encontrado");
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

    public static void menuReservas(
            Scanner scanner,
            ReservaService reservaService,
            ClienteService clienteService,
            QuartoService quartoService) {

        int opcao;

        do {
            System.out.println("\n===== GERENCIAR RESERVAS =====");
            System.out.println("1 - Cadastrar reserva");
            System.out.println("2 - Listar reservas");
            System.out.println("3 - Buscar reservas por quarto");
            System.out.println("4 - Buscar reservas por CPF do cliente");
            System.out.println("5 - Remover reserva");
            System.out.println("0 - Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1: {
                    System.out.println("\n===== CADASTRO DE RESERVA =====");

                    // Buscar cliente
                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = scanner.nextLine();

                    Optional<Cliente> clienteEncontrado =
                            clienteService.buscarClientePorCpf(cpf);

                    if (clienteEncontrado.isEmpty()) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }

                    Cliente cliente = clienteEncontrado.get();

                    System.out.println("Cliente encontrado: " + cliente);

                    // Buscar quarto
                    int numeroQuarto;
                    int numeroTentativas = 0;
                    boolean numeroValido = false;

                    while (numeroTentativas < 3) {
                        try {
                            System.out.print("Digite o número do quarto: ");
                            numeroQuarto = Integer.parseInt(scanner.nextLine());

                            numeroValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            numeroTentativas++;

                            System.out.println("Número inválido. Tentativas restantes: " + (3 - numeroTentativas));
                            break;
                        }
                    }

                    if (!numeroValido) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
                        break;
                    }

                    Optional<Quarto> quartoEncontrado =
                            quartoService.buscarQuartoPorNumero(numeroQuarto);

                    if (quartoEncontrado.isEmpty()) {
                        System.out.println("Quarto não encontrado.");
                        break;
                    }

                    Quarto quarto = quartoEncontrado.get();

                    System.out.println("Quarto encontrado: " + quarto);

                    LocalDate dataEntrada;
                    LocalDate dataSaida;

                    try {
                        System.out.print("Digite a data de entrada (AAAA-MM-DD): ");
                        dataEntrada = LocalDate.parse(scanner.nextLine());

                        System.out.print("Digite a data de saída (AAAA-MM-DD): ");
                        dataSaida = LocalDate.parse(scanner.nextLine());
                    } catch (DateTimeException e){

                        System.out.println("Erro: digite uma data válida no formato AAAA-MM-DD.");
                        break;
                    }




                    Reserva reserva = new Reserva(
                            cliente,
                            quarto,
                            dataEntrada,
                            dataSaida
                    );

                    try {
                        reservaService.cadastrarReserva(reserva);
                        System.out.println("Reserva cadastrada com sucesso");

                    } catch (ReservaInvalidaException e){
                        System.out.println("Erro ao cadastrar reserva: " +e.getMessage());
                    }


                    break;
                }



                case 2:
                    // Listar reservas
                    break;

                case 3:
                    // Buscar reservas por quarto
                    break;

                case 4:
                    // Buscar reservas por CPF
                    break;

                case 5:
                    // Remover reserva
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
