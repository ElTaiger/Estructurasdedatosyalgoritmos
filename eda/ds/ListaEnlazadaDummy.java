package eda.ds;

import eda.adt.Lista;
import eda.exceptions.IndiceFueraDeRango;
import java.util.Iterator;

/**
 * Implementacion de lista enlazada con nodo dummy al inicio.
 *
 * @param <E> tipo de dato que guarda la lista
 */
public class ListaEnlazadaDummy<E> implements Lista<E> {
    
    private class Nodo {
        E dato;
        Nodo siguiente;
        
        Nodo(E dato, Nodo siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    // nodo "falso" al inicio para no complicar el caso 0
    private Nodo dummy;
    private int talla;

    /**
     * Crea una lista vacia con nodo dummy inicial.
     */
    public ListaEnlazadaDummy() {
        dummy = new Nodo(null, null);
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
        if (pos < 0 || pos > talla) throw new IndiceFueraDeRango();
        
        Nodo previo = dummy;
        for (int i = 0; i < pos; i++) {
            previo = previo.siguiente;
        }
        
        previo.siguiente = new Nodo(dato, previo.siguiente);
        talla++;
    }

    /**
     * Elimina el elemento de la posicion indicada.
     *
     * @param pos posicion a eliminar
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    @Override
    public void eliminar(int pos) throws IndiceFueraDeRango {
        if (pos < 0 || pos >= talla) throw new IndiceFueraDeRango();
        
        Nodo previo = dummy;
        for (int i = 0; i < pos; i++) {
            previo = previo.siguiente;
        }
        
        previo.siguiente = previo.siguiente.siguiente;
        talla--;
    }

    /**
     * Recupera el elemento de una posicion.
     *
     * @param pos posicion que se quiere leer
     * @return dato en esa posicion
     * @throws IndiceFueraDeRango si la posicion no es valida
     */
    @Override
    public E recuperar(int pos) throws IndiceFueraDeRango {
        if (pos < 0 || pos >= talla) throw new IndiceFueraDeRango();
        
        Nodo actual = dummy.siguiente;
        for (int i = 0; i < pos; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    /**
     * Busca un dato y devuelve su posicion.
     *
     * @param dato elemento a buscar
     * @return posicion donde esta, o -1 si no aparece
     */
    @Override
    public int buscar(E dato) {
        Nodo actual = dummy.siguiente;
        int pos = 0;
        while (actual != null) {
            if (actual.dato.equals(dato)) return pos;
            actual = actual.siguiente;
            pos++;
        }
        return -1;
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
     * Devuelve cuantos elementos hay guardados.
     *
     * @return numero de elementos
     */
    @Override
    public int talla() {
        return talla;
    }

    /**
     * Devuelve un iterador que recorre la lista de inicio a fin.
     *
     * @return iterador de la lista
     */
    @Override
    public Iterator<E> iterator() {
        return new MiIteradorDummy();
    }

    private class MiIteradorDummy implements Iterator<E> {
        // recorre desde el primer nodo con dato
        private Nodo actual = dummy.siguiente;

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public E next() {
            E dato = actual.dato;
            actual = actual.siguiente;
            return dato;
        }
    }
}
