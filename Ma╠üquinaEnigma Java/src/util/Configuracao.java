package util;

public class Configuracao {

    private String[] ordemRotores;
    private char[] posicoesIniciais;
    private char[] aneis;
    private String reflectorNome;
    private String plugboardPares;

    public Configuracao(String[] ordemRotores, char[] posicoesIniciais, char[] aneis,
                         String reflectorNome, String plugboardPares) {
        this.ordemRotores = ordemRotores;
        this.posicoesIniciais = posicoesIniciais;
        this.aneis = aneis;
        this.reflectorNome = reflectorNome;
        this.plugboardPares = plugboardPares;
    }

    public static Configuracao padrao() {
        return new Configuracao(
                new String[]{"I", "II", "III"},
                new char[]{'A', 'A', 'A'},
                new char[]{'A', 'A', 'A'},
                "B",
                ""
        );
    }

    public String[] getOrdemRotores() {
        return ordemRotores;
    }

    public char[] getPosicoesIniciais() {
        return posicoesIniciais;
    }

    public char[] getAneis() {
        return aneis;
    }

    public String getReflectorNome() {
        return reflectorNome;
    }

    public String getPlugboardPares() {
        return plugboardPares;
    }

    @Override
    public String toString() {
        return "Rotores: " + ordemRotores[0] + "-" + ordemRotores[1] + "-" + ordemRotores[2]
                + " | Posições: " + new String(posicoesIniciais)
                + " | Anéis: " + new String(aneis)
                + " | Refletor: " + reflectorNome
                + " | Plugboard: " + (plugboardPares.isBlank() ? "(nenhum)" : plugboardPares);
    }
}