package webapp.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import webapp.model.Resume;

public class MapResumeStorage extends AbstractStorage<Resume> {
    protected final Map<String, Resume> storage = new HashMap<>();

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
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doSave(Resume r, Resume searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return storage.get(searchKey.getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Resume searchKey) {
        storage.replace(searchKey.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }
}