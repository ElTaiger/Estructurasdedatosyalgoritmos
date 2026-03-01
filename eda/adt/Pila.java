package eda.adt;

import eda.exceptions.PilaVacia;

/**
 * Interfaz basica de una pila (LIFO: lo ultimo en entrar es lo primero en salir).
 *
 * @param <E> tipo de dato que se guarda en la pila
 */
public interface Pila<E> extends Iterable<E> {
    /**
     * Mete un dato en la parte de arriba.
     *
     * @param dato elemento que se quiere guardar
     */
    void apilar(E dato);

    /**
     * Quita el dato que esta arriba de todo.
     *
     * @throws PilaVacia si no hay elementos
     */
    void desapilar() throws PilaVacia;

    /**
     * Devuelve el dato que esta arriba sin quitarlo.
     *
     * @return elemento que esta en el tope
     * @throws PilaVacia si no hay elementos
     */
    E tope() throws PilaVacia;

    /**
     * Dice si la pila esta vacia.
     *
     * @return true si no hay datos, false si hay al menos uno
     */
    boolean esVacia();

    /**
     * Devuelve cuantos elementos hay guardados.
     *
     * @return numero de elementos actuales
     */
    int talla();
}
