package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0)
            storage[index] = r;
        else
            System.out.println("UPDATE ERROR: " + r.getUuid() + " не существует");
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        if (size >= STORAGE_LIMIT)
            System.out.println("SAVE ERROR: " + uuid + " не добавлено. Хранилище заполнено");

        int index = findIndex(uuid);
        if (index < 0) {
            doSave(r, index);
            size++;
        } else
            System.out.println("SAVE ERROR: " + uuid + " уже существует");
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0)
            return storage[index];
        else {
            System.out.println("GET ERROR: " + uuid + " не существует");
            return null;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            size--;
        } else
            System.out.println("DELETE ERROR: " + uuid + " не существует");
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void doSave(Resume r, int index);

    protected abstract void doDelete(int index);
}