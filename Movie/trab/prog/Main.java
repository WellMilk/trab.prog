package trab.prog;

import java.util.Scanner;

public class Main {

    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        Locadora locadora = new Locadora("MovieLoc", "Videira - SC");
        int opcao;

        do {
            mostrarMenu(locadora);
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarCliente(locadora);
                    break;

                case 2:
                    locadora.listarClientes();
                    break;

                case 3:
                    buscarCliente(locadora);
                    break;

                case 4:
                    atualizarCliente(locadora);
                    break;

                case 5:
                    removerCliente(locadora);
                    break;

                case 6:
                    cadastrarFilme(locadora);
                    break;

                case 7:
                    locadora.listarFilmes();
                    break;

                case 8:
                    buscarFilme(locadora);
                    break;

                case 9:
                    atualizarFilme(locadora);
                    break;

                case 10:
                    removerFilme(locadora);
                    break;

                case 11:
                    realizarLocacao(locadora);
                    break;

                case 12:
                    devolverFilme(locadora);
                    break;

                case 13:
                    locadora.listarLocacoes();
                    break;

                case 14:
                    buscarLocacao(locadora);
                    break;

                case 15:
                    atualizarLocacao(locadora);
                    break;

                case 16:
                    removerLocacao(locadora);
                    break;

                case 17:
                    cadastrarFuncionario(locadora);
                    break;

                case 18:
                    locadora.listarFuncionarios();
                    break;

                case 19:
                    atualizarFuncionario(locadora);
                    break;

                case 20:
                    removerFuncionario(locadora);
                    break;

                case 21:
                    locadora.listarTodasAsPessoas();
                    break;

                case 0:
                    System.out.println(
                            "Saindo do MovieLoc. Até mais!");
                    break;

                default:
                    System.out.println(
                            "Opção inválida. Escolha uma opção que aparece no menu.");
            }

            if (opcao != 0) {
                System.out.println();
            }

        } while (opcao != 0);

        entrada.close();
    }

    private static void mostrarMenu(Locadora locadora) {
        System.out.println(
                "\n========== " + locadora.getNome().toUpperCase()
                        + " ==========");
        System.out.println("Endereço: " + locadora.getEndereco());

        System.out.println("--- CLIENTES ---");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Buscar cliente por CPF");
        System.out.println("4 - Atualizar telefone/e-mail do cliente");
        System.out.println("5 - Remover cliente");

        System.out.println("--- FILMES ---");
        System.out.println("6 - Cadastrar filme");
        System.out.println("7 - Listar filmes");
        System.out.println("8 - Buscar filme por título");
        System.out.println("9 - Atualizar filme");
        System.out.println("10 - Remover filme");

        System.out.println("--- LOCAÇÕES ---");
        System.out.println("11 - Realizar locação");
        System.out.println("12 - Devolver filme");
        System.out.println("13 - Listar locações");
        System.out.println("14 - Buscar locação por código");
        System.out.println("15 - Atualizar valor da locação");
        System.out.println("16 - Remover locação");

        System.out.println("--- FUNCIONÁRIOS / POO ---");
        System.out.println("17 - Cadastrar funcionário");
        System.out.println("18 - Listar funcionários");
        System.out.println("19 - Atualizar funcionário");
        System.out.println("20 - Remover funcionário");
        System.out.println(
                "21 - Listar clientes e funcionários (polimorfismo)");

        System.out.println("0 - Sair");
    }

    private static void cadastrarCliente(Locadora locadora) {
        System.out.println("\n--- CADASTRAR CLIENTE ---");

        String nome = lerTexto("Nome: ");
        String cpf = lerCPF();
        String telefone = lerTelefone();
        String email = lerEmail();

        try {
            Cliente cliente = new Cliente(
                    nome, cpf, telefone, email);

            if (locadora.cadastrarCliente(cliente)) {
                System.out.println(
                        "Cliente cadastrado com sucesso!");
            } else {
                System.out.println(
                        "Já existe um cliente cadastrado com esse CPF.");
            }

        } catch (IllegalArgumentException erro) {
            System.out.println(
                    "Não foi possível cadastrar: " + erro.getMessage());
        }
    }

    private static void buscarCliente(Locadora locadora) {
        Cliente cliente = locadora.buscarClientePorCpf(lerCPF());

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println(cliente);
        }
    }

    private static void atualizarCliente(Locadora locadora) {
        System.out.println("\n--- ATUALIZAR CLIENTE ---");

        String cpf = lerCPF();

        if (locadora.buscarClientePorCpf(cpf) == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        String telefone = lerTelefone();
        String email = lerEmail();

        try {
            locadora.atualizarCliente(cpf, telefone, email);
            System.out.println(
                    "Cliente atualizado com sucesso!");

        } catch (IllegalArgumentException erro) {
            System.out.println(
                    "Não foi possível atualizar: " + erro.getMessage());
        }
    }

    private static void removerCliente(Locadora locadora) {
        String cpf = lerCPF();

        if (locadora.removerCliente(cpf)) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println(
                    "Cliente não encontrado ou possui uma locação em aberto.");
        }
    }

    private static void cadastrarFilme(Locadora locadora) {
        System.out.println("\n--- CADASTRAR FILME ---");

        String titulo = lerTexto("Título: ");
        String genero = lerTexto("Gênero: ");

        int ano = lerInteiro("Ano (1888 a 2100): ");

        while (ano < 1888 || ano > 2100) {
            System.out.println("Ano inválido.");
            ano = lerInteiro("Informe um ano entre 1888 e 2100: ");
        }

        String duracao = lerTexto("Duração (ex.: 120 minutos): ");

        try {
            Filme filme = new Filme(
                    titulo, genero, ano, duracao);

            if (locadora.cadastrarFilme(filme)) {
                System.out.println(
                        "Filme cadastrado com sucesso!");
            } else {
                System.out.println(
                        "Já existe um filme com esse título.");
            }

        } catch (IllegalArgumentException erro) {
            System.out.println(
                    "Não foi possível cadastrar: " + erro.getMessage());
        }
    }

    private static void buscarFilme(Locadora locadora) {
        Filme filme = locadora.buscarFilmePorTitulo(
                lerTexto("Título do filme: "));

        if (filme == null) {
            System.out.println("Filme não encontrado.");
        } else {
            System.out.println(filme);
        }
    }

    private static void atualizarFilme(Locadora locadora) {
        System.out.println("\n--- ATUALIZAR FILME ---");

        String titulo = lerTexto(
                "Título do filme que deseja atualizar: ");

        if (locadora.buscarFilmePorTitulo(titulo) == null) {
            System.out.println("Filme não encontrado.");
            return;
        }

        String genero = lerTexto("Novo gênero: ");
        int ano = lerInteiro("Novo ano (1888 a 2100): ");

        while (ano < 1888 || ano > 2100) {
            System.out.println("Ano inválido.");
            ano = lerInteiro("Informe um ano entre 1888 e 2100: ");
        }

        String duracao = lerTexto("Nova duração: ");

        try {
            locadora.atualizarFilme(
                    titulo, genero, ano, duracao);

            System.out.println(
                    "Filme atualizado com sucesso!");

        } catch (IllegalArgumentException erro) {
            System.out.println(
                    "Não foi possível atualizar: " + erro.getMessage());
        }
    }

    private static void removerFilme(Locadora locadora) {
        String titulo = lerTexto(
                "Título do filme que deseja remover: ");

        if (locadora.removerFilme(titulo)) {
            System.out.println("Filme removido com sucesso!");
        } else {
            System.out.println(
                    "Filme não encontrado ou está alugado.");
        }
    }

    private static void realizarLocacao(Locadora locadora) {
        System.out.println("\n--- REALIZAR LOCAÇÃO ---");

        String cpf = lerCPF();
        String titulo = lerTexto("Título do filme: ");
        String data = lerData(
                "Data da locação (DD/MM/AAAA): ");
        double valor = lerDoublePositivo(
                "Valor da locação (R$): ");

        System.out.println(
                locadora.realizarLocacao(cpf, titulo, data, valor));
    }

    private static void devolverFilme(Locadora locadora) {
        System.out.println("\n--- DEVOLVER FILME ---");

        String titulo = lerTexto("Título do filme: ");
        String data = lerData(
                "Data da devolução (DD/MM/AAAA): ");

        System.out.println(
                locadora.devolverFilme(titulo, data));
    }

    private static void buscarLocacao(Locadora locadora) {
        int codigo = lerInteiro("Código da locação: ");

        Locacao locacao = locadora.buscarLocacaoPorCodigo(codigo);

        if (locacao == null) {
            System.out.println("Locação não encontrada.");
        } else {
            System.out.println(locacao);
        }
    }

    private static void atualizarLocacao(Locadora locadora) {
        int codigo = lerInteiro(
                "Código da locação que deseja atualizar: ");

        if (locadora.buscarLocacaoPorCodigo(codigo) == null) {
            System.out.println("Locação não encontrada.");
            return;
        }

        double valor = lerDoublePositivo("Novo valor (R$): ");

        if (locadora.atualizarValorLocacao(codigo, valor)) {
            System.out.println(
                    "Valor da locação atualizado com sucesso!");
        } else {
            System.out.println(
                    "Não foi possível atualizar a locação.");
        }
    }

    private static void removerLocacao(Locadora locadora) {
        int codigo = lerInteiro(
                "Código da locação que deseja remover: ");

        if (locadora.removerLocacao(codigo)) {
            System.out.println("Locação removida com sucesso!");
        } else {
            System.out.println("Locação não encontrada.");
        }
    }

    private static void cadastrarFuncionario(Locadora locadora) {
        System.out.println("\n--- CADASTRAR FUNCIONÁRIO ---");

        String nome = lerTexto("Nome: ");
        String telefone = lerTelefone();
        String matricula = lerTexto("Matrícula: ");
        String cargo = lerTexto("Cargo: ");

        try {
            Funcionario funcionario = new Funcionario(
                    nome, telefone, matricula, cargo);

            if (locadora.cadastrarFuncionario(funcionario)) {
                System.out.println(
                        "Funcionário cadastrado com sucesso!");
            } else {
                System.out.println(
                        "Já existe um funcionário com essa matrícula.");
            }

        } catch (IllegalArgumentException erro) {
            System.out.println(
                    "Não foi possível cadastrar: " + erro.getMessage());
        }
    }

    private static void atualizarFuncionario(Locadora locadora) {
        String matricula = lerTexto(
                "Matrícula do funcionário: ");

        if (locadora.buscarFuncionarioPorMatricula(matricula) == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        String telefone = lerTelefone();
        String cargo = lerTexto("Novo cargo: ");

        try {
            locadora.atualizarFuncionario(
                    matricula, telefone, cargo);

            System.out.println(
                    "Funcionário atualizado com sucesso!");

        } catch (IllegalArgumentException erro) {
            System.out.println(
                    "Não foi possível atualizar: " + erro.getMessage());
        }
    }

    private static void removerFuncionario(Locadora locadora) {
        String matricula = lerTexto(
                "Matrícula do funcionário que deseja remover: ");

        if (locadora.removerFuncionario(matricula)) {
            System.out.println(
                    "Funcionário removido com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static String lerTexto(String mensagem) {
        String texto;

        do {
            System.out.print(mensagem);
            texto = entrada.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println(
                        "Este campo não pode ficar vazio. Tente novamente.");
            }

        } while (texto.isEmpty());

        return texto;
    }

    private static String lerCPF() {
        String cpf;

        do {
            System.out.print("CPF (com ou sem pontuação): ");
            cpf = entrada.nextLine().trim();

            if (!Validadores.validarCPF(cpf)) {
                System.out.println(
                        "CPF inválido. Digite um CPF com 11 números e dígitos verificadores corretos.");
            }

        } while (!Validadores.validarCPF(cpf));

        return Validadores.somenteNumeros(cpf);
    }

    private static String lerEmail() {
        String email;

        do {
            System.out.print("E-mail: ");
            email = entrada.nextLine().trim();

            if (!Validadores.validarEmail(email)) {
                System.out.println(
                        "E-mail inválido. Exemplo: nome@gmail.com ou nome@hotmail.com. Digite novamente.");
            }

        } while (!Validadores.validarEmail(email));

        return email;
    }

    private static String lerTelefone() {
        String telefone;

        do {
            System.out.print("Telefone com DDD: ");
            telefone = entrada.nextLine().trim();

            if (!Validadores.validarTelefone(telefone)) {
                System.out.println(
                        "Telefone inválido. Informe 10 dígitos (fixo) ou 11 (celular), contando o DDD.");
            }

        } while (!Validadores.validarTelefone(telefone));

        return Validadores.somenteNumeros(telefone);
    }

    private static String lerData(String mensagem) {
        String data;

        do {
            System.out.print(mensagem);
            data = entrada.nextLine().trim();

            if (!Validadores.validarData(data)) {
                System.out.println(
                        "Data inválida. Use uma data real no formato DD/MM/AAAA.");
            }

        } while (!Validadores.validarData(data));

        return data;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();

            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException erro) {
                System.out.println(
                        "Digite um número inteiro válido.");
            }
        }
    }

    private static double lerDoublePositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            String texto = entrada.nextLine()
                    .trim()
                    .replace(',', '.');

            try {
                double valor = Double.parseDouble(texto);

                if (Double.isFinite(valor) && valor > 0) {
                    return valor;
                }

                System.out.println(
                        "O valor deve ser maior que zero.");

            } catch (NumberFormatException erro) {
                System.out.println(
                        "Digite um valor numérico, por exemplo 12,50.");
            }
        }
    }
}