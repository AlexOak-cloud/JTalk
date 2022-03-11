package app.repository;

import app.entity.User;

import java.io.File;
import java.util.List;

public interface RepositoryCore<T> {

    void save(File file);

    T getById(long id);

    T getById(int id);

    List<T> getAllByUser(User user);

    void deleteById(long id);

    void deleteById(int id);
}
