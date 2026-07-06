package model;
public class Rotor {

    private final String wiring;
    private final char notch;
    private int position;
    private int ringSetting;

    public Rotor(String wiring, char notch, int position, int ringSetting) {
        this.wiring = wiring;
        this.notch = notch;
        this.position = position;
        this.ringSetting = ringSetting;
    }

    public int forward(int c) {
        int shifted = mod26(c + position - ringSetting);
        int mapped = wiring.charAt(shifted) - 'A';
        return mod26(mapped - position + ringSetting);
    }

    public int backward(int c) {
        int shifted = mod26(c + position - ringSetting);
        int mapped = wiring.indexOf((char) ('A' + shifted));
        return mod26(mapped - position + ringSetting);
    }

    public boolean atNotch() {
        return (char) ('A' + position) == notch;
    }

    public void advance() {
        position = mod26(position + 1);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = mod26(position);
    }

    public void setRingSetting(int ringSetting) {
        this.ringSetting = mod26(ringSetting);
    }

    public char getPositionLetter() {
        return (char) ('A' + position);
    }

    private static int mod26(int x) {
        return ((x % 26) + 26) % 26;
    }
}