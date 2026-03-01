package eda.ds;

import eda.adt.Cola;
import eda.exceptions.ColaVacia;
import java.util.Iterator;

/**
 * Implementacion de una cola usando un array circular.
 *
 * @param <E> tipo de dato que guarda la cola
 */
public class ColaArray<E> implements Cola<E> {
    
    private E[] array;
    private int primero; // Señala en qué posición está el primero de la fila
    private int talla;   // Cuánta gente hay en la fila

    /**
     * Crea una cola vacia.
     */
    @SuppressWarnings("unchecked")
    public ColaArray() {
        array = (E[]) new Object[4]; // Empezamos con caja de 5
        primero = 0;
        talla = 0;
    }

    /**
     * Mete un dato al final de la cola.
     *
     * @param dato elemento que se anade
     */
    @Override
    public void encolar(E dato) {
        // Si se llena, hacemos que crezca como pedía la práctica
        if (talla == array.length) {
            crecerArray();
        }
        // Calculamos la posición del último usando el truco circular
        int ultimo = (primero + talla) % array.length;
        array[ultimo] = dato;
        talla++;
    }

    /**
     * Quita el primer elemento de la cola.
     *
     * @throws ColaVacia si la cola esta vacia
     */
    @Override
    public void desencolar() throws ColaVacia {
        if (esVacia()) {
            throw new ColaVacia();
        }
        array[primero] = null; // Borramos al que acabamos de atender
        // Movemos la flecha del primero al siguiente (con truco circular)
        primero = (primero + 1) % array.length;
        talla--;
    }

    /**
     * Devuelve el primer elemento sin quitarlo.
     *
     * @return elemento del frente de la cola
     * @throws ColaVacia si la cola esta vacia
     */
    @Override
    public E primero() throws ColaVacia {
        if (esVacia()) {
            throw new ColaVacia();
        }
        return array[primero];
    }

    /**
     * Indica si la cola no tiene elementos.
     *
     * @return true si esta vacia
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

    // --- MAGIA PARA HACER LA CAJA MÁS GRANDE ---
    @SuppressWarnings("unchecked")
    private void crecerArray() {
        E[] cajaNueva = (E[]) new Object[array.length * 2];
        
        // Pasamos los elementos a la nueva caja poniéndolos rectos (desde la pos 0)
        for (int i = 0; i < talla; i++) {
            cajaNueva[i] = array[(primero + i) % array.length];
        }
        
        array = cajaNueva;
        primero = 0; // En la caja nueva, el primero vuelve a estar en la posición 0
    }

    /**
     * Devuelve un iterador para recorrer la cola en orden de salida.
     *
     * @return iterador de la cola
     */
    @Override
    public Iterator<E> iterator() {
        return new MiIteradorCola();
    }

    private class MiIteradorCola implements Iterator<E> {
        private int contador = 0; // Llevamos la cuenta de a cuántos hemos mirado

        @Override
        public boolean hasNext() {
            return contador < talla; // Quedan elementos si el contador es menor a la talla
        }

        @Override
        public E next() {
            // Miramos al actual usando el truco circular y avanzamos el contador
            E dato = array[(primero + contador) % array.length];
            contador++;
            return dato;
        }
    }
}
