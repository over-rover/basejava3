package webapp.storage;

import java.util.Arrays;
import webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void addResume(Resume r, Integer index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void removeResume(Integer index) {
        int removeIndex = index;
        System.arraycopy(storage, removeIndex + 1, storage, removeIndex, size - removeIndex - 1);
    }
}