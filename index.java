public interface List<E> extends Iterable<E> { void
insert(int pos, E data) throws WrongIndexException; void
delete(int pos) throws WrongIndexException;
 E get(int pos) throws WrongIndexException;
 int search(E data);
 Iterator<E> iterator();
int size(); }




public class ListImpl <E> implements List<E> {
... private static class
Node<E>{…}
private class CIterator implements Iterator<E>{…}
...
}

public class TestLista <E> implements List<E> {



    
}