package webapp.storage;

import webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        checkIfOverflow(r);
        storage[size] = r;
        size++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(int) searchKey] = storage[size - 1];
        size--;
    }
}