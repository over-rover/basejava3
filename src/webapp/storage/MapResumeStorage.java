package webapp.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import webapp.model.Resume;

public class MapResumeStorage extends AbstractStorage {
    protected final Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> listStorage = new ArrayList<>(storage.values());
        listStorage.sort(RESUME_COMPARATOR);
        return listStorage;
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
        return uuid.hashCode();
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put((Integer) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace((Integer) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((Integer) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((Integer) searchKey);
    }
}