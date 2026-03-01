package eda.solutions;

import eda.ds.ColaPrioridadArray;
import eda.ds.ListaEnlazadaDummy;
import eda.exceptions.IndiceFueraDeRango;

public class TestEstructuras {

    public static void main(String[] args) throws IndiceFueraDeRango {
        ListaEnlazadaDummy<String> lista = new ListaEnlazadaDummy<>();
        lista.insertar(0, "uno");
        lista.insertar(1, "dos");
        lista.insertar(2, "tres");

        ColaPrioridadArray<Integer> cola = new ColaPrioridadArray<>();
        cola.encolar(4);
        cola.encolar(1);
        cola.encolar(3);

        System.out.println("ListaEnlazadaDummy:");
        for (String dato : lista) {
            System.out.println(dato);
        }

        System.out.println("ColaPrioridadArray:");
        for (Integer dato : cola) {
            System.out.println(dato);
        }
    }
}
