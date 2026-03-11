package webapp.storage;

import webapp.model.Resume;

public interface Storage {
    void save(Resume r);

    Resume get(String uuid);

    Resume[] getAll();

    void update(Resume r);

    void delete(String uuid);

    void clear();

    int size();
}