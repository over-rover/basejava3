package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        throwIfExistInStorage(searchKey, r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        throwIfNotExistInStorage(searchKey, uuid);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        throwIfNotExistInStorage(searchKey, r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        throwIfNotExistInStorage(searchKey, uuid);
        doDelete(searchKey);
    }

    private void throwIfExistInStorage(Object searchKey, String uuid) {
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
    }

    private void throwIfNotExistInStorage(Object searchKey, String uuid) {
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);
}