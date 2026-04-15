package webapp.storage;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getUuid).thenComparing(Resume::getFullName);

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume r) {
        // LOG.info("Save " + r.getUuid());
        SK searchKey = findSearchKey(r.getUuid());
        throwIfExistInStorage(searchKey, r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        // LOG.info("Get " + uuid);
        SK searchKey = findSearchKey(uuid);
        throwIfNotExistInStorage(searchKey, uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        // LOG.info("Get all sorted");
        List<Resume> resumes = getListStorage();
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }

    @Override
    public void update(Resume r) {
        // LOG.info("Update " + r.getUuid());
        SK searchKey = findSearchKey(r.getUuid());
        throwIfNotExistInStorage(searchKey, r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        // LOG.info("Delete " + uuid);
        SK searchKey = findSearchKey(uuid);
        throwIfNotExistInStorage(searchKey, uuid);
        doDelete(searchKey);
    }

    private void throwIfExistInStorage(SK searchKey, String uuid) {
        if (isExist(searchKey)) {
            // LOG.warning("Резюме " + uuid + " уже существует!");
            throw new ExistStorageException(uuid);
        }
    }

    private void throwIfNotExistInStorage(SK searchKey, String uuid) {
        if (!isExist(searchKey)) {
            // LOG.warning("Резюме " + uuid + " не существует!");
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract SK findSearchKey(String uuid);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> getListStorage();

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);
}