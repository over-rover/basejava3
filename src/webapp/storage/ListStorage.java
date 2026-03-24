package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import java.util.ArrayList;
import java.util.List;
import webapp.model.Resume;

public class ListStorage implements Storage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void save(Resume r) {
        if (storage.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    @Override
    public Resume get(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void update(Resume r) {
        if (!storage.contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage.set(storage.indexOf(r), r);
    }

    @Override
    public void delete(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                storage.remove(r);
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
