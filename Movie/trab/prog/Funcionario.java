package trab.prog;

public class Funcionario extends Pessoa {
    private String matricula;
    private String cargo;

    public Funcionario(String nome, String telefone,
            String matricula, String cargo) {
        super(nome, telefone);
        setMatricula(matricula);
        setCargo(cargo);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A matrícula não pode ficar vazia.");
        }
        this.matricula = matricula.trim();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O cargo não pode ficar vazio.");
        }
        this.cargo = cargo.trim();
    }

    @Override
    public String getTipo() {
        return "Funcionário";
    }

    @Override
    public String toString() {
        return dadosBasicos()
                + " | Matrícula: " + matricula
                + " | Cargo: " + cargo;
    }
}