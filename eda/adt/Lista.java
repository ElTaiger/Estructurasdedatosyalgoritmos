package eda.adt;

import eda.exceptions.IndiceFueraDeRango;

/**
 * Interfaz basica para manejar una lista por posiciones.
 *
 * @param <E> tipo de dato que guarda la lista
 */
public interface Lista<E> extends Iterable<E> {
    /**
     * Inserta un dato en la posicion indicada.
     *
     * @param pos posicion donde se inserta (0 a talla)
     * @param dato elemento a insertar
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    void insertar(int pos, E dato) throws IndiceFueraDeRango;

    /**
     * Elimina el dato que hay en una posicion.
     *
     * @param pos posicion a borrar
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    void eliminar(int pos) throws IndiceFueraDeRango;

    /**
     * Recupera el dato que hay en una posicion.
     *
     * @param pos posicion que se quiere leer
     * @return dato en esa posicion
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    E recuperar(int pos) throws IndiceFueraDeRango;

    /**
     * Busca un dato en la lista.
     *
     * @param dato elemento a buscar
     * @return posicion donde esta el dato, o -1 si no aparece
     */
    int buscar(E dato);

    /**
     * Dice si la lista esta vacia.
     *
     * @return true si no hay datos, false si hay alguno
     */
    boolean esVacia();

    /**
     * Devuelve cuantos elementos hay en la lista.
     *
     * @return numero de elementos actuales
     */
    int talla();
}
