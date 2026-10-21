package trab.prog;

public class Filme {
    private String titulo;
    private String genero;
    private int ano;
    private String duracao;
    private boolean disponivel;

    public Filme(String titulo, String genero, int ano, String duracao) {
        setTitulo(titulo);
        setGenero(genero);
        setAno(ano);
        setDuracao(duracao);
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O título não pode ficar vazio.");
        }
        this.titulo = titulo.trim();
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O gênero não pode ficar vazio.");
        }
        this.genero = genero.trim();
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano < 1888 || ano > 2100) {
            throw new IllegalArgumentException(
                    "Informe um ano entre 1888 e 2100.");
        }
        this.ano = ano;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        if (duracao == null || duracao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A duração não pode ficar vazia.");
        }
        this.duracao = duracao.trim();
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean verificarDisponibilidade() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Título: " + titulo
                + " | Gênero: " + genero
                + " | Ano: " + ano
                + " | Duração: " + duracao
                + " | Disponível: " + (disponivel ? "Sim" : "Não");
    }
}