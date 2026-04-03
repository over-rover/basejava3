package webapp.storage;

import java.util.List;
import webapp.model.Resume;

public interface Storage {
    void save(Resume r);

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void update(Resume r);

    void delete(String uuid);

    void clear();

    int size();
}