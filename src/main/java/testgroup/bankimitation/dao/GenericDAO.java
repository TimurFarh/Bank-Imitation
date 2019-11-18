package testgroup.bankimitation.dao;

import java.util.List;

public interface GenericDAO<T> {
    List<T> getAll();
    void add(T object);
    void delete (T object);
    void edit (T object);
    T getById (int id);
}
