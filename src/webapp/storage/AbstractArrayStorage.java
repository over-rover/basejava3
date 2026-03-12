package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        if (size >= STORAGE_LIMIT)
            throw new StorageException("SAVE ERROR: Хранилище заполнено", uuid);

        int index = findIndex(uuid);
        if (index < 0) {
            doSave(r, index);
            size++;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0)
            return storage[index];
        else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            size--;
            storage[size] = null;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void doSave(Resume r, int index);

    protected abstract void doDelete(int index);
}