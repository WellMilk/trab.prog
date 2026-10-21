package trab.prog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public final class Validadores {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    private Validadores() {
    }

    public static String somenteNumeros(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.replaceAll("\\D", "");
    }

    public static boolean validarCPF(String cpf) {
        String numero = somenteNumeros(cpf);

        if (numero.length() != 11
                || numero.matches("(\\d)\\1{10}")) {
            return false;
        }

        int primeiroDigito = calcularDigitoCPF(numero, 9);
        int segundoDigito = calcularDigitoCPF(numero, 10);

        return primeiroDigito
                == Character.getNumericValue(numero.charAt(9))
                && segundoDigito
                == Character.getNumericValue(numero.charAt(10));
    }

    private static int calcularDigitoCPF(String cpf, int quantidade) {
        int soma = 0;
        int peso = quantidade + 1;

        for (int i = 0; i < quantidade; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso;
            peso--;
        }

        int resto = (soma * 10) % 11;

        return resto == 10 ? 0 : resto;
    }

    public static String formatarCPF(String cpf) {
        String numero = somenteNumeros(cpf);

        if (numero.length() != 11) {
            return cpf;
        }

        return numero.substring(0, 3) + "."
                + numero.substring(3, 6) + "."
                + numero.substring(6, 9) + "-"
                + numero.substring(9, 11);
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()
                || email.contains(" ")) {
            return false;
        }

        return email.matches(
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean validarTelefone(String telefone) {
        String numero = somenteNumeros(telefone);

        return numero.length() == 10 || numero.length() == 11;
    }

    public static String formatarTelefone(String telefone) {
        String numero = somenteNumeros(telefone);

        if (numero.length() == 11) {
            return "(" + numero.substring(0, 2) + ") "
                    + numero.substring(2, 7) + "-"
                    + numero.substring(7, 11);
        }

        if (numero.length() == 10) {
            return "(" + numero.substring(0, 2) + ") "
                    + numero.substring(2, 6) + "-"
                    + numero.substring(6, 10);
        }

        return telefone;
    }

    public static boolean validarData(String data) {
        if (data == null) {
            return false;
        }

        try {
            LocalDate.parse(data, FORMATO_DATA);
            return true;
        } catch (DateTimeParseException erro) {
            return false;
        }
    }

    public static boolean dataFinalNaoAnterior(
            String dataInicial, String dataFinal) {

        if (!validarData(dataInicial)
                || !validarData(dataFinal)) {
            return false;
        }

        LocalDate inicio = LocalDate.parse(dataInicial, FORMATO_DATA);
        LocalDate fim = LocalDate.parse(dataFinal, FORMATO_DATA);

        return !fim.isBefore(inicio);
    }
}