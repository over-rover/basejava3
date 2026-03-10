package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

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
        if (size >= STORAGE_LIMIT - 1)
            System.out.println("SAVE ERROR: " + uuid + " не добавлено. Хранилище заполнено");

        int index = findIndex(uuid);
        if (index == -1) {
            storage[size] = r;
            size++;
        } else
            System.out.println("SAVE ERROR: " + uuid + " уже существует");
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        } else
            System.out.println("DELETE ERROR: " + uuid + " не существует");
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}