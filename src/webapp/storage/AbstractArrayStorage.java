package webapp.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import webapp.exception.StorageException;
import webapp.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public List<Resume> getListStorage() {
        return new ArrayList<>(List.of(Arrays.copyOf(storage, size)));
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
    protected void doSave(Resume r, Integer index) {
        throwIfOverflow(r);
        addResume(r, index);
        size++;
    }

    protected void throwIfOverflow(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("SAVE ERROR: Хранилище заполнено", r.getUuid());
        }
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected void doDelete(Integer index) {
        removeResume(index);
        size--;
        storage[size] = null;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void addResume(Resume r, Integer index);

    protected abstract void removeResume(Integer index);
}