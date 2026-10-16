package Bla;

public class Locacao {

    private int codigo;
    private Cliente cliente;
    private Filme filme;
    private boolean devolvido;

    public Locacao(int codigo, Cliente cliente, Filme filme) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.filme = filme;
        this.devolvido = false;
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Filme getFilme() {
        return filme;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void devolver() {
        devolvido = true;
        filme.setDisponivel(true);
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo
                + " | Cliente: " + cliente.getNome()
                + " | Filme: " + filme.getTitulo()
                + " | Devolvido: " + (devolvido ? "Sim" : "Nao");
    }
}