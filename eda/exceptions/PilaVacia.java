package eda.exceptions;

public class PilaVacia extends Exception {
    public PilaVacia() {
        super("La pila está vacía");
    }
}