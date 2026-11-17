package hotel;

import hotel.exception.ClienteInvalidoException;
import hotel.exception.FuncionarioInvalidoException;
import hotel.exception.QuartoInvalidoException;
import hotel.exception.ReservaInvalidaException;
import hotel.model.*;
import hotel.service.ClienteService;
import hotel.service.FuncionarioService;
import hotel.service.QuartoService;
import hotel.service.ReservaService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
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

        int opcao = -1;

        // MENU EXTERNO
        do {
            System.out.println("\n===== SISTEMA DE HOTEL =====");
            System.out.println("1 - Gerenciar clientes");
            System.out.println("2 - Gerenciar funcionários");
            System.out.println("3 - Gerenciar quartos");
            System.out.println("4 - Gerenciar reservas");
            System.out.println("0 - Encerrar programa");



            int tentativas = 0;
            boolean opcaoValida = false;

            while (tentativas < 3) {
                System.out.print("Escolha uma opção: ");
                try {
                    opcao = Integer.parseInt(scanner.nextLine());

                    opcaoValida = true;
                    break;

                } catch (NumberFormatException e) {
                    tentativas++;

                    System.out.println("Opção inválida. Digite um número.");
                    System.out.println("Tentativas restantes: " + (3 - tentativas));
                }
            }

            if (!opcaoValida) {
                System.out.println("Limite de tentativas atingido. Retornando ao menu principal.");
                continue;
            }

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

        int opcao = -1;

        do {
            System.out.println("\n===== MENU DE CLIENTES =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar cliente por CPF");
            System.out.println("4 - Remover cliente");
            System.out.println("0 - Voltar ao menu principal");


            int tentativas = 0;
            boolean opcaoValida = false;

            while (tentativas < 3) {
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    opcaoValida = true;
                    break;

                } catch (NumberFormatException e) {
                    tentativas++;
                    System.out.println("Opção inválida. Tentativas restantes: "
                            + (3 - tentativas));
                }
            }

            if (!opcaoValida) {
                System.out.println("Limite de tentativas atingido. Retornando ao menu.");
                continue;
            }

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

                    try {
                        clienteService.cadastrarCliente(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (ClienteInvalidoException e) {
                        System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
                    }

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
                        System.out.println("Não foi possível remover o cliente.");
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

        int opcao = -1;

        do {
            System.out.println("\n===== MENU DE FUNCIONÁRIOS =====");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar funcionário por CPF");
            System.out.println("4 - Remover funcionário");
            System.out.println("0 - Voltar ao menu principal");

            int tentativas = 0;
            boolean opcaoValida = false;

            while (tentativas < 3) {
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    opcaoValida = true;
                    break;

                } catch (NumberFormatException e) {
                    tentativas++;

                    System.out.println("Opção inválida. Tentativas restantes: "
                            + (3 - tentativas));
                }
            }

            if (!opcaoValida) {
                System.out.println("Limite de tentativas atingido. Retornando ao menu.");
                continue;
            }


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

                    double salario = 0;
                    int tentativasSalario = 0;
                    boolean salarioValido = false;

                    while (tentativasSalario < 3) {
                        try {
                            System.out.print("Digite o salário: ");
                            salario = Double.parseDouble(scanner.nextLine());

                            salarioValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativasSalario++;

                            System.out.println("Salário inválido. Tentativas restantes: "
                                    + (3 - tentativasSalario));
                        }
                    }

                    if (!salarioValido) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
                        break;
                    }

                    Funcionario funcionario = new Funcionario(
                            nome, cpf, telefone, email, cargo, salario
                    );

                    try {
                        funcionarioService.cadastrarFuncionario(funcionario);
                        System.out.println("Funcionário cadastrado com sucesso!");
                    } catch (FuncionarioInvalidoException e) {
                        System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
                    }

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

                    System.out.print("Digite o CPF: ");
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

        int opcao = -1;

        do {
            System.out.println("\n===== MENU DE QUARTOS =====");
            System.out.println("1 - Cadastrar quarto");
            System.out.println("2 - Listar quartos");
            System.out.println("3 - Buscar quarto por número");
            System.out.println("4 - Remover quarto");
            System.out.println("0 - Voltar ao menu principal");

            int tentativasMenu = 0;
            boolean opcaoValida = false;

            while (tentativasMenu < 3) {
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    opcaoValida = true;
                    break;

                } catch (NumberFormatException e) {
                    tentativasMenu++;

                    System.out.println("Opção inválida. Tentativas restantes: "
                            + (3 - tentativasMenu));
                }
            }

            if (!opcaoValida) {
                System.out.println("Limite de tentativas atingido. Retornando ao menu.");
                continue;
            }

            switch (opcao) {

                case 1: {
                    System.out.println("\n===== CADASTRO DO QUARTO =====");

                    int numero = 0;
                    int tentativasNumero = 0;
                    boolean numeroValido = false;

                    while (tentativasNumero < 3) {
                        try {
                            System.out.print("Digite o número do quarto: ");
                            numero = Integer.parseInt(scanner.nextLine());

                            numeroValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativasNumero++;

                            System.out.println("Número inválido. Tentativas restantes: "
                                    + (3 - tentativasNumero));
                        }
                    }

                    if (!numeroValido) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
                        break;
                    }

                    System.out.println("\n===== TIPOS DE QUARTO =====");
                    System.out.println("1 - STANDARD");
                    System.out.println("2 - LUXO");
                    System.out.println("3 - SUITE");

                    TipoQuarto tipo = null;
                    int tentativasTipo = 0;

                    while (tentativasTipo < 3) {
                        try {
                            System.out.print("Escolha o tipo do quarto: ");
                            int opcaoTipo = Integer.parseInt(scanner.nextLine());

                            tipo = switch (opcaoTipo) {
                                case 1 -> TipoQuarto.STANDARD;
                                case 2 -> TipoQuarto.LUXO;
                                case 3 -> TipoQuarto.SUITE;
                                default -> null;
                            };

                            if (tipo != null) {
                                break;
                            }

                            tentativasTipo++;
                            System.out.println("Opção inválida. Tentativas restantes: "
                                    + (3 - tentativasTipo));

                        } catch (NumberFormatException e) {
                            tentativasTipo++;

                            System.out.println("Digite um número válido. Tentativas restantes: "
                                    + (3 - tentativasTipo));
                        }
                    }

                    if (tipo == null) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
                        break;
                    }

                    double precoDiaria = 0;
                    int tentativas = 0;
                    boolean precoValido = false;

                    while (tentativas < 3) {
                        try {
                            System.out.print("Digite o preço da diária: ");
                            precoDiaria = Double.parseDouble(scanner.nextLine());

                            precoValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativas++;

                            System.out.println("Preço inválido. Tentativas restantes: "
                                    + (3 - tentativas));
                        }
                    }

                    if (!precoValido) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
                        break;
                    }

                    int capacidade = 0;
                    int tentativasCapacidade = 0;
                    boolean capacidadeValida = false;

                    while (tentativasCapacidade < 3) {
                        try {
                            System.out.print("Digite a capacidade do quarto: ");
                            capacidade = Integer.parseInt(scanner.nextLine());

                            capacidadeValida = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativasCapacidade++;

                            System.out.println("Capacidade inválida. Tentativas restantes: "
                                    + (3 - tentativasCapacidade));
                        }
                    }

                    if (!capacidadeValida) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
                        break;
                    }

                    Quarto quarto = new Quarto(numero, tipo, precoDiaria, capacidade);

                    try {
                        quartoService.cadastrarQuarto(quarto);
                        System.out.println("Quarto cadastrado com sucesso!");
                    } catch (QuartoInvalidoException e) {
                        System.out.println("Erro ao cadastrar quarto: " + e.getMessage());
                    }

                    break;
                }

                case 2: {
                    System.out.println("\n===== LISTA DE QUARTOS =====");
                    quartoService.listarQuarto();

                    break;
                }

                case 3: {
                    System.out.println("\n===== BUSCAR QUARTO =====");

                    int numero = 0;
                    int tentativas = 0;
                    boolean numeroValido = false;

                    while (tentativas < 3) {
                        try {
                            System.out.print("Digite o número do quarto: ");
                            numero = Integer.parseInt(scanner.nextLine());

                            numeroValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativas++;

                            System.out.println("Número inválido. Tentativas restantes: "
                                    + (3 - tentativas));
                        }
                    }

                    if (!numeroValido) {
                        System.out.println("Limite de tentativas atingido. Operação cancelada.");
                        break;
                    }

                    Optional<Quarto> quartoEncontrado = quartoService.buscarQuartoPorNumero(numero);

                    if (quartoEncontrado.isPresent()){
                        System.out.println("Quarto encontrado");
                        System.out.println(quartoEncontrado.get());
                    }else {
                        System.out.println("Quarto não encontrado.");
                    }

                    break;
                }

                case 4: {
                    System.out.println("\n===== REMOVER QUARTO =====");

                    int numero = 0;
                    int tentativas = 0;
                    boolean numeroValido = false;

                    while (tentativas < 3) {
                        try {
                            System.out.print("Digite o número do quarto: ");
                            numero = Integer.parseInt(scanner.nextLine());

                            numeroValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativas++;

                            System.out.println("Número inválido. Tentativas restantes: "
                                    + (3 - tentativas));
                        }
                    }

                    if (!numeroValido) {
                        System.out.println("Limite de tentativas atingido. Operação cancelada.");
                        break;
                    }

                    boolean removido = quartoService.removerQuarto(numero);

                    if (removido) {
                        System.out.println("Quarto removido com sucesso");
                    } else {
                        System.out.println("Não foi possível remover o quarto. "
                                + "Verifique se ele existe ou possui reservas.");
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

        int opcao = -1;

        do {
            System.out.println("\n===== GERENCIAR RESERVAS =====");
            System.out.println("1 - Cadastrar reserva");
            System.out.println("2 - Listar reservas");
            System.out.println("3 - Buscar reservas por quarto");
            System.out.println("4 - Buscar reservas por CPF do cliente");
            System.out.println("5 - Remover reserva");
            System.out.println("0 - Voltar ao menu principal");

            int tentativasReserva = 0;
            boolean opcaoValida = false;

            while (tentativasReserva < 3) {
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    opcaoValida = true;
                    break;

                } catch (NumberFormatException e) {
                    tentativasReserva++;

                    System.out.println("Opção inválida. Tentativas restantes: "
                            + (3 - tentativasReserva));
                }
            }

            if (!opcaoValida) {
                System.out.println("Limite de tentativas atingido. Retornando ao menu.");
                continue;
            }

            switch (opcao) {
                case 1: {
                    System.out.println("\n===== CADASTRO DE RESERVA =====");

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

                    int numeroQuarto = 0;
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

                    LocalDate dataEntrada = null;
                    LocalDate dataSaida = null;

                    int tentativasEntrada = 0;
                    boolean entradaValida = false;

                    while (tentativasEntrada < 3) {
                        try {
                            System.out.print("Digite a data de entrada (AAAA-MM-DD): ");
                            dataEntrada = LocalDate.parse(scanner.nextLine());

                            entradaValida = true;
                            break;

                        } catch (DateTimeParseException e) {
                            tentativasEntrada++;

                            System.out.println("Data inválida. Tentativas restantes: "
                                    + (3 - tentativasEntrada));
                        }
                    }

                    if (!entradaValida) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
                        break;
                    }

                    int tentativasSaida = 0;
                    boolean saidaValida = false;

                    while (tentativasSaida < 3) {
                        try {
                            System.out.print("Digite a data de saída (AAAA-MM-DD): ");
                            dataSaida = LocalDate.parse(scanner.nextLine());

                            saidaValida = true;
                            break;

                        } catch (DateTimeParseException e) {
                            tentativasSaida++;

                            System.out.println("Data inválida. Tentativas restantes: "
                                    + (3 - tentativasSaida));
                        }
                    }

                    if (!saidaValida) {
                        System.out.println("Limite de tentativas atingido. Cadastro cancelado.");
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

                case 2: {
                    System.out.println("\n===== LISTA DE RESERVAS =====");
                    reservaService.listarReservas();
                    break;
                }

                case 3: {
                    System.out.println("\n===== BUSCAR RESERVAS POR NÚMERO DO QUARTO =====");

                    int numeroQuarto = 0;
                    int tentativas = 0;
                    boolean numeroValido = false;

                    while (tentativas < 3) {
                        try {
                            System.out.print("Digite o número do quarto: ");
                            numeroQuarto = Integer.parseInt(scanner.nextLine());

                            numeroValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativas++;

                            System.out.println("Número inválido. Tentativas restantes: "
                                    + (3 - tentativas));
                        }
                    }

                    if (!numeroValido) {
                        System.out.println("Limite de tentativas atingido. Operação cancelada.");
                        break;
                    }

                    List<Reserva> reservasEncontradas =
                            reservaService.buscarReservaPorQuarto(numeroQuarto);

                    if (reservasEncontradas.isEmpty()) {
                        System.out.println("Nenhuma reserva encontrada para esse quarto.");
                    } else {
                        for (Reserva reserva : reservasEncontradas) {
                            System.out.println(reserva);
                        }
                    }

                    break;
                }

                case 4: {
                    System.out.println("\n===== BUSCAR RESERVAS POR CPF =====");

                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    List<Reserva> reservasEncontradas =
                            reservaService.buscarReservaPorCpf(cpf);

                    if (reservasEncontradas.isEmpty()) {
                        System.out.println("Nenhuma reserva encontrada para esse CPF.");
                    } else {
                        for (Reserva reserva : reservasEncontradas) {
                            System.out.println(reserva);
                        }
                    }

                    break;
                }

                case 5: {
                    System.out.println("\n===== REMOVER RESERVA =====");

                    int numeroQuarto = 0;
                    int tentativasNumero = 0;
                    boolean numeroValido = false;

                    while (tentativasNumero < 3) {
                        try {
                            System.out.print("Digite o número do quarto: ");
                            numeroQuarto = Integer.parseInt(scanner.nextLine());

                            numeroValido = true;
                            break;

                        } catch (NumberFormatException e) {
                            tentativasNumero++;

                            System.out.println("Número inválido. Tentativas restantes: "
                                    + (3 - tentativasNumero));
                        }
                    }

                    if (!numeroValido) {
                        System.out.println("Limite de tentativas atingido. Operação cancelada.");
                        break;
                    }

                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    LocalDate dataEntrada = null;
                    int tentativasData = 0;
                    boolean dataValida = false;

                    while (tentativasData < 3) {
                        try {
                            System.out.print("Digite a data de entrada (AAAA-MM-DD): ");
                            dataEntrada = LocalDate.parse(scanner.nextLine());

                            dataValida = true;
                            break;

                        } catch (DateTimeParseException e) {
                            tentativasData++;

                            System.out.println("Data inválida. Tentativas restantes: "
                                    + (3 - tentativasData));
                        }
                    }

                    if (!dataValida) {
                        System.out.println("Limite de tentativas atingido. Operação cancelada.");
                        break;
                    }

                    boolean removida = reservaService.removerReserva(
                            numeroQuarto, cpf, dataEntrada);

                    if (removida){
                        System.out.println("Reserva removida com sucesso!");
                    } else {
                        System.out.println("Reserva não encontrada.");
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
