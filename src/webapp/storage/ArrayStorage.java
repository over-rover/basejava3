package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndexIfExist(r.getUuid());
        if (index >= 0)
            storage[index] = r;
        else
            System.out.println("UPDATE ERROR: " + r.getUuid() + " не существует");
    }

    public void save(Resume r) {
        if (size == storage.length - 1)
            System.out.println("SAVE ERROR: " + r.getUuid() + " хранилище переполнено]");

        if (!isExist(r)) {
            storage[size] = r;
            size++;
        } else
            System.out.println("SAVE ERROR: " + r.getUuid() + " уже существует");
    }

    public Resume get(String uuid) {
        int index = getIndexIfExist(uuid);
        if (index >= 0)
            return storage[index];
        else {
            System.out.println("GET ERROR: " + uuid + " не существует");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndexIfExist(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else
            System.out.println("DELETE ERROR: " + uuid + " не существует");

    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndexIfExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }

    private boolean isExist(Resume r) {
        return getIndexIfExist(r.getUuid()) >= 0;
    }
}