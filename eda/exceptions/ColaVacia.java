package eda.exceptions;

public class ColaVacia extends Exception {
    public ColaVacia() {
        super("La cola está vacía");
    }
}