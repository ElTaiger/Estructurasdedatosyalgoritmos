package eda.ds;

import eda.adt.Pila;
import eda.exceptions.PilaVacia;
import java.util.Iterator;

/**
 * Implementacion de una pila usando un array dinamico.
 *
 * @param <E> tipo de dato que guarda la pila
 */
public class PilaArray<E> implements Pila<E> {
    
    private E[] array; // Nuestra "caja" con huecos
    private int talla; // Cuántos platos llevamos apilados

    /**
     * Crea una pila vacia con capacidad inicial pequena.
     */
    @SuppressWarnings("unchecked")
    public PilaArray() {
        // Empezamos con una caja pequeñita de 5 huecos
        array = (E[]) new Object[3];
        talla = 0;
    }

    /**
     * Mete un elemento en el tope de la pila.
     *
     * @param dato elemento que se guarda
     */
    @Override
    public void apilar(E dato) {
        // ¡EL TRUCO DEL ARRAY QUE CRECE!
        // Si los platos llegan al tope de la caja, hacemos una más grande
        if (talla == array.length) {
            crecerArray();
        }
        // Guardamos el plato y sumamos 1 a la talla
        array[talla] = dato;
        talla++;
    }

    /**
     * Quita el elemento que esta en el tope.
     *
     * @throws PilaVacia si la pila no tiene elementos
     */
    @Override
    public void desapilar() throws PilaVacia {
        if (esVacia()) {
            throw new PilaVacia(); // Nos quejamos si intentan sacar de donde no hay
        }
        talla--; // Restamos 1 a la talla (esto "borra" el último plato virtualmente)
        array[talla] = null; // Lo borramos de verdad para no gastar memoria
    }

    /**
     * Devuelve el elemento que esta arriba sin quitarlo.
     *
     * @return elemento en el tope
     * @throws PilaVacia si la pila no tiene elementos
     */
    @Override
    public E tope() throws PilaVacia {
        if (esVacia()) {
            throw new PilaVacia();
        }
        // Devolvemos el último plato que apilamos (posición talla - 1)
        return array[talla - 1];
    }

    /**
     * Indica si la pila esta vacia.
     *
     * @return true si no hay elementos
     */
    @Override
    public boolean esVacia() {
        return talla == 0;
    }

    /**
     * Devuelve cuantos elementos hay en la pila.
     *
     * @return numero de elementos
     */
    @Override
    public int talla() {
        return talla;
    }

    // --- MÉTODO PRIVADO (OCULTO): MAGIA PARA HACER LA CAJA MÁS GRANDE ---
    @SuppressWarnings("unchecked")
    private void crecerArray() {
        // Creamos una caja nueva que sea el DOBLE de grande [4, 5]
        E[] cajaNueva = (E[]) new Object[array.length * 2]; 
        
        // Pasamos los platos de la caja vieja a la nueva uno por uno
        for (int i = 0; i < array.length; i++) {
            cajaNueva[i] = array[i]; 
        }
        // Cambiamos la caja vieja por la nueva
        array = cajaNueva; 
    }

    /**
     * Devuelve un iterador para recorrer la pila del tope hacia abajo.
     *
     * @return iterador de la pila
     */
    @Override
    public Iterator<E> iterator() {
        return new MiIteradorPila(); // Entregamos nuestro iterador personalizado [6, 7]
    }

    // Una mini-clase interna que sabe cómo recorrer nuestra Pila [7]
    private class MiIteradorPila implements Iterator<E> {
        // Como es una pila, empezamos a mirar por el último plato que entró (arriba)
        private int posicionActual = talla - 1; 

        @Override
        public boolean hasNext() {
            // Quedan elementos mientras la posición no baje de 0 [8]
            return posicionActual >= 0; 
        }

        @Override
        public E next() {
            E dato = array[posicionActual]; // Miramos el plato actual [8]
            posicionActual--; // Bajamos el dedo al siguiente plato para la próxima vez [8]
            return dato;
        }
    }
}
