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
    protected void doInsert(Resume r, Object index) {
        checkIfOverflow(r);
        int insertIndex = -(int) index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void doRemove(Object index) {
        int removeIndex = (int) index;
        System.arraycopy(storage, removeIndex + 1, storage, removeIndex, size - removeIndex - 1);
    }
}