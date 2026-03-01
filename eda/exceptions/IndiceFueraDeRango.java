package eda.exceptions;

public class IndiceFueraDeRango extends Exception {
    public IndiceFueraDeRango() {
        super("El índice indicado no es válido para esta lista");
    }
}