package webapp.storage;

import java.util.ArrayList;
import java.util.List;
import webapp.model.Resume;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return storage.indexOf(r);
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.set(storage.indexOf(r), r);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove((int) index);
    }
}