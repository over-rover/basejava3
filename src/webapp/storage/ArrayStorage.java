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
    protected void doInsert(Resume r, Object index) {
        checkIfOverflow(r);
        storage[size] = r;
    }

    @Override
    protected void doRemove(Object index) {
        storage[(int) index] = storage[size - 1];
    }
}