package trab.prog;

public class Locacao {
    private int codigo;
    private String dataLocacao;
    private String dataDevolucao;
    private double valor;
    private Cliente cliente;
    private Filme filme;

    public Locacao(int codigo, String dataLocacao, double valor,
            Cliente cliente, Filme filme) {

        if (codigo <= 0) {
            throw new IllegalArgumentException(
                    "Código de locação inválido.");
        }

        if (!Validadores.validarData(dataLocacao)) {
            throw new IllegalArgumentException(
                    "Data de locação inválida. Use DD/MM/AAAA.");
        }

        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException(
                    "O valor da locação deve ser maior que zero.");
        }

        if (cliente == null || filme == null) {
            throw new IllegalArgumentException(
                    "A locação precisa de um cliente e um filme.");
        }

        this.codigo = codigo;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = "";
        this.valor = valor;
        this.cliente = cliente;
        this.filme = filme;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(String dataLocacao) {
        if (!Validadores.validarData(dataLocacao)) {
            throw new IllegalArgumentException(
                    "Data de locação inválida. Use DD/MM/AAAA.");
        }

        if (!dataDevolucao.isEmpty()
                && !Validadores.dataFinalNaoAnterior(
                        dataLocacao, dataDevolucao)) {
            throw new IllegalArgumentException(
                    "A data da locação não pode ser posterior à devolução.");
        }

        this.dataLocacao = dataLocacao;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        if (dataDevolucao == null || dataDevolucao.trim().isEmpty()) {
            this.dataDevolucao = "";
            return;
        }

        if (!Validadores.dataFinalNaoAnterior(
                dataLocacao, dataDevolucao)) {
            throw new IllegalArgumentException(
                    "A devolução deve ser uma data válida, igual ou posterior à locação.");
        }

        this.dataDevolucao = dataDevolucao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException(
                    "O valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Filme getFilme() {
        return filme;
    }

    public double calcularValor() {
        return valor;
    }

    public void registrarDevolucao(String dataDevolucao) {
        if (!this.dataDevolucao.isEmpty()) {
            throw new IllegalStateException(
                    "Esta locação já foi devolvida.");
        }

        setDataDevolucao(dataDevolucao);
        filme.setDisponivel(true);
    }

    public boolean estaEmAberto() {
        return dataDevolucao.isEmpty();
    }

    @Override
    public String toString() {
        String devolucao = dataDevolucao.isEmpty()
                ? "Pendente" : dataDevolucao;

        return "Código: " + codigo
                + " | Cliente: " + cliente.getNome()
                + " | Filme: " + filme.getTitulo()
                + " | Data da locação: " + dataLocacao
                + " | Data da devolução: " + devolucao
                + " | Valor: R$ "
                + String.format(
                        java.util.Locale.forLanguageTag("pt-BR"),
                        "%.2f", calcularValor());
    }
}