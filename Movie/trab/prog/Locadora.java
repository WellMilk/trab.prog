package trab.prog;

import java.util.ArrayList;

public class Locadora {
    private String nome;
    private String endereco;
    private ArrayList<Pessoa> pessoas;
    private ArrayList<Filme> filmes;
    private ArrayList<Locacao> locacoes;
    private int proximoCodigoLocacao;

    public Locadora(String nome, String endereco) {
        setNome(nome);
        setEndereco(endereco);

        pessoas = new ArrayList<>();
        filmes = new ArrayList<>();
        locacoes = new ArrayList<>();

        proximoCodigoLocacao = 1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome da locadora não pode ficar vazio.");
        }
        this.nome = nome.trim();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O endereço não pode ficar vazio.");
        }
        this.endereco = endereco.trim();
    }

    // CRUD DE CLIENTES

    public boolean cadastrarCliente(Cliente cliente) {
        if (cliente == null
                || buscarClientePorCpf(cliente.getCpf()) != null) {
            return false;
        }

        pessoas.add(cliente);
        return true;
    }

    public Cliente buscarClientePorCpf(String cpf) {
        String cpfNormalizado = Validadores.somenteNumeros(cpf);

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Cliente) {
                Cliente cliente = (Cliente) pessoa;

                if (cliente.getCpf().equals(cpfNormalizado)) {
                    return cliente;
                }
            }
        }

        return null;
    }

    public void listarClientes() {
        boolean encontrou = false;

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Cliente) {
                System.out.println(pessoa);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }

    public boolean atualizarCliente(
            String cpf, String novoTelefone, String novoEmail) {

        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente == null) {
            return false;
        }

        cliente.setTelefone(novoTelefone);
        cliente.setEmail(novoEmail);

        return true;
    }

    public boolean removerCliente(String cpf) {
        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente == null || possuiLocacaoAberta(cliente)) {
            return false;
        }

        return pessoas.remove(cliente);
    }

    private boolean possuiLocacaoAberta(Cliente cliente) {
        for (Locacao locacao : locacoes) {
            if (locacao.getCliente() == cliente
                    && locacao.estaEmAberto()) {
                return true;
            }
        }

        return false;
    }

    // CRUD DE FUNCIONÁRIOS

    public boolean cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null
                || buscarFuncionarioPorMatricula(
                        funcionario.getMatricula()) != null) {
            return false;
        }

        pessoas.add(funcionario);
        return true;
    }

    public Funcionario buscarFuncionarioPorMatricula(
            String matricula) {

        if (matricula == null) {
            return null;
        }

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Funcionario) {
                Funcionario funcionario = (Funcionario) pessoa;

                if (funcionario.getMatricula()
                        .equalsIgnoreCase(matricula.trim())) {
                    return funcionario;
                }
            }
        }

        return null;
    }

    public void listarFuncionarios() {
        boolean encontrou = false;

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Funcionario) {
                System.out.println(pessoa);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum funcionário cadastrado.");
        }
    }

    public boolean atualizarFuncionario(
            String matricula, String novoTelefone, String novoCargo) {

        Funcionario funcionario =
                buscarFuncionarioPorMatricula(matricula);

        if (funcionario == null) {
            return false;
        }

        funcionario.setTelefone(novoTelefone);
        funcionario.setCargo(novoCargo);

        return true;
    }

    public boolean removerFuncionario(String matricula) {
        Funcionario funcionario =
                buscarFuncionarioPorMatricula(matricula);

        if (funcionario == null) {
            return false;
        }

        return pessoas.remove(funcionario);
    }

    // POLIMORFISMO

    public void listarTodasAsPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getTipo() + " | " + pessoa);
        }
    }

    // CRUD DE FILMES

    public boolean cadastrarFilme(Filme filme) {
        if (filme == null
                || buscarFilmePorTitulo(filme.getTitulo()) != null) {
            return false;
        }

        filmes.add(filme);
        return true;
    }

    public Filme buscarFilmePorTitulo(String titulo) {
        if (titulo == null) {
            return null;
        }

        for (Filme filme : filmes) {
            if (filme.getTitulo()
                    .equalsIgnoreCase(titulo.trim())) {
                return filme;
            }
        }

        return null;
    }

    public void listarFilmes() {
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }

        for (Filme filme : filmes) {
            System.out.println(filme);
        }
    }

    public boolean atualizarFilme(
            String titulo, String novoGenero,
            int novoAno, String novaDuracao) {

        Filme filme = buscarFilmePorTitulo(titulo);

        if (filme == null) {
            return false;
        }

        filme.setGenero(novoGenero);
        filme.setAno(novoAno);
        filme.setDuracao(novaDuracao);

        return true;
    }

    public boolean removerFilme(String titulo) {
        Filme filme = buscarFilmePorTitulo(titulo);

        if (filme == null || !filme.verificarDisponibilidade()) {
            return false;
        }

        return filmes.remove(filme);
    }

    // CRUD DE LOCAÇÕES

    public String realizarLocacao(
            String cpf, String titulo,
            String dataLocacao, double valor) {

        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente == null) {
            return "Cliente não encontrado. Cadastre o cliente antes de realizar a locação.";
        }

        Filme filme = buscarFilmePorTitulo(titulo);

        if (filme == null) {
            return "Filme não encontrado.";
        }

        if (!filme.verificarDisponibilidade()) {
            return "Este filme já está alugado e não está disponível.";
        }

        if (!Validadores.validarData(dataLocacao)) {
            return "Data inválida. Use o formato DD/MM/AAAA.";
        }

        if (!Double.isFinite(valor) || valor <= 0) {
            return "O valor da locação deve ser maior que zero.";
        }

        Locacao locacao = new Locacao(
                proximoCodigoLocacao,
                dataLocacao,
                valor,
                cliente,
                filme
        );

        locacoes.add(locacao);
        proximoCodigoLocacao++;

        filme.setDisponivel(false);

        return "Locação realizada com sucesso! Código da locação: "
                + locacao.getCodigo();
    }

    public Locacao buscarLocacaoPorCodigo(int codigo) {
        for (Locacao locacao : locacoes) {
            if (locacao.getCodigo() == codigo) {
                return locacao;
            }
        }

        return null;
    }

    public void listarLocacoes() {
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locação cadastrada.");
            return;
        }

        for (Locacao locacao : locacoes) {
            System.out.println(locacao);
        }
    }

    public boolean atualizarValorLocacao(
            int codigo, double novoValor) {

        Locacao locacao = buscarLocacaoPorCodigo(codigo);

        if (locacao == null
                || !Double.isFinite(novoValor)
                || novoValor <= 0) {
            return false;
        }

        locacao.setValor(novoValor);
        return true;
    }

    public boolean removerLocacao(int codigo) {
        Locacao locacao = buscarLocacaoPorCodigo(codigo);

        if (locacao == null) {
            return false;
        }

        if (locacao.estaEmAberto()) {
            locacao.getFilme().setDisponivel(true);
        }

        return locacoes.remove(locacao);
    }

    public String devolverFilme(
            String titulo, String dataDevolucao) {

        Filme filme = buscarFilmePorTitulo(titulo);

        if (filme == null) {
            return "Filme não encontrado.";
        }

        for (Locacao locacao : locacoes) {
            if (locacao.getFilme() == filme
                    && locacao.estaEmAberto()) {

                try {
                    locacao.registrarDevolucao(dataDevolucao);
                    return "Filme devolvido com sucesso!";
                } catch (IllegalArgumentException
                        | IllegalStateException erro) {
                    return erro.getMessage();
                }
            }
        }

        return "Não existe locação em aberto para esse filme.";
    }
}