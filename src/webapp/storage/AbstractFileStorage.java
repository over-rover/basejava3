package webapp.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import webapp.exception.StorageException;
import webapp.model.Resume;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (isEmpty(files)) {
            return;
        }
        for (File file : files) {
            file.delete();
        }
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        if (isEmpty(files)) {
            return 0;
        }
        return files.length;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", r.getUuid(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", "", e);
        }
    }

    @Override
    protected List<Resume> getListStorage() {
        File[] files = directory.listFiles();
        if (isEmpty(files)) {
            return new ArrayList<>();
        }
        try {
            List<Resume> resumes = new ArrayList<>();
            for (File file : files) {
                resumes.add(doRead(file));
            }
            return resumes;
        } catch (IOException e) {
            throw new StorageException("IO error", "", e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    private boolean isEmpty(File[] files) {
        return files == null || files.length == 0;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}