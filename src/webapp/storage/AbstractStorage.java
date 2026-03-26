package webapp.storage;

import webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public static final String NO_SUCH_UUID = "null";

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        checkIfExist(r, searchKey);
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        checkIfNotExist(searchKey);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        checkIfNotExist(searchKey);
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        checkIfNotExist(searchKey);
        doDelete(searchKey);
    }

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract void clear();

    @Override
    public abstract int size();

    protected abstract void checkIfExist(Resume r, Object searchKey);

    protected abstract void checkIfNotExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);
}