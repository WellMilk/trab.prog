package trab.prog;
import java.util.Scanner;
public class Main {


	    private static Scanner entrada = new Scanner(System.in);
	    private static Locadora locadora = new Locadora();

	    public static void main(String[] args) {
	        int opcao;

	        do {
	            mostrarMenu();
	            opcao = lerInteiro("Escolha: ");

	            switch (opcao) {
	                case 1:
	                    cadastrarCliente();
	                    break;
	                case 2:
	                    locadora.listarClientes();
	                    break;
	                case 3:
	                    buscarCliente();
	                    break;
	                case 4:
	                    atualizarCliente();
	                    break;
	                case 5:
	                    removerCliente();
	                    break;
	                case 6:
	                    cadastrarFuncionario();
	                    break;
	                case 7:
	                    locadora.listarFuncionarios();
	                    break;
	                case 8:
	                    atualizarFuncionario();
	                    break;
	                case 9:
	                    removerFuncionario();
	                    break;
	                case 10:
	                    locadora.listarPessoas();
	                    break;
	                case 11:
	                    cadastrarFilme();
	                    break;
	                case 12:
	                    locadora.listarFilmes();
	                    break;
	                case 13:
	                    buscarFilme();
	                    break;
	                case 14:
	                    atualizarFilme();
	                    break;
	                case 15:
	                    removerFilme();
	                    break;
	                case 16:
	                    alugarFilme();
	                    break;
	                case 17:
	                    devolverFilme();
	                    break;
	                case 18:
	                    locadora.listarLocacoes();
	                    break;
	                case 19:
	                    buscarLocacao();
	                    break;
	                case 20:
	                    removerLocacao();
	                    break;
	                case 0:
	                    System.out.println("Saindo do sistema...");
	                    break;
	                default:
	                    System.out.println("Opcao invalida!");
	            }
	        } while (opcao != 0);

	        entrada.close();
	    }

	    private static void mostrarMenu() {
	        System.out.println("\n=========== MOVIELOC ===========");
	        System.out.println("--- CLIENTES ---");
	        System.out.println("1  - Cadastrar cliente");
	        System.out.println("2  - Listar clientes");
	        System.out.println("3  - Buscar cliente por CPF");
	        System.out.println("4  - Atualizar cliente");
	        System.out.println("5  - Remover cliente");
	        System.out.println("--- FUNCIONARIOS ---");
	        System.out.println("6  - Cadastrar funcionario");
	        System.out.println("7  - Listar funcionarios");
	        System.out.println("8  - Atualizar funcionario");
	        System.out.println("9  - Remover funcionario");
	        System.out.println("10 - Listar todas as pessoas (polimorfismo)");
	        System.out.println("--- FILMES ---");
	        System.out.println("11 - Cadastrar filme");
	        System.out.println("12 - Listar filmes");
	        System.out.println("13 - Buscar filme por titulo");
	        System.out.println("14 - Atualizar filme");
	        System.out.println("15 - Remover filme");
	        System.out.println("--- LOCACOES ---");
	        System.out.println("16 - Alugar filme");
	        System.out.println("17 - Devolver filme");
	        System.out.println("18 - Listar locacoes");
	        System.out.println("19 - Buscar locacao por codigo");
	        System.out.println("20 - Remover locacao");
	        System.out.println("0  - Sair");
	    }

	    // ================= CLIENTES =================

	    private static void cadastrarCliente() {
	        String nome = lerTexto("Nome: ");
	        String telefone = lerTexto("Telefone: ");
	        String cpf = lerTexto("CPF: ");

	        Cliente cliente = new Cliente(nome, telefone, cpf);

	        if (locadora.cadastrarCliente(cliente)) {
	            System.out.println("Cliente cadastrado com sucesso!");
	        } else {
	            System.out.println("Ja existe um cliente com esse CPF.");
	        }
	    }

	    private static void buscarCliente() {
	        String cpf = lerTexto("CPF do cliente: ");
	        Cliente cliente = locadora.buscarCliente(cpf);

	        if (cliente == null) {
	            System.out.println("Cliente nao encontrado.");
	        } else {
	            System.out.println(cliente);
	        }
	    }

	    private static void atualizarCliente() {
	        String cpf = lerTexto("CPF do cliente: ");

	        if (locadora.buscarCliente(cpf) == null) {
	            System.out.println("Cliente nao encontrado.");
	            return;
	        }

	        String nome = lerTexto("Novo nome: ");
	        String telefone = lerTexto("Novo telefone: ");

	        locadora.atualizarCliente(cpf, nome, telefone);
	        System.out.println("Cliente atualizado com sucesso!");
	    }

	    private static void removerCliente() {
	        String cpf = lerTexto("CPF do cliente: ");

	        if (locadora.removerCliente(cpf)) {
	            System.out.println("Cliente removido com sucesso!");
	        } else {
	            System.out.println("Cliente nao encontrado ou possui locacao em aberto.");
	        }
	    }

	    // ================= FUNCIONARIOS =================

	    private static void cadastrarFuncionario() {
	        String nome = lerTexto("Nome: ");
	        String telefone = lerTexto("Telefone: ");
	        String cargo = lerTexto("Cargo: ");

	        locadora.cadastrarFuncionario(new Funcionario(nome, telefone, cargo));
	        System.out.println("Funcionario cadastrado com sucesso!");
	    }

	    private static void atualizarFuncionario() {
	        String nome = lerTexto("Nome do funcionario: ");

	        if (locadora.buscarFuncionario(nome) == null) {
	            System.out.println("Funcionario nao encontrado.");
	            return;
	        }

	        String telefone = lerTexto("Novo telefone: ");
	        String cargo = lerTexto("Novo cargo: ");

	        locadora.atualizarFuncionario(nome, telefone, cargo);
	        System.out.println("Funcionario atualizado com sucesso!");
	    }

	    private static void removerFuncionario() {
	        String nome = lerTexto("Nome do funcionario: ");

	        if (locadora.removerFuncionario(nome)) {
	            System.out.println("Funcionario removido com sucesso!");
	        } else {
	            System.out.println("Funcionario nao encontrado.");
	        }
	    }

	    // ================= FILMES =================

	    private static void cadastrarFilme() {
	        String titulo = lerTexto("Titulo: ");
	        String genero = lerTexto("Genero: ");
	        int ano = lerInteiro("Ano: ");

	        if (locadora.cadastrarFilme(new Filme(titulo, genero, ano))) {
	            System.out.println("Filme cadastrado com sucesso!");
	        } else {
	            System.out.println("Ja existe um filme com esse titulo.");
	        }
	    }

	    private static void buscarFilme() {
	        String titulo = lerTexto("Titulo do filme: ");
	        Filme filme = locadora.buscarFilme(titulo);

	        if (filme == null) {
	            System.out.println("Filme nao encontrado.");
	        } else {
	            System.out.println(filme);
	        }
	    }

	    private static void atualizarFilme() {
	        String titulo = lerTexto("Titulo do filme: ");

	        if (locadora.buscarFilme(titulo) == null) {
	            System.out.println("Filme nao encontrado.");
	            return;
	        }

	        String genero = lerTexto("Novo genero: ");
	        int ano = lerInteiro("Novo ano: ");

	        locadora.atualizarFilme(titulo, genero, ano);
	        System.out.println("Filme atualizado com sucesso!");
	    }

	    private static void removerFilme() {
	        String titulo = lerTexto("Titulo do filme: ");

	        if (locadora.removerFilme(titulo)) {
	            System.out.println("Filme removido com sucesso!");
	        } else {
	            System.out.println("Filme nao encontrado ou esta alugado.");
	        }
	    }

	    // ================= LOCACOES =================

	    private static void alugarFilme() {
	        String cpf = lerTexto("CPF do cliente: ");
	        String titulo = lerTexto("Titulo do filme: ");

	        System.out.println(locadora.realizarLocacao(cpf, titulo));
	    }

	    private static void devolverFilme() {
	        String titulo = lerTexto("Titulo do filme: ");

	        System.out.println(locadora.devolverFilme(titulo));
	    }

	    private static void buscarLocacao() {
	        int codigo = lerInteiro("Codigo da locacao: ");
	        Locacao locacao = locadora.buscarLocacao(codigo);

	        if (locacao == null) {
	            System.out.println("Locacao nao encontrada.");
	        } else {
	            System.out.println(locacao);
	        }
	    }

	    private static void removerLocacao() {
	        int codigo = lerInteiro("Codigo da locacao: ");

	        if (locadora.removerLocacao(codigo)) {
	            System.out.println("Locacao removida com sucesso!");
	        } else {
	            System.out.println("Locacao nao encontrada.");
	        }
	    }

	    // ================= LEITURA =================

	    private static String lerTexto(String mensagem) {
	        String texto;
	        do {
	            System.out.print(mensagem);
	            texto = entrada.nextLine().trim();
	            if (texto.isEmpty()) {
	                System.out.println("Campo nao pode ficar vazio.");
	            }
	        } while (texto.isEmpty());
	        return texto;
	    }

	    private static int lerInteiro(String mensagem) {
	        while (true) {
	            System.out.print(mensagem);
	            try {
	                return Integer.parseInt(entrada.nextLine().trim());
	            } catch (NumberFormatException e) {
	                System.out.println("Digite um numero inteiro valido.");
	            }
	        }
	    }
	}
}
