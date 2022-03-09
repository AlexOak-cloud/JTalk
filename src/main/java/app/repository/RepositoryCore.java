package app.repository;

import app.entity.User;

import java.io.File;
import java.util.List;

public interface RepositoryCore<T> {

    void save(T t);

    T getById(long id);

    T getById(int id);

    List<T> getAllByUser(User user);

    void deleteById(long id);

    void deleteById(int id);
}
