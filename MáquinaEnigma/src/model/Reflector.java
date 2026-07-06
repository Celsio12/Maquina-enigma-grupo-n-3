package model;

public class Reflector {

    private final String wiring;

    public Reflector(String wiring) {
        this.wiring = wiring;
    }

    public int reflect(int c) {
        return wiring.charAt(c) - 'A';
    }

    public boolean isInvolution() {
        for (int i = 0; i < 26; i++) {
            if (reflect(reflect(i)) != i) {
                return false;
            }
            if (reflect(i) == i) {
                return false;
            }
        }
        return true;
    }

    public String getWiring() {
        return wiring;
    }
}