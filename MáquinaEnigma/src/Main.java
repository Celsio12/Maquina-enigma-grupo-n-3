import java.util.Scanner;
import model.Enigma;
import model.Plugboard;
import model.Reflector;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            cli();
        } else {
            correrTestesObrigatorios();
        }
    }

    private static void correrTestesObrigatorios() {
        System.out.println("=== Caso de teste 1: configuração histórica I ===");

        Enigma m1 = new Enigma(
                new String[]{"I", "II", "III"},
                new char[]{'A', 'A', 'A'},
                new char[]{'A', 'A', 'A'},
                "B", "");
        String entrada1 = "AAAAA";
        String saida1 = m1.cifrarMensagem(entrada1);
        String esperado1 = "BDZGO";
        System.out.println("Entrada:   " + entrada1);
        System.out.println("Saída:     " + saida1);
        System.out.println("Esperado:  " + esperado1);
        System.out.println("Resultado: " + (saida1.equals(esperado1) ? "PASSOU" : "FALHOU"));

        System.out.println();
        Enigma m1b = new Enigma(
                new String[]{"I", "II", "III"},
                new char[]{'A', 'A', 'A'},
                new char[]{'A', 'A', 'A'},
                "B", "");
        String entrada1b = "AAAAAAAAAA";
        String saida1b = m1b.cifrarMensagem(entrada1b);
        System.out.println("Entrada: " + entrada1b);
        System.out.println("Saída:   " + saida1b);

        System.out.println();
        System.out.println("=== Caso de teste 2: auto-inversão ===");
        String mensagemPropria = "ISTOEUMAMENSAGEMDETESTEDOGRUPO";
        String[] ordem = {"III", "I", "II"};
        char[] posicoes = {'Q', 'E', 'V'};
        char[] aneis = {'A', 'B', 'C'};
        String plug = "AT BS DE FM";

        Enigma cifra = new Enigma(ordem, posicoes, aneis, "B", plug);
        String textoCifrado = cifra.cifrarMensagem(mensagemPropria);

        Enigma decifra = new Enigma(ordem, posicoes, aneis, "B", plug);
        String textoDecifrado = decifra.cifrarMensagem(textoCifrado);

        System.out.println("Mensagem original: " + mensagemPropria);
        System.out.println("Texto cifrado:     " + textoCifrado);
        System.out.println("Texto decifrado:   " + textoDecifrado);
        System.out.println("Resultado: " + (textoDecifrado.equals(mensagemPropria) ? "PASSOU" : "FALHOU"));

        System.out.println();
        Reflector refB = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        System.out.println("sigma^2 = Id ? " + refB.isInvolution());

        System.out.println();
        for (int n = 0; n <= 13; n += 2) {
            System.out.println(n + " pares -> " + Plugboard.numeroConfiguracoes(n) + " configurações");
        }
    }

    private static void cli() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Rotor esquerdo (I,II,III): ");
        String rE = sc.nextLine().trim().toUpperCase();
        System.out.print("Rotor do meio: ");
        String rM = sc.nextLine().trim().toUpperCase();
        System.out.print("Rotor direito: ");
        String rD = sc.nextLine().trim().toUpperCase();

        System.out.print("Posições iniciais (3 letras, ex. AAA): ");
        String pos = sc.nextLine().trim().toUpperCase();
        System.out.print("Anéis (3 letras, ex. AAA): ");
        String rings = sc.nextLine().trim().toUpperCase();

        System.out.print("Refletor (B ou C): ");
        String ref = sc.nextLine().trim().toUpperCase();

        System.out.print("Plugboard (ex. AB CD, ou vazio): ");
        String plug = sc.nextLine().trim();

        Enigma enigma = new Enigma(
                new String[]{rE, rM, rD},
                new char[]{pos.charAt(0), pos.charAt(1), pos.charAt(2)},
                new char[]{rings.charAt(0), rings.charAt(1), rings.charAt(2)},
                ref, plug);

        System.out.println("\nConfiguração pronta. Escreva mensagens (linha vazia para sair).");
        while (true) {
            System.out.print("> ");
            String linha = sc.nextLine();
            if (linha.isBlank()) break;
            System.out.println("Resultado: " + enigma.cifrarMensagem(linha));
            System.out.println("(rotores agora: " + enigma.getEstadoRotores() + ")");
        }
        sc.close();
    }
}