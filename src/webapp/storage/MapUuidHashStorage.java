package webapp.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import webapp.model.Resume;

public class MapUuidHashStorage extends AbstractStorage<Integer> {
    protected final Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public List<Resume> getListStorage() {
        return new ArrayList<>(storage.values());
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
    protected Integer getSearchKey(String uuid) {
        return uuid.hashCode();
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        storage.put(searchKey, r);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage.replace(searchKey, r);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return storage.containsKey(searchKey);
    }
}