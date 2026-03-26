package webapp.storage;

import java.util.Arrays;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
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

    protected void checkIfOverflow(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("SAVE ERROR: Хранилище заполнено", r.getUuid());
        }
    }

    @Override
    protected void checkIfExist(Resume r, Object searchKey) {
        if ((int) searchKey >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    protected void checkIfNotExist(Object searchKey) {
        int index = (int) searchKey;
        if (index < 0) {
            throw new NotExistStorageException(String.valueOf(index));
        }
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);
}