package testgroup.bankimitation.service;

import java.util.List;

public interface ServiceFactory<T> {
    List<T> getAll();
    void add(T T);
    void delete (T T);
    void edit (T T);
    T getById (int id);
}
