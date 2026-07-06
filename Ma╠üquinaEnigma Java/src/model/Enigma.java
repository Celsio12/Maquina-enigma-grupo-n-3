package model;
import java.util.HashMap;
import java.util.Map;

public class Enigma {

    private static final Map<String, String> WIRINGS = new HashMap<>();
    private static final Map<String, Character> NOTCHES = new HashMap<>();
    private static final Map<String, String> REFLECTORS = new HashMap<>();

    static {
        WIRINGS.put("I", "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        WIRINGS.put("II", "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        WIRINGS.put("III", "BDFHJLCPRTXVZNYEIWGAKMUSQO");

        NOTCHES.put("I", 'Q');
        NOTCHES.put("II", 'E');
        NOTCHES.put("III", 'V');

        REFLECTORS.put("B", "YRUHQSLDPXNGOKMIEBFZCWVJAT");
        REFLECTORS.put("C", "FVPJIAOYEDRZXWGCTKUQSBNMHL");
    }

    private final Rotor rotorEsquerdo;
    private final Rotor rotorMeio;
    private final Rotor rotorDireito;
    private final Reflector reflector;
    private final Plugboard plugboard;

    public Enigma(String[] ordemRotores, char[] posicoesIniciais, char[] aneis,
                  String reflectorNome, String plugboardPares) {
        rotorEsquerdo = criarRotor(ordemRotores[0], posicoesIniciais[0], aneis[0]);
        rotorMeio = criarRotor(ordemRotores[1], posicoesIniciais[1], aneis[1]);
        rotorDireito = criarRotor(ordemRotores[2], posicoesIniciais[2], aneis[2]);

        reflector = new Reflector(REFLECTORS.get(reflectorNome.toUpperCase()));
        plugboard = new Plugboard(plugboardPares);
    }

    private static Rotor criarRotor(String nome, char posicao, char anel) {
        String wiring = WIRINGS.get(nome.toUpperCase());
        char notch = NOTCHES.get(nome.toUpperCase());
        return new Rotor(wiring, notch, posicao - 'A', anel - 'A');
    }

    private void avancarRotores() {
        boolean meioNoEntalhe = rotorMeio.atNotch();
        boolean direitoNoEntalhe = rotorDireito.atNotch();

        if (meioNoEntalhe) {
            rotorEsquerdo.advance();
            rotorMeio.advance();
        } else if (direitoNoEntalhe) {
            rotorMeio.advance();
        }
        rotorDireito.advance();
    }

    public char cifrarLetra(char letra) {
        avancarRotores();

        int c = letra - 'A';
        c = plugboard.swap(c);
        c = rotorDireito.forward(c);
        c = rotorMeio.forward(c);
        c = rotorEsquerdo.forward(c);
        c = reflector.reflect(c);
        c = rotorEsquerdo.backward(c);
        c = rotorMeio.backward(c);
        c = rotorDireito.backward(c);
        c = plugboard.swap(c);

        return (char) ('A' + c);
    }

    public String cifrarMensagem(String mensagem) {
        StringBuilder sb = new StringBuilder();
        for (char ch : mensagem.toUpperCase().toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                sb.append(cifrarLetra(ch));
            }
        }
        return sb.toString();
    }

    public String getEstadoRotores() {
        return "" + rotorEsquerdo.getPositionLetter()
                + rotorMeio.getPositionLetter()
                + rotorDireito.getPositionLetter();
    }

    public Reflector getReflector() {
        return reflector;
    }
}