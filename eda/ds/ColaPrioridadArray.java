package eda.ds;

import eda.adt.Cola;
import eda.exceptions.ColaVacia;
import java.util.Iterator;

/**
 * Cola de prioridad implementada con array ordenado.
 * El elemento con mas prioridad se guarda al final.
 *
 * @param <E> tipo de dato comparable que se guarda
 */
public class ColaPrioridadArray<E extends Comparable<E>> implements Cola<E> {
    
    private E[] array;
    private int talla;

    /**
     * Crea una cola de prioridad vacia.
     */
    @SuppressWarnings("unchecked")
    public ColaPrioridadArray() {
        array = (E[]) new Comparable[2];
        talla = 0;
    }

    /**
     * Inserta un dato manteniendo el array ordenado.
     *
     * @param dato elemento que se quiere anadir
     */
    @Override
    public void encolar(E dato) {
        // si se llena, hacemos el array mas grande
        if (talla == array.length) {
            crecerArray();
        }

        // insertamos dejando todo ordenado
        int i = talla - 1;
        
        while (i >= 0 && array[i].compareTo(dato) > 0) {
            array[i + 1] = array[i];
            i--;
        }
        
        array[i + 1] = dato;
        talla++;
    }

    /**
     * Quita el elemento con mayor prioridad.
     *
     * @throws ColaVacia si no hay elementos
     */
    @Override
    public void desencolar() throws ColaVacia {
        if (esVacia()) {
            throw new ColaVacia();
        }
        // quitamos el de mayor prioridad (esta al final)
        talla--;
        array[talla] = null;
    }

    /**
     * Devuelve el elemento con mayor prioridad sin quitarlo.
     *
     * @return elemento de mayor prioridad
     * @throws ColaVacia si no hay elementos
     */
    @Override
    public E primero() throws ColaVacia {
        if (esVacia()) {
            throw new ColaVacia();
        }
        // el primero logico queda al final del tramo usado
        return array[talla - 1];
    }

    /**
     * Indica si la cola de prioridad esta vacia.
     *
     * @return true si no hay elementos
     */
    @Override
    public boolean esVacia() {
        return talla == 0;
    }

    /**
     * Devuelve cuantos elementos hay en la cola.
     *
     * @return numero de elementos
     */
    @Override
    public int talla() {
        return talla;
    }

    @SuppressWarnings("unchecked")
    private void crecerArray() {
        E[] cajaNueva = (E[]) new Comparable[array.length * 2];
        for (int i = 0; i < talla; i++) {
            cajaNueva[i] = array[i];
        }
        array = cajaNueva;
    }

    /**
     * Devuelve un iterador de mayor a menor prioridad.
     *
     * @return iterador de la cola
     */
    @Override
    public Iterator<E> iterator() {
        return new MiIteradorVIP();
    }

    private class MiIteradorVIP implements Iterator<E> {
        // recorre de mayor a menor prioridad
        private int pos = talla - 1;

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public E next() {
            E dato = array[pos];
            pos--;
            return dato;
        }
    }
}
