package app.repository;

import app.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface RepositoryCore<T> {

    void save(MultipartFile file);

    T getById(long id);

    T getById(int id);

    List<T> getAllByUser(User user);

    void deleteById(long id);

    void deleteById(int id);
}
