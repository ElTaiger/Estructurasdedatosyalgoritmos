package eda.adt;

import eda.exceptions.ColaVacia;

/**
 * Interfaz basica de una cola (FIFO: primero en entrar, primero en salir).
 *
 * @param <E> tipo de dato que se guarda en la cola
 */
public interface Cola<E> extends Iterable<E> {
    /**
     * Mete un dato al final de la cola.
     *
     * @param dato elemento que se quiere anadir
     */
    void encolar(E dato);

    /**
     * Quita el primer elemento de la cola.
     *
     * @throws ColaVacia si no hay elementos
     */
    void desencolar() throws ColaVacia;

    /**
     * Devuelve el primer elemento sin quitarlo.
     *
     * @return primer elemento de la cola
     * @throws ColaVacia si no hay elementos
     */
    E primero() throws ColaVacia;

    /**
     * Dice si la cola esta vacia.
     *
     * @return true si no hay datos, false si hay alguno
     */
    boolean esVacia();

    /**
     * Devuelve cuantos elementos hay en la cola.
     *
     * @return numero de elementos actuales
     */
    int talla();
}
