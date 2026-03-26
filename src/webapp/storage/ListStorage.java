package webapp.storage;

import java.util.ArrayList;
import java.util.List;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
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
    protected void checkIfExist(Resume r, Object searchKey) {
        if (storage.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    protected void checkIfNotExist(Object searchKey) {
        String uuid = (String) searchKey;
        if (uuid.equals(NO_SUCH_UUID)) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return uuid;
            }
        }
        return NO_SUCH_UUID;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        String uuid = (String) searchKey;
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.set(storage.indexOf(r), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        String uuid = (String) searchKey;
        Resume resumeToDelete = null;
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                resumeToDelete = r;
                break;
            }
        }
        storage.remove(resumeToDelete);
    }
}