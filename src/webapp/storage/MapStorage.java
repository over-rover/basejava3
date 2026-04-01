package webapp.storage;

import java.util.HashMap;
import java.util.Map;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace((String) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected void checkIfExist(Resume r, Object searchKey) {
        if (storage.containsKey((String) searchKey)) {
            throw new ExistStorageException((String) searchKey);
        }
    }

    @Override
    protected void checkIfNotExist(Object searchKey) {
        if (!storage.containsKey((String) searchKey)) {
            throw new NotExistStorageException((String) searchKey);
        }
    }
}