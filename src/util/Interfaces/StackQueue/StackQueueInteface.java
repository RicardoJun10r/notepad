package util.Interfaces.StackQueue;

import util.Interfaces.CollectionInteface;

/**
 * <h1>StackQueueInteface</h1> é uma interface {@code StackQueueInteface} que
 * extende de {@link CollectionInteface}, adicionando métodos
 * específicos para as seguintes classes:
 * 
 * @see MinhaStack
 * @see MinhaQueue
 */
public interface StackQueueInteface<T> extends CollectionInteface<T> {
    public boolean isFull();

    public boolean isEmpty();
}
