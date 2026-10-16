package trab.prog;

public class Filme {

    private String titulo;
    private String genero;
    private int ano;
    private boolean disponivel;

    public Filme(String titulo, String genero, int ano) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Filme: " + titulo
                + " | Genero: " + genero
                + " | Ano: " + ano
                + " | Disponivel: " + (disponivel ? "Sim" : "Nao");
    }
}