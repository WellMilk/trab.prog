package Bla;

public class Cliente extends Pessoa {

    private String cpf;

    public Cliente(String nome, String telefone, String cpf) {
        super(nome, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }

    @Override
    public String toString() {
        return super.toString() + " | CPF: " + cpf;
    }
}