package webapp.storage;

import java.util.Arrays;
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

    @Override
    protected void doSave(Resume r, Object index) {
        checkCapacity(r);
        addResume(r, index);
        size++;
    }

    protected void checkCapacity(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("SAVE ERROR: Хранилище заполнено", r.getUuid());
        }
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(int) index] = r;
    }

    @Override
    protected void doDelete(Object index) {
        removeResume(index);
        size--;
        storage[size] = null;
    }

    protected abstract void addResume(Resume r, Object index);

    protected abstract void removeResume(Object index);
}