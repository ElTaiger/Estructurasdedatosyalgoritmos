package eda.ds;

import eda.adt.Lista;
import eda.exceptions.IndiceFueraDeRango;
import java.util.Iterator;

/**
 * Implementacion de lista usando un array dinamico.
 *
 * @param <E> tipo de dato que guarda la lista
 */
public class ListaArray<E> implements Lista<E> {
    
    private E[] array;
    private int talla;

    /**
     * Crea una lista vacia.
     */
    @SuppressWarnings("unchecked")
    public ListaArray() {
        array = (E[]) new Object[6]; // Empezamos con una caja de 5 huecos
        talla = 0;
    }

    /**
     * Inserta un elemento en la posicion indicada.
     *
     * @param pos posicion donde insertar
     * @param dato elemento a insertar
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    @Override
    public void insertar(int pos, E dato) throws IndiceFueraDeRango {
        // Comprobamos que no intenten insertar en posiciones raras
        if (pos < 0 || pos > talla) {
            throw new IndiceFueraDeRango();
        }
        
        // Si la caja está llena, la hacemos más grande
        if (talla == array.length) {
            crecerArray();
        }

        // Damos un "paso a la derecha" a los elementos para hacer el hueco
        for (int i = talla; i > pos; i--) {
            array[i] = array[i - 1];
        }
        
        // Ponemos el dato en su hueco y aumentamos la talla
        array[pos] = dato;
        talla++;
    }

    /**
     * Elimina el elemento de una posicion.
     *
     * @param pos posicion a eliminar
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    @Override
    public void eliminar(int pos) throws IndiceFueraDeRango {
        if (pos < 0 || pos >= talla) {
            throw new IndiceFueraDeRango();
        }

        // Damos un "paso a la izquierda" para rellenar el hueco que vamos a borrar
        for (int i = pos; i < talla - 1; i++) {
            array[i] = array[i + 1];
        }
        
        talla--;
        array[talla] = null; // Borramos el último de verdad para no gastar memoria
    }

    /**
     * Devuelve el elemento en una posicion.
     *
     * @param pos posicion que se quiere leer
     * @return dato en esa posicion
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    @Override
    public E recuperar(int pos) throws IndiceFueraDeRango {
        if (pos < 0 || pos >= talla) {
            throw new IndiceFueraDeRango();
        }
        return array[pos];
    }

    /**
     * Busca un dato y devuelve su posicion.
     *
     * @param dato elemento a buscar
     * @return posicion del dato, o -1 si no aparece
     */
    @Override
    public int buscar(E dato) {
        // Miramos posición a posición si está el dato
        for (int i = 0; i < talla; i++) {
            if (array[i].equals(dato)) {
                return i; // Devolvemos la posición donde lo hemos encontrado
            }
        }
        return -1; // Devolvemos -1 si no existe en toda la lista
    }

    /**
     * Indica si la lista esta vacia.
     *
     * @return true si no hay elementos
     */
    @Override
    public boolean esVacia() {
        return talla == 0;
    }

    /**
     * Devuelve cuantos elementos hay en la lista.
     *
     * @return numero de elementos
     */
    @Override
    public int talla() {
        return talla;
    }

    @SuppressWarnings("unchecked")
    private void crecerArray() {
        E[] cajaNueva = (E[]) new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            cajaNueva[i] = array[i];
        }
        array = cajaNueva;
    }

    /**
     * Devuelve un iterador para recorrer la lista desde la posicion 0.
     *
     * @return iterador de la lista
     */
    @Override
    public Iterator<E> iterator() {
        return new MiIteradorLista();
    }

    private class MiIteradorLista implements Iterator<E> {
        private int posicionActual = 0;

        @Override
        public boolean hasNext() {
            return posicionActual < talla;
        }

        @Override
        public E next() {
            E dato = array[posicionActual];
            posicionActual++;
            return dato;
        }
    }
}
