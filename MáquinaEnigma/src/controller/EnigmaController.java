package controller;

import model.Enigma;
import model.Reflector;
import model.Plugboard;
import util.Configuracao;

public class EnigmaController {

    public String cifrarMensagem(Configuracao config, String mensagem) {
        Enigma enigma = criarEnigma(config);
        return enigma.cifrarMensagem(mensagem);
    }

    public boolean verificarAutoInversao(Configuracao config, String mensagem) {
        Enigma cifra = criarEnigma(config);
        String cifrado = cifra.cifrarMensagem(mensagem);

        Enigma decifra = criarEnigma(config);
        String decifrado = decifra.cifrarMensagem(cifrado);

        return decifrado.equals(mensagem.toUpperCase().replaceAll("[^A-Z]", ""));
    }

    public String correrCasoTesteHistorico() {
        Configuracao config = Configuracao.padrao();
        return cifrarMensagem(config, "AAAAA");
    }

    public boolean verificarReflectorInvolutivo(String reflectorNome) {
        String wiring = reflectorNome.equalsIgnoreCase("C")
                ? "FVPJIAOYEDRZXWGCTKUQSBNMHL"
                : "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        return new Reflector(wiring).isInvolution();
    }

    public long numeroConfiguracoesPlugboard(int numeroPares) {
        return Plugboard.numeroConfiguracoes(numeroPares);
    }

    private Enigma criarEnigma(Configuracao config) {
        return new Enigma(
                config.getOrdemRotores(),
                config.getPosicoesIniciais(),
                config.getAneis(),
                config.getReflectorNome(),
                config.getPlugboardPares()
        );
    }
}