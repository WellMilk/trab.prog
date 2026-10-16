package Bla;


public class Funcionario extends Pessoa {

    private String cargo;

    public Funcionario(String nome, String telefone, String cargo) {
        super(nome, telefone);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String getTipo() {
        return "Funcionario";
    }

    @Override
    public String toString() {
        return super.toString() + " | Cargo: " + cargo;
    }
}