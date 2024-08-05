package data;

import java.util.Collection;

public interface CrudRepository<T> {
    void add(T item);

    T get(int id);

    void update(T item);

    void delete(int id);

    Collection<T> getAll();
}