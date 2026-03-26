package webapp.storage;

import java.util.Arrays;
import webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        checkIfOverflow(r);
        int insertIndex = -(int) searchKey - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
        size++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (int) searchKey;
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }
}