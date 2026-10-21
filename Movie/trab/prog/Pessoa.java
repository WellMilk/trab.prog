package trab.prog;


public abstract class Pessoa {
    private String nome;
    private String telefone;

    public Pessoa(String nome, String telefone) {
        setNome(nome);
        setTelefone(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome não pode ficar vazio.");
        }
        this.nome = nome.trim();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (!Validadores.validarTelefone(telefone)) {
            throw new IllegalArgumentException(
                    "O telefone deve possuir 10 ou 11 dígitos, contando o DDD.");
        }
        this.telefone = Validadores.somenteNumeros(telefone);
    }

    public abstract String getTipo();

    protected String dadosBasicos() {
        return "Nome: " + nome + " | Telefone: "
                + Validadores.formatarTelefone(telefone);
    }
}