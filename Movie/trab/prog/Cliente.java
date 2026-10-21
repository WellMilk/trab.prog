package trab.prog;

public class Cliente extends Pessoa {
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String telefone, String email) {
        super(nome, telefone);
        setCpf(cpf);
        setEmail(email);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!Validadores.validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = Validadores.somenteNumeros(cpf);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Validadores.validarEmail(email)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = email.trim();
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }

    @Override
    public String toString() {
        return dadosBasicos() + " | CPF: "
                + Validadores.formatarCPF(cpf)
                + " | E-mail: " + email;
    }
}