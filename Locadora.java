package Bla;


import java.util.ArrayList;

public class Locadora {

    private ArrayList<Pessoa> pessoas;
    private ArrayList<Filme> filmes;
    private ArrayList<Locacao> locacoes;
    private int codigo;

    public Locadora() {
        pessoas = new ArrayList<>();
        filmes = new ArrayList<>();
        locacoes = new ArrayList<>();
        codigo = 1;
    }

    // ================= CLIENTES =================

    public boolean cadastrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getCpf()) != null) {
            return false;
        }
        pessoas.add(cliente);
        return true;
    }

    public Cliente buscarCliente(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Cliente) {
                Cliente c = (Cliente) pessoa;
                if (c.getCpf().equals(cpf)) {
                    return c;
                }
            }
        }
        return null;
    }

    public void listarClientes() {
        boolean achou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Cliente) {
                System.out.println(pessoa);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }

    public boolean atualizarCliente(String cpf, String novoNome, String novoTelefone) {
        Cliente c = buscarCliente(cpf);
        if (c == null) {
            return false;
        }
        c.setNome(novoNome);
        c.setTelefone(novoTelefone);
        return true;
    }

    public boolean removerCliente(String cpf) {
        Cliente c = buscarCliente(cpf);
        if (c == null) {
            return false;
        }
        for (Locacao l : locacoes) {
            if (l.getCliente() == c && !l.isDevolvido()) {
                return false;
            }
        }
        pessoas.remove(c);
        return true;
    }

    // ================= FUNCIONARIOS =================

    public void cadastrarFuncionario(Funcionario funcionario) {
        pessoas.add(funcionario);
    }

    public Funcionario buscarFuncionario(String nome) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Funcionario) {
                Funcionario f = (Funcionario) pessoa;
                if (f.getNome().equalsIgnoreCase(nome)) {
                    return f;
                }
            }
        }
        return null;
    }

    public void listarFuncionarios() {
        boolean achou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Funcionario) {
                System.out.println(pessoa);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhum funcionario cadastrado.");
        }
    }

    public boolean atualizarFuncionario(String nome, String novoTelefone, String novoCargo) {
        Funcionario f = buscarFuncionario(nome);
        if (f == null) {
            return false;
        }
        f.setTelefone(novoTelefone);
        f.setCargo(novoCargo);
        return true;
    }

    public boolean removerFuncionario(String nome) {
        Funcionario f = buscarFuncionario(nome);
        if (f == null) {
            return false;
        }
        pessoas.remove(f);
        return true;
    }

    // ================= POLIMORFISMO =================

    public void listarPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }
        for (Pessoa pessoa : pessoas) {
            System.out.println("[" + pessoa.getTipo() + "] " + pessoa);
        }
    }

    // ================= FILMES =================

    public boolean cadastrarFilme(Filme filme) {
        if (buscarFilme(filme.getTitulo()) != null) {
            return false;
        }
        filmes.add(filme);
        return true;
    }

    public Filme buscarFilme(String titulo) {
        for (Filme f : filmes) {
            if (f.getTitulo().equalsIgnoreCase(titulo)) {
                return f;
            }
        }
        return null;
    }

    public void listarFilmes() {
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }
        for (Filme f : filmes) {
            System.out.println(f);
        }
    }

    public boolean atualizarFilme(String titulo, String novoGenero, int novoAno) {
        Filme f = buscarFilme(titulo);
        if (f == null) {
            return false;
        }
        f.setGenero(novoGenero);
        f.setAno(novoAno);
        return true;
    }

    public boolean removerFilme(String titulo) {
        Filme f = buscarFilme(titulo);
        if (f == null || !f.isDisponivel()) {
            return false;
        }
        filmes.remove(f);
        return true;
    }

    // ================= LOCACOES =================

    public String realizarLocacao(String cpf, String titulo) {
        Cliente cliente = buscarCliente(cpf);
        if (cliente == null) {
            return "Cliente nao encontrado.";
        }

        Filme filme = buscarFilme(titulo);
        if (filme == null) {
            return "Filme nao encontrado.";
        }

        if (!filme.isDisponivel()) {
            return "Filme indisponivel.";
        }

        Locacao locacao = new Locacao(codigo, cliente, filme);
        locacoes.add(locacao);
        filme.setDisponivel(false);
        codigo++;

        return "Locacao realizada com sucesso! Codigo: " + locacao.getCodigo();
    }

    public String devolverFilme(String titulo) {
        for (Locacao locacao : locacoes) {
            if (locacao.getFilme().getTitulo().equalsIgnoreCase(titulo)
                    && !locacao.isDevolvido()) {
                locacao.devolver();
                return "Filme devolvido com sucesso!";
            }
        }
        return "Locacao em aberto nao encontrada.";
    }

    public Locacao buscarLocacao(int codigoLocacao) {
        for (Locacao l : locacoes) {
            if (l.getCodigo() == codigoLocacao) {
                return l;
            }
        }
        return null;
    }

    public void listarLocacoes() {
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locacao registrada.");
            return;
        }
        for (Locacao l : locacoes) {
            System.out.println(l);
        }
    }

    public boolean removerLocacao(int codigoLocacao) {
        Locacao l = buscarLocacao(codigoLocacao);
        if (l == null) {
            return false;
        }
        if (!l.isDevolvido()) {
            l.getFilme().setDisponivel(true);
        }
        locacoes.remove(l);
        return true;
    }
}