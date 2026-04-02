package webapp.storage;

import java.util.Arrays;
import java.util.Comparator;
import webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR =
            (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void addResume(Resume r, Object index) {
        int insertIndex = -(int) index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void removeResume(Object index) {
        int removeIndex = (int) index;
        System.arraycopy(storage, removeIndex + 1, storage, removeIndex, size - removeIndex - 1);
    }
}