package webapp.storage;

import java.util.Comparator;
import java.util.List;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    public void save(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        throwIfExistInStorage(searchKey, r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKey(uuid);
        throwIfNotExistInStorage(searchKey, uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> listStorage = getListStorage();
        listStorage.sort(RESUME_COMPARATOR);
        return listStorage;
    }

    @Override
    public void update(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        throwIfNotExistInStorage(searchKey, r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKey(uuid);
        throwIfNotExistInStorage(searchKey, uuid);
        doDelete(searchKey);
    }

    private void throwIfExistInStorage(SK searchKey, String uuid) {
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
    }

    private void throwIfNotExistInStorage(SK searchKey, String uuid) {
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> getListStorage();

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);
}