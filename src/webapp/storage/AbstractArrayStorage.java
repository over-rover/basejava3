package webapp.storage;

import webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
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

    protected abstract int findIndex(String uuid);
}